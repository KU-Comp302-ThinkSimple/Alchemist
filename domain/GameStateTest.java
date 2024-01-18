package domain;

import java.io.Serializable;

import network.NetworkUtilities;

public class GameStateTest implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7196128476894229422L;
	String name;
	Element elem1;
	Element elem2;
	public class Element implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -2133230584530613795L;
		private String name;
		private Element otherElem;
		public Element(String name) {
			this.name = name;
		}
	}

	public GameStateTest() {
		this.elem1 = new Element("elem1");
		this.elem2 = new Element("elem2");
		this.elem1.otherElem = this.elem2;
		this.elem2.otherElem = this.elem1;
	}
	
	public static void main(String[] args) {
		byte[] serializedData = NetworkUtilities.serializeObject(new GameStateTest());
		
		
		
        // Deserialize the object
        GameStateTest outState = (GameStateTest) NetworkUtilities.deserializeObject(serializedData);

		System.out.println(outState.elem1.otherElem == outState.elem2);
		System.out.println(outState.elem2.otherElem == outState.elem1);
		System.out.println(outState.elem1.name);
		
	}
}
