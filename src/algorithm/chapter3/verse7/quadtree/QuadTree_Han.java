package algorithm.chapter3.verse7.quadtree;

import java.util.Arrays;
import java.util.Iterator;

public class QuadTree_Han {

	
	/*
	 * 1. QuadTree 클래스에 다 담은 후
	 * 2. 반씩 쪼개서 list 3,4번째는 1,2번쨰로 
	 * 3. 1,2번쨰는 3,4번쨰로 
	 * 4.  quadStr() 메서드로 출력
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 예제 입력
		String input1 = "w";
		String input2 = "xbwwb";
		String input3 = "xbwxwbbwb";
		String input4 = "xxwwwbxwxwbbbwwxxxwwbbbwwwwwbb";
		
		QuadTree q = new QuadTree();
		
		convertQuadTree(q, input4);
		
		
	}

	public static QuadTree convertQuadTree(QuadTree q, String input) {
		
		if(!input.startsWith("x") && input.length() == 1) {
			return new QuadTree();
		}
		
		String[] split_input = input.split("");
		
		System.out.println(split_input.length);
		
		
		return null;
	}
	
	static class QuadTree {
		
		private String compression;		// 압축 값
		
		private QuadTree[] list;
		
		public QuadTree() {}
		
		public QuadTree(String c) {
			this.compression = c;
		}
		public String getCompression() {
			return compression;
		}
		public void setCompression(String compression) {
			this.compression = compression;
		}
		
		// 쿼드트리 string 압축
		public String quadStr() {
			
			if(list.length != 4) {
				return this.compression;
			}else {
				String str = "";
				for(int i=0; i<4; i++) {
					str += list[i].quadStr();
				}
				
				if("x".equals(this.compression)) {
					return this.compression + str;
				}else {
					return str;
				}
			}
			
		}
		
	}
	
}
