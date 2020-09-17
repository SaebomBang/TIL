import java.util.ArrayList;
import java.util.Arrays;

class Solution {
  public int[] solution(int[] arr, int divisor) {
      int[] answer;
      ArrayList<Integer> ansList = new ArrayList<>();
      
      for(int i=0; i<arr.length; i++){
          if(arr[i]%divisor == 0)
              ansList.add(arr[i]);
      }
      
      if(ansList.size() == 0){
          answer = new int[]{-1};
      }
      else{
          answer = new int[ansList.size()];
          for(int i=0; i<ansList.size(); i++){
          answer[i] = ansList.get(i);
          }
      }
      
      Arrays.sort(answer);
          
      return answer;
  }
}