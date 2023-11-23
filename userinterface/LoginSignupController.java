package userinterface;

import domain.player.*;
import techServices.UserInfoSaver;

import java.util.*;

public class LoginSignupController {
	private static LoginSignupController instance;

	private LoginSignupController() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginSignupController getInstance() {
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
		} catch (Exception e) {
			// TODO: add game controller message logic
			//GameController.showErrorMessage(e.toString());
		}
	}
	
	public void login(String username, String password) {
		try {
			Player player = UserInfoSaver.getPlayer(username, password);
			//TODO: add game controller logged in player logic
			//GameController.addToLoggedInPlayer(player);
		} catch (Exception e) {
			// TODO: add game controller message logic
			//GameController.showErrorMessage(e.toString());
		}
	}
}
