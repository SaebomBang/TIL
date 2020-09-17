import java.util.Arrays;

class Solution {

public int solution(String a) {
        int answer= 1000000;
    
        if(a.length() == 1)
            return 1;
    
        for(int th = 1; th<=(a.length()/2); th++){
            StringBuffer s = new StringBuffer(a);
        
            for(int i=th; i<s.length(); i+=(th+1)){
                s.insert(i, 0);
            }
        
        String sToString = s.toString();
        String sArray[] = sToString.split("0");
        String sAnswer = "";
        
        int count = 1;
        for(int i=0; i<sArray.length-1; i+=count){
            count = 1;
            for(;i+count < sArray.length;){
                if(sArray[i].equals(sArray[i+count])){
                    sArray[i+count] = "";
                    count++;
                }
                
                else
                    break;
            }
            
            if(count == 1)
                sAnswer = sAnswer + sArray[i];
            else
                sAnswer = sAnswer + sArray[i] + count;
                
                }
                sAnswer = sAnswer + sArray[sArray.length-1]; 
                
                answer = (answer > sAnswer.length()) ? sAnswer.length() : answer;
        }
        
    return answer;
    }
}