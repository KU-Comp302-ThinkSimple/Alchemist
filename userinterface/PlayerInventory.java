package userinterface;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;

import domain.GameController;
import domain.boards.BoardController;
import domain.cards.IngredientCard;
import domain.cards.artifactCards.ArtifactCard;
import domain.player.Player;
import userinterface.util.GlobalIcons;

import javax.swing.JTable;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class PlayerInventory extends JPanel {

	private static final long serialVersionUID = 1L;
	//private int id; //There will be two of this view so there should be an id attribute to separate them
	private Player player;
	private JPanel viewPort;
	private JPanel viewPort_1;


	/**
	 * Create the panel.
	 */
	public PlayerInventory() {
		//this.id=id;


		player = GameController.getCurrentPlayer();
		setLayout(null);

		JScrollPane ingredientCardsScrollPanel = new JScrollPane();
		ingredientCardsScrollPanel.setBounds(10, 5, 430, 142);
		add(ingredientCardsScrollPanel);

		viewPort = new JPanel();
		ingredientCardsScrollPanel.setViewportView(viewPort);
		viewPort.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel ingredientCardLabel = new JLabel("IngredientCard");
		ingredientCardsScrollPanel.setColumnHeaderView(ingredientCardLabel);

		JScrollPane artifactCardsScrollPanel = new JScrollPane();
		artifactCardsScrollPanel.setBounds(10, 158, 430, 124);
		add(artifactCardsScrollPanel);

		viewPort_1 = new JPanel();
		artifactCardsScrollPanel.setViewportView(viewPort_1);
		viewPort_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel artifactCardLabel = new JLabel("ArtifactCard");
		artifactCardsScrollPanel.setColumnHeaderView(artifactCardLabel);

		for(int i=0;i<player.getInventory().getPlayerIngredientCardList().size();i++) {

			ImageIcon icon = GlobalIcons.getCardImage(player.getInventory().getPlayerIngredientCardList().get(i).getName());
			JPanel panel = new JPanel();
			panel.setSize(100,130);
			panel.add(new JLabel(icon));
			viewPort.add(panel);

		}

		for(int i=0;i<player.getInventory().getPlayerArtifactCardList().size();i++) {
		
			ImageIcon icon = GlobalIcons.getCardImage(player.getInventory().getPlayerArtifactCardList().get(i).getName());
			JButton button = new JButton();
			button.add(new JLabel(icon));
			ArtifactCard currentCard=player.getInventory().getPlayerArtifactCardList().get(i);
			button.addActionListener(e -> {
				currentCard.useCard();
				System.out.println("Used card named "+ currentCard.getName());
				
			
			});
			
			viewPort_1.add(button);
				}

	}

	public void updatePlayerInventory() {
		viewPort.removeAll();
		viewPort_1.removeAll();
		player = GameController.getCurrentPlayer();

		for(int i=0;i<player.getInventory().getPlayerIngredientCardList().size();i++) {

			ImageIcon icon = GlobalIcons.getCardImage(player.getInventory().getPlayerIngredientCardList().get(i).getName());
			JPanel panel = new JPanel();
			panel.setSize(100,130);
			panel.add(new JLabel(icon));
			viewPort.add(panel);
		}
		
		
		for(int i=0;i<player.getInventory().getPlayerArtifactCardList().size();i++) {
			
			ImageIcon icon = GlobalIcons.getCardImage(player.getInventory().getPlayerArtifactCardList().get(i).getName());
			JButton button = new JButton();
			button.add(new JLabel(icon));
			ArtifactCard currentCard=player.getInventory().getPlayerArtifactCardList().get(i);
			button.addActionListener(e -> {
				currentCard.useCard();
				System.out.println("Used card named "+ currentCard.getName());
				if(currentCard.getName().equals("Elixir of Insight")) {
					ArrayList<IngredientCard> cards=  currentCard.useCard();
					String s="The top 3 cards are: ";
					for(int j=0;j<3;j++) {
						s+=j+". ";
						s+=(cards.get(j).getName());
					}
					JOptionPane.showInputDialog(s);
				}
				
				
			});
			viewPort_1.add(button);

		}

		//TODO same for artifact cards
	}
}
