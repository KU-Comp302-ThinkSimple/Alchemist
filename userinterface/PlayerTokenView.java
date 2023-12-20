package userinterface;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTextField;
import javax.swing.JSplitPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import domain.GameController;
import domain.player.Player;
import domain.potion.Potion;
import userinterface.util.GlobalFonts;
import userinterface.util.GlobalIcons;


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

	public PlayerTokenView() {

		player=GameController.getInstance().getCurrentPlayer();
		panelFontHeader = GlobalFonts.PLAYER_TOKEN_HEADER;
		panelFontText = GlobalFonts.PLAYER_TOKEN_TEXT;

		setLayout(null);
		this.setPreferredSize(new Dimension(325, 300));

		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setBounds(16, 16, 95, 26);
		nameField.setColumns(10);
		nameField.setText(player.getPlayerName());
		nameField.setFont(panelFontText);
		add(nameField);

		healthField = new JTextField();
		healthField.setEditable(false);
		healthField.setBounds(250, 16, 53, 26);
		healthField.setColumns(10);
		healthField.setText(Integer.toString(player.getPlayerToken().getPlayerHealth()));
		healthField.setFont(panelFontText);
		add(healthField);

		goldField = new JTextField();
		goldField.setEditable(false);
		goldField.setBounds(249, 46, 54, 26);
		goldField.setColumns(10);
		goldField.setText(Integer.toString(player.getPlayerToken().getGold()));
		goldField.setFont(panelFontText);
		add(goldField);

		repField = new JTextField();
		repField.setEditable(false);
		repField.setBounds(249, 76, 54, 26);
		repField.setColumns(10);
		repField.setText(Integer.toString(player.getPlayerToken().getReputation()));
		repField.setFont(panelFontText);
		add(repField);

		actionField = new JTextField();
		actionField.setEditable(false);
		actionField.setColumns(10);
		actionField.setBounds(249, 109, 54, 26);
		actionField.setText(Integer.toString(player.getPlayerToken().getPlayerAction()));
		actionField.setFont(panelFontText);
		add(actionField);

		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(16, 46, 95, 89);
		add(imagePanel);

		ImageIcon icon = new ImageIcon(player.getPlayerToken().getPlayerAvatar());
		imagePanel.add(new JLabel(icon));

		JPanel potPanel0 = new JPanel();
		potPanel0.setBounds(16, 146, 40, 65);
		add(potPanel0);
		potPanels.add(potPanel0);

		JPanel potPanel1 = new JPanel();
		potPanel1.setBounds(66, 146, 40, 65);
		add(potPanel1);
		potPanels.add(potPanel1);

		JPanel potPanel2 = new JPanel();
		potPanel2.setBounds(116, 146, 40, 65);
		add(potPanel2);
		potPanels.add(potPanel2);

		JPanel potPanel3 = new JPanel();
		potPanel3.setBounds(166, 146, 40, 65);
		add(potPanel3);
		potPanels.add(potPanel3);

		JPanel potPanel4 = new JPanel();
		potPanel4.setBounds(16, 216, 40, 65);
		add(potPanel4);
		potPanels.add(potPanel4);

		JPanel potPanel5 = new JPanel();
		potPanel5.setBounds(66, 216, 40, 65);
		add(potPanel5);
		potPanels.add(potPanel5);

		JPanel potPanel6 = new JPanel();
		potPanel6.setBounds(116, 216, 40, 65);
		add(potPanel6);
		potPanels.add(potPanel6);

		JPanel potPanel7 = new JPanel();
		potPanel7.setBounds(166, 216, 40, 65);
		add(potPanel7);
		potPanels.add(potPanel7);

		healthHeader = new JTextField();
		healthHeader.setText("Health:");
		healthHeader.setEditable(false);
		healthHeader.setColumns(10);
		healthHeader.setBounds(116, 16, 124, 26);
		healthHeader.setFont(panelFontHeader);
		add(healthHeader);

		goldHeader = new JTextField();
		goldHeader.setText("Golds:");
		goldHeader.setEditable(false);
		goldHeader.setColumns(10);
		goldHeader.setBounds(116, 46, 124, 26);
		goldHeader.setFont(panelFontHeader);
		add(goldHeader);

		reputationHeader = new JTextField();
		reputationHeader.setText("Reputation:");
		reputationHeader.setEditable(false);
		reputationHeader.setColumns(10);
		reputationHeader.setBounds(116, 76, 124, 26);
		reputationHeader.setFont(panelFontHeader);
		add(reputationHeader);

		actionHeader = new JTextField();
		actionHeader.setText("Actions Left:");
		actionHeader.setEditable(false);
		actionHeader.setColumns(10);
		actionHeader.setBounds(116, 106, 124, 26);
		actionHeader.setFont(panelFontHeader);
		add(actionHeader);

		displayPlayerPotions();

	}
	//TODO potion images
	public void displayPlayerPotions() {

		//Clear panels first
		for(int i=0;i<potPanels.size();i++) {
			potPanels.get(i).removeAll();
		}

		//ADD POTION IMAGES

		//If player has 2 different recipes for a potion, the same image won't be shown twice.
		ArrayList<String> addedPotions = new ArrayList<String>();

		for(int i=0;i<player.getInventory().getPlayerPotionList().size();i++) {

			String potname = player.getInventory().getPlayerPotionList().get(i).getPotionType();
			ImageIcon icon = GlobalIcons.getLittlePotionImage(potname);

			//if icon is already displayed before don't display it again
			if (addedPotions.contains(potname)) continue;
			JLabel iconLabel = new JLabel(icon);
			iconLabel.setBounds(0, 0, 40, 65);
			potPanels.get(i).add(iconLabel);

			//after the icon is added, add it to the ArrayList
			addedPotions.add(potname);
		}
	}


	public void updatePlayerTokenView() {
		player = GameController.getInstance().getCurrentPlayer();
		nameField.setText(player.getPlayerName());
		healthField.setText(Integer.toString(player.getPlayerToken().getPlayerHealth()));
		goldField.setText(Integer.toString(player.getPlayerToken().getGold()));
		repField.setText(Integer.toString(player.getPlayerToken().getReputation()));
		actionField.setText(Integer.toString(player.getPlayerToken().getPlayerAction()));
		displayPlayerPotions();
	}
}
