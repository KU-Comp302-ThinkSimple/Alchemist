package domain;

import java.util.Random;

public class InitializeGameHelper {

	InitializeGameHelper(){
		// This class calls a various functions to initialize the game within its constructor.
		initializeIngredientCards();
		shuffleCards();
		distributeInitialCards();
		distributeGold();
		chooseStartingPlayer();
		
		
	}

	public void initializeIngredientCards() {
		GameController.gameInventory.createAtom();
		GameController.gameInventory.createMolecule();
		GameController.gameInventory.createIngredientCard();
	
		//TODO Call the the function that fills the deck with these cards(See the corresponding comment in IngredientBoard class)
	}
	
	
	
	public void chooseStartingPlayer() {
		Random rand = new Random();
		int num = rand.nextInt(2);
		GameController.currentPlayer=GameController.activePlayers.get(num);
	}
	
	public void shuffleCards(){
		
		// board.getIngredientBoard().shuffleIngredientCards();
		// board.getPIngredientBoard().shuffleArtifactCards();
		
		//TODO implement these, also need to find a way to represent artifact cards (maybe within the IngredientBoard?)
	}	
	
	public void distributeInitialCards() {
		
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				//activePlayers[i].getInventory().addAIngredientCard(board.getIngredientBoard().popIngredient());
				
			}
		}
	}
	
	public void distributeGold() {
		
		for(int i=0;i<2;i++) {
			GameController.activePlayers.get(i).getPlayerToken().addGold(10);
			}
		}
	
	
	
	

	
	
}
