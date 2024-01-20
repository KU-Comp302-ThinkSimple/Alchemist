package userinterface;

import domain.GameController;
import domain.LocalData;
import domain.cards.IngredientCard;
import domain.cards.artifactCards.ArtifactCard;
import domain.cards.artifactCards.ElixirOfInsight;
import domain.player.Player;
import userinterface.util.GlobalIcons;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class PlayerInventory extends JPanel {

	private static final long serialVersionUID = 1L;
	private Player player;
	private JPanel viewPort;
	private JPanel viewPort_1;



	public PlayerInventory(String online) {
		if (online.equals("online")) player = LocalData.getInstance().getLocalPlayer();
		else player = GameController.getInstance().getCurrentPlayer();
		ConstructorHelper();
	}

	private void ConstructorHelper() {

		this.setPreferredSize(new Dimension(450, 415));
		this.setMinimumSize(getPreferredSize());
		this.setMaximumSize(getPreferredSize());

//		player = GameController.getInstance().getCurrentPlayer();
		player = LocalData.getInstance().getLocalPlayer();
		setLayout(null);

		JScrollPane ingredientCardsScrollPanel = new JScrollPane();
		ingredientCardsScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		ingredientCardsScrollPanel.setBounds(10, 5, 430, 200);
		add(ingredientCardsScrollPanel);

		viewPort = new JPanel();
		viewPort.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		ingredientCardsScrollPanel.setViewportView(viewPort);
		viewPort.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel ingredientCardLabel = new JLabel("IngredientCard");
		ingredientCardsScrollPanel.setColumnHeaderView(ingredientCardLabel);

		JScrollPane artifactCardsScrollPanel = new JScrollPane();
		artifactCardsScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		artifactCardsScrollPanel.setBounds(10, 212, 430, 200);
		add(artifactCardsScrollPanel);

		viewPort_1 = new JPanel();
		viewPort_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		artifactCardsScrollPanel.setViewportView(viewPort_1);
		viewPort_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel artifactCardLabel = new JLabel("ArtifactCard");
		artifactCardsScrollPanel.setColumnHeaderView(artifactCardLabel);

		for(int i=0;i<player.getInventory().getPlayerIngredientCardList().size();i++) {

			ImageIcon icon = GlobalIcons.getIngredientCardImage(player.getInventory().getPlayerIngredientCardList().get(i).getName());
			JPanel panel = new JPanel();
			panel.setSize(100,130);
			panel.add(new JLabel(icon));
			viewPort.add(panel);

		}

		for(int i=0;i<player.getInventory().getPlayerArtifactCardList().size();i++) {

			ImageIcon icon = GlobalIcons.getArtifactCardImage(player.getInventory().getPlayerArtifactCardList().get(i).getName());
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

	//This func updtaes this component
	public void updatePlayerInventory(String online) {

		if (online.equals("online")) updatePlayerInventoryHelper();
		else {
			player = GameController.getInstance().getCurrentPlayer();
			updatePlayerInventoryHelper();
		}
	}

	private void updatePlayerInventoryHelper() {
		viewPort.removeAll();
		viewPort.repaint();
		viewPort_1.removeAll();
		viewPort_1.repaint();

		player = LocalData.getInstance().getLocalPlayer();

		for(int i=0;i<player.getInventory().getPlayerIngredientCardList().size();i++) {

			ImageIcon icon = GlobalIcons.getIngredientCardImage(player.getInventory().getPlayerIngredientCardList().get(i).getName());
			JPanel panel = new JPanel();
			panel.setSize(100,130);
			panel.add(new JLabel(icon));
			viewPort.add(panel);
		}


		for(int i=0;i<player.getInventory().getPlayerArtifactCardList().size();i++) {

			ImageIcon icon = GlobalIcons.getArtifactCardImage(player.getInventory().getPlayerArtifactCardList().get(i).getName());
			JButton button = new JButton();
			button.setContentAreaFilled(false);
			button.setIcon(icon);
			button.setPreferredSize(new Dimension(100, 160));
			button.setBorder(new EmptyBorder(0, 0, 0, 0));
			button.setCursor(new Cursor(Cursor.HAND_CURSOR));

			ArtifactCard currentCard=player.getInventory().getPlayerArtifactCardList().get(i);
			button.addActionListener(e -> {
				currentCard.useCard();
				System.out.println("Used card named "+ currentCard.getName());
				String message = "This artifact cards shall awaits its usage when its time comes.";

				if(currentCard.getName().equals("Elixir Of Insight")) {
					LocalData.getInstance().getLocalPlayer().getPlayerToken().addGold(-1);
					ArrayList<IngredientCard> cards= (ArrayList<IngredientCard>) currentCard.useCard();
					String s="The top 3 cards are: \n";
					for(int j=0;j<3;j++) {
						s+=(j+1)+".";
						s+=(cards.get(j).getName());
						s+="\n";
					}
					s+="Please write the new order of cards, separated by comma";

					JTextArea jta = new JTextArea(10, 30);
					jta.setText(s);
					jta.setEditable(false);
					JScrollPane jsp = new JScrollPane(jta);
					String order= JOptionPane.showInputDialog(null, jsp);

					try {
						String[] numString = order.split(",");
						int[] arr = new int[numString.length];
						for (int j = 0; j < numString.length; j++) {
							arr[j] = Integer.valueOf(numString[j]);
						}
						ElixirOfInsight elixCard= (ElixirOfInsight) currentCard;
						elixCard.changeCards(arr);


					}catch(Exception exc) {
						System.out.println("Something went wrong");
					}
				}
				else if (currentCard.getName().equals("Vaccine")){
					//TODO Create a pop up telling "This artifact cards shall awaits its usage when its time comes."
					JOptionPane. showMessageDialog(this, message);
				}
				else if (currentCard.getName().equals("Magic Mortar")){
					//TODO Create a pop up telling "This artifact cards shall awaits its usage when its time comes."
					JOptionPane. showMessageDialog(this, message);
				}
				else if (currentCard.getName().equals("Printing Press")){
					//TODO Create a pop up telling "This artifact cards shall awaits its usage when its time comes."
					JOptionPane. showMessageDialog(this, message);
				}
				else if (currentCard.getName().equals("Wisdom Idol")){
					//TODO Create a pop up telling "This artifact cards shall awaits its usage when its time comes."
					JOptionPane. showMessageDialog(this, message);
				}

				LocalData.getInstance().getLocalPlayer().getInventory().getPlayerArtifactCardList().remove(currentCard);
				LocalData.getInstance().getLocalPlayer().getPlayerToken().reducePlayerAction();
				
			});

			viewPort_1.add(button);


		}
	}

}