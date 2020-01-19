package javaci.net;

public class Palindrome {

	
	public static boolean isPalindrome(String str) {
 		StringBuffer sb = new StringBuffer(str);
		sb = sb.reverse();
		boolean ret= sb.toString().equals(str);
		return ret;
	}
}
