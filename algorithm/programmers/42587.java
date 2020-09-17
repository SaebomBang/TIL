import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Printer implements Comparable<Printer>{
	int pri;
	int idx;
	
	public Printer(int pri, int idx){
		this.pri = pri;
		this.idx = idx;
	}
	public int getPri(){
		return this.pri;
	}
	public int getIdx(){
		return this.idx;
	}
	
	public int compareTo(Printer p){
		if (this.pri > p.pri)
			return 1;
		else if (this.pri < p.pri)
			return -1;
		else
			return 0;
	}
}

class Solution {
	public int solution(int[] pri, int location) {
		List<Printer> priList = new ArrayList<Printer>();
		int[] answerList = new int[pri.length];
		int answer = -1;
		for (int i = 0; i < pri.length; i++) {
			priList.add(new Printer(pri[i], i));
		}
		
		for (int i = 0; i < pri.length; i++) {
			Printer maxPinter = Collections.max(priList);
			int maxPrinterIdx = priList.indexOf(maxPinter);

			List<Printer> sortPriList = new ArrayList<Printer>();
			sortPriList =  priList.subList(maxPrinterIdx, priList.size());
			sortPriList.addAll(priList.subList(0, maxPrinterIdx));

			answerList[i] = sortPriList.get(0).getIdx();
			if(answerList[i] == location){
				answer = i;
				break;
				
			}
			sortPriList.remove(0);
			
		
			priList = sortPriList;
		}
	
		return answer+1;
	}
}