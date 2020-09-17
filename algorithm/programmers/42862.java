class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] stuArr = new int[n+2];
        int answer = 0;
        
        for(int i=1; i<=n; i++){
            stuArr[i] = 1;
        }
        for(int i=0; i<lost.length; i++){
            stuArr[lost[i]]--;
        }
        for(int i=0; i<reserve.length; i++){
            stuArr[reserve[i]]++;
        }
        
        for(int i=1; i<=n; i++){
            if(stuArr[i] == 0){
                if(stuArr[i-1] == 2){
                    stuArr[i] = 1;
                    stuArr[i-1] = 1;
                }
                else if(stuArr[i+1] == 2){
                    stuArr[i] = 1;
                    stuArr[i+1] = 1;
                }
                else
                    answer++;
            }
        }
        return (n-answer);
    }
}