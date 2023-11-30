package userinterface;
import java.awt.Cursor;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import java.awt.Choice;



public class BrewPotionPanel extends JPanel {
	private JTextField txtWelcomeToBrew;

    

    public BrewPotionPanel(){
       setSize(GlobalDimensions.FULL_SCREEN);
         setPreferredSize(GlobalDimensions.FULL_SCREEN);
         JFrame j=new JFrame();
         j.getContentPane().setBackground(new Color(128, 64, 64));
         j.getContentPane().setForeground(new Color(128, 64, 64));
         j.getContentPane().setLayout(null);
         
         txtWelcomeToBrew = new JTextField();
         txtWelcomeToBrew.setBounds(0, 0, 1920, 124);
         txtWelcomeToBrew.setCaretColor(new Color(128, 64, 64));
         txtWelcomeToBrew.setForeground(new Color(0, 0, 0));
         txtWelcomeToBrew.setHorizontalAlignment(SwingConstants.CENTER);
         txtWelcomeToBrew.setBackground(new Color(128, 64, 64));
         txtWelcomeToBrew.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 23));
         txtWelcomeToBrew.setText("Welcome to Brew Portion Area");
         j.getContentPane().add(txtWelcomeToBrew);
         txtWelcomeToBrew.setColumns(10);
        
         JButton btnNewButton = new JButton("Alternative 1");
         btnNewButton.setBounds(113, 782, 412, 169);
         btnNewButton.setBackground(new Color(128, 64, 64));
         btnNewButton.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         	}
         });
         j.getContentPane().add(btnNewButton);
         j.setUndecorated(true);
         
         JButton btnNewButton_1 = new JButton("Alternative 2");
         btnNewButton_1.setBounds(633, 782, 348, 162);
         btnNewButton_1.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         	}
         });
         j.getContentPane().add(btnNewButton_1);
         
         JButton btnNewButton_2 = new JButton("Alternative 3");
         btnNewButton_2.setBounds(1086, 782, 348, 162);
         j.getContentPane().add(btnNewButton_2);
         
         JLayeredPane layeredPane = new JLayeredPane();
         layeredPane.setBounds(314, 471, 1, 1);
         j.getContentPane().add(layeredPane);
         
         Choice choice = new Choice();
         choice.setFont(new Font("Rockwell Nova Extra Bold", Font.PLAIN, 15));
         choice.setBounds(49, 538, 240, 26);
         j.getContentPane().add(choice);
         
         choice.add("raven’s feather");
         choice.add("mandrake root");
         choice.add("mushroom");
         choice.add("frog");
         choice.add("flower");
         choice.add("scorpion");
         choice.add("bird claw");
         choice.add("seedling");
         j.getContentPane().add(choice);
         Choice choice2 = new Choice();
         choice2.setFont(new Font("Rockwell Nova Extra Bold", Font.PLAIN, 15));
         choice2.setBounds(553, 623, 320, 26);
         
         
         
         JPanel panelimg1 = new JPanel();
         panelimg1.setBounds(83, 267, 136, 169);
         j.getContentPane().add(panelimg1);
         ImageIcon birdclaw = new ImageIcon("C:\\Users\\Lara\\Desktop\\cards\\birdclaw_100x160.png");
         ImageIcon feather = new ImageIcon("C:\\Users\\Lara\\Desktop\\cards\\feather_100x160.png");
         ImageIcon flower = new ImageIcon("C:\\Users\\Lara\\Desktop\\cards\\flower_100x160.png");
         ImageIcon frog = new ImageIcon("C:\\Users\\Lara\\Desktop\\cards\\frog_100x160.png");
         ImageIcon mushroom = new ImageIcon("C:\\Users\\Lara\\Desktop\\cards\\mushroom_100x160.png");
         ImageIcon plant = new ImageIcon("\"C:\\Users\\Lara\\Desktop\\cards\\plant_100x160.png");
         ImageIcon root = new ImageIcon("\"C:\\Users\\Lara\\Desktop\\cards\\root_100x160.png");
         ImageIcon scorpion = new ImageIcon("\"C:\\Users\\Lara\\Desktop\\cards\\scorpion_100x160.png");
         panelimg1.setLayout(null);
         JLabel label1 = new JLabel(birdclaw);
         panelimg1.add(label1);
         JLabel label2 = new JLabel(feather);
         panelimg1.add(label2);
         JLabel label3 = new JLabel(flower);
         panelimg1.add(label3);
         JLabel label4 = new JLabel(frog);
         panelimg1.add(label4);
         JLabel label5 = new JLabel(mushroom);
         panelimg1.add(label5);
         JLabel label6 = new JLabel(plant);
         panelimg1.add(label6);
         JLabel label7 = new JLabel(root);
         panelimg1.add(label7);
         JLabel label8 = new JLabel(scorpion);
         panelimg1.add(label8);
         JLabel label = new JLabel(birdclaw);
         label.setBounds(-75, -40, 293, 251);
         panelimg1.add(label);
         
         
         
         JPanel panelimg2 = new JPanel();
         panelimg2.setBounds(553, 164, 320, 400);
         j.getContentPane().add(panelimg2);
         
         
         
         ImageIcon birdclaw2 = new ImageIcon("C:\\Users\\Lara\\Desktop\\cards\\birdclaw_100x160.png");
         ImageIcon feather2 = new ImageIcon("C:\\Users\\Lara\\Desktop\\cards\\feather_100x160.png");
         ImageIcon flower2 = new ImageIcon("C:\\Users\\Lara\\Desktop\\cards\\flower_100x160.png");
         ImageIcon frog2 = new ImageIcon("C:\\Users\\Lara\\Desktop\\cards\\frog_100x160.png");
         ImageIcon mushroom2 = new ImageIcon("C:\\Users\\Lara\\Desktop\\cards\\mushroom_100x160.png");
         ImageIcon plant2 = new ImageIcon("\"C:\\Users\\Lara\\Desktop\\cards\\plant_100x160.png");
         ImageIcon root2 = new ImageIcon("\"C:\\Users\\Lara\\Desktop\\cards\\root_100x160.png");
         ImageIcon scorpion2 = new ImageIcon("\"C:\\Users\\Lara\\Desktop\\cards\\scorpion_100x160.png");
         panelimg2.setLayout(null);
         JLabel label11 = new JLabel(birdclaw2);
         label11.setBounds(0, 0, 334, 400);
         panelimg2.add(label11);
         JLabel label22 = new JLabel(feather2);
         panelimg2.add(label22);
         JLabel label33 = new JLabel(flower2);
         panelimg2.add(label33);
         JLabel label44 = new JLabel(frog2);
         panelimg2.add(label44);
         JLabel label55 = new JLabel(mushroom2);
         panelimg2.add(label55);
         JLabel label66 = new JLabel(plant2);
         panelimg2.add(label66);
         JLabel label77 = new JLabel(root2);
         panelimg2.add(label77);
         JLabel label88 = new JLabel(scorpion2);
         panelimg2.add(label88);
         
         
         JPanel panelimage3 = new JPanel();
         panelimage3.setBounds(1050, 164, 348, 400);
         j.getContentPane().add(panelimage3);
         
         choice2.add("raven’s feather");
         choice2.add("mandrake root");
         choice2.add("mushroom");
         choice2.add("frog");
         choice2.add("flower");
         choice2.add("scorpion");
         choice2.add("bird claw");
         choice2.add("seedling");
 		 j.setMaximumSize(new Dimension(1920, 1080));
 		 j.setBounds(0, 0, 1920, 1080);
 		 /*j.setTitle("Welcome to Alchemists!");
 		 j.setResizable(false);*/
         // l1 = new Label("Welcome to Brew Potion Area");
         //l1.setAlignment(Label.CENTER);
        
    }
}














