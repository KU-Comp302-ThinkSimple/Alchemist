package domain;

import java.util.Random;

import domain.boards.Board;
import domain.boards.GameBoard;
import domain.player.Player;

public class Game {
	
	int currentRound; //1 2 and 3
	Player currentPlayer;
	Player[] activePlayers;
	GameBoard board;
	
	Game(){
		currentRound=1;
		activePlayers= new Player[2];
		this.board = new GameBoard();
	}
	
	
	public void chooseStartingPlayer() {
		Random rand = new Random();
		int num = rand.nextInt(2);
		currentPlayer=activePlayers[num];
	}
	
	public void shuffleCards(){
		
		// board.getIngredientBoard().shuffle();
		// board.getPublicationBoard().shuffle();
		
		//TO DO implement these, also need to find a way to represent artifact cards (maybe within the IngredientBoard?)
	}	
	
	public void distibuteInitialCards() {
		
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				//activePlayers[i].getInventory().addAIngredientCard(board.getIngredientBoard().popIngredient());
				
			}
		}
	}
	
	public void distibuteGold() {
		
		for(int i=0;i<2;i++) {
			activePlayers[i].getPlayerToken().addGold(10);
			}
		}
	}
	
	

	
	
	
     
    
    
    
    
    

