package domain.potion;

import java.io.Serializable;

public class MoleculeFactory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8926819847643175106L;
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
