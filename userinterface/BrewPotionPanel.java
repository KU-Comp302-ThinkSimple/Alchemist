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
	private JTextField txtWelcomeToBrew;
	Choice choice = new Choice();
	//tf = new TextField(7);


	public BrewPotionPanel(){
		setAlignmentY(Component.BOTTOM_ALIGNMENT);
		setAlignmentX(Component.RIGHT_ALIGNMENT);
		setBackground(new Color(255, 128, 0));
		setAutoscrolls(true);
		setSize(GlobalDimensions.FULL_SCREEN);
		setPreferredSize(GlobalDimensions.FULL_SCREEN);
		setLayout(null);

		JTextPane txtpnWelcomeToPotion = new JTextPane();
		txtpnWelcomeToPotion.setBounds(370, 55, 844, 80);
		txtpnWelcomeToPotion.setForeground(new Color(102, 0, 0));
		txtpnWelcomeToPotion.setFont(new Font("OCR A Extended", Font.BOLD, 39));
		txtpnWelcomeToPotion.setBackground(new Color(255, 128, 0));
		txtpnWelcomeToPotion.setText("Welcome to Potion Brewing Area");
		add(txtpnWelcomeToPotion);

		JPanel panel = new JPanel();
		panel.setBounds(775, 14, 1, 1);
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.setBackground(new Color(128, 64, 64));
		add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Drink Potion");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(128, 0, 128));
		btnNewButton.setBounds(215, 732, 223, 80);
		btnNewButton.setFont(new Font("Rockwell Nova", Font.BOLD, 18));
		btnNewButton.getColorModel();
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Test on Student");
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(new Color(0, 128, 0));
		btnNewButton_1.setBounds(1135, 734, 204, 76);
		btnNewButton_1.setFont(new Font("Rockwell Nova", Font.BOLD, 18));
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Sell a potion");
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		btnNewButton_2.setBackground(new Color(0, 64, 128));
		btnNewButton_2.setBounds(688, 732, 217, 80);
		btnNewButton_2.setFont(new Font("Rockwell Nova", Font.BOLD, 18));
		add(btnNewButton_2);

		JPanel panelimg1 = new JPanel();
		panelimg1.setBounds(100, 297, 156, 189);
		panel.add(panelimg1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(775, 498, 207, 253);
		panel.add(panel_1);

		JLabel imageLabel = new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/drinkPotion_247x285.png")));
		imageLabel.setBounds(215, 501, 223, 281);
		add(imageLabel);

		JLabel imageLabel2=new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/sellAPotion_247x285.png")));
		imageLabel2.setBounds(688, 501, 207, 267);
		add(imageLabel2);

		JLabel imageLabel3=new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/testOnStudent_247x285.png")));
		imageLabel3.setBounds(1116, 501, 223, 281);
		add(imageLabel3);

		Choice choice1 = new Choice();
		choice1.setForeground(new Color(236, 166, 34));
		choice1.setBackground(new Color(100, 0, 0));
		choice1.setFont(new Font("Felix Titling", Font.BOLD, 14));
		choice1.setBounds(215, 383, 251, 309);
		add(choice1);

		choice1.add("Please select an ingredient");
		choice1.add("raven's feather");
		choice1.add("mandrake root");
		choice1.add("mushroom");
		choice1.add("frog");
		choice1.add("flower");
		choice1.add("scorpion");
		choice1.add("bird claw");
		choice1.add("seedling");

		Choice choice2 = new Choice();
		choice2.setForeground(new Color(196, 133, 2));
		choice2.setBackground(new Color(85, 0, 0));
		choice2.setFont(new Font("Felix Titling", Font.BOLD, 14));
		choice2.setBounds(656, 383, 251, 18);
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


		JLabel imageLabel11=new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/birdclaw_100x160.png")));
		imageLabel11.setBounds(130, 76, 411, 389);
		add(imageLabel11);

		JLabel imageLabel22=new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/feather_100x160.png")));
		imageLabel22.setBounds(580, 76, 385, 372);
		add(imageLabel22);

		JLabel imageLabel33=new JLabel(new ImageIcon(BrewPotionPanel.class.getResource("/userinterface/images/unknownPotion_103x151.png")));
		imageLabel33.setBounds(1021, 70, 466, 421);
		add(imageLabel33);


		ImageIcon flower = new ImageIcon("C:\\Users\\Lara\\Desktop\\cards\\flower_100x160.png");
		ImageIcon frog = new ImageIcon("C:\\Users\\Lara\\Desktop\\cards\\frog_100x160.png");
		ImageIcon mushroom = new ImageIcon("C:\\Users\\Lara\\Desktop\\cards\\mushroom_100x160.png");
		ImageIcon plant = new ImageIcon("\"C:\\Users\\Lara\\Desktop\\cards\\plant_100x160.png");
		ImageIcon root = new ImageIcon("\"C:\\Users\\Lara\\Desktop\\cards\\root_100x160.png");
		ImageIcon scorpion = new ImageIcon("\"C:\\Users\\Lara\\Desktop\\cards\\scorpion_100x160.png");











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














