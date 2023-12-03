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

		new LoginSignUpWindow();
		//UserInfoSaver.addToTxtFile("Mina","79776", "userinfo.txt");



	}



}
