package algorithm.chapter5.verse6.clocksync;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Clocksync_Han {

	static List<Integer> answerList = new ArrayList<>();
	
	public static void main(String[] args) {
		int[] input1 = {12, 6, 6, 6, 6, 6, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12 };
		int[] input2 = {12, 9, 3, 12, 6, 6, 9, 3, 12, 9, 12, 9, 12, 12, 6, 6 };
		
		solution(input1);
//		solution(input2);
		
//		System.out.println(logClocks(input2));
//		ClockSwitch.pushSwitch(input2, 0);
//		ClockSwitch.pushSwitch(input2, 1);
//		ClockSwitch.pushSwitch(input2, 5);
//		ClockSwitch.pushSwitch(input2, 6);
//		ClockSwitch.pushSwitch(input2, 5);
//		ClockSwitch.pushSwitch(input2, 6);
//		ClockSwitch.pushSwitch(input2, 6);
//		ClockSwitch.pushSwitch(input2, 3);
//		ClockSwitch.pushSwitch(input2, 7);
//
//		System.out.println(logClocks(input2));
		
		
	}
	
	enum ClockSwitch {
		CLOCK0(  0,  Arrays.asList(0,1,2),			0)
		, CLOCK1(1,  Arrays.asList(3,7,9,11),		0)
		, CLOCK2(2,  Arrays.asList(4,10,14,15),		0)
		, CLOCK3(3,  Arrays.asList(0,4,5,6,7),		0)
		, CLOCK4(4,  Arrays.asList(6,7,8,10,12),	0)
		, CLOCK5(5,  Arrays.asList(0,2,14,15),		0)
		, CLOCK6(6,  Arrays.asList(3,14,15),		0)
		, CLOCK7(7,  Arrays.asList(4,5,7,14,15),	0)
		, CLOCK8(8,  Arrays.asList(1,2,3,4,5),		0)
		, CLOCK9(9, Arrays.asList(3,4,5,9,13),		0);
		
		ClockSwitch(int clockIndex, List<Integer> asList, int pushCnt) {
			this.clockIndex = clockIndex;
			this.switchclocks = asList;
			this.pushCnt = pushCnt;
		}
		
		private final int clockIndex;		//고유값으로 입력할것
		private final List<Integer> switchclocks;
		private int pushCnt;
		
		public int getClockIndex() {
			return clockIndex;
		}
		
		public List<Integer> getSwitchclocks() {
			return switchclocks;
		}
		
		public int getPushCnt() {
			return pushCnt;
		}
		
		public void plusPushCnt() {
			this.pushCnt++;
		}
		
		private static List<ClockSwitch> getList() {
			return Arrays.asList(values());
		}
		
		// 시계를 포함한 스위치 list
		public static List<Integer> getClockIndexList(int ci) {
			return getList().stream().filter(cs -> cs.getSwitchclocks().contains(ci)).map(cs -> cs.getClockIndex()).collect(Collectors.toList());
		}
		
		public static void pushSwitch(int[] clocks, int si) {
			if(si <= getList().size()-1) {
				ClockSwitch clockSwitch = null;
				
				for(ClockSwitch cs : getList()) {
					if(cs.getClockIndex() == si) {
						clockSwitch = cs;
						break;
					}
				}
				
				if(null != clockSwitch) {
					for(Integer i : clockSwitch.getSwitchclocks()) {
						int a = (clocks[i] + 3) % 12;
						if(a == 0) a=12;
						clocks[i] = a; 
					}
					
					clockSwitch.pushCnt ++;
//					System.out.println("pushSwitch >> clockSwitch.pushCnt : " + clockSwitch.pushCnt);
				}
			}
		}
		
		public static void inversePushSwitch(int[] clocks, int si) {
			if(si <= getList().size()-1) {
				
				ClockSwitch clockSwitch = null;
				
				for(ClockSwitch cs : getList()) {
					if(cs.getClockIndex() == si) {
						clockSwitch = cs;
						break;
					}
				}
				
				if(null != clockSwitch) {
					for(Integer i : clockSwitch.getSwitchclocks()) {
						int a = (clocks[i] + 9) % 12;
						if(a == 0) a=12;
						clocks[i] = a; 
					}
					
					clockSwitch.pushCnt --;
//					System.out.println("inversePushSwitch >> clockSwitch.pushCnt : " + clockSwitch.pushCnt);
				}
			}
		}
		
		// 스위치 누른 횟수 체크 4이상면 false
		public boolean pushCntChk() {
			if(this.getPushCnt() > 3) {
				return false;
			}else {
				return true;
			}
		}
		
		/// 버튼 누른 수
		public static int getSumPushCnt() {
			int ret = 0;
			for(ClockSwitch cs : getList()) {
				ret += cs.getPushCnt();
			}
			return ret;
		}
	}
	
	public static class SwitchNode {
		int switchIndex;
		List<SwitchNode> lowerRank;
		
		public SwitchNode() {}
		
		public void setSwitchIndex(int switchIndex) {
			this.switchIndex = switchIndex;
		}
		
		public void addNode(SwitchNode node) {
			if(null == lowerRank) {
				lowerRank = new ArrayList<>();
			}
			
			this.lowerRank.add(node);
		}
	}
	
	public static void repeatSolution(int[] clocks) {
		int firstStart = -1;
		
		if(ClockSwitch.getSumPushCnt() > 3) {
			return ;
		}

		for(int i=0; i<clocks.length; i++ ) {
			if(clocks[i]%12 != 0) {
				firstStart = i;
				break;
			}
		}
		
		if(firstStart == -1) {
			// 모든 시계가 12시
			answerList.add(ClockSwitch.getSumPushCnt());
			return ;
		}else {
			List<Integer> clockIndexList = ClockSwitch.getClockIndexList(firstStart);
			int size = clockIndexList.size();
			
			for(int i=0; i<size; i++) {
				int it = clockIndexList.get(i);
				
				System.out.println(logClocks(clocks) + " 스위치 번호 : " + it);
				
				ClockSwitch.pushSwitch(clocks, it);
				repeatSolution(clocks);
				ClockSwitch.inversePushSwitch(clocks, it);
			}
			
		}
	}
	
	public static int solution(int[] clocks) {
		
		repeatSolution(clocks);
		System.out.println(answerList);
		
		return 0;
	}
	
	public static String logClocks(int[] clocks) {
		String str = "";
		for(int c : clocks) {
			str += c + " ";
		}
		return str;
	}
	
}
