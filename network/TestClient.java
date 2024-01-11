package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestClient extends Thread{
	private final String name;
	private final String serverHost;
	private final int serverPort;
	public TestClient(String name, String serverHost, int serverPort) {
		this.name = name;	
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}

    private void sendMessage(Socket socket) {
        try {
            OutputStream output = socket.getOutputStream();
            String message = "Client " + this.name + " says hello!";
            byte[] messageBytes = message.getBytes();
            output.write(messageBytes);
            System.out.println(this.name + " sent message: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void listen(Socket socket) {
    	try {
            InputStream input = socket.getInputStream();	
            OutputStream output = socket.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                // Relay the message to all other clients
            	String message = new String(buffer);
            	System.out.println(this.name + " received: " + message);
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
			
			ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(() -> sendMessage(serverConnection), 0, 10, TimeUnit.SECONDS);
            
            listen(serverConnection);

		} catch (Exception e) {
			System.out.println("Client " + this.name + " failed to connect");
			System.out.println(e.getMessage());
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
