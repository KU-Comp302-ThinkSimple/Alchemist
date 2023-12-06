package domain.boards;

import domain.GameController;
import domain.player.*;
import domain.cards.*;
import domain.potion.*;
import exception.*;

public class PotionBrewingBoard extends Board{
    public PotionBrewingBoard() {
 
    }
    
    
    public String makeExperiment(IngredientCard ingredient1, IngredientCard ingredient2, Boolean testOnStu) throws UserErrorException {
    	
    	//Get current player and needed informations
    	Player player = GameController.getCurrentPlayer(); 
    	PlayerInventory inv = player.getInventory();
    	int gold = player.getPlayerToken().getGold();
    	int health = player.getPlayerToken().getPlayerHealth();
    	
    	//Check if player has these 2 ingredients
    	if(!(inv.getPlayerIngredientCardList().contains(ingredient1))) {
    		throw new UserErrorException("User does not have these first ingredient");
    	}
    	if(!(inv.getPlayerIngredientCardList().contains(ingredient2))) {
    		throw new UserErrorException("User does not have these second ingredient");
    	}
    		
    	//Create recipe and potion with given ingredients		
		Recipe rec = new Recipe(ingredient1, ingredient2);
    	Potion pot = new Potion(rec);
    	
    	//Check if user already owns this recipe
    	if (inv.getPlayerPotionList().contains(rec)) {
    		throw new UserErrorException("The user have this recipe already!");
    	}
    	
    	
    	//Determine type of new potion
    	pot.determinePotion();
    	
    	//test potion on student or player own
    	if (testOnStu) {
    		pot.setPersonToTest("Student");
    		//check if user has sufficient gold for needed situations
        	if(gold < 1) {
        		throw new UserErrorException("The user does not have enough gold!");
        	}
    	}
    	else {
    		pot.setPersonToTest("Self");
    		//check if user has sufficient health for needed situations
    		if(health < 1) {
        		throw new UserErrorException("The user does not have enough health!");
        	}	
    	}
    	pot.testPotion();
    	
    	
    	//Remove ingredients from players ingredient list
    	inv.removeIngredientCard(ingredient1);
    	inv.removeIngredientCard(ingredient2);

    	
    	//Add potion and recipe to the players inventory
    	inv.addPotion(pot);
    	inv.addRecipe(rec);
    	
    	return pot.getPotionType();
    }
}

