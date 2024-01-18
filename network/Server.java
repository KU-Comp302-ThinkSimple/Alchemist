package network;

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
    private int port;
    private final ArrayList<Socket> clients;
    private final int clientBufferSize;
    private static final boolean createTestClients = false;

    /**
     * A server class that relays any message it receives into other connections
     * @param port Port to connect to
     * @param clientBufferSize the size of the client buffers (RESTRICTS MAX MESSAGE SIZE!)
     * @throws IOException if it cannot create server port
     */
    public Server(int port, int clientBufferSize) throws IOException {
    	this.clients = new ArrayList<Socket>();
		this.clientBufferSize = clientBufferSize;
        serverSocket = new ServerSocket(port);
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
    	int port = findAvailablePort();
        Server server = new Server(port, 1024);
        System.out.println("Available port: " + server.getPort());
        // Start the server thread
        server.start();
        
        if(createTestClients) {
            //create three clients to test the server
            TestClient client1 = new TestClient("client1", "127.0.0.1", port);
            TestClient client2 = new TestClient("client2", "127.0.0.1", port);
            TestClient client3 = new TestClient("client3", "127.0.0.1", port);
            
            client1.start();
            TimeUnit.SECONDS.sleep(2);
            client2.start();
            TimeUnit.SECONDS.sleep(2);
            client3.start();
        }
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
            	//handle the client operations (relaying) in a new thread
            	Thread clientThread = new Thread(()->handleClient(newClientSocket));
            	clientThread.start();
            	System.out.println("New connection");
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception according to your needs
            }
        }
    }
    
    private void handleClient(Socket clientSocket) {
    	try {
            InputStream input = clientSocket.getInputStream();	

            byte[] buffer = new byte[clientBufferSize];
            int bytesRead;
            
            //read all incoming messages into buffer, then send them out
            while ((bytesRead = input.read(buffer)) != -1) {
                // Relay the message to all other clients
                relayBinaryMessage(clientSocket, buffer, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Remove the client when it eventually disconnects
            clients.remove(clientSocket);
            System.out.println("Client disconnected.");
        }
    }
    
    private void relayBinaryMessage(Socket sender, byte[] message, int length) {
    	//relay each message to all other clients
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
