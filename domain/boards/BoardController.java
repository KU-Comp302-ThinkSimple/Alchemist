package domain.boards;

import java.util.Random;

import domain.GameController;
import domain.cards.IngredientCard;
import domain.cards.artifactCards.ArtifactCard;
import domain.potion.Molecule;
import domain.theory.Hypotheses;
import exception.UserErrorException;

public class BoardController {

	static GameBoard board = GameController.getInstance().getBoard();
	static PotionBrewingBoard potBoard =board.getPotionBrewingBoard();
	static PublicationBoard pubBoard = board.getPublicationBoard();
	static IngredientBoard ingrBoard = board.getIngredientBoard();

	BoardController(){}

	
	public static void transmuteIngredient(String ingredient) throws UserErrorException, RuntimeException {
		//Finding proper Ingredient with given name
		
		IngredientCard ingr = null;
		for (IngredientCard ingr_1 : GameController.getInstance().getGameInventory().getIngredientCards()){
    		if (ingr_1.getName().equals(ingredient)) {
    			ingr = ingr_1;}
    	}

		ingrBoard.transmuteIngredient(ingr);
//		GameController.getMainGameWindow().updateMainGameWindow();
	}
	public static void buyArtifactCard() {
		
		Random rand = new Random();
		int randomIndex = rand.nextInt(GameController.getInstance().getGameInventory().getArtCards().size());
		ArtifactCard card= GameController.getInstance().getGameInventory().getArtCards().get(randomIndex);
		GameController.getInstance().getCurrentPlayer().getInventory().addArtifactCard(card);
		GameController.getInstance().getCurrentPlayer().getPlayerToken().reducePlayerAction();

	}

	public static void forageForIngredient() throws UserErrorException, RuntimeException{
		ingrBoard.forageForIngredient();
//		GameController.getMainGameWindow().updateMainGameWindow();

	}

	public static void publishTheory(IngredientCard ingredient, Molecule hypothesizedMolecule) throws UserErrorException, RuntimeException{
		pubBoard.publishTheory(GameController.getInstance().getCurrentPlayer(), ingredient, hypothesizedMolecule);
//		GameController.getMainGameWindow().updateMainGameWindow();

	}

	public static String debunkTheory(Hypotheses hypothesis, int atomId) throws UserErrorException, RuntimeException {
		String ret = pubBoard.debunkTheory(GameController.getInstance().getCurrentPlayer(), hypothesis, atomId);
//		GameController.getMainGameWindow().updateMainGameWindow();
		return ret;
	}

	
	public static String brewPotion(String ingredient1, String ingredient2, boolean onStu) throws UserErrorException, RuntimeException{
		//Find ingredients with given names  	
		IngredientCard ingr1 = null;
		IngredientCard ingr2 = null;
		for (IngredientCard ingredient : GameController.getInstance().getGameInventory().getIngredientCards()){
    		if (ingredient.getName().equals(ingredient1)) {
    			ingr1 = ingredient;}
    		if (ingredient.getName().equals(ingredient2)) {
        		ingr2 = ingredient;}
    	}
		String ret = potBoard.makeExperiment(ingr1, ingr2, onStu);
//		GameController.getMainGameWindow().updateMainGameWindow();
		return ret;
  }


}

