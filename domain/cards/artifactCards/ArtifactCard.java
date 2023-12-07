package domain.cards.artifactCards;

import domain.cards.Card;

public class ArtifactCard extends Card  {
	
	private ArtifactEffectBehavior effect;
	private String name;
	
	public ArtifactCard(ArtifactEffectBehavior effect, String name) {
		super();
		this.effect = effect;
		this.name = name;
	}
	
	public  <T> T useCard() {
		
		return this.effect.use();
	}
	
}
