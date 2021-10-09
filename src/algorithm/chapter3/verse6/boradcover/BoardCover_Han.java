package algorithm.chapter3.verse6.boradcover;

public class BoardCover_Han {

	
	/*
	 * 문제: 게임판 덮기(문제 ID: BORADCOVER, 난이도: 하)
	 * 
	 * L자 모양의 블록으로 게임판의 흰 칸을 덮는 방법의 수를 계산하시오
	 * 
	 * 입력
	 * 		int H : 줄 수(세로)
	 * 		int W : 칸 수(가로)
	 * 		게임 판 모양 boolean[][] > true : 검은 칸, false : 흰 칸
	 */
	
	public static void main(String[] args) {
		// 예제1
		int H1 = 3;
		int W1 = 7;
		boolean[][] borad1 = new boolean[H1][W1];
		borad1[0][0] = true;
		borad1[0][6] = true;
		borad1[1][0] = true;
		borad1[1][6] = true;
		borad1[2][0] = true;
		borad1[2][1] = true;
		borad1[2][5] = true;
		borad1[2][6] = true;
		
		
		// 예제2
		int H2 = 3;
		int W2 = 7;
		boolean[][] borad2 = new boolean[H2][W2];
		borad2[0][0] = true;
		borad2[0][6] = true;
		borad2[1][0] = true;
		borad2[1][6] = true;
		borad2[2][0] = true;
		borad2[2][1] = true;
		borad2[2][4] = true;
		borad2[2][5] = true;
		borad2[2][6] = true;
		
		// 예제3
		int H3 = 8;
		int W3 = 10;
		boolean[][] borad3 = new boolean[H3][W3];
		
		for(int i=0; i<W3; i++) {
			borad3[0][i] = true;
			borad3[7][i] = true;
		}
		
		for(int i=1; i<H3-1; i++) {
			borad3[i][0] = true;
			borad3[i][9] = true;
		}
		
//		borad_view(borad3);
//		borad_view(borad2);
//		borad_view(borad3);
		
		System.out.println(setBlockCnt(H1, W1, borad1));
		System.out.println(setBlockCnt(H2, W2, borad2));
		System.out.println(setBlockCnt(H3, W3, borad3));
		
	}
	
	
	/**
	 * 게임 판 출력
	 * @param borad
	 */
	static void borad_view(boolean[][] borad) {
		StringBuilder sb = new StringBuilder();
		for(boolean[] row : borad) {
			
			
			for(boolean col : row) {
				if(col) {
					sb.append("#");
				}else {
					sb.append(".");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int setBlockCnt(int H, int W, boolean[][] borad) {
		
		/*
		 * 1. 왼쪽 위부터 가장 첫번째 흰 칸 찾기
		 * 2. L모양 블럭 덮기
		 */
		int ret = 0;
		int h = 0;
		int w = 0;
		boolean breakYn = false;
		
		for(int i=0; i<H; i++) {
			
			if(breakYn) {
				break;
			}
			
			for(int j=0; j<W; j++) {
				if(borad[i][j]) {
					continue;
				}else {
					h=i;
					w=j;
					breakYn = true;
					break;
				}
			}
		}
		
//		System.out.println("h : " + h + " w : " + w);
//		borad_view(borad);
		
		if(h == 0 && w ==0 && borad[0][0]) {
			// 흰 칸이 없다
			return 1;
		}
		
		if(setBlock1(H,W,borad,h,w)) {
//			System.out.println("setBlock1 : true");
			boolean[][] boradCopy = boradCopy(borad);
			// 검은 칸으로 변경
			boradCopy[h][w] = true;
			boradCopy[h+1][w] = true;
			boradCopy[h+1][w+1] = true;
			ret += setBlockCnt(H,W,boradCopy);
		} 
		
		if(setBlock2(H,W,borad,h,w)) {
//			System.out.println("setBlock2 : true");
			boolean[][] boradCopy = boradCopy(borad);
			// 검은 칸으로 변경
			boradCopy[h][w] = true;
			boradCopy[h+1][w] = true;
			boradCopy[h+1][w-1] = true;
			ret += setBlockCnt(H,W,boradCopy);
		} 
		
		if(setBlock3(H,W,borad,h,w)) {
//			System.out.println("setBlock3 : true");
			boolean[][] boradCopy = boradCopy(borad);
			// 검은 칸으로 변경
			boradCopy[h][w] = true;
			boradCopy[h][w+1] = true;
			boradCopy[h+1][w+1] = true;
			ret += setBlockCnt(H,W,boradCopy);
		} 
		
		if(setBlock4(H,W,borad,h,w)) {
//			System.out.println("setBlock4 : true");
			boolean[][] boradCopy = boradCopy(borad);
			// 검은 칸으로 변경
			boradCopy[h][w] = true;
			boradCopy[h][w+1] = true;
			boradCopy[h+1][w] = true;
			ret += setBlockCnt(H,W,boradCopy);
		}
		
		return ret;
	}
	
	/**
	 * 
	 * 	#
	 * 	## 
	 * 
	 * 모양 블럭 덮기
	 * 
	 * @param H	: 줄
	 * @param W : 칸
	 * @param borad : 게임판
	 * @param h,w : 시작점
	 * @return
	 */
	static boolean setBlock1 (int H, int W, boolean[][] borad, int h, int w) {
		
		H -= 1;
		W -= 1;
		
		if(H <h+1) return false;
		if(W < w+1) return false;
		
		if(borad[h+1][w]) return false;
		if(borad[h+1][w+1]) return false;
		
		return true;
	}
	
	/**
	 * 
	 * 	 #
	 * 	## 
	 * 
	 * 모양 블럭 덮기
	 * 
	 * @param H	: 줄
	 * @param W : 칸
	 * @param borad
	 * @param h,w : 시작점
	 * @return
	 */
	static boolean setBlock2 (int H, int W, boolean[][] borad, int h, int w) {
		
		H -= 1;
		
		if(H < h+1) return false;
		if(w-1 < 0) return false;
		
		if(borad[h+1][w]) return false;
		if(borad[h+1][w-1]) return false;
		
		return true;
	}
	
	/**
	 * 
	 * 	##
	 * 	 # 
	 * 
	 * 모양 블럭 덮기
	 * 
	 * @param H	: 줄
	 * @param W : 칸
	 * @param borad
	 * @param h,w : 시작점
	 * @return
	 */
	static boolean setBlock3 (int H, int W, boolean[][] borad, int h, int w) {
		
		H -= 1;
		W -= 1;
		
		if(H <h+1) return false;
		if(W <w+1) return false;
		
		if(borad[h][w+1]) return false;
		if(borad[h+1][w+1]) return false;
		
		return true;
	}
	
	/**
	 * 
	 * 	##
	 * 	# 
	 * 
	 * 모양 블럭 덮기
	 * 
	 * @param H	: 줄
	 * @param W : 칸
	 * @param borad
	 * @param h,w : 시작점
	 * @return
	 */
	static boolean setBlock4 (int H, int W, boolean[][] borad, int h, int w) {
		
		H -= 1;
		W -= 1;
		
		if(H <h+1) return false;
		if(W <w+1) return false;
		
		if(borad[h][w+1]) return false;
		if(borad[h+1][w]) return false;
		
		return true;
	}
	
	/*
	 * 2차원 배열 복사
	 */
	static boolean[][] boradCopy(boolean[][] borad){
		boolean[][] ret = new boolean[borad.length][borad[0].length];
		for(int i=0; i<borad.length; i++) {
			for(int j=0; j<borad[0].length; j++) {
				ret[i][j] = borad[i][j];
			}
		}
		return ret;
	}
}
