package com;

import java.util.ArrayList;

import utils.SqlTools;

public class Checker implements Runnable {

	@Override
	public void run() {
		try {
			while (main.Main.running) {
				Thread.sleep(10000);
				ArrayList<ConHandler> conTmp= new ArrayList<>();
				for (ConHandler con : Main.cons) {
					if (!(SqlTools.checkOnline(con.getAddress().toString()))) {
						con.stop();
					}else{
						conTmp.add(con);
					}
				}
				Main.cons = conTmp;
			}
		} catch (InterruptedException e) {
			main.Main.ErrorQuit(e);
		}

	}

}
