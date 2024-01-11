package domain.theory;

import java.io.Serializable;

import domain.cards.IngredientCard;

public class AlchemyMarkers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7656321360816079488L;
	private IngredientCard ingredient;
    private Hypotheses hypothesis;
	public AlchemyMarkers() {
		super();
	}
	public AlchemyMarkers(IngredientCard ingredient, Hypotheses hypothesis) {
		super();
		this.ingredient = ingredient;
		this.hypothesis = hypothesis;
	}
	public IngredientCard getIngredient() {
		return ingredient;
	}
	public void setIngredient(IngredientCard ingredient) {
		this.ingredient = ingredient;
	}
	public Hypotheses getHypothesis() {
		return hypothesis;
	}
	public void setHypothesis(Hypotheses hypothesis) {
		this.hypothesis = hypothesis;
	}
    
}
