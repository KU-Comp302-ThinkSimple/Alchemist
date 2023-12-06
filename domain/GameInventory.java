package domain;

import java.util.ArrayList;
import java.util.Collections;

import domain.cards.ArtifactCard;
import domain.cards.IngredientCard;
import domain.cards.PublicationCard;
import domain.potion.Atom;
import domain.potion.AtomFactory;
import domain.potion.Molecule;
import domain.potion.MoleculeFactory;
import domain.potion.Potion;
import domain.potion.Recipe;

public class GameInventory {

	ArrayList<Atom> atoms=new ArrayList<Atom>();
	ArrayList<Molecule> molecules=new ArrayList<Molecule>();
	ArrayList<IngredientCard> ingrCards=new ArrayList<IngredientCard>();
	ArrayList<PublicationCard> pubCards=new ArrayList<PublicationCard>();
	ArrayList<ArtifactCard> artCards=new ArrayList<ArtifactCard>();

	
	AtomFactory atomFactory=AtomFactory.getInstance();
	MoleculeFactory molFactory=MoleculeFactory.getInstance();
	
	
	
	private static GameInventory instance;
	
	private GameInventory() {}
	
	
	public static GameInventory getInstance() {
		if (instance == null) {
			instance = new GameInventory();
		}
		return instance;
	}
	
	public void createAtom() {
		
		atoms.add(atomFactory.createAtom(0, 0, 0)); // small red negative index 0
		atoms.add(atomFactory.createAtom(1, 0, 0)); // big red negative	index 1
		atoms.add(atomFactory.createAtom(0, 0, 1)); // small red positive index 2
		atoms.add(atomFactory.createAtom(1, 0, 1)); // big red positive index 3
		
		atoms.add(atomFactory.createAtom(0, 1, 0)); // small green negative index 4
		atoms.add(atomFactory.createAtom(1, 1, 0)); // big green negative	index 5
		atoms.add(atomFactory.createAtom(0, 1, 1)); // small green positive index 6
		atoms.add(atomFactory.createAtom(1, 1, 1)); // big green positive index 7
		
		atoms.add(atomFactory.createAtom(0, 2, 0)); // small blue negative index 8
		atoms.add(atomFactory.createAtom(1, 2, 0)); // big blue negative	index 9
		atoms.add(atomFactory.createAtom(0, 2, 1)); // small blue  positive index 10
		atoms.add(atomFactory.createAtom(1, 3, 1)); // big blue positive index 11
	}
	
	
	public void createMolecule() {
		
		molecules.add(molFactory.createMolecule(atoms.get(0), atoms.get(6), atoms.get(9))); 
		molecules.add(molFactory.createMolecule(atoms.get(2), atoms.get(4), atoms.get(11)));
		molecules.add(molFactory.createMolecule(atoms.get(2), atoms.get(5), atoms.get(8)));
		molecules.add(molFactory.createMolecule(atoms.get(0), atoms.get(7), atoms.get(10)));
		molecules.add(molFactory.createMolecule(atoms.get(1), atoms.get(4), atoms.get(10)));
		molecules.add(molFactory.createMolecule(atoms.get(3), atoms.get(6), atoms.get(8)));
		molecules.add(molFactory.createMolecule(atoms.get(1), atoms.get(5), atoms.get(9)));
		molecules.add(molFactory.createMolecule(atoms.get(3), atoms.get(7), atoms.get(11)));
	}
	
	
	public void createIngredientCard() {
		
		//Shuffles the molecule list and creates ingrCards using those molecules and add them to the list.
		Collections.shuffle(molecules);
		
		ingrCards.add(new IngredientCard("Mushroom",molecules.get(0)));
		ingrCards.add(new IngredientCard("Seedling",molecules.get(1)));
		ingrCards.add(new IngredientCard("Frog",molecules.get(2)));
		ingrCards.add(new IngredientCard("Bird Claw",molecules.get(3)));
		ingrCards.add(new IngredientCard("Flower",molecules.get(4)));
		ingrCards.add(new IngredientCard("Mandrake Root",molecules.get(5)));
		ingrCards.add(new IngredientCard("Scorpion",molecules.get(6)));
		ingrCards.add(new IngredientCard("Raven's Feather",molecules.get(7)));
		
	}
	public void createArtifactCard() {
		
		//TODO create artifact cards manually
}

	
	public void createPublicationCard() {
		
			//TODO create publication cards manually
	}
	
	public ArrayList<IngredientCard> getIngredientCards() {
		return ingrCards;
	}
	
	
	
	
	
}
