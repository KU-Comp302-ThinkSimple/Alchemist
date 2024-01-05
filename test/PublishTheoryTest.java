package test;

import domain.GameController;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

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
    Tests for round.
    */
    @Test
    @DisplayName("Test round number.")
    public void testRoundNumber(){

    }

    /*
    Black Box
    Tests for player gold.
     */

    @Test
    @DisplayName("Test player gold.")
    public void testPlayerGold(){
    }


}