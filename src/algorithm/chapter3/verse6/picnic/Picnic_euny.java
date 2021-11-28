package algorithm.chapter3.verse6.picnic;

public class Picnic_euny {

	public static void main(String[] args) {
		System.out.println(sum(3));
		System.out.println(recursiveSum(3));
	}
	
	public static int sum(int n){
		int ret = 0;
		for (int i = 0; i <= n; i++) {
			ret += i;			
		}
		return ret;
	}
	
	public static int recursiveSum(int n){
		if(n==1){
			return 1;
		}else{
			return n+recursiveSum(n-1);
		}
	}
	
	int n;
	boolean areFriends[][];
	
	int countPairngs(boolean taken[]){
		int firstFree = -1;
		for(int i=0;i<n;++i){
			if(!taken[i]){
				firstFree = i;
				break;
			}
		}
		if(firstFree == -1){
			return 1;
		}
		int ret = 0;
		for(int pairWith = firstFree+1;pairWith<n;++pairWith){
			if(!taken[pairWith]&&areFriends[firstFree][pairWith]){
				taken[firstFree] = taken[pairWith] = true;
				ret += countPairngs(taken);
				taken[firstFree] = taken[pairWith] = false;
			}
		}
		return ret;
	}

}
