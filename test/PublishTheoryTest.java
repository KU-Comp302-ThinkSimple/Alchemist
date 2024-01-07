package test;

import domain.GameController;
import domain.boards.PublicationBoard;
import domain.cards.IngredientCard;
import domain.player.Player;
import domain.potion.Molecule;
import exception.UserErrorException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.*;
import static test.TestGameInitializer.initializeTestGame;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PublishTheoryTest {
    private GameController gameController;

    @BeforeAll
    public void setUpBeforeAll() {
        initializeTestGame();
        gameController = GameController.getInstance();

    }

    /*
    Black Box
    Tests for  updates of related effects.
    */
    @Test
    @DisplayName("Publish Theory updates neccessary places.")
    public void testUpdates() {
        gameController.setCurrentRound(2);
        Player currentPlayer = gameController.getCurrentPlayer();
        IngredientCard ingredientCard = gameController.getGameInventory().getIngredientCards().get(0);
        Molecule hypothesizedMolecule = ingredientCard.getMolecule();
        PublicationBoard publicationBoard = new PublicationBoard();
        try {
            publicationBoard.publishTheory(currentPlayer, ingredientCard, hypothesizedMolecule);
            assertEquals("Player's reputation points should increase by 1.", 1, currentPlayer.getPlayerToken().getReputation());
            assertEquals("Player's gold should decrease by 1.", 2, currentPlayer.getPlayerToken().getGold());
            assertTrue("Player's inventory should contain the published hypothesis.", currentPlayer.getInventory().getPlayerHypothesesList().contains(hypothesizedMolecule));
            assertTrue("Global hypotheses list should contain the published hypothesis.", publicationBoard.getHypotheses().contains(hypothesizedMolecule));
        } catch (UserErrorException | RuntimeException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

    }

    /*
    Black Box test for round number
     */

    @Test
    @DisplayName("Test player gold.")
    public void testRoundNumberExceptions() {
        gameController = GameController.getInstance();
        gameController.setCurrentRound(1);
        Player currentPlayer = gameController.getCurrentPlayer();
        IngredientCard ingredientCard = gameController.getGameInventory().getIngredientCards().get(0);
        Molecule hypothesizedMolecule = ingredientCard.getMolecule();
        PublicationBoard publicationBoard = new PublicationBoard();
        try {
            publicationBoard.publishTheory(currentPlayer, ingredientCard, hypothesizedMolecule);

        } catch (UserErrorException | RuntimeException e) {
            fail("Unexpected exception: " + e.getMessage());
        }


    }
}