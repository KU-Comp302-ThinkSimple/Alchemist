package network.messages;

import domain.loginSignup.*;

public class SignupResponseMessage extends Message {
	private final SignupResult response;
	public SignupResponseMessage(SignupResult response) {
		this.response = response;
	}
	public SignupResult getResponse() {
		return response;
	}

}
