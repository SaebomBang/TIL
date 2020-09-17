class Solution {
	public int gcd(int a, int b) {
		if (a % b == 0)
			return b;
		return gcd(b, a % b);
	}

	public long solution(int w, int h) {
		int big = (w >= h ? w : h);
		int small = w < h ? w : h;

		long gcd = gcd(small, big);
		long answer = (long) (((double)w * h )- (w + h - gcd));

		return answer;
	}
}