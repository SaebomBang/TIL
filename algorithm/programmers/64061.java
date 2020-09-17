class Solution {
    public int solution(int[][] board, int[] moves) {
        	int[] stack = new int[1000];
		int cnt = 1;
		int answer = 0;

	
		for (int i = 0; i < moves.length; i++) {
			//System.out.println(moves[i]);

			for (int j = 0; j < board.length; j++) {
				if (board[j][moves[i] - 1] != 0) {
					if (stack[cnt - 1] == board[j][moves[i] - 1]) {
						stack[--cnt] = 0;
						answer+=2;
					}
					else
						stack[cnt++] = board[j][moves[i] - 1];
					board[j][moves[i] - 1] = 0;
					//System.out.println(Arrays.deepToString(board));
					//System.out.println(Arrays.toString(stack));
					break;
				}
			}
		}

		return answer;
    }
}