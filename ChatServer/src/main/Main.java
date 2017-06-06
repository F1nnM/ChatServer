package main;

import java.io.IOException;
import java.sql.SQLException;

import utils.SqlTools;

public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		SqlTools.c();
		Thread t = new Thread(new com.Main());
		Thread tCheck = new Thread(new com.Checker());
		t.start();
		tCheck.start();
		System.out.println("Started!");
	}

}
