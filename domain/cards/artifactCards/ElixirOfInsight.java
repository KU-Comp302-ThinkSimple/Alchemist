package domain.cards.artifactCards;

import java.util.ArrayList;

import domain.GameController;
import domain.cards.IngredientCard;

public class ElixirOfInsight extends ArtifactCard{

	
	public ElixirOfInsight(ArtifactEffectBehavior effect, String name) {
		super(effect, name);
		
	}

	public static ArrayList<IngredientCard> shuffle(ArrayList<IngredientCard> cards, int[] order){
		ArrayList<IngredientCard> newCards = new ArrayList<IngredientCard>();
		newCards.add(cards.get(order[0]-1));
		newCards.add(cards.get(order[1]-1));
		newCards.add(cards.get(order[2]-1));
		return newCards;
	}

	
	public ArrayList<IngredientCard> changeCards(int [] order) {
		ArrayList<IngredientCard> topThreeCards =this.useCard();

		ArrayList<IngredientCard> newTopThreeCards = shuffle(topThreeCards, order);

		for (int i = 0; i<3; i++){
			GameController.getInstance().getBoard().getIngredientBoard().getIngredientList().set(i, newTopThreeCards.get(i));
		}

		return newTopThreeCards;

	}

}
