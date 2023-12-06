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
        initializeIngredientList();
    }
    
   // Function creates deck for ingredient cards 
   public void initializeIngredientList(){
	   ArrayList<IngredientCard> ingrs = GameController.getGameInventory().getIngredientCards();
	   while (ingredientList.size() < 3) {
		   Collections.shuffle(ingrs);		   
		   ingredientList.add(ingrs.get(0));
	   }
   }
    
   //Returns top element in deck and add new element for ensuring that deck is endless
   public void forageForIngredient() throws UserErrorException {
	  
	   if (!GameController.getCurrentPlayer().getPlayerToken().hasActionsLeft()) {
	   		throw new UserErrorException("The player has no more actions left!");
	   	   }
	   
	   IngredientCard ingr = ingredientList.get(0);
	   GameController.getCurrentPlayer().getInventory().getPlayerIngredientCardList().add(ingr);
	   initializeIngredientList();
	  
   }
   
   
   public IngredientCard popIngredient() {
	   IngredientCard ingr = ingredientList.get(0);
	   initializeIngredientList();
	   return ingr;
	  
   }
   
   
   //Sells 1 ingredient card for 1 gold
   public void transmuteIngredient(IngredientCard ingredientCard) throws UserErrorException { 
	   Player player = GameController.getCurrentPlayer();
	   PlayerInventory inv = player.getInventory();
	   PlayerToken token = player.getPlayerToken();
	   
	   if(!token.hasActionsLeft()) {
   		throw new UserErrorException("The player has no more actions left!");
   	   }
	   if(!inv.getPlayerIngredientCardList().contains(ingredientCard)) {
	   	throw new UserErrorException("TThe user does not own this ingredient!");
	   }
	   
	   inv.removeIngredientCard(ingredientCard);
	   token.addGold(1);
   }
   
    
   public ArrayList<IngredientCard> getIngredientList(){
	   return ingredientList;
   }

}
