package techServices;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {

	//jpasswordfield returns char[]
	public byte[] charArraytoMD5(char[] password) throws UnsupportedEncodingException, NoSuchAlgorithmException {

		byte[] passwordByteArray = new String(password).getBytes();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] theMD5 = md.digest(passwordByteArray);
		return theMD5;

	}

	public String byteArraytoString(byte[] arr) {
		String s = new String(arr, StandardCharsets.UTF_8);
		return s;
	}

	public String passwordtoMD5String(char[] password) throws UnsupportedEncodingException, NoSuchAlgorithmException { //jpasswordfield returns char[]
		return byteArraytoString(charArraytoMD5(password));
	}



}
