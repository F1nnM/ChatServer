package main;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import ConHandler;

public class Main {

	private static ArrayList<ConHandler> cons;

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(50000);
		cons = new ArrayList<ConHandler>();
		while (true) {
			try {
				ConHandler con = new ConHandler(ss.accept());
				cons.add(con);
				new Thread(con).start();
				System.out.println("Connected to: "+con.getIP());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean sendTo(String msg, String IP) {
		for (ConHandler con : cons) {
			if(con.getIP()==IP)con.send(msg);
			return true;
		}
		return false;
	}
}
