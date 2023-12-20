package domain.boards;

import domain.GameController;
import domain.cards.IngredientCard;
import domain.player.Player;
import domain.player.PlayerInventory;
import domain.potion.Molecule;
import domain.theory.Hypotheses;
import exception.UserErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
public class PublicationBoard extends Board{
	private ArrayList<Hypotheses> hypotheses;
	private HashMap<IngredientCard, HashSet<Integer>> provenIngredientAtoms;

	public PublicationBoard() {
        this.hypotheses = new ArrayList<Hypotheses>();
        this.provenIngredientAtoms = new HashMap<IngredientCard, HashSet<Integer>>();
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
    	if(GameController.getInstance().getCurrentRound()<2) {
    		throw new UserErrorException("Theories can only be published in rounds 2 and 3.");
    	}
  
    	
//    	//check if user indeed owns this ingredient
//    	if(!inv.getPlayerIngredientCardList().contains(ingredient)) {
//    		throw new UserErrorException("The user does not own this ingredient!");
//    	}
    	
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
     * @param atomColorId: color id of atom to debunk
     * @return the true sign of the atom
     * @throws UserErrorException: Thrown if round isnt or hypothesis isn't in publication track
     */
    public String debunkTheory(Player player, Hypotheses hypothesis, int atomColorId) throws UserErrorException{

    	//check if the hypothesis exists in the publication track
    	if(!hypotheses.contains(hypothesis)) {
    		throw new IllegalArgumentException("The given hypothesis does not exist within the publication track");
    	}
    	
    	//check if we are in round 3
    	if(GameController.getInstance().getCurrentRound()<3) {
    		throw new UserErrorException("Theories can only be debunked in the third round.");
    	}
    	String out;
    	
    	if(hypothesis.isValid(atomColorId)) {
    		//if the hypothesis is valid, punish the debunker
    		player.getPlayerToken().addReputationPoint(-1);
    		out=" Hypothesis proven correct.";
    	}
    	else {
    		//if the hypothesis is incorrect, reward the debunker
    		player.getPlayerToken().addReputationPoint(2);
    		out=" Hypothesis proven false.";
    	}
    	//Since either way the true nature of the ingredient is revealed, put it into the provenIngredients list
    	//and remove the hypothesis from everywhere
    	if(!provenIngredientAtoms.containsKey(hypothesis.getIngredient())) {
    		provenIngredientAtoms.put(hypothesis.getIngredient(), new HashSet<Integer>());
    	}
    	provenIngredientAtoms.get(hypothesis.getIngredient()).add(atomColorId);
    	hypotheses.remove(hypothesis);
    	hypothesis.getOwner().getInventory().removeHypoteses(hypothesis);
    	
    	//Reduce Player Actions
    	player.getPlayerToken().reducePlayerAction();
    	
    	//return the true nature of the ingredient
    	switch (hypothesis.getIngredient().getMolecule().getAtomByColor(atomColorId).getAtomSign()) {
		case 0: {
			return "Negative" + out;
		}
		case 1: {
			return "Positive" + out;
		}
		default:
			throw new IllegalArgumentException("Invalid atom sign");
		}
    }
    
    public ArrayList<Hypotheses> getHypotheses() {
		return hypotheses;
	}
}
