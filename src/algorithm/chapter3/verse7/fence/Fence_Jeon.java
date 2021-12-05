package algorithm.chapter3.verse7.fence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fence_Jeon {
	
	static int MAX_HEIGHT = 10001;

	public static void main(String[] args) {
		int[] ex1 = {7,1,5,9,6,7,3};
		int[] ex2 = {1,4,4,4,4,1,1};
		int[] ex3 = {1,8,2,2};
		
		algo(ex1, MAX_HEIGHT);
		
		
		System.out.println("결과 ex1 --> " + algo(ex1, MAX_HEIGHT) );
		System.out.println("결과 ex2 --> " + algo(ex2, MAX_HEIGHT) );
		System.out.println("결과 ex3 --> " + algo(ex3, MAX_HEIGHT) );
	}   
	
	public static int algo(int[] ex, int maxH) {
		
		int result = 0;
		int findMaxH = 0;
		int index = 0;
		int left = 0;
		int right = 0;
		
		for(int i =0; i< ex.length; i++) {
			//if(ex[i] > findMaxH &&  ex[i] < maxH) {
				maxH = ex[i];
				index = i;				
				
				left = leftCheck(maxH, index-1, ex);
				right = rightCheck(maxH, index+1, ex);
				
				if(result < (maxH+left+right)) {
					result = maxH + left + right;
				}
				
		//	}
		}
		
		
		
		return result;
		
	}
	
	public static int leftCheck(int maxH, int index, int[] ex) {
		
		int leftResult = 0;
		
		if(index < 0) {
			return 0;
		}
		
			
		if(ex[index] >= maxH) {
			leftResult = maxH;
			leftResult += leftCheck(maxH, index-1, ex);
		}
	
		return leftResult;
	}
	
	public static int rightCheck(int maxH, int index, int[] ex) {
		int rightResult = 0;
		
		if(index >= ex.length) {
			return 0;
		}		
			
		if(ex[index] >= maxH) {
			rightResult = maxH;
			rightResult += rightCheck(maxH, index+1, ex);
		}
		
		return rightResult;
	}
	
	
}
