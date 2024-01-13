package domain.cards.artifactCards.behaviors;

import domain.GameController;
import domain.cards.IngredientCard;

import java.io.Serializable;
import java.util.ArrayList;

public class ElixirOfInsightBehavior implements ArtifactCardBehavior<ArrayList<IngredientCard>>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4707363177434578900L;

	@Override
	public ArrayList<IngredientCard> use(Object... parameters) {

		IngredientCard card1 = GameController.getInstance().getBoard().getIngredientBoard().getIngredientDeck().getCards().get(0);
		IngredientCard card2 = GameController.getInstance().getBoard().getIngredientBoard().getIngredientDeck().getCards().get(1);
		IngredientCard card3 = GameController.getInstance().getBoard().getIngredientBoard().getIngredientDeck().getCards().get(2);
		ArrayList<IngredientCard> topThreeCards = new ArrayList<IngredientCard>();
		topThreeCards.add(card1);
		topThreeCards.add(card2);
		topThreeCards.add(card3);


		return topThreeCards;

	}
}