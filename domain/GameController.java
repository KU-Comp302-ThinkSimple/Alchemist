package domain;

import domain.boards.GameBoard;
import domain.initialization.InitializeGameHelper;
import domain.player.Player;
import userinterface.MainGameWindowOnline;
import userinterface.observer.Observable;
import userinterface.observer.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import domain.boards.Board;
import domain.boards.GameBoard;
import domain.initialization.InitializeGameHelper;
import domain.player.Player;
import userinterface.observer.Observable;
import userinterface.observer.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameController implements Serializable, Observable {
	private static final long serialVersionUID = 4936278445022118697L;

	private static GameController instance;
	private transient List<Observer> observers = new ArrayList<>();
	int currentRound=1; //1 2 and 3
	private Player currentPlayer;
	private String gameMode;
	private ArrayList<Player> activePlayers;
	private GameBoard board;
	private GameInventory gameInventory;



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
		if(GameController.getInstance().getGameMode().equals("online")) {
			System.out.println("update instance online");
			MainGameWindowOnline window = ((MainGameWindowOnline)LocalData.getInstance().getMainGameWindow());
			if(window != null) {
				window.addThisToObservables();
				window.updateMainGameWindow();
			}
			LocalData.getInstance().getClient().addThisToObservables();
		}
	}

	//	//This func initializes the game by calling creating a new instance of initliazegamehelper
	//	public void initializeGame() {
	//
	//		//GameController should initialize game when two player are present;
	//
	//		if(activePlayers.size() >= 2) {
	//			System.out.println("Game initialized");
	//			new InitializeGameHelper();
	//		}
	//		else {
	//			System.out.println("Not able to initialize");
	//		}
	//
	//	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	//This func changes rounds
	public void changeRounds() {

		currentRound++;

		for(int i=0;i<activePlayers.size();i++) {
			activePlayers.get(i).getPlayerToken().setPlayerAction(3);
		}
		notifyObserver();
	}

	//Use this function to check whether the gameController should change the round, and if it does, then change the round using changeRounds fucntion.
	public boolean shouldChangeRound(){


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
		if (actionCounter == activePlayers.size()) {
			if ( currentRound == 3) {
				//TODO Game over
				return true;
			}
			return true;
		}else {
			return false;
		}
	}

	//Change the current player after an action using this function
	public void changeCurrentPlayer() {

		for(int i=0;i<activePlayers.size();i++) {
			if(activePlayers.get(i).equals(currentPlayer)) {
				currentPlayer = activePlayers.get((i+1)%activePlayers.size());
				if(gameMode.equals("offline")) {
					LocalData.getInstance().setLocalPlayerIndex((i+1)%activePlayers.size());
				}
				break;
			}
		}
		notifyObserver();
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

	public boolean checkGameEnd() {
		if (currentRound == 4) {
			return true;
		}
		else return false;
	}

	public String gameOverMessage() {

		String str = "";

		Player topPlayer = null;
		ArrayList<Player> drawPlayers = new ArrayList<Player>();
		for (Player p : this.activePlayers) {

			//update string
			str += "\n" + p + "\n";

			//Find players with most points
			if (topPlayer == null) {
				topPlayer = p;
			}
			//if current player has more points
			else if (topPlayer.calculatePoints() < p.calculatePoints()) {
				topPlayer = p;
				drawPlayers.clear();
			}
			//if the top players share the same point
			else if (topPlayer.calculatePoints() == p.calculatePoints()) {
				if (topPlayer.getPlayerToken().getGold() < p.getPlayerToken().getGold()) {
					topPlayer = p;
					drawPlayers.clear();
				}
				else {
					drawPlayers.add(topPlayer);
					drawPlayers.add(p);
				}
			}
		}

		boolean draw = (drawPlayers.size() != 0);
		//If top score is not shared

		if (draw) {
			str += "\nThe game ended with a draw between ";
			for (Player p : drawPlayers) {
				str += " " + p.getPlayerName();
				str += " and ";
			}
			str = str.substring(0, str.length()-4);
			str += " with " + drawPlayers.get(0).calculatePoints() + " points!";
		}
		else {
			str += "\nThe winner is " + topPlayer.getPlayerName() + " with " + topPlayer.calculatePoints() + " points!";
		}

		return str;
	}

	@Override
	public void addObserver(Observer observer) {
		if(observers == null) {
			observers = new ArrayList<>();
		}
		observers.add(observer);
	}

	@Override
	public void notifyObserver() {
		if(observers == null) {
			return;
		}
		for (Observer observer : observers){
			observer.update();
		}
	}
}














