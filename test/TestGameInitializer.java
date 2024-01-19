package test;

import domain.GameController;
import domain.initialization.GameInitializerAdapter;
import domain.initialization.GameInitializerAdapterFactory;
import domain.initialization.GameInitializerAdapterFactory.InitializerType;
import domain.loginSignup.LoginSignupController;

public class TestGameInitializer {

	public TestGameInitializer() {
		// TODO Auto-generated constructor stub
	}

	public static void initializeTestGame() {
		LoginSignupController.getInstance().signup("user_1", "user_1");
		LoginSignupController.getInstance().signup("user_2", "user_2");
		LoginSignupController.getInstance().signup("user_3", "user_3");
		LoginSignupController.getInstance().signup("user_4", "user_4");

		LoginSignupController.getInstance().login("user_1", "user_1");
		LoginSignupController.getInstance().login("user_2", "user_2");
		LoginSignupController.getInstance().login("user_3", "user_3");
		LoginSignupController.getInstance().login("user_4", "user_4");

		GameInitializerAdapter initializerAdapter =  GameInitializerAdapterFactory.getInstance().getInitializerAdapter(InitializerType.Offline);
		try {
			initializerAdapter.finalizeInitialization(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
