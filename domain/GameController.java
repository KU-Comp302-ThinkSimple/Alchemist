package domain;

import java.util.ArrayList;
import java.util.Random;

import domain.boards.Board;
import domain.boards.GameBoard;
import domain.player.Player;
import domain.potion.Atom;

public class GameController {
	
	static int currentRound=1; //1 2 and 3
	static Player currentPlayer;
	static ArrayList<Player> activePlayers=new ArrayList<Player>();
	static GameBoard board= new GameBoard();
	static GameInventory gameInventory=GameInventory.getInstance();
	static InitializeGameHelper initalizeGameHelper;
	
	GameController(){
		
		initializeGame();
	}
	
	public void initializeGame() {
		
		//GameController waits until the activePlayers list has 2 players in it, and after that, initializes the game by creating a new instance of initializeGameHelper.
		
		while(activePlayers.size()<2) {
			
		}
		initalizeGameHelper=new InitializeGameHelper();
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

	
	
	
	
	}
	
	
	

	
	
	
     
    
    
    
    
    

