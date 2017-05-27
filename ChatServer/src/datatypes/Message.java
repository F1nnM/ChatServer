package datatypes;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import files.Exceptions;
import utils.SqlTools;

public class Message {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
	private Object content;
	private Timestamp timestamp;
	private int state;
	private boolean received;
	private String type;
	private int ID_from;
	private int ID_to;
	
	public Message(Object content, String type, Timestamp timestamp2, int ID_from, int ID_to) {
		this.content = content;
		this.timestamp = timestamp2;
		this.type = type;
		this.ID_from = ID_from;
		this.ID_to = ID_to;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Object getContent() {
		return content;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public boolean isReceived() {
		return received;
	}
	
	public String getType(){
		return type;
	}

	public int getID_from() {
		return ID_from;
	}

	public int getID_to() {
		return ID_to;
	}
	
	public String toString(){
		String messToString = null;
		try {
			messToString = (sdf.format(timestamp) + " " + SqlTools.getUser(ID_from).getName() + ": " + (content.equals("MESSAGE") ? (String) content : null));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			Exceptions.send(e);
		}
		return messToString;
	}
}
