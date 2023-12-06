package userinterface;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;

import domain.GameController;
import domain.player.Player;

import javax.swing.JTable;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;

public class PlayerInventory extends JPanel {

	private static final long serialVersionUID = 1L;
	private int id; //There will be two of this view so there should be an id attribute to separate them
	private Player player;

	

	/**
	 * Create the panel.
	 */
	public PlayerInventory(int id) {
		this.id=id;
		
		
		player=GameController.getActivePlayers().get(id);
		setLayout(null);
		
		JScrollPane ingredientCardsScrollPanel = new JScrollPane();
		ingredientCardsScrollPanel.setBounds(47, 5, 365, 124);
		add(ingredientCardsScrollPanel);
		
		JPanel viewPort = new JPanel();
		ingredientCardsScrollPanel.setViewportView(viewPort);
		viewPort.setLayout(null);
		
		JLabel ingredientCardLabel = new JLabel("IngredientCard");
		ingredientCardsScrollPanel.setColumnHeaderView(ingredientCardLabel);
		
		JScrollPane artifactCardsScrollPanel = new JScrollPane();
		artifactCardsScrollPanel.setBounds(47, 158, 365, 124);
		add(artifactCardsScrollPanel);
		
		JPanel viewPort_1 = new JPanel();
		artifactCardsScrollPanel.setViewportView(viewPort_1);
		viewPort_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel artifactCardLabel = new JLabel("ArtifactCard");
		artifactCardsScrollPanel.setColumnHeaderView(artifactCardLabel);
		
		for(int i=0;i<player.getInventory().getPlayerIngredientCardList().size();i++) {
			
			ImageIcon icon = new ImageIcon(player.getInventory().getPlayerIngredientCardList().get(i).getName());
			JPanel panel = new JPanel();
			panel.setSize(50,70);
			panel.add(new JLabel(icon));
			viewPort.add(panel);
			
		}

//		for(int i=0;i<player.getInventory().getPlayerArtifactCardList().size();i++) {
//			
//			ImageIcon icon = new ImageIcon(player.getInventory().getPlayerArtifactCardList().get(i).getName());
//			JPanel panel = new JPanel();
//			panel.add(new JLabel(icon));
//			viewPort.add(panel);
//			
//		}

	}
}
