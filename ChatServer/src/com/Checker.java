package com;

import java.io.IOException;

public class Checker implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				System.out.println("Checking..");
				for (ConHandler con : Main.cons) {
					if (!(con.getInetAddress().isReachable(3000))) {
						con.stop();
						System.out.println("disconnected: "+con.getIP());
					}
				}
				Thread.sleep(10000);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
