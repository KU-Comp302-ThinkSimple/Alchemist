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
		ArrayList<IngredientCard> ingrs = new ArrayList<IngredientCard>(GameController.getInstance().getGameInventory().getIngredientCards());
		while (ingredientList.size() < 3) {
			Collections.shuffle(ingrs);
			ingredientList.add(ingrs.get(0));
		}
	}

	//Returns top element in deck and add new element for ensuring that deck is endless
	
	
	/**
	 * @throws UserErrorException if the user doesn't have any actions
	 * @throws RuntimeException if the ingredient list returned no ingredient
	 */
	public void forageForIngredient() throws UserErrorException, RuntimeException { 
		//REQUIRES: A current player is present in the game controller
		//EFFECTS: Pops an ingredient card from the list and adds it to the inventory
		//of the current player. The ingr. list automatically adds a new random 
		//ingredient card to itself (so that it is always full). The curr player
		//loses 1 action. If they have no action left the player is changed 
		//(rounds updated) automatically
		Player currentPlayer = GameController.getInstance().getCurrentPlayer();
		if(!currentPlayer.getPlayerToken().hasActionsLeft()) {
			throw new UserErrorException("The user has no more actions left!");
		}

		IngredientCard ingr = popIngredient(); //get the first element in the ingr list
		if(ingr == null) {
			throw new RuntimeException("No more ingredients in the ingredient list");
		}
		currentPlayer.getInventory().getPlayerIngredientCardList().add(ingr);
		
		//Reduce Player Actions
		GameController.getInstance().getCurrentPlayer().getPlayerToken().reducePlayerAction();
	}



	public IngredientCard popIngredient() {
		IngredientCard ingr = ingredientList.remove(0);
		initializeIngredientList();
		return ingr;

	}


	//Sells 1 ingredient card for 1 gold
	public void transmuteIngredient(IngredientCard ingredientCard) throws UserErrorException, RuntimeException {
		Player player = GameController.getInstance().getCurrentPlayer();
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
