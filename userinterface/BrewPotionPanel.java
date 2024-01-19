package userinterface;

import domain.GameController;
import domain.boards.BoardController;
import domain.cards.IngredientCard;
import domain.player.Player;
import userinterface.util.GlobalColors;
import userinterface.util.GlobalFonts;
import userinterface.util.GlobalIcons;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;



public class BrewPotionPanel extends JPanel {


	public BrewPotionPanel(){
		setMinimumSize(new Dimension(690, 555));
		setMaximumSize(new Dimension(690, 555));
		setPreferredSize(new Dimension(610, 437));
		setBounds(new Rectangle(0, 0, 690, 555));

		setBackground(GlobalColors.BACKGROUND_COLOR);
		//setOpaque(false);
		setAutoscrolls(true);
		setLayout(null);
		setSize(691, 604);


		//CARD IMAGES AND SELECTION TOOL
		JLabel card1Label = new JLabel(GlobalIcons.getIngredientCardImage("Unknown"));
		card1Label.setBounds(55, 0, 100, 160);
		add(card1Label);

		JLabel card2Label = new JLabel(GlobalIcons.getIngredientCardImage("Unknown"));
		card2Label.setBounds(255, 0, 100, 160);
		add(card2Label);

		String[] ingredientNames = {"Mushroom", "Seedling", "Frog", "Bird Claw", "Flower", "Mandrake Root", "Scorpion", "Raven's Feather"};

		JComboBox ingrSelect1 = new JComboBox(ingredientNames);
		ingrSelect1.setBounds(5, 165, 190, 20);
		ingrSelect1.addActionListener(e -> {
			card1Label.setIcon(GlobalIcons.getIngredientCardImage((String)ingrSelect1.getSelectedItem()));
		});
		add(ingrSelect1);

		JComboBox ingrSelect2 = new JComboBox(ingredientNames);
		ingrSelect2.setBounds(210, 165, 190, 20);
		ingrSelect2.addActionListener(e -> {
			card2Label.setIcon(GlobalIcons.getIngredientCardImage((String)ingrSelect2.getSelectedItem()));
		});
		add(ingrSelect2);


		//DRINK POTION PANEL
		JPanel drinkPotionPanel = new JPanel();
		drinkPotionPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		drinkPotionPanel.setBounds(5, 190, 200, 240);
		drinkPotionPanel.setOpaque(false);
		add(drinkPotionPanel);
		drinkPotionPanel.setLayout(null);

		//DRINK POTION BUTTON
		JButton drinkPotionButton = new JButton("Drink Potion");
		drinkPotionButton.setBounds(25, 195, 150, 45);
		drinkPotionButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		drinkPotionButton.setBackground(GlobalColors.BUTTON_COLOR);
		drinkPotionButton.setFont(GlobalFonts.BREW_BUTTON);
		drinkPotionButton.addActionListener(e -> {
			try {
				Player currPlayer = GameController.getInstance().getCurrentPlayer();
				String message = BoardController.brewPotion(((String)ingrSelect1.getSelectedItem()), ((String)ingrSelect2.getSelectedItem()), false).toString().toLowerCase();
				JOptionPane.showMessageDialog(this, "You brew: " + message);
				if (currPlayer.getInventory().getPlayerArtifactCardList().contains(GameController.getInstance().getGameInventory().getArtCards().get(3))){
					IngredientCard ingr1 = null;
					IngredientCard ingr2 = null;
					for (IngredientCard ingredient : GameController.getInstance().getGameInventory().getIngredientCards()){
						if (ingredient.getName().equals((String)ingrSelect1.getSelectedItem())) {
							ingr1 = ingredient;}
						if (ingredient.getName().equals((String)ingrSelect2.getSelectedItem())) {
							ingr2 = ingredient;}
					}
					
					int result = JOptionPane.showConfirmDialog(null, "Do you want to use the card?");
					if(result == JOptionPane.YES_OPTION ){
						 String[] options = { "Ingredient 1", "Ingredient 2" };
						 var selection = JOptionPane.showOptionDialog(null, "Select one:", "", 
                                0, 2, null, options, options[0]);
						 	if (selection == 0) {
						 		GameController.getInstance().getCurrentPlayer().getInventory().addAIngredientCard(ingr1);
						    	GameController.getInstance().getCurrentPlayer().getInventory().removeArtifactCard(GameController.getInstance().getGameInventory().getArtCards().get(3));

						    }
						    if (selection == 1) { 
						    	GameController.getInstance().getCurrentPlayer().getInventory().addAIngredientCard(ingr2);
						    	GameController.getInstance().getCurrentPlayer().getInventory().removeArtifactCard(GameController.getInstance().getGameInventory().getArtCards().get(3));
						    }
					} 
				}
			}
			catch (Exception error) {
				JOptionPane.showMessageDialog(this, error.getMessage());
			}
		});
		drinkPotionPanel.add(drinkPotionButton);

		//DRINK POTION LABEL
		JLabel drinkPotionLabel = new JLabel(GlobalIcons.getButtonImage("drink potion small"));
		drinkPotionLabel.setBounds(0, 0, 200, 200);
		drinkPotionPanel.add(drinkPotionLabel);


		//TEST ON STUDENT PANEL
		JPanel testOnStudentPanel = new JPanel();
		testOnStudentPanel.setBounds(205, 190, 200, 240);
		testOnStudentPanel.setBorder(new EmptyBorder(0,0,0,0));
		testOnStudentPanel.setOpaque(false);
		add(testOnStudentPanel);
		testOnStudentPanel.setLayout(null);

		//TEST ON STUDENT BUTTON
		JButton testOnStudentButton = new JButton("Test on Student");
		testOnStudentButton.setBounds(15, 195, 170, 46);
		testOnStudentButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		testOnStudentButton.setBackground(GlobalColors.BUTTON_COLOR);
		testOnStudentButton.setFont(GlobalFonts.BREW_BUTTON);
		testOnStudentButton.addActionListener(e -> {
			try {
				Player currPlayer = GameController.getInstance().getCurrentPlayer();
				String message = BoardController.brewPotion(((String)ingrSelect1.getSelectedItem()), ((String)ingrSelect2.getSelectedItem()), true).toString().toLowerCase();
				JOptionPane.showMessageDialog(this, "You brew: " + message);
				if (currPlayer.getInventory().getPlayerArtifactCardList().contains(GameController.getInstance().getGameInventory().getArtCards().get(3))){
					IngredientCard ingr1 = null;
					IngredientCard ingr2 = null;
					for (IngredientCard ingredient : GameController.getInstance().getGameInventory().getIngredientCards()){
						if (ingredient.getName().equals((String)ingrSelect1.getSelectedItem())) {
							ingr1 = ingredient;}
						if (ingredient.getName().equals((String)ingrSelect2.getSelectedItem())) {
							ingr2 = ingredient;}
					}

					int result = JOptionPane.showConfirmDialog(null, "Do you want to use the card?");
					if(result == JOptionPane.YES_OPTION ){
						String[] options = { "Ingredient 1", "Ingredient 2" };
						var selection = JOptionPane.showOptionDialog(null, "Select one:", "",
								0, 2, null, options, options[0]);
						if (selection == 0) {
							GameController.getInstance().getCurrentPlayer().getInventory().addAIngredientCard(ingr1);
							GameController.getInstance().getCurrentPlayer().getInventory().removeArtifactCard(GameController.getInstance().getGameInventory().getArtCards().get(3));

						}
						if (selection == 1) {
							GameController.getInstance().getCurrentPlayer().getInventory().addAIngredientCard(ingr2);
							GameController.getInstance().getCurrentPlayer().getInventory().removeArtifactCard(GameController.getInstance().getGameInventory().getArtCards().get(3));
						}
					}
				}

			}
			catch (Exception error) {
				JOptionPane.showMessageDialog(this, error.getMessage());
			}
		});
		testOnStudentPanel.add(testOnStudentButton);

		//TEST ON STUDENT LABEL
		JLabel testOnStudentLabel=new JLabel(GlobalIcons.getButtonImage("test on student small"));
		testOnStudentLabel.setBounds(0, 0, 200, 200);
		testOnStudentPanel.add(testOnStudentLabel);

		String[] guarantees = {"No guarantee", "Positive or Neutral", "Positive"};
		JComboBox guaranteeComboBox = new JComboBox(guarantees);
		guaranteeComboBox.setBounds(415, 165, 190, 20);
		add(guaranteeComboBox);

		//SELL A POTION PANEL
		JPanel sellAPotionPanel = new JPanel();
		sellAPotionPanel.setBounds(405, 190, 200, 240);
		sellAPotionPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		sellAPotionPanel.setOpaque(false);
		add(sellAPotionPanel);
		sellAPotionPanel.setLayout(null);


		//SELL A POTION BUTTON
		JButton sellAPotionButton = new JButton("Sell a potion");
		sellAPotionButton.setBounds(25, 195, 150, 45);
		sellAPotionPanel.add(sellAPotionButton);
		sellAPotionButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		sellAPotionButton.setBackground(GlobalColors.BUTTON_COLOR);
		sellAPotionButton.setFont(GlobalFonts.BREW_BUTTON);
		sellAPotionButton.addActionListener(e -> {
			try {
				String message = BoardController.sellPotion(((String)ingrSelect1.getSelectedItem()), ((String)ingrSelect2.getSelectedItem()), guaranteeComboBox.getSelectedIndex());
				JOptionPane.showMessageDialog(this, message);
			}
			catch (Exception error) {
				JOptionPane.showMessageDialog(this, error.getMessage());
			}
		});

		//SELL A POTION LABEL
		JLabel sellAPotionLabel=new JLabel(GlobalIcons.getButtonImage("sell a potion small"));
		sellAPotionLabel.setBounds(0, 0, 200, 200);
		sellAPotionPanel.add(sellAPotionLabel);


		//POTION LABEL
		JLabel potionLabel=new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/unknownPotion_103x151.png")));
		potionLabel.setBounds(461, 0, 103, 151);
		add(potionLabel);




	}
}














