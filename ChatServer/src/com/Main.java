package com;

import java.net.ServerSocket;
import java.net.SocketAddress;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.SqlTools;

public class Main implements Runnable {

	public static ArrayList<ConHandler> cons;

	@Override
	public void run() {
		ServerSocket ss;
		try {
			ss = new ServerSocket(23456);
			cons = new ArrayList<ConHandler>();
			while (main.Main.running) {
				try {
					ConHandler con = new ConHandler(ss.accept());
					cons.add(con);
					new Thread(con).start();
				} catch (Exception e) {
					main.Main.ErrorQuit(e);
				}
				Thread.sleep(300);
			}
			
			for (ConHandler con : cons) {
				con.stop();
			}
			
		} catch (Exception e1) {
			main.Main.ErrorQuit(e1);
		}
	}

	public static void newMessage(int ID) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		for (ConHandler con : cons) {
			if (con.getAddress().toString().equals(SqlTools.getIp(ID))){
				con.newMessage();
				break;
			}
		}		
	}
	
	public static void out(Object msg){
		System.out.println(msg);
	}
	
	public static ArrayList<SocketAddress> getAll(){
		ArrayList<SocketAddress> all = new ArrayList<>();
		for (ConHandler con : cons) {
			all.add(con.getAddress());
		}
		return all;
	}
	
	
}
