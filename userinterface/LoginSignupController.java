package userinterface;

import domain.GameController;
import domain.player.*;
import techServices.UserInfoSaver;

import java.util.*;

public class LoginSignupController {
	private static LoginSignupController instance;
	private static String loginMessage="";
	private static String signUpMessage="";

	private LoginSignupController() {
		// TODO Auto-generated constructor stub
	}
	
	public static LoginSignupController getInstance() {
		if (instance == null) {
			instance = new LoginSignupController();
		}
		return instance;
	}
	
	public void signup(String username, String password) {
		Random rand = new Random();
		int id = rand.nextInt();
		while(!UserInfoSaver.isPlayerIDAvailable(id)) {
			id = rand.nextInt();
		}
		Player player = new Player(id, username, password);
		try {
			UserInfoSaver.savePlayer(player);
			signUpMessage="Succesful.";
		} catch (Exception e) {
			signUpMessage="There is already a user with the nickname";
			// TODO: add game controller message logic
			//GameController.showErrorMessage(e.toString());
		}
	}
	
	public void login(String username, String password) {
		try {
			Player player = UserInfoSaver.getPlayer(username, password);
			//TODO: add game controller logged in player logic
			System.out.print("Logged in username: ");
			System.out.print(player.getPlayerName());
			System.out.print(" password: ");
			System.out.println(player.getPassword());
			GameController.getActivePlayers().add(player);
			loginMessage="success.";
		} catch (Exception e) {
			loginMessage="Username or password does not match.";
			// TODO: add game controller message logic
			//GameController.showErrorMessage(e.toString());
		}
	}

	public static String getLoginMessage() {
		return loginMessage;
	}

	public static void setLoginMessage(String loginMessage) {
		LoginSignupController.loginMessage = loginMessage;
	}

	public static String getSignUpMessage() {
		return signUpMessage;
	}

	public static void setSignUpMessage(String signUpMessage) {
		LoginSignupController.signUpMessage = signUpMessage;
	}
	
}
