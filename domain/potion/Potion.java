package domain.potion;

import domain.GameController;

import java.io.Serializable;
import java.util.Arrays;

public class Potion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3176729771195387799L;


	int pValue;
	//int neutralityValue; // 0 if negative, 1 if positive, 2 if neutral

	public enum potionNeutrality {
		Negative,
		Positive,
		Neutral
	}
	potionNeutrality neutrality;
	//	String potionType;


	public enum potionType {
		Poison,
		Health,
		Slow,
		Speed,
		Insanity,
		Wisdom,
		Neutral
	}
	potionType type;


	Recipe potionRecipe;

	public Potion(Recipe potionRecipe) {
		this.potionRecipe=potionRecipe;
		determinePotion();
		determinePotionNeutrality();
	}

	public void determinePotion() {

		if(potionRecipe.checkRedMatch() == 0) {
			this.type = potionType.Poison;
		}
		else if(potionRecipe.checkRedMatch() == 1) {
			this.type = potionType.Health;
		}
		else if(potionRecipe.checkGreenMatch() == 0) {
			this.type = potionType.Slow;
		}
		else if(potionRecipe.checkGreenMatch() == 1) {
			this.type = potionType.Speed;
		}
		else if(potionRecipe.checkBlueMatch() == 0) {
			this.type = potionType.Insanity;
		}
		else if(potionRecipe.checkBlueMatch() == 1) {
			this.type = potionType.Wisdom;
		}
		else {
			this.type = potionType.Neutral;
		}
	}

	public void determinePotionNeutrality() {
		if (this.type == potionType.Poison || this.type == potionType.Slow || this.type == potionType.Insanity) {
			this.neutrality = potionNeutrality.Negative;
		}
		else if (this.type == potionType.Health || this.type == potionType.Speed || this.type == potionType.Wisdom) {
			this.neutrality = potionNeutrality.Positive;
		}
		else {
			this.neutrality = potionNeutrality.Neutral;
		}
	}

	/*public void testPotion() {
		if(this.personToTest.equals("Self")) {
			if (this.potionType == "Poison") {
				if (!GameController.getInstance().getCurrentPlayer().getInventory().getPlayerArtifactCardList().contains(GameController.getInstance().getGameInventory().getArtCards().get(1))){ //Vaccine card controller
					GameController.getInstance().getCurrentPlayer().getPlayerToken().reduceHealth();
				}
				else {
					GameController.getInstance().getCurrentPlayer().getInventory().getPlayerArtifactCardList().remove(GameController.getInstance().getGameInventory().getArtCards().get(1)); //Vaccine card remover
				}
			}else if(this.potionType == "Health") {
				GameController.getInstance().getCurrentPlayer().getPlayerToken().addHealth();
			}else if(this.potionType == "Slow") {
				GameController.getInstance().getCurrentPlayer().getPlayerToken().reduceHealth();
				GameController.getInstance().getCurrentPlayer().getPlayerToken().reducePlayerAction();
			}else if(this.potionType == "Speed") {
				GameController.getInstance().getCurrentPlayer().getPlayerToken().setPlayerAction(GameController.getInstance().getCurrentPlayer().getPlayerToken().getPlayerAction()+1);;
			}else if(this.potionType == "Insanity") {
				GameController.getInstance().getCurrentPlayer().getPlayerToken().reduceHealth();
				GameController.getInstance().getCurrentPlayer().getPlayerToken().subtractReputationPoint(1);
			}else if(this.potionType == "Wisdom") {
				GameController.getInstance().getCurrentPlayer().getPlayerToken().addReputationPoint(1);
			}


		}else if (this.personToTest.equals("Student")) {

			if (this.potionType == "Poison") {
				GameController.getInstance().getCurrentPlayer().getPlayerToken().subtractGold(1);
			}else if(this.potionType == "Slow") {
				GameController.getInstance().getCurrentPlayer().getPlayerToken().subtractGold(1);
			}else if(this.potionType == "Insanity") {
				GameController.getInstance().getCurrentPlayer().getPlayerToken().subtractGold(1);
			}
		}
	}*/


	public int getpValue() {
		return pValue;
	}

	public void setpValue(int pValue) {
		this.pValue = pValue;
	}

	public potionType getPotionType() {
		return type;
	}

	public void setPotionType(potionType potionType) {
		this.type = potionType;
	}

	/*public String getPersonToTest() {
		return personToTest;
	}

	public void setPersonToTest(String personToTest) {
		this.personToTest = personToTest;
	}*/

	public Recipe getPotionRecipe() {
		return potionRecipe;
	}

	public void setPotionRecipe(Recipe potionRecipe) {
		this.potionRecipe = potionRecipe;
	}

	public potionNeutrality getNeutralityValue() {
		return neutrality;
	}

	public void setNeutralityValue(potionNeutrality neutralityValue) {
		this.neutrality = neutralityValue;
	}


}
