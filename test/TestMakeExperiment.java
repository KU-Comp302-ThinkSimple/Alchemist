package test;

import java.util.ArrayList;

import domain.GameController;
import domain.GameInventory;
import domain.cards.IngredientCard;
import domain.potion.Atom;
import domain.potion.Molecule;
import userinterface.LoginSignupController;

public class TestMakeExperiment {

	GameController controller;
	GameInventory inventory;
	ArrayList<IngredientCard> ingredientCards;

	public static void main(String[] args) {

	}

	public void testExperiment() {

		LoginSignupController.getInstance().signup("TestUser1", "testuser");
		LoginSignupController.getInstance().login("TestUser1", "testuser");

		LoginSignupController.getInstance().signup("TestUser2", "testuser");
		LoginSignupController.getInstance().login("TestUser2", "testuser");

		GameController.initializeGame();
		//controller = GameController.geti
		inventory = GameInventory.getInstance();
	}
}
