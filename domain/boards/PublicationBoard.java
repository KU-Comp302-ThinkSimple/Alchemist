package domain.boards;
import java.util.*;
import exception.*;

import domain.theory.*;
import domain.player.*;
import domain.cards.*;
import domain.potion.*;
public class PublicationBoard extends Board{
	private ArrayList<Hypotheses> hypotheses;


	public PublicationBoard(int size, String name) {
        super(size, name);
        this.hypotheses = new ArrayList<Hypotheses>();
    }
    
    public void publishTheory(Player player, IngredientCard ingredient, Molecule hypothesizedMolecule) throws UserErrorException{
    	PlayerInventory inv = player.getInventory();
    	int gold = player.getPlayerToken().getGold();
    	if(!player.getPlayerToken().hasActionsLeft()) {
    		throw new UserErrorException("The user has no more actions left!");
    	}
    	if(!inv.getPlayerIngredientCardList().contains(ingredient)) {
    		throw new UserErrorException("The user does not own this ingredient!");
    	}
    	if(gold < 1) {
    		throw new UserErrorException("The user does not have enough gold!");
    	}
    	if(hypotheses.stream().map(t -> t.getIngredient()).anyMatch(t -> t.equals(ingredient))){
    		throw new UserErrorException("This ingredient already has a theory on it!");
    	}
    	Hypotheses hypothesis = new Hypotheses(player, ingredient, hypothesizedMolecule);
    	player.getInventory().addHypoteses(hypothesis);
    	hypotheses.add(hypothesis);
    	player.getPlayerToken().addReputationPoint(1);
    	player.getPlayerToken().subtractGold(1);
    }
    
    public ArrayList<Hypotheses> getHypotheses() {
		return hypotheses;
	}
}
