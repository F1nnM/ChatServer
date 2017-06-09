package com;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ConHandler implements Runnable {

	private Socket socket;
	private boolean run = false;
	private BufferedInputStream in;
	private BufferedOutputStream out;
	private static int hasNew = 0;

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
		hasNew = 1;
	}

	@Override
	public void run() {
		try {
			run = true;
			in = new BufferedInputStream(socket.getInputStream());
			out = new BufferedOutputStream(socket.getOutputStream());

			socket.setKeepAlive(true);
			while (run) {
				System.out.println(hasNew);
				out.write(hasNew);
				hasNew = 0;
				if (in.available() > 0) {
					Main.newMessage(in.read());
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
