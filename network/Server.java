package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import domain.GameController;
import domain.loginSignup.LoginSignupController;
import domain.loginSignup.*;
import network.messages.GameStateUpdateMessage;
import network.messages.LoginMessage;
import network.messages.LoginResponseMessage;
import network.messages.Message;
import network.messages.SignupMessage;
import network.messages.SignupResponseMessage;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private int port;
    private final ArrayList<ClientConnection> clients;
    
    private class ClientConnection{
		private final Socket socket;
    	private final ObjectInputStream objectInputStream;
    	private final ObjectOutputStream objectOutputStream;
    	
    	public ClientConnection(Socket socket) throws IOException {
			this.socket = socket;
			this.objectInputStream = new ObjectInputStream(socket.getInputStream());
			this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    	}
    	
    	
    	public Socket getSocket() {
			return socket;
		}

		public ObjectInputStream getObjectInputStream() {
			return objectInputStream;
		}

		public ObjectOutputStream getObjectOutputStream() {
			return objectOutputStream;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ClientConnection other = (ClientConnection) obj;

			return Objects.equals(socket, other.socket);
		}
    }
    /**
     * A server class that relays any message it receives into other connections
     * @param port Port to connect to
     * @param clientBufferSize the size of the client buffers (RESTRICTS MAX MESSAGE SIZE!)
     * @throws IOException if it cannot create server port
     */
    public Server(int port) throws IOException {
    	this.clients = new ArrayList<ClientConnection>();
        serverSocket = new ServerSocket(port);
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
    	int port = findAvailablePort();
        Server server = new Server(port);
        System.out.println("Available port: " + server.getPort());
        // Start the server thread
        server.start();
        
        if(true) {
            Client client1 = new Client("client1", "127.0.0.1", port);
            client1.start();
            
            try {
				String response = client1.remoteSignupBlocking("user_1", "pwd", 3000).getMessage();
				System.out.println(response);
				LoginResult result = client1.remoteLoginBlocking("user_1", "pwd", 3000);
				System.out.println(result.getMessage());
				System.out.println(result.getLocalPlayerIndex());
				System.out.println(GameController.getInstance().getActivePlayers().size());
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
            	ClientConnection newClientConnection = new ClientConnection(serverSocket.accept());
            	clients.add(newClientConnection);
            	//handle the client operations (relaying) in a new thread
            	Thread clientThread = new Thread(()->handleClient(newClientConnection));
            	clientThread.start();
            	System.out.println("New connection");
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception according to your needs
            }
        }
    }
    
    private void handleClient(ClientConnection clientConnection) {
    	try {
            while (true) {
            	Message receivedMessage = (Message) clientConnection.getObjectInputStream().readObject();
            	if (receivedMessage instanceof GameStateUpdateMessage) {
            		// Relay the message to all other clients
                	relayMessage(clientConnection, receivedMessage);
            	}
                else if (receivedMessage instanceof SignupMessage) {
					handleSignupMessage(clientConnection, (SignupMessage)receivedMessage);
				}
                else if (receivedMessage instanceof LoginMessage) {
                	handleLoginMessage(clientConnection, (LoginMessage)receivedMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			System.out.println("Host server received invalid message type");
			e.printStackTrace();
        }catch (ClassCastException cce) {
			System.out.println("Host server received message which wasn't of type 'Message'");
			cce.printStackTrace();
		} finally {
            // Remove the client when it eventually disconnects
            clients.remove(clientConnection);
            System.out.println("Client disconnected.");
        }
    }
    
    private void relayMessage(ClientConnection sender, Message message) {
    	//relay each message to all other clients
        for (ClientConnection client : clients) {
            if (client != sender) {
                try {
                    sendMessage(client, message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void sendMessage(ClientConnection receiver, Message message) throws IOException{
        receiver.getObjectOutputStream().writeObject(message);
    }
    
    private void handleSignupMessage(ClientConnection sender, SignupMessage message) throws IOException {
    	SignupResult result = LoginSignupController.getInstance().signup(message.getUsername(), message.getPassword());
    	sendMessage(sender, new SignupResponseMessage(result));
    }
    
    private void handleLoginMessage(ClientConnection sender, LoginMessage message) throws IOException {
    	LoginResult result = LoginSignupController.getInstance().login(message.getUsername(), message.getPassword());
    	sendMessage(sender, new LoginResponseMessage(result));
    }
}
