package domain.cards.artifactCards;

import domain.cards.artifactCards.behaviors.ArtifactCardBehavior;

public class PrintingPress extends ArtifactCard<Void>{

    public PrintingPress(ArtifactCardBehavior effect, String name) {
        super(effect, name);
    }
}
