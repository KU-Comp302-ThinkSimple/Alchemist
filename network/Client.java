package network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import domain.GameController;
import network.messages.GameStateUpdateMessage;
import network.messages.LoginMessage;
import network.messages.LoginResponseMessage;
import network.messages.Message;
import network.messages.SignupMessage;
import network.messages.SignupResponseMessage;
import userinterface.observer.Observer;

public class Client extends Thread implements Observer{
	public static boolean debugEnabled = true;
	private final String name;
	private final String serverHost;
	private final int serverPort;
	private final Socket connection;
	private SignupResponseMessage receivedSignupResponse;
	private LoginResponseMessage receivedLoginResponse;
	public Client(String name, String serverHost, int serverPort) throws UnknownHostException, IOException {
		this.name = name;
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		
		connection = new Socket(serverHost, serverPort);
		receivedSignupResponse = null;
		receivedLoginResponse = null;
	}

    private void sendMessage(Message message) {
        try {
            OutputStream output = connection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(output);
            oos.writeObject(message);
            if(debugEnabled) {
                System.out.println(this.name + " sent game state");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void listen() {
    	try {
            InputStream input = connection.getInputStream();	
            ObjectInputStream ois = new ObjectInputStream(input);
            
            while (true) {
            	try {
            		Message receivedMessage = (Message) ois.readObject();
            		if(receivedMessage instanceof GameStateUpdateMessage) {
            			GameController newGameController = ((GameStateUpdateMessage) receivedMessage).getNewGameController();
                    	if(debugEnabled) {
                			System.out.println(this.name + " received game state");
                    	}
                    	GameController.updateInstance(newGameController);
            		}
            		else if(receivedMessage instanceof SignupResponseMessage) {
            			receivedSignupResponse = (SignupResponseMessage)receivedMessage;
            		}
            		else if (receivedMessage instanceof LoginResponseMessage) {
            			receivedLoginResponse = (LoginResponseMessage)receivedLoginResponse;
            		}
            		
				} catch (ClassNotFoundException e) {
					System.out.println("Client received invalid packet");
					if(debugEnabled) {
						e.printStackTrace();
					}
				}
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Remove the client when it disconnects
            System.out.println("Client " + this.name + " disconnected.");
        }
    }

	@Override
	public void run() {
		System.out.println("Starting client " + this.name);
        listen();
	}

	@Override
	public void update() {
		sendMessage(new GameStateUpdateMessage(GameController.getInstance()));
	}
	
	public String remoteSignupBlocking(String username, String password, int timeoutMillis) throws TimeoutException{
		sendMessage(new SignupMessage(username, password));
		receivedSignupResponse = null;
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - startTime < timeoutMillis) {
			if(receivedSignupResponse != null) {
				return receivedSignupResponse.getResponse();
			}
		}
		throw new TimeoutException("Remote signup operation timed out!");
	}
	
	public String remoteLoginBlocking(String username, String password, int timeoutMillis) throws TimeoutException {
		sendMessage(new LoginMessage(username, password));
		receivedLoginResponse = null;
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - startTime < timeoutMillis) {
			if(receivedLoginResponse != null) {
				return receivedLoginResponse.getResponse();
			}
		}
		throw new TimeoutException("Remote login operation timed out!");
	}
}
