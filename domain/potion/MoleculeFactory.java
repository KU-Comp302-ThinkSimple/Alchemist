package domain.potion;

public class MoleculeFactory {
	
	private static MoleculeFactory instance;
	private static int id;
	private MoleculeFactory() {}
	
	public static MoleculeFactory getInstance() {
		if (instance == null) {
			instance = new MoleculeFactory();
		}
		return instance;
	}
	
	
	public Molecule createMolecule(Atom redAtom, Atom greenAtom, Atom blueAtom) {
		Molecule ret =  new Molecule(id, redAtom, greenAtom, blueAtom);
		id++;
		return ret;
	}
	

}
