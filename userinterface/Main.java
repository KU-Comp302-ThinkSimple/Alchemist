package userinterface;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import techServices.UserInfoSaver;
import userinterface.util.GlobalDimensions;

public class Main {
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {

		//new LoginSignUpWindow();
		//UserInfoSaver.addToTxtFile("Mina","79776", "userinfo.txt");
		JPanel brewpot=new BrewPotionPanel();
		JFrame brewpotFrame = new JFrame();
		brewpotFrame.setUndecorated(true);
		brewpotFrame.setMaximumSize(new Dimension(1920, 1080));
		brewpotFrame.setBounds(0, 0, 1920, 1080);
		//LoginSignUpWindowFrame.setExtendedState(JFrame.NORMAL);

	    brewpotFrame.setTitle("Welcome to Alchemists!");
		brewpotFrame.setResizable(false);
	    brewpotFrame.setSize(GlobalDimensions.FULL_SCREEN);
		brewpotFrame.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		brewpotFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		


		brewpotFrame.add(brewpot);
		brewpotFrame.setVisible(true);


	}



}
