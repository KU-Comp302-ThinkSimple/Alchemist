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


}
