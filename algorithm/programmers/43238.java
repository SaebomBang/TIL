public class Solution {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] times = { 7, 10 };
		sol.solution(6, times);
	}

	public long solution(int n, int[] times) {
		Arrays.sort(times);

		long min = 0, max = times[times.length - 1] * (long) n, mid = 0;
		long answer = max;
		while (min <= max) {
			mid = (min + max) / 2;
			if (binarySearch(mid, times, n)) {
				answer = answer > mid ? mid : answer;
				max = mid - 1;
			} else
				min = mid + 1;
		}

		System.out.println(min + " " + max);
		return answer;
	}

	public boolean binarySearch(long mid, int[] times, int n) {
		long nowN = 0;
		for (int time : times)
			nowN += mid / time;

		if (nowN >= n)
			return true;
		else
			return false;
	}
}