package test;
import domain.GameController;
import domain.GameInventory;
import domain.boards.PublicationBoard;

import domain.player.Player;
import domain.theory.Hypotheses;
import org.junit.jupiter.api.Test;
import userinterface.LoginSignupController;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPublishTheory {
    private final GameController controller = GameController.getInstance();
    private final LoginSignupController loginSignupController = LoginSignupController.getInstance();
    private final PublicationBoard publicationBoard = new PublicationBoard();
    private final GameInventory gameInventory = GameInventory.getInstance();

    private ArrayList<Hypotheses> testHypotheses = new ArrayList<>();

    TestPublishTheory(){
        loginSignupController.signup("publishTheoryTesterUser1", "");
        loginSignupController.login("publishTheoryTesterUser1", "");

        loginSignupController.signup("publishTheoryTesterUser2", "");
        loginSignupController.login("publishTheoryTesterUser2", "");

        controller.initializeGame();
    }

    @Test
    public void testRoundNumberNotToBe1(){
        assertFalse(controller.getCurrentRound()==1);
    }

    @Test
    public void testRoundNumberToBe2Or3(){
        assertTrue(controller.getCurrentRound()==2 || controller.getCurrentRound()==3);
    }
}
