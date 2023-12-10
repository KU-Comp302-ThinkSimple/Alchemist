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

	public ArtifactEffectBehavior getEffect() {
		return effect;
	}

	public void setEffect(ArtifactEffectBehavior effect) {
		this.effect = effect;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
