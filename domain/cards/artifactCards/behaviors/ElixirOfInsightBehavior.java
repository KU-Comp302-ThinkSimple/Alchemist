package domain.cards.artifactCards.behaviors;

import domain.GameController;
import domain.cards.IngredientCard;
import domain.cards.artifactCards.behaviors.ArtifactCardBehavior;

import java.util.ArrayList;

public class ElixirOfInsightBehavior implements ArtifactCardBehavior<ArrayList<IngredientCard>> {

    @Override
    public ArrayList<IngredientCard> use() {

        IngredientCard card1 = GameController.getBoard().getIngredientBoard().getIngredientList().get(0);
        IngredientCard card2 = GameController.getBoard().getIngredientBoard().getIngredientList().get(1);
        IngredientCard card3 = GameController.getBoard().getIngredientBoard().getIngredientList().get(2);
        ArrayList<IngredientCard> topThreeCards = new ArrayList<IngredientCard>();
        topThreeCards.add(card1);
        topThreeCards.add(card2);
        topThreeCards.add(card3);


        return topThreeCards;

    }
}
