import java.util.Arrays;

class Solution {
  public String solution(String s) {
      String answer = "";
      char[] sArr = new char[s.length()];
      
      for(int i=0; i<s.length(); i++){
          sArr[i] = s.charAt(i);
      }
      
      Arrays.sort(sArr);
      
      for(int i=s.length()-1; i>=0; i--){
          answer += sArr[i];
      }
      return answer;
  }
}