package test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.GameController;
import domain.boards.BoardController;
import domain.boards.IngredientBoard;
import domain.boards.IngredientCardDeckArrayList;
import domain.boards.RandomCardDeck;
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
	
	//Glass-box testing
	//Scenario: A user who has no actions tries to forage for an ingredient (this shouldn't be possible in normal operation)
	//Expected result: The function throws an error
	@Test
	public void noActionsLeft() {
		Player currentPlayer = GameController.getInstance().getCurrentPlayer();
		currentPlayer.getPlayerToken().setPlayerAction(0);
		Exception exception = assertThrows(UserErrorException.class, () -> BoardController.forageForIngredient());
		assertEquals(exception.getMessage(), "The user has no more actions left!");
	}
	
	//Black-box testing
	//Scenario: A user forages for an ingredient
	//Expected result: The user gains one card and loses one action
	@Test
	public void singleSuccess() {
		Player currentPlayer = GameController.getInstance().getCurrentPlayer();
//		currentPlayer.getPlayerToken().setPlayerAction(3);
		int initialSize = currentPlayer.getInventory().getPlayerIngredientCardList().size();
		assertDoesNotThrow(() -> BoardController.forageForIngredient());
		assertEquals(initialSize+1, currentPlayer.getInventory().getPlayerIngredientCardList().size());
		assertEquals(2, currentPlayer.getPlayerToken().getPlayerAction());
	}
	
	//Black-box testing
	//Scenario: A user forages for 3 ingredients, exhausting their actions.
	//Expected result: The user gains three cards
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
	
	//Black-box testing
	//Scenario: A user forages for an ingredient, exhausting their actions. Then another forage is issued
	//in which case the user must have switched to another user (since the first user's actions were exhausted)
	//Expected result: The initial user gains three cards. The user changes
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
	
	//Black-box testing
	//Scenario: A user forages for 3 ingredients, exhausting their actions. Then another forage is issued
	//in which case the user must have switched to another user (since the first user's actions were exhausted)
	//Expected result: The initial user gains three cards. The user changes
	@Test
	public void allSuccessNoActionsLeft() {
		Player firstPlayer = GameController.getInstance().getCurrentPlayer();
		assertEquals(3, firstPlayer.getPlayerToken().getPlayerAction());
		int firstPlayerSize = firstPlayer.getInventory().getPlayerIngredientCardList().size();
		assertDoesNotThrow(() -> BoardController.forageForIngredient());
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