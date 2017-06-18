package com;

import java.sql.SQLException;
import java.util.ArrayList;

import utils.SqlTools;

public class Checker implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(10000);
				Main.out("Checking..");
				ArrayList<ConHandler> conTmp= new ArrayList<>();
				for (ConHandler con : Main.cons) {
					if (!(SqlTools.checkOnline(con.getAddress().toString()))) {
						con.stop();
						Main.out("disconnected: "+con.getAddress().toString());
					}else{
						conTmp.add(con);
					}
				}
				Main.cons = conTmp;
			}
		} catch (InterruptedException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
