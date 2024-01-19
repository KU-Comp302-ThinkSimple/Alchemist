package network.messages;

import domain.loginSignup.*;

public class LoginResponseMessage extends Message{
	private final LoginResult response;
	public LoginResponseMessage(LoginResult response) {
		this.response = response;
	}
	public LoginResult getResponse() {
		return response;
	}
}
