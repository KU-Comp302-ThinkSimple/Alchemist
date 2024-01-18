package domain;

import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.player.Player;
import userinterface.MainGameWindowOffline;

public class LocalData {

	private Player localPlayer;
	private JFrame mainGameWindow;
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
		return localPlayer;
	}

	public void setLocalPlayer(Player localPlayer) {
		this.localPlayer = localPlayer;
	}
	
	public MainGameWindowOffline getMainGameWindow() {
		return mainGameWindow;
	}


	public void setMainGameWindow(MainGameWindowOffline mainGameWindow) {
		this.mainGameWindow = mainGameWindow;
	}
}
