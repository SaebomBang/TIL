class Solution {
    	public String solution(String p) {

		if (isCorrect(p))
			return p;
		else {
			return recur(p);
		}

	}

	public String recur(String p) {
		if (p == null || p.length() == 0)
			return p;
		int idx = cutStr(p);
		String u = p.substring(0, idx);
		String v = p.substring(idx);
		System.out.println(u + " " + v);

		if (isCorrect(u))
			return u + recur(v);
		else {
			String temp = "(" + recur(v) + ")";
			u = u.substring(1, u.length() - 1);
			u = reverse(u);
			return temp + u;
		}
	}

	public String reverse(String u) {
		String ret = "";
		for (int i = 0; i < u.length(); i++) {
			if (u.charAt(i) == '(')
				ret += ")";
			else
				ret += "(";
		}
		return ret;
	}

	public int cutStr(String p) {
		int open = 0;
		int close = 0;
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == ')')
				open++;
			else
				close++;
			if (open == close)
				break;
		}
		return open + close;
	}

	public boolean isCorrect(String p) {
		while (p.contains("()"))
			p = p.replace("()", "");

		return p.length() == 0;
	}
}