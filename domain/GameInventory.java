package domain;

import domain.cards.IngredientCard;
import domain.cards.PublicationCard;
import domain.cards.artifactCards.ArtifactCard;
import domain.cards.artifactCards.ElixirOfInsight;
import domain.cards.artifactCards.behaviors.ElixirOfInsightBehavior;
import domain.potion.Atom;
import domain.potion.AtomFactory;
import domain.potion.Molecule;
import domain.potion.MoleculeFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
public class GameInventory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7722233986486390188L;
	private final ArrayList<Atom> atoms=new ArrayList<Atom>();
	private final ArrayList<Molecule> molecules=new ArrayList<Molecule>();
	private final ArrayList<IngredientCard> ingrCards=new ArrayList<IngredientCard>();
	private final ArrayList<PublicationCard> pubCards=new ArrayList<PublicationCard>();
	private final ArrayList<ArtifactCard> artCards=new ArrayList<ArtifactCard>();

	
	AtomFactory atomFactory=AtomFactory.getInstance();
	MoleculeFactory molFactory=MoleculeFactory.getInstance();
	
	
	// This is a singleton gameInventroy class that stores all the cards and objects of the game
	private static GameInventory instance;

	private GameInventory() {}
	
	
	public static GameInventory getInstance() {
		if (instance == null) {
			instance = new GameInventory();
		}
		return instance;
	}
	
	//this func creates atoms using factory and adds them to the list
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
	
	//this func creates molecules using factory and adds them to the list
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
	
	//this func creates ingrs and adds them to the list
	public void createIngredientCard() {
		
		//Shuffles the molecule list and creates ingrCards using those molecules and add them to the list.
		Collections.shuffle(molecules);
		
		ingrCards.add(new IngredientCard(0, "Mushroom",molecules.get(0)));
		ingrCards.add(new IngredientCard(1, "Seedling",molecules.get(1)));
		ingrCards.add(new IngredientCard(2, "Frog",molecules.get(2)));
		ingrCards.add(new IngredientCard(3, "Bird Claw",molecules.get(3)));
		ingrCards.add(new IngredientCard(4, "Flower",molecules.get(4)));
		ingrCards.add(new IngredientCard(5, "Mandrake Root",molecules.get(5)));
		ingrCards.add(new IngredientCard(6, "Scorpion",molecules.get(6)));
		ingrCards.add(new IngredientCard(7, "Raven's Feather",molecules.get(7)));
		
	}
	//this func creates artifact cards and adds them to the list
	public void createArtifactCard() {
		
		//TODO create artifact cards manually
		artCards.add(new ElixirOfInsight( new ElixirOfInsightBehavior(), "Elixir Of Insight" ));
		//artCards.add(new ArtifactCard(new Vaccine(),"Vaccine"));
}

	
	public void createPublicationCard() {
		
			//TODO create publication cards manually
	}
	
	public ArrayList<IngredientCard> getIngredientCards() {
		return ingrCards;
	}
	

	public ArrayList<Atom> getAtoms() {
		return atoms;
	}


	public ArrayList<Molecule> getMolecules() {
		return molecules;
	}


	public ArrayList<IngredientCard> getIngrCards() {
		return ingrCards;
	}


	public ArrayList<ArtifactCard> getArtCards() {
		return artCards;
	}
	
	
	
}
