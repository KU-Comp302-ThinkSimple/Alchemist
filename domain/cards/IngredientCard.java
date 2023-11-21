package domain.cards;

import domain.potion.Molecule;

public class IngredientCard extends Card{
	
	int ingredientId;
	String name;
	int pointValue;
	Molecule molecule;
	
	public Molecule createMolecule(int moleculeId) {
		return new Molecule(moleculeId);
	}
	
}
