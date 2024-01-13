package domain.cards.artifactCards;

import domain.cards.Card;
import domain.cards.artifactCards.behaviors.ArtifactCardBehavior;
import userinterface.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class ArtifactCard<R> extends Card {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5185302057469407727L;
	protected ArtifactCardBehavior<R> effect;
	private String name;
	private List<Observer> observers = new ArrayList<>();

	public ArtifactCard(ArtifactCardBehavior effect, String name) {
		super();
		this.effect = effect;
		this.name = name;
	}

	public  R useCard() {
		notifyObserver();
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
	@Override
	public void notifyObserver(){
		for (Observer observer : observers){
			observer.update();
		}
	}
	public void addObserver(Observer observer){
		observers.add(observer);
	}

}