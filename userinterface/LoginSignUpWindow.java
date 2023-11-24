package userinterface;

import java.awt.Cursor;

import java.awt.Dimension;
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

import userinterface.*;

public class LoginSignUpWindow {

	private JPanel contentPane;
	private JTextField loginHeaderText;
	private JTextField loginUserNameDisplay;
	private JTextField loginPasswordDisplay;
	private JTextField loginUserNameInputTextField;
	private JPasswordField loginPasswordField;
	private JButton loginButton;
	private JTextField signUpHeaderText;
	private JTextField signUpUserNameText;
	private JTextField signUpPasswordDisplayText;
	private JTextField signUpUserNameInputTextField;
	private JPasswordField signUpPasswordField;
	private JLabel alchemistImageLabel;



	public LoginSignUpWindow() {

		JFrame LoginSignUpWindowFrame = new JFrame();
		LoginSignUpWindowFrame.setUndecorated(true);
		LoginSignUpWindowFrame.setMaximumSize(new Dimension(1920, 1080));
		LoginSignUpWindowFrame.setBounds(0, 0, 1920, 1080);
		//LoginSignUpWindowFrame.setExtendedState(JFrame.NORMAL);

		LoginSignUpWindowFrame.setTitle("Welcome to Alchemists!");
		LoginSignUpWindowFrame.setResizable(false);
		LoginSignUpWindowFrame.setSize(GlobalDimensions.FULL_SCREEN);
		LoginSignUpWindowFrame.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		LoginSignUpWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginSignUpWindowFrame.setBounds(0, 0, 1920, 1080);

		contentPane = new JPanel();
		contentPane.setBackground(GlobalColors.BACKGROUND_COLOR);
		contentPane.setSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setMinimumSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setMaximumSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
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

		JPanel loginSignUpFormPanel = new JPanel();
		loginSignUpFormPanel.setOpaque(false);
		loginSignUpFormPanel.setBounds(995, 242, 466, 582);
		contentPane.add(loginSignUpFormPanel);
		loginSignUpFormPanel.setLayout(new GridLayout(0, 2, 1, 25));


		loginHeaderText = new JTextField();
		loginHeaderText.setEditable(false);
		loginHeaderText.setBorder(null);
		loginHeaderText.setForeground(GlobalColors.TEXT_COLOR);
		loginHeaderText.setFont(GlobalFonts.DISPLAY_HEADER_FONT);
		loginHeaderText.setOpaque(false);
		loginHeaderText.setText("LOG IN");
		loginSignUpFormPanel.add(loginHeaderText);
		loginHeaderText.setColumns(10);

		JLabel emptyLabel1 = new JLabel("");
		loginSignUpFormPanel.add(emptyLabel1);

		loginUserNameDisplay = new JTextField();
		loginUserNameDisplay.setEditable(false);
		loginUserNameDisplay.setBorder(null);
		loginUserNameDisplay.setText("Username");
		loginUserNameDisplay.setOpaque(false);
		loginUserNameDisplay.setForeground(GlobalColors.TEXT_COLOR);
		loginUserNameDisplay.setFont(GlobalFonts.DISPLAYFONT);
		loginUserNameDisplay.setColumns(10);
		loginSignUpFormPanel.add(loginUserNameDisplay);

		loginUserNameInputTextField = new JTextField();
		loginUserNameInputTextField.setBorder(new EmptyBorder(0, 3, 0, 0));
		loginUserNameInputTextField.setFont(GlobalFonts.INPUTFONT);
		loginSignUpFormPanel.add(loginUserNameInputTextField);
		loginUserNameInputTextField.setColumns(10);

		loginPasswordDisplay = new JTextField();
		loginPasswordDisplay.setEditable(false);
		loginPasswordDisplay.setBorder(null);
		loginPasswordDisplay.setText("Password");
		loginPasswordDisplay.setOpaque(false);
		loginPasswordDisplay.setForeground(GlobalColors.TEXT_COLOR);
		loginPasswordDisplay.setFont(GlobalFonts.DISPLAYFONT);
		loginPasswordDisplay.setColumns(10);
		loginSignUpFormPanel.add(loginPasswordDisplay);

		loginPasswordField = new JPasswordField();
		loginPasswordField.setBorder(new EmptyBorder(0, 3, 0, 0));
		loginPasswordField.setFont(GlobalFonts.PASSWORDFONT);
		loginSignUpFormPanel.add(loginPasswordField);

		JLabel emptyLabel2 = new JLabel("");
		loginSignUpFormPanel.add(emptyLabel2);

		loginButton = new JButton("Log In");
		loginButton.setBackground(GlobalColors.BUTTON_COLOR);
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setBorder(null);
		loginButton.setRequestFocusEnabled(false);
		loginButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		loginButton.setFont(GlobalFonts.DISPLAYFONT);
		loginButton.addActionListener(e -> {
			//TODO log in action
			String loginUserNameInput = loginUserNameInputTextField.getText();
			char[] loginPasswordInput = loginPasswordField.getPassword();
			
			LoginSignupController.getInstance().login(loginUserNameInput, new String(loginPasswordInput));
		}
				);
		loginSignUpFormPanel.add(loginButton);

		signUpHeaderText = new JTextField();
		signUpHeaderText.setText("SIGN UP");
		signUpHeaderText.setOpaque(false);
		signUpHeaderText.setForeground(GlobalColors.TEXT_COLOR);
		signUpHeaderText.setFont(GlobalFonts.DISPLAY_HEADER_FONT);
		signUpHeaderText.setEditable(false);
		signUpHeaderText.setColumns(10);
		signUpHeaderText.setBorder(null);
		loginSignUpFormPanel.add(signUpHeaderText);

		JLabel emptyLabel3 = new JLabel("");
		loginSignUpFormPanel.add(emptyLabel3);

		signUpUserNameText = new JTextField();
		signUpUserNameText.setText("Username");
		signUpUserNameText.setOpaque(false);
		signUpUserNameText.setForeground(GlobalColors.TEXT_COLOR);
		signUpUserNameText.setFont(GlobalFonts.DISPLAYFONT);
		signUpUserNameText.setEditable(false);
		signUpUserNameText.setColumns(10);
		signUpUserNameText.setBorder(null);
		loginSignUpFormPanel.add(signUpUserNameText);

		signUpUserNameInputTextField = new JTextField();
		signUpUserNameInputTextField.setBorder(new EmptyBorder(0, 3, 0, 0));
		signUpUserNameInputTextField.setFont(GlobalFonts.INPUTFONT);
		signUpUserNameInputTextField.setColumns(10);
		loginSignUpFormPanel.add(signUpUserNameInputTextField);

		signUpPasswordDisplayText = new JTextField();
		signUpPasswordDisplayText.setText("Password");
		signUpPasswordDisplayText.setOpaque(false);
		signUpPasswordDisplayText.setForeground(GlobalColors.TEXT_COLOR);
		signUpPasswordDisplayText.setFont(GlobalFonts.DISPLAYFONT);
		signUpPasswordDisplayText.setEditable(false);
		signUpPasswordDisplayText.setColumns(10);
		signUpPasswordDisplayText.setBorder(null);
		loginSignUpFormPanel.add(signUpPasswordDisplayText);

		signUpPasswordField = new JPasswordField();
		signUpPasswordField.setBorder(new EmptyBorder(0, 3, 0, 0));
		signUpPasswordField.setFont(GlobalFonts.PASSWORDFONT);
		loginSignUpFormPanel.add(signUpPasswordField);

		JLabel emptyLabel4 = new JLabel("");
		loginSignUpFormPanel.add(emptyLabel4);

		JButton signUpButton = new JButton("Sign Up");
		signUpButton.setBackground(GlobalColors.BUTTON_COLOR);
		signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpButton.setBorder(null);
		signUpButton.setRequestFocusEnabled(false);
		signUpButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		signUpButton.setFont(GlobalFonts.DISPLAYFONT);
		signUpButton.addActionListener(e -> {
			//TODO sign up action
			String signUpUserNameInput = signUpUserNameInputTextField.getText();
			char[] signUpPasswordInput = signUpPasswordField.getPassword();
			LoginSignupController.getInstance().signup(signUpUserNameInput, new String(signUpPasswordInput));
		}
				);
		loginSignUpFormPanel.add(signUpButton);
		contentPane.add(closeButton);


		alchemistImageLabel = new JLabel();
		alchemistImageLabel.setBounds(110, 121, 766, 766);
		alchemistImageLabel.setIcon(new ImageIcon(LoginSignUpWindow.class.getResource("/userinterface/images/alchemist_square.png")));
		contentPane.add(alchemistImageLabel);


		LoginSignUpWindowFrame.setVisible(true);
	}
}
