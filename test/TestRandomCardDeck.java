package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.GameController;
import domain.boards.RandomCardDeck;
import domain.cards.IngredientCard;

public class TestRandomCardDeck {

	public TestRandomCardDeck() {
		// TODO Auto-generated constructor stub
	}
	
	@BeforeEach
	public void beforeEach() {
		TestGameInitializer.initializeTestGame();
	}
	
	/**
	 * Uses reflections to access the protected field "repr" of a RandomCardDeck class
	 * WARNING: This method is for testing purposes ONLY! Don't use if you aren't exactly sure what you are doing.
	 * @param deck the RandomCardDeck object from which to extract the repr field
	 * @return the repr of the deck
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public Collection getReprOfRandomCardDeck(RandomCardDeck deck) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field reprField = RandomCardDeck.class.getDeclaredField("repr"); //Do some reflection magic to access the "repr" field of the RandomCardDeck class
		reprField.setAccessible(true); //Make it accessible (otherwise the following line will always throw "IllegalAccessException")
		return (Collection) reprField.get(deck); //get and return the actual object
	}
	
	//Black-box testing
	//Scenario: Pop one ingredient from the deck
	//Expected result: The representation should remain functional
	@Test
	public void getOneIngredient() {
		RandomCardDeck<IngredientCard> deck = GameController.getInstance().getBoard().getIngredientBoard().getIngredientDeck();
		
		deck.popCard();
		
		assertEquals(true, deck.repOk());
	}
	//Glass-box testing
	//Scenario: Break the repr. by clearing the deck illegaly
	//Expected result: The repOk should fail.
	@Test
	public void breakReprByClear() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		RandomCardDeck<IngredientCard> deck = GameController.getInstance().getBoard().getIngredientBoard().getIngredientDeck();
		
		Collection repr = getReprOfRandomCardDeck(deck);
		repr.clear();
		
		assertEquals(false, deck.repOk());
	}
}
