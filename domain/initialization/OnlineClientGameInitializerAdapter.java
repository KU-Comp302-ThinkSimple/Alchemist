package domain.initialization;

import java.util.Map;

import domain.GameController;

public class OnlineClientGameInitializerAdapter implements GameInitializerAdapter{

	public OnlineClientGameInitializerAdapter() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void startInitialization(Map<String, Object> initialSettings) throws Exception{
		//login
		//if login successful, connect to server
	};
	@Override
	public void finalizeInitialization(Map<String, Object> gameSettings) throws Exception{
		//TODO: add when network gets completed
	}

}
