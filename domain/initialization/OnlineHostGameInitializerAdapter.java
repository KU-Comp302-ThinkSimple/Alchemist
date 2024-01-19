package domain.initialization;

import java.io.IOException;
import java.util.Map;

import domain.LocalData;
import network.Client;
import network.Server;

public class OnlineHostGameInitializerAdapter implements GameInitializerAdapter{
	private Server server;
	private Client client;
	public OnlineHostGameInitializerAdapter() {
	}
	public void startInitialization(Map<String, Object> initialSettings) throws Exception{
		try {
			Integer port = (Integer) initialSettings.get("port");
			Integer bufferSize = (Integer) initialSettings.get("bufferSize");
			
			server = new Server(port, bufferSize);
			server.start();
			
			client = new Client("host", "127.0.0.1", port);
			client.start();
		}
		catch(IOException ioe) {
			throw new Exception("Could not start server, or connect to local server.");
		}
		catch (Exception e) {
			throw new Exception("Invalid arguments");
		}
	}
	public void finalizeInitialization(Map<String, Object> gameSettings) throws Exception{
		new InitializeGameHelper();	
		//TODO: how to set local player?
		//LocalData.getInstance().setLocalPlayer(null);
		client.update();
	}

}
