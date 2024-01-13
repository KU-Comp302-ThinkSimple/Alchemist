package domain.cards.artifactCards;

import domain.GameController;
import domain.cards.IngredientCard;
import domain.cards.artifactCards.behaviors.ArtifactCardBehavior;

import java.util.ArrayList;

public class ElixirOfInsight extends ArtifactCard<ArrayList<IngredientCard>> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6303850557348729601L;

	public ElixirOfInsight(ArtifactCardBehavior effect, String name) {
		super(effect, name);

	}



public ArrayList<IngredientCard> changeCards(int [] order) {
	ArrayList<IngredientCard> topThreeCards =this.useCard();
	
	GameController.getInstance().getBoard().getIngredientBoard().getIngredientDeck().reorder(order);
	notifyObserver();
	return GameController.getInstance().getBoard().getIngredientBoard().getIngredientDeck().getCards();

}


}