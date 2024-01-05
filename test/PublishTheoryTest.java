package test;

import domain.GameController;
import domain.GameInventory;
import domain.boards.PublicationBoard;
import domain.theory.Hypotheses;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import userinterface.LoginSignupController;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PublishTheoryTest {
    private final GameController controller = GameController.getInstance();
    private final LoginSignupController loginSignupController = LoginSignupController.getInstance();
    private final PublicationBoard publicationBoard = new PublicationBoard();
    private final GameInventory gameInventory = GameInventory.getInstance();

    private ArrayList<Hypotheses> testHypotheses = new ArrayList<>();

    // JUnit 5 setup before all tests
    @BeforeAll
    public void setUpBeforeAll() {

        controller.initializeGame();
    }

    /*
    Black Box
    Tests for round.
    */
    @Test
    @DisplayName("Test round number.")
    public void testRoundNumber(){
        int currentRound = controller.getCurrentRound();
        assertFalse("Player should be in round 2 or 3 to publish a theory.", currentRound!=1);
    }

    /*
    Black Box
    Tests for player gold.
     */

    @Test
    @DisplayName("Test player gold.")
    public void testPlayerGold(){
        assertFalse("Player should have at least 1 gold to publish a theory",controller.getCurrentPlayer().getPlayerToken().getGold() < 1);
    }


}