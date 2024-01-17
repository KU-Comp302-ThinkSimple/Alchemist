package network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NetworkUtilities {
	public static boolean debugEnabled = false;

	// Serialize the object to a byte array
	public static byte[] serializeObject(Object object) {
	    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
	         ObjectOutputStream oos = new ObjectOutputStream(baos)) {
	        oos.writeObject(object);
	        if(debugEnabled) {
		        System.out.println("Object has been serialized to a byte array");
	        }
	        return baos.toByteArray();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	// Deserialize the object from a byte array
	public static Object deserializeObject(byte[] data) {
	    try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
	         ObjectInputStream ois = new ObjectInputStream(bais)) {
	    	Object deserializedObject = ois.readObject();
	    	if(debugEnabled) {
		        System.out.println("Object has been deserialized from a byte array");
	    	}
	        return deserializedObject;
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
