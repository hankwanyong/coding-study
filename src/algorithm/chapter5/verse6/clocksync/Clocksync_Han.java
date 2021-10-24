package algorithm.chapter5.verse6.clocksync;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clocksync_Han {

	public static void main(String[] args) {
		int[] input1 = {12, 6, 6, 6, 6, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12 };
		int[] input2 = {12, 9, 3, 12, 6, 6, 9, 3, 12, 9, 12, 9, 12, 12, 6, 6 };
		int[] input3 = {12, 6, 6, 6, 6, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12 };
		
		System.out.println(SwitchCaseUtil.getClockIndex(1));
		
		SwitchCaseUtil.pressSwitch(input1, 1);
		
		System.out.println(chkEqClockArr(input1, input2));
		System.out.println(chkEqClockArr(input1, input3));
		
	}
	
	static class SwitchCaseUtil {
		private static List<List<Integer>> switch_case = null;
		
		private static void init() {
			if(null == switch_case) {
				switch_case = new ArrayList<List<Integer>>();
				switch_case.add(Arrays.asList(0,1,2));
				switch_case.add(Arrays.asList(3,7,9,11));
				switch_case.add(Arrays.asList(4,10,14,15));
				switch_case.add(Arrays.asList(0,4,5,6,7));
				switch_case.add(Arrays.asList(6,7,8,10,12));
				switch_case.add(Arrays.asList(0,2,14,15));
				switch_case.add(Arrays.asList(3,14,15));
				switch_case.add(Arrays.asList(4,5,7,14,15));
				switch_case.add(Arrays.asList(1,2,3,4,5));
				switch_case.add(Arrays.asList(3,4,5,9,13));
			}
		}
		
		public static List<Integer> getClockIndex(int ci) {
			init();
			List<Integer> ret = new ArrayList<>();
			for(int i=0; i<switch_case.size(); i++) {
				if(switch_case.get(i).contains(ci)) ret.add(i);
			}
			return ret;
		}
		
		public static void pressSwitch(int[] clocks, int si) {
			init();
			if(si <= switch_case.size()-1) {
				for(Integer i : switch_case.get(si)) {
					clocks[i] = (clocks[i] + 3) % 12; 
				}
			}
		}
	}
	
	/**
	 * 두 배열 비교
	 * @param orginArr
	 * @param chgArr
	 * @return
	 */
	public static boolean chkEqClockArr(int[] orginArr, int[] chgArr) {
		if(null == orginArr || null == chgArr) return false;
		if(orginArr.length != chgArr.length) return false;
		
		for(int i=0; i<orginArr.length; i++) {
			if(orginArr[i] != chgArr[i])return false;
			
		}
		return true;
	}
	
	public static int repeatSolution(int[] originArr, int[] chgArr) {
		
		
		
	}
	
	public static int solution(int[] clocks) {
		return 0;
	}
	
}
