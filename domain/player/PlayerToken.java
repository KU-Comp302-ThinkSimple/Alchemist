package domain.player;

import domain.GameController;
import domain.cards.IngredientCard;
import domain.cards.PublicationCard;
import domain.cards.artifactCards.ArtifactCard;
import domain.potion.Potion;
import domain.potion.Recipe;
import domain.theory.Hypotheses;
import exception.GameOverException;
import exception.UserErrorException;
import userinterface.observer.Observable;
import userinterface.observer.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerToken implements Serializable, Observable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1622372778708598182L;
	int gold=0;
	int reputation=0;
	int playerHealth = 3;
	int playerAction=3;
	String playerAvatar; // the directory of the playeravatar is kept in this as a string.
	PlayerInventory playerInventory;
	private transient List<Observer> observers = new ArrayList<>();
	
	public PlayerToken() {

		this.playerInventory= new PlayerInventory();
	}


	public void addGold(int gold) {
		this.gold=this.gold+gold;
		notifyObserver();
	}
	public void subtractGold(int gold) {
		this.gold=this.gold-gold;
		notifyObserver();
	}

	public void addReputationPoint(int point) {
		this.reputation=this.reputation+point;
		notifyObserver();
	}
	public void subtractReputationPoint(int point) {
		this.reputation=this.reputation-point;
		notifyObserver();
	}


	public void addHealth() {
		if(this.playerHealth<3) {
			this.playerHealth++;
		}
		notifyObserver();
	}

	public void reduceHealth() {
		if(this.playerHealth>0) {
			this.playerHealth--;
		}
		if(this.playerHealth==0) {
			this.playerHealth=3;
			this.gold=0;
		}
		notifyObserver();
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
		notifyObserver();
	}
	public boolean hasActionsLeft() {
		notifyObserver();
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


			throw new RuntimeException("Tried to reduce user actions when the user had no actions left");

		}
		this.playerAction -= 1;

		//GameController.changeCurrentPlayer();
		if(this.playerAction ==0) {
			GameController.getInstance().changeCurrentPlayer();
		}


		if(GameController.getInstance().shouldChangeRound()) {
			GameController.getInstance().changeRounds();

		}
		notifyObserver();

	}

	public PlayerInventory getPlayerInventory() {
		return playerInventory;
	}


	public void setPlayerInventory(PlayerInventory playerInventory) {
		this.playerInventory = playerInventory;
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
		String str = "Gold: " + this.gold;
		str += "\nReputation: " + this.reputation;
		str += "\n" + this.playerInventory;

		return str;
	}


}
