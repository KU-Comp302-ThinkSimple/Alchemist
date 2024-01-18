package userinterface.util;

import javax.swing.ImageIcon;

import userinterface.BrewPotionPanel;

public class GlobalIcons {

	//CARDS

	//Ingredient Cards
	public static ImageIcon flower = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/flower_100x160.png"));
	public static ImageIcon birdclaw = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/birdclaw_100x160.png"));
	public static ImageIcon feather = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/feather_100x160.png"));
	public static ImageIcon frog = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/frog_100x160.png"));
	public static ImageIcon seedling = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/seedling_100x160.png"));
	public static ImageIcon mandrakeroot = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/mandrakeroot_100x160.png"));
	public static ImageIcon mushroom = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/mushroom_100x160.png"));
	public static ImageIcon scorpion = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/scorpion_100x160.png"));
	public static ImageIcon unknowncard = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/unknownCard_100x160.png"));

	public static ImageIcon getIngredientCardImage(String name) {
		switch (name) {
		case "Flower": return flower;
		case "Bird Claw": return birdclaw;
		case "Raven's Feather": return feather;
		case "Frog": return frog;
		case "Seedling": return seedling;
		case "Mandrake Root": return mandrakeroot;
		case "Mushroom" : return mushroom;
		case "Scorpion" : return scorpion;
		case "Unknown" : return unknowncard;
		default : return unknowncard;
		}
	}

	//Artifact Cards
	public static ImageIcon elixirofinsight = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/elixirofinsight_100x160.png"));
	public static ImageIcon vaccine = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/vaccine_100x160.png"));
	public static ImageIcon wisdomidol = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/wisdomIdol_100x160.png"));
	public static ImageIcon magicmortar = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/magicMortar_100x160.png"));
	public static ImageIcon printingpress = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/printingPress_100x160.png"));
	public static ImageIcon unknownArtifact = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/unknownArtifactCard_100x160.png"));

	public static ImageIcon getArtifactCardImage(String name) {
		switch (name) {
		case "Elixir Of Insight": return elixirofinsight;
		case "Vaccine": return vaccine;
		case "Wisdom Idol": return wisdomidol;
		case "Magic Mortar": return magicmortar;
		case "Printing Press": return printingpress;
		case "Unknown" : return unknownArtifact;
		default : return unknownArtifact;
		}
	}

	//POTIONS 40X60
	public static ImageIcon littlehealth = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/healthpotion_40x60.png")); //red plus
	public static ImageIcon littlepoison = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/poisonpotion_40x60.png")); //red minus
	public static ImageIcon littlespeed = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/speedpotion_40x60.png")); //green plus
	public static ImageIcon littleslow = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/slowpotion_40x60.png")); //green minus
	public static ImageIcon littlewisdom = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/wisdompotion_40x60.png")); //blue plus
	public static ImageIcon littleinsanity = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/insanitypotion_40x60.png")); //blue minus
	public static ImageIcon littleneutral = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/neutralpotion_40x60.png")); //gray neutral
	public static ImageIcon littleunknown = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/unknownpotion_40x60.png")); //unknown potion

	public static ImageIcon getLittlePotionImage(String name) {
		switch (name) {
		case "Health": return littlehealth;
		case "Poison": return littlepoison;
		case "Speed": return littlespeed;
		case "Slow": return littleslow;
		case "Wisdom": return littlewisdom;
		case "Insanity": return littleinsanity;
		case "Neutral" : return littleneutral;
		case "Unknown" : return littleunknown;
		default : return littleunknown;
		}
	}

	//MOLECULES 100X100
	public static ImageIcon mol0 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/mol1_100x100.png"));
	public static ImageIcon mol1 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/mol2_100x100.png"));
	public static ImageIcon mol2 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/mol3_100x100.png"));
	public static ImageIcon mol3 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/mol4_100x100.png"));
	public static ImageIcon mol4 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/mol5_100x100.png"));
	public static ImageIcon mol5 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/mol6_100x100.png"));
	public static ImageIcon mol6 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/mol7_100x100.png"));
	public static ImageIcon mol7 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/mol8_100x100.png"));

	public static ImageIcon getMoleculeImage(String id) {
		switch (id) {
		case "0": return mol0;
		case "1": return mol1;
		case "2": return mol2;
		case "3": return mol3;
		case "4": return mol4;
		case "5": return mol5;
		case "6": return mol6;
		case "7": return mol7;
		default : return mol0; //TODO unknown molecule
		}
	}

	public static ImageIcon alchemistBig = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/alchemist_square.png"));
	public static ImageIcon alchemistBig2 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/alchemist_square2.png"));
	public static ImageIcon alchemistBig3 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/alchemist_square3.png"));

	public static ImageIcon alchemistSmall1 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/alchemist1_90x90.png"));
	public static ImageIcon alchemistSmall2 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/alchemist2_90x90.png"));
	public static ImageIcon alchemistSmall3 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/alchemist3_90x90.png"));
	public static ImageIcon alchemistSmall4 = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/alchemist4_90x90.png"));

	public static ImageIcon getImage(String image) {
		switch (image) {
		case "alchemist square 1": return alchemistBig;
		case "alchemist square 2": return alchemistBig2;
		case "alchemist square 3": return alchemistBig3;
		case "alchemist icon 1": return alchemistSmall1;
		case "alchemist icon 2": return alchemistSmall2;
		case "alchemist icon 3": return alchemistSmall3;
		case "alchemist icon 4": return alchemistSmall4;
		default : return alchemistSmall4;
		}
	}




}
