class Solution43105 {
	public int solution(int[][] triangle) {
		int answer = 0;

		for (int i = 1; i < triangle.length; i++) {
			for (int j = 0; j < triangle[i].length; j++) {
				if (j == 0)
					triangle[i][j] += triangle[i - 1][0];
				else if (j == triangle[i].length - 1)
					triangle[i][j] += triangle[i - 1][j - 1];
				else {
					int num = (triangle[i - 1][j - 1] > triangle[i - 1][j] ? triangle[i - 1][j - 1]
							: triangle[i - 1][j]);
					triangle[i][j] += num;
				}
			}
			if (i == triangle.length - 1) {
				System.out.println(triangle.length);
				for (int k = 0; k < triangle.length; k++) {
					if (answer < triangle[i][k]) {
						answer = triangle[i][k];
					}
				}
			}
		}

		return answer;
	}
}