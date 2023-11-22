package techServices;

import java.io.FileWriter;
import java.io.IOException;

//import org.json.*;//TODO

public class UserInfoSaver {

	/*
	//create a json object
	public JSONObject createJsonObj(String username, String encodedPassword) {

	}
	 */

	//add to txt
	public static void addToTxtFile(String username, String encodedPassword, String txtFileName) {
		FileWriter writer;
		try {

			writer = new FileWriter(txtFileName);
			String info = username + ", " + encodedPassword + "/n";
			writer.append(info);
			writer.close();


			/*
			Path filePath = new Path("");
			String info = username + ", " + encodedPassword + "/n";
			Files.writeString("", info);
			 */

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
