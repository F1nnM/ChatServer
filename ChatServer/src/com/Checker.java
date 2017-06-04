package com;

import java.io.IOException;

public class Checker implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				for (ConHandler con : Main.cons) {
					if (!(con.getInetAddress().isReachable(3000))) {
						con.stop();
					}
				}
				Thread.sleep(10000);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
