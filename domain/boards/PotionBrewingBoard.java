package domain.boards;

import domain.GameController;
import domain.player.*;
import domain.cards.*;
import domain.potion.*;
import domain.potion.Potion.potionType;
import exception.*;
import userinterface.observer.Observable;
import userinterface.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class PotionBrewingBoard extends Board implements Observable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3304320403492089717L;
	private transient List<Observer> observers = new ArrayList<>();

	public PotionBrewingBoard() {

	}


	public potionType makeExperiment(IngredientCard ingredient1, IngredientCard ingredient2, boolean onStu) throws UserErrorException, RuntimeException {
		//REQUIRES: 2 Ingredient Cards which are not null
		//			Current Player is present in GameController
		//EFFECTS:  Brew a potion with given 2 ingredient cards. And drink this potion or test on student. Reduce
		//			player`s gold if potion tested on stu is NEGATIVE. Reduce players health if drinked potion is NEGATIVE.
		//			Reduce player`s action if drinked potion is SLOW. Reduce player`s rep if drinked potion is INSANITY.
		//			Drinked HEALTH potion gives extra health. Drinked WISDOM potion gives extra rep. Drinked SPEED potion
		//			gives extra action.
		//			Throws UserError Exception if:
		//			** 2 ingredients are same
		//			** Current Player does not have both of ingredients
		//			** Current Player has this potion already
		//			** Current Player does not have enough gold or health
		//MODIFIES: Potion and Neurality type of new brewed potion
		//			Current Player`s inventory (removes 2 used Ingredient Cards)
		//			Current Player`s inverntory (adds new potion and recipe)
		//			Current Player`s token (add/reduce gold or health accordingly)		
		//			Current Player`s actions (reduce by 1)
		
		
		//Get current player and needed informations
		Player player = GameController.getInstance().getCurrentPlayer();
		PlayerInventory inv = player.getInventory();

		//Check if ingredients are same
		if (ingredient1.equals(ingredient2)) {
			throw new UserErrorException("For creating potion 2 DIFFERENT ingredients are needed!");
		}



		//Check if player has these 2 ingredients
		if(!(inv.getPlayerIngredientCardList().contains(ingredient1))) {
			throw new UserErrorException("User does not have first ingredient");
		}
		if(!(inv.getPlayerIngredientCardList().contains(ingredient2))) {
			throw new UserErrorException("User does not have second ingredient");
		}

		//Create recipe and potion with given ingredients
		Recipe rec = new Recipe(ingredient1, ingredient2);
		Potion pot = new Potion(rec);

		//Check if user already owns this recipe
		if (inv.getPlayerPotionList().contains(pot)) {
			throw new UserErrorException("The user have this recipe already!");
		}


		//Determine type of new potion
		pot.determinePotion();


		//Drink potion or test on student
		if(onStu) {
			this.testOnStu(pot);
		}
		else {
			this.drinkPoiton(pot);
		}


		//Remove ingredients from players ingredient list
		inv.removeIngredientCard(ingredient1);
		inv.removeIngredientCard(ingredient2);


		//Add potion and recipe to the players inventory
		inv.addPotion(pot);
		inv.addRecipe(rec);

		//Reduce Player Action
		player.getPlayerToken().reducePlayerAction();
		notifyObserver();
		return pot.getPotionType();
	}


	//Helper function for makeExperiment which makes needed changes on features if player drink potion

	public void drinkPoiton(Potion pot) throws UserErrorException {
		Player player = GameController.getInstance().getCurrentPlayer();
		PlayerInventory inv = player.getInventory();
		int health = player.getPlayerToken().getPlayerHealth();

		//check if user has sufficient health for needed situations
		if(health < 1) {
			throw new UserErrorException("The user does not have enough health!");
		}

		//Test potion and change needed features
		if (pot.getPotionType() == potionType.Poison) {
			if (!inv.getPlayerArtifactCardList().contains(GameController.getInstance().getGameInventory().getArtCards().get(1))){ //Vaccine card controller
				player.getPlayerToken().reduceHealth();
			}
			else {
				inv.getPlayerArtifactCardList().remove(GameController.getInstance().getGameInventory().getArtCards().get(1)); //Vaccine card remover
			}
		}
		else if (pot.getPotionType() == potionType.Health) {
			player.getPlayerToken().addHealth();
		}
		else if (pot.getPotionType() == potionType.Slow) {
			player.getPlayerToken().reduceHealth();
			player.getPlayerToken().reducePlayerAction();
		}
		else if (pot.getPotionType() == potionType.Speed) {
			player.getPlayerToken().setPlayerAction(GameController.getInstance().getCurrentPlayer().getPlayerToken().getPlayerAction()+1);;
		}
		else if (pot.getPotionType() == potionType.Insanity) {
			player.getPlayerToken().reduceHealth();
			player.getPlayerToken().subtractReputationPoint(1);
		}
		else if (pot.getPotionType() == potionType.Wisdom) {
			player.getPlayerToken().addReputationPoint(1);
		}
		notifyObserver();
	}


	// Helper function for makeExperiment if player test potion on Student

	public void testOnStu(Potion pot) throws UserErrorException{
		Player player = GameController.getInstance().getCurrentPlayer();
		PlayerInventory inv = player.getInventory();
		int gold = player.getPlayerToken().getGold();

		//check if user has sufficient gold for needed situations
		if(gold < 1) {
			throw new UserErrorException("The user does not have enough gold!");
		}

		//Test potion and change needed features
		if (pot.getPotionType() == potionType.Poison) {
			player.getPlayerToken().subtractGold(1);
		}
		else if(pot.getPotionType() == potionType.Slow) {
			player.getPlayerToken().subtractGold(1);
		}
		else if(pot.getPotionType() == potionType.Insanity) {
			player.getPlayerToken().subtractGold(1);
		}
		notifyObserver();
	}

	//brew and sell new potion by giving some guarantee
	//0 guarantee means can be anything, 1 guarantee means not negative, 2 guarantee means only positive
	public String sellPotion(IngredientCard ingr1, IngredientCard ingr2, int guarantee) throws UserErrorException {
		// EFFECTS  : This is a partial function. Takes 2 ingredient cards and a guarantee value, then brews
		//			  the potion and sells it for golds according to the potion neutrality and guarantee value.
		//			  Throws UserError Exception if:
		//			  ** Current player doesn't have at least one of the ingredients
		//		      ** It's round 1
		//			  ** Two ingredients are the same
		//
		// REQUIRES : Ingredient cards must be other than null (of GameController)
		//			  There must be a current player other than null
		// MODIFIES : Neutrality type and potion type of the new brewed potion,
		//			  Current player's inventory (removes used ingr cards),
		//			  Current player's action (-1)
		//			  Current player's player token (adds golds accordingly)
		//			  //TODO (Missing in this code) The recipe should have been added to player's recipes list in player token

		//Get current player and needed informations
		Player player = GameController.getInstance().getCurrentPlayer();
		PlayerInventory inv = player.getInventory();

		//check if we are in round 2 or 3
		if(GameController.getInstance().getCurrentRound()<2) {
			throw new UserErrorException("Potions can only be sold in rounds 2 and 3.");
		}

		//Check if ingredients are same
		if (ingr1.equals(ingr2)) {
			throw new UserErrorException("For creating potion 2 DIFFERENT ingredients are needed!");
		}

		//Check if player has these 2 ingredients
		if(!(inv.getPlayerIngredientCardList().contains(ingr1))) {
			throw new UserErrorException("User does not have first ingredient");
		}
		if(!(inv.getPlayerIngredientCardList().contains(ingr2))) {
			throw new UserErrorException("User does not have second ingredient");
		}

		//Create recipe and potion with given ingredients
		Recipe rec = new Recipe(ingr1, ingr2);
		Potion pot = new Potion(rec);


		//Determine type and neuturality value of new potion
		pot.determinePotion();
		Potion.potionNeutrality neuVal = pot.getNeutralityValue();

		//Remove ingredients from players ingredient list
		inv.removeIngredientCard(ingr1);
		inv.removeIngredientCard(ingr2);

		//Reduce Player Action
		player.getPlayerToken().reducePlayerAction();
		//Change related features by guarantee degree
		String guaranteeMsg;
		if (guarantee == 2) {
			if (neuVal == Potion.potionNeutrality.Positive) {
				player.getPlayerToken().addGold(3);
				guaranteeMsg  = "The potion was positive, guarantee met! Gained 3 gold.";
			}
			else {
				player.getPlayerToken().subtractReputationPoint(2);
				guaranteeMsg = "The potion was not positive, guarantee not met! Lost 2 reputation.";
			}
		}
		else if (guarantee == 1) {
			if (neuVal == Potion.potionNeutrality.Negative) {
				player.getPlayerToken().subtractReputationPoint(1);
				guaranteeMsg = "The potion was negative, guarantee not met! Lost 1 reputation.";
			}
			else {
				player.getPlayerToken().addGold(2);
				guaranteeMsg = "The potion was not negative, guarantee met! Gained 2 gold.";
			}
		}
		else {
			player.getPlayerToken().addGold(1);
			guaranteeMsg = "No guarantee. Gained 1 gold.";
		}

		String potionResult = "Created potion: " + pot.getPotionType().toString();
		notifyObserver();
		return potionResult + "\n" + guaranteeMsg;

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

