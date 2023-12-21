package userinterface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;

import domain.GameController;
import domain.player.Player;

import javax.swing.border.BevelBorder;

public class DeductionBoard extends JPanel {

	private JButton[][] buttons = new JButton[8][8]; //button[y][x]
	private int[][] buttonSelect;
	private Player player;

	public DeductionBoard() {

		player = GameController.getInstance().getCurrentPlayer();
		buttonSelect = player.getDeductionSelection();
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
						buttonSelect[thisButton.getY()/42][thisButton.getX()/88] = 1; //TODO idk if this is copying the player's array or editing the same obj

					}
					else {
						thisButton.setSelected(false);
						thisButton.setIcon(new ImageIcon(DeductionBoard.class.getResource("/userinterface/images/transparent_88x42.png")));
						buttonSelect[thisButton.getY()/42][thisButton.getX()/88] = -1;
					}
				}
						);
				//put the button item in the correlating index in matrix
				buttons[crossButton.getY()/42][crossButton.getX()/88] = crossButton;
				buttonsPanel.add(crossButton);
			}
		}


		JLabel deductionBoardLabel = new JLabel();
		deductionBoardLabel.setBounds(0, 0, 704, 341);
		deductionBoardLabel.setIcon(new ImageIcon(LoginSignUpWindow.class.getResource("/userinterface/images/deductionBoard_706x339.png")));
		buttonsPanel.add(deductionBoardLabel);

		this.add(buttonsPanel);
		this.setVisible(true);
	}

	public void updateDeductionBoard() {
		player = GameController.getInstance().getCurrentPlayer();
		buttonSelect = player.getDeductionSelection();

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				//If button is selected, make sure it's selected and icon is crossed
				if (buttonSelect[y][x] == 1) {
					buttons[y][x].setSelected(true);
					buttons[y][x].setIcon(new ImageIcon(DeductionBoard.class.getResource("/userinterface/images/cross_88x42.png")));
				}
				//if not, make sure it's not selected and icon is transparent
				else {
					buttons[y][x].setSelected(false);
					buttons[y][x].setIcon(new ImageIcon(DeductionBoard.class.getResource("/userinterface/images/transparent_88x42.png")));
				}
			}
		}
	}
}
