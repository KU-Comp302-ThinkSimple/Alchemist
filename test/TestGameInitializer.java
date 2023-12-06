package test;

import domain.GameController;
import userinterface.LoginSignupController;

public class TestGameInitializer {

	public TestGameInitializer() {
		// TODO Auto-generated constructor stub
	}
	
	public static void initializeTestGame() {
		LoginSignupController.getInstance().signup("user_1", "user_1");
		LoginSignupController.getInstance().signup("user_2", "user_2");
		
		LoginSignupController.getInstance().login("user_1", "user_1");
		LoginSignupController.getInstance().login("user_2", "user_2");
		
		GameController.initializeGame();
	}
}
