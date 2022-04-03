package algorithm.chapter16.verse4.granduation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 문제: 졸업 학기 
 * @author Caf
 *
 */
public class Graduation_Jeon {
	/*
	 *졸업 필수 학점을 채우려면 전공과목 N개 K개 이상을 수강해야함.
	 * but 각 과목은 해당 과목의 선수과목을 미리 수강했어야만 수강할 수 있다.
	 * 
	 * 각 학기마다 모든 과목이 개설되는 것이 아니다.
	 * 
	 *  @ 어떻게 해야 최소 학기에 졸업을 할 수 있을까?
	 *   ( 한 과목도 수강하지 않은 학기는 휴학 처리-> 다닌 학기 수에 포함되지 않는다.)
	 *   
	 *   입력 : 첫줄 테스트 케이스 C <= 50
	 *         다음줄 전공 과목의 수 1<= N <= 12    들어야할 과목수 0 <= K <= N  , 학기의 수 1 <= M <= 10, 학기당 최대 들을 수 있는 과목의 수 1 <= L <= 10
	 *         각 과목에는 0 ~ N-1 까지의 번호가 매겨져 있다.
	 *         
	 *   
	 *   케이스1
	 *   4(전공과목수) 4(들어야할 과목수) 학기수(4) 4(한학기최대)
	 *   0(과목0- 선수과목수0)
	 *   1 0 (과목1- 선수과목1개 0번과목)
	 *   3 0, 1, 3(과목2- 선수과목3개 0,1,3 과목)
	 *   0 ( 과목3 선수과목수0)
	 *   4 0 1 2 3 (1학기 개설과목 4개, 0 1 2 3 )
	 *   4 0 1 2 3  (2학기 개설과목 4개, 0 1 2 3 )
	 *   3 0 1 3  (3학기 개설과목 3개, 0 1 3 )
	 *   4 0 1 2 3  (4학기 개설과목 4개, 0 1 3 )
	 *         
	 */
	
	
	public static void main(String[] args) {
	
		
		//과목정보(선수강) 세팅
		ArrayList<Integer[]> subList = new ArrayList<Integer[]>();
		subList.add(new Integer[] {}); //과목0 세팅
		subList.add(new Integer[] {0}); //과목1 세팅
		subList.add(new Integer[] {0,1,3}); //과목2 세팅
		subList.add(new Integer[] {}); //과목3 세팅
		
		//학기정보 세팅
		ArrayList<Integer[]> semesterList = new ArrayList<Integer[]>();
		semesterList.add(new Integer[] {0, 1, 2, 3}); //학기0 세팅
		semesterList.add(new Integer[] {0, 1, 2, 3}); //학기1 세팅
		semesterList.add(new Integer[] {0,1,3}); //학기2 세팅
		semesterList.add(new Integer[] {0, 1, 2, 3}); //학기3 세팅
		
		//내 학기세팅
		ArrayList<Integer[]> mySemesterList = new ArrayList<Integer[]>();
		mySemesterList.add(new Integer[] {}); //학기0 세팅
		mySemesterList.add(new Integer[] {}); //학기1 세팅
		mySemesterList.add(new Integer[] {}); //학기2 세팅
		mySemesterList.add(new Integer[] {}); //학기3 세팅
		
		
		//과목 보정
		for(int x = 0;x < subList.size()* subList.size() ; x++ ) {
			
			int x_sub = x%4;
			
			//0학기 ~ 3학기 까지 
			for(int j =0; semesterList.size()> j; j++) {	
				
				
				//중복 체크
				boolean duplChk = false;
				
				//내 학기를 뒤져서 과목 기수강여부 체크
				for(int z =0; z < mySemesterList.size(); z++) {
					Integer[] sub_mySemesterList = mySemesterList.get(z);
					List<Integer> subList_mySemesterList = Arrays.asList(sub_mySemesterList);
					if(subList_mySemesterList.contains(x_sub)) {
						duplChk = true;
						break;
					}
				}
				
				//이미 들은게 아니라면
				if(!duplChk) {
					
					if(checkPsblSub(subList.get(x_sub), x_sub, j, semesterList, mySemesterList)) { //수강 가능 여부 체크
						
						Integer[] newSub = mySemesterList.get(j);
						if(0 == newSub.length) {
							newSub = new Integer[subList.size()];
							Arrays.fill(newSub, -1);
							newSub[0] = x_sub;
						} else {
							for(int t=0; t< newSub.length; t++) {
								if(newSub[t] == -1) {
									newSub[t] = x_sub;
									break;
								}
							}
							
						}
						mySemesterList.set(j, newSub);
												
					}
				}
			}
		}
		
		//최종 mySemesterList 결과 체크
		
		int res = 0;
		
		for(Integer[] res_tmp : mySemesterList) {
			if(res_tmp.length > 0) {
				res++;
			}
		}

		System.out.println("내가 수강해야되는 학기 : " + res);
		for(int r =0; r <  mySemesterList.size(); r++) {
			String sm = "";
			for(int y=0; y < mySemesterList.get(r).length; y++) {
				Integer a = mySemesterList.get(r)[y];
				if(a != -1) {
					sm += String.valueOf(a) + " ";
				}
			}
			System.out.println(r+ "학기 : " + sm);
		}
	}
	
	//과목수강 가능여부 체크
	public static boolean checkPsblSub(Integer[] temp_sub ,int subIdx, int semesterIdx, ArrayList<Integer[]> semesterList, ArrayList<Integer[]> mySemesterList) {
		//temp_sub 체크할 과목정보
		//semesterIdx 학기 인덱스
		//semesterList 학기 리스트
		//subIdx 과목번호
		
		Integer[] smt_temp = semesterList.get(semesterIdx); //해당학기 과목정보
		
		//선수강 과목갯수가 0 인경우 현재 학기에 수강가능한지만 체크
		if(temp_sub.length == 0) {
			boolean corr = false;
			
			for(int x =0; smt_temp.length > x; x++) {
				if(subIdx == (int)smt_temp[x]) {
					corr = true;
					break;
				}
			}
			
			return corr;
		}
		//선수강 과목이 있다면 나의 학기에서 전학기 정보 찾기
		else {
			//현재 학기가 0학기인 경우 무조건 false;			
			if(semesterIdx == 0) {
				return false;
			} else {
				//내학기에서 선수강 과목 찾기
				int chkSum = 0;
				/**
				 * 1. 선수강 갯수 반복문 체크
				 * 2. 나의학기 마다 반복문 최대 현재학기 전까지
				 * 3. 하나라도 매칭되는지 체크
				 */
				for(int j =0; j<temp_sub.length; j++) { //선수강 for문
					//smt_temp[j] //선수강과목

					for(int i =0; i<mySemesterList.size(); i++) {//내학기 반복
						Integer[] mySe = mySemesterList.get(i);
						
						List<Integer> mySeList = Arrays.asList(mySe);
						if(mySeList.contains(smt_temp[j])) { //찾으면 다음 선수강
							chkSum++;
							break;
						}						
					}
				}
				
				//chkSum과 선수강 과목수랑 같으면 수강가능
				if(chkSum == temp_sub.length) {
					return true;
				}
				
			}
		}
		
		
		return false;
	}
	
	
}
