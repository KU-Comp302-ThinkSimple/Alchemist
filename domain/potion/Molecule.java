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



	public int getMoleculeId() {
		return moleculeId;
	}



	public void setMoleculeId(int moleculeId) {
		this.moleculeId = moleculeId;
	}



	public Atom getRedAtom() {
		return redAtom;
	}



	public void setRedAtom(Atom redAtom) {
		this.redAtom = redAtom;
	}



	public Atom getGreenAtom() {
		return greenAtom;
	}



	public void setGreenAtom(Atom greenAtom) {
		this.greenAtom = greenAtom;
	}



	public Atom getBlueAtom() {
		return blueAtom;
	}



	public void setBlueAtom(Atom blueAtom) {
		this.blueAtom = blueAtom;
	}
	
	
}
