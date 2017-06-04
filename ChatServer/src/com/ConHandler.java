package com;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import utils.SqlTools;

public class ConHandler implements Runnable {

	private Socket socket;
	private boolean run = false;
	private Scanner in;
	private PrintWriter out;
	private static ArrayList<String> ToWrite;

	public ConHandler(Socket socket) {
		this.socket = socket;
	}

	public String getIP() {
		return socket.getRemoteSocketAddress().toString();
	}

	public void stop() {
		run = false;
	}

	public void send(String str) {
		ToWrite.add(str);
	}

	@Override
	public void run() {
		ToWrite = new ArrayList<String>();
		try {
			run = true;
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);
			socket.setKeepAlive(true);
			String input;
			while (run) {
				if (ToWrite.size() > 0) {
					out.println(ToWrite.get(0));
					ToWrite.remove(0);
				}else{
					out.println("");
				}
				if (in.hasNext()) {
					if (!(input = in.nextLine()).equals("")){
						String[] parts = input.split("-");
						int id = Integer.parseInt(parts[1]);
						if (SqlTools.checkOnline(id)) {
							Main.sendTo(parts[0], id);
						}

					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
