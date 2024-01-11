package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private int availablePort;
    
    private final ArrayList<Socket> clients;
    
    private final int clientBufferSize;

    public Server(int port, int clientBufferSize) throws IOException {
    	this.clients = new ArrayList<Socket>();
		this.clientBufferSize = clientBufferSize;
        serverSocket = new ServerSocket(port);
        availablePort = port;
        //serverSocket.setSoTimeout(10000);
    }

    public int getAvailablePort() {
        return availablePort;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
    	int port = findAvailablePort();
        Server server = new Server(port, 1024);
        System.out.println("Available port: " + server.getAvailablePort());
        // Start the server thread
        server.start();
        TestClient client1 = new TestClient("client1", "127.0.0.1", port);
        TestClient client2 = new TestClient("client2", "127.0.0.1", port);
        TestClient client3 = new TestClient("client3", "127.0.0.1", port);
        
        client1.start();
        TimeUnit.SECONDS.sleep(2);
        client2.start();
        TimeUnit.SECONDS.sleep(2);
        client3.start();
    }

    public static int findAvailablePort() {
        int port = 0;
        try (ServerSocket socket = new ServerSocket(0)) {
            port = socket.getLocalPort();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
        return port;
    }
   /* public int play() {
    	int result;
    }*/
    public void run() {
        while (true) {
            try {
            	System.out.println("Waiting for connections on port " + serverSocket.getLocalPort() + "...");
            	Socket newClientSocket = serverSocket.accept();
            	clients.add(newClientSocket);
            	Thread clientThread = new Thread(()->handleClient(newClientSocket));
            	clientThread.start();
            	System.out.println("New connection");
//				Socket player1 = serverSocket.accept();
//	            System.out.println("Player 1 connected.");
//	            Socket player2 = serverSocket.accept();
//	            System.out.println("Player 2 connected.");
//
//				
//	            BufferedReader frPlayer1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
//	            PrintWriter toPlayer1 = new PrintWriter(player1.getOutputStream(), true);
//	            BufferedReader frPlayer2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
//	            PrintWriter toPlayer2 = new PrintWriter(player2.getOutputStream(), true);
//
//	            toPlayer1.println("You are Player 1.");
//	            toPlayer2.println("You are Player 2.");
                // Logic for handling client connections goes here
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception according to your needs
            }
        }
    }
    
    private void handleClient(Socket clientSocket) {
    	try {
            InputStream input = clientSocket.getInputStream();	
            OutputStream output = clientSocket.getOutputStream();

            byte[] buffer = new byte[clientBufferSize];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                // Relay the message to all other clients
                relayBinaryMessage(clientSocket, buffer, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Remove the client when it disconnects
            clients.remove(clientSocket);
            System.out.println("Client disconnected.");
        }
    }
    
    private void relayBinaryMessage(Socket sender, byte[] message, int length) {
        for (Socket client : clients) {
            if (client != sender) {
                try {
                    OutputStream output = client.getOutputStream();
                    output.write(message, 0, length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
