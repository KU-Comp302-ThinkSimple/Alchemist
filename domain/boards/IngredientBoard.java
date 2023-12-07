package domain.boards;

import java.util.ArrayList;
import java.util.Collections;

import domain.GameController;
import domain.cards.IngredientCard;
import domain.player.*;
import exception.*;

public class IngredientBoard extends Board{
	private ArrayList<IngredientCard> ingredientList;

	public IngredientBoard() {

		ingredientList = new ArrayList<IngredientCard>();

	}

	// Function creates deck for ingredient cards
	public void initializeIngredientList(){
		ArrayList<IngredientCard> ingrs = new ArrayList<IngredientCard>(GameController.getGameInventory().getIngredientCards());
		while (ingredientList.size() < 3) {
			Collections.shuffle(ingrs);
			ingredientList.add(ingrs.get(0));
		}
	}

	//Returns top element in deck and add new element for ensuring that deck is endless
	public void forageForIngredient() throws UserErrorException, RuntimeException {

		IngredientCard ingr = popIngredient();
		GameController.getCurrentPlayer().getInventory().getPlayerIngredientCardList().add(ingr);
		
		//Reduce Player Actions
		GameController.getCurrentPlayer().getPlayerToken().reducePlayerAction();
	}


	public IngredientCard popIngredient() {
		IngredientCard ingr = ingredientList.remove(0);
		initializeIngredientList();
		return ingr;

	}


	//Sells 1 ingredient card for 1 gold
	public void transmuteIngredient(IngredientCard ingredientCard) throws UserErrorException, RuntimeException {
		Player player = GameController.getCurrentPlayer();
		PlayerInventory inv = player.getInventory();
		PlayerToken token = player.getPlayerToken();

		if(!inv.getPlayerIngredientCardList().contains(ingredientCard)) {
			throw new UserErrorException("TThe user does not own this ingredient!");
		}

		inv.removeIngredientCard(ingredientCard);
		token.addGold(1);
		
		//Reduce Player Action
		token.reducePlayerAction();
	}


	public ArrayList<IngredientCard> getIngredientList(){
		return ingredientList;
	}

}
