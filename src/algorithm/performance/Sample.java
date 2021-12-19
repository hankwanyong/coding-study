package algorithm.performance;

public class Sample {

	public static void main(String[] args) {
		// Garbage Collection으로 메모리 초기화
 		System.gc(); 
 		
 		// 실행전 메모리 사용량 조회
 		long before = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
 		
 		// 측정하고싶은 코드
 		long st = System.currentTimeMillis();
		
 		// 테스트하고 싶은 메서드
 		
        long et = System.currentTimeMillis();
 		
 		// Garbage Collection으로 메모리 정리
 		System.gc();
 		
 		// 실행 후 메모리 사용량 조회
 		long after  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
 		
 		// 메모리 사용량 측정
 		long usedMemory = (before - after)/1024/1024;

 		System.out.println("메모리 사용량 : " + usedMemory + "MB");
 		System.out.println("프로그램 실행 시간 : " + (et - st)+" ms");
	}

}
