package com;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

import utils.SqlTools;

public class ConHandler implements Runnable {

	private Socket socket;
	private boolean run = false;
	private BufferedInputStream in;
	private BufferedOutputStream out;
	private static int hasNew = 0;

	public ConHandler(Socket socket) {
		this.socket = socket;
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
			
			while (in.available()<1) {
				Main.out("waiting");
			}
			SqlTools.setIP(in.read(), socket.getRemoteSocketAddress().toString());
			Main.out(socket.getRemoteSocketAddress());
			
			while (run) {
				System.out.flush();
				if (hasNew==1){
					out.write(hasNew);
					hasNew = 0;
					out.flush();
				}
				if (in.available() > 0) {
					Main.newMessage(in.read());
				}
				Thread.sleep(500);
			}
			Main.out("Stopped: " + getAddress().toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SocketAddress getAddress() {
		return socket.getRemoteSocketAddress();
	}

}
