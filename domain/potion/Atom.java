package domain.potion;

import java.io.Serializable;
import java.util.Objects;

public class Atom implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4074276591507476096L;
	int atomId;
	int atomSize; //0 small, 1 big
	int atomColor; // 0 red, 1 green, 2 blue
	int atomSign; //0 negative, 1 positive
	
	public Atom(int atomId, int atomSize, int atomColor, int atomSign) {
		
		this.atomId=atomId;
		this.atomSize = atomSize;
		this.atomColor = atomColor;
		this.atomSign = atomSign;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(atomColor, atomSign, atomSize);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atom other = (Atom) obj;
		return atomColor == other.atomColor && atomSign == other.atomSign && atomSize == other.atomSize;
	}
	
	public int getAtomId() {
		return atomId;
	}




	public void setAtomId(int atomId) {
		this.atomId = atomId;
	}




	public int getAtomSize() {
		return atomSize;
	}

	public void setAtomSize(int atomSize) {
		this.atomSize = atomSize;
	}

	public int getAtomColor() {
		return atomColor;
	}

	public void setAtomColor(int atomColor) {
		this.atomColor = atomColor;
	}

	public int getAtomSign() {
		return atomSign;
	}

	public void setAtomSign(int atomSign) {
		this.atomSign = atomSign;
	}
}
