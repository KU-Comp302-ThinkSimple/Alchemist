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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import domain.GameController;
import domain.loginSignup.*;
import domain.player.Player;
import network.messages.GameStateUpdateMessage;
import network.messages.LoginMessage;
import network.messages.LoginResponseMessage;
import network.messages.Message;
import network.messages.SignupMessage;
import network.messages.SignupResponseMessage;
import userinterface.observer.MainObserver;
import userinterface.observer.Observable;
import userinterface.observer.Observer;

public class Client extends Thread implements Observer, Observable{
	public static boolean debugEnabled = true;
	private final String name;
	private final Socket connection;
	private SignupResponseMessage receivedSignupResponse;
	private LoginResponseMessage receivedLoginResponse;
	private final Object responseLock = new Object();
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private List<Observer> observers;
	public Client(String name, String serverHost, int serverPort) throws UnknownHostException, IOException {
		this.name = name;
		connection = new Socket(serverHost, serverPort);
		observers = new ArrayList<Observer>();
		receivedSignupResponse = null;
		receivedLoginResponse = null;
	}
	public static void main(String[] args) throws UnknownHostException, IOException, TimeoutException {
		Client client = new Client("client1", "127.0.0.1", 4000);
		client.start();
//		client.remoteSignupBlocking("a", "a", 1000);
	}

    private void sendMessage(Message message) {
        try {
        	getObjectOutputStream().reset();
            getObjectOutputStream().writeObject(message);
            getObjectOutputStream().reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void listen() {
    	try {
            
            while (true) {
            	try {
            		getObjectInputStream().reset();
            		Message receivedMessage = (Message) getObjectInputStream().readObject();
            		getObjectInputStream().reset();
            		if(receivedMessage instanceof GameStateUpdateMessage) {
            			GameController newGameController = ((GameStateUpdateMessage) receivedMessage).getNewGameController();
                    	if(debugEnabled) {
                			System.out.println(this.name + " received game state");
                			System.out.println("Incoming gamestate:");
                			for (Player player : GameController.getInstance().getActivePlayers()) {
                				System.out.println(player.getPlayerName() + ": " + player.getPlayerToken().getPlayerAction());
                			}
                    	}
                    	GameController.updateInstance(newGameController);
                    	notifyObserver();
            		}
            		else if(receivedMessage instanceof SignupResponseMessage) {
            			receivedSignupResponse = (SignupResponseMessage)receivedMessage;
            		}
            		else if(receivedMessage instanceof LoginResponseMessage) {
            			receivedLoginResponse = (LoginResponseMessage)receivedMessage;
            		}
            		synchronized (responseLock) {
        				responseLock.notify();
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
		GameStateUpdateMessage message = new GameStateUpdateMessage(GameController.getInstance());
		System.out.println("Before sending game state");
		for (Player player : message.getNewGameController().getActivePlayers()) {
			System.out.println(player.getPlayerName() + ": " + player.getPlayerToken().getPlayerAction());
		}
		sendMessage(message);
		System.out.println("After sending game state");
		for (Player player : message.getNewGameController().getActivePlayers()) {
			System.out.println(player.getPlayerName() + ": " + player.getPlayerToken().getPlayerAction());
		}
		System.out.println(GameController.getInstance().getCurrentPlayer().getPlayerToken().getPlayerAction());
	}
	
	public SignupResult remoteSignupBlocking(String username, String password, int timeoutMillis) throws TimeoutException{
		sendMessage(new SignupMessage(username, password));
		receivedSignupResponse = null;
		synchronized (responseLock) {
			try {
				responseLock.wait(timeoutMillis);
				if(receivedSignupResponse!= null) {
					return receivedSignupResponse.getResponse();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		throw new TimeoutException("Remote signup operation timed out!");
	}
	
	public LoginResult remoteLoginBlocking(String username, String password, int timeoutMillis) throws TimeoutException {
		sendMessage(new LoginMessage(username, password));
		receivedLoginResponse = null;
		synchronized (responseLock) {
			try {
				responseLock.wait(timeoutMillis);
				if(receivedLoginResponse != null) {
					return receivedLoginResponse.getResponse();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		throw new TimeoutException("Remote login operation timed out!");
	}
	
	private ObjectInputStream getObjectInputStream() throws IOException {
		if(objectInputStream == null) {
			objectInputStream = new ObjectInputStream(connection.getInputStream());
		}
		return objectInputStream;
	}
	
	private ObjectOutputStream getObjectOutputStream() throws IOException {
		if(objectOutputStream == null) {
			objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
		}
		return objectOutputStream;
	}
	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void clearObservers() {
		observers.clear();
	}
	@Override
	public void notifyObserver() {
		if(observers == null) {
			return;
		}
		for(int i = 0; i<observers.size(); i++) {
			if(i>=observers.size()) {
				continue;
			}
			System.out.println("Client notifying observer: " + i);
			observers.get(i).update();
		}
	}
	
	public void addThisToObservables() {
		System.out.println("Client observing");
		//OBSERVER RELATED
		GameController.getInstance().getBoard().getIngredientBoard().addObserver(this);
		GameController.getInstance().getBoard().getPotionBrewingBoard().addObserver(this);
		GameController.getInstance().getBoard().getPublicationBoard().addObserver(this);
		for (Player player: GameController.getInstance().getActivePlayers()){
			player.getInventory().addObserver(this);
		}
		GameController.getInstance().addObserver(this);
	}
}
