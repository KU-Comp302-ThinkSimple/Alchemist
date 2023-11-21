package domain.potion;

public class Atom {
	int atomSize; //0 small, 1 big
	int atomColor; // 0 red, 1 green, 2 blue
	int atomSign; //0 negative, 1 positive
	
	public Atom(int atomSize, int atomColor, int atomSign) {
		super();
		this.atomSize = atomSize;
		this.atomColor = atomColor;
		this.atomSign = atomSign;
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
