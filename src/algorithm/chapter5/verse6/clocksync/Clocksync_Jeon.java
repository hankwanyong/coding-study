package algorithm.chapter5.verse6.clocksync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Clocksync_Jeon {
	/*
	 * 시계 맞추기 page.168
	 */
	public static int[] switch0 = new int[] {0, 1, 2};
	public static int[] switch1 = new int[] {3, 7, 9, 11};
	public static int[] switch2 = new int[] {4, 10, 14, 15};
	public static int[] switch3 = new int[] {0, 4, 5, 6, 7};
	public static int[] switch4 = new int[] {6, 7, 8, 10, 12};
	public static int[] switch5 = new int[] {0, 2, 14, 15};
	public static int[] switch6 = new int[] {3, 14, 15};
	public static int[] switch7 = new int[] {4, 5, 7, 14, 15};
	public static int[] switch8 = new int[] {1, 2, 3, 4, 5};
	public static int[] switch9 = new int[] {3, 4, 5, 9, 13};
	
	public static Map<String, int[]> switchMap = new HashMap<String, int[]>();
	
	public static int[] clockArr = new int[16]; //실행 결과 배열
	public static int[] clockArr2 = new int[16]; //실행 결과 배열
	
	public static int resultClick = -1; //최소 몇번
	
	public static List<Integer> test = new ArrayList<Integer>(); 
	
	
	/*
	 * 완전탐색
	 * 스위치를 누르는 순서는 중요하지 않다.
	 * 스위치를 누르는 순서를 바꾼다고 해도 그 결과가 바뀌지 않는다.
	 * -> 각 스위치를 몇번이나 누를 것이냐.
	 * -> 스위치를 누르는 횟수의 모든 조합을 하나 하나 열거해야하지만 조합의 수가 무한하다.
	 * 
	 * 포인트 4번 누르면 12시 -> 안누른거랑 같다
	 * 그러므로 누르는 횟수는 0(== 4회) ~ 3
	 * 전체 경우의 수는 4의 10제곱 
	 * 
	 */
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
		String ex = "12 9 3 12 6 6 9 3 12 9 12 9 12 12 6 6"; //미친 index7 하면 됨. 3-> 6 
		String checkTest = "12 12 12 12 12 12 12 12 12 12 12 12 12 12 12 12";
		// 1번스위치 12 9 3 3 6 6 9 6 12 12 12 12 12 12 6 6  //1(2) 2(3) 3(4) 4(5) 5(4) 6(2) 7(3)  14(4)  15(3)
		// 8번스위치 12 12 6 6 9 9 9 6 12 12 12 12 12 12 6 6 
		// 6번스위치 12 12 6 12 9 9 9 6 12 12 12 12 12 12 12 12 //2번
		        
		
		// 3: 2, 7,  -> 3번 / 7배수
		// 6: 4,5,14,15 -> 2번 / 6배수
		// 9: 1,6,9,11 -> 1번 / 5배수
		// 12 : 4번 /4배수
		
		
		String[] exArr = ex.split(" ");
		String[] exArr2 = checkTest.split(" ");
		
		int index = 0;
		for(String a : exArr) {
			clockArr[index] = Integer.parseInt(a);
			index++;
		}
		//algo(clockArr, 0);
		
		int index2 = 0;
		for(String a : exArr2) {
			clockArr2[index2] = Integer.parseInt(a);
			index2++;
		}
		System.out.println("check12 테스트 : " + check12(clockArr2));
		
		algo2(clockArr);
		
		int click = -1;
		for(int i =0; i< test.size(); i++) {
			if(i  == 0) {
				click = test.get(0);
			} else {
				if( test.get(i) < click ) {
					click = test.get(i);
				}
			}			
		}
		System.out.println("결과 : " + click);		
		
		//정답 테스트  	// 0 1 3 55 666 7
		clockArr = addClock2(0, clockArr, 1);
		clockArr = addClock2(1, clockArr, 1);
		clockArr = addClock2(3, clockArr, 1);
		clockArr = addClock2(5, clockArr, 2);
		clockArr = addClock2(6, clockArr, 3);
		clockArr = addClock2(7, clockArr, 1);
		
		for(int i =0; i<clockArr.length; i++) {
			System.out.println("정답배열:" + clockArr[i]);
		}
		
		
	}
	
	public static void testAdd(int clickCnt) {
		test.add(clickCnt);
	}
	
	public static void algo2(int[] cArr) {

		int clickCnt = 0;
		int[] newMap  = cArr.clone();
		
		// 스위치는 0부터 9까지 차례대로
		
		for(int j=0; j < 4 ; j++) { //스위치 돌리는 횟수 0 ~ 3						
			
			newMap = addClock2(0, newMap, j);
			clickCnt += j;
			
			if(check12(newMap)) {
				testAdd(clickCnt);
			} 
		
			for(int j1=0; j1< 4; j1++) {
				newMap = addClock2(1, newMap, j1);
				clickCnt += j1;
				if(check12(newMap)) {
					testAdd(clickCnt);
				}  
			
				for(int j2=0; j2< 4; j2++) {
					newMap = addClock2(2, newMap, j2);
					clickCnt += j2;
					if(check12(newMap)) {
						testAdd(clickCnt);
					} 
										
					for(int j3=0; j3< 4; j3++) {
						newMap = addClock2(3, newMap, j3);
						clickCnt += j3;
						if(check12(newMap)) {
							testAdd(clickCnt);
						} 
					
						for(int j4=0; j4< 4; j4++) {
							newMap = addClock2(4, newMap, j4);
							clickCnt += j4;
							if(check12(newMap)) {
								testAdd(clickCnt);
							} 
														
							for(int j5=0; j5< 4; j5++) {
								newMap = addClock2(5, newMap, j5);
								clickCnt += j5;
								if(check12(newMap)) {
									testAdd(clickCnt);
								} 
								
								for(int j6=0; j6< 4; j6++) {
									newMap = addClock2(6, newMap, j6);
									clickCnt += j6;
									if(check12(newMap)) {
										testAdd(clickCnt);
									} 
								
									for(int j7=0; j7< 4; j7++) {
										newMap = addClock2(7, newMap, j7);
										clickCnt += j7;
										if(check12(newMap)) {
											testAdd(clickCnt);
										}
							
										
										for(int j8=0; j8< 4; j8++) {
											newMap = addClock2(8, newMap, j8);
											clickCnt += j8;
											if(check12(newMap)) {
												testAdd(clickCnt);
											} 
											
											for(int j9=0; j9< 4; j9++) {
												newMap = addClock2(9, newMap, j9);
												clickCnt += j9;
												if(check12(newMap)) {
													testAdd(clickCnt);
												} 
												newMap = resetClock2(9, newMap, j9);												
												clickCnt -= j9;
												
											}
											newMap = resetClock2(8, newMap, j8);
											clickCnt -= j8;
										}
										newMap = resetClock2(7, newMap, j7);
										clickCnt -= j7;
									}
									newMap = resetClock2(6, newMap, j6);
									clickCnt -= j6;
								}
								newMap = resetClock2(5, newMap, j5);
								clickCnt -= j5;
							}
							newMap = resetClock2(4, newMap, j4);
							clickCnt -= j4;
						}
						newMap = resetClock2(3, newMap, j3);
						clickCnt -= j3;
					}
					newMap = resetClock2(2, newMap, j2);
					clickCnt -= j2;					
				}
				newMap = resetClock2(1, newMap, j1);
				clickCnt -= j1;
			}
			newMap = resetClock2(0, newMap, j);
			clickCnt -= j;
		}

	}

	
	public static int[] addClock2(int switchNum, int[] cArr, int clickCnt) {
		int[] sw = (int[])switchMap.get(String.valueOf(switchNum));
	
		
		for(int x = 0; x < clickCnt; x++) {
			for(int i =0; i < sw.length; i++) {
				cArr[sw[i]] =  cArr[sw[i]] + 3;
				if(cArr[sw[i]] == 15) {
					cArr[sw[i]] = 3;
				}
			}
			
		}
		
		return cArr;
	}
	
	public static int[]  resetClock2(int switchNum, int[] cArr, int clickCnt) {
		int[] sw = (int[])switchMap.get(String.valueOf(switchNum));
	
		
		for(int x = 0; x < clickCnt; x++) {
			for(int i =0; i < sw.length; i++) {
				cArr[sw[i]] =  cArr[sw[i]] - 3;
				if(cArr[sw[i]] == 0) {
					cArr[sw[i]] = 12;
				}
			}
			
		}
		
		return cArr;
	}
	
	//결과 체크
	public static boolean check12(int[] cArr) {
		boolean result = true;
		
		for(int i =0; i < cArr.length; i++) {
			if(cArr[i] != 12) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	////////////////////////////// 해답 참고 전 버전 /////////////////////////////////////////////////
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
