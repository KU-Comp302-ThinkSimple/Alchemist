package domain.initialization;

import java.io.IOException;
import java.util.Map;

import domain.LocalData;
import domain.loginSignup.LoginResult;
import domain.loginSignup.LoginSignupController;
import exception.UserErrorException;
import network.Client;
import network.Server;

public class OnlineHostGameInitializerAdapter implements GameInitializerAdapter{
	private Server server;
	private Client client;

	public void startInitialization(Map<String, Object> initialSettings) throws Exception{
		try {
			Integer port = (Integer) initialSettings.get("port");
			if(port==null) {
				throw new Exception("Port cannot be null");
			}
			
			server = new Server(port);
			server.start();
			
			client = new Client("hostClient", "127.0.0.1", port);
			client.start();
		}
		catch(IOException ioe) {
			throw new Exception("Could not start server, or connect to local server.");
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}
	public void finalizeInitialization(Map<String, Object> gameSettings) throws Exception, UserErrorException{
		String password = (String) gameSettings.get("password");
		String username = (String) gameSettings.get("username");
		
		if(password == null || username == null) {
			throw new Exception("Password or username cannot be null");
		}
		
		LoginResult result = LoginSignupController.getInstance().login(username, password);
		if(!result.isSuccess()) {
			throw new UserErrorException(result.getMessage());
		}
		
		LocalData.getInstance().setLocalPlayerIndex(result.getLocalPlayerIndex());
		
		new InitializeGameHelper();	
		client.update();
	}

}
