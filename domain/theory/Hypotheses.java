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

	public String isValid() {
		String str = "";
		Molecule actualMol = this.getIngredient().getMolecule();
		Molecule theoryMol = this.getMolecule();
		if (actualMol.equals(theoryMol)) {
			str += "The hypotheses by " + this.owner.getPlayerName() + " was correct!";
			str += "\nIngredient Card of Hypothesis: " + this.getIngredient().getName();
			str += "\nGuessed molecule : " + theoryMol;
		}
		else {
			str += "The hypotheses by " + this.owner.getPlayerName() + " was wrong!";
			str += "\nIngredient Card of Hypothesis: " + this.getIngredient().getName();
			str += "\nGuessed molecule : " + theoryMol;
			str += "\nActual molecule: " + actualMol;
		}
		return str;
	}

}
