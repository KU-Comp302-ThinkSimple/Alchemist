package domain.cards.artifactCards;

import domain.cards.artifactCards.behaviors.ArtifactCardBehavior;

public class MagicMortar extends ArtifactCard<Void>{
    public MagicMortar(ArtifactCardBehavior effect, String name) {
        super(effect, name);
    }
}
