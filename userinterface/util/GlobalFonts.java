package userinterface.util;

import java.awt.Font;

public class GlobalFonts {

	public static String TAHOMA = "Tahoma";

	public static Font DISPLAY = new Font(TAHOMA, Font.BOLD, 25);
	public static Font DISPLAY_HEADER = new Font(TAHOMA, Font.BOLD, 25);
	public static Font INPUT = new Font(TAHOMA, Font.PLAIN, 20);
	public static Font PASSWORD = new Font(TAHOMA, Font.PLAIN, 18);
	public static Font ERROR_MESSAGE = new Font(TAHOMA, Font.PLAIN, 19);
	public static Font ACTION_BUTTON = new Font(TAHOMA, Font.PLAIN, 15);
	public static Font BREW_BUTTON = new Font(TAHOMA, Font.BOLD, 15);
	public static Font PLAYER_TOKEN_HEADER = new Font(TAHOMA, Font.BOLD, 15);
	public static Font PLAYER_TOKEN_TEXT = new Font(TAHOMA, Font.PLAIN, 15);

	public static String GAME_INFORMATION = """
			Welcome to Alchemy Lab Game!

			In this game, you take on the role of an alchemist conducting experiments in the lab. Your goal is to brew potions, contribute to publications, and form theories about ingredient properties. Here's a quick guide to get you started:

			Game Phases:

			First Round:
			Forage for Ingredients: Draw ingredients from the deck.
			Transmute Ingredient: Discard an ingredient for gold.
			Buy Artifacts: Purchase artifacts with gold.
			Make Experiments: Mix ingredients, test potions, and gain results.
			Second Round:
			Sell a Potion: Offer potions to adventurers for gold.
			Publish a Theory: Share your knowledge about ingredients for reputation points.
			Final Round:
			Debunk or Endorse Theories: Prove or disprove published theories for reputation points.
			Game Elements:

			Player Tokens: Represent your unique avatar. Track your position, resources, and scores.
			Ingredients: Various types with unique properties. Store them in the Ingredient Storage.
			Potions: Brew potions with specific recipes and point values.
			Publication Cards: Contribute to theories for reputation points.
			Artifact Cards: Purchase for unique game advantages.
			Alchemy Markers: Form theories on the Deduction Board.
			Winning the Game:

			Accumulate reputation points and gold.
			Exchange leftover artifacts for gold.
			Score one-third of a point for each gold piece.
			The player with the most score points wins!
			Good luck, alchemist! May your potions be potent and your theories�groundbreaking!
			""" ;
}
