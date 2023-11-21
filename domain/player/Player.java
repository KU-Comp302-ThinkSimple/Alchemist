package domain.player;


import java.util.ArrayList;

import domain.cards.IngredientCard;
import domain.potion.*;


public class Player {
	
	int playerId;
	String playerName;
	int playerHealth = 3;
	PlayerToken playerToken;
	
	
	
	public Player(int playerId, String playerName) {
	
		this.playerId = playerId;
		this.playerName = playerName;
		this.playerHealth = playerHealth;
		
	}
	
	public void setPlayerToken(PlayerToken playerToken) {
		this.playerToken=playerToken;
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
