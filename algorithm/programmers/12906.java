import java.util.ArrayList;

public class Solution {
	public int[] solution(int []arr) {
        
        int[] answer;
        ArrayList<Integer> ansList = new ArrayList<>();
        
        int comp = -1;
        
        for(int i=0; i<arr.length; i++){
            if(arr[i]!=comp){
                comp = arr[i];
                ansList.add(comp);
            }
        }
        
        answer = new int[ansList.size()];
        
        for(int i=0; i<ansList.size(); i++){
            answer[i] = ansList.get(i);
        }
        
        return answer;
	}
}