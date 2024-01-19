package userinterface;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domain.initialization.GameInitializerAdapter;
import domain.initialization.GameInitializerAdapterFactory;
import domain.initialization.GameInitializerAdapterFactory.InitializerType;
import domain.initialization.OfflineGameInitializerAdapter;
import userinterface.util.GlobalColors;
import userinterface.util.GlobalDimensions;
import userinterface.util.GlobalFonts;
import userinterface.util.GlobalIcons;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JLabel alchemistImageLabel;
	private JTextField loginHeaderText;
	private int loggedinUserCount;
	GameInitializerAdapter gameInitializer;
	private LoginSignUpPanel logsignPanel;

	public MainMenu() {
		this.setUndecorated(true);
		this.setMaximumSize(new Dimension(1920, 1080));
		this.setBounds(0, 0, 1920, 1080);

		this.setTitle("Welcome to Alchemists!");
		this.setResizable(false);
		this.setSize(GlobalDimensions.FULL_SCREEN);
		this.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 1920, 1080);

		//CONTENT PANE
		contentPane = new JPanel();
		contentPane.setBackground(GlobalColors.BACKGROUND_COLOR);
		contentPane.setSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setPreferredSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setMinimumSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setMaximumSize(GlobalDimensions.FULL_SCREEN);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);

		//CLOSE BUTTON
		JButton closeButton = new JButton("X");
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.setBorder(null);
		closeButton.setBackground(GlobalColors.BUTTON_COLOR);
		closeButton.setRequestFocusEnabled(false);
		closeButton.setFont(GlobalFonts.DISPLAY);
		closeButton.setBounds(1818, 47, 60, 39);
		closeButton.addActionListener(e -> {
			this.dispose();
		}
				);
		contentPane.add(closeButton);

		alchemistImageLabel = new JLabel();
		alchemistImageLabel.setBounds(110, 121, 766, 766);
		alchemistImageLabel.setIcon(GlobalIcons.getImage("alchemist square 1"));
		contentPane.add(alchemistImageLabel);


		//OFFLINE BUTTON
		JButton offlineButton = new JButton("Play Offline");
		offlineButton.setLocation(0, 0);
		offlineButton.setBackground(GlobalColors.BUTTON_COLOR);
		offlineButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		offlineButton.setBorder(null);
		offlineButton.setRequestFocusEnabled(false);
		offlineButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		offlineButton.setFont(GlobalFonts.DISPLAY);

		//JOIN BUTTON
		JButton joinButton = new JButton("Join Online Lobby");
		joinButton.setLocation(0, 125);
		joinButton.setBackground(GlobalColors.BUTTON_COLOR);
		joinButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		joinButton.setBorder(null);
		joinButton.setRequestFocusEnabled(false);
		joinButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		joinButton.setFont(GlobalFonts.DISPLAY);

		//HOST BUTTON
		JButton hostButton = new JButton("Host Online Lobby");
		hostButton.setLocation(0, 250);
		hostButton.setBackground(GlobalColors.BUTTON_COLOR);
		hostButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hostButton.setBorder(null);
		hostButton.setRequestFocusEnabled(false);
		hostButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		hostButton.setFont(GlobalFonts.DISPLAY);

		//GAME BUTTONS JPANEL
		JPanel buttons = new JPanel();
		buttons.setLocation(1262, 366);
		buttons.add(offlineButton);
		offlineButton.setSize(300,100);

		buttons.add(joinButton);
		joinButton.setSize(300,100);

		buttons.add(hostButton);
		hostButton.setSize(300,100);

		buttons.setSize(300, 350);
		buttons.setOpaque(false);
		buttons.setLayout(null);
		this.contentPane.add(buttons);

		logsignPanel = new LoginSignUpPanel();
		getContentPane().add(logsignPanel);
		logsignPanel.setLocation(937, 212);
		logsignPanel.setSize(470, 580);
		logsignPanel.setVisible(false);

		//ENTER LOBBY ID PANEL
		JPanel joinLobbyPanel = new JPanel();
		joinLobbyPanel.setBounds(1441, 212, 349, 185);
		joinLobbyPanel.setVisible(false);
		contentPane.add(joinLobbyPanel);

		//LOBBY ID HEADER
		JTextField loginHeaderText = new JTextField("ENTER LOBBY ID");
		loginHeaderText.setEditable(false);
		loginHeaderText.setBorder(null);
		loginHeaderText.setForeground(GlobalColors.TEXT_COLOR);
		loginHeaderText.setBackground(GlobalColors.BACKGROUND_COLOR);
		loginHeaderText.setFont(GlobalFonts.DISPLAY_HEADER);
		loginHeaderText.setOpaque(false);
//		loginHeaderText.setText();

		//LOBBY ID
		JTextField lobbyIDInputTextField = new JTextField();
		lobbyIDInputTextField.setBorder(new EmptyBorder(0, 3, 0, 0));
		lobbyIDInputTextField.setFont(GlobalFonts.INPUT);

		//JOIN LOBBY BUTTON
		JButton joinIDButton = new JButton("Join Lobby");
		joinIDButton.setBackground(GlobalColors.BUTTON_COLOR);
		joinIDButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		joinIDButton.setBorder(null);
		joinIDButton.setRequestFocusEnabled(false);
		joinIDButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		joinIDButton.setFont(GlobalFonts.DISPLAY);
		joinIDButton.addActionListener(e -> {
			String IDs = lobbyIDInputTextField.getText();
			gameInitializer = GameInitializerAdapterFactory.getInstance().getInitializerAdapter(InitializerType.OnlineClient);
			logsignPanel.setGameInitializer(gameInitializer);
			HashMap<String, Object> settings = new HashMap<>();
			settings.put("port", Integer.valueOf(4000));
			settings.put("hostAddress", IDs);
			try {
				gameInitializer.startInitialization(settings);
				logsignPanel.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//TODO backend calls to join lobby
		});
		joinLobbyPanel.setLayout(new GridLayout(3, 1, 0, 0));

		//JOIN LOBBY COMPONENTS PUT IN LABEL
		joinLobbyPanel.add(loginHeaderText);
		joinLobbyPanel.add(lobbyIDInputTextField);
		joinLobbyPanel.add(joinIDButton);
		getContentPane().add(joinLobbyPanel);

		//ONLINE LOBBY PANEL
		JPanel onlineLobbyPanel = new OnlineLobbyPanel();
		onlineLobbyPanel.setSize(onlineLobbyPanel.getPreferredSize());
		onlineLobbyPanel.setLocation(937, 121);
		onlineLobbyPanel.setVisible(false);

		//HOST GAME BUTTON (after logging in)
		JButton hostGameButton = new JButton("Host Game");
		hostGameButton.setSize(349, 100);
		hostGameButton.setLocation(1441, 476);
		hostGameButton.setBackground(GlobalColors.BUTTON_COLOR);
		hostGameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hostGameButton.setBorder(null);
		hostGameButton.setRequestFocusEnabled(false);
		hostGameButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		hostGameButton.setFont(GlobalFonts.DISPLAY);
		hostGameButton.addActionListener(e -> {
			gameInitializer = GameInitializerAdapterFactory.getInstance().getInitializerAdapter(InitializerType.OnlineHost);
			logsignPanel.setGameInitializer(gameInitializer);
			HashMap<String, Object> settings = new HashMap<>();
			settings.put("port", Integer.valueOf(4000));
			try {
				gameInitializer.startInitialization(settings);
				//TODO call needed functions from backend, open a lobby
				logsignPanel.setVisible(true);
				getContentPane().add(onlineLobbyPanel);
				onlineLobbyPanel.setVisible(true);
				((JButton) e.getSource()).setVisible(false);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		hostGameButton.setVisible(false);
		getContentPane().add(hostGameButton);



		//MAIN MENU OFFLINE GAME BUTTON
		offlineButton.addActionListener(e -> {
			gameInitializer = GameInitializerAdapterFactory.getInstance().getInitializerAdapter(InitializerType.Offline);
			logsignPanel.setGameInitializer(gameInitializer);
			try {
				gameInitializer.startInitialization(null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new LoginSignUpWindowOffline((OfflineGameInitializerAdapter)gameInitializer);
			this.dispose();
		});

		//MAIN MENU JOIN ONLINE LOBBY BUTTON
		joinButton.addActionListener(e -> {

			logsignPanel.setVisible(false);
			joinLobbyPanel.setVisible(true);
			buttons.setVisible(false);
			alchemistImageLabel.setIcon(GlobalIcons.getImage("alchemist square 2"));
			alchemistImageLabel.repaint();

		});

		//MAIN MENU HOST ONLINE LOBBY BUTTON
		hostButton.addActionListener(e -> {
			logsignPanel.setVisible(false);
			hostGameButton.setVisible(true);
			buttons.setVisible(false);
			alchemistImageLabel.setIcon(GlobalIcons.getImage("alchemist square 3"));
			alchemistImageLabel.repaint();
		});


		this.setVisible(true);
	}
}
