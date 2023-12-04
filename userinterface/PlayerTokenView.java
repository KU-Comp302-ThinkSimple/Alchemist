package userinterface;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
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
import net.miginfocom.swing.MigLayout;

public class PlayerTokenView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField goldField;
	private JTextField repField;
	private JTextField healthField;
	private ArrayList<JPanel> potPanels=new  ArrayList<JPanel>();
	private int id; //There will be two of this view so there should be an id attribute to separate them
	private Player player;
	
	

	/**
	 * Create the panel.
	 */
	
	public PlayerTokenView(int id) {
		this.id=id;
		
		
		player=GameController.getActivePlayers().get(id);
		setLayout(null);
		
		nameField = new JTextField();
		nameField.setBounds(16, 16, 95, 26);
		add(nameField);
		nameField.setColumns(10);
		nameField.setText(player.getPlayerName());
		
		healthField = new JTextField();
		healthField.setBounds(227, 46, 95, 26);
		add(healthField);
		healthField.setColumns(10);
		healthField.setText(Integer.toString(player.getPlayerToken().getPlayerHealth()));
		
		goldField = new JTextField();
		goldField.setBounds(334, 46, 96, 26);
		add(goldField);
		goldField.setColumns(10);
		goldField.setText(Integer.toString(player.getPlayerToken().getGold()));
		
		repField = new JTextField();
		repField.setBounds(334, 76, 96, 26);
		add(repField);
		repField.setColumns(10);
		repField.setText(Integer.toString(player.getPlayerToken().getReputation()));
		
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
		
		displayPlayerPotions();

	}
	
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

	
	public void updatePlayerViewTokenController() {
		nameField.setText(player.getPlayerName());
		healthField.setText(Integer.toString(player.getPlayerToken().getPlayerHealth()));
		goldField.setText(Integer.toString(player.getPlayerToken().getGold()));
		repField.setText(Integer.toString(player.getPlayerToken().getReputation()));
		displayPlayerPotions();
	}
	

}
