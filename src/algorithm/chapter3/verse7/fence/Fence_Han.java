package algorithm.chapter3.verse7.fence;

import java.util.Arrays;
import java.util.stream.Stream;

/*
 * 문제 : 너비가 같은 N개의 나무 판자를 붙여 새로운 울타리가 있습니다. 
 * 시간이 지남에 따라 판자들이 부러지거나 망가져 높이가 달라진 관계로 울타리를 통째로 교체하기로 했습니다.
 * 이때 버리는 울타리의 일부를 직사각형으로 잘라내 활용하고 싶습니다.
 * 울타리를 구성하는 각 판자의 높이가 주어질 때, 잘라낼 수 있는 직사각형의 최대 크기를 계산하는 프로그램을 작성하세요.
 * (단, 비스듬히 잘라낼 수는 없습니다.)
 * 
 * 풀이 : 각 판자의 높이와 그 판자가 덮을수 있는 최대길이의 곱을 구하기
 * 1. 각 판자의 왼쪽 넓이 구하기
 * 2. 각 판자의 오른쪽 널이 구하기
 * 3. 두 넓이의 합
 * (판자의 기준은 왼쪽으로 두고 오른쪽 넓이를 구할때 길이+1 할것)
 */
public class Fence_Han {

	public static void main(String[] args) {
		String testCase = "3\n7\n7 1 5 9 6 7 3\n7\n1 4 4 4 4 1 1\n4\n1 8 2 2";
        
        // Garbage Collection으로 메모리 초기화
 		System.gc(); 
 		
 		// 실행전 메모리 사용량 조회
 		long before = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
 		
 		// 측정하고싶은 코드
 		long st = System.currentTimeMillis();
		test(testCase);
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
	
	public static void test(String testCase) {
		String[] rows = testCase.split("\\n");
		
		if(rows != null && rows.length > 2 && rows.length%2 == 1) {
			int caseNo = 0;
			
			for(int caseNum=1; caseNum<rows.length; caseNum+=2) {
				++caseNo;
				int fenceCnt = Integer.parseInt(rows[caseNum]);
				int[] fenceHeights = Stream.of(rows[caseNum+1].split(" ")).mapToInt(Integer::parseInt).toArray();
				
				if(fenceCnt != fenceHeights.length) {
					System.out.println("### caseNo : " + caseNo + " 잘못된 문제 ###");
				}else {
					System.out.println("### " + caseNo + "번 >>  result : " + getMaxRectangle(fenceCnt, fenceHeights));
				}
				
			}
			
		}
		
	}
	
	public static int getMaxRectangle(int fenceCnt, int[] fenceHeights) {
		
//		System.out.println("fenceCnt : " + fenceCnt);
//		for(int h : fenceHeights) {
//			System.out.println("높이 : " + h);
//		}
//		System.out.println("##################");
		
		
		
		if(fenceCnt == 1) {
			return fenceHeights[0];
		}else {
			int[] areas = new int[fenceCnt];
			for(int i=0; i<fenceHeights.length; i++) {
				int area = 0; 
				if(i ==0) {
					// 오른쪽 넓이만
					area = getrightArea(fenceHeights, i);
				}else if(i == fenceHeights.length-1) {
					// 왼쪽 넓이만
					area = getLeftArea(fenceHeights, i);
				}else {
					// 좌우 넓이
					area = getLeftArea(fenceHeights, i) + getrightArea(fenceHeights, i);
				}
				
				areas[i] = area;
			}

			return Arrays.stream(areas).max().getAsInt();
		}
	}

	
	public static int getLeftArea(int[] fenceHeights, int index) {
		
		int h = fenceHeights[index];
		int l = 0;
		
		for(int i=index; i>0; i--) {
			if(fenceHeights[i-1] >= h) {
				l++;
			}else {
				break;
			}
		}
		
//		System.out.println("getLeftArea >> index: "+index+" h:"+h+" l:"+l + " 왼쪽 넓이 : " + (h*l));
		
		return h*l;
	}
	
	public static int getrightArea(int[] fenceHeights, int index) {
		int h = fenceHeights[index];
		int l = 0;
		
		for(int i=index; i<fenceHeights.length-1; i++) {
			if(fenceHeights[i+1] >= h) {
				l++;
			}else {
				break;
			}
		}
		
//		System.out.println("getrightArea >> index: "+index+" h:"+h+" l:"+l + " 오른쪽 넓이 : " + (h*l));
		return h*(l+1);
	}
}
