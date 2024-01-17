package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import domain.boards.Board;
import domain.boards.GameBoard;
import domain.player.Player;
import domain.potion.Atom;
import exception.UserErrorException;
import userinterface.MainGameWindow;

public class GameController implements Serializable{
	private static final long serialVersionUID = 4936278445022118697L;

	private static GameController instance;

	int currentRound=1; //1 2 and 3
	private Player currentPlayer;
	private Player localPlayer;
	private String gameMode;
	private ArrayList<Player> activePlayers;
	private GameBoard board;
	private GameInventory gameInventory;
	private InitializeGameHelper initalizeGameHelper;
	private MainGameWindow mainGameWindow;


	// This is the main game controller, it holds various states and attributes of the game

	GameController(){
		this.activePlayers = new ArrayList<Player>();
		this.board = new GameBoard();
		this.gameInventory = GameInventory.getInstance();
	}
	
	public static GameController getInstance() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}


	//This func initializes the game by calling creating a new instance of initliazegamehelper
	public void initializeGame() {

		//GameController should initialize game when two player are present;

		if(activePlayers.size() == 2) { //TODO change this to number of players
			System.out.println("Game initialized");
			initalizeGameHelper=new InitializeGameHelper();
		}
		else {
			System.out.println("Not able to initialize");
		}

	}

//This func changes rounds
	public void changeRounds() throws UserErrorException {
		
		
		
		if(currentRound==3) {
			throw new UserErrorException("The game is over");
		}
		
		currentRound++;
		
		

		for(int i=0;i<activePlayers.size();i++) {
			activePlayers.get(i).getPlayerToken().setPlayerAction(3);
		}
		
	}

	//Use this function to check whether the gameController should change the round, and if it does, then change the round using changeRounds fucntion.
	public Boolean shouldChangeRound(){
		
		
		//Below is the old code before refactoring.

//		int player1ActionsLeft= activePlayers.get(0).getPlayerToken().getPlayerAction();
//		int player2ActionsLeft= activePlayers.get(1).getPlayerToken().getPlayerAction();
//
//		if(player1ActionsLeft==0 && player1ActionsLeft==0) {
//			return true;
//		}else {
//			return false;
//		}
		
		int actionCounter=0;
		for(int i=0; i<activePlayers.size();i++) {
			if(activePlayers.get(i).getPlayerToken().getPlayerAction()==0) {
				actionCounter++;
			}
		}
		if(actionCounter==activePlayers.size()) {
			return true;
		}else {
			return false;
		}
	}

	//Change the current player after an action using this function
	public void changeCurrentPlayer() {
		
		//TODO change the gameMode attribute and update these conditions.
		//If game is online, currentPlayer and localPlayer are different.
		if(gameMode.equals("online")) {
			
			for(int i=0;i<activePlayers.size();i++) {
				if(!activePlayers.get(i).equals(currentPlayer)) {
					currentPlayer=activePlayers.get(i);
					break;
				}
			}
			
			//If game is offline localPlayer is equal to currentPlayer 
		}else if(gameMode.equals("offline")) {
			
			for(int i=0;i<activePlayers.size();i++) {
				if(!activePlayers.get(i).equals(currentPlayer)) {
					currentPlayer=activePlayers.get(i);
					localPlayer=currentPlayer;
					break;
				}
			}
			
		}else {
			//If the game mode is not specified
			for(int i=0;i<activePlayers.size();i++) {
				if(!activePlayers.get(i).equals(currentPlayer)) {
					currentPlayer=activePlayers.get(i);
					localPlayer=currentPlayer;
					break;
				}
			}
		}
		
		
		//Below is the old code.
//		for(int i=0;i<activePlayers.size();i++) {
//			if(!activePlayers.get(i).equals(currentPlayer)) {
//				currentPlayer=activePlayers.get(i);
//				break;
//			}
//		}
		
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

	public InitializeGameHelper getInitalizeGameHelper() {
		return initalizeGameHelper;
	}

	public void setInitalizeGameHelper(InitializeGameHelper initalizeGameHelper) {
		this.initalizeGameHelper = initalizeGameHelper;
	}



	public MainGameWindow getMainGameWindow() {
		return mainGameWindow;
	}


	public void setMainGameWindow(MainGameWindow mainGameWindow) {
		this.mainGameWindow = mainGameWindow;
	}
	
}














