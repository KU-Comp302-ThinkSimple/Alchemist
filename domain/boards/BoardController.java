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

	
	public static void transmuteIngredient(String ingredient) throws UserErrorException {
		//Finding proper Ingredient with given name
		
		IngredientCard ingr = null;
		for (IngredientCard ingr_1 : GameController.getGameInventory().getIngredientCards()){
    		if (ingr_1.getName() == ingredient) {
    			ingr = ingr_1;}
    	}

		ingrBoard.transmuteIngredient(ingr);
	}

	public static void forageForIngredient() throws UserErrorException{
		ingrBoard.forageForIngredient();
	}

	public static void publishTheory(IngredientCard ingredient, Molecule hypothesizedMolecule) throws UserErrorException{
		pubBoard.publishTheory(GameController.getCurrentPlayer(), ingredient, hypothesizedMolecule);
	}

	public static void debunkTheory(Hypotheses hypothesis) throws UserErrorException {
		pubBoard.debunkTheory(GameController.getCurrentPlayer(), hypothesis);
	}

	
	public static String brewPotion(String ingredient1, String ingredient2, boolean onStu) throws UserErrorException{
		//Find ingredients with given names  	
    IngredientCard ingr1 = null;
		IngredientCard ingr2 = null;
		for (IngredientCard ingredient : GameController.getGameInventory().getIngredientCards()){
    		if (ingredient.getName() == ingredient1) {
    			ingr1 = ingredient;}
    		if (ingredient.getName() == ingredient2) {
        		ingr2 = ingredient;}
    	}
		return potBoard.makeExperiment(ingr1, ingr2, onStu);
  }


}

