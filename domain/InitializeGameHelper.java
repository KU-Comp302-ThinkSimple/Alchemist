package domain;

import java.util.Random;

public class InitializeGameHelper {

	InitializeGameHelper(){
		// This class calls a various functions to initialize the game within its constructor.
		initializeIngredientCards();
		shuffleCards();
		GameController.board.getIngredientBoard().initializeIngredientList();
		distributeInitialCards();
		distributeGold();
		chooseStartingPlayer();


	}

// This func initializes the ingr cards.
	public void initializeIngredientCards() {
		GameController.gameInventory.createAtom();
		GameController.gameInventory.createMolecule();
		GameController.gameInventory.createIngredientCard();
		GameController.gameInventory.createArtifactCard();
		//TODO Call the the function that fills the deck with these cards(See the corresponding comment in IngredientBoard class)
	}


	// This func chooses the starting player.
	public void chooseStartingPlayer() {
		Random rand = new Random();
		int num = rand.nextInt(2);
		GameController.currentPlayer=GameController.activePlayers.get(num);
	}

	// This func shuffles cards
	public void shuffleCards(){

		// board.getIngredientBoard().shuffleIngredientCards();
		// board.getPIngredientBoard().shuffleArtifactCards();

		//TODO implement these, also need to find a way to represent artifact cards (maybe within the IngredientBoard?)
	}

	// This func distributes the ingr cards.
	public void distributeInitialCards() {

		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				GameController.activePlayers.get(i).getInventory().addAIngredientCard(GameController.board.getIngredientBoard().popIngredient());

			}
		}
	}

	// This func distributes the gold.
	public void distributeGold() {

		for(int i=0;i<2;i++) {
			GameController.activePlayers.get(i).getPlayerToken().addGold(10);
		}
	}







}
