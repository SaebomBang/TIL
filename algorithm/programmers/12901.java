class Solution {
  public String solution(int a, int b) {
      int[] mon = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
      String[] week = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
      int days = 0;
      
      for(int i=0; i<(a-1); i++){
          days += mon[i];
      }
      days = (days+b)%7;
      
      
      return week[days];
  }
}