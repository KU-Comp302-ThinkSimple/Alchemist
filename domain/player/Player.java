package domain.player;


import java.util.ArrayList;

import domain.cards.IngredientCard;
import domain.potion.*;


public class Player {
	
	int playerId;
	String playerName;
	String playerPassword;
	
	
	PlayerToken playerToken;
	
	
	
	
	public Player(int playerId, String playerPassword, String playerName) {
	
		this.playerId = playerId;
		this.playerName = playerName;
		this.playerPassword=playerPassword;
		
		
	}
	
	public void setPlayerToken(PlayerToken playerToken) {
		this.playerToken=playerToken;
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

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerPassword() {
		return playerPassword;
	}

	public void setPlayerPassword(String playerPassword) {
		this.playerPassword = playerPassword;
	}

	public PlayerToken getPlayerToken() {
		return playerToken;
	}
	
	
//
//	public void transmuteIngredient(IngredientCard ingredient) {
//		
//	}
//	
//	public void publishTheory(IngredientCard ingredient) {
//		
//		
//		
//	}
//	 
//	public void makeExperiment(IngredientCard ingredient1, IngredientCard ingredient2) {
//		
//	}
	
	
//	
//	public void sellAPotion(Potion potion, Boolean guarantee, Player customer) {
//		
//		
//	}
	
	
	
}
