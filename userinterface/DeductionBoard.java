package userinterface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class DeductionBoard extends JPanel {

	public DeductionBoard() {

		this.setPreferredSize(new Dimension(704, 341));
		this.setLayout(null);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(0, 0, 704, 341);
		buttonsPanel.setLayout(null);


		for (int j = 0; j < 8; j++) {
			for (int i=0 ; i < 8; i++) {

				JButton crossButton = new JButton();

				crossButton.setIcon(new ImageIcon(DeductionBoard.class.getResource("/userinterface/images/transparent_88x42.png")));
				int x = (i) * 88;
				int y = (j) * 42;
				crossButton.setBounds(x, y, 88, 42);
				crossButton.setPreferredSize(new Dimension(88, 42));

				crossButton.setContentAreaFilled(false);
				crossButton.addActionListener(e -> {

					JButton thisButton = (JButton) e.getSource();
					if (!thisButton.isSelected()) {
						thisButton.setSelected(true);
						thisButton.setIcon(new ImageIcon(DeductionBoard.class.getResource("/userinterface/images/cross_88x42.png")));

					}
					else {
						thisButton.setSelected(false);
						thisButton.setIcon(new ImageIcon(DeductionBoard.class.getResource("/userinterface/images/transparent_88x42.png")));

					}
				}
						);

				buttonsPanel.add(crossButton);
			}
		}


		JLabel deductionBoardLabel = new JLabel();
		deductionBoardLabel.setBounds(0, 0, 704, 341);
		deductionBoardLabel.setIcon(new ImageIcon(LoginSignUpWindow.class.getResource("/userinterface/images/deductionBoard_706x339.png")));
		buttonsPanel.add(deductionBoardLabel);


		//LayoutManager eightLayout = new GridLayout();
		//(GridLayout) eightLayout.setColumn(8);



		this.add(buttonsPanel);
		this.setVisible(true);



	}
}
