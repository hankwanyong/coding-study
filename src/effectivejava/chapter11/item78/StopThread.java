package effectivejava.chapter11.item78;

import java.util.concurrent.TimeUnit;

public class StopThread {
	private static boolean stopRequest;
	
	private static synchronized void requestStop() {
		stopRequest = true;
	}
	
	private static synchronized boolean stopRequest() {
		return stopRequest;
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		Thread backgroundThread = new Thread(() ->  {
			int i = 0;
			while (!stopRequest()) {
				i++;
				System.out.println(i);
			}
		});
		
		backgroundThread.start();
		
		TimeUnit.SECONDS.sleep(1);
		requestStop();
	}
}
