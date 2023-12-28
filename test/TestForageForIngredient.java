package test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.GameController;
import domain.boards.BoardController;
import domain.boards.IngredientBoard;
import domain.player.*;

public class TestForageForIngredient {

	public TestForageForIngredient() {
		// TODO Auto-generated constructor stub
	}
	
	@BeforeEach
	public static void beforeEach() {
		TestGameInitializer.initializeTestGame();
	}
	
	@Test
	public static void noActionsLeft() {
		Player currentPlayer = GameController.getInstance().getCurrentPlayer();
		currentPlayer.getPlayerToken().setPlayerAction(0);
		assertThrows(null, BoardController.forageForIngredient);
	}

}
