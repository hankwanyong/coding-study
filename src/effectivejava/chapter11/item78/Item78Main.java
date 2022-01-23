package effectivejava.chapter11.item78;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
/**
 * 공유 중인 가변 데이터는 동기화해 사용하라.
 * @author 박민영
 *
 */
public class Item78Main {
	/*
	 * syncronized 키워드는 해당 메서드나 블록을 한번에 한 스레드씩 수행하도록 보장한다.
	 * 많은 프로그래머가 동기화를 배타적 실행, 즉 한 스레드가 변경하는 중이라서 상태가 일관되지 않은 순간의 객체를 다른 스레드가 보지 못하게 막는 용도로만 생각한다.
	 * 
	 * 맞는 설명이지만, 동기화에는 중요한 기능이 하나 더 있다.
	 * 동기화 없이는 한 스레드가 만든 변화를 다른 스레드에서 확인하지 못할 수 있다.
	 * 동기화는 일관성이 깨진 상태를 볼 수 없게 하는 것은 물론, 동기화된 메서드나 블록에 들어간 스레드가 같은 락의 보호하에 수행된 모든 이전 수정의 최종 결과를 보게 해준다.
	 * 
	 * 언어 명세상 long과 double 외의 변수를 읽고 쓰는 동작은 원자적(atomic)이다.
	 * 여러 스레드가 같은 변수를 동기화 없이 수정하는 중이도, 항상 어떤 스레드가 정상적으로 저장한 값을 온전히 읽어옴을 보장한다는 뜻이다.
	 * 
	 * 이 말을 듣고 "성능을 높이려면 원자적 데이터를 읽고 쓸 때는 동기화하지 말아야겠다"고 생각하기 쉬운데, 아주 위험한 발상이다.
	 * 자바 언어 명세는 스레드가 필드를 읽을 때 항상 '수정이 완전히 반영된'값을 얻는다고 보장하지만,
	 * 한 스레드가 저장한 값이 다른 스레드에게 '보이는가'는 보장하지 않는다.
	 * 동기화는 배타적 실행뿐 아니라 스레드 사이의 안정적인 통신에 꼭 필요하다.
	 * 
	 * 공유 중인 가변 데이터를 비록 원자적으로 읽고 쓸 수 있을지라도 동기화에 실패하면 처참한 결과로 이어질 수 있다.
	 * 다른 스레드를 멈추는 작업을 생각해 보자
	 * */
	private static boolean stopRequest;
	
	public static void main(String[] args) throws InterruptedException {
		Thread backgroundThread = new Thread(() ->  {
			int i = 0;
			while (!stopRequest) {
				i++;
				System.out.println(i);
			}
		});
		
		backgroundThread.start();
		
		TimeUnit.SECONDS.sleep(1);
		stopRequest = true;
		
		
		
		/*
		 * 위 프로그램이 1초뒤에 끝나지 않는 이유는 동기화에 있다.
		 * 동기화 하지 않으면 메인 스레드가 수정한 값을 백그라운드 스레드가 언제쯤에나 보게 될 지 보증할 수 없다.
		 * 동기화가 빠지면 가상머신이 다음과 같은 최적화를 수행할 수도 있는 것이다.
		 * */
		
		//원래코드
//		while (!stopRequest) {
//			i++;
//		}
		
		//최적화 코드
//		if (!stopRequest) {
//			while(true) {
//				i++;
//			}
//		}
		
		/*
		 * OpenJDK 서버 VM이 실제로 적용하는 끌어올리기 라는 최적화 기법이다.
		 * 이 결과 프로그램은 응답불가 상태가 되어 더 이상 진전이 없다.
		 * stopRequest를 동기화 해 접근하려면 이 문제를 해결할 수 있다.
		 * */
		StopThread.main(args);
		
		
		/*
		 * 쓰기와 읽기 메서드 모두 동기화 했음에 주목하자.
		 * 쓰기만 동기화해서는 충분하지 않다.
		 * 쓰기와 읽기 모두가 동기화되지 않으면 동작은 보장하지 않는다.
		 * 
		 * 반복문에서 매번 동기화 하는 비용이 크진 않지만 더 빠른 대안이 있다.
		 * stopRequest 필드를 volatile으로 선언하면 동기화를 생략해도 된다.
		 * volatile 한정자는 배타적 수행과는 상관없지만 항상 가장 최근에 기록된 값을 읽게 됨을 보장한다.
		 * */
		StopThread2.main(args);
		
	}
	/*
	 * volatile은 배타적 수행과는 상관이 없어서 시퀀스 용도로 사용하면 문제가 있다.
	 * */
	private static volatile int nextSerialNumber = 0;
	
	public static int generateSerialNumber() {
		return nextSerialNumber++;
	}
	
	/*
	 * 증가 연산자(++)는 코드상으론 하나지만 실제로는 필드에 두번 접근한다.
	 * 먼저 값을 읽고, 그런 다음 새로운 값을 저장하는 것이기 때문에 두 번째 스레드가 이 두 접근 사이를 비집고 들어와 값을 읽어가면 첫번째 스레드와 같은 값을 돌려받게 된다.
	 * generateSerialNumber 메서드에 synchronized 한정자를 붙이면 이 문제가 해결된다.
	 * 메서드에 synchronized를 붙였다면 nextSerialNumber필드에 volatile를 제거해야 한다.
	 * 
	 * AtomicLong 을 사용한다면 락 없이도 스레드 안전한 프로그래밍을 할 수 있다.
	 * 
	 * 
	 * */
	
	private static final AtomicLong nextSerialNum = new AtomicLong();
	
	public static long generateSerialNum() {
		return nextSerialNum.getAndIncrement();
	}
	
	/*
	 * 이번 아이템에서 언급한 문제를 피하는 가장 좋은 방법은 물론 애초에 가변데이터를 공유하지 않는것.
	 * 불변 데이터만 공유하거나 아무것도 공유하지 말자.
	 * 다시 말해 가변 데이터는 단일 스레드에서만 쓰도록 하자.
	 * 
	 * 
	 * 여러 스레드가 가변 데이터를 공유한다면 그 데이터를 익고 읽고쓰는 동작은 반드시 동기화 해야 한다.
	 * 동기화 하지 않으면 한 스레드가 수행한 변경을 다른 스레드가 보지 못할 수도 있다.
	 * 공유되는 가변 데이터를 동기화하는데 실패하면 응답 불가 상태에 빠지거나 안전 실패로 이어질 수 있다.
	 * 이는 디버깅 난이도가 가장 높은 문제에 속한다.
	 * 간헐적이거나 특정 타이밍에만 발생할 수 있고, VM에 따라 현상이 달라지기도 한다.
	 * 배타적 실행은 필요없고 스레드끼리의 통신만 필요하다면 volatile 한정자만으로 동기화 할 수 있다.
	 * 다만 올바로 사용하기가 까다롭다.
	 * 
	 * */
	
	
	
}


