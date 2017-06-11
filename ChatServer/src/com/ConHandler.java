package com;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import utils.SqlTools;

public class ConHandler implements Runnable {

	private Socket socket;
	private boolean run = false;
	private BufferedInputStream in;
	private BufferedOutputStream out;
	private static int hasNew = 0;
	private static InetSocketAddress addr;
	private static int id;

	public ConHandler(Socket socket) {
		this.socket = socket;
	}

	public void stop() {
		run = false;
	}

	public void newMessage() {
		hasNew = 1;
	}
	
	public int getID(){
		return id;
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
			addr = new InetSocketAddress(socket.getInetAddress(), socket.getPort());
			id = in.read();
			SqlTools.setIP(id, addr.toString());
			Main.out(addr.toString());
			Main.out(id);
			
			while (run) {
				Main.out("now in main loop: "+addr+"  "+id);
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
			SqlTools.setIP(id, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InetSocketAddress getAddress() {
		return addr;
	}

}
