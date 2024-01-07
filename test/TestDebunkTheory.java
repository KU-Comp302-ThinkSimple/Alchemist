package test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import domain.GameController;
import domain.cards.IngredientCard;
import domain.player.Player;
import domain.potion.Molecule;
import domain.theory.Hypotheses;  

public class TestDebunkTheory {  
	  
   int a=0;
    
    //Scenario 1- Determine whether the player action is reduced- Glass Box *
    //Scenario 2 - Determine what happens to reputation when the hypoteses is proven false - Blsck Box
    //Scenario 3 -  Determine what happens to reputation when the hypoteses is proven false - Black Box
    //Scenario 4  - Determine whether it works for other rounds than 3 - Glass Box *
    //Scenario 5  - Determine whether it works if the hypotheses is not registered(published) - Glass Box *
	public TestDebunkTheory() {
		
	}

    
    @BeforeEach
	public void beforeEach() {
		TestGameInitializer.initializeTestGame();
		
	}
    
    
	@Test  
    public void isPlayerActionReduced() {
    	
    	Player currentPlayer = GameController.getInstance().getCurrentPlayer();
  
    	int initialAction=currentPlayer.getPlayerToken().getPlayerAction();
    	System.out.println(initialAction);
        IngredientCard testCard=GameController.getInstance().getGameInventory().getIngrCards().get(0);
        Molecule testMolecule=GameController.getInstance().getGameInventory().getMolecules().get(0);
        Hypotheses hypotheses = new Hypotheses(currentPlayer, testCard, testMolecule);
        GameController.getInstance().getBoard().getPublicationBoard().getHypotheses().add(hypotheses);//
        GameController.getInstance().changeRounds();
        GameController.getInstance().changeRounds();
        assertDoesNotThrow(() -> GameController.getInstance().getBoard().getPublicationBoard().debunkTheory(currentPlayer, hypotheses, 0));
       
        assertEquals(initialAction-1, currentPlayer.getPlayerToken().getPlayerAction());
	}
    
    @Test  
    public void isPlayerRepCorrectIfProvenFalse() {
    	
    	Player currentPlayer = GameController.getInstance().getCurrentPlayer();
    	int initialRep=currentPlayer.getPlayerToken().getReputation();
    	
        IngredientCard testCard=GameController.getInstance().getGameInventory().getIngrCards().get(0);
        Molecule testMolecule=GameController.getInstance().getGameInventory().getMolecules().get(1);
        Hypotheses hypotheses = new Hypotheses(currentPlayer, testCard, testMolecule);
        GameController.getInstance().getBoard().getPublicationBoard().getHypotheses().add(hypotheses);
        GameController.getInstance().changeRounds();
        GameController.getInstance().changeRounds();
        assertDoesNotThrow(() -> GameController.getInstance().getBoard().getPublicationBoard().debunkTheory(currentPlayer, hypotheses, 0));
        
        assertEquals(initialRep+2, currentPlayer.getPlayerToken().getReputation());
       
    }
    
    @Test
    public void isPlayerRepCorrectIfProvenTrue() {
    	
    	Player currentPlayer = GameController.getInstance().getCurrentPlayer();
    	int initialRep=currentPlayer.getPlayerToken().getReputation();
    	
        IngredientCard testCard=GameController.getInstance().getGameInventory().getIngrCards().get(0);
        Molecule testMolecule=GameController.getInstance().getGameInventory().getMolecules().get(0);
        Hypotheses hypotheses = new Hypotheses(currentPlayer, testCard, testMolecule);
        GameController.getInstance().getBoard().getPublicationBoard().getHypotheses().add(hypotheses);
        GameController.getInstance().changeRounds();
        GameController.getInstance().changeRounds();
        assertDoesNotThrow(() -> GameController.getInstance().getBoard().getPublicationBoard().debunkTheory(currentPlayer, hypotheses, 0));
        
        assertEquals(initialRep-1, currentPlayer.getPlayerToken().getReputation());
       
    }
    
    @Test
    public void checkWhichRoundsItWorks() {
    	//Normally debunkTheory func is supposed to work at round 3 only. This test func tests whether it works for other rounds. It should throw an exception if it works correctly
    	Player currentPlayer = GameController.getInstance().getCurrentPlayer();
    	int initialRep=currentPlayer.getPlayerToken().getReputation();
    	
        IngredientCard testCard=GameController.getInstance().getGameInventory().getIngrCards().get(0);
        Molecule testMolecule=GameController.getInstance().getGameInventory().getMolecules().get(0);
        Hypotheses hypotheses = new Hypotheses(currentPlayer, testCard, testMolecule);
        GameController.getInstance().getBoard().getPublicationBoard().getHypotheses().add(hypotheses);
        GameController.getInstance().setCurrentRound(1);
        assertDoesNotThrow(() -> GameController.getInstance().getBoard().getPublicationBoard().debunkTheory(currentPlayer, hypotheses, 0));
        
       
    }
   
    @Test
    public void checkIfWorksWithUnpublishedHypotheses() {
    	//Normally debunkTheory func is supposed to work with onlu hypoteses that are saved to hypotheses array within publicationBoard. This test func tests whether it works for unregistered hypotheses. It should throw an exception if it works correctly
    	Player currentPlayer = GameController.getInstance().getCurrentPlayer();
    	int initialRep=currentPlayer.getPlayerToken().getReputation();
    	
        IngredientCard testCard=GameController.getInstance().getGameInventory().getIngrCards().get(0);
        Molecule testMolecule=GameController.getInstance().getGameInventory().getMolecules().get(0);
        Hypotheses hypotheses = new Hypotheses(currentPlayer, testCard, testMolecule);
        GameController.getInstance().setCurrentRound(3);
        assertDoesNotThrow(() -> GameController.getInstance().getBoard().getPublicationBoard().debunkTheory(currentPlayer, hypotheses, 0));
        
    }
        //TODO implement tests for the scenario
 }

