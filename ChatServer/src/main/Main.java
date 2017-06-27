package main;

import utils.Logger;
import utils.OS;
import utils.SqlTools;

public class Main {

	public static boolean running = false;

	public static void main(String[] args) {
		try {
			if (args.length > 0) {
				Logger.path = args[0];
			} else {
				switch (OS.get()) {
				case OS.OS_LINUX:
					Logger.path = "/tmp/ChatServerLogs";
					break;
				case OS.OS_MACOS:
					
					break;
				case OS.OS_SOLARIS:

					break;
				case OS.OS_WINDOWS:
					Logger.path = "C:\\ChatServerLogs";
					break;
				case OS.OS_UNDEFINED:
					ErrorQuit(new Exception("Operating System not recognized. Please enter preferred path for logs as argument"));
					break;
				default:
					ErrorQuit(new Exception("Operating System not supported. Please enter preferred path for logs as argument"));
					break;
				}
			}
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
			utils.SqlTools.setIP(0, com.Main.getHostIp());
		} catch (Exception e) {
			ErrorQuit(e);
		}

	}

	public static void stop() {
		try {
			Logger.log("[Main] Exiting..");
			running = false;
			Thread.sleep(1500);
			utils.SqlTools.setIP(0, null);
			SqlTools.d();
		} catch (Exception e) {
			ErrorQuit(e);
		}
		System.out.println("Good bye  :)");
		System.exit(0);
	}

	public static void ErrorQuit(Exception e) {
		try {
			Logger.elog("[Main] ERROR: " + e.getMessage());
			Logger.elog("[Main] ForceQuitting...");
			e.printStackTrace(System.out);
		} catch (Exception e1) {
			// Nothing I can do about it..
		}
		System.exit(1);
	}

}
