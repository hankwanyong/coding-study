package algorithm.chapter5.verse6.clocksync;

import java.util.HashMap;
import java.util.Map;

public class Clocksync_Jeon {
	/*
	 * 시계 맞추기 page.168
	 */
	public static int[] switch0 = new int[] {0, 1, 2};
	public static int[] switch1 = new int[] {3, 7, 9, 11};
	public static int[] switch2 = new int[] {4, 10, 14, 15};
	public static int[] switch3 = new int[] {0, 4, 5, 6};
	public static int[] switch4 = new int[] {6, 7, 8, 10, 12};
	public static int[] switch5 = new int[] {0, 2, 14, 15};
	public static int[] switch6 = new int[] {3, 14, 15};
	public static int[] switch7 = new int[] {4, 5, 7, 14, 15};
	public static int[] switch8 = new int[] {1, 2, 3, 4, 5};
	public static int[] switch9 = new int[] {3, 4, 5, 9, 13};
	
	public static Map<String, int[]> switchMap = new HashMap<String, int[]>();
	
	public static int[] clockArr = new int[16]; //실행 결과 배열
	
	public static int resultClick = -1; //최소 몇번
	
	public static void main(String[] args) {
		
		switchMap.put("0", switch0);
		switchMap.put("1", switch1);
		switchMap.put("2", switch2);
		switchMap.put("3", switch3);
		switchMap.put("4", switch4);
		switchMap.put("5", switch5);
		switchMap.put("6", switch6);
		switchMap.put("7", switch7);
		switchMap.put("8", switch8);
		switchMap.put("9", switch9);
		
		//String ex = "12 6 6 6 6 6 12 12 12 12 12 12 12 12 12 12";
		String ex = "12 9 3 12 6 6 9 3 12 9 12 9 12 12 6 6";
		// 1번스위치 12 9 3 3 6 6 9 6 12 12 12 12 12 12 6 6  //1(2) 2(3) 3(4) 4(5) 5(4) 6(2) 7(3)  14(4)  15(3)
		// 8번스위치 12 12 6 6 9 9 9 6 12 12 12 12 12 12 6 6 
		// 6번스위치 12 12 6 12 9 9 9 6 12 12 12 12 12 12 12 12 //2번
		        
		
		// 3: 2, 7,  -> 3번 / 7배수
		// 6: 4,5,14,15 -> 2번 / 6배수
		// 9: 1,6,9,11 -> 1번 / 5배수
		// 12 : 4번 /4배수
		
		
		String[] exArr = ex.split(" ");
		
		int index = 0;
		for(String a : exArr) {
			clockArr[index] = Integer.parseInt(a);
			index++;
		}
		algo(clockArr, 0);
		System.out.println("결과 : " + resultClick);
		
	}
	
	
	public static int algo(int[] cArr, int click) {
		
		int result = click;
		
		if(resultClick != -1 && resultClick < click) return click;
		
		//대상 찾기
		int position = -1; //대상 index
		int count = 0;
		int switchNum = -1;
		
	
		for(int i=0; i< cArr.length; i++) {
			if( cArr[i] == 3) {
				position = i;
				//index에 맞는 스위치 찾기
				switchNum = findSwitch(position);
				
			} else if(cArr[i] == 6) {
				position = i;
				//index에 맞는 스위치 찾기
				switchNum = findSwitch(position);
				
			} else if( cArr[i] == 9) {
				position = i;
				//index에 맞는 스위치 찾기
				switchNum = findSwitch(position);
				
			}
			
			if(switchNum != -1) {
				break;
			}
		}
		
		if(position == -1) {
			resultClick = result;
			return result; //찾음
		}
		
		//스위치 찾은경우
		if(switchNum != -1) {
			//스위치 실행
			count = addClock(switchNum, cArr, position);
			result = algo(cArr, result+count);
			resetClock(switchNum, cArr, count);
		}
					
		return -1;
	}
	
	public static int findSwitch(int position) { 
		int [] a;
		for(int i =0; i< switchMap.size(); i++) {
			a = (int[])switchMap.get(String.valueOf(i));
			
			if(a[0] == position) {				
				return i;
			}
			
		}		
		return -1;		
	}
	
	public static int addClock(int switchNum, int[] cArr, int position) {
		int[] sw = (int[])switchMap.get(String.valueOf(switchNum));
		int count = 0;
		while(cArr[position] != 12) {
			for(int i =0; i < sw.length; i++) {
				cArr[sw[i]] =  cArr[sw[i]] + 3;
				if(cArr[sw[i]] == 15) {
					cArr[sw[i]] = 3;
				}
			}
			count++;
		}
		
		return count;
	}
	
	public static void resetClock(int switchNum, int[] cArr, int count) {
		int[] sw = (int[])switchMap.get(String.valueOf(switchNum));
		for(int x = 0; x<count ; x++) {
			for(int i =0; i < sw.length; i++) {
				cArr[sw[i]] =  cArr[sw[i]] - 3;
				if(cArr[sw[i]] == 0) {
					cArr[sw[i]] = 12;
				}
			}
		}
	}
}
