package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import userinterface.util.GlobalFonts;


public class LoginSignUpWindow {

	private JPanel contentPane;
	private JTextField loginFormHeaderText;
	private JTextField userNameText;
	private JTextField passwordText;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton loginButton;



	public LoginSignUpWindow() {

		JFrame LoginSignUpWindowFrame = new JFrame();
		LoginSignUpWindowFrame.setBounds(0, 0, 1920, 1080);
		LoginSignUpWindowFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		LoginSignUpWindowFrame.setUndecorated(true);

		LoginSignUpWindowFrame.setTitle("Welcome to Alchemists!");
		LoginSignUpWindowFrame.setResizable(false);
		LoginSignUpWindowFrame.setSize(new Dimension(1920, 1080));
		LoginSignUpWindowFrame.setPreferredSize(new Dimension(1920, 1080));
		LoginSignUpWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginSignUpWindowFrame.setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(1920, 1080));
		contentPane.setPreferredSize(new Dimension(1920, 1080));
		contentPane.setMinimumSize(new Dimension(1920, 1080));
		contentPane.setMaximumSize(new Dimension(1920, 1080));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		LoginSignUpWindowFrame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton closeButton = new JButton("X");
		closeButton.setRequestFocusEnabled(false);
		closeButton.setFont(new Font(GlobalFonts.TAHOMA, Font.BOLD, 20));
		closeButton.setBounds(1500, 141, 57, 39);
		closeButton.addActionListener(e -> {
			LoginSignUpWindowFrame.dispose();
		}
				);

		JPanel loginFormPanel = new JPanel();
		loginFormPanel.setOpaque(false);
		loginFormPanel.setBounds(995, 221, 562, 645);
		contentPane.add(loginFormPanel);
		loginFormPanel.setLayout(null);


		loginFormHeaderText = new JTextField();
		loginFormHeaderText.setEditable(false);
		loginFormHeaderText.setBorder(null);
		loginFormHeaderText.setBounds(32, 0, 140, 37);
		loginFormHeaderText.setForeground(UIManager.getColor("Button.light"));
		loginFormHeaderText.setFont(new Font(GlobalFonts.TAHOMA, Font.BOLD, 25));
		loginFormHeaderText.setOpaque(false);
		loginFormHeaderText.setText("LOG IN");
		loginFormPanel.add(loginFormHeaderText);
		loginFormHeaderText.setColumns(10);

		textField = new JTextField();
		textField.setBounds(302, 49, 159, 35);
		loginFormPanel.add(textField);
		textField.setColumns(10);

		userNameText = new JTextField();
		userNameText.setEditable(false);
		userNameText.setBorder(null);
		userNameText.setBounds(32, 47, 140, 37);
		userNameText.setText("Username");
		userNameText.setOpaque(false);
		userNameText.setForeground(SystemColor.controlHighlight);
		userNameText.setFont(new Font(GlobalFonts.TAHOMA, Font.BOLD, 25));
		userNameText.setColumns(10);
		loginFormPanel.add(userNameText);

		passwordText = new JTextField();
		passwordText.setEditable(false);
		passwordText.setBorder(null);
		passwordText.setBounds(32, 95, 140, 37);
		passwordText.setText("Password");
		passwordText.setOpaque(false);
		passwordText.setForeground(SystemColor.controlHighlight);
		passwordText.setFont(new Font(GlobalFonts.TAHOMA, Font.BOLD, 25));
		passwordText.setColumns(10);
		loginFormPanel.add(passwordText);

		passwordField = new JPasswordField();
		passwordField.setBounds(302, 95, 159, 34);
		loginFormPanel.add(passwordField);

		loginButton = new JButton("Log In");
		loginButton.setRequestFocusEnabled(false);
		loginButton.setForeground(new Color(56, 32, 38));
		loginButton.setFont(new Font(GlobalFonts.TAHOMA, Font.BOLD, 25));
		loginButton.setBounds(302, 140, 159, 37);
		loginButton.addActionListener(e -> {
			//TODO log in action
		}
				);
		loginFormPanel.add(loginButton);
		contentPane.add(closeButton);

		JLabel backgroundImage = new JLabel("New label");
		backgroundImage.setBounds(0, 0, 1920, 1080);
		backgroundImage.setSize(new Dimension(1920, 1080));
		backgroundImage.setPreferredSize(new Dimension(1920, 1080));
		backgroundImage.setIcon(new ImageIcon(LoginSignUpWindow.class.getResource("/userinterface/images/login.png")));
		backgroundImage.setMinimumSize(new Dimension(1920, 1080));
		backgroundImage.setMaximumSize(new Dimension(1920, 1080));
		contentPane.add(backgroundImage);

		LoginSignUpWindowFrame.setVisible(true);
	}



}
