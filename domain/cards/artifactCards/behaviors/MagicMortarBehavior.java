package domain.cards.artifactCards.behaviors;

import domain.GameController;
import domain.cards.IngredientCard;

public class MagicMortarBehavior implements ArtifactCardBehavior<Void>{
    @Override
    public Void use(Object... parameters) {
        GameController.getInstance().getCurrentPlayer().getInventory().addAIngredientCard((IngredientCard) parameters[0]);
        return null;
    }


}
