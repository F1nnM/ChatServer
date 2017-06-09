package com;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.StringUtils;

import utils.SqlTools;

public class Main implements Runnable {

	static ArrayList<ConHandler> cons;

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

	public static void newMessage(int ID) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int i = 0;
		System.out.println("Notify: "+ID);
		for (ConHandler con : cons) {
			String conIP = con.getIP();
			System.out.println(i+" "+conIP);
			System.out.println(i+" "+SqlTools.getIp(ID));
			if (conIP.equals(SqlTools.getIp(ID))){
				System.out.println("joa");
				con.newMessage();
			}
			i++;
		}		
	}
	
}
