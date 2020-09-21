import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        HashSet<Integer> hSet = new HashSet<Integer>();
		for (int i = 0; i < numbers.length; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				hSet.add(numbers[i] + numbers[j]);
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>(hSet);
		Collections.sort(list);

		int[] answer = new int[list.size()];
		int a = 0;
		for (int i : list) {
			answer[a++] = i;
		}

		return answer;
	}
}