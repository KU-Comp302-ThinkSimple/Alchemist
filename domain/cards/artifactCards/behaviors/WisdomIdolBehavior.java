package domain.cards.artifactCards.behaviors;

import domain.GameController;

public class WisdomIdolBehavior implements ArtifactCardBehavior<Void>{

    @Override
    public Void use(Object... parameters) {
        GameController.getInstance().getCurrentPlayer().getPlayerToken().addReputationPoint(1);
        return null;
    }
}
