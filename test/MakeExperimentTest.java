package test;


import static org.junit.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.GameController;
import domain.cards.IngredientCard;
import domain.player.Player;
import domain.potion.Potion;
import domain.potion.Recipe;
import exception.UserErrorException;

class MakeExperimentTest {

	Player player;
	
	@BeforeEach
	void initialize() {
		//Create test game
		TestGameInitializer.initializeTestGame();
		
		//Creating current player
		player = GameController.getInstance().getCurrentPlayer();
	}
	
	@AfterEach
	void removePlayers() {
		//remove all players
		GameController.getInstance().getActivePlayers().clear();
	}
	
	@Test
	@DisplayName("Testing card ownership") 
	void test1() {
		IngredientCard ingr1 = GameController.getInstance().getGameInventory().getIngrCards().get(1);
		IngredientCard ingr2 = GameController.getInstance().getGameInventory().getIngrCards().get(2);
		
		//For making sure that player own only one of this cards
		//clearing ingredient list and adding 1 card		
		player.getInventory().getPlayerIngredientCardList().clear();
		player.getInventory().getPlayerIngredientCardList().add(ingr1);
		
		Throwable exception = assertThrows(UserErrorException.class, () -> {
			GameController.getInstance().getBoard().getPotionBrewingBoard().makeExperiment(ingr1, ingr2, true);
		});
		
		Assertions.assertTrue(exception.getMessage().equals("User does not have second ingredient"));
		
	}
	
	
	
	@Test
	@DisplayName("Testing recipe ownership")
	void test2() {
		IngredientCard ingr1 = GameController.getInstance().getGameInventory().getIngrCards().get(1);
		IngredientCard ingr2 = GameController.getInstance().getGameInventory().getIngrCards().get(2);
		
		//Add ingredients to player`s inventory
		player.getInventory().addAIngredientCard(ingr1);
		player.getInventory().addAIngredientCard(ingr2);
		
		//Create potion and recipe with ingredients
		Recipe rec = new Recipe(ingr1, ingr2);
		Potion pot = new Potion(rec);
		
		//Add new potion and recepy to players inventory
		player.getInventory().addPotion(pot);
		player.getInventory().addRecipe(rec);
		
		
		Throwable exception = assertThrows(UserErrorException.class, () -> {
			GameController.getInstance().getBoard().getPotionBrewingBoard().makeExperiment(ingr1, ingr2, false);
		});
		
		Assertions.assertTrue(exception.getMessage().equals("The user have this recipe already!"));
		
	}
	
	@Test
	@DisplayName("Testing Health")
	void test3() {
		IngredientCard ingr1 = GameController.getInstance().getGameInventory().getIngrCards().get(1);
		IngredientCard ingr2 = GameController.getInstance().getGameInventory().getIngrCards().get(2);
		
		//Add ingredients to player`s inventory
		player.getInventory().addAIngredientCard(ingr1);
		player.getInventory().addAIngredientCard(ingr2);
		
		//setting player`s health to 0 for checking
		player.getPlayerToken().setPlayerHealth(0);
		
		Throwable exception = assertThrows(UserErrorException.class, () -> {
			GameController.getInstance().getBoard().getPotionBrewingBoard().makeExperiment(ingr1, ingr2, false);
		});
		
		Assertions.assertTrue(exception.getMessage().equals("The user does not have enough health!"));
	}
	
	@Test
	@DisplayName("Drinking Health Potion")
	void test4() {
		//Molecule 1 and Molecule 7 gives Health Potion
		IngredientCard ingr1 = GameController.getInstance().getGameInventory().getIngrCards().get(1);
		IngredientCard ingr2 = GameController.getInstance().getGameInventory().getIngrCards().get(7);
		
		//Add ingredients to player`s inventory
		player.getInventory().addAIngredientCard(ingr1);
		player.getInventory().addAIngredientCard(ingr2);
		
		//set players health to 2 for creating a chance of incrementing
		player.getPlayerToken().setPlayerHealth(2);
		
		//Get current values of players gold, health, reputation and card
		int health = player.getPlayerToken().getPlayerHealth();
		int gold = player.getPlayerToken().getGold();
		int repu = player.getPlayerToken().getReputation();
		ArrayList<IngredientCard> cards = player.getInventory().getPlayerIngredientCardList();
		
		//Make Experiment and Drink Potion
		try {
			GameController.getInstance().getBoard().getPotionBrewingBoard().makeExperiment(ingr1, ingr2, false);
		}
		catch (UserErrorException exception){
			Assert.fail("Make Experiment failed: " + exception.getMessage());
		}
		
		
		//New values of player`s gold, health, reputation and card
		int health_new = player.getPlayerToken().getPlayerHealth();
		int gold_new = player.getPlayerToken().getGold();
		int repu_new = player.getPlayerToken().getReputation();
		ArrayList<IngredientCard> cards_new = player.getInventory().getPlayerIngredientCardList();
		
		cards.remove(ingr1);
		cards.remove(ingr2);
		
		//Check for if everything changed perfectly
		Assertions.assertEquals(health + 1, health_new);
		Assertions.assertEquals(gold, gold_new);
		Assertions.assertEquals(repu, repu_new);
		Assertions.assertEquals(cards, cards_new);
	}
	
	@Test
	@DisplayName("Drinking Neuteral Potion")
	void test5() {
		//Molecule 0 and Molecule 1 gives Neuteral Potion
		IngredientCard ingr1 = GameController.getInstance().getGameInventory().getIngrCards().get(0);
		IngredientCard ingr2 = GameController.getInstance().getGameInventory().getIngrCards().get(1);
		
		//Add ingredients to player`s inventory
		player.getInventory().addAIngredientCard(ingr1);
		player.getInventory().addAIngredientCard(ingr2);
		
		//set players health to 2 for creating a chance of incrementing
		player.getPlayerToken().setPlayerHealth(2);
		
		//Get current values of players gold, health, reputation and card
		int health = player.getPlayerToken().getPlayerHealth();
		int gold = player.getPlayerToken().getGold();
		int repu = player.getPlayerToken().getReputation();
		ArrayList<IngredientCard> cards = player.getInventory().getPlayerIngredientCardList();
		
		//Make Experiment and Drink Potion
		try {
			GameController.getInstance().getBoard().getPotionBrewingBoard().makeExperiment(ingr1, ingr2, false);
		}
		catch (UserErrorException exception){
			Assert.fail("Make Experiment failed: " + exception.getMessage());
		}
		
		
		//New values of player`s gold, health, reputation and card
		int health_new = player.getPlayerToken().getPlayerHealth();
		int gold_new = player.getPlayerToken().getGold();
		int repu_new = player.getPlayerToken().getReputation();
		ArrayList<IngredientCard> cards_new = player.getInventory().getPlayerIngredientCardList();
		
		cards.remove(ingr1);
		cards.remove(ingr2);
		
		//Check for if everything changed perfectly
		Assertions.assertEquals(health, health_new);
		Assertions.assertEquals(gold, gold_new);
		Assertions.assertEquals(repu, repu_new);
		Assertions.assertEquals(cards, cards_new);
	}
	
	
	
}
