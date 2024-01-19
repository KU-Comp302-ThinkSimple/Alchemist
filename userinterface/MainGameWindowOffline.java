package userinterface;

import domain.GameController;
import domain.boards.BoardController;
import domain.cards.IngredientCard;
import domain.player.Player;
import exception.UserErrorException;
import test.TestGameInitializer;
import userinterface.observer.MainObserver;
import userinterface.util.GlobalColors;
import userinterface.util.GlobalDimensions;
import userinterface.util.GlobalFonts;
import userinterface.util.GlobalIcons;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MainGameWindowOffline extends JFrame{

	private JPanel contentPane;
	private JPanel deductionBoard = new DeductionBoard();
	private JPanel resultsTriangle = new ResultsTriangle(1);
	private JPanel playerInventory = new PlayerInventory("offline");
	private JPanel potionBrewingBoard = new BrewPotionPanel();
	private JPanel playerTokenView = new PlayerTokenView(0);//TODO add ptw 2
	private JPanel publishTheoryPanel = new PublishTheoryPanel();
	private JPanel debunkTheoryView = new DebunkTheoryView();
	private JComboBox transmuteIngredientComboBox;
	private JLabel roundLabel;
	private JLabel turnLabel;
	private ArrayList<PlayerTokenView> playerTokens = new ArrayList<PlayerTokenView>();

	public static void main(String[] args) {
		TestGameInitializer.initializeTestGame();
		new MainGameWindowOffline();
	}
	public MainGameWindowOffline() {
		//		GameController.setMainGameWindow(this);

		this.setUndecorated(true);
		this.setMaximumSize(new Dimension(1920, 1080));
		this.setBounds(0, 0, 1920, 1080);
		//this.setExtendedState(JFrame.NORMAL);

		this.setTitle("Alchemists");
		this.setResizable(false);
		this.setSize(GlobalDimensions.FULL_SCREEN);
		this.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 1920, 1080);

		contentPane = new JPanel();
		contentPane.setBackground(GlobalColors.BACKGROUND_COLOR);
		contentPane.setSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setMinimumSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setMaximumSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton closeButton = new JButton("X");
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.setBorder(null);
		closeButton.setBackground(GlobalColors.BUTTON_COLOR);
		closeButton.setRequestFocusEnabled(false);
		closeButton.setFont(GlobalFonts.DISPLAY);
		closeButton.setBounds(10, 11, 60, 39);
		closeButton.addActionListener(e -> {
			this.dispose();
		}
				);
		contentPane.add(closeButton);

		//PLAYER TOKENS
		int playercount = GameController.getInstance().getActivePlayers().size();
		int xalign = 161;
		int yalign = 21;
		for (int i = 0; i < playercount; i++) {

			PlayerTokenView ptw = new PlayerTokenView(i);
			ptw.setSize(272, 187);

			if ( i != 3) {
				ptw.setLocation(xalign, yalign);
				yalign += 198;
			}
			else ptw.setLocation(1484, 11);

			getContentPane().add(ptw);
			this.playerTokens.add(ptw);

		}



		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(142, 620, 325, 174);
		contentPane.add(buttonsPanel);
		buttonsPanel.setLayout(null);


		JPanel transmuteIngredientPanel = new JPanel();
		transmuteIngredientPanel.setBounds(0, 0, (buttonsPanel.getSize().width/3), buttonsPanel.getSize().height);
		buttonsPanel.add(transmuteIngredientPanel);
		transmuteIngredientPanel.setLayout(null);

		JLabel transmuteIngredientLabel = new JLabel();
		transmuteIngredientLabel.setVerticalAlignment(SwingConstants.TOP);
		transmuteIngredientLabel.setBounds(transmuteIngredientPanel.getBounds());
		transmuteIngredientLabel.setIcon(GlobalIcons.getIngredientCardImage("Unknown"));

		JButton transmuteIngredientButton = new JButton("<html>Transmute<br />Ingredient</html>");
		transmuteIngredientButton.setHorizontalTextPosition(SwingConstants.LEFT);
		transmuteIngredientButton.setVerticalAlignment(SwingConstants.BOTTOM);
		transmuteIngredientButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		transmuteIngredientButton.setFont(GlobalFonts.ACTION_BUTTON);
		transmuteIngredientButton.setBounds(0, 22, 108, 152);
		transmuteIngredientButton.setContentAreaFilled(false); //TODO make it transparent


		//get an array of ingredients of active player
		ArrayList<IngredientCard> ingredientsList = GameController.getInstance().getCurrentPlayer().getInventory().getPlayerIngredientCardList();
		String[] ingrs = new String[ingredientsList.size()];
		for (int i = 0; i < ingredientsList.size(); i++) {
			ingrs[i] = ingredientsList.get(i).getName();
		}

		transmuteIngredientComboBox = new JComboBox(ingrs);
		transmuteIngredientComboBox.setBounds(0, 0, transmuteIngredientPanel.getWidth(), 22);
		transmuteIngredientComboBox.addActionListener(e -> {
			if (transmuteIngredientComboBox.getSelectedItem() != null) {
				String selected = (String)transmuteIngredientComboBox.getSelectedItem();
				transmuteIngredientLabel.setIcon(GlobalIcons.getIngredientCardImage(selected));
			}
		});


		//TODO action listener for transmute ingredient button
		transmuteIngredientButton.addActionListener(e -> {
			if (transmuteIngredientComboBox.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Select an ingredient first to sell it for 1 gold.");
			}
			else {
				try{
					BoardController.transmuteIngredient((String)transmuteIngredientComboBox.getSelectedItem());
				}
				catch (UserErrorException a) {
					JOptionPane.showMessageDialog(this, a.getMessage());
				}
			}
			//IngredientBoard.forageForIngredient();
		});

		transmuteIngredientPanel.add(transmuteIngredientComboBox);
		transmuteIngredientPanel.add(transmuteIngredientButton);
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

		buyArtifactButton.addActionListener(e -> {

			BoardController.buyArtifactCard();
			updateMainGameWindow();
		});

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
				JOptionPane.showMessageDialog(this, a.getMessage());
			}
		});
		forageForIngredientPanel.add(forageForIngredientButton);

		JLabel forageForIngredientLabel = new JLabel();
		forageForIngredientLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		forageForIngredientLabel.setBounds(0, 0, 105, 174);
		forageForIngredientLabel.setIcon(new ImageIcon(MainGameWindowOffline.class.getResource("/userinterface/images/forageforing_100x160.png")));
		forageForIngredientPanel.add(forageForIngredientLabel);


		//DEDUCTION BOARD
		deductionBoard.setLocation(571, 703);
		deductionBoard.setSize(deductionBoard.getPreferredSize());
		contentPane.add(deductionBoard);


		//RESULTS TRIANGLE
		resultsTriangle.setSize(resultsTriangle.getPreferredSize());
		resultsTriangle.setLocation(561, 61);
		contentPane.add(resultsTriangle);


		//PLAYER INVENTORY
		playerInventory.setSize(new Dimension(450, 415));
		playerInventory.setLocation(1404, 204);
		contentPane.add(playerInventory);

		JButton infoButton = new JButton("i");
		infoButton.setRequestFocusEnabled(false);
		infoButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		infoButton.setBorder(null);
		infoButton.setBackground(SystemColor.controlHighlight);
		infoButton.setBounds(10, 61, 60, 39);
		infoButton.addActionListener(e -> {

			JOptionPane.showMessageDialog(this, GlobalFonts.GAME_INFORMATION);
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
			JOptionPane.showMessageDialog(this, "Game paused. Close this window to continue.");
		});
		contentPane.add(pauseButton);

		//POTION BREWING BOARD
		potionBrewingBoard.setLocation(1300, 620);
		potionBrewingBoard.setSize(potionBrewingBoard.getPreferredSize());
		contentPane.add(potionBrewingBoard);

		//PUBLISH THEORY PANEL
		publishTheoryPanel.setLocation(86, 816);
		publishTheoryPanel.setSize(publishTheoryPanel.getPreferredSize());
		contentPane.add(publishTheoryPanel);

		//DEBUNK THEORY PANEL
		debunkTheoryView.setLocation(324, 879);
		debunkTheoryView.setSize(debunkTheoryView.getPreferredSize());
		contentPane.add(debunkTheoryView);

		//ROUND AND TURN INFORMATION
		JPanel roundInfoPanel = new JPanel();
		roundInfoPanel.setBounds(476, 61, 200, 90);
		roundInfoPanel.setOpaque(false);
		roundInfoPanel.setLayout(null);
		contentPane.add(roundInfoPanel);

		roundLabel = new JLabel("Round: 1");
		roundLabel.setFont(GlobalFonts.DISPLAY_HEADER);
		roundLabel.setForeground(GlobalColors.TEXT_COLOR);
		roundLabel.setBounds(0, 0, 130, 38);
		roundInfoPanel.add(roundLabel);

		turnLabel = new JLabel("Turn: " + GameController.getInstance().getCurrentPlayer().getPlayerName());
		turnLabel.setFont(GlobalFonts.DISPLAY_HEADER);
		turnLabel.setForeground(GlobalColors.TEXT_COLOR);
		turnLabel.setBounds(0, 40, 190, 43);
		roundInfoPanel.add(turnLabel);


		MainObserver mainObserver = new MainObserver(this);
		GameController.getInstance().getBoard().getIngredientBoard().addObserver(mainObserver);
		GameController.getInstance().getBoard().getPotionBrewingBoard().addObserver(mainObserver);
		GameController.getInstance().getBoard().getPublicationBoard().addObserver(mainObserver);
		for (Player player: GameController.getInstance().getActivePlayers()){
			player.getInventory().addObserver(mainObserver);
		}
		GameController.getInstance().addObserver(mainObserver);
		this.setVisible(true);
	}

	public void updateMainGameWindow() {
		//Deduction Board Changer
		((DeductionBoard)deductionBoard).updateDeductionBoard();

		//Player Inventory Changer
		((PlayerInventory)playerInventory).updatePlayerInventory("offline");

		//Results Triangle Changer
		((ResultsTriangle)resultsTriangle).updateResultsTriangle("offline");

		//Transmute Ingredient ComboBoxChanger
		transmuteIngredientComboBox.removeAllItems();
		ArrayList<IngredientCard> ingredientsListt = GameController.getInstance().getCurrentPlayer().getInventory().getPlayerIngredientCardList();
		String[] ingrss = new String[ingredientsListt.size()];
		for (int i = 0; i < ingredientsListt.size(); i++) {
			ingrss[i] = ingredientsListt.get(i).getName();
			transmuteIngredientComboBox.addItem(ingredientsListt.get(i).getName());
		}

		//Player Token View Changer
		for (PlayerTokenView p : this.playerTokens) {
			p.updatePlayerTokenView();
		}

		//Publish theory update
		((PublishTheoryPanel)publishTheoryPanel).updatePublishTheoryPanel();

		//Debunk theory update
		((DebunkTheoryView)debunkTheoryView).updateDebunkTheoryPanel();

		//Round and turn update
		roundLabel.setText("Round: " + GameController.getInstance().getCurrentRound());
		turnLabel.setText("Turn: " + GameController.getInstance().getCurrentPlayer().getPlayerName());

		//Show message if game has ended
		gameEndCheck();
	}

	public void gameEndCheck() {
		boolean end = GameController.getInstance().checkGameEnd();
		if (end) {
			new GameOverDialog(this);
		}
	}
}
