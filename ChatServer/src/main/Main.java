package main;

import utils.Logger;
import utils.SqlTools;

public class Main {

	public static boolean running = false;

	public static void main(String[] args) {
		try {
			Logger.log("[Main] Started");
			running = true;
			SqlTools.c();
			Thread tCon = new Thread(new com.Main());
			Thread tCheck = new Thread(new com.Checker());
			Thread tCons = new Thread(new console.listener());
			tCon.start();
			Logger.log("[Main] Main Connector started");
			tCheck.start();
			Logger.log("[Main] Checker started");
			tCons.start();
			Logger.log("[Main] Console started");
			System.out.println("Started!");
		} catch (Exception e) {
			ErrorQuit(e);
		}

	}

	public static void stop() {
		try {
			Logger.log("[Main] Exiting..");
			running = false;
			SqlTools.d();
			Thread.sleep(1500);
		} catch (Exception e) {
			ErrorQuit(e);
		}
		System.out.println("Good bye  :)");
		System.exit(0);
	}

	public static void ErrorQuit(Exception e) {
		try {
			Logger.log("[Main] ERROR: " + e.getMessage());
			Logger.log("[Main] ForceQuitting...");
		} catch (Exception e1) {
			//Nothing I can do about it..
		}
		System.exit(1);
	}

}
