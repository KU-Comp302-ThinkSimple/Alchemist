package userinterface;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.GameController;
import domain.boards.BoardController;
import domain.boards.IngredientBoard;
import domain.boards.PotionBrewingBoard;
import domain.cards.IngredientCard;
import exception.UserErrorException;
import test.TestGameInitializer;
import userinterface.util.GlobalColors;
import userinterface.util.GlobalDimensions;
import userinterface.util.GlobalFonts;
import userinterface.util.GlobalIcons;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.List;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class MainGameWindow {
	private static String infoText = """
Welcome to Alchemy Lab Game!

In this game, you take on the role of an alchemist conducting experiments in the lab. Your goal is to brew potions, contribute to publications, and form theories about ingredient properties. Here's a quick guide to get you started:

Game Phases:

First Round:
Forage for Ingredients: Draw ingredients from the deck.
Transmute Ingredient: Discard an ingredient for gold.
Buy Artifacts: Purchase artifacts with gold.
Make Experiments: Mix ingredients, test potions, and gain results.
Second Round:
Sell a Potion: Offer potions to adventurers for gold.
Publish a Theory: Share your knowledge about ingredients for reputation points.
Final Round:
Debunk or Endorse Theories: Prove or disprove published theories for reputation points.
Game Elements:

Player Tokens: Represent your unique avatar. Track your position, resources, and scores.
Ingredients: Various types with unique properties. Store them in the Ingredient Storage.
Potions: Brew potions with specific recipes and point values.
Publication Cards: Contribute to theories for reputation points.
Artifact Cards: Purchase for unique game advantages.
Alchemy Markers: Form theories on the Deduction Board.
Winning the Game:

Accumulate reputation points and gold.
Exchange leftover artifacts for gold.
Score one-third of a point for each gold piece.
The player with the most score points wins!
Good luck, alchemist! May your potions be potent and your theories groundbreaking!
""";

	private JPanel contentPane;
	private JPanel deductionBoard = new DeductionBoard();
	private JPanel resultsTriangle = new ResultsTriangle(1);
	private JPanel playerInventory = new PlayerInventory();
	private JPanel potionBrewingBoard = new BrewPotionPanel();
	private JPanel playerTokenView = new PlayerTokenView();
	private JPanel publishTheoryPanel = new PublishTheoryPanel();
	private JPanel debunkTheoryView = new DebunkTheoryView();
	private JComboBox transmuteIngredientComboBox;
	public static void main(String[] args) {
		TestGameInitializer.initializeTestGame();
		new MainGameWindow();
	}
	public MainGameWindow() {
		GameController.setMainGameWindow(this);
		JFrame MainGameWindowFrame = new JFrame();
		MainGameWindowFrame.setUndecorated(true);
		MainGameWindowFrame.setMaximumSize(new Dimension(1920, 1080));
		MainGameWindowFrame.setBounds(0, 0, 1920, 1080);
		//MainGameWindowFrame.setExtendedState(JFrame.NORMAL);

		MainGameWindowFrame.setTitle("Alchemists");
		MainGameWindowFrame.setResizable(false);
		MainGameWindowFrame.setSize(GlobalDimensions.FULL_SCREEN);
		MainGameWindowFrame.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		MainGameWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainGameWindowFrame.setBounds(0, 0, 1920, 1080);

		contentPane = new JPanel();
		contentPane.setBackground(GlobalColors.BACKGROUND_COLOR);
		contentPane.setSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setMinimumSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setMaximumSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		MainGameWindowFrame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton closeButton = new JButton("X");
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.setBorder(null);
		closeButton.setBackground(GlobalColors.BUTTON_COLOR);
		closeButton.setRequestFocusEnabled(false);
		closeButton.setFont(GlobalFonts.DISPLAY);
		closeButton.setBounds(10, 11, 60, 39);
		closeButton.addActionListener(e -> {
			MainGameWindowFrame.dispose();
		}
				);
		playerTokenView.setLocation(134, 89);
		//playerTokenView.setSize(playerTokenView.getPreferredSize().getSize());
		playerTokenView.setSize(325, 300); //TODO for testing purposes, dont show in windowbuilder otherwise

		contentPane.add(playerTokenView);
		contentPane.add(closeButton);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(134, 426, 325, 174);
		contentPane.add(buttonsPanel);
		buttonsPanel.setLayout(null);

		JPanel transmuteIngredientPanel = new JPanel();
		transmuteIngredientPanel.setBounds(0, 0, (buttonsPanel.getSize().width/3), buttonsPanel.getSize().height);
		buttonsPanel.add(transmuteIngredientPanel);
		transmuteIngredientPanel.setLayout(null);

		JButton transmuteIngredientButton = new JButton("<html>Transmute<br />Ingredient</html>");
		transmuteIngredientButton.setHorizontalTextPosition(SwingConstants.LEFT);
		transmuteIngredientButton.setVerticalAlignment(SwingConstants.BOTTOM);
		transmuteIngredientButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		transmuteIngredientButton.setFont(GlobalFonts.ACTION_BUTTON);
		transmuteIngredientButton.setBounds(0, 0, transmuteIngredientPanel.getWidth(), transmuteIngredientPanel.getHeight());
		transmuteIngredientButton.setContentAreaFilled(false); //TODO make it transparent


		//GONNA BE IMPLEMENTED AFTER UPDATE OF SOME FUNCTIONS (Ingr card name -> Ingr card item function)
		//get an array of ingredients of active player
		ArrayList<IngredientCard> ingredientsList = GameController.getCurrentPlayer().getInventory().getPlayerIngredientCardList();
		String[] ingrs = new String[ingredientsList.size()];
		for (int i = 0; i < ingredientsList.size(); i++) {
			ingrs[i] = ingredientsList.get(i).getName();
		}

		transmuteIngredientComboBox = new JComboBox(ingrs);
		transmuteIngredientComboBox.setBounds(0, 0, transmuteIngredientPanel.getWidth(), 22);


		//TODO action listener for transmute ingredient button
		transmuteIngredientButton.addActionListener(e -> {
			if (transmuteIngredientComboBox.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(MainGameWindowFrame, "Select an ingredient first to sell it for 1 gold.");
			}
			else {
				try{
					BoardController.transmuteIngredient((String)transmuteIngredientComboBox.getSelectedItem());
				}
				catch (UserErrorException a) {
					JOptionPane.showMessageDialog(MainGameWindowFrame, a.getMessage());
				}
			}

			//IngredientBoard.forageForIngredient();
		});

		transmuteIngredientPanel.add(transmuteIngredientButton);
		transmuteIngredientPanel.add(transmuteIngredientComboBox);

		JLabel transmuteIngredientLabel = new JLabel();
		transmuteIngredientLabel.setVerticalAlignment(SwingConstants.TOP);
		transmuteIngredientLabel.setBounds(transmuteIngredientPanel.getBounds());
		transmuteIngredientLabel.setIcon(new ImageIcon(MainGameWindow.class.getResource("/userinterface/images/flower_100x160.png")));
		transmuteIngredientPanel.add(transmuteIngredientLabel);

		JPanel buyArtifactPanel = new JPanel();
		buyArtifactPanel.setBounds(transmuteIngredientPanel.getX()+transmuteIngredientPanel.getWidth(), 0, buttonsPanel.getSize().width/3, buttonsPanel.getSize().height);
		buttonsPanel.add(buyArtifactPanel);
		buyArtifactPanel.setLayout(null);

		JButton buyArtifactButton = new JButton("<html>Buy<br />Artifact<br />Card</html>");
		buyArtifactButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		buyArtifactButton.setVerticalAlignment(SwingConstants.BOTTOM);
		buyArtifactButton.setFont(GlobalFonts.ACTION_BUTTON);
		buyArtifactButton.setContentAreaFilled(false);
		buyArtifactButton.setBounds(0, 0, buyArtifactPanel.getWidth(), buyArtifactPanel.getHeight());
		buyArtifactPanel.add(buyArtifactButton);

		JLabel buyArtifactLabel = new JLabel();
		buyArtifactLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		buyArtifactLabel.setBounds(0, 0, buyArtifactPanel.getWidth(), buyArtifactPanel.getHeight());
		buyArtifactPanel.add(buyArtifactLabel);

		JPanel forageForIngredientPanel = new JPanel();
		forageForIngredientPanel.setLayout(null);
		forageForIngredientPanel.setBounds(buyArtifactPanel.getX()+buyArtifactPanel.getWidth(), 0, buttonsPanel.getSize().width/3, buttonsPanel.getSize().height);
		buttonsPanel.add(forageForIngredientPanel);

		JButton forageForIngredientButton = new JButton("<html>Forage For<br />Ingredient</html>");
		forageForIngredientButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		forageForIngredientButton.setVerticalAlignment(SwingConstants.BOTTOM);
		forageForIngredientButton.setFont(GlobalFonts.ACTION_BUTTON);
		forageForIngredientButton.setContentAreaFilled(false);
		forageForIngredientButton.setBounds(0, 0, forageForIngredientPanel.getWidth(), forageForIngredientPanel.getHeight());
		forageForIngredientButton.addActionListener(e -> {
			try {
				BoardController.forageForIngredient();
			}
			catch (Exception a) {
				JOptionPane.showMessageDialog(MainGameWindowFrame, a.getMessage());
			}
		});
		forageForIngredientPanel.add(forageForIngredientButton);

		JLabel forageForIngredientLabel = new JLabel();
		forageForIngredientLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		forageForIngredientLabel.setBounds(0, 0, 105, 174);
		forageForIngredientLabel.setIcon(new ImageIcon(MainGameWindow.class.getResource("/userinterface/images/forageforing_100x160.png")));
		forageForIngredientPanel.add(forageForIngredientLabel);

		deductionBoard.setLocation(514, 740);
		deductionBoard.setSize(deductionBoard.getPreferredSize());
		contentPane.add(deductionBoard);


		resultsTriangle.setSize(resultsTriangle.getMaximumSize());
		resultsTriangle.setLocation(514, 98);
		contentPane.add(resultsTriangle);


		playerInventory.setBounds(1219, 89, 437, 300);
		contentPane.add(playerInventory);

		JButton infoButton = new JButton("i");
		infoButton.setRequestFocusEnabled(false);
		infoButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		infoButton.setBorder(null);
		infoButton.setBackground(SystemColor.controlHighlight);
		infoButton.setBounds(10, 61, 60, 39);
		infoButton.addActionListener(e -> {
			//TODO new information
			JOptionPane.showMessageDialog(MainGameWindowFrame, infoText);
		}
				);
		contentPane.add(infoButton);


		JButton pauseButton = new JButton("P");
		pauseButton.setRequestFocusEnabled(false);
		pauseButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		pauseButton.setBorder(null);
		pauseButton.setBackground(SystemColor.controlHighlight);
		pauseButton.setBounds(10, 111, 60, 39);
		pauseButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(MainGameWindowFrame, "Game paused. Close this window to continue.");
		});
		contentPane.add(pauseButton);

		potionBrewingBoard.setLocation(1219, 477);
		potionBrewingBoard.setSize(new Dimension(690, 555));
		contentPane.add(potionBrewingBoard);

		publishTheoryPanel.setLocation(134, 636);
		publishTheoryPanel.setSize(publishTheoryPanel.getPreferredSize());
		contentPane.add(publishTheoryPanel);
		
		debunkTheoryView.setLocation(134, 776);
		debunkTheoryView.setSize(debunkTheoryView.getPreferredSize());
		contentPane.add(debunkTheoryView);

		//TODO FOR TESTING PURPOSES
		JButton updateButton = new JButton("Update");
		updateButton.setBounds(80, 11, 89, 23);
		updateButton.addActionListener(e ->{
			updateMainGameWindow();

		});
		contentPane.add(updateButton);
		MainGameWindowFrame.setVisible(true);
	}
	public void updateMainGameWindow() {
		//Deduction Board Changer
		((DeductionBoard)deductionBoard).updateDeductionBoard();

		//Player Inventory Changer
		((PlayerInventory)playerInventory).updatePlayerInventory();

		//Results Triangle Changer
		((ResultsTriangle)resultsTriangle).updateResultsTriangle();

		//Transmute Ingredient ComboBoxChanger
		transmuteIngredientComboBox.removeAllItems();
		ArrayList<IngredientCard> ingredientsListt = GameController.getCurrentPlayer().getInventory().getPlayerIngredientCardList();
		String[] ingrss = new String[ingredientsListt.size()];
		for (int i = 0; i < ingredientsListt.size(); i++) {
			ingrss[i] = ingredientsListt.get(i).getName();
			transmuteIngredientComboBox.addItem(ingredientsListt.get(i).getName());
		}

		//Player Token View Changer
		((PlayerTokenView)playerTokenView).updatePlayerTokenView();
		
		//Publish theory update
		((PublishTheoryPanel)publishTheoryPanel).updatePublishTheoryPanel();
		
		//Debunk theory update
		((DebunkTheoryView)debunkTheoryView).updateDebunkTheoryPanel();
	}
}
