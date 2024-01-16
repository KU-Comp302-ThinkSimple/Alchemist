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

/*Test Cases:
	Black-Box Test: Transmuting Ingredient Card owned by current player
	Scenario: Providing an ingredient card owned by the player to transmute.
	Expected result: The method successfully transmutes the ingredient card to 1 gold and reduces the player's action count.

	Black-Box Test: Transmuting Non-Owned Ingredient Card
	Scenario: Providing an ingredient card not owned by the player to transmute.
	Expected result: The method throws a UserErrorException since the ingredient card is not owned by the player.

	Glass-Box Test: Inventory Modification
	Scenario: Checking the inventory modification.
	Expected result: ingredient card is removed from the player's inventory after transmutation.

	Glass-Box Test: Gold Addition
	Scenario: checking the gold addition.
	Expected result: The player's gold increased by 1 after transmutation.

	Glass-Box Test: Player Action Reduction
	Scenario: Checking the player action reduction.
	Expected result: The player's action count decreases by 1 after transmutation.*/
public class TransmuteIngredientTest {
       Player player;	
       
       
       @BeforeEach
       void TestInitialize() {
    	   TestGameInitializer.initializeTestGame();
           player = GameController.getInstance().getCurrentPlayer();
           player.getPlayerToken().setPlayerAction(100);
           GameController.getInstance().changeRounds();
       }
       @AfterEach
       void removeActivePlayers() {
           GameController.getInstance().getActivePlayers().clear();
       }

       @Test
       void transmuteOwnedIngredientCard() {
           ArrayList<IngredientCard> ingrs = GameController.getInstance().getGameInventory().getIngrCards();
           IngredientCard ownedIngredient = ingrs.get(0);

           player.getInventory().getPlayerIngredientCardList().add(ownedIngredient);

           int initGold = player.getPlayerToken().getGold();
           int initAct = player.getPlayerToken().getPlayerAction();

           assertDoesNotThrow(() -> GameController.getInstance().getTransmutationBoard().transmuteIngredient(ownedIngredient));

           int finalGold = player.getPlayerToken().getGold();
           int finalAct = player.getPlayerToken().getPlayerAction();
	   Assertions.assertEquals(initGold + 1, finalGold);
           Assertions.assertEquals(initAct - 1, finalAct);
           Assertions.assertFalse(player.getInventory().getPlayerIngredientCardList().contains(ownedIngredient));
       }

       @Test
       void transmuteNonOwnedIngredientCard() {
           ArrayList<IngredientCard> ingrs = GameController.getInstance().getGameInventory().getIngrCards();
           IngredientCard nonOwnedIngredient = ingrs.get(0);

           int initGold = player.getPlayerToken().getGold();
           int initActions = player.getPlayerToken().getPlayerAction();

           Throwable exception = assertThrows(UserErrorException.class, () ->
                   GameController.getInstance().getTransmutationBoard().transmuteIngredient(nonOwnedIngredient)
           );

           Assertions.assertEquals("Cant transmute a non owned ingredient card.", exception.getMessage());

           int finalGold = player.getPlayerToken().getGold();
           int finalAct = player.getPlayerToken().getPlayerAction();

           Assertions.assertEquals(initGold, finalGold);
           Assertions.assertEquals(initActions, finalAct);
       }

       @Test
       void checkInventoryModification() {
           ArrayList<IngredientCard> ingrs = GameController.getInstance().getGameInventory().getIngrCards();
           IngredientCard ingredientToTransmute = ingrs.get(0);

           player.getInventory().getPlayerIngredientCardList().add(ingredientToTransmute);

           assertDoesNotThrow(() -> GameController.getInstance().getTransmutationBoard().transmuteIngredient(ingredientToTransmute));

           Assertions.assertFalse(player.getInventory().getPlayerIngredientCardList().contains(ingredientToTransmute));
       }

       @Test
       void checkGoldAddition() {
           ArrayList<IngredientCard> ingredients = GameController.getInstance().getGameInventory().getIngrCards();
           IngredientCard ingredientToTransmute = ingredients.get(0);

           player.getInventory().getPlayerIngredientCardList().add(ingredientToTransmute);

           int initGold = player.getPlayerToken().getGold();

           assertDoesNotThrow(() -> GameController.getInstance().getTransmutationBoard().transmuteIngredient(ingredientToTransmute));

           int finalGold = player.getPlayerToken().getGold();

           Assertions.assertEquals(initGold + 1, finalGold);
       }

       @Test
       void checkPlayerActionReduction() {
           ArrayList<IngredientCard> ingrs = GameController.getInstance().getGameInventory().getIngrCards();
           IngredientCard ingredientToTransmute = ingrs.get(0);

           player.getInventory().getPlayerIngredientCardList().add(ingredientToTransmute);

           int initialAct = player.getPlayerToken().getPlayerAction();
           assertDoesNotThrow(() -> GameController.getInstance().getTransmutationBoard().transmuteIngredient(ingredientToTransmute));

           int finalAct = player.getPlayerToken().getPlayerAction();

           Assertions.assertEquals(initialAct - 1, finalAct);
       }
       
}
