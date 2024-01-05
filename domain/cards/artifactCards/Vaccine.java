package domain.cards.artifactCards;

import domain.cards.artifactCards.behaviors.ArtifactCardBehavior;

public class Vaccine extends ArtifactCard {

    public Vaccine(ArtifactCardBehavior effect, String name) {
        super(effect, name);
    }
}