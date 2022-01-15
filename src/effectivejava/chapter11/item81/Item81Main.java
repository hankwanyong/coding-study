package effectivejava.chapter11.item81;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public class Item81Main {

	
	/*
	 * Item81 wait 와 notify보다는 동시성 유틸리티를 애용하라
	 */
	public static void main(String[] args) {
		/*
		 * wait, notify, notifyAll > Object method
		 * 
		 * 메서드 		기능												3개 함수 특징
		 * wait			갖고 있던 고유 락을 해제하고, 스레드를 잠들게 한다			호출하는 스레드가 반드기 고유 락을 갖고 있어야한다.
		 * notify		잠들어 있던 스레드 중 임의로 하나를 골라 깨운다.			(synchronized 블록 내에서 호출 되어야 한다.)
		 * notifyAll	호출로 잠들어 있던 스레드 모두 깨운다						..
		 * 
		 * 오래된 함수로 구현하기도 어렵다.
		 * ConcurrenthashMap을 사용하자
		 * ConcurrentHashMap 특징
		 * 	1. get은 map과 똑같다 > read는 자유롭다
		 * 	2. put은 동시성을 고려해(버킷....어쩌구...) 동기화해준다.
		 * 
		 * CountDownLatch
		 * 	1. 생성자 래치(걸쇠) 수
		 * 	2. countDown : 래치수를 1개 감소
		 * 	3. await	 : 래치의 숫자가 0이 될 때까지 기다리는 코드
		 * 
		 */
		
		
	}
	
	public static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
		CountDownLatch ready = new CountDownLatch(concurrency);
		CountDownLatch start = new CountDownLatch(1);
		CountDownLatch done = new CountDownLatch(concurrency);
		
		for(int i=0; i < concurrency; i++) {
			executor.execute(() -> {
				// 타이머에게 준비를 마쳤음을 알린다.
				ready.countDown();
				
				try {
					// 모든 작업자 스레드가 준비될 때까지 기다린다.
					start.await();
					action.run();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} finally {
					// 타이머에게 작업을 마쳤음을 알린다.
					done.countDown();
				}
			});
		}
		ready.await();	// 모든 작업자가 준비될 때까지 기다린다.
		long startNanos = System.nanoTime();
		start.countDown();	// 작업자들을 깨운다.
		done.await();		// 모든 작업자가 일을 끝마치기를 기다린다.
		return System.nanoTime() - startNanos;
		
	}

}
