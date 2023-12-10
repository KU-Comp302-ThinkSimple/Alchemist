package domain;

import java.util.ArrayList;
import java.util.Random;

import domain.boards.Board;
import domain.boards.GameBoard;
import domain.player.Player;
import domain.potion.Atom;
import userinterface.MainGameWindow;

public class GameController {

	static int currentRound=1; //1 2 and 3
	static Player currentPlayer;
	static ArrayList<Player> activePlayers=new ArrayList<Player>();
	static GameBoard board = new GameBoard();
	static GameInventory gameInventory=GameInventory.getInstance();
	static InitializeGameHelper initalizeGameHelper;
	static MainGameWindow mainGameWindow;


	// This is the main game controller, it holds various states and attributes of the game

	GameController(){

	}


	//This func initializes the game by calling creating a new instance of initliazegamehelper
	public static void initializeGame() {

		//GameController should initialize game when two player are present;

		if(activePlayers.size() == 2) {
			System.out.println("Game initialized");
			initalizeGameHelper=new InitializeGameHelper();
		}
		else {
			System.out.println("Not able to initialize");
		}

	}

//This func changes rounds
	public static void changeRounds() {
		currentRound++;

		for(int i=0;i<activePlayers.size();i++) {
			activePlayers.get(i).getPlayerToken().setPlayerAction(3);
		}
	}

	//Use this function to check whether the gameController should change the round, and if it does, then change the round using changeRounds fucntion.
	public static Boolean shouldChangeRound(){

		int player1ActionsLeft= activePlayers.get(0).getPlayerToken().getPlayerAction();
		int player2ActionsLeft= activePlayers.get(1).getPlayerToken().getPlayerAction();

		if(player1ActionsLeft==0 && player1ActionsLeft==0) {
			return true;
		}else {
			return false;
		}
	}

	//Change the current player after an action using this function
	public static void changeCurrentPlayer() {

		for(int i=0;i<activePlayers.size();i++) {
			if(!activePlayers.get(i).equals(currentPlayer)) {
				currentPlayer=activePlayers.get(i);
				break;
			}
		}
	}

	public static int getCurrentRound() {
		return currentRound;
	}

	public static void setCurrentRound(int currentRound) {
		GameController.currentRound = currentRound;
	}

	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	public static void setCurrentPlayer(Player currentPlayer) {
		GameController.currentPlayer = currentPlayer;
	}

	public static ArrayList<Player> getActivePlayers() {
		return activePlayers;
	}


	public static void setActivePlayers(ArrayList<Player> activePlayers) {
		GameController.activePlayers = activePlayers;
	}

	public static GameBoard getBoard() {
		return board;
	}

	public static void setBoard(GameBoard board) {
		GameController.board = board;
	}

	public static GameInventory getGameInventory() {
		return gameInventory;
	}

	public static void setGameInventory(GameInventory gameInventory) {
		GameController.gameInventory = gameInventory;
	}

	public static InitializeGameHelper getInitalizeGameHelper() {
		return initalizeGameHelper;
	}

	public static void setInitalizeGameHelper(InitializeGameHelper initalizeGameHelper) {
		GameController.initalizeGameHelper = initalizeGameHelper;
	}



	public static MainGameWindow getMainGameWindow() {
		return mainGameWindow;
	}


	public static void setMainGameWindow(MainGameWindow mainGameWindow) {
		GameController.mainGameWindow = mainGameWindow;
	}
}














