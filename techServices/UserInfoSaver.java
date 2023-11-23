package techServices;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import domain.player.Player;

//import org.json.*;//TODO

public class UserInfoSaver {
	
	static HashMap<Integer, Player> players = new HashMap<Integer, Player>();

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
	
	public static void savePlayer(Player player) throws Exception{
		if(!isPlayerIDAvailable(player.getPlayerId())){
			throw new Exception("Player id already taken");
		}
		if(!isPlayerNameAvailable(player.getPlayerName())) {
			throw new Exception("Plater name already taken");
		}
		players.put(player.getPlayerId(), player);
	}
	
	public static Player getPlayer(String username, String password) throws Exception{
		for (Player player : players.values()) {
			if(player.getPlayerName().equals(username)) {
				if(player.getPassword().equals(password)) {
					return player;
				}
				else {
					throw new Exception("Username and password do not match");
				}
			}
		}
		throw new Exception("No user with given username");
	}
	
	public static boolean isPlayerIDAvailable(int id) {
		return !players.containsKey(id);
	}
	
	public static boolean isPlayerNameAvailable(String name) {
		return !players.values().stream().filter(t -> t.getPlayerName().equals(name)).findAny().isPresent();
	}
}
