package domain.initialization;

import java.util.Map;

import domain.GameController;

public class OfflineGameInitializerAdapter implements GameInitializerAdapter {
	@Override
	public void startInitialization(Map<String, Object> initialSettings) throws Exception{
		
	};
	@Override
	public void finalizeInitialization(Map<String, Object> gameSettings) throws Exception{
		GameController.getInstance().setGameMode("offline");
		new InitializeGameHelper();
	}
}
