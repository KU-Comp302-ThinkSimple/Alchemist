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
import userinterface.util.GlobalColors;
import userinterface.util.GlobalDimensions;
import userinterface.util.GlobalFonts;
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



public class BrewPotionPanel extends JPanel {

	Choice choice = new Choice();


	public BrewPotionPanel(){

		setBackground(GlobalColors.BACKGROUND_COLOR);
		//setOpaque(false);
		setAutoscrolls(true);
		setLayout(null);

		JPanel drinkPotionPanel = new JPanel();
		drinkPotionPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		drinkPotionPanel.setBounds(10, 261, 247, 311);
		drinkPotionPanel.setOpaque(false);
		add(drinkPotionPanel);
		drinkPotionPanel.setLayout(null);

		JButton drinkPotionButton = new JButton("Drink Potion");
		drinkPotionButton.setBounds(22, 231, 180, 80);
		drinkPotionPanel.add(drinkPotionButton);
		drinkPotionButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		drinkPotionButton.setBackground(GlobalColors.BUTTON_COLOR);
		drinkPotionButton.setFont(GlobalFonts.BREW_BUTTON);

		JLabel drinkPotionLabel = new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/drinkPotion_247x285.png")));
		drinkPotionLabel.setBounds(0, 0, 247, 285);
		drinkPotionPanel.add(drinkPotionLabel);
		drinkPotionButton.getColorModel();
		drinkPotionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		JPanel testOnStudentPanel = new JPanel();
		testOnStudentPanel.setBounds(225, 261, 247, 311);
		testOnStudentPanel.setBorder(new EmptyBorder(0,0,0,0));
		testOnStudentPanel.setOpaque(false);
		add(testOnStudentPanel);
		testOnStudentPanel.setLayout(null);

		JButton testOnStudentButton = new JButton("Test on Student");
		testOnStudentButton.setBounds(22, 233, 180, 80);
		testOnStudentPanel.add(testOnStudentButton);
		testOnStudentButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		testOnStudentButton.setBackground(GlobalColors.BUTTON_COLOR);
		testOnStudentButton.setFont(GlobalFonts.BREW_BUTTON);

		JLabel testOnStudentLabel=new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/testOnStudent_247x285.png")));
		testOnStudentLabel.setBounds(0, 0, 247, 285);
		testOnStudentPanel.add(testOnStudentLabel);

		JPanel sellAPotionPanel = new JPanel();
		sellAPotionPanel.setBounds(446, 261, 247, 311);
		sellAPotionPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		sellAPotionPanel.setOpaque(false);
		add(sellAPotionPanel);
		sellAPotionPanel.setLayout(null);

		JButton sellAPotionButton = new JButton("Sell a potion");
		sellAPotionButton.setBounds(22, 231, 180, 80);
		sellAPotionPanel.add(sellAPotionButton);
		sellAPotionButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		sellAPotionButton.setBackground(GlobalColors.BUTTON_COLOR);
		sellAPotionButton.setFont(GlobalFonts.BREW_BUTTON);

		JLabel sellAPotionLabel=new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/sellAPotion_247x285.png")));
		sellAPotionLabel.setBounds(0, 0, 247, 285);
		sellAPotionPanel.add(sellAPotionLabel);

		Choice choice1 = new Choice();
		choice1.setForeground(GlobalColors.BUTTON_COLOR);
		choice1.setBackground(new Color(100, 0, 0));
		choice1.setFont(new Font("Felix Titling", Font.BOLD, 14)); //TODO GlobalFonts
		choice1.setBounds(10, 234, 230, 20);
		add(choice1);

		/*
		choice1.add("Please select an ingredient");
		choice1.add("raven's feather");
		choice1.add("mandrake root");
		choice1.add("mushroom");
		choice1.add("frog");
		choice1.add("flower");
		choice1.add("scorpion");
		choice1.add("bird claw");
		choice1.add("seedling");
		 */

		//TODO get inventory items and add them to choice
		int item_count = GameController.getCurrentPlayer().getInventory().getPlayerIngredientCardList().size();
		String[] inventory = new String[item_count];
		for (int i = 0; i < item_count; i++) {
			inventory[i] = GameController.getCurrentPlayer().getInventory().getPlayerIngredientCardList().get(i).getName();
			choice1.add(inventory[i]);
		}


		Choice choice2 = new Choice();
		choice2.setForeground(new Color(196, 133, 2));
		choice2.setBackground(new Color(85, 0, 0));
		choice2.setFont(new Font("Felix Titling", Font.BOLD, 14));
		choice2.setBounds(266, 234, 230, 20);
		add(choice2);

		choice2.add("Please select an ingredient");
		choice2.add("raven's feather");
		choice2.add("mandrake root");
		choice2.add("mushroom");
		choice2.add("frog");
		choice2.add("flower");
		choice2.add("scorpion");
		choice2.add("bird claw");
		choice2.add("seedling");

		JLabel potionLabel=new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/unknownPotion_103x151.png")));
		potionLabel.setBounds(537, 72, 103, 151);
		add(potionLabel);

		JLabel card1Label = new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/unknownCard_100x160.png")));
		card1Label.setBounds(57, 49, 100, 160);
		add(card1Label);

		JLabel card2Label = new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/unknownCard_100x160.png")));
		card2Label.setBounds(328, 49, 100, 160);
		add(card2Label);












		/* btnNewButton.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         	}
         });
         btnNewButton_1.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {

         	}
         });







 		 //j.setMaximumSize(new Dimension(1920, 1080));
 		 //j.setBounds(0, 0, 1920, 1080);
 		 /*j.setTitle("Welcome to Alchemists!");
 		 j.setResizable(false);*/
		//l1 = new Label("Welcome to Brew Potion Area");
		//l1.setAlignment(Label.CENTER);

	}
}














