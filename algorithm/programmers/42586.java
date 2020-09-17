import java.util.Arrays;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = new int[100]; 
        int lastDays = 0;
        int k = -1;
        
        for(int i=0; i<progresses.length; i++){
        	int thisDays = (100-progresses[i])/speeds[i]
                +((100-progresses[i])%speeds[i] == 0 ? 0 : 1);
            if(thisDays > lastDays){
                lastDays = thisDays;
                answer[++k] = 1;
            } else{
               answer[k]++;
            }
        }
        
        return Arrays.stream(answer).filter(i -> i!=0).toArray();
	}
}