package domain.loginSignup;

import domain.GameController;
import domain.player.*;
import techServices.UserInfoSaver;

import java.io.Serializable;
import java.util.*;

public class LoginSignupController {
	private static LoginSignupController instance;
//	private static String loginMessage="";
	public static String loginSuccessMessage = "Log in successful.";
//	private static String signUpMessage="";
	public static String signUpSuccessMessage = "Signed up successfully.";
	
	private LoginSignupController() {
		// TODO Auto-generated constructor stub
	}

	public static LoginSignupController getInstance() {
		if (instance == null) {
			instance = new LoginSignupController();
		}
		return instance;
	}

	public SignupResult signup(String username, String password) {
		Random rand = new Random();
		int id = rand.nextInt();
		while(!UserInfoSaver.isPlayerIDAvailable(id)) {
			id = rand.nextInt();
		}
		Player player = new Player(id, username, password);
		try {
			UserInfoSaver.savePlayer(player);
			return new SignupResult(signUpSuccessMessage, true);
		} catch (Exception e) {
			return new SignupResult("There is already a user with the nickname", true);
			// TODO: add game controller message logic
			//GameController.showErrorMessage(e.toString());
		}
	}

	public LoginResult login(String username, String password) {
		try {
			Player player = UserInfoSaver.getPlayer(username, password);
			if (GameController.getInstance().getActivePlayers().contains(player)) {
				return new LoginResult("This user is already logged in.", -1);
			}
			//TODO: add game controller logged in player logic
			System.out.print("Logged in username: ");
			System.out.print(player.getPlayerName());
			System.out.print(" password: ");
			System.out.println(player.getPassword());
			GameController.getInstance().getActivePlayers().add(player);
			return new LoginResult(loginSuccessMessage, GameController.getInstance().getActivePlayers().size()-1);
		} catch (Exception e) {
			return new LoginResult("Username or password does not match.", -1);
			// TODO: add game controller message logic
			//GameController.showErrorMessage(e.toString());
		}
	}
}
