package domain.potion;

public class AtomFactory {
	
	private static int id;
	private static AtomFactory instance;
	
	private AtomFactory() {}
		
	public static AtomFactory getInstance() {
		if (instance == null) {
			instance = new AtomFactory();
		}
		return instance;
	}
	
	
	public Atom createAtom(int atomSize, int atomColor, int atomSign) {
		id++;
		return new Atom(id, atomSize, atomColor, atomSign);
	}
	
	
	

	
	
}
