package test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.GameController;
import domain.boards.BoardController;
import domain.boards.IngredientBoard;
import domain.player.*;
import exception.UserErrorException;

public class TestForageForIngredient {

	public TestForageForIngredient() {
		// TODO Auto-generated constructor stub
	}
	
	@BeforeEach
	public void beforeEach() {
		TestGameInitializer.initializeTestGame();
	}
	
	@Test
	public void noActionsLeft() {
		Player currentPlayer = GameController.getInstance().getCurrentPlayer();
		currentPlayer.getPlayerToken().setPlayerAction(0);
		Exception exception = assertThrows(UserErrorException.class, () -> BoardController.forageForIngredient());
		assertEquals(exception.getMessage(), "The user has no more actions left!");
	}
	
	@Test
	public void singleSuccess() {
		Player currentPlayer = GameController.getInstance().getCurrentPlayer();
		currentPlayer.getPlayerToken().setPlayerAction(3);
		int initialSize = currentPlayer.getInventory().getPlayerIngredientCardList().size();
		assertDoesNotThrow(() -> BoardController.forageForIngredient());
		assertEquals(initialSize+1, currentPlayer.getInventory().getPlayerIngredientCardList().size());
	}
	
	@Test
	public void multipleSuccess() {
		Player currentPlayer = GameController.getInstance().getCurrentPlayer();
		currentPlayer.getPlayerToken().setPlayerAction(3);
		int initialSize = currentPlayer.getInventory().getPlayerIngredientCardList().size();
		assertDoesNotThrow(() -> BoardController.forageForIngredient());
		assertDoesNotThrow(() -> BoardController.forageForIngredient());
		assertDoesNotThrow(() -> BoardController.forageForIngredient());
		assertEquals(initialSize+3, currentPlayer.getInventory().getPlayerIngredientCardList().size());
	}
	
	@Test
	public void singleSuccessNoActionsLeft() {
		Player firstPlayer = GameController.getInstance().getCurrentPlayer();
		firstPlayer.getPlayerToken().setPlayerAction(1);
		int firstPlayerSize = firstPlayer.getInventory().getPlayerIngredientCardList().size();
		assertDoesNotThrow(() -> BoardController.forageForIngredient());
		//since we had no more actions left, other user must have been switched to
		Player secondPlayer = GameController.getInstance().getCurrentPlayer();
		int secondPlayerSize = secondPlayer.getInventory().getPlayerIngredientCardList().size();
		assertDoesNotThrow(() -> BoardController.forageForIngredient());
		assertNotEquals(GameController.getInstance().getCurrentPlayer(), firstPlayer);
		assertEquals(GameController.getInstance().getCurrentPlayer(), secondPlayer);
		assertNotEquals(secondPlayer, firstPlayer);

		assertEquals(firstPlayerSize+1, firstPlayer.getInventory().getPlayerIngredientCardList().size());
		assertEquals(secondPlayerSize+1, secondPlayer.getInventory().getPlayerIngredientCardList().size());
	}
	
	@Test
	public void allSuccessNoActionsLeft() {
		Player firstPlayer = GameController.getInstance().getCurrentPlayer();
		assertEquals(2, firstPlayer.getPlayerToken().getPlayerAction());
		int firstPlayerSize = firstPlayer.getInventory().getPlayerIngredientCardList().size();
		assertDoesNotThrow(() -> BoardController.forageForIngredient());
		assertDoesNotThrow(() -> BoardController.forageForIngredient());//exhaust actions

		//since we had no more actions left, other user must have been switched to
		Player secondPlayer = GameController.getInstance().getCurrentPlayer();
		int secondPlayerSize = secondPlayer.getInventory().getPlayerIngredientCardList().size();
		assertDoesNotThrow(() -> BoardController.forageForIngredient());
		assertNotEquals(GameController.getInstance().getCurrentPlayer(), firstPlayer);
		assertEquals(GameController.getInstance().getCurrentPlayer(), secondPlayer);
		assertNotEquals(secondPlayer, firstPlayer);

		assertEquals(firstPlayerSize+3, firstPlayer.getInventory().getPlayerIngredientCardList().size());
		assertEquals(secondPlayerSize+1, secondPlayer.getInventory().getPlayerIngredientCardList().size());
	}
}