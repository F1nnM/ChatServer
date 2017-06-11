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
				System.out.println("waiting");
			}
			addr = new InetSocketAddress(socket.getInetAddress(), socket.getPort());
			id = in.read();
			SqlTools.setIP(id, addr.toString());
			System.out.println(addr.toString());
			System.out.println(id);
			
			while (run) {
				System.out.println("now in main loop: "+getAddress()+"  "+getID());
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
			System.out.println("Stopped: " + getAddress().toString());
			SqlTools.setIP(id, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InetSocketAddress getAddress() {
		return addr;
	}

}
