import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] answers) {
        int[] result = {0, 0, 0};
        int[] stu2 = {1, 3, 4, 5};
        int[] stu3 = {3, 1, 2, 4, 5};
        int[] answer;
        for(int i=0; i<answers.length; i++){
            if((i+1)%5 == answers[i]%5)
                result[0]++;
            
            if((i%2 == 0 && answers[i] == 2)
              || (i%2 == 1 && answers[i] == stu2[(i%8)/2]))
                result[1]++;
            
            if(answers[i] == stu3[(i%10)/2])
                result[2]++;
        }
        
        
        ArrayList<Integer> resultList = new ArrayList<>();
        
        int max = (result[0]>result[1]? result[0] : result[1]);
        max = (max > result[2] ? max : result[2]);
        
        if(result[0] == max)
            resultList.add(1);
        if(result[1] == max)
            resultList.add(2);
        if(result[2] == max)
            resultList.add(3);
        
        answer = new int[resultList.size()];
        int i=0;
        for(int temp : resultList){
            answer[i++] = temp;
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }
}