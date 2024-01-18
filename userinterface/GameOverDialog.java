package userinterface;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.GameController;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

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
		dialog.getContentPane().setLayout(new FlowLayout());
		dialog.getContentPane().setLayout(new FlowLayout());
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


		String endData = GameController.getInstance().gameOverMessage();

		JTextPane textPane = new JTextPane();
		textPane.setSize(new Dimension(550, 0));
		textPane.setEditable(false);
		textPane.setText(endData);
		content.add(textPane);
		/*
		JLabel messageLabel = new JLabel(endData);
		dialog.getContentPane().add(messageLabel);
		 */



		JButton closeButton = new JButton("Close Game");
		closeButton.addActionListener(e-> {
			frame.dispose();
		});


		JButton mainMenuButton = new JButton("Main Menu");
		mainMenuButton.addActionListener(e -> {
			//TODO remove all active users etc.
			//TODO to be implemented after updating log in sign up window
			frame.dispose();
		});

		dialog.getContentPane().add(mainMenuButton);
		dialog.getContentPane().add(closeButton);



		dialog.setVisible(true);
	}
}
