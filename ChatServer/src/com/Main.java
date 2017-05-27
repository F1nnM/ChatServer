package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

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

	public static boolean sendTo(String msg, String IP) {
		for (ConHandler con : cons) {
			if (con.getIP() == IP)
				con.send(msg);
			return true;
		}
		return false;
	}
}
