package domain.player;

import java.util.ArrayList;

import domain.cards.*;
import domain.potion.*;
import domain.theory.Hypotheses;

public class PlayerInventory {

	ArrayList<Recipe> playerRecipeList;
	ArrayList<Potion> playerPotionList;
	ArrayList<Hypotheses> playerHypothesesList;
	ArrayList<IngredientCard> playerIngredientCardList;
	ArrayList<ArtifactCard> playerArtifactCardList;
	ArrayList<PublicationCard> playerPublicationCardList;
	


	public PlayerInventory() {
		
		playerRecipeList = new ArrayList<Recipe>();
		playerPotionList =  new ArrayList<Potion>();
		playerHypothesesList =  new ArrayList<Hypotheses>();
		playerIngredientCardList =  new ArrayList<IngredientCard>();
		playerArtifactCardList =  new ArrayList<ArtifactCard>();
		playerPublicationCardList = new ArrayList<PublicationCard>();
	}
	

	public void addPotion(Potion potion) {
		this.playerPotionList.add(potion);
	}
	
	public void addRecipe(Recipe recipe) {
		this.playerRecipeList.add(recipe);
	}
	
	public void addHypoteses(Hypotheses hyp) {
		this.playerHypothesesList.add(hyp);
	}
	
	public void addAIngredientCard(IngredientCard ing) {
		this.playerIngredientCardList.add(ing);
	}
	
	public void addArtifactCard(ArtifactCard art) {
		this.playerArtifactCardList.add(art);
	}
	
	public void addPublicationCard(PublicationCard pub) {
		this.playerPublicationCardList.add(pub);
	}
	
	public void removePotion(Potion potion) {
		this.playerPotionList.remove(potion);
	}
	
	public void removeRecipe(Recipe recipe) {
		this.playerRecipeList.remove(recipe);
	}
	
	public void removeHypoteses(Hypotheses hyp) {
		this.playerHypothesesList.remove(hyp);
	}
	
	public void removeIngredientCard(IngredientCard ing) {
		this.playerIngredientCardList.remove(ing);
	}
	
	public void removeArtifactCard(ArtifactCard art) {
		this.playerArtifactCardList.remove(art);
	}
	
	public void removePublicationCard(PublicationCard pub) {
		this.playerPublicationCardList.remove(pub);
	}
	
}
