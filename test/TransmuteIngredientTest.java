package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import domain.GameController;
import domain.boards.BoardController;
import domain.cards.IngredientCard;
import domain.player.Player;
import exception.UserErrorException;

//1 min
public class TransmuteIngredientTest {
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

	@BeforeAll
	public void beforeAll(){
		GameController.getGameInventory().createAtom();
		GameController.getGameInventory().createMolecule();
		GameController.getGameInventory().createIngredientCard();
		
		IngredientCard ownedCard=GameController.getGameInventory().getIngrCards().get(0);	
		Player currentPlayer = new Player(1, "aa", "bb");
        currentPlayer.getInventory().addAIngredientCard(ownedCard);
        currentPlayer.getPlayerToken().setPlayerAction(3);
        currentPlayer.getPlayerToken().setGold(5);
        BoardController.transmuteIngredient(ownedCard.getName());
	}
	@Test
    public void testTransmuteOwnedIngredient() throws UserErrorException {
       

        // Assert
        assertEquals(4, currentPlayer.getPlayerToken().getPlayerAction());
        assertEquals(6, currentPlayer.getPlayerToken().getGold());
        assertFalse(currentPlayer.getInventory().getPlayerIngredientCardList().contains(ownedCard));
    }
	@Test
	public void test2(){
		assertFalse(currentPlayer.getInventory().getPlayerIngredientCardList().contains(ownedCard));
	}


}
