package userinterface;
import java.awt.Cursor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domain.GameController;
import domain.boards.BoardController;
import userinterface.util.GlobalColors;
import userinterface.util.GlobalDimensions;
import userinterface.util.GlobalFonts;
import userinterface.util.GlobalIcons;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import java.awt.Choice;
import java.awt.Component;
import javax.swing.JTextPane;
import java.awt.ComponentOrientation;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.Icon;
import java.awt.Rectangle;



public class BrewPotionPanel extends JPanel {


	public BrewPotionPanel(){
		setMinimumSize(new Dimension(690, 555));
		setMaximumSize(new Dimension(690, 555));
		setPreferredSize(new Dimension(690, 555));
		setBounds(new Rectangle(0, 0, 690, 555));

		setBackground(GlobalColors.BACKGROUND_COLOR);
		//setOpaque(false);
		setAutoscrolls(true);
		setLayout(null);
		setSize(691, 604);


		//CARD IMAGES AND SELECTION TOOL
		JLabel card1Label = new JLabel(GlobalIcons.getIngredientCardImage("Unknown"));
		card1Label.setBounds(57, 11, 100, 160);
		add(card1Label);

		JLabel card2Label = new JLabel(GlobalIcons.getIngredientCardImage("Unknown"));
		card2Label.setBounds(328, 11, 100, 160);
		add(card2Label);

		String[] ingredientNames = {"Mushroom", "Seedling", "Frog", "Bird Claw", "Flower", "Mandrake Root", "Scorpion", "Raven's Feather"};

		JComboBox ingrSelect1 = new JComboBox(ingredientNames);
		ingrSelect1.setBounds(10, 196, 200, 20);
		ingrSelect1.addActionListener(e -> {
			card1Label.setIcon(GlobalIcons.getIngredientCardImage((String)ingrSelect1.getSelectedItem()));
		});
		add(ingrSelect1);

		JComboBox ingrSelect2 = new JComboBox(ingredientNames);
		ingrSelect2.setBounds(225, 196, 200, 20);
		ingrSelect2.addActionListener(e -> {
			card2Label.setIcon(GlobalIcons.getIngredientCardImage((String)ingrSelect2.getSelectedItem()));
		});
		add(ingrSelect2);


		//DRINK POTION PANEL
		JPanel drinkPotionPanel = new JPanel();
		drinkPotionPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		drinkPotionPanel.setBounds(10, 223, 247, 311);
		drinkPotionPanel.setOpaque(false);
		add(drinkPotionPanel);
		drinkPotionPanel.setLayout(null);

		//DRINK POTION BUTTON
		JButton drinkPotionButton = new JButton("Drink Potion");
		drinkPotionButton.setBounds(22, 231, 180, 80);
		drinkPotionButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		drinkPotionButton.setBackground(GlobalColors.BUTTON_COLOR);
		drinkPotionButton.setFont(GlobalFonts.BREW_BUTTON);
		drinkPotionButton.addActionListener(e -> {
			try {
				String message = BoardController.brewPotion(((String)ingrSelect1.getSelectedItem()), ((String)ingrSelect2.getSelectedItem()), false).toString().toLowerCase();
				JOptionPane.showMessageDialog(this, "You brew: " + message);
			}
			catch (Exception error) {
				JOptionPane.showMessageDialog(this, error.getMessage());
			}
		});
		drinkPotionPanel.add(drinkPotionButton);

		//DRINK POTION LABEL
		JLabel drinkPotionLabel = new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/drinkPotion_247x285.png")));
		drinkPotionLabel.setBounds(0, 0, 247, 285);
		drinkPotionPanel.add(drinkPotionLabel);


		//TEST ON STUDENT PANEL
		JPanel testOnStudentPanel = new JPanel();
		testOnStudentPanel.setBounds(225, 223, 247, 311);
		testOnStudentPanel.setBorder(new EmptyBorder(0,0,0,0));
		testOnStudentPanel.setOpaque(false);
		add(testOnStudentPanel);
		testOnStudentPanel.setLayout(null);

		//TEST ON STUDENT BUTTON
		JButton testOnStudentButton = new JButton("Test on Student");
		testOnStudentButton.setBounds(22, 233, 180, 80);
		testOnStudentButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		testOnStudentButton.setBackground(GlobalColors.BUTTON_COLOR);
		testOnStudentButton.setFont(GlobalFonts.BREW_BUTTON);
		testOnStudentButton.addActionListener(e -> {
			try {
				String message = BoardController.brewPotion(((String)ingrSelect1.getSelectedItem()), ((String)ingrSelect2.getSelectedItem()), true).toString().toLowerCase();
				JOptionPane.showMessageDialog(this, "You brew: " + message);
			}
			catch (Exception error) {
				JOptionPane.showMessageDialog(this, error.getMessage());
			}
		});
		testOnStudentPanel.add(testOnStudentButton);

		//TEST ON STUDENT LABEL
		JLabel testOnStudentLabel=new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/testOnStudent_247x285.png")));
		testOnStudentLabel.setBounds(0, 0, 247, 285);
		testOnStudentPanel.add(testOnStudentLabel);

		String[] guarantees = {"No guarantee", "Positive or Neutral", "Positive"};
		JComboBox guaranteeComboBox = new JComboBox(guarantees);
		guaranteeComboBox.setBounds(464, 196, 181, 20);
		add(guaranteeComboBox);

		//SELL A POTION PANEL
		JPanel sellAPotionPanel = new JPanel();
		sellAPotionPanel.setBounds(446, 223, 247, 311);
		sellAPotionPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		sellAPotionPanel.setOpaque(false);
		add(sellAPotionPanel);
		sellAPotionPanel.setLayout(null);


		//SELL A POTION BUTTON
		JButton sellAPotionButton = new JButton("Sell a potion");
		sellAPotionButton.setBounds(22, 231, 180, 80);
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
		JLabel sellAPotionLabel=new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/sellAPotion_247x285.png")));
		sellAPotionLabel.setBounds(0, 0, 247, 285);
		sellAPotionPanel.add(sellAPotionLabel);


		//POTION LABEL
		JLabel potionLabel=new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/unknownPotion_103x151.png")));
		potionLabel.setBounds(516, 34, 103, 151);
		add(potionLabel);




	}
}














