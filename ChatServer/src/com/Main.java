package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.SqlTools;

public class Main implements Runnable {

	private static ArrayList<ConHandler> cons;

	@Override
	public void run() {
		ServerSocket ss;
		try {
			ss = new ServerSocket(50000);
			cons = new ArrayList<ConHandler>();
			while (true) {
				try {
					ConHandler con = new ConHandler(ss.accept());
					cons.add(con);
					new Thread(con).start();
					System.out.println("Connected to: " + con.getIP());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static boolean sendTo(String msg, int ID) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		for (ConHandler con : cons) {
			System.out.println(con.getIP());
			System.out.println(SqlTools.getIp(ID));
			if (con.getIP() == SqlTools.getIp(ID))
				con.send(msg);
				System.out.println(msg+"  "+SqlTools.getIp(ID));
			return true;
		}
		return false;
	}
}
