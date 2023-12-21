package domain.cards.artifactCards;

import domain.cards.Card;
import domain.cards.artifactCards.behaviors.ArtifactCardBehavior;

public abstract class ArtifactCard<R> extends Card  {
	
	protected ArtifactCardBehavior<R> effect;
	private String name;
	
	public ArtifactCard(ArtifactCardBehavior effect, String name) {
		super();
		this.effect = effect;
		this.name = name;
	}
	
	public  R useCard() {
		
		return this.effect.use();
	}

	public ArtifactCardBehavior getEffect() {
		return effect;
	}

	public void setEffect(ArtifactCardBehavior effect) {
		this.effect = effect;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
