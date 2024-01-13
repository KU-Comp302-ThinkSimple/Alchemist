package domain.cards.artifactCards.behaviors;

public interface ArtifactCardBehavior<R> {

    R use(Object... parameters);
}