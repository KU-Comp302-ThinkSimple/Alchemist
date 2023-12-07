package domain.boards;
import java.util.*;
import exception.*;

import domain.theory.*;
import domain.player.*;
import domain.GameController;
import domain.cards.*;
import domain.potion.*;
import exception.*;
public class PublicationBoard extends Board{
	private ArrayList<Hypotheses> hypotheses;
	private ArrayList<IngredientCard> provenIngredients;

	public PublicationBoard() {
    
        this.hypotheses = new ArrayList<Hypotheses>();
    }

    /**
     * @param player: The player who initiated the publishing action
     * @param ingredient: The ingredient being theorized on
     * @param hypothesizedMolecule: The molecule that is theorized to make up said ingredient
     * @throws UserErrorException: Thrown if user has insufficient gold, rounds are incorrect, the player doesnt have said ingredient.
     */
    public void publishTheory(Player player, IngredientCard ingredient, Molecule hypothesizedMolecule) throws UserErrorException, RuntimeException{
      PlayerInventory inv = player.getInventory();
    	int gold = player.getPlayerToken().getGold();
    	
    	//check if we are in round 2 or 3
    	if(GameController.getCurrentRound()<2) {
    		throw new UserErrorException("Theories can only be published in rounds 2 and 3.");
    	}
  
      if(!player.getPlayerToken().hasActionsLeft()) {
    		throw new UserErrorException("The user has no more actions left!");
    	}
    	
    	//check if user indeed owns this ingredient
    	if(!inv.getPlayerIngredientCardList().contains(ingredient)) {
    		throw new UserErrorException("The user does not own this ingredient!");
    	}
    	
    	//check if user has sufficient gold
    	if(gold < 1) {
    		throw new UserErrorException("The user does not have enough gold!");
    	}
    	
    	//check if this ingredient already has a theory on it
    	if(hypotheses.stream().map(t -> t.getIngredient()).anyMatch(t -> t.equals(ingredient))){
    		throw new UserErrorException("This ingredient already has a theory on it!");
    	}
    	//create hypothesis and add it to the relevant places
    	Hypotheses hypothesis = new Hypotheses(player, ingredient, hypothesizedMolecule);
    	player.getInventory().addHypoteses(hypothesis);
    	hypotheses.add(hypothesis);
    	player.getPlayerToken().addReputationPoint(1);
    	player.getPlayerToken().subtractGold(1);
    	
    	//Reduce Player Actions
    	player.getPlayerToken().reducePlayerAction();
    }
    
    /**
     * @param player: The player performing the debunking
     * @param hypothesis: The hypothesis to be debunked
     * @throws UserErrorException: Thrown if round isnt or hypothesis isn't in publication track
     */
    public void debunkTheory(Player player, Hypotheses hypothesis) throws UserErrorException, RuntimeException{
    	//TODO: Is the implementation correct? The ingredients whose molecule have been uncovered get put in the 
    	//provenIngredients list, and the hypothesis gets removed everywhere (so it doesnt get debunked again)
    	
    	//check if user has actions left
    	if(!player.getPlayerToken().hasActionsLeft()) {
    		throw new UserErrorException("The player has no more actions left!");
    	}
    	//check if the hypothesis exists in the publication track
    	if(!hypotheses.contains(hypothesis)) {
    		throw new IllegalArgumentException("The given hypothesis does not exist within the publication track");
    	}
    	
    	//check if we are in round 3
    	if(GameController.getCurrentRound()<3) {
    		throw new UserErrorException("Theories can only be debunked in the third round.");
    	}
    	
    	if(hypothesis.isValid()) {
    		//if the hypothesis is valid, punish the debunker
    		player.getPlayerToken().setReputation(player.getPlayerToken().getReputation() - 1);
    	}
    	else {
    		//if the hypothesis is incorrect, reward the debunker
    		player.getPlayerToken().setReputation(player.getPlayerToken().getReputation() + 2);
    		
    		//TODO: will the hypothesis owner lose points?
    		//Player hypothesisOwner = hypothesis.getOwner();
    		//hypothesisOwner.getPlayerToken().setReputation(hypothesisOwner.getPlayerToken().getReputation() - 1);
    	}
    	//Since either way the true nature of the ingredient is revealed, put it into the provenIngredients list
    	//and remove the hypothesis from everywhere
		provenIngredients.add(hypothesis.getIngredient());
    	hypotheses.remove(hypothesis);
    	hypothesis.getOwner().getInventory().removeHypoteses(hypothesis);
    	player.getPlayerToken().reducePlayerAction();
    	
    	//Reduce Player Actions
    	player.getPlayerToken().reducePlayerAction();
    }
    
    public ArrayList<Hypotheses> getHypotheses() {
		return hypotheses;
	}
}
