package test;

import domain.GameController;
import userinterface.LoginSignupController;

public class TestGameInitializer {

	public TestGameInitializer() {
		// TODO Auto-generated constructor stub
	}

	public static void initializeTestGame() {
		LoginSignupController.getInstance().signup("TestUser1", "user_1");
		LoginSignupController.getInstance().signup("TestUser2", "user_2");

		LoginSignupController.getInstance().login("TestUser1", "user_1");
		LoginSignupController.getInstance().login("TestUser2", "user_2");

		GameController.getInstance().initializeGame();
	}
}
