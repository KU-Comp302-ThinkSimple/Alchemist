package domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
		byte[] serializedData = serializeObject(new GameStateTest());
		
		
		
        // Deserialize the object
        GameStateTest outState = deserializeObject(serializedData);

		System.out.println(outState.elem1.otherElem == outState.elem2);
		System.out.println(outState.elem2.otherElem == outState.elem1);
		System.out.println(outState.elem1.name);
		
	}
	
	// Serialize the object to a byte array
    private static byte[] serializeObject(GameStateTest object) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
            System.out.println("Object has been serialized to a byte array");
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Deserialize the object from a byte array
    private static GameStateTest deserializeObject(byte[] data) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
        	GameStateTest deserializedObject = (GameStateTest) ois.readObject();
            System.out.println("Object has been deserialized from a byte array");
            return deserializedObject;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
