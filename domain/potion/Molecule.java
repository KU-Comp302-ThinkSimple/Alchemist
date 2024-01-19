package domain.potion;

import java.io.Serializable;
import java.util.Objects;

public class Molecule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1684983868961676166L;
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

	@Override
	public int hashCode() {
		return Objects.hash(blueAtom, greenAtom, redAtom);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Molecule other = (Molecule) obj;
		return Objects.equals(blueAtom, other.blueAtom) && Objects.equals(greenAtom, other.greenAtom)
				&& Objects.equals(redAtom, other.redAtom);
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

	public Atom getAtomByColor(int atomColorId) {
		if(redAtom.getAtomColor() == atomColorId) {
			return redAtom;
		}

		else if(greenAtom.getAtomColor() == atomColorId) {
			return greenAtom;
		}

		else if(blueAtom.getAtomColor() == atomColorId) {
			return blueAtom;
		}
		else {
			throw new RuntimeException("Invalid atomColorId");
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

	@Override
	public String toString() {
		String str = this.redAtom + ", " + this.greenAtom + " ," + this.blueAtom;
		return str;
	}

}
