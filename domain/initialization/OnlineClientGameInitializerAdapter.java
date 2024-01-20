package domain.initialization;

import java.util.Map;
import java.util.Random;

import domain.GameController;
import domain.LocalData;
import domain.loginSignup.LoginResult;
import domain.loginSignup.LoginSignupController;
import exception.UserErrorException;
import network.Client;

public class OnlineClientGameInitializerAdapter implements GameInitializerAdapter{
	
	@Override
	public void startInitialization(Map<String, Object> initialSettings) throws Exception{
		Integer port = (Integer) initialSettings.get("port");
		String hostAddress = (String) initialSettings.get("hostAddress");
		
		if(port == null || hostAddress == null) {
			throw new Exception("Port or hostAddress cannot be null");
		}
		
		Client client = new Client("client_" + (new Random()).nextInt(100000), hostAddress, port);
		client.start();
		LocalData.getInstance().setClient(client);
	};
	@Override
	public void finalizeInitialization(Map<String, Object> gameSettings) throws Exception{
		String password = (String) gameSettings.get("password");
		String username = (String) gameSettings.get("username");
		Integer timeoutMillis = (Integer) gameSettings.get("timeoutMillis");
		if(timeoutMillis == null) {
			timeoutMillis = 5000;
		}
		
		if(password == null || username == null) {
			throw new Exception("Password or username cannot be null");
		}
		
		LoginResult result = LocalData.getInstance().getClient().remoteLoginBlocking(username, password, timeoutMillis);
		if(!result.isSuccess()) {
			throw new UserErrorException(result.getMessage());
		}
		
		LocalData.getInstance().setLocalPlayerIndex(result.getLocalPlayerIndex());
	}
}
