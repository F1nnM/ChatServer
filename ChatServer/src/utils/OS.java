package utils;

public class OS {

	public final static int OS_UNDEFINED = -1;
	public final static int OS_LINUX = 0;
	public final static int OS_WINDOWS = 1;
	public final static int OS_SOLARIS = 2;
	public final static int OS_MACOS = 3;
	
	public static int get() {
		int osType;
		String osName = System.getProperty("os.name");
		String osNameMatch = osName.toLowerCase();
		if (osNameMatch.contains("linux")) {
			osType = OS_LINUX;
		} else if (osNameMatch.contains("windows")) {
			osType = OS_WINDOWS;
		} else if (osNameMatch.contains("solaris") || osNameMatch.contains("sunos")) {
			osType = OS_SOLARIS;
		} else if (osNameMatch.contains("mac os") || osNameMatch.contains("macos") || osNameMatch.contains("darwin")) {
			osType = OS_MACOS;
		} else {
			osType=OS_UNDEFINED;
		}
		return osType;
	}

}
