package userinterface;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domain.GameController;
import domain.boards.BoardController;
import domain.cards.IngredientCard;
import exception.UserErrorException;
import test.TestGameInitializer;
import userinterface.util.GlobalColors;
import userinterface.util.GlobalDimensions;
import userinterface.util.GlobalFonts;
import userinterface.util.GlobalIcons;

public class MainGameWindowOnline extends JFrame{

	JPanel contentPane;
	JPanel publishTheoryPanel = new PublishTheoryPanel();
	JPanel debunkTheoryView = new DebunkTheoryView();
	JPanel potionBrewingBoard = new BrewPotionPanel();
	JPanel resultsTriangle = new ResultsTriangle(1);
	JPanel deductionBoard = new DeductionBoard();
	ArrayList<PlayerTokenView> playerTokens = new ArrayList<PlayerTokenView>();
	JPanel playerInventory = new PlayerInventory();

	public static void main(String[] args) {
		TestGameInitializer.initializeTestGame();
		new MainGameWindowOnline();
	}

	public MainGameWindowOnline() {

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
		buttonsPanel.setBounds(10, 650, 325, 174);
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


		//get an array of ingredients of active player //TODO curr player
		ArrayList<IngredientCard> ingredientsList = GameController.getInstance().getCurrentPlayer().getInventory().getPlayerIngredientCardList();
		String[] ingrs = new String[ingredientsList.size()];
		for (int i = 0; i < ingredientsList.size(); i++) {
			ingrs[i] = ingredientsList.get(i).getName();
		}

		JComboBox transmuteIngredientComboBox = new JComboBox(ingrs);
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
		publishTheoryPanel.setLocation(10, 835);
		publishTheoryPanel.setSize(publishTheoryPanel.getPreferredSize());
		contentPane.add(publishTheoryPanel);

		//DEBUNK THEORY PANEL
		debunkTheoryView.setLocation(248, 998);
		debunkTheoryView.setSize(debunkTheoryView.getPreferredSize());
		contentPane.add(debunkTheoryView);

		//POTION BREWING BOARD
		potionBrewingBoard.setLocation(1300, 620);
		potionBrewingBoard.setSize(potionBrewingBoard.getPreferredSize());
		contentPane.add(potionBrewingBoard);

		//RESULTS TRIANGLE
		resultsTriangle.setSize(resultsTriangle.getMaximumSize());
		resultsTriangle.setLocation(584, 98);
		contentPane.add(resultsTriangle);

		//DEDUCTION BOARD
		deductionBoard.setLocation(594, 740);
		deductionBoard.setSize(deductionBoard.getPreferredSize());
		contentPane.add(deductionBoard);

		//PLAYER TOKENS
		int playercount = 2; //TODO
		//GameController.getInstance().getActivePlayers().size(); maybe?
		int xalign;
		int yalign;
		for (int i = 0; i < playercount; i++) {
			PlayerTokenView ptw = new PlayerTokenView(i);
			ptw.setSize(ptw.getPreferredSize());


		}
		PlayerTokenView ptw = new PlayerTokenView(0);
		ptw.setLocation(161, 219);
		ptw.setSize(new Dimension(272, 187));
		getContentPane().add(ptw);

		PlayerTokenView ptw_1 = new PlayerTokenView(0);
		ptw_1.setSize(ptw_1.getPreferredSize());
		ptw_1.setBounds(161, 417, 272, 187);
		contentPane.add(ptw_1);

		PlayerTokenView ptw_2 = new PlayerTokenView(0);
		ptw_2.setSize(ptw_2.getPreferredSize());
		ptw_2.setBounds(161, 21, 272, 187);
		contentPane.add(ptw_2);

		PlayerTokenView ptw_local = new PlayerTokenView(0); //TODO how can i achieve this?
		ptw_local.setLocation(1484, 11);
		ptw_local.setSize(272, 187);
		ptw.setSize(new Dimension(272, 187));
		getContentPane().add(ptw_local);
		playerInventory.setLocation(1389, 200);


		playerInventory.setSize(playerInventory.getPreferredSize());
		playerInventory.setSize(new Dimension(450, 415));
		getContentPane().add(playerInventory);


		this.setVisible(true);
	}
}
