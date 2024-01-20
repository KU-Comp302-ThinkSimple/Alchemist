package domain.initialization;

import java.io.IOException;
import java.util.Map;

import domain.GameController;
import domain.LocalData;
import domain.loginSignup.LoginResult;
import domain.loginSignup.LoginSignupController;
import exception.UserErrorException;
import network.Client;
import network.Server;

public class OnlineHostGameInitializerAdapter implements GameInitializerAdapter{
	public void startInitialization(Map<String, Object> initialSettings) throws Exception{
		try {
			Integer port = (Integer) initialSettings.get("port");
			if(port==null) {
				throw new Exception("Port cannot be null");
			}
			
			Server server = new Server(port);
			server.start();
			LocalData.getInstance().setServer(server);

			
			Client client = new Client("hostClient", "127.0.0.1", port);
			client.start();
			LocalData.getInstance().setClient(client);

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
		GameController.getInstance().setGameMode("online");
		LocalData.getInstance().getClient().addThisToObservables();
		LocalData.getInstance().getClient().update();
	}

}
