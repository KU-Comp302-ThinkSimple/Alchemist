package network.messages;

public class LoginResponseMessage extends Message{
	private final String response;
	public LoginResponseMessage(String response) {
		this.response = response;
	}
	public String getResponse() {
		return response;
	}
}
