package domain.theory;

import domain.cards.IngredientCard;
import domain.potion.Molecule;
import domain.player.*;

public class Hypotheses {
	private final Player owner;
	private final IngredientCard ingredient;
	private final Molecule molecule;

	public Hypotheses(Player owner, IngredientCard ingredient, Molecule molecule) {
		super();
		this.owner = owner;
		this.ingredient = ingredient;
		this.molecule = molecule;
	}
	
	public boolean isValid() {
		return ingredient.getMolecule().equals(molecule);
	}
	
	public IngredientCard getIngredient() {
		return ingredient;
	}

	public Molecule getMolecule() {
		return molecule;
	}
	
	public Player getOwner() {
		return owner;
	}
}
