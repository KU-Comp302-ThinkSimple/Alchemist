package userinterface;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

		JPanel panel = new JPanel();
		ResultsTriangle resultsTriangle = new ResultsTriangle();

		JComboBox potionSelector1;
		JComboBox potionSelector2;
		JComboBox iconSelector;
		
		String[] potions = {"0","1","2","3","4","5","6","7"};
		String[] icons = {"rm", "gm", "bm", "rp", "gp", "bp", "empty"};
		JButton button = new JButton();
		potionSelector1 = new JComboBox(potions);
		potionSelector2 = new JComboBox(potions);
		iconSelector = new JComboBox(icons);
		potionSelector1.setMaximumSize(new Dimension(200, 40));
		potionSelector2.setMaximumSize(new Dimension(200, 40));
		iconSelector.setMaximumSize(new Dimension(200, 40));
		
		button.addActionListener(e -> {

			resultsTriangle.setCircleImage(potionSelector1.getSelectedIndex(), potionSelector2.getSelectedIndex(), iconSelector.getSelectedIndex());

		});
		
		panel.add(resultsTriangle);
		panel.add(potionSelector1);
		panel.add(potionSelector2);
		panel.add(iconSelector);
		panel.add(button);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(900, 700);
	}



}
