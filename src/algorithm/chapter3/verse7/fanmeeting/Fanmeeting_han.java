package algorithm.chapter3.verse7.fanmeeting;

import java.util.stream.Stream;

public class Fanmeeting_han {

	
	
	public static void main(String[] args) {
		String input = "4\nFFFMMM\nMMMFFF\nFFFFF\nFFFFFFFFFF\nFFFFM\nFFFFFMMMMF\nMFMFMFFFMMMFMF\nMMFFFFFMFFFMFFFFFFMFFFMFFFFMFMMFFFFFF";
		
		test(input);
		
	}

	public static void test(String testCase) {
		String[] rows = testCase.split("\\n");
		
		if(rows != null && rows.length > 2 && rows.length%2 == 1) {
			
			int caseNo = 0;
			
			for(int caseNum=1; caseNum<rows.length; caseNum+=2) {
				caseNo ++;
				int[] members = Stream.of(rows[caseNum].split("")).mapToInt(s -> convertInt(s)).toArray();
				int[] fans = Stream.of(rows[caseNum+1].split("")).mapToInt(s -> convertInt(s)).toArray();
				
				if(members.length > fans.length) {
					System.out.println("### caseNo : " + caseNo + " result : 0 ###");
				}else {
					System.out.print("### " + caseNo + "번 members >>> ");
					for(int i : members) {
						System.out.print(i);
					}
					System.out.println("");
					System.out.print("### " + caseNo + "번 fans >>> ");
					for(int i : fans) {
						System.out.print(i);
					}
					System.out.println("");
//					System.out.println("### " + caseNum + "번 >>  result : " + getMaxRectangle(fenceCnt, fenceHeights));
				}
				
			}
			
		}
		
	}
	
	/**
	 * F : 0
	 * M : 1
	 * @param s
	 * @return
	 */
	private static int convertInt(String s) {
		if("F".equals(s)) return 0;
		else return 1;
	}
	
}
