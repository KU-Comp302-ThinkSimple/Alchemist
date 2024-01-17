package domain.player;

import domain.cards.IngredientCard;
import domain.cards.PublicationCard;
import domain.cards.artifactCards.ArtifactCard;
import domain.potion.Potion;
import domain.potion.Recipe;
import domain.theory.Hypotheses;
import userinterface.observer.Observable;
import userinterface.observer.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerInventory implements Serializable, Observable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7542601474564386240L;
	final ArrayList<Recipe> playerRecipeList;
	final ArrayList<Potion> playerPotionList;
	final ArrayList<Hypotheses> playerHypothesesList;
	final ArrayList<IngredientCard> playerIngredientCardList;
	final ArrayList<ArtifactCard> playerArtifactCardList;
	final ArrayList<PublicationCard> playerPublicationCardList;
	private transient List<Observer> observers = new ArrayList<>();

	public PlayerInventory() {
		
		playerRecipeList = new ArrayList<Recipe>();
		playerPotionList =  new ArrayList<Potion>();
		playerHypothesesList =  new ArrayList<Hypotheses>();
		playerIngredientCardList =  new ArrayList<IngredientCard>();
		playerArtifactCardList =  new ArrayList<ArtifactCard>();
		playerPublicationCardList = new ArrayList<PublicationCard>();
	}
	
	public ArrayList<Recipe> getPlayerRecipeList() {
		return playerRecipeList;
	}


	public ArrayList<Potion> getPlayerPotionList() {
		return playerPotionList;
	}


	public ArrayList<Hypotheses> getPlayerHypothesesList() {
		return playerHypothesesList;
	}


	public ArrayList<IngredientCard> getPlayerIngredientCardList() {
		return playerIngredientCardList;
	}


	public ArrayList<ArtifactCard> getPlayerArtifactCardList() {
		return playerArtifactCardList;
	}


	public ArrayList<PublicationCard> getPlayerPublicationCardList() {
		return playerPublicationCardList;
	}

	public void addPotion(Potion potion) {
		this.playerPotionList.add(potion);
		notifyObserver();
	}
	
	public void addRecipe(Recipe recipe) {
		this.playerRecipeList.add(recipe);
		notifyObserver();
	}
	
	public void addHypoteses(Hypotheses hyp) {
		this.playerHypothesesList.add(hyp);
		notifyObserver();
	}
	
	public void addAIngredientCard(IngredientCard ing) {
		this.playerIngredientCardList.add(ing);
		notifyObserver();
	}
	
	public void addArtifactCard(ArtifactCard art) {
		this.playerArtifactCardList.add(art);
		notifyObserver();
	}
	
	public void addPublicationCard(PublicationCard pub) {
		this.playerPublicationCardList.add(pub);
		notifyObserver();
	}
	
	public void removePotion(Potion potion) {
		this.playerPotionList.remove(potion);
		notifyObserver();
	}
	
	public void removeRecipe(Recipe recipe) {
		this.playerRecipeList.remove(recipe);
		notifyObserver();
	}
	
	public void removeHypoteses(Hypotheses hyp) {
		this.playerHypothesesList.remove(hyp);
		notifyObserver();
	}
	
	public void removeIngredientCard(IngredientCard ing) {
		this.playerIngredientCardList.remove(ing);
		notifyObserver();
	}
	
	public void removeArtifactCard(ArtifactCard art) {
		this.playerArtifactCardList.remove(art);
		notifyObserver();
	}
	
	public void removePublicationCard(PublicationCard pub) {
		this.playerPublicationCardList.remove(pub);
		notifyObserver();
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
	
}
