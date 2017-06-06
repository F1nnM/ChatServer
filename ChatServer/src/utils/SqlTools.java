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

	}

	public static void d() throws SQLException, IOException { // disconnect
		conn.close();
	}

	private static ResultSet query(String cmd)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(cmd);
		return rs; 
	}

	private static void update(String cmd)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(cmd);
	}



	public static String getIp(int user_ID) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ResultSet rs = query("SELECT ip FROM test.ips WHERE ID='"+user_ID+"';");
		rs.first();
		return rs.getString("ip");
	}
	
	public static boolean checkOnline(int ID) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ResultSet rs = query("SELECT ip FROM test.ips WHERE ID='"+ID+"';");
		rs.first();
		return (!(rs.getString("ip")==null));
	}
	
	public static boolean checkOnline(String ip) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ResultSet rs = query("SELECT ip FROM test.ips WHERE ip='"+ip+"';");
		return (rs.isBeforeFirst());
	}

}
