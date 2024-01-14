package domain.cards.artifactCards.behaviors;

import domain.GameController;

public class PrintingPressBehavior implements ArtifactCardBehavior<Void>{

    @Override
    public Void use(Object... parameters) {
        GameController.getInstance().getCurrentPlayer().getPlayerToken().addGold(1);
        return null;
    }
}
