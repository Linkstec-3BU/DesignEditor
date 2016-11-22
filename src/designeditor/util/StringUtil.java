package designeditor.util;

public class StringUtil {
	public static String NullToEmpty(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
			
	}
	
	public static void main(String args[]) {
		System.out.println();
	}
	
	public static String GetUniqueId() {
		return String.valueOf(System.nanoTime());
	}
	
	public static boolean NotNullAndEmpty(String str) {
		if (str == null) {
			return false;
		} else if ("".equals(str)) {
			return false;
		} 
		return true;
	}
}
