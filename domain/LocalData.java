package domain;

import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.player.Player;
import network.Client;
import network.Server;
import userinterface.MainGameWindowOffline;

public class LocalData {

	private int localPlayerIndex;
	private JFrame mainGameWindow;
	private Client client;
	private Server server;
	private static LocalData instance;
	private LocalData() {
	}
	
	public static LocalData getInstance() {
		if(instance == null) {
			instance = new LocalData();
		}
		return instance;
	}

	public Player getLocalPlayer() {
		return GameController.getInstance().getActivePlayers().get(localPlayerIndex);
	}

	public void setLocalPlayerIndex(int localPlayerIndex) {
		this.localPlayerIndex = localPlayerIndex;
	}
	
	public JFrame getMainGameWindow() {
		return mainGameWindow;
	}


	public void setMainGameWindow(JFrame mainGameWindow) {
		this.mainGameWindow = mainGameWindow;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	
}
