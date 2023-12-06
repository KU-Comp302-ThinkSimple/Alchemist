package domain.player;

import java.util.ArrayList;

import domain.cards.IngredientCard;
import domain.potion.*;

public class Player {

	final String playerName;
	final String playerPassword;
	PlayerToken playerToken;

	public Player(String playerName, String password) {

		this.playerName = playerName;
		this.playerPassword = password;
		this.playerToken=new PlayerToken();
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


	public String getPlayerName() {
		return playerName;
	}

	public String getPlayerPassword() {
		return playerPassword;
	}

	public PlayerToken getPlayerToken() {
		return playerToken;
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

}
