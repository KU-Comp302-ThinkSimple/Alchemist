package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
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
import domain.potion.Recipe;
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
		player.getPlayerToken().setPlayerAction(100);

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
		//Molecules 0 and 3 create a Speed Potion (Green +)
		//Mol 0 = sR- sG+ bB- (Molecule of ingr(0))
		//Mol 3 = sR- bG+ sB+ (Molecule of ingr(1))

		ArrayList<IngredientCard> ingrs = GameController.getInstance().getGameInventory().getIngrCards();
		IngredientCard ingr0 = ingrs.get(0);
		IngredientCard ingr3 = ingrs.get(3);

		//Double check corresponding molecules create Green + pot
		Assertions.assertEquals(new Recipe(ingr0, ingr3).checkGreenMatch(), 1);

		//Add cards to player's inventory
		player.getInventory().getPlayerIngredientCardList().add(ingr0);
		player.getInventory().getPlayerIngredientCardList().add(ingr3);

		//Player's gold and card stats before selling a potion
		int moneyBeforeSell = player.getPlayerToken().getGold();
		System.out.println(moneyBeforeSell); //TODO
		ArrayList<IngredientCard> cardsBeforeSell = player.getPlayerToken().getPlayerInventory().getPlayerIngredientCardList();

		//Sell potion with ingr0, ingr3, 2 (positive guaranteed)
		try {
			GameController.getInstance().getBoard().getPotionBrewingBoard().sellPotion(ingr0, ingr3, 2);
		}
		catch (UserErrorException e) {
			Assert.fail("sellPotion method threw UserErrorException with message: \n" + e.getMessage());
		}


		//Check ingr card inventory before and after
		ArrayList<IngredientCard> cardsAfterSell = player.getPlayerToken().getPlayerInventory().getPlayerIngredientCardList();
		cardsBeforeSell.remove(ingr0);
		cardsBeforeSell.remove(ingr3);
		Assertions.assertEquals(cardsBeforeSell, cardsAfterSell);

		//Check money before and after
		int moneyAfterSell = player.getPlayerToken().getGold();
		System.out.println(moneyAfterSell);
		Assertions.assertEquals(moneyBeforeSell + 3, moneyAfterSell);

		//check recipes list?
	}

	@Test
	void positiveGuaranteeNotFulfilled() {

	}

	@Test
	void noGuarantee() {

	}



}
