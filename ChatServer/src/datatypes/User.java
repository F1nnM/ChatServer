package datatypes;


public class User {

	private int id;
	private String name;
	private String ip;
	private String email;
	private String state; //for friends

	public User(){
		id = -1;
		name = null;
		ip = null;
		email = null;
		state = null;
	}

	/**
	 * Get the user ID
	 * @return The ID; -1 if not set
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the username 
	 * @return The name; null if not set
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the users IP
	 * @return The IP; null if not set
	 */
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString(){
		return "User: ['Email': "+email+"; 'Username': "+name+"; 'IP': "+ip+"; 'ID': "+id+";]";
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
 