package userinterface;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import techServices.UserInfoSaver;
import userinterface.util.GlobalDimensions;
import userinterface.DeductionBoard;
public class Main {
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.add(new ResultsTriangle());
		frame.setVisible(true);
		frame.setSize(400, 400);
	}



}
