package domain.loginSignup;

import java.io.Serializable;

public class SignupResult implements Serializable{
	private final String message;
	private final boolean success;
	public SignupResult(String message, boolean success) {
		this.message = message;
		this.success = success;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public String getMessage() {
		return message;
	}
}
