package userinterface;

import domain.GameController;
import domain.LocalData;
import domain.player.Player;
import userinterface.util.GlobalFonts;
import userinterface.util.GlobalIcons;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class PlayerTokenView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField goldField;
	private JTextField repField;
	private JTextField healthField;
	private JTextField actionField;
	private JTextField healthHeader;
	private JTextField goldHeader;
	private JTextField reputationHeader;
	private JTextField actionHeader;
	private ArrayList<JPanel> potPanels=new  ArrayList<JPanel>();
	//private int id; //There will be two of this view so there should be an id attribute to separate them
	private Player player;
	private Font panelFontHeader;
	private Font panelFontText;



	/**
	 * Create the panel.
	 */

	public PlayerTokenView(int i) {

		player = GameController.getInstance().getActivePlayers().get(i);
		if (player == null) {
			throw new RuntimeException("Active player i is null.");
		}
		panelFontHeader = GlobalFonts.PLAYER_TOKEN_HEADER;
		panelFontText = GlobalFonts.PLAYER_TOKEN_TEXT;

		setLayout(null);
		this.setPreferredSize(new Dimension(272, 187));

		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setBounds(16, 5, 95, 25);
		nameField.setColumns(10);
		nameField.setText(player.getPlayerName());
		nameField.setFont(panelFontText);
		add(nameField);

		healthField = new JTextField();
		healthField.setEditable(false);
		healthField.setBounds(222, 5, 32, 25);
		healthField.setColumns(10);
		healthField.setText(Integer.toString(player.getPlayerToken().getPlayerHealth()));
		healthField.setFont(panelFontText);
		add(healthField);

		goldField = new JTextField();
		goldField.setEditable(false);
		goldField.setBounds(222, 35, 32, 25);
		goldField.setColumns(10);
		goldField.setText(Integer.toString(player.getPlayerToken().getGold()));
		goldField.setFont(panelFontText);
		add(goldField);

		repField = new JTextField();
		repField.setEditable(false);
		repField.setBounds(222, 65, 32, 25);
		repField.setColumns(10);
		repField.setText(Integer.toString(player.getPlayerToken().getReputation()));
		repField.setFont(panelFontText);
		add(repField);

		actionField = new JTextField();
		actionField.setEditable(false);
		actionField.setColumns(10);
		actionField.setBounds(222, 95, 32, 25);
		actionField.setText(Integer.toString(player.getPlayerToken().getPlayerAction()));
		actionField.setFont(panelFontText);
		add(actionField);

		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(16, 30, 90, 90);
		add(imagePanel);

		//ImageIcon icon = new ImageIcon(player.getPlayerToken().getPlayerAvatar());
		int randint = (int) (Math.random() * 4) + 1 ;
		String iconname = "alchemist icon " + randint;
		ImageIcon icon = GlobalIcons.getImage(iconname);
		JLabel label = new JLabel(icon);
		label.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		imagePanel.add(label);


		int potx = 36;
		int poty = 60;

		JPanel potPanel0 = new JPanel();
		potPanel0.setBounds(5, 124, potx, poty);
		add(potPanel0);
		potPanels.add(potPanel0);

		JPanel potPanel1 = new JPanel();
		potPanel1.setBounds(43, 124, potx, poty);
		add(potPanel1);
		potPanels.add(potPanel1);

		JPanel potPanel2 = new JPanel();
		potPanel2.setBounds(81, 124, potx, poty);
		add(potPanel2);
		potPanels.add(potPanel2);

		JPanel potPanel3 = new JPanel();
		potPanel3.setBounds(119, 124, potx, poty);
		add(potPanel3);
		potPanels.add(potPanel3);

		JPanel potPanel4 = new JPanel();
		potPanel4.setBounds(157, 124, potx, poty);
		add(potPanel4);
		potPanels.add(potPanel4);

		JPanel potPanel5 = new JPanel();
		potPanel5.setBounds(195, 124, potx, poty);
		add(potPanel5);
		potPanels.add(potPanel5);

		JPanel potPanel6 = new JPanel();
		potPanel6.setBounds(233, 124, potx, poty);
		add(potPanel6);
		potPanels.add(potPanel6);

		ImageIcon unknownPot = GlobalIcons.getLittlePotionImage("Unknown");
		for (JPanel p : this.potPanels) {
			p.setLayout(null);
			JLabel iconLabel = new JLabel(icon);
			iconLabel.setBounds(0, 0, 40, 65);
			iconLabel.setIcon(unknownPot);
			p.add(iconLabel);
			p.setVisible(true);
		}


		healthHeader = new JTextField();
		healthHeader.setText("Health:");
		healthHeader.setEditable(false);
		healthHeader.setColumns(10);
		healthHeader.setBounds(116, 5, 105, 25);
		healthHeader.setFont(panelFontHeader);
		add(healthHeader);

		goldHeader = new JTextField();
		goldHeader.setText("Golds:");
		goldHeader.setEditable(false);
		goldHeader.setColumns(10);
		goldHeader.setBounds(116, 35, 105, 25);
		goldHeader.setFont(panelFontHeader);
		add(goldHeader);

		reputationHeader = new JTextField();
		reputationHeader.setText("Reputation:");
		reputationHeader.setEditable(false);
		reputationHeader.setColumns(10);
		reputationHeader.setBounds(116, 65, 105, 25);
		reputationHeader.setFont(panelFontHeader);
		add(reputationHeader);

		actionHeader = new JTextField();
		actionHeader.setText("Actions Left:");
		actionHeader.setEditable(false);
		actionHeader.setColumns(10);
		actionHeader.setBounds(116, 95, 105, 25);
		actionHeader.setFont(panelFontHeader);
		add(actionHeader);

		displayPlayerPotions();

	}

	public void displayPlayerPotions() {

		//Clear panels first
		for(int i=0;i<potPanels.size();i++) {
			potPanels.get(i).removeAll();
		}

		//ADD POTION IMAGES

		//If player has 2 different recipes for a potion, the same image won't be shown twice.
		ArrayList<String> addedPotions = new ArrayList<String>();

		for(int i=0;i<player.getInventory().getPlayerPotionList().size();i++) {

			String potname = player.getInventory().getPlayerPotionList().get(i).getPotionType().toString();
			ImageIcon icon = GlobalIcons.getLittlePotionImage(potname);

			//if icon is already displayed before don't display it again
			if (addedPotions.contains(potname)) continue;
			JLabel iconLabel = new JLabel(icon);
			iconLabel.setBounds(0, 0, 40, 65);
			potPanels.get(i).add(iconLabel);
			potPanels.get(i).repaint();

			//after the icon is added, add it to the ArrayList
			addedPotions.add(potname);
		}
	}


	public void updatePlayerTokenView() {
		healthField.setText(Integer.toString(player.getPlayerToken().getPlayerHealth()));
		healthField.repaint();
		goldField.setText(Integer.toString(player.getPlayerToken().getGold()));
		goldField.repaint();
		repField.setText(Integer.toString(player.getPlayerToken().getReputation()));
		repField.repaint();
		actionField.setText(Integer.toString(player.getPlayerToken().getPlayerAction()));
		actionField.repaint();
		displayPlayerPotions();
		this.repaint();
	}

}
