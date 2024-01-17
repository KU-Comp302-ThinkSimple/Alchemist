package network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import domain.GameController;

public class Client extends Thread{
	public static boolean debugEnabled = true;
	private final String name;
	private final String serverHost;
	private final int serverPort;
	private final int bufferSize;
	public Client(String name, String serverHost, int serverPort, int bufferSize) {
		this.name = name;
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		this.bufferSize = bufferSize;
	}

    private void sendGameState(Socket socket, GameController gameState) {
        try {
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(output);
            oos.writeObject(gameState);
            if(debugEnabled) {
                System.out.println(this.name + " sent game state");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void listen(Socket socket) {
    	try {
            InputStream input = socket.getInputStream();	
            ObjectInputStream ois = new ObjectInputStream(input);
            
            while (true) {
            	try {
            		GameController newGameController = (GameController) ois.readObject();
            		//do sth with gameState
                	System.out.println(this.name + " received game state");
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
		try (Socket serverConnection = new Socket(serverHost, serverPort)){
			System.out.println("Client " + name + " connected to host");
			
            listen(serverConnection);

		} catch (Exception e) {
			System.out.println("Client " + this.name + " failed to connect");
			System.out.println(e.getMessage());
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
