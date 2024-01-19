package domain.player;

import userinterface.observer.Observable;
import userinterface.observer.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.GameController;

public class Player implements Serializable, Observable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7552661838843237048L;
	final int playerId;
	final String playerName;
	final String playerPassword;
	int playerHealth;
	PlayerToken playerToken;
	final int[][] deductionSelection;
	private transient List<Observer> observers = new ArrayList<>();

	//Constructor of player object
	public Player(int playerId, String playerName, String password) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.playerPassword = password;
		this.playerHealth = 3;
		this.playerToken=new PlayerToken();
		deductionSelection = new int[8][8];
	}

	public void setPlayerToken(PlayerToken playerToken) {
		this.playerToken = playerToken;
	}

	public String getPassword() {
		return playerPassword;
	}

	public PlayerInventory getInventory() {
		return this.playerToken.playerInventory;
	}

	public void addHealth() {
		this.playerToken.addHealth();
	}

	public void reduceHealth() {
		this.playerToken.reduceHealth();
	}

	public int getPlayerId() {
		return playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getPlayerPassword() {
		return playerPassword;
	}

	public PlayerToken getPlayerToken() {
		return playerToken;
	}

	public int[][] getDeductionSelection(){
		return deductionSelection;
	}


	public boolean equals(Player p2) {
		if (p2.getPlayerName().equals(this.playerName)) return true;
		else return false;
	}

	@Override
	public void addObserver(Observer observer) {
		if(observers == null) {
			observers = new ArrayList<>();
		}
		observers.add(observer);
	}

	@Override
	public void notifyObserver() {
		if(observers == null) {
			return;
		}
		for (Observer observer : observers){
			observer.update();
		}
	}

	@Override
	public String toString() {
		String str = "Player Name: " + this.playerName;
		str += "\nPlayer Health: " + this.playerHealth;
		str += "\n" + this.getPlayerToken();
		str += "\nPoints: " + this.calculatePoints();
		str += "\nHypotheses published by this player:\n";
		str += GameController.getInstance().getBoard().getPublicationBoard().playerHypoThesisCorrectnessList(this);
		return str;
	}

	public int calculatePoints() {
		int golds = this.playerToken.gold;
		int artifactCards = this.playerToken.playerInventory.getPlayerArtifactCardList().size();
		golds += artifactCards/2;
		int score = this.playerToken.reputation*10 + golds;
		return score;
	}
}
