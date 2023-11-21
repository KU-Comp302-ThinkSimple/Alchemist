package domain.player;

import java.util.ArrayList;

import domain.boards.Board;
import domain.potion.*;

public class PlayerToken {

	int gold=0;
	int reputation=0;
	ArrayList<Recipe> playerRecipeList;
	ArrayList<Potion> playerPotionList;
	

	public void addAPotion(Potion potion) {
		this.playerPotionList.add(potion);
	}
	
	public void addARecipe(Recipe recipe) {
		this.playerRecipeList.add(recipe);
	}
	
	
	
}
