package domain;

import java.util.Random;

public class InitializeGameHelper {

	InitializeGameHelper(){
		// This class calls a various functions to initialize the game within its constructor.
		initializeIngredientCards();
		GameController.getInstance().getBoard().getIngredientBoard().initializeIngredientDeck();
		shuffleCards();
		distributeInitialCards();
		distributeGold();
		chooseStartingPlayer();
		

	}

// This func initializes the ingr cards.
	public void initializeIngredientCards() {
		GameController.getInstance().getGameInventory().createAtom();
		GameController.getInstance().getGameInventory().createMolecule();
		GameController.getInstance().getGameInventory().createIngredientCard();
		GameController.getInstance().getGameInventory().createArtifactCard();
		//TODO Call the the function that fills the deck with these cards(See the corresponding comment in IngredientBoard class)
	}


	// This func chooses the starting player.
	public void chooseStartingPlayer() {
		Random rand = new Random();
		int num = rand.nextInt(GameController.getInstance().getActivePlayers().size());
		GameController.getInstance().setCurrentPlayer(GameController.getInstance().getActivePlayers().get(num));;
	}

	// This func shuffles cards
	public void shuffleCards(){

		// board.getIngredientBoard().shuffleIngredientCards();
		// board.getPIngredientBoard().shuffleArtifactCards();

		//TODO implement these, also need to find a way to represent artifact cards (maybe within the IngredientBoard?)
	}

	// This func distributes the ingr cards.
	public void distributeInitialCards() {

		for(int i=0;i<GameController.getInstance().getActivePlayers().size();i++) {
			for(int j=0;j<2;j++) {
				GameController.getInstance().getActivePlayers().get(i).getInventory().addAIngredientCard(GameController.getInstance().getBoard().getIngredientBoard().popIngredient());

			}
		}
	}

	// This func distributes the gold.
	public void distributeGold() {

		for(int i=0;i<GameController.getInstance().getActivePlayers().size();i++) {
			GameController.getInstance().getActivePlayers().get(i).getPlayerToken().addGold(10);
		}
	}







}
