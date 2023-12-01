package userinterface;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import userinterface.util.GlobalDimensions;
import userinterface.util.GlobalFonts;
import javax.swing.SwingConstants;

public class PlayerTokenBoard extends JPanel {

	public PlayerTokenBoard() {

		this.setPreferredSize(GlobalDimensions.PLAYER_TOKEN_BOARD);
		this.setLayout(null);

		JLabel userimgLabel = new JLabel();
		userimgLabel.setBounds(10, 54, 114, 114);
		userimgLabel.setIcon(new ImageIcon(LoginSignUpWindow.class.getResource("/userinterface/images/alchemist_square_icon1_114x114.png")));
		this.add(userimgLabel);

		JLabel userNameLabel = new JLabel();
		userNameLabel.setText("Doremina"); //TODO put the username of the current player here
		userNameLabel.setFont(GlobalFonts.PLAYER_TOKEN);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setBounds(10, 10, 200, 50);
		this.add(userNameLabel);


		JPanel statsPanel = new JPanel();





	}
}
