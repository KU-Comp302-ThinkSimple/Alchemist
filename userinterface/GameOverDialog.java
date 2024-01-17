package userinterface;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.GameController;

public class GameOverDialog extends JDialog {

	JDialog dialog;

	public GameOverDialog(JFrame frame) {

		dialog = new JDialog(frame, "Game Over!");
		dialog.setLayout(new FlowLayout());
		dialog.setResizable(false);

		String endData = GameController.getInstance().gameOverMessage();
		JLabel messageLabel = new JLabel(endData);
		dialog.getContentPane().add(messageLabel);

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
	}
}
