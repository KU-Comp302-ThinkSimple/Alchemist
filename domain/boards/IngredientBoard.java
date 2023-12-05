package domain.boards;

import java.util.ArrayList;
import java.util.Collections;

import domain.GameController;
import domain.cards.IngredientCard;

public class IngredientBoard extends Board{
	private ArrayList<IngredientCard> ingredientList;

    public IngredientBoard(int size, String name) {
        super(size, name);
        ingredientList = new ArrayList<IngredientCard>();
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
   public IngredientCard forageForIngredient() {
	   IngredientCard ingr = ingredientList.get(0);
	   initializeIngredientList();
	   return ingr;
   }
    
   public ArrayList<IngredientCard> getIngredientList(){
	   return ingredientList;
   }

}
