package domain.initialization;

public class GameInitializerAdapterFactory {
	private static GameInitializerAdapterFactory instance;
	private GameInitializerAdapterFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public enum InitializerType{Offline, OnlineHost, OnlineClient};
	
	public static GameInitializerAdapterFactory getInstance() {
		if(instance == null) {
			instance = new GameInitializerAdapterFactory();
		}
		return instance;
	}
	
	public GameInitializerAdapter getInitializerAdapter(InitializerType gameInitMode) {
		switch (gameInitMode) {
		case Offline: {
			return new OfflineGameInitializerAdapter();
		}
		
		case OnlineHost: {
			return new OnlineHostGameInitializerAdapter();
		}
		
		case OnlineClient: {
			return new OnlineClientGameInitializerAdapter();
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + gameInitMode);
		}
	}
}
