package domain.loginSignup;

import java.io.Serializable;

public class LoginResult implements Serializable{
	private final String message;
	private final int localPlayerIndex;
	public LoginResult(String message, int localPlayerIndex) {
		this.message = message;
		this.localPlayerIndex = localPlayerIndex;
	}
	
	public boolean isSuccess() {
		return localPlayerIndex != -1;
	}
	
	public String getMessage() {
		return message;
	}
	public int getLocalPlayerIndex() {
		return localPlayerIndex;
	}
}
