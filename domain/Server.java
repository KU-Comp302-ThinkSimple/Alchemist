package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private int availablePort;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        availablePort = port;
        //serverSocket.setSoTimeout(10000);
    }

    public int getAvailablePort() {
        return availablePort;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(findAvailablePort());
        System.out.println("Available port: " + server.getAvailablePort());
        // Start the server thread
        server.start();
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
            	System.out.println("Waiting for players on port " + serverSocket.getLocalPort() + "...");
				Socket player1 = serverSocket.accept();
	            System.out.println("Player 1 connected.");
	            Socket player2 = serverSocket.accept();
	            System.out.println("Player 2 connected.");

				
	            BufferedReader fromPlayer1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
	            PrintWriter toPlayer1 = new PrintWriter(player1.getOutputStream(), true);
	            BufferedReader fromPlayer2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
	            PrintWriter toPlayer2 = new PrintWriter(player2.getOutputStream(), true);

	            toPlayer1.println("You are Player 1.");
	            toPlayer2.println("You are Player 2.");
                // Logic for handling client connections goes here
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception according to your needs
            }
        }
    }
}
