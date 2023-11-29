package domain.theory;

import domain.cards.IngredientCard;
import domain.potion.Molecule;

public class Hypotheses {
	private IngredientCard ingredient;
	private Molecule molecule;
	public Hypotheses() {
		super();
	}
	public Hypotheses(IngredientCard ingredient, Molecule molecule) {
		super();
		this.ingredient = ingredient;
		this.molecule = molecule;
	}
	
	public boolean isValid() {
		return ingredient.getMolecule().equals(molecule);
	}
	
	public IngredientCard getIngredient() {
		return ingredient;
	}
	public void setIngredient(IngredientCard ingredient) {
		this.ingredient = ingredient;
	}
	public Molecule getMolecule() {
		return molecule;
	}
	public void setMolecule(Molecule molecule) {
		this.molecule = molecule;
	}
	
}
