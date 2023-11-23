package domain.player;


import java.util.ArrayList;

import domain.cards.IngredientCard;
import domain.potion.*;


public class Player {

	
	final int playerId;
	final String playerName;
	final String password;
	int playerHealth;
	PlayerToken token;
	
	
	
	public Player(int playerId, String playerName, String password) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.password = password;
		this.playerHealth = 3;		
	}
	
	public void setPlayerToken(PlayerToken playerToken) {
		this.token=playerToken;
	}

	public int getPlayerId() {
		return playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getPassword() {
		return password;
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
