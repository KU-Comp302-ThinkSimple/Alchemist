package userinterface;

import java.awt.Cursor;
import java.awt.GridLayout;

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

public class LoginSignUpWindow {

	private JPanel contentPane;
	private JTextField loginFormHeaderText;
	private JTextField userNameTextField;
	private JTextField passwordTextField;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JTextField txtSgnUp;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField_1;



	public LoginSignUpWindow() {

		JFrame LoginSignUpWindowFrame = new JFrame();
		LoginSignUpWindowFrame.setBounds(0, 0, 1920, 1080);
		LoginSignUpWindowFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		LoginSignUpWindowFrame.setUndecorated(true);

		LoginSignUpWindowFrame.setTitle("Welcome to Alchemists!");
		LoginSignUpWindowFrame.setResizable(false);
		LoginSignUpWindowFrame.setSize(GlobalDimensions.FULL_SCREEN);
		LoginSignUpWindowFrame.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		LoginSignUpWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginSignUpWindowFrame.setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setMinimumSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setMaximumSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		LoginSignUpWindowFrame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton closeButton = new JButton("X");
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.setBorder(null);
		closeButton.setBackground(GlobalColors.BUTTON_COLOR);
		closeButton.setRequestFocusEnabled(false);
		closeButton.setFont(GlobalFonts.DISPLAYFONT);
		closeButton.setBounds(1497, 141, 60, 39);
		closeButton.addActionListener(e -> {
			LoginSignUpWindowFrame.dispose();
		}
				);

		JPanel loginFormPanel = new JPanel();
		loginFormPanel.setOpaque(false);
		loginFormPanel.setBounds(995, 242, 466, 582);
		contentPane.add(loginFormPanel);
		loginFormPanel.setLayout(new GridLayout(0, 2, 1, 25));


		loginFormHeaderText = new JTextField();
		loginFormHeaderText.setEditable(false);
		loginFormHeaderText.setBorder(null);
		loginFormHeaderText.setForeground(GlobalColors.TEXT_COLOR);
		loginFormHeaderText.setFont(GlobalFonts.DISPLAY_HEADER_FONT);
		loginFormHeaderText.setOpaque(false);
		loginFormHeaderText.setText("LOG IN");
		loginFormPanel.add(loginFormHeaderText);
		loginFormHeaderText.setColumns(10);

		JLabel label = new JLabel("");
		loginFormPanel.add(label);

		userNameTextField = new JTextField();
		userNameTextField.setEditable(false);
		userNameTextField.setBorder(null);
		userNameTextField.setText("Username");
		userNameTextField.setOpaque(false);
		userNameTextField.setForeground(GlobalColors.TEXT_COLOR);
		userNameTextField.setFont(GlobalFonts.DISPLAYFONT);
		userNameTextField.setColumns(10);
		loginFormPanel.add(userNameTextField);

		textField = new JTextField();
		textField.setBorder(new EmptyBorder(0, 3, 0, 0));
		textField.setFont(GlobalFonts.INPUTFONT);
		loginFormPanel.add(textField);
		textField.setColumns(10);

		passwordTextField = new JTextField();
		passwordTextField.setEditable(false);
		passwordTextField.setBorder(null);
		passwordTextField.setText("Password");
		passwordTextField.setOpaque(false);
		passwordTextField.setForeground(GlobalColors.TEXT_COLOR);
		passwordTextField.setFont(GlobalFonts.DISPLAYFONT);
		passwordTextField.setColumns(10);
		loginFormPanel.add(passwordTextField);

		passwordField = new JPasswordField();
		passwordField.setBorder(new EmptyBorder(0, 3, 0, 0));
		passwordField.setFont(GlobalFonts.PASSWORDFONT);
		loginFormPanel.add(passwordField);

		JLabel label_1 = new JLabel("");
		loginFormPanel.add(label_1);

		loginButton = new JButton("Log In");
		loginButton.setBackground(GlobalColors.BUTTON_COLOR);
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setBorder(null);
		loginButton.setRequestFocusEnabled(false);
		loginButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		loginButton.setFont(GlobalFonts.DISPLAYFONT);
		loginButton.addActionListener(e -> {
			//TODO log in action
		}
				);
		loginFormPanel.add(loginButton);

		txtSgnUp = new JTextField();
		txtSgnUp.setText("SIGN UP");
		txtSgnUp.setOpaque(false);
		txtSgnUp.setForeground(GlobalColors.TEXT_COLOR);
		txtSgnUp.setFont(GlobalFonts.DISPLAY_HEADER_FONT);
		txtSgnUp.setEditable(false);
		txtSgnUp.setColumns(10);
		txtSgnUp.setBorder(null);
		loginFormPanel.add(txtSgnUp);

		JLabel label_2 = new JLabel("");
		loginFormPanel.add(label_2);

		textField_2 = new JTextField();
		textField_2.setText("Username");
		textField_2.setOpaque(false);
		textField_2.setForeground(GlobalColors.TEXT_COLOR);
		textField_2.setFont(GlobalFonts.DISPLAYFONT);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBorder(null);
		loginFormPanel.add(textField_2);

		textField_4 = new JTextField();
		textField_4.setBorder(new EmptyBorder(0, 3, 0, 0));
		textField_4.setFont(GlobalFonts.INPUTFONT);
		textField_4.setColumns(10);
		loginFormPanel.add(textField_4);

		textField_3 = new JTextField();
		textField_3.setText("Password");
		textField_3.setOpaque(false);
		textField_3.setForeground(GlobalColors.TEXT_COLOR);
		textField_3.setFont(GlobalFonts.DISPLAYFONT);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBorder(null);
		loginFormPanel.add(textField_3);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBorder(new EmptyBorder(0, 3, 0, 0));
		passwordField_1.setFont(GlobalFonts.PASSWORDFONT);
		loginFormPanel.add(passwordField_1);

		JLabel label_3 = new JLabel("");
		loginFormPanel.add(label_3);

		JButton signUpButton = new JButton("Sign Up");
		signUpButton.setBackground(GlobalColors.BUTTON_COLOR);
		signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpButton.setBorder(null);
		signUpButton.setRequestFocusEnabled(false);
		signUpButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		signUpButton.setFont(GlobalFonts.DISPLAYFONT);
		signUpButton.addActionListener(e -> {
			//TODO sign up action
		}
				);
		loginFormPanel.add(signUpButton);
		contentPane.add(closeButton);

		JLabel backgroundImage = new JLabel();
		backgroundImage.setBounds(0, 0, 1920, 1080);
		backgroundImage.setSize(GlobalDimensions.FULL_SCREEN);
		backgroundImage.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		backgroundImage.setIcon(new ImageIcon(LoginSignUpWindow.class.getResource("/userinterface/images/login.png")));
		backgroundImage.setMinimumSize(GlobalDimensions.FULL_SCREEN);
		backgroundImage.setMaximumSize(GlobalDimensions.FULL_SCREEN);
		contentPane.add(backgroundImage);

		LoginSignUpWindowFrame.setVisible(true);
	}
}
