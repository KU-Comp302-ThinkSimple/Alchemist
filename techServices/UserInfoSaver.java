package techServices;

import domain.player.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Random;

public class UserInfoSaver {
    static HashMap<Integer, Player> players = new HashMap<Integer, Player>();

    public static JSONObject createJsonObj(String username, String encodedPassword) {
        return new JSONObject()
                .put("username", username)
                .put("password", encodedPassword);
    }

    public static void readExistingData() {
        Random rand = new Random();
        try (BufferedReader reader = new BufferedReader(new FileReader("userinfo.json"))) {
            String fileContent = reader.readLine();
            if (fileContent != null && !fileContent.isEmpty()){
                JSONArray jsonArray= new JSONArray(fileContent);
                for (int i = 0; i < jsonArray.length(); i++){
                    int id = rand.nextInt();
                    while(!UserInfoSaver.isPlayerIDAvailable(id)) {
                        id = rand.nextInt();
                    }
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    players.put(players.size(),new Player(id, jsonObject.getString("username"),jsonObject.getString("password")));

                }
            }
        } catch (IOException e) {
            //TODO: Related Exception Handling
        }
    }

    public static void saveIntoJSONFile(HashMap<Integer, Player> players){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userinfo.json"))) {
            JSONArray jsonArray = new JSONArray();
            for (Player player : players.values()){
                jsonArray.put(createJsonObj(player.getPlayerName(),player.getPassword()));
            }
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            //TODO: Related Exception Handling

        }
    }


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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void savePlayer(Player player) throws Exception {
        if (!isPlayerIDAvailable(player.getPlayerId())) {
            throw new Exception("Player id already taken");
        }
        if (!isPlayerNameAvailable(player.getPlayerName())) {
            throw new Exception("Plater name already taken");
        }
        players.put(player.getPlayerId(), player);
        System.out.println(players);
    }

    public static Player getPlayer(String username, String password) throws Exception {
        for (Player player : players.values()) {
            if (player.getPlayerName().equals(username)) {
                if (player.getPassword().equals(password)) {
                    return player;
                } else {
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
