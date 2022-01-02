package algorithm.chapter3.verse8;

public class WildCard_Han {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String p1 = "he?p";
		String[] input1 = {"help", "heap", "helpp"};
		
		String p2 = "*p*";
		String[] input2 = {"help", "papa", "hello"};
		
		String p3 = "*bb*";
		String[] input3 = {"babbbc"};
		
		
//		System.out.println("p1 chkAster : " + chkAster(p1));
//		System.out.println("p2 chkAster : " + chkAster(p2));
//		System.out.println("p3 chkAster : " + chkAster(p3));
//		
//		System.out.println("p1 chkQm : " + chkQm(p1));
//		System.out.println("p2 chkQm : " + chkQm(p2));
//		System.out.println("p3 chkQm : " + chkQm(p3));
	
		
		System.out.println(chkQmWildCard(p1, "help"));
	}

	// asterisk chk
	static boolean chkAster(String str) {
		if(null != str && str.contains("*")) return true;
		else return false;
	}
	
	// question mark chk
	static boolean chkQm(String str) {
		if(null != str && str.contains("?")) return true;
		else return false;
	}
	
	static String test(String patternStr, String[] inputArr) {
		
		if(null == patternStr || inputArr.length == 0) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(chkAster(patternStr) && chkQm(patternStr)) {
			// ??
		} else if(!chkAster(patternStr) && chkQm(patternStr)) {
			
			for(String input : inputArr) {
				if(patternStr.length() == input.length()) {
					if(chkQmWildCard(patternStr, input)) {
						if(sb.length() > 0) {
							sb.append(", ");
						}
						
						sb.append(input);
					}
				}
			}
			
		}else if(chkAster(patternStr) && !chkQm(patternStr)) {
			
		}else {
			for(String input : inputArr) {
				if(patternStr.equals(input)) {
					if(sb.length() > 0) {
						sb.append(", ");
					}
					
					sb.append(input);
				} 
					
			}
		}
		
		return sb.toString();
	}
	
	static boolean chkQmWildCard(String patternStr, String chkStr) {
		
		String[] qmSplitArr = patternStr.split("\\?");
		int index = 0;
		
		for(String qmSplit : qmSplitArr) {
			if(qmSplit.length() > 0) {
				if(!chkStr.substring(index, index+qmSplit.length()).equals(qmSplit)) return false;
				index += qmSplit.length()+1;
			}
		}
		return true;
	}
	
	static boolean chkAsterWildCard(String patternStr, String chkStr) {
		return false;
	}
	
	
}
