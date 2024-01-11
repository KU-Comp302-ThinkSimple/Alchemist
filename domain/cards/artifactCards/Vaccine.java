package domain.cards.artifactCards;

import domain.cards.artifactCards.behaviors.ArtifactCardBehavior;

public class Vaccine extends ArtifactCard {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3658979915804184905L;

	public Vaccine(ArtifactCardBehavior effect, String name) {
        super(effect, name);
    }
}