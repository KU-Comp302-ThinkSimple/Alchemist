package userinterface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class DeductionBoard extends JPanel {

	public DeductionBoard() {

		this.setPreferredSize(new Dimension(704, 341));
		this.setLayout(null);

		JLabel deductionBoardLabel = new JLabel();
		deductionBoardLabel.setBounds(0, 0, 704, 341);
		deductionBoardLabel.setIcon(new ImageIcon(LoginSignUpWindow.class.getResource("/userinterface/images/deductionBoard_706x339.png")));
		this.add(deductionBoardLabel);


		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(0, 0, 704, 341);
		buttonsPanel.setLayout(null);
		//buttonsPanel.back
		//buttonsPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		//buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		//buttonsPanel.setLayout(new GridLayout(8, 8, 0, 0));

		for (int j = 0; j < 8; j++) {
			for (int i=0 ; i < 8; i++) {

				JButton crossButton = new JButton();
				crossButton.setOpaque(false);
				crossButton.setContentAreaFilled(false);
				crossButton.setBorderPainted(false);
				crossButton.setIcon(new ImageIcon(DeductionBoard.class.getResource("/userinterface/images/transparent_88x42.png")));
				int x = (i) * 88;
				int y = (j) * 42;
				crossButton.setBounds(x, y, 88, 42);
				crossButton.setPreferredSize(new Dimension(88, 42));



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


		//LayoutManager eightLayout = new GridLayout();
		//(GridLayout) eightLayout.setColumn(8);



		this.add(buttonsPanel);
		this.setVisible(true);



	}
}
