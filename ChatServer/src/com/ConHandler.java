package com;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import main.Main;

public class ConHandler implements Runnable {

	private Socket socket;
	private boolean run = false;
	private Scanner in;
	private PrintWriter out;
	private ArrayList<String> ToWrite;

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
			String input;
			while (run) {
				if (ToWrite.size() > 0)
					out.write(ToWrite.get(0));
				if ((input = in.nextLine()) != null) {
					String[] parts = input.split("-");
					if (!(Main.sendTo(parts[0], parts[1]))) {
						send("Invalid IP");
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
