package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlTools {

	private static Connection conn;

	public static void c() // connect
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		final String hostname = loginManager.getHost();
		final String port = loginManager.getSqlPort();
		final String dbname = loginManager.getSqlDb();
		final String user = loginManager.getSqlUser();
		final String password = loginManager.getPass();

		conn = null;
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		String url = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname;
		conn = DriverManager.getConnection(url, user, password);

		Logger.log("[SQL] Connected");
	}

	public static void d() throws SQLException, IOException { // disconnect
		conn.close();
		Logger.log("[SQL] Disconnected");
	}

	private static ResultSet query(String cmd) {
		ResultSet rs = null;
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(cmd);
		} catch (Exception e) {
			main.Main.ErrorQuit(e);
		}
		return rs;
	}

	private static void update(String cmd) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(cmd);
		} catch (Exception e) {
			main.Main.ErrorQuit(e);
		}
	}

	public static String getIp(int user_ID) {
		try {
			ResultSet rs = query("SELECT ip FROM test.ips WHERE ID='" + user_ID + "';");
			if (rs.first())
				return rs.getString("ip");
		} catch (Exception e) {
			main.Main.ErrorQuit(e);
		}
		return "";
	}

	public static boolean checkOnline(String IP) {
		try {
			ResultSet rs = query("SELECT ip FROM test.ips WHERE Ip='" + IP + "';");
			if (rs.first())
				return (!(rs.getString("ip") == null));
		} catch (Exception e) {
			main.Main.ErrorQuit(e);
		}
		return false;
	}

	/**
	 * Saves the IP of a specific user in the database
	 * 
	 * @param user_ID
	 *            The user (ID) to set the IP
	 * @param IP
	 *            The IP to set
	 * @author Finn
	 */
	public static void setIP(int user_ID, String IP) {
		if (IP == null) {
			update("UPDATE test.ips SET ip=null WHERE ID='" + user_ID + "';");
		} else {
			update("UPDATE test.ips SET ip='" + IP + "' WHERE ID='" + user_ID + "';");
		}
	}

}
