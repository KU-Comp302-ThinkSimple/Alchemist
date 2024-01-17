package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import domain.boards.Board;
import domain.boards.GameBoard;
import domain.player.Player;
import domain.potion.Atom;
import userinterface.MainGameWindow;

public class GameController implements Serializable{
	private static final long serialVersionUID = 4936278445022118697L;

	private static GameController instance;

	int currentRound=1; //1 2 and 3
	private Player currentPlayer;
	private ArrayList<Player> activePlayers;
	private GameBoard board;
	private GameInventory gameInventory;
	private MainGameWindow mainGameWindow;


	// This is the main game controller, it holds various states and attributes of the game

	GameController(){
		this.activePlayers = new ArrayList<Player>();
		this.board = new GameBoard();
		this.gameInventory = new GameInventory();
	}
	
	public static GameController getInstance() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	public static void updateInstance(GameController newInstance) {
		GameController.instance = newInstance;
	}


	//This func initializes the game by calling creating a new instance of initliazegamehelper
	public void initializeGame() {

		//GameController should initialize game when two player are present;

		if(activePlayers.size() == 2) {
			System.out.println("Game initialized");
			new InitializeGameHelper();
		}
		else {
			System.out.println("Not able to initialize");
		}

	}

//This func changes rounds
	public void changeRounds() {
		currentRound++;

		for(int i=0;i<activePlayers.size();i++) {
			activePlayers.get(i).getPlayerToken().setPlayerAction(3);
		}
	}

	//Use this function to check whether the gameController should change the round, and if it does, then change the round using changeRounds fucntion.
	public Boolean shouldChangeRound(){

		int player1ActionsLeft= activePlayers.get(0).getPlayerToken().getPlayerAction();
		int player2ActionsLeft= activePlayers.get(1).getPlayerToken().getPlayerAction();

		if(player1ActionsLeft==0 && player1ActionsLeft==0) {
			return true;
		}else {
			return false;
		}
	}

	//Change the current player after an action using this function
	public void changeCurrentPlayer() {

		for(int i=0;i<activePlayers.size();i++) {
			if(!activePlayers.get(i).equals(currentPlayer)) {
				currentPlayer=activePlayers.get(i);
				break;
			}
		}
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public ArrayList<Player> getActivePlayers() {
		return activePlayers;
	}


	public void setActivePlayers(ArrayList<Player> activePlayers) {
		this.activePlayers = activePlayers;
	}

	public GameBoard getBoard() {
		return board;
	}

	public void setBoard(GameBoard board) {
		this.board = board;
	}

	public GameInventory getGameInventory() {
		return gameInventory;
	}

	public void setGameInventory(GameInventory gameInventory) {
		this.gameInventory = gameInventory;
	}


	public MainGameWindow getMainGameWindow() {
		return mainGameWindow;
	}


	public void setMainGameWindow(MainGameWindow mainGameWindow) {
		this.mainGameWindow = mainGameWindow;
	}
	
}














