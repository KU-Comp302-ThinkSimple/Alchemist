package domain;

import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.player.Player;
import userinterface.MainGameWindowOffline;

public class LocalData {

	private int localPlayerIndex;
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
}
