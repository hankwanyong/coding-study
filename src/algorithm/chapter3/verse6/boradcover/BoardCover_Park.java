package algorithm.chapter3.verse6.boradcover;

public class BoardCover_Park {
	public static void main(String[] args) {
		String testCase = "3\n3 7\n#.....#\n#.....#\n##...##\n3 7\n#.....#\n#.....#\n##..###\n8 10\n##########\n#........#\n#........#\n#........#\n#........#\n#........#\n#........#\n##########";
		System.out.println(testCase);
		solution(testCase);
	}

	private static void solution(String testCase) {
		String[] rows = testCase.split("\\n");
		if(rows == null || rows.length == 0) {
			return;
		}
		int testCnt = Integer.parseInt(rows[0]);
		
		int caseInfoRow = 1;
		for(int i = 0;i<testCnt;i++) {
			String H = rows[caseInfoRow].split(" ")[0];
			String W = rows[caseInfoRow].split(" ")[1];
			boolean[][] board = new boolean[Integer.parseInt(H)][Integer.parseInt(W)];
			int whiteCnt = 0;
			for(int j = caseInfoRow+1, k = 0;j<caseInfoRow+Integer.parseInt(H)+1;j++,k++) {
				String boardInfo = rows[j];
				for(int l = 0; l<boardInfo.length();l++) {
					char blackOrWhite = boardInfo.charAt(l);
					if(blackOrWhite == '.') {
						whiteCnt++;
						board[k][l] = true;
					}
				}
			}
			
			if(whiteCnt%3 != 0 || whiteCnt == 0) {
				System.out.println(0);
			} else {
				int result = 0;
				int [][][] blockType = {
					{{0,0} , {0,1} , {1,0}},
					{{0,0} , {0,1} , {1,1}},
					{{0,0} , {1,0} , {1,1}},
					{{0,0} , {1,0} , {1,-1}}
				};
				result += countResult(board,blockType); 
				System.out.println(result);
				
			}
			caseInfoRow += Integer.parseInt(H)+1;
		}
	}

	private static int countResult(boolean[][] board, int[][][] blockType) {
		int remainWhiteCnt = 0;
		boolean firstBlankExist = false;
		int[] firstBlank = new int[2]; 
		for(int i = 0; i<board.length;i++) {
			for(int j = 0; j<board[i].length;j++) {
				if(board[i][j] == true) {
					remainWhiteCnt++;
					if(firstBlankExist == false) {
						firstBlankExist = true;
						firstBlank[0] = i;
						firstBlank[1] = j;
					}
				}
			}
		}
		
		if(remainWhiteCnt == 0) {
			return 1;
		} else if(remainWhiteCnt<2 || remainWhiteCnt%3 != 0) {
			return 0;
		}
		
		
		
		int ret = 0;
		for(int i = 0;i<blockType.length;i++) {
			boolean typeBlock1 = true;
			boolean typeBlock2 = true;
			boolean typeBlock3 = true;
			try {
				int block11 = firstBlank[0]+blockType[i][0][0];
				int block12 = firstBlank[1]+blockType[i][0][1];
				typeBlock1 = board[block11][block12];
				int block21 = firstBlank[0]+blockType[i][1][0];
				int block22 = firstBlank[1]+blockType[i][1][1];
				typeBlock2 = board[block21][block22];
				int block31 = firstBlank[0]+blockType[i][2][0];
				int block32 = firstBlank[1]+blockType[i][2][1];
				typeBlock3 = board[block31][block32];
			} catch (ArrayIndexOutOfBoundsException e) {
				typeBlock1 = false;
				typeBlock2 = false;
				typeBlock3 = false;
			}
			
			if(typeBlock1 && typeBlock2 && typeBlock3) {
				board[firstBlank[0]+blockType[i][0][0]][firstBlank[1]+blockType[i][0][1]] = false;
				board[firstBlank[0]+blockType[i][1][0]][firstBlank[1]+blockType[i][1][1]] = false;
				board[firstBlank[0]+blockType[i][2][0]][firstBlank[1]+blockType[i][2][1]] = false;
				ret += countResult(board,blockType);
				board[firstBlank[0]+blockType[i][0][0]][firstBlank[1]+blockType[i][0][1]] = true;
				board[firstBlank[0]+blockType[i][1][0]][firstBlank[1]+blockType[i][1][1]] = true;
				board[firstBlank[0]+blockType[i][2][0]][firstBlank[1]+blockType[i][2][1]] = true;
			}
			
		}
		
		return ret;
	}
}
