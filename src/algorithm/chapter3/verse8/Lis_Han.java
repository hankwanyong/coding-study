package algorithm.chapter3.verse8;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lis_Han {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] aArr1 = {1,2,4};
		int[] bArr1 = {3,4,7};
		
		int[] aArr2 = {1,2,3};
		int[] bArr2 = {4,5,6};
		
		int[] aArr3 = {10,20,30,1,2};
		int[] bArr3 = {10,20,30};
		
	}

	// 증가 부분 수열 리스트 구하기
	static List<Set<Integer>> getIsList(int[] arr) {
		if(arr.length != 0) {
			
			for(int i=0; i<arr.length; i++) {
				for(int j=i; j<arr.length; j++) {
					Set<Integer> tmpSet = new HashSet<>();
					
				}
			}
			
			
			return null;
		}else {
			return null;
		}
	}
	
}
