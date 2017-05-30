package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.StringUtils;

import utils.SqlTools;

public class Main implements Runnable {

	private static ArrayList<ConHandler> cons;

	@Override
	public void run() {
		ServerSocket ss;
		try {
			ss = new ServerSocket(23456);
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
			String conIP = con.getIP().substring(1).split(":")[0];
			System.out.println(conIP);
			System.out.println(SqlTools.getIp(ID));
			if (conIP == SqlTools.getIp(ID)|| conIP.contains("127.0.0.1")){
				System.out.println("joa");
				con.send(msg);
				return true;
			}
		}
		return false;
	}
}
