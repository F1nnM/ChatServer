package com;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import utils.SqlTools;

public class ConHandler implements Runnable {

	private Socket socket;
	private boolean run = false;
	private Scanner in;
	private PrintWriter out;
	private static boolean hasNew = false;

	public ConHandler(Socket socket) {
		this.socket = socket;
	}

	public String getIP() {
		return socket.getRemoteSocketAddress().toString();
	}

	public void stop() {
		run = false;
	}

	public void newMessage() {
		hasNew = true;
	}

	@Override
	public void run() {
		try {
			run = true;
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);
			socket.setKeepAlive(true);
			String input;
			while (run) {
				out.print(hasNew);
				if(in.hasNextInt()){
					Main.newMessage(in.nextInt());
				}

			}
			System.out.println("Stopped: " + getIP());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InetAddress getInetAddress() {
		return socket.getInetAddress();
	}

}
