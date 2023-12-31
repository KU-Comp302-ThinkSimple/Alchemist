package domain.potion;

import domain.cards.IngredientCard;

public class Recipe {

	final private IngredientCard ingredient1;
	final private IngredientCard ingredient2;

	public Recipe(IngredientCard ingredient1, IngredientCard ingredient2) {
		//keep the id's of the recipes sorted so that the ingredients being swapped doesn't
		//represent a new recipe
		if(ingredient1.getId() > ingredient2.getId()) {
			this.ingredient1 = ingredient2;
			this.ingredient2 = ingredient1;
		}
		else {
			this.ingredient1 = ingredient1;
			this.ingredient2 = ingredient2;
		}

	}

	public int checkRedMatch() { //0 if matches and negative, 1 if matches and positive, 2 if does not match

		int redAtomSign1=ingredient1.getMolecule().getRedAtom().getAtomSign();
		int redAtomSign2=ingredient2.getMolecule().getRedAtom().getAtomSign();
		int redAtomSize1 = ingredient1.getMolecule().getRedAtom().getAtomSize();
		int redAtomSize2 = ingredient2.getMolecule().getRedAtom().getAtomSize();

		if (redAtomSign1 == redAtomSign2 && redAtomSize1 != redAtomSize2) {
			return redAtomSign1;
		}
		else {
			return 2;
		}
	}

	public int checkGreenMatch() { //0 if matches and negative, 1 if matches and positive, 2 if does not match

		int greenAtomSign1=ingredient1.getMolecule().getGreenAtom().getAtomSign();
		int greenAtomSign2=ingredient2.getMolecule().getGreenAtom().getAtomSign();
		int greenAtomSize1 = ingredient1.getMolecule().getGreenAtom().getAtomSize();
		int greenAtomSize2 = ingredient2.getMolecule().getGreenAtom().getAtomSize();

		if (greenAtomSign1==greenAtomSign2 && greenAtomSize1 != greenAtomSize2) {
			return greenAtomSign1;
		}
		else {
			return 2;
		}
	}

	public int checkBlueMatch() { //0 if matches and negative, 1 if matches and positive, 2 if does not match

		int blueAtomSign1=ingredient1.getMolecule().getBlueAtom().getAtomSign();
		int blueAtomSign2=ingredient2.getMolecule().getBlueAtom().getAtomSign();
		int blueAtomSize1 = ingredient1.getMolecule().getBlueAtom().getAtomSize();
		int blueAtomSize2 = ingredient2.getMolecule().getBlueAtom().getAtomSize();

		if(blueAtomSign1==blueAtomSign2 && blueAtomSize1 != blueAtomSize2) {
			return blueAtomSign1;
		}else {
			return 2;
		}
	}

	public IngredientCard getIngredient1() {
		return ingredient1;
	}

	public IngredientCard getIngredient2() {
		return ingredient2;
	}



}
