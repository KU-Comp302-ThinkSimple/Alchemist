package domain.potion;

public class Molecule {
	
	int MoleculeId;
	Atom redAtom;
	Atom greenAtom;
	Atom blueAtom;
	
	public Molecule(int moleculeId) {
	
		MoleculeId = moleculeId;
		
	}
	
	public Atom createAtom(int atomSize, int atomColor, int atomSign) {
		return new Atom(atomSize, atomColor, atomSign);
	}
	
	public void setAtom(Atom atom) {
		if(atom.getAtomColor()==0) {
			
			this.redAtom=atom;
		}else if(atom.getAtomColor()==1) {
			
			this.greenAtom=atom;
		}else if(atom.getAtomColor()==2) {
			
			this.blueAtom=atom;
		}
	}
	
	
}
