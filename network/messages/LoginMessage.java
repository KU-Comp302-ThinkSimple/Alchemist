package network.messages;

public class LoginMessage extends Message {
	private final String username;
	private final String password;
	public LoginMessage(String username, String password) {
		this.password = password;
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}
