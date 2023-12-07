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


public class PlayerTokenView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField goldField;
	private JTextField repField;
	private JTextField healthField;
	private ArrayList<JPanel> potPanels=new  ArrayList<JPanel>();
	//private int id; //There will be two of this view so there should be an id attribute to separate them
	private Player player;
	private JTextField actionField;
	private JTextField healthHeader;
	private JTextField goldHeader;
	private JTextField reputationHeader;
	private JTextField actionHeader;
	private Font panelFontHeader;
	private Font panelFontText;



	/**
	 * Create the panel.
	 */

	public PlayerTokenView() {

		player=GameController.getCurrentPlayer();
		panelFontHeader = GlobalFonts.PLAYER_TOKEN_HEADER;
		panelFontText = GlobalFonts.PLAYER_TOKEN_TEXT;

		setLayout(null);
		this.setPreferredSize(new Dimension(450, 300));

		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setBounds(16, 16, 95, 26);
		nameField.setColumns(10);
		nameField.setText(player.getPlayerName());
		nameField.setFont(panelFontText);
		add(nameField);

		healthField = new JTextField();
		healthField.setEditable(false);
		healthField.setBounds(335, 16, 95, 26);
		healthField.setColumns(10);
		healthField.setText(Integer.toString(player.getPlayerToken().getPlayerHealth()));
		healthField.setFont(panelFontText);
		add(healthField);

		goldField = new JTextField();
		goldField.setEditable(false);
		goldField.setBounds(334, 46, 96, 26);
		goldField.setColumns(10);
		goldField.setText(Integer.toString(player.getPlayerToken().getGold()));
		goldField.setFont(panelFontText);
		add(goldField);

		repField = new JTextField();
		repField.setEditable(false);
		repField.setBounds(334, 76, 96, 26);
		repField.setColumns(10);
		repField.setText(Integer.toString(player.getPlayerToken().getReputation()));
		repField.setFont(panelFontText);
		add(repField);

		actionField = new JTextField();
		actionField.setEditable(false);
		actionField.setColumns(10);
		actionField.setBounds(334, 109, 96, 26);
		actionField.setText(Integer.toString(player.getPlayerToken().getPlayerAction()));
		actionField.setFont(panelFontText);
		add(actionField);

		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(16, 46, 95, 89);
		add(imagePanel);

		ImageIcon icon = new ImageIcon(player.getPlayerToken().getPlayerAvatar());
		imagePanel.add(new JLabel(icon));

		JPanel potPanel0 = new JPanel();
		potPanel0.setBounds(16, 168, 95, 54);
		add(potPanel0);
		potPanels.add(potPanel0);

		JPanel potPanel1 = new JPanel();
		potPanel1.setBounds(119, 168, 96, 54);
		add(potPanel1);
		potPanels.add(potPanel1);

		JPanel potPanel2 = new JPanel();
		potPanel2.setBounds(227, 168, 95, 54);
		add(potPanel2);
		potPanels.add(potPanel2);

		JPanel potPanel3 = new JPanel();
		potPanel3.setBounds(334, 172, 96, 50);
		add(potPanel3);
		potPanels.add(potPanel3);

		JPanel potPanel4 = new JPanel();
		potPanel4.setBounds(16, 226, 95, 58);
		add(potPanel4);
		potPanels.add(potPanel4);

		JPanel potPanel5 = new JPanel();
		potPanel5.setBounds(119, 226, 96, 58);
		add(potPanel5);
		potPanels.add(potPanel5);

		JPanel potPanel6 = new JPanel();
		potPanel6.setBounds(227, 226, 95, 58);
		add(potPanel6);
		potPanels.add(potPanel6);

		JPanel potPanel7 = new JPanel();
		potPanel7.setBounds(334, 226, 96, 58);
		add(potPanel7);
		potPanels.add(potPanel7);

		healthHeader = new JTextField();
		healthHeader.setText("Health:");
		healthHeader.setEditable(false);
		healthHeader.setColumns(10);
		healthHeader.setBounds(201, 16, 124, 26);
		healthHeader.setFont(panelFontHeader);
		add(healthHeader);

		goldHeader = new JTextField();
		goldHeader.setText("Golds:");
		goldHeader.setEditable(false);
		goldHeader.setColumns(10);
		goldHeader.setBounds(201, 46, 124, 26);
		goldHeader.setFont(panelFontHeader);
		add(goldHeader);

		reputationHeader = new JTextField();
		reputationHeader.setText("Reputation:");
		reputationHeader.setEditable(false);
		reputationHeader.setColumns(10);
		reputationHeader.setBounds(201, 76, 124, 26);
		reputationHeader.setFont(panelFontHeader);
		add(reputationHeader);

		actionHeader = new JTextField();
		actionHeader.setText("Actions Left:");
		actionHeader.setEditable(false);
		actionHeader.setColumns(10);
		actionHeader.setBounds(201, 106, 124, 26);
		actionHeader.setFont(panelFontHeader);
		add(actionHeader);

		displayPlayerPotions();

	}
	//TODO potion images
	public void displayPlayerPotions() {

		for(int i=0;i<potPanels.size();i++) {
			potPanels.get(i).removeAll();
		}


		for(int i=0;i<player.getInventory().getPlayerPotionList().size();i++) {


			ImageIcon icon;

			if(player.getInventory().getPlayerPotionList().get(i).getNeutralityValue()==0) {

				icon = new ImageIcon("negative image");

			}else if (player.getInventory().getPlayerPotionList().get(i).getNeutralityValue()==1) {
				icon = new ImageIcon("positive image");

			}else {
				icon = new ImageIcon("neutral image");
			}

			potPanels.get(i).add(new JLabel(icon));
		}
	}


	public void updatePlayerTokenView() {
		nameField.setText(player.getPlayerName());
		healthField.setText(Integer.toString(player.getPlayerToken().getPlayerHealth()));
		goldField.setText(Integer.toString(player.getPlayerToken().getGold()));
		repField.setText(Integer.toString(player.getPlayerToken().getReputation()));
		displayPlayerPotions();
	}
}
