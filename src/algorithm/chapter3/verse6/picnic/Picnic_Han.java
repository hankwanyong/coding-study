package algorithm.chapter3.verse6.picnic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Picnic_Han {

	
	/*
	 * 문제:소풍(문제 ID: PICNIC, 난이도: 하)
	 * 
	 * 모두 친구인 학생 쌍 구하기
	 * 
	 * 입력
	 * 		- 첫 줄에는 테스트 케이스의 수 C(C<=50)
	 * 		- 각 테스트 케이스의 첫줄에는 학생의 수 n(2<=n<=10)과 친구 쌍의 수 m(0<=m<=n(n-1)/2)
	 *		- 다음 줄에는 m개의 정수 쌍으로 서로 친구인 두 학생의 번호
	 * 		
	 * 
	 * 시간 및 메모리 제한 : 프로그램은 1초 내에 실행되어야 하고, 64MB 이하의 메모리만을 사용해야 합니다.
	 */
	public static void main(String[] args) {
		String input = "3\n2 1\n0 1\n4 6\n0 1 1 2 2 3 3 0 0 2 1 3\n6 10\n0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5";
		System.out.println("예제입력\n"+input);
		
		System.out.println("예제 출력\n" + solution(input));
		
		
		
	}

	
	public static String solution(String input) {
		
		String ret = "";
		
		if(input.contains("\n")) {
			String[] input_split = input.split("\\n");
			
			int test_case_cnt = Integer.parseInt(input_split[0]);	// 첫 줄 테스트 케이스 수
			if(input_split.length != (test_case_cnt*2 +1)) {
				ret = "잘못된 예제";
			}else {
				for(int i=1; i<(test_case_cnt*2 +1); i+=2) {
					String input1 = input_split[i];
					String input2 = input_split[i+1];
					
					System.out.println("\n-------------------------------------");
					System.out.println("input1 : " + input1);
					System.out.println("input2 : " + input2);
					
					if(!input1.contains(" ")) {
						return "잘못된 예제";
					}else {
						String[] input1_arr = input1.split(" ");
						if(input1_arr.length != 2) return "잘못된 예제";
							
						if(Integer.parseInt(input1_arr[1]) < 1) {
							ret += "모두 친구인 학생 쌍이 없습니다.";
							continue;
						}
						
						if(!input2.contains(" ")) return "잘못된 예제";

						String[] input2_arr = input2.split(" ");
						if(input2_arr.length != (Integer.parseInt(input1_arr[1])*2)) return "잘못된 예제"; 
						
						int students_cnt = Integer.parseInt(input1_arr[0]);			// 학생 수
						int friend_pair_cnt = Integer.parseInt(input1_arr[1]);		// 친구 쌍의 수
						Set<String> friend_pair = new HashSet<>();
						
						// 친구 쌍 저장
						for(int j=0; j<friend_pair_cnt*2; j+=2) {
							int s1 = Integer.parseInt(input2_arr[j]);
							int s2 = Integer.parseInt(input2_arr[j+1]);
							
							if(s1 > s2) {
								friend_pair.add(s2+","+s1);
							}else {
								friend_pair.add(s1+","+s2);
							}
						}
						
						boolean[] taken = new boolean[students_cnt];
						int ex_answer = countParings(taken, friend_pair);
						
						System.out.println("예제 답 : " + ex_answer);
						
						if(ret.length() == 0) {
							ret += ex_answer;
						}else {
							ret += "\n"+ex_answer;
						}
					}
					
				}
			}
			
			
			
		}else {
			ret = "모두 친구인 학생 쌍이 없습니다.";
		}
		
		return ret;
	}
	
	// taken[i]= i번째 학생이 짝을 이미 찾았으면 true, 아니면 false
	static int countParings(boolean[] taken, Set<String> friend_pair_set) {
		// 남은 학생들 중 가장 번호가 빠른 학생을 찾는다.
		int firstFree = -1;
		
		for(int i=0; i<taken.length; ++i) {
			if(!taken[i]) {
				firstFree = i;
				break;
			}
		}
		
		// 기저 사례: 모든 학생이 짝을 찾았으면 한 가지 방법을 찾았으니 종료한다.
		if(firstFree == -1) return 1;
		int ret = 0;
		// 이 학생과 짝지을 학생을 결정한다.
		for(int pairWith = firstFree+1; pairWith < taken.length; ++pairWith) {
			String friend_pair = firstFree+","+pairWith;
			boolean friend_yn = friend_pair_set.contains(friend_pair);
			if(!taken[pairWith] && friend_yn) {
				taken[firstFree] = taken[pairWith] =  true;
				ret += countParings(taken, friend_pair_set);
				taken[firstFree] = taken[pairWith] =  false;
			}
		}
		return ret;
	}
	
}
