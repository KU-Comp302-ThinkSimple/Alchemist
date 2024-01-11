package domain.theory;

import java.io.Serializable;

import domain.cards.IngredientCard;
import domain.potion.Molecule;
import domain.player.*;

public class Hypotheses implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4080852308402293360L;
	private final Player owner;
	private final IngredientCard ingredient;
	private final Molecule molecule;

	public Hypotheses(Player owner, IngredientCard ingredient, Molecule molecule) {
		super();
		this.owner = owner;
		this.ingredient = ingredient;
		this.molecule = molecule;
	}
	
	public boolean isValid(int atomColorId) {
		return ingredient.getMolecule().getAtomByColor(atomColorId).equals(molecule.getAtomByColor(atomColorId));		
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
