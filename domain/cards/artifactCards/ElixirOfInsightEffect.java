package domain.cards.artifactCards;

import domain.GameController;
import domain.cards.IngredientCard;

import java.util.ArrayList;

public class ElixirOfInsightEffect implements ArtifactEffectBehavior{

	
	public static ArrayList<IngredientCard> see() {
		IngredientCard card1 = GameController.getBoard().getIngredientBoard().getIngredientList().get(0);
		IngredientCard card2 = GameController.getBoard().getIngredientBoard().getIngredientList().get(1);
		IngredientCard card3 = GameController.getBoard().getIngredientBoard().getIngredientList().get(2);
		ArrayList<IngredientCard> topThreeCards = new ArrayList<IngredientCard>();
		topThreeCards.add(card1);
		topThreeCards.add(card2);
		topThreeCards.add(card3);

		return topThreeCards;

	}

	public static ArrayList<IngredientCard> shuffle(ArrayList<IngredientCard> cards, int[] order){
		ArrayList<IngredientCard> newCards = new ArrayList<IngredientCard>();
		newCards.add(cards.get(order[0]));
		newCards.add(cards.get(order[1]));
		newCards.add(cards.get(order[2]));
		return newCards;
	}

	@Override
	public ArrayList<IngredientCard> use() {
		ArrayList<IngredientCard> topThreeCards = see();
		//TODO: get the order from the player with Interfaces
		int[] order = {2,0,1};

		ArrayList<IngredientCard> newTopThreeCards = shuffle(topThreeCards, order);

		for (int i = 0; i<3; i++){
			GameController.getBoard().getIngredientBoard().getIngredientList().set(i, newTopThreeCards.get(i));
		}

		return newTopThreeCards;

	}


}
