package test;

import java.util.ArrayList;

import domain.GameController;
import domain.GameInventory;
import domain.cards.IngredientCard;
import domain.potion.Atom;
import domain.potion.Molecule;
import exception.UserErrorException;
import userinterface.LoginSignupController;

public class TestMakeExperiment {

	GameController controller;
	GameInventory inventory;
	ArrayList<IngredientCard> ingredientCards;

	public static void main(String[] args) {
		testExperiment();
		System.out.print(controller.getCurrentPlayer());
	}

	public void testExperiment() {

		//Initialize game first
		controller = GameController.getInstance();

		LoginSignupController.getInstance().signup("TestUser1", "testuser");
		LoginSignupController.getInstance().login("TestUser1", "testuser");

		LoginSignupController.getInstance().signup("TestUser2", "testuser");
		LoginSignupController.getInstance().login("TestUser2", "testuser");

		controller.initializeGame();

		//Then we can get inventory (after the game is initialized)
		inventory = controller.getGameInventory();

		//makeExperiment(IngredientCard ingredient1, IngredientCard ingredient2, Boolean testOnStu)
		//throws UserErrorException, RuntimeException

		//For testing purposes, added 100 actions to the current player
		controller.getCurrentPlayer().getPlayerToken().setPlayerAction(100);

		/*
		 * Things to test:
		 **** For all tests
		 **
		 **** Drink Potion Tests
		 ** Health Potion (Red +) -> playerHealth should increase by 1.
		 ** Speed Potion (Green +) -> playerAction should increase by 1.
		 ** Wisdom Potion (Blue +) -> playerReputation should increase by 1.
		 *                           (Since player's gonna use 1 action to test the potion, the action amount before and after...
		 * 							 ...makeExperiment should remain the same.)
		 * 
		 ** Poison Potion (Red -) -> playerHealth should reduce by 1.
		 ** Slow Potion (Green -) -> playerHealth should reduce by 1. playerAction should reduce by 1 (if any left).
		 ** Insanity Potion (Blue -) -> playerHealth should reduce by 1. playerReputation should reduce by 1.
		 *
		 ** Neutral Potion -> No effect.
		 */

		//TODO In Potion class's testPotion() method, line 61
		//if (!GameController.getInstance().getCurrentPlayer().getInventory().getPlayerArtifactCardList().contains(GameController.getInstance().getGameInventory().getArtCards().get(1)))
		//is a very fragile code. It should check all items in ArrayList and check if (item instanceof Vaccine)
		//when the order of the cards change, the function won't function
		//TODO playerHealth is in both Player and PlayerToken classes. Choose one (I think Player class is a better answer)



	}
}
