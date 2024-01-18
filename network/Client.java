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

import domain.GameController;
import userinterface.observer.Observer;

public class Client extends Thread implements Observer{
	public static boolean debugEnabled = true;
	private final String name;
	private final String serverHost;
	private final int serverPort;
	private final Socket connection;
	public Client(String name, String serverHost, int serverPort) throws UnknownHostException, IOException {
		this.name = name;
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		
		connection = new Socket(serverHost, serverPort);
	}

    private void sendGameState(GameController gameState) {
        try {
            OutputStream output = connection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(output);
            oos.writeObject(gameState);
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
            		GameController newGameController = (GameController) ois.readObject();
            		//do sth with gameState
                	System.out.println(this.name + " received game state");
                	GameController.updateInstance(newGameController);
				} catch (ClassNotFoundException e) {
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
		sendGameState(GameController.getInstance());
	}
}
