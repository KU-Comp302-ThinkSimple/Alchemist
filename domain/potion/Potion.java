package domain.potion;

import domain.GameController;

public class Potion {
	
	int pValue;
	String potionType;
	String personToTest;
	Recipe potionRecipe;
	
	public Potion(Recipe potionRecipe) {
		this.potionRecipe=potionRecipe;
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
	
	public String testPotion() {
		if(this.personToTest.equals("Self")) {
			if (this.potionType == "Poison") {
				GameController.getCurrentPlayer().getPlayerToken().reduceHealth();;	 
			}else if(this.potionType == "Health") {
				//return "I feel renewed!";
			}else if(this.potionType == "Slow") {
				//GameController.getCurrentPlayer().getPlayerToken().reducePlayerAction();;	 	 
			}else if(this.potionType == "Speed") {
				//return "Faster than light!";
			}else if(this.potionType == "Insanity") {
				GameController.getCurrentPlayer().getPlayerToken().subtractReputationPoint(1);;	 
			}else if(this.potionType == "Wisdom") {
				GameController.getCurrentPlayer().getPlayerToken().subtractReputationPoint(1); 
			}else {
				//return "Nothing feels different...";
			}
			
			
		}else if (this.personToTest.equals("Student")) {
			
			if (this.potionType == "Poison") {
				GameController.getCurrentPlayer().getPlayerToken().subtractGold(1);	 
			}else if(this.potionType == "Health") {
				return "I feel renewed!";
			}else if(this.potionType == "Slow") {
				GameController.getCurrentPlayer().getPlayerToken().subtractGold(1);	 	 
			}else if(this.potionType == "Speed") {
				return "Faster than light!";
			}else if(this.potionType == "Insanity") {
				GameController.getCurrentPlayer().getPlayerToken().subtractGold(1);	 
			}else if(this.potionType == "Wisdom") {
				return "Excellence is not a gift, but a skill that takes practice."; 
			}else  {
				return "Nothing feels different...";
			}
		}
		return "";
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
	

}
