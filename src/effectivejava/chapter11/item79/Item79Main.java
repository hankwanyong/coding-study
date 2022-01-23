package effectivejava.chapter11.item79;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 과도한 동기화는 피하라
 * @author 박민영
 *
 */
public class Item79Main {
	/*
	 * 과도한 동기화는 성능을 떨어뜨리고, 교착 상태에 빠뜨리고, 심지어 예측할 수 없는 동작을 낳기도 한다. 
	 * 
	 * 응답 불가와 안전 실패를 피하려면 동기화 메서드나 동기화 블록 안에서는 제어를 절대로 클라이언트에 양도하면 안 된다.
	 * 예를 들어 동기화된 영역 안에서는 재정의할 수 있는 메서드는 호출하면 안되며, 클라이언트가 넘겨준 함수 객체를 호출해서도 안된다.
	 * 동기화된 영역을 포함한 클래스 관점에서는 이런 메서드는 모두 바깥 세상에서 온 외계인이다.
	 * 외계인 메서드가 하는일에 따라 동기화된 영역은 예외를 일으키거나, 교착상태에 빠지거나, 데이터를 훼손할 수 있다.
	 * 
	 * 구체적인 예를 보자.
	 * 어떤 집합을 감싼 래퍼 클래스고, 이 클래스의 클라이언트는 집합에 원소가 추가되면 알림을 받을수 있다.(관찰자 패턴)
	 * 
	 * 눈으로 보기에 이상없다. 다음은 0부터 99를 출력한다
	 * */
	public static void main(String[] args) {
		ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());
//		set.addObserver((s, e) -> System.out.println(e));
//		for(int i = 0; i<100;i++) {
//			set.add(i);
//		}
		
		/*
		 * 평상시에넌 앞서와 같이 집합에 추가된 정숫값을 출력하다가, 그 값이 23이면 자기 자신을 제거하는 관찰자를 추가해보자
		 * */
		set = new ObservableSet<>(new HashSet<>());
		set.addObserver(new SetObserver<Integer>() {
			
			@Override
			public void added(ObservableSet<Integer> observableSet, Integer element) {
				System.out.println(element);
				if(element == 23) {
					observableSet.removeObserver(this);
				}
			}
		});
		for(int i = 0; i<100;i++) {
			set.add(i);
		}
		
		/*
		 * 프로그램은 23까지 출력한 후 ConcurrentModificationException 을 던진다.
		 * 관찰자의 added메서드 호출이 일어난 시점이 notifyElementAdded가 관찰자들의 리스를 순회하는 도중이기 때문이다.
		 * added메서드는 ObservableSet의 removeObserver메소드를 호출하고 이 메서드는 다시 observer.remove메서드를 호출한다.
		 * 리스트를 원소에서 제거하려는데 마침 지금은 이 리스트를 순회하는 도중이다. 즉 허용되지 않는 동작이다.
		 * */
		
		
		set.addObserver(new SetObserver<Integer>() {
			
			@Override
			public void added(ObservableSet<Integer> s, Integer e) {
				System.out.println(e);
				if(e == 23) {
					ExecutorService exec = Executors.newSingleThreadExecutor();
					try {
						exec.submit(() -> s.removeObserver(this)).get();
					} catch (Exception e2) {
						// TODO: handle exception
					} finally {
						exec.shutdown();
					}
				}
			}
		});
		
		/*
		 * 위 프로그램은 나쁘지 않지만 교착상채에 빠진다.
		 * 백그라운드 스레드가 s.removeObserver 를 호출하면 관찰자를 잠그려 시도하지만 락은 얻을 수 없다.
		 * 메인 스레드가 이미 락을 쥐고 있기 때문이다.
		 * 그와 동시에 메인 스레드는 백그라운드 스레드가 관찰자를 제거하기만을 기다리는 중이다.
		 * 
		 * notifyElementAdded 메서드를 수정하여 해결할 수 있다. 외계인 메소드 호출을 동기화 블록 밖으로 옮기면 된다.
		 *  
		 * */
		
		
	}
	/*
	 * 기본 규칙은 동기화 영역에서는 가능한 한 일을 적게 하는것이다.
	 * 
	 * 
	 * 자바의 동기화 비용은 빠르게 낮아져 왔지만 과도한 동기화는 성능을 악화시킬 수 있다.
	 * 
	 * 가변 클래스를 작성하려거든 두가지 선택지 중 하나를 따르자.
	 * 1. 동기화를 전혀 하지말고 그 클래스를 동시에 사용해야 하는 클래스가 외부에서 알아서 동기화하게 하자.
	 * 2. 동기화를 내부에서 수행해 스레드 안전한 클래스로 만들자
	 * 
	 * 
	 * 
	 * 교착상태와 데이터 훼손을 피하려면 동기화 영역 안에서 외계인 메서드를 절대 호출하지 말자.
	 * 일반화해 이야기하면, 동기화 영역 안에서의 작업은 최소한으로 줄이자.
	 * 가변 클래스를 설계할 때는 스스로 동기화해야 할지 고민하자.
	 * 멀티코어 세상인 지금은 과도한 동기화를 피하는 게 과거 어느때보다 중요하다.
	 * 합당한 이유가 있을 때만 내부에서 동기화 하고, 동기화했는지 여부를 문서에 정확히 밝히자.
	 * */
}
