package domain.cards;

import domain.potion.Molecule;

public class IngredientCard extends Card{
	

	String name;
	Molecule molecule;
	
	public IngredientCard(String name) {
		
		this.name = name;
	}

	public void setMolecule(Molecule mol) {
		this.molecule=mol;
	}
	
	
	
}
