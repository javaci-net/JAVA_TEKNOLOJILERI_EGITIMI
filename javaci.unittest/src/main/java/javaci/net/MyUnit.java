package javaci.net;

public class MyUnit {
/*
	public String concatenate(String str1, String str2) {
		return str1+str2;
	}
	*/
	public String concatenate(String ...strings) {
		StringBuilder sb = new StringBuilder();
		for (String s: strings) {
			if (s==null)
				sb.append("");
			else 
				sb.append(s);
		}
		return sb.toString();
	}

	public String[] getTheStringArray(String string, String ...sep) {
		String seperator;
		if (sep == null || sep.length == 0)
			seperator = " ";
		else 
			seperator = sep[0];
		return string.split(seperator);
	}

}
