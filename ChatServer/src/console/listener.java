package console;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.SocketAddress;
import java.util.Scanner;

import utils.Logger;

public class listener implements Runnable {

	public static void exec(String cmd) {
		switch (cmd.toLowerCase()) {
		case "help":
			System.out.println("Supported commands: help, stop, list, logcat, clearlog");
			break;
		case "stop":
			main.Main.stop();
			break;
		case "list":
			for (SocketAddress sa : com.Main.getAll()) {
				System.out.println(sa.toString());
			}
			break;
		case "logcat":
			Scanner inputLog;
			try {
				inputLog = new Scanner(new File("C:\\ChatServerLogs\\latest.log"));
				while (inputLog.hasNextLine()) {
					System.out.println(inputLog.nextLine());
				}
				break;
			} catch (Exception e) {
				main.Main.ErrorQuit(e);
			}
			break;
		case "clearlog":
			Logger.clear();
			break;
		default:
			System.out.println("Sorry. I didn't underatand this. Check your spelling or see help for reference.");
			break;
		}
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("");
			while (main.Main.running) {
				System.out.print("> ");
				exec(br.readLine());
			}
		} catch (Exception e) {
			main.Main.ErrorQuit(e);
		}
	}

}
