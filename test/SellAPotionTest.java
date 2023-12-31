package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.GameController;
import domain.boards.PotionBrewingBoard;
import domain.cards.IngredientCard;
import domain.player.Player;
import domain.potion.Molecule;
import domain.potion.Potion;
import exception.UserErrorException;

class SellAPotionTest {

	//Sell a potion makes use of functions in Potion class
	//These functions include getting the proper potion from the recipe etc.
	//I will assume that these functions already work well since I just want to test Sell a Potion's work

	/*
	ArrayList<Potion> negativePotions;
	ArrayList<Potion> positivePotions;
	ArrayList<Potion> neutralPotions;
	 */

	Player player;


	@BeforeEach
	void testInitialize() {
		//Initialize game with 2 players
		TestGameInitializer.initializeTestGame();

		//set player
		player = GameController.getInstance().getCurrentPlayer();

		//Add actions to the current player (so we'll not change turns and keep testing one player)
		player.getPlayerToken().setPlayerAction(50);

		//Make sure it's round 2+
		GameController.getInstance().changeRounds(); // now we're in round2

	}

	@AfterEach
	void removeActivePlayers() {
		GameController.getInstance().getActivePlayers().clear();
	}


	@Test
	void userDoesNotHaveIngredients() {

		//Current player has no ingredient cards now.
		player.getInventory().getPlayerIngredientCardList().clear();

		//try to sell a potion
		//it should throw UserErrorException bc user doesn't have ingredients
		//also check the message of exception to make sure exception is thrown accordingly
		Throwable exception = assertThrows(UserErrorException.class, () -> {

			ArrayList<IngredientCard> ingrs = GameController.getInstance().getGameInventory().getIngrCards();
			IngredientCard ingr1 = ingrs.get(0);
			IngredientCard ingr2 = ingrs.get(1);
			GameController.getInstance().getBoard().getPotionBrewingBoard().sellPotion(ingr1, ingr2, 0);;
		});

		Assertions.assertTrue((exception.getMessage().equals("User does not have first ingredient") || exception.getMessage().equals("User does not have second ingredient")));
	}

	@Test
	void duplicateIngredientsCantBeMixed() {

		//Add 2 of same ingredient cards to player's ingrdcard list
		ArrayList<IngredientCard> ingrs = GameController.getInstance().getGameInventory().getIngrCards();
		IngredientCard ingr = ingrs.get(0);
		player.getInventory().getPlayerIngredientCardList().add(ingr);
		player.getInventory().getPlayerIngredientCardList().add(ingr);
		//Current player has 2 of same ingredient cards now.

		//try to sell a potion with same 2 ingredients
		//it should throw UserErrorException bc of duplicate ingredients
		//also check the message of exception to make sure exception is thrown accordingly
		Throwable exception = assertThrows(UserErrorException.class, () -> {
			GameController.getInstance().getBoard().getPotionBrewingBoard().sellPotion(ingr, ingr, 0);;
		});
		Assertions.assertEquals("For creating potion 2 DIFFERENT ingredients are needed!", exception.getMessage());

	}

	@Test
	void positiveGuaranteeFulfilled() {

	}

	@Test
	void nonNegativeGuaranteeFulfilled() {

	}

	@Test
	void noGuarantee() {

	}



}
