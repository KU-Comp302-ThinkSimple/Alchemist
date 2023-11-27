package domain.cards;

import domain.potion.Molecule;

public class IngredientCard extends Card{
	

	String name;
	Molecule molecule;
	
	public IngredientCard(String name, Molecule molecule) {
		
		this.name = name;
		this.molecule=molecule;
	}

	public void setMolecule(Molecule mol) {
		this.molecule=mol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Molecule getMolecule() {
		return molecule;
	}
	
	
	
}
