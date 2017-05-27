package utils;

public class loginManager {
	
	private static final char[] pass = {102, 49, 105, 49, 110, 49, 110, 49};
	private static final char[] ftp_pass = {70, 49, 105, 49, 110, 49, 110, 49};
	private static final char[] host = {102, 105, 110, 110, 115, 119, 101, 98, 46, 100, 101 };
	private static final char[] sql_port = {51, 51, 48, 54};
	private static final char[] sql_db = {116, 101, 115, 116};
	private static final char[] sql_user = {102, 105, 110, 110};
	private static final char[] ftp_user = {70, 105, 110, 110, 115, 119, 101, 98, 97, 100, 109, 105, 110};
	private static final char[] ftp_host = {102, 116, 112, 46, 102, 105, 110, 110, 115, 119, 101, 98, 46, 100, 101};

	public static String getPass() {
		String tmp = "";
		for (char c : pass) {
			tmp = tmp + c;
		}
		return tmp;
	}
	
	public static String getFtpPass() {
		String tmp = "";
		for (char c : ftp_pass) {
			tmp = tmp + c;
		}
		return tmp;
	}

	public static String getHost() {
		String tmp = "";
		for (char c : host) {
			tmp = tmp + c;
		}
		return tmp;
	}
	
	public static String getFtpHost() {
		String tmp = "";
		for (char c : ftp_host) {
			tmp = tmp + c;
		}
		return tmp;
	}

	public static String getSqlPort() {
		String tmp = "";
		for (char c : sql_port) {
			tmp = tmp + c;
		}
		return tmp;
	}

	public static String getSqlDb() {
		String tmp = "";
		for (char c : sql_db) {
			tmp = tmp + c;
		}
		return tmp;
	}

	public static String getSqlUser() {
		String tmp = "";
		for (char c : sql_user) {
			tmp = tmp + c;
		}
		return tmp;
	}

	public static String getFtpUser() {
		String tmp = "";
		for (char c : ftp_user) {
			tmp = tmp + c;
		}
		return tmp;
	}

}
