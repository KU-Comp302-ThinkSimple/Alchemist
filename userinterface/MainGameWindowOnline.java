package userinterface;

import domain.GameController;
import domain.LocalData;
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

public class MainGameWindowOnline extends JFrame{

	Player localPlayer = LocalData.getInstance().getLocalPlayer();
	JPanel contentPane;
	JPanel publishTheoryPanel = new PublishTheoryPanel();
	JPanel debunkTheoryView = new DebunkTheoryView();
	JPanel potionBrewingBoard = new BrewPotionPanel();
	JPanel resultsTriangle = new ResultsTriangle(1);
	JPanel deductionBoard = new DeductionBoard();
	PlayerTokenView ptwLocal;
	ArrayList<PlayerTokenView> playerTokens = new ArrayList<PlayerTokenView>();
	JPanel playerInventory = new PlayerInventory("online");
	JComboBox transmuteIngredientComboBox;
	JLabel roundLabel;
	JLabel turnLabel;

	public static void main(String[] args) {
		TestGameInitializer.initializeTestGame();
		new MainGameWindowOnline();
	}

	public MainGameWindowOnline() {
		LocalData.getInstance().setMainGameWindow(this);
		this.setUndecorated(true);
		this.setMaximumSize(new Dimension(1920, 1080));
		this.setBounds(0, 0, 1920, 1080);
		//this.setExtendedState(JFrame.NORMAL);

		this.setTitle("Alchemists Online");
		this.setResizable(false);
		this.setSize(GlobalDimensions.FULL_SCREEN);
		this.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 1920, 1080);

		//CONTENT PANE
		contentPane = new JPanel();
		contentPane.setBackground(GlobalColors.BACKGROUND_COLOR);
		contentPane.setSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setMinimumSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setMaximumSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		this.setContentPane(contentPane);

		//CLOSE BUTTON
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
		getContentPane().add(closeButton);

		//INFORMATION BUTTON
		JButton infoButton = new JButton("i");
		infoButton.setRequestFocusEnabled(false);
		infoButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		infoButton.setBorder(null);
		infoButton.setBackground(SystemColor.controlHighlight);
		infoButton.setBounds(10, 61, 60, 39);
		infoButton.addActionListener(e -> {
			String infoText = GlobalFonts.GAME_INFORMATION;
			JOptionPane.showMessageDialog(this, infoText);
		}
				);
		contentPane.add(infoButton);

		//PAUSE BUTTON
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


		//TRANSMUTE, BUY ARTIFACT, FORAGE BUTTONS PANEL
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(142, 620, 325, 174);
		contentPane.add(buttonsPanel);
		buttonsPanel.setLayout(null);


		//TRANSMUTE INGREDIENT
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
		transmuteIngredientButton.setContentAreaFilled(false);


		//get an array of ingredients of active player //TODO: LOCAL player DONE.
		ArrayList<IngredientCard> ingredientsList = LocalData.getInstance().getLocalPlayer().getInventory().getPlayerIngredientCardList();
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


		//action listener for transmute ingredient button
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


		//PUBLISH THEORY PANEL
		publishTheoryPanel.setLocation(86, 816);
		publishTheoryPanel.setSize(publishTheoryPanel.getPreferredSize());
		contentPane.add(publishTheoryPanel);

		//DEBUNK THEORY PANEL
		debunkTheoryView.setLocation(324, 879);
		debunkTheoryView.setSize(debunkTheoryView.getPreferredSize());
		contentPane.add(debunkTheoryView);

		//POTION BREWING BOARD
		potionBrewingBoard.setLocation(1300, 620);
		potionBrewingBoard.setSize(potionBrewingBoard.getPreferredSize());
		contentPane.add(potionBrewingBoard);

		//RESULTS TRIANGLE
		resultsTriangle.setSize(resultsTriangle.getPreferredSize());
		resultsTriangle.setLocation(561, 61);
		contentPane.add(resultsTriangle);

		//DEDUCTION BOARD
		deductionBoard.setLocation(571, 703);
		deductionBoard.setSize(deductionBoard.getPreferredSize());
		contentPane.add(deductionBoard);

		//PLAYER TOKENS
		int playercount = GameController.getInstance().getActivePlayers().size();
		localPlayer = GameController.getInstance().getActivePlayers().get(0);
		int xalign = 161;
		int yalign = 21;
		for (int i = 0; i < playercount; i++) {

			PlayerTokenView ptw = new PlayerTokenView(i);

			if (GameController.getInstance().getActivePlayers().get(i).equals(localPlayer)) {
				ptwLocal = ptw;
				ptwLocal.setLocation(1484, 11);
				ptwLocal.setSize(272, 187);
				ptwLocal.setSize(new Dimension(272, 187));
				getContentPane().add(ptwLocal);
			}
			else {
				ptw.setLocation(xalign, yalign);
				ptw.setSize(new Dimension(272, 187));
				getContentPane().add(ptw);
				yalign += 198;
			}

			ptw.setSize(ptw.getPreferredSize());
			this.playerTokens.add(ptw);
		}


		//PLAYER INVENTORY
		playerInventory.setSize(new Dimension(450, 415));
		playerInventory.setLocation(1404, 204);
		getContentPane().add(playerInventory);

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

		turnLabel = new JLabel("Turn: ");
		turnLabel.setFont(GlobalFonts.DISPLAY_HEADER);
		turnLabel.setForeground(GlobalColors.TEXT_COLOR);
		turnLabel.setBounds(0, 40, 190, 43);
		roundInfoPanel.add(turnLabel);




		addThisToObservables();


		this.setVisible(true);
	}

	public void addThisToObservables() {
		//OBSERVER RELATED
		MainObserver mainObserver = new MainObserver(this);
		GameController.getInstance().getBoard().getIngredientBoard().addObserver(mainObserver);
		GameController.getInstance().getBoard().getPotionBrewingBoard().addObserver(mainObserver);
		GameController.getInstance().getBoard().getPublicationBoard().addObserver(mainObserver);
		for (Player player: GameController.getInstance().getActivePlayers()){
			player.getInventory().addObserver(mainObserver);
		}
		GameController.getInstance().addObserver(mainObserver);
	}

	public void updateMainGameWindow() {
		System.out.println("Main Game Window Update");
		//Deduction Board Changer
		((DeductionBoard)deductionBoard).updateDeductionBoard();

		//Player Inventory Changer
		((PlayerInventory)playerInventory).updatePlayerInventory("online");

		//Results Triangle Changer
		((ResultsTriangle)resultsTriangle).updateResultsTriangle();

		//Transmute Ingredient ComboBoxChanger
		transmuteIngredientComboBox.removeAllItems();
		ArrayList<IngredientCard> ingredientsListt = LocalData.getInstance().getLocalPlayer().getInventory().getPlayerIngredientCardList();
		String[] ingrss = new String[ingredientsListt.size()];
		for (int i = 0; i < ingredientsListt.size(); i++) {
			ingrss[i] = ingredientsListt.get(i).getName();
			transmuteIngredientComboBox.addItem(ingredientsListt.get(i).getName());
		}

		//Player Token View Changer
		for (PlayerTokenView playerTokenView : this.playerTokens) {
			playerTokenView.updatePlayerTokenView();
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
