package com;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.SqlTools;

public class Checker implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(10000);
				System.out.println("Checking..");
				ArrayList<ConHandler> conTmp= new ArrayList<>();
				for (ConHandler con : Main.cons) {
					if (!(SqlTools.checkOnline(con.getIP()))) {
						con.stop();
						System.out.println("disconnected: "+con.getIP());
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