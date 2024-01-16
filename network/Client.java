package network;

public class Client extends Thread{
	private final String serverHost;
	private final int serverPort;
	public Client(String serverHost, int serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}


}
