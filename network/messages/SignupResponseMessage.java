package network.messages;

public class SignupResponseMessage extends Message {
	private final String response;
	public SignupResponseMessage(String response) {
		this.response = response;
	}
	public String getResponse() {
		return response;
	}

}
