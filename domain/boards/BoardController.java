package domain.boards;

import domain.GameController;
import domain.cards.IngredientCard;
import domain.potion.Molecule;
import domain.theory.Hypotheses;
import exception.UserErrorException;

public class BoardController {

	static GameBoard board = GameController.getBoard();
	static PotionBrewingBoard potBoard =board.getPotionBrewingBoard();
	static PublicationBoard pubBoard = board.getPublicationBoard();
	static IngredientBoard ingrBoard = board.getIngredientBoard();

	BoardController(){}

	
	public static void transmuteIngredient(String ingredient) throws UserErrorException, RuntimeException {
		//Finding proper Ingredient with given name
		
		IngredientCard ingr = null;
		for (IngredientCard ingr_1 : GameController.getGameInventory().getIngredientCards()){
    		if (ingr_1.getName().equals(ingredient)) {
    			ingr = ingr_1;}
    	}

		ingrBoard.transmuteIngredient(ingr);
		GameController.getCurrentPlayer().getPlayerToken().reducePlayerAction();
	}

	public static void forageForIngredient() throws UserErrorException, RuntimeException{
		ingrBoard.forageForIngredient();
		GameController.getCurrentPlayer().getPlayerToken().reducePlayerAction();
	}

	public static void publishTheory(IngredientCard ingredient, Molecule hypothesizedMolecule) throws UserErrorException, RuntimeException{
		pubBoard.publishTheory(GameController.getCurrentPlayer(), ingredient, hypothesizedMolecule);
		GameController.getCurrentPlayer().getPlayerToken().reducePlayerAction();
	}

	public static String debunkTheory(Hypotheses hypothesis, int atomId) throws UserErrorException, RuntimeException {
		return pubBoard.debunkTheory(GameController.getCurrentPlayer(), hypothesis, atomId);
	}

	
	public static String brewPotion(String ingredient1, String ingredient2, boolean onStu) throws UserErrorException, RuntimeException{
		//Find ingredients with given names  	
		IngredientCard ingr1 = null;
		IngredientCard ingr2 = null;
		for (IngredientCard ingredient : GameController.getGameInventory().getIngredientCards()){
    		if (ingredient.getName().equals(ingredient1)) {
    			ingr1 = ingredient;}
    		if (ingredient.getName().equals(ingredient2)) {
        		ingr2 = ingredient;}
    	}
		GameController.getCurrentPlayer().getPlayerToken().reducePlayerAction();
		return potBoard.makeExperiment(ingr1, ingr2, onStu);
		
  }


}

