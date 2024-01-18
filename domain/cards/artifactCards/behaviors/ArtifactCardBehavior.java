package domain.cards.artifactCards.behaviors;

import java.io.Serializable;

public interface ArtifactCardBehavior<R> extends Serializable{

    R use(Object... parameters);
}