package domain.cards.artifactCards;

import domain.cards.artifactCards.behaviors.ArtifactCardBehavior;

public class WisdomIdol extends ArtifactCard<Void>{
    public WisdomIdol(ArtifactCardBehavior effect, String name) {
        super(effect, name);
    }
}
