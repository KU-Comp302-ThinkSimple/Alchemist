package domain.potion;

import domain.GameController;

import java.util.Arrays;

public class Potion {
	
	int pValue;
	int neutralityValue; // 0 if negative, 1 if positive, 2 if neutral
	String potionType;
	String personToTest;
	Recipe potionRecipe;
	
	public Potion(Recipe potionRecipe) {
		this.potionRecipe=potionRecipe;
		determinePotion();
		determinePotionNeutrality();
	}
	
	public void determinePotion() {
		
		if(potionRecipe.checkRedMatch()==0) {
			this.potionType="Poison";
			
		}else if(potionRecipe.checkRedMatch()==1) {
			
			this.potionType="Health";
		}else if(potionRecipe.checkGreenMatch()==0) {
			
			this.potionType="Slow";
			
		}else if(potionRecipe.checkGreenMatch()==1) {
			this.potionType="Speed";
		
		}else if(potionRecipe.checkBlueMatch()==0) {
			this.potionType="Insanity";
			
		}else if(potionRecipe.checkBlueMatch()==1) {
			this.potionType="Wisdom";
		}else {
			this.potionType="Neutral";
		}
	}
	
	public void determinePotionNeutrality() {
		
		if(Arrays.asList("Poison", "Slow", "Insanity").contains(this.potionType)) {
			this.neutralityValue=0;
		}else if(Arrays.asList("Health", "Slow", "Insanity").contains(this.potionType)) {
			
			this.neutralityValue=1;
		}else {
			this.neutralityValue=2;
		}
	}
	
	public void testPotion() {
		if(this.personToTest.equals("Self")) {
			if (this.potionType == "Poison") {
				if (!GameController.getCurrentPlayer().getInventory().getPlayerArtifactCardList().contains(GameController.getGameInventory().getArtCards().get(1))){ //Vaccine card controller
					GameController.getCurrentPlayer().getPlayerToken().reduceHealth();
				}
				else {
					GameController.getCurrentPlayer().getInventory().getPlayerArtifactCardList().remove(GameController.getGameInventory().getArtCards().get(1)); //Vaccine card remover
				}
			}else if(this.potionType == "Health") {
				GameController.getCurrentPlayer().getPlayerToken().addHealth();
			}else if(this.potionType == "Slow") {
				GameController.getCurrentPlayer().getPlayerToken().reduceHealth();
				GameController.getCurrentPlayer().getPlayerToken().reducePlayerAction();	 	 
			}else if(this.potionType == "Speed") {
				GameController.getCurrentPlayer().getPlayerToken().setPlayerAction(GameController.getCurrentPlayer().getPlayerToken().getPlayerAction()+1);; 
			}else if(this.potionType == "Insanity") {
				GameController.getCurrentPlayer().getPlayerToken().reduceHealth();
				GameController.getCurrentPlayer().getPlayerToken().subtractReputationPoint(1); 	 
			}else if(this.potionType == "Wisdom") {
				GameController.getCurrentPlayer().getPlayerToken().addReputationPoint(1);
			}
			
			
		}else if (this.personToTest.equals("Student")) {
			
			if (this.potionType == "Poison") {
				GameController.getCurrentPlayer().getPlayerToken().subtractGold(1);	 
			}else if(this.potionType == "Slow") {
				GameController.getCurrentPlayer().getPlayerToken().subtractGold(1);	 	 
			}else if(this.potionType == "Insanity") {
				GameController.getCurrentPlayer().getPlayerToken().subtractGold(1);	 
			}
		}
	}
	

	public int getpValue() {
		return pValue;
	}

	public void setpValue(int pValue) {
		this.pValue = pValue;
	}

	public String getPotionType() {
		return potionType;
	}

	public void setPotionType(String potionType) {
		this.potionType = potionType;
	}

	public String getPersonToTest() {
		return personToTest;
	}

	public void setPersonToTest(String personToTest) {
		this.personToTest = personToTest;
	}

	public Recipe getPotionRecipe() {
		return potionRecipe;
	}

	public void setPotionRecipe(Recipe potionRecipe) {
		this.potionRecipe = potionRecipe;
	}

	public int getNeutralityValue() {
		return neutralityValue;
	}

	public void setNeutralityValue(int neutralityValue) {
		this.neutralityValue = neutralityValue;
	}
	

}
