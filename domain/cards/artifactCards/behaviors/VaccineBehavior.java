package domain.cards.artifactCards.behaviors;

import domain.GameController;

public class VaccineBehavior implements ArtifactCardBehavior<Void>{
    @Override
    public Void use(Object... parameters) {
        GameController.getInstance().getCurrentPlayer().addHealth();
        return null;
    }
}
