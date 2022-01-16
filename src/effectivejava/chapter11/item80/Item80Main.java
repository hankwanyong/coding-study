package effectivejava.chapter11.item80;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 스레드보다는 실행자, 태스크, 스트림을 애용하라
 * 
 * @author 박민영
 *
 */
public class Item80Main {
	/*
	 * 초판에 작성된 작업 큐(work queue)는 클라이언트가 요청한 작업을 백그라운드 스레드에 위임해 비동기적을 처리한다.
	 * 작업 큐가 필요없어지면 클라이언트는 큐에 중단을 요청할 수 있고, 그러면 큐는 남아있는 작업을 마저 완료한 후 스스로 종료한다.
	 * 해당 코드는 안전실패나 응답불가가 될 여지를 없애주는 코드가 있어 간단하지만 긴 코드가 작성되었다.
	 * 
	 * java.util.concurrent 패키지는 실행자 프레임워크라고하는 인터페이스 기반의 유연한 태스트 실행 기능을 담고있다.
	 * 그래서 초판의 작업큐보다 모든면에서 뛰어난 작업큐를 단 한줄로 생성할 수 있게 되었다. 
	 * */
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		
		/*
		 * 다음은 이 실행자에 실행할 태스크를 넘기는 방법이다
		 * */
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
			}
		};
		exec.execute(runnable);
		
		/*
		 * 다음은 실행자를 종료하는 방법이다.
		 * */
		exec.shutdown();
		
		
		/*
		 * 이외의 실행자 서비스의 주요 기능
		 * 1. 특정 태스크가 완료되기를 기다린다(코드 79-2에서 본 get메서드)
		 * 2. 태스크 모음 중 아무것 하나(invokeAny 메서드) 혹은 모든 태스크 (invokeAll 메서드)가 완료되기를 기다린다.
		 * 3. 실행자 서비스가 종료하기를 기다린다(awaitTermination 메서드)
		 * 4. 완료된 태스크들의 결과를 차례로 받는다(ExecutorCompletionService 이용)
		 * 5. 태스크를 특정 시간에 혹은 주기적으로 실행하게 한다.(ScheduledThreadPoolExecutor 이용)
		 * 
		 * 큐를 둘 이상의 스레드가 처리하게 하고싶다면 간단히 다른 정적 팩터리를 이용하여 다른 종류의 실행자 서비스를 생성하면 된다.
		 * 여러분에게 필요한 실행자 대부분은 java.util.concurrent.Executors 의 정적 팩터리를 이용해 생성할 수 있을 것이다.
		 * 평범하지 않은 실행자를 원한다면 ThreadPoolExcutor 클래스를 직접 사용해도 된다.
		 * 
		 * 실행자 서비스를 사용하기에 까다로운 애플리케이션도 있다.
		 * 작은 프로그램이나 가벼운 서버라면 Executors.newCachedThreadPool 이 일반적으로 좋은 선택일 것이다.(무거운 프로덕션 서버에는 좋지 못하다.)
		 * 무거운 프로덕션 서버에는 스레드 개수를 고정한 Executors.newFixedThreadPool을 선택하거나 완전히 통제할 수 있는   ThreadPoolExcutor를 직접 사용하는편이 낫다.
		 * 
		 * 작업큐를 손수 만드는 일은 삼가야 하고, 스레드를 직접 다루는것도 일반적으로 삼가야 한다.
		 * 
		 * */
	}
}
