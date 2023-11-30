package userinterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import techServices.UserInfoSaver;

public class Main {
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {

		//new LoginSignUpWindow();
		//UserInfoSaver.addToTxtFile("Mina","79776", "userinfo.txt");
		JFrame testFrame = new JFrame();

		/*
		JPanel buttonsPanel = new JPanel();
		for (int i=0 ; i < 8; i++) {

			JButton crossButton = new JButton();
			int x = 88;
			int y = (i-1) * 42;
			crossButton.setSize(88,42);
			//crossButton.setOpaque(false);
			crossButton.setContentAreaFilled(false);
			crossButton.setBorderPainted(true);
			crossButton.addActionListener(e -> {
				JButton thisButton = (JButton) e.getSource();
				if (!thisButton.isSelected()) {
					thisButton.setSelected(true);
					thisButton.setIcon(new ImageIcon(DeductionBoard.class.getResource("/userinterface/images/cross_88x42.png")));
				}
				else {
					thisButton.setSelected(false);
					thisButton.setIcon(null);
				}
			}
					);

			buttonsPanel.add(crossButton);
		}
		buttonsPanel.setLayout(new GridLayout());
		 */
		/*
		JPanel buttonsPanel = new JPanel();
		for (int j = 0; j < 8; j++) {
			for (int i=0 ; i < 8; i++) {

				JButton crossButton = new JButton();
				int x = i * 88;
				int y = j * 42;
				crossButton.setBounds(x, y, 88, 42);
				crossButton.setPreferredSize(new Dimension(88, 42));
				crossButton.setContentAreaFilled(false);
				crossButton.setBorderPainted(true);
				crossButton.addActionListener(e -> {
					JButton thisButton = (JButton) e.getSource();
					if (!thisButton.isSelected()) {
						thisButton.setSelected(true);
						thisButton.setIcon(new ImageIcon(DeductionBoard.class.getResource("/userinterface/images/cross_88x42.png")));
					}
					else {
						thisButton.setSelected(false);
						thisButton.setIcon(null);
					}
				}
						);

				buttonsPanel.add(crossButton);
			}
		}
		 */

		testFrame.add(new DeductionBoard());
		testFrame.setVisible(true);



	}



}
