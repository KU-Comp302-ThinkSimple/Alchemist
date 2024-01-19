package domain.initialization;

import java.util.Map;

public interface GameInitializerAdapter {
	public void startInitialization(Map<String, Object> initialSettings) throws Exception;
	public void finalizeInitialization(Map<String, Object> gameSettings) throws Exception;
}
