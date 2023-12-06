package domain.boards;

import domain.GameController;
import domain.cards.IngredientCard;
import domain.potion.Molecule;
import domain.theory.Hypotheses;
import exception.UserErrorException;

public class BoardController {
	
	static GameBoard board = GameController.getBoard();
	static PotionBrewingBoard potBoard =(PotionBrewingBoard) board.getPotionBrewingBoard();
	static PublicationBoard pubBoard = (PublicationBoard) board.getPublicationBoard();
	static IngredientBoard ingrBoard = (IngredientBoard) board.getIngredientBoard();
	
	BoardController(){}
	
	public static void transmuteIngredient(IngredientCard ingr) throws UserErrorException {
		ingrBoard.transmuteIngredient(ingr);
	}
	
	public static IngredientCard forageForIngredient() throws UserErrorException{
		return ingrBoard.forageForIngredient();
	}
	
	public static void publishTheory(IngredientCard ingredient, Molecule hypothesizedMolecule) throws UserErrorException{
		pubBoard.publishTheory(GameController.getCurrentPlayer(), ingredient, hypothesizedMolecule);
	}
	
	public static void debunkTheory(Hypotheses hypothesis) throws UserErrorException {
		pubBoard.debunkTheory(GameController.getCurrentPlayer(), hypothesis);
	}
	
	public static String brewPotion(String ing1, String ing2, boolean onStu) throws UserErrorException{
		return potBoard.makeExperiment(ing1, ing2, onStu);
	}

}

