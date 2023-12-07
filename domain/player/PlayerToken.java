package domain.player;

import java.util.ArrayList;

import domain.GameController;
import domain.boards.Board;
import domain.cards.ArtifactCard;
import domain.cards.IngredientCard;
import domain.cards.PublicationCard;
import domain.potion.*;
import domain.theory.Hypotheses;
import java.lang.*;
public class PlayerToken {

	int gold=0;
	int reputation=0;
	int playerHealth = 3;
	int playerAction=3;
	String playerAvatar; // the directory of the playeravatar is kept in this as a string.
	PlayerInventory playerInventory;
	
	
	public PlayerToken() {
		
		this.playerInventory= new PlayerInventory();
	}
	
	
	public void addGold(int gold) {
		this.gold=this.gold+gold;
		
	}
	public void subtractGold(int gold) {
		this.gold=this.gold-gold;
		
	}

	public void addReputationPoint(int point) {
		this.reputation=this.reputation+point;
		
	}
	public void subtractReputationPoint(int point) {
		this.reputation=this.reputation-point;
		
	}
	

	public void addHealth() {
		if(this.playerHealth<3) {
			this.playerHealth++;
		}
	}
	
	public void reduceHealth() {
		if(this.playerHealth>0) {
			this.playerHealth--;
		}
		if(this.playerHealth==0) {
			this.playerHealth=3;
			this.gold=0;
		}
	}
	
	
	
	
	
	public void addPotion(Potion potion) {
		playerInventory.playerPotionList.add(potion);
	}
	
	public void addRecipe(Recipe recipe) {
		playerInventory.playerRecipeList.add(recipe);
	}
	
	public void addHypoteses(Hypotheses hyp) {
		playerInventory.playerHypothesesList.add(hyp);
	}
	
	public void addAIngredientCard(IngredientCard ing) {
		playerInventory.playerIngredientCardList.add(ing);
	}
	
	public void addArtifactCard(ArtifactCard art) {
		playerInventory.playerArtifactCardList.add(art);
	}
	
	public void addPublicationCard(PublicationCard pub) {
		playerInventory.playerPublicationCardList.add(pub);
	}
	

	public void removePotion(Potion potion) {
		playerInventory.playerPotionList.remove(potion);
	}
	
	public void removeRecipe(Recipe recipe) {
		playerInventory.playerRecipeList.remove(recipe);
	}
	
	public void removeHypoteses(Hypotheses hyp) {
		playerInventory.playerHypothesesList.remove(hyp);
	}
	
	public void removeIngredientCard(IngredientCard ing) {
		playerInventory.playerIngredientCardList.remove(ing);
	}
	
	public void removeArtifactCard(ArtifactCard art) {
		playerInventory.playerArtifactCardList.remove(art);
	}
	
	public void removePublicationCard(PublicationCard pub) {
		playerInventory.playerPublicationCardList.remove(pub);
	}


	public int getGold() {
		return gold;
	}


	public void setGold(int gold) {
		this.gold = gold;
	}


	public int getReputation() {
		return reputation;
	}


	public void setReputation(int reputation) {
		this.reputation = reputation;
	}


	public int getPlayerHealth() {
		return playerHealth;
	}


	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}


	public int getPlayerAction() {
		return playerAction;
	}


	public void setPlayerAction(int playerAction) {
		this.playerAction = playerAction;
	}
	public boolean hasActionsLeft() {
		return this.playerAction>0;
	}

	public String getPlayerAvatar() {
		return playerAvatar;
	}


	public void setPlayerAvatar(String playerAvatar) {
		this.playerAvatar = playerAvatar;
	}


	/**
	 * Reduces the actions a player has by 1
	 * @throws RuntimeException if the user has no more actions left
	 */
	public void reducePlayerAction() throws RuntimeException{
		if(this.playerAction<=0) {
			GameController.changeCurrentPlayer();
			
			if(GameController.shouldChangeRound()) {
				GameController.changeRounds();
			}
			
			throw new RuntimeException("Tried to reduce user actions when the user had no actions left");
			
		}
		this.playerAction -= 1;
		
		//GameController.changeCurrentPlayer();
	
	
		
		
	}
	
	public PlayerInventory getPlayerInventory() {
		return playerInventory;
	}


	public void setPlayerInventory(PlayerInventory playerInventory) {
		this.playerInventory = playerInventory;
	}
	
	
	
	
}
