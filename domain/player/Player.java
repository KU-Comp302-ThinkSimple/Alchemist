package domain.player;

import java.util.ArrayList;
import java.util.Arrays;

import domain.cards.IngredientCard;
import domain.potion.*;

public class Player {

	final int playerId;
	final String playerName;
	final String playerPassword;
	int playerHealth;
	PlayerToken playerToken;
	final int[][] deductionSelection;

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

	//
	// public void transmuteIngredient(IngredientCard ingredient) {
	//
	// }
	//
	// public void publishTheory(IngredientCard ingredient) {
	//
	//
	//
	// }
	//
	// public void makeExperiment(IngredientCard ingredient1, IngredientCard
	// ingredient2) {
	//
	// }

	//
	// public void sellAPotion(Potion potion, Boolean guarantee, Player customer) {
	//
	//
	// }

	public boolean equals(Player p2) {
		if (p2.getPlayerName().equals(this.playerName)) return true;
		else return false;
	}

}
