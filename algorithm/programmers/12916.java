class Solution {
    boolean solution(String s) {
        boolean answer = true;
        s = s.toLowerCase();
        
        while(s.contains("p") && s.contains("y")){
            s = s.replaceFirst("p", "");
            s = s.replaceFirst("y", "");
        }
        
        answer = s.contains("p") == s.contains("y");
         
        return answer;
    }
}