package domain.cards.artifactCards;

import java.util.ArrayList;

import domain.GameController;
import domain.cards.IngredientCard;

public class ElixirOfInsightEffect implements ArtifactEffectBehavior{

	
	public ArrayList<IngredientCard> use() {
		
		return GameController.getBoard().getIngredientBoard().getIngredientList();
	}

}
