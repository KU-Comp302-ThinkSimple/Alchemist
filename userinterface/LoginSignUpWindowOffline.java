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

import domain.GameController;
import domain.loginSignup.LoginSignupController;
import domain.loginSignup.*;
import domain.initialization.GameInitializerAdapter;
import domain.initialization.OfflineGameInitializerAdapter;
import userinterface.util.GlobalColors;
import userinterface.util.GlobalDimensions;
import userinterface.util.GlobalFonts;

import userinterface.*;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;

public class LoginSignUpWindowOffline {

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
	private JLabel user1info;
	private JLabel user2info;
	private JLabel user3info;
	private JLabel user4info;

	private int loggedinUserCount = 0;
	private JTextField loginMessageDisplay;
	private JTextField signUpMessage;



	public LoginSignUpWindowOffline(OfflineGameInitializerAdapter gameInitializer) {

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
		closeButton.setFont(GlobalFonts.DISPLAY);
		closeButton.setBounds(1497, 141, 60, 39);
		closeButton.addActionListener(e -> {
			LoginSignUpWindowFrame.dispose();
		}
				);


		JButton startGameButton = new JButton("Start Game");
		startGameButton.setRequestFocusEnabled(false);
		startGameButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		startGameButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		startGameButton.setBorder(null);
		startGameButton.setBackground(GlobalColors.BUTTON_COLOR);
		startGameButton.setBounds(1497, 543, 232, 50);
		startGameButton.setVisible(false);
		startGameButton.addActionListener(e -> {
			try {
				gameInitializer.finalizeInitialization(null);
				LoginSignUpWindowFrame.dispose();
				new MainGameWindowOffline();
			}
			catch (Exception exc) {
				System.out.println("Cannot initialize game: " + exc.getMessage());
				exc.printStackTrace();
			}
		}
				);
		contentPane.add(startGameButton);


		JPanel loggedinUserInfo = new JPanel();
		loggedinUserInfo.setBounds(1497, 242, 290, 277);
		loggedinUserInfo.setOpaque(false);
		loggedinUserInfo.setLayout(new GridLayout(0, 1, 0, 0));


		JLabel user1 = new JLabel("User 1");
		user1.setForeground(GlobalColors.TEXT_COLOR);
		user1.setFont(GlobalFonts.DISPLAY);
		loggedinUserInfo.add(user1);

		user1info  = new JLabel();
		user1info.setVisible(false);
		user1info.setForeground(GlobalColors.TEXT_COLOR);
		user1info.setFont(GlobalFonts.DISPLAY);
		loggedinUserInfo.add(user1info);

		JLabel user2 = new JLabel("User 2");
		user2.setForeground(GlobalColors.TEXT_COLOR);
		user2.setFont(GlobalFonts.DISPLAY);
		loggedinUserInfo.add(user2);

		user2info = new JLabel();
		user2info.setVisible(false);
		user2info.setForeground(GlobalColors.TEXT_COLOR);
		user2info.setFont(GlobalFonts.DISPLAY);
		loggedinUserInfo.add(user2info);

		JLabel user3 = new JLabel("User 3");
		user3.setForeground(GlobalColors.TEXT_COLOR);
		user3.setFont(GlobalFonts.DISPLAY);
		loggedinUserInfo.add(user3);

		user3info = new JLabel();
		user3info.setVisible(false);
		user3info.setForeground(GlobalColors.TEXT_COLOR);
		user3info.setFont(GlobalFonts.DISPLAY);
		loggedinUserInfo.add(user3info);

		JLabel user4 = new JLabel("User 4");
		user4.setForeground(GlobalColors.TEXT_COLOR);
		user4.setFont(GlobalFonts.DISPLAY);
		loggedinUserInfo.add(user4);

		user4info = new JLabel();
		user4info.setVisible(false);
		user4info.setForeground(GlobalColors.TEXT_COLOR);
		user4info.setFont(GlobalFonts.DISPLAY);
		loggedinUserInfo.add(user4info);

		contentPane.add(loggedinUserInfo);

		JPanel loginSignUpFormPanel = new JPanel();
		loginSignUpFormPanel.setOpaque(false);
		loginSignUpFormPanel.setBounds(995, 242, 466, 582);
		contentPane.add(loginSignUpFormPanel);
		loginSignUpFormPanel.setLayout(new GridLayout(0, 2, 1, 25));


		loginHeaderText = new JTextField();
		loginHeaderText.setEditable(false);
		loginHeaderText.setBorder(null);
		loginHeaderText.setForeground(GlobalColors.TEXT_COLOR);
		loginHeaderText.setFont(GlobalFonts.DISPLAY_HEADER);
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
		loginUserNameDisplay.setFont(GlobalFonts.DISPLAY);
		loginUserNameDisplay.setColumns(10);
		loginSignUpFormPanel.add(loginUserNameDisplay);

		loginUserNameInputTextField = new JTextField();
		loginUserNameInputTextField.setBorder(new EmptyBorder(0, 3, 0, 0));
		loginUserNameInputTextField.setFont(GlobalFonts.INPUT);
		loginSignUpFormPanel.add(loginUserNameInputTextField);
		loginUserNameInputTextField.setColumns(10);

		loginPasswordDisplay = new JTextField();
		loginPasswordDisplay.setEditable(false);
		loginPasswordDisplay.setBorder(null);
		loginPasswordDisplay.setText("Password");
		loginPasswordDisplay.setOpaque(false);
		loginPasswordDisplay.setForeground(GlobalColors.TEXT_COLOR);
		loginPasswordDisplay.setFont(GlobalFonts.DISPLAY);
		loginPasswordDisplay.setColumns(10);
		loginSignUpFormPanel.add(loginPasswordDisplay);

		loginPasswordField = new JPasswordField();
		loginPasswordField.setBorder(new EmptyBorder(0, 3, 0, 0));
		loginPasswordField.setFont(GlobalFonts.PASSWORD);
		loginSignUpFormPanel.add(loginPasswordField);

		loginMessageDisplay = new JTextField();
		loginMessageDisplay.setEditable(false);
		loginMessageDisplay.setBorder(null);
		loginMessageDisplay.setText("error");
		loginMessageDisplay.setOpaque(false);
		loginMessageDisplay.setForeground(GlobalColors.ERROR_MESSAGE);
		loginMessageDisplay.setFont(GlobalFonts.ERROR_MESSAGE);
		loginMessageDisplay.setVisible(false);
		loginMessageDisplay.setColumns(10);
		loginSignUpFormPanel.add(loginMessageDisplay);


		loginButton = new JButton("Log In");
		loginButton.setBackground(GlobalColors.BUTTON_COLOR);
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setBorder(null);
		loginButton.setRequestFocusEnabled(false);
		loginButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		loginButton.setFont(GlobalFonts.DISPLAY);
		loginButton.addActionListener(e -> {

			String loginUserNameInput = loginUserNameInputTextField.getText();
			char[] loginPasswordInput = loginPasswordField.getPassword();

			//log in action
			LoginResult result = LoginSignupController.getInstance().login(loginUserNameInput, new String(loginPasswordInput));
			if (result.isSuccess()) {

				loggedinUserCount += 1;

				loginMessageDisplay.setText("");
				loginMessageDisplay.setVisible(true);

				//clear editables
				loginUserNameInputTextField.setText(null);
				loginPasswordField.setText(null);

				switch (loggedinUserCount) {
				case 1 :
					//user1 info display
					user1info.setText(loginUserNameInput);
					user1info.setVisible(true);
					break;
				case 2 :
					user2info.setText(loginUserNameInput);
					user2info.setVisible(true);

					//start game button active
					startGameButton.setVisible(true);
					break;
				case 3 :
					user3info.setText(loginUserNameInput);
					user3info.setVisible(true);
					break;
				case 4 :
					user4info.setText(loginUserNameInput);
					user4info.setVisible(true);
					//log in button deactivated
					loginButton.setVisible(false);
					break;
				}

			}
			//log in unsuccessful
			else {
				loginMessageDisplay.setText(result.getMessage());
			}
		}
				);
		loginSignUpFormPanel.add(loginButton);

		signUpHeaderText = new JTextField();
		signUpHeaderText.setText("SIGN UP");
		signUpHeaderText.setOpaque(false);
		signUpHeaderText.setForeground(GlobalColors.TEXT_COLOR);
		signUpHeaderText.setFont(GlobalFonts.DISPLAY_HEADER);
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
		signUpUserNameText.setFont(GlobalFonts.DISPLAY);
		signUpUserNameText.setEditable(false);
		signUpUserNameText.setColumns(10);
		signUpUserNameText.setBorder(null);
		loginSignUpFormPanel.add(signUpUserNameText);

		signUpUserNameInputTextField = new JTextField();
		signUpUserNameInputTextField.setBorder(new EmptyBorder(0, 3, 0, 0));
		signUpUserNameInputTextField.setFont(GlobalFonts.INPUT);
		signUpUserNameInputTextField.setColumns(10);
		loginSignUpFormPanel.add(signUpUserNameInputTextField);

		signUpPasswordDisplayText = new JTextField();
		signUpPasswordDisplayText.setText("Password");
		signUpPasswordDisplayText.setOpaque(false);
		signUpPasswordDisplayText.setForeground(GlobalColors.TEXT_COLOR);
		signUpPasswordDisplayText.setFont(GlobalFonts.DISPLAY);
		signUpPasswordDisplayText.setEditable(false);
		signUpPasswordDisplayText.setColumns(10);
		signUpPasswordDisplayText.setBorder(null);
		loginSignUpFormPanel.add(signUpPasswordDisplayText);

		signUpPasswordField = new JPasswordField();
		signUpPasswordField.setBorder(new EmptyBorder(0, 3, 0, 0));
		signUpPasswordField.setFont(GlobalFonts.PASSWORD);
		loginSignUpFormPanel.add(signUpPasswordField);

		signUpMessage = new JTextField();
		signUpMessage.setEditable(false);
		signUpMessage.setBorder(null);
		signUpMessage.setText("error");
		signUpMessage.setOpaque(false);
		signUpMessage.setForeground(GlobalColors.ERROR_MESSAGE);
		signUpMessage.setFont(GlobalFonts.ERROR_MESSAGE);
		signUpMessage.setVisible(false);
		loginSignUpFormPanel.add(signUpMessage);
		signUpMessage.setColumns(10);

		JButton signUpButton = new JButton("Sign Up");
		signUpButton.setBackground(GlobalColors.BUTTON_COLOR);
		signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpButton.setBorder(null);
		signUpButton.setRequestFocusEnabled(false);
		signUpButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		signUpButton.setFont(GlobalFonts.DISPLAY);
		signUpButton.addActionListener(e -> {

			//sign up action
			String signUpUserNameInput = signUpUserNameInputTextField.getText();
			char[] signUpPasswordInput = signUpPasswordField.getPassword();
			SignupResult result = LoginSignupController.getInstance().signup(signUpUserNameInput, new String(signUpPasswordInput));

			signUpMessage.setText(result.getMessage());
			signUpMessage.setVisible(true);

			//clear editible textfields
			signUpUserNameInputTextField.setText("");
			signUpPasswordField.setText(null);
		}
				);
		loginSignUpFormPanel.add(signUpButton);
		contentPane.add(closeButton);


		alchemistImageLabel = new JLabel();
		alchemistImageLabel.setBounds(110, 121, 766, 766);
		alchemistImageLabel.setIcon(new ImageIcon(LoginSignUpWindowOffline.class.getResource("/userinterface/images/alchemist_square.png")));
		contentPane.add(alchemistImageLabel);





		LoginSignUpWindowFrame.setVisible(true);
	}
}
