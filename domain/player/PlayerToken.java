package domain.player;

import java.util.ArrayList;

import domain.boards.Board;
import domain.cards.ArtifactCard;
import domain.cards.IngredientCard;
import domain.cards.PublicationCard;
import domain.potion.*;
import domain.theory.Hypotheses;

public class PlayerToken {

	int gold=0;
	int reputation=0;
	PlayerInventory playerInventory;
	
	public PlayerToken() {
		
		createInventory();
	}
	
	
	
	public PlayerInventory createInventory() {
		
		return new PlayerInventory();
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
	
	public void removeAIngredientCard(IngredientCard ing) {
		playerInventory.playerIngredientCardList.remove(ing);
	}
	
	public void removeArtifactCard(ArtifactCard art) {
		playerInventory.playerArtifactCardList.remove(art);
	}
	
	public void removePublicationCard(PublicationCard pub) {
		playerInventory.playerPublicationCardList.remove(pub);
	}
}
