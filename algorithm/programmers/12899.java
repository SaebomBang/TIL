class Solution {
  public String solution(int n) {
      String answer = "";
      int i = 0;
      do{
          if(n%3 == 0){
              answer = 4+answer;
              n = n-3;
          }else{
              answer = n%3 + answer;
          }
          n = n/3;
          i++;
      }while(n>0);
      
      return answer;
  }
}