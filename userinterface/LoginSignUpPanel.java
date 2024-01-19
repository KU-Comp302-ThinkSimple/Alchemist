package userinterface;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domain.loginSignup.LoginSignupController;
import domain.LocalData;
import domain.initialization.GameInitializerAdapter;
import domain.initialization.OfflineGameInitializerAdapter;
import domain.initialization.OnlineClientGameInitializerAdapter;
import domain.initialization.OnlineHostGameInitializerAdapter;
import domain.loginSignup.*;
import userinterface.util.GlobalColors;
import userinterface.util.GlobalFonts;

//this PANEL LOGS ONLY 1 PLAYER IN
public class LoginSignUpPanel extends JPanel {

	private int loggedinUserCount = 0;
	
	private GameInitializerAdapter gameInitializer;


	public LoginSignUpPanel() {

		this.setBackground(GlobalColors.BACKGROUND_COLOR);
		this.setOpaque(true);
		this.setSize(470, 580);
		this.setLayout(new GridLayout(0, 2, 1, 25));


		JTextField loginHeaderText = new JTextField();
		loginHeaderText.setEditable(false);
		loginHeaderText.setBorder(null);
		loginHeaderText.setForeground(GlobalColors.TEXT_COLOR);
		loginHeaderText.setFont(GlobalFonts.DISPLAY_HEADER);
		loginHeaderText.setOpaque(false);
		loginHeaderText.setText("LOG IN");
		this.add(loginHeaderText);
		loginHeaderText.setColumns(10);

		JLabel emptyLabel1 = new JLabel("");
		this.add(emptyLabel1);

		JTextField loginUserNameDisplay = new JTextField();
		loginUserNameDisplay.setEditable(false);
		loginUserNameDisplay.setBorder(null);
		loginUserNameDisplay.setText("Username");
		loginUserNameDisplay.setOpaque(false);
		loginUserNameDisplay.setForeground(GlobalColors.TEXT_COLOR);
		loginUserNameDisplay.setFont(GlobalFonts.DISPLAY);
		loginUserNameDisplay.setColumns(10);
		this.add(loginUserNameDisplay);

		JTextField loginUserNameInputTextField = new JTextField();
		loginUserNameInputTextField.setBorder(new EmptyBorder(0, 3, 0, 0));
		loginUserNameInputTextField.setFont(GlobalFonts.INPUT);
		this.add(loginUserNameInputTextField);
		loginUserNameInputTextField.setColumns(10);

		JTextField loginPasswordDisplay = new JTextField();
		loginPasswordDisplay.setEditable(false);
		loginPasswordDisplay.setBorder(null);
		loginPasswordDisplay.setText("Password");
		loginPasswordDisplay.setOpaque(false);
		loginPasswordDisplay.setForeground(GlobalColors.TEXT_COLOR);
		loginPasswordDisplay.setFont(GlobalFonts.DISPLAY);
		loginPasswordDisplay.setColumns(10);
		this.add(loginPasswordDisplay);

		JPasswordField loginPasswordField = new JPasswordField();
		loginPasswordField.setBorder(new EmptyBorder(0, 3, 0, 0));
		loginPasswordField.setFont(GlobalFonts.PASSWORD);
		this.add(loginPasswordField);

		JTextField loginMessageDisplay = new JTextField();
		loginMessageDisplay.setEditable(false);
		loginMessageDisplay.setBorder(null);
		loginMessageDisplay.setText(" ");
		loginMessageDisplay.setOpaque(false);
		loginMessageDisplay.setForeground(GlobalColors.ERROR_MESSAGE);
		loginMessageDisplay.setFont(GlobalFonts.ERROR_MESSAGE);
		loginMessageDisplay.setVisible(false);
		loginMessageDisplay.setColumns(10);
		this.add(loginMessageDisplay);


		JButton loginButton = new JButton("Log In");
		loginButton.setBackground(GlobalColors.BUTTON_COLOR);
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setBorder(null);
		loginButton.setRequestFocusEnabled(false);
		loginButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		loginButton.setFont(GlobalFonts.DISPLAY);
		loginButton.addActionListener(e -> {
			String loginUserNameInput = loginUserNameInputTextField.getText();
			String loginPasswordInput = new String(loginPasswordField.getPassword());
			
			if(gameInitializer == null) {
				throw new RuntimeException("GameInitializer cannot be null.");
			}
			
			if(gameInitializer instanceof OfflineGameInitializerAdapter) {
				//log in action
				LoginResult result = LoginSignupController.getInstance().login(loginUserNameInput, new String(loginPasswordInput));

				if (result.isSuccess()) {

					loggedinUserCount += 1;

					loginMessageDisplay.setText(result.getMessage());
					loginMessageDisplay.setVisible(true);

					//clear editables
					loginUserNameInputTextField.setText(null);
					loginPasswordField.setText(null);


					//log in button invisible now
					((JButton) e.getSource()).setVisible(false);

				}
				//log in unsuccessful
				else {
					loginMessageDisplay.setText(result.getMessage());
				}
			}
			else {
				HashMap<String, Object> settings = new HashMap<>();
				settings.put("password", loginPasswordInput);
				settings.put("username", loginUserNameInput);
				
				try {
					gameInitializer.finalizeInitialization(settings);
					if(gameInitializer instanceof OnlineHostGameInitializerAdapter) {
						System.out.println("Loading host screen");
						System.out.println(LocalData.getInstance().getLocalPlayer().getPlayerName());
						new MainGameWindowOnline();
					}
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}
			}
			
		}
				);
		this.add(loginButton);

		JTextField signUpHeaderText = new JTextField();
		signUpHeaderText.setText("SIGN UP");
		signUpHeaderText.setOpaque(false);
		signUpHeaderText.setForeground(GlobalColors.TEXT_COLOR);
		signUpHeaderText.setFont(GlobalFonts.DISPLAY_HEADER);
		signUpHeaderText.setEditable(false);
		signUpHeaderText.setColumns(10);
		signUpHeaderText.setBorder(null);
		this.add(signUpHeaderText);

		JLabel emptyLabel3 = new JLabel("");
		this.add(emptyLabel3);

		JTextField signUpUserNameText = new JTextField();
		signUpUserNameText.setText("Username");
		signUpUserNameText.setOpaque(false);
		signUpUserNameText.setForeground(GlobalColors.TEXT_COLOR);
		signUpUserNameText.setFont(GlobalFonts.DISPLAY);
		signUpUserNameText.setEditable(false);
		signUpUserNameText.setColumns(10);
		signUpUserNameText.setBorder(null);
		this.add(signUpUserNameText);

		JTextField signUpUserNameInputTextField = new JTextField();
		signUpUserNameInputTextField.setBorder(new EmptyBorder(0, 3, 0, 0));
		signUpUserNameInputTextField.setFont(GlobalFonts.INPUT);
		signUpUserNameInputTextField.setColumns(10);
		this.add(signUpUserNameInputTextField);

		JTextField signUpPasswordDisplayText = new JTextField();
		signUpPasswordDisplayText.setText("Password");
		signUpPasswordDisplayText.setOpaque(false);
		signUpPasswordDisplayText.setForeground(GlobalColors.TEXT_COLOR);
		signUpPasswordDisplayText.setFont(GlobalFonts.DISPLAY);
		signUpPasswordDisplayText.setEditable(false);
		signUpPasswordDisplayText.setColumns(10);
		signUpPasswordDisplayText.setBorder(null);
		this.add(signUpPasswordDisplayText);

		JPasswordField signUpPasswordField = new JPasswordField();
		signUpPasswordField.setBorder(new EmptyBorder(0, 3, 0, 0));
		signUpPasswordField.setFont(GlobalFonts.PASSWORD);
		this.add(signUpPasswordField);

		JTextField signUpMessage = new JTextField();
		signUpMessage.setEditable(false);
		signUpMessage.setBorder(null);
		signUpMessage.setText("");
		signUpMessage.setOpaque(false);
		signUpMessage.setForeground(GlobalColors.ERROR_MESSAGE);
		signUpMessage.setFont(GlobalFonts.ERROR_MESSAGE);
		signUpMessage.setVisible(false);
		this.add(signUpMessage);
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
			String signUpPasswordInput = new String(signUpPasswordField.getPassword());
			
			if(gameInitializer instanceof OnlineClientGameInitializerAdapter) {
				try {
					SignupResult result = LocalData.getInstance().getClient().remoteSignupBlocking(signUpUserNameInput, signUpPasswordInput, 5000);

					signUpMessage.setText(result.getMessage());
					signUpMessage.setVisible(true);
				} catch (TimeoutException e1) {
					System.out.println("Signup request timed out");
					e1.printStackTrace();
				}
			}
			else {
				SignupResult result = LoginSignupController.getInstance().signup(signUpUserNameInput, signUpPasswordInput);

				signUpMessage.setText(result.getMessage());
				signUpMessage.setVisible(true);
			}
			

			//clear editible textfields
			signUpUserNameInputTextField.setText("");
			signUpPasswordField.setText(null);
		}
				);
		this.add(signUpButton);

		this.setVisible(true);
		this.repaint();
	}
	

	public void setGameInitializer(GameInitializerAdapter gameInitializer) {
		this.gameInitializer = gameInitializer;
	}
}
