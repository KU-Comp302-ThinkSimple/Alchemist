package domain.cards.artifactCards;

import domain.GameController;
import domain.cards.IngredientCard;
import domain.cards.artifactCards.behaviors.ArtifactCardBehavior;

import java.util.ArrayList;

public class ElixirOfInsight extends ArtifactCard<ArrayList<IngredientCard>> {


	public ElixirOfInsight(ArtifactCardBehavior effect, String name) {
		super(effect, name);

	}

	public static ArrayList<IngredientCard> shuffle(ArrayList<IngredientCard> cards, int[] order) {
		ArrayList<IngredientCard> newCards = new ArrayList<IngredientCard>();
		newCards.add(cards.get(order[0] - 1));
		newCards.add(cards.get(order[1] - 1));
		newCards.add(cards.get(order[2] - 1));
		return newCards;
	}


	public void changeCards(int[] order) {
		ArrayList<IngredientCard> topThreeCards = this.useCard();

		ArrayList<IngredientCard> newTopThreeCards = shuffle(topThreeCards, order);

		for (int i = 0; i < 3; i++) {
			GameController.getInstance().getBoard().getIngredientBoard().getIngredientDeck().getCards().set(i, newTopThreeCards.get(i));
		}

	}


}