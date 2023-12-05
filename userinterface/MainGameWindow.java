package userinterface;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import userinterface.util.GlobalColors;
import userinterface.util.GlobalDimensions;
import userinterface.util.GlobalFonts;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Component;

public class MainGameWindow {

	private JPanel contentPane;
	private JPanel deductionBoard = new DeductionBoard();
	private JPanel resultsTriangle = new ResultsTriangle();
	private JPanel player1Inventory = new PlayerInventory(0);
	private JPanel player2Inventory = new PlayerInventory(1);

	public MainGameWindow() {
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
		contentPane.add(closeButton);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(81, 500, 315, 174);
		contentPane.add(buttonsPanel);
		buttonsPanel.setLayout(null);

		JPanel transmuteIngredientPanel = new JPanel();
		transmuteIngredientPanel.setBounds(0, 0, 105, 174);
		buttonsPanel.add(transmuteIngredientPanel);
		transmuteIngredientPanel.setLayout(null);

		JButton sellIngredientButton = new JButton("Transmute Ingredient");
		sellIngredientButton.setHorizontalTextPosition(SwingConstants.LEFT);
		sellIngredientButton.setVerticalAlignment(SwingConstants.BOTTOM);
		sellIngredientButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		sellIngredientButton.setFont(GlobalFonts.ACTION_BUTTON);
		sellIngredientButton.setBounds(transmuteIngredientPanel.getBounds());
		sellIngredientButton.setContentAreaFilled(false); //TODO make it transparent
		transmuteIngredientPanel.add(sellIngredientButton);

		JLabel sellIngredientLabel = new JLabel();
		sellIngredientLabel.setVerticalAlignment(SwingConstants.TOP);
		sellIngredientLabel.setBounds(transmuteIngredientPanel.getBounds());
		sellIngredientLabel.setIcon(new ImageIcon(MainGameWindow.class.getResource("/userinterface/images/flower_100x160.png")));
		transmuteIngredientPanel.add(sellIngredientLabel);

		JPanel buyArtifactPanel = new JPanel();
		buyArtifactPanel.setBounds(transmuteIngredientPanel.getX()+transmuteIngredientPanel.getWidth(), 0, 105, 174);
		buttonsPanel.add(buyArtifactPanel);
		buyArtifactPanel.setLayout(null);

		JButton buyArtifactButton = new JButton("Buy Artifact Card");
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
		forageForIngredientPanel.setBounds(buyArtifactPanel.getX()+buyArtifactPanel.getWidth(), 0, 105, 174);
		buttonsPanel.add(forageForIngredientPanel);

		JButton forageForIngredientButton = new JButton("Forage For Ingredient");
		forageForIngredientButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		forageForIngredientButton.setVerticalAlignment(SwingConstants.BOTTOM);
		forageForIngredientButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		forageForIngredientButton.setContentAreaFilled(false);
		forageForIngredientButton.setBounds(0, 0, 105, 174);
		forageForIngredientPanel.add(forageForIngredientButton);

		JLabel forageForIngredientLabel = new JLabel();
		forageForIngredientLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		forageForIngredientLabel.setBounds(0, 0, 105, 174);
		forageForIngredientPanel.add(forageForIngredientLabel);

		deductionBoard.setLocation(514, 541);
		deductionBoard.setSize(deductionBoard.getPreferredSize());
		contentPane.add(deductionBoard);


		resultsTriangle.setSize(resultsTriangle.getMaximumSize());
		resultsTriangle.setLocation(612, 89);
		contentPane.add(resultsTriangle);


		player1Inventory.setBounds(149, 89, 437, 300);
		contentPane.add(player1Inventory);
		contentPane.add(player2Inventory);


		MainGameWindowFrame.setVisible(true);
	}
}
