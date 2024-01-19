package network.messages;

import domain.GameController;

public class GameStateUpdateMessage extends Message {
	private final GameController newGameController;
	public GameStateUpdateMessage(GameController gameController) {
		this.newGameController = gameController;
	}
	public GameController getNewGameController() {
		return newGameController;
	}
}
