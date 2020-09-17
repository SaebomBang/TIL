class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
		for(int i=0; i<skill_trees.length; i++){
            String temp = "_";
            for(int j=0; j<skill_trees[i].length(); j++){
                if(skill.contains(skill_trees[i].charAt(j)+"")){
                    temp+=skill_trees[i].charAt(j);
                }
            }
            
            if(("_"+skill).contains(temp))
                answer++;
            
     
        }
        return answer;
    }
}