class Solution {
  public boolean solution(String s) {
      boolean answer = false;
      
      try{
         int sInt = Integer.parseInt(s); 
          if(s.length() == 4 || s.length()==6)
              answer = true;
      }catch(Exception e){
          answer = false;
      }
      
      return answer;
  }
}