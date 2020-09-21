package programmers;

class Solution {
	public int[] solution(int n) {
		int len = n * (1 + n) / 2;
		int[][] triangle = new int[n][];

		for (int i = 0; i < triangle.length; i++) {
			triangle[i] = new int[i + 1];
		}

		int now = 0;
		if (n % 3 == 1) {
			triangle[n - n / 3 - 1][n / 3] = len;
		}

		for (int i = 0; i <= n; i++) {

			for (int j = 0; j < n - 1 - i / 3 * 3; j++) {
				now++;
				if (i % 3 == 0) {
					int idx = (i / 3) * 2;
					triangle[idx + j][i / 3] = now;
				} else if (i % 3 == 1) {
					triangle[n - 1 - (i / 3)][j + i / 3] = now;
				} else if (i % 3 == 2) {
					int idx = (n - 1) - j - i / 3;
					triangle[idx][idx - i / 3] = now;
				}
			}
		}
		int[] answer = new int[len];
		int a = 0;
		for (int i = 0; i < triangle.length; i++) {
			for (int j = 0; j < triangle[i].length; j++) {
				answer[a++] = triangle[i][j];
			}
		}

		return answer;
	}
}