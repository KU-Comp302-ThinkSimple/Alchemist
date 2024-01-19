package userinterface;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import domain.GameController;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class GameOverDialog extends JDialog {

	JDialog dialog;

	public GameOverDialog(JFrame frame) {

		dialog = new JDialog(frame, "Game Over!", true);
		JPanel content = new JPanel();
		dialog.setContentPane(content);
		dialog.setAlwaysOnTop(true);
		dialog.setLocation(new Point(800, 200));
		dialog.setPreferredSize(new Dimension(600, 300));
		dialog.setSize(new Dimension(600, 300));
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


		String endData = GameController.getInstance().gameOverMessage();
		GridBagLayout gbl_content = new GridBagLayout();
		gbl_content.columnWidths = new int[] {300, 300, 0};
		gbl_content.rowHeights = new int[] {210, 50, 0, 0};
		gbl_content.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_content.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		content.setLayout(gbl_content);


		JButton mainMenuButton = new JButton("Main Menu");
		mainMenuButton.addActionListener(e -> {
			//TODO remove all active users etc.
			//TODO to be implemented after updating log in sign up window
			frame.dispose();
			this.dispose();
		});

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		content.add(scrollPane, gbc_scrollPane);

		JTextArea textPane = new JTextArea();
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		textPane.setText(endData);
		textPane.setSize(new Dimension(600, 210));

		GridBagConstraints gbc_mainMenuButton = new GridBagConstraints();
		gbc_mainMenuButton.insets = new Insets(0, 0, 5, 5);
		gbc_mainMenuButton.gridx = 0;
		gbc_mainMenuButton.gridy = 1;
		dialog.getContentPane().add(mainMenuButton, gbc_mainMenuButton);



		JButton closeButton = new JButton("Close Game");
		closeButton.addActionListener(e-> {
			frame.dispose();
			this.dispose();
		});
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.insets = new Insets(0, 0, 5, 0);
		gbc_closeButton.gridx = 1;
		gbc_closeButton.gridy = 1;
		dialog.getContentPane().add(closeButton, gbc_closeButton);
		/*
		JLabel messageLabel = new JLabel(endData);
		dialog.getContentPane().add(messageLabel);
		 */



		dialog.setVisible(true);
	}
}
