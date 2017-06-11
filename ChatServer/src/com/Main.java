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
				for (ConHandler con : cons) {
					System.out.println(cons.size());
					System.out.flush();
				}
				try {
					ConHandler con = new ConHandler(ss.accept());
					cons.add(con);
					new Thread(con).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Thread.sleep(300);
			}
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public static void newMessage(int ID) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int i = 0;
		for (ConHandler con : cons) {
			if (con.getAddress().toString().equals(SqlTools.getIp(ID))){
				con.newMessage();
				System.out.println("notified"+con.getAddress().toString());
				break;
			}
			i++;
		}		
	}
	
}
