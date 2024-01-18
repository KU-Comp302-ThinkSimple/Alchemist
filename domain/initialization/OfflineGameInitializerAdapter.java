package domain.initialization;

import java.util.Map;

public class OfflineGameInitializerAdapter implements GameInitializerAdapter {

	public OfflineGameInitializerAdapter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeGame(Map<String, Object> gameSettings) throws Exception {
		new InitializeGameHelper();		
	}
}
