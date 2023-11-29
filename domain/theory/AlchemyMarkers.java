package domain.theory;

import domain.cards.IngredientCard;

public class AlchemyMarkers {
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
