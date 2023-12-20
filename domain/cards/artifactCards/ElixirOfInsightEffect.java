package domain.cards.artifactCards;

import domain.GameController;
import domain.cards.IngredientCard;

import java.util.ArrayList;

public class ElixirOfInsightEffect implements ArtifactEffectBehavior{

	
	public ArrayList<IngredientCard> use() {
		
		IngredientCard card1 = GameController.getInstance().getBoard().getIngredientBoard().getIngredientList().get(0);
		IngredientCard card2 = GameController.getInstance().getBoard().getIngredientBoard().getIngredientList().get(1);
		IngredientCard card3 = GameController.getInstance().getBoard().getIngredientBoard().getIngredientList().get(2);
		ArrayList<IngredientCard> topThreeCards = new ArrayList<IngredientCard>();
		topThreeCards.add(card1);
		topThreeCards.add(card2);
		topThreeCards.add(card3);

		
		return topThreeCards;

	}



}
