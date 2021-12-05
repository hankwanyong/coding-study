package algorithm.chapter3.verse7.quadtree;

public class QuadTree_Park {
	public static void main(String[] args) {
		String testCase = "4\nw\nxbwwb\nxbwxwbbwb\nxxwwwbxwxwbbbwwxxxwwbbbwwwwbb";
		System.out.println(testCase);
		solution(testCase);
	}
	public static void solution(String testCase) {
		String[] rows = testCase.split("\\n");
		if(rows == null || rows.length == 0) {
			return;
		}
		int testCnt = Integer.parseInt(rows[0]);
		
		for(int i = 0;i<testCnt;i++) {
			String row =  rows[i+1];
			
			Tree flipOverTree = new Tree(row);
			System.out.println(flipOverTree);
		}
		
		
	}
}

class Tree {
	String value;
	Tree first;
	Tree second;
	Tree third;
	Tree fourth;
	@Override
	public String toString() {
		String toString = value;
		if(first !=null) {
			toString+=third.toString()+fourth.toString()+first.toString()+second.toString();
		}
		return toString;
	}
	public Tree(String value) {
		super();
		
		if(value.charAt(0) != 'x') {
			this.value = value;
		} else {
			this.value = value.charAt(0)+"";
			String[] subValue = new String[4];
			String remainder = value.substring(1);
			boolean continuing = false;
			int length = 0;
			String tmp = "";
			int tmpLimitLength = 1;
			for(int i = 0 ; i<remainder.length();i++) {
				char remainderChar = remainder.charAt(i);
				if(remainderChar == 'x') {
					tmp+=remainderChar+"";
					tmpLimitLength += 4;
					continuing = true;
				} else {
					if(continuing == false) {
						subValue[length++] = remainderChar+"";
					} else {
						tmp+=remainderChar+"";
						if(tmp.length() == tmpLimitLength) {
							subValue[length++] = tmp;
							tmp = "";
							tmpLimitLength = 1;
							continuing = false;
						}
					}
				}
			}
			
			
			first = new Tree(subValue[0]);
			second = new Tree(subValue[1]);
			third = new Tree(subValue[2]);
			fourth = new Tree(subValue[3]);
		}
		
	}
	
	
}