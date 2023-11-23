package domain.potion;

public class Molecule {
	
	int moleculeId;
	Atom redAtom;
	Atom greenAtom;
	Atom blueAtom;
	
	
	
	public Molecule(int moleculeId, Atom redAtom, Atom greenAtom, Atom blueAtom) {
		
		this.moleculeId = moleculeId;
		this.redAtom = redAtom;
		this.greenAtom = greenAtom;
		this.blueAtom = blueAtom;
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
