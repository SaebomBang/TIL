class Solution {
  public int solution(int n) {
      int answer = 0;
      int b1 = 1;
      int b2 = 2;
      
      if(n == 1){
          answer = 1;
      }
      else if(n == 2){
          answer = 2;
      }
      else{
          for(int i=2; i<n; i++){
              answer = (b1+b2)%1000000007;
              b1 = (b2)%1000000007;
              b2 = answer%1000000007;
          }
      }
      return answer;
  }
}