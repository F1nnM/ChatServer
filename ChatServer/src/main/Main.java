package main;

import java.io.IOException;
import java.sql.SQLException;

import utils.SqlTools;

public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		SqlTools.c();
		Thread t = new Thread(new com.Main());
		t.start();
		
	}

}
