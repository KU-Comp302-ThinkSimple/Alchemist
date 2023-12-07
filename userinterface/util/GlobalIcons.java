package userinterface.util;

import javax.swing.ImageIcon;

import userinterface.BrewPotionPanel;

public class GlobalIcons {

	//CARDS
	public static ImageIcon flower = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/flower_100x160.png"));
	public static ImageIcon birdclaw = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/birdclaw_100x160.png"));
	public static ImageIcon feather = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/feather_100x160.png"));
	public static ImageIcon frog = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/frog_100x160.png"));
	public static ImageIcon seedling = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/seedling_100x160.png"));
	public static ImageIcon mandrakeroot = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/mandrakeroot_100x160.png"));
	public static ImageIcon mushroom = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/mushroom_100x160.png"));
	public static ImageIcon scorpion = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/scorpion_100x160.png"));
	public static ImageIcon unknowncard = new ImageIcon(GlobalIcons.class.getResource("/userinterface/images/unknownCard_100x160.png"));

	public static ImageIcon getCardImage(String name) {
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


}
