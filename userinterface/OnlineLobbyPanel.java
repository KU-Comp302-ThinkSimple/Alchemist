package userinterface;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import userinterface.util.GlobalColors;
import userinterface.util.GlobalFonts;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;

public class OnlineLobbyPanel extends JPanel {

	JLabel hostinfo;
	JTextField lobbyIDHeaderText;
	int playercount = 0;

	public OnlineLobbyPanel() {
		setPreferredSize(new Dimension(600, 300));

		this.setSize(600, 300);
		this.setBackground(GlobalColors.BACKGROUND_COLOR);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 300};
		gridBagLayout.rowHeights = new int[] {40, 40};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		setLayout(gridBagLayout);

		//LOBBY CODE HEADER
		lobbyIDHeaderText = new JTextField();
		lobbyIDHeaderText.setEditable(false);
		lobbyIDHeaderText.setBorder(null);
		lobbyIDHeaderText.setForeground(GlobalColors.TEXT_COLOR);
		lobbyIDHeaderText.setFont(GlobalFonts.DISPLAY_HEADER);
		lobbyIDHeaderText.setOpaque(false);
		lobbyIDHeaderText.setText("LOBBY ID: ");

		//TODO update text after log in
		//TODO altlarýndaki user nameler
		//TODO lobby id code text þeyi
		//TODO lokal player host ise start game butonu aktive olacak

		GridBagConstraints gbc_lobbyIDHeaderText = new GridBagConstraints();
		gbc_lobbyIDHeaderText.gridwidth = 2;
		gbc_lobbyIDHeaderText.anchor = GridBagConstraints.NORTHWEST;
		gbc_lobbyIDHeaderText.insets = new Insets(0, 0, 5, 5);
		gbc_lobbyIDHeaderText.gridx = 0;
		gbc_lobbyIDHeaderText.gridy = 0;
		this.add(lobbyIDHeaderText, gbc_lobbyIDHeaderText);

		//HOST HEADER
		JTextField hostHeaderText = new JTextField();
		hostHeaderText.setEditable(false);
		hostHeaderText.setBorder(null);
		hostHeaderText.setForeground(GlobalColors.TEXT_COLOR);
		hostHeaderText.setFont(GlobalFonts.DISPLAY_HEADER);
		hostHeaderText.setOpaque(false);
		hostHeaderText.setText("HOST");
		GridBagConstraints gbc_hostHeaderText = new GridBagConstraints();
		gbc_hostHeaderText.anchor = GridBagConstraints.NORTHWEST;
		gbc_hostHeaderText.insets = new Insets(0, 0, 0, 5);
		gbc_hostHeaderText.gridx = 0;
		gbc_hostHeaderText.gridy = 1;
		this.add(hostHeaderText, gbc_hostHeaderText);

		//HOST USER INFO
		hostinfo = new JLabel();
		hostinfo.setVisible(false);
		hostinfo.setForeground(GlobalColors.TEXT_COLOR);
		hostinfo.setFont(GlobalFonts.DISPLAY);
		hostinfo.setText("test");
		GridBagConstraints gbc_hostinfo = new GridBagConstraints();
		gbc_hostinfo.insets = new Insets(0, 0, 0, 5);
		gbc_hostinfo.anchor = GridBagConstraints.NORTHWEST;
		gbc_hostinfo.gridx = 1;
		gbc_hostinfo.gridy = 1;
		this.add(hostinfo, gbc_hostinfo);
	}

	public void setLobbyID(int id) {
		this.lobbyIDHeaderText.setText("Lobby ID: " + id );
		this.repaint();
	}

	public void setHost(String playerName) {
		hostinfo.setText(playerName);
		this.playercount += 1;
		this.repaint();
	}

	//if some player joins lobby
	public void addPlayer(String playerName) {
		//HEADER
		JTextField playerHeaderText = new JTextField();
		playerHeaderText.setEditable(false);
		playerHeaderText.setBorder(null);
		playerHeaderText.setForeground(GlobalColors.TEXT_COLOR);
		playerHeaderText.setFont(GlobalFonts.DISPLAY_HEADER);
		playerHeaderText.setOpaque(false);
		playerHeaderText.setText("Player " + (this.playercount + 1) );
		GridBagConstraints gbc_playerHeaderText = new GridBagConstraints();
		gbc_playerHeaderText.anchor = GridBagConstraints.NORTHWEST;
		gbc_playerHeaderText.insets = new Insets(0, 0, 0, 5);
		gbc_playerHeaderText.gridx = 0;
		gbc_playerHeaderText.gridy = 1;
		this.add(playerHeaderText, gbc_playerHeaderText);

		//USER INFO
		JLabel playerinfo = new JLabel();
		playerinfo.setVisible(false);
		playerinfo.setForeground(GlobalColors.TEXT_COLOR);
		playerinfo.setFont(GlobalFonts.DISPLAY);
		playerinfo.setText(playerName);
		GridBagConstraints gbc_playerinfo = new GridBagConstraints();
		gbc_playerinfo.insets = new Insets(0, 0, 0, 5);
		gbc_playerinfo.anchor = GridBagConstraints.NORTHWEST;
		gbc_playerinfo.gridx = 1;
		gbc_playerinfo.gridy = 1;
		this.add(playerinfo, gbc_playerinfo);

		this.playercount += 1;
		this.repaint();
	}

}
