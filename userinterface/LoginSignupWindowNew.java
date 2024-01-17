package userinterface;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import userinterface.util.GlobalIcons;

public class LoginSignupWindowNew extends JFrame {

	private JPanel contentPane;
	private JLabel alchemistImageLabel;
	private JTextField loginHeaderText;
	private int loggedinUserCount;

	public LoginSignupWindowNew() {


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
		offlineButton.addActionListener(e -> {
			new LoginSignUpWindow();
			this.dispose();
		});


		//JOIN BUTTON
		JButton joinButton = new JButton("Join Online Lobby");
		joinButton.setLocation(0, 125);
		joinButton.setBackground(GlobalColors.BUTTON_COLOR);
		joinButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		joinButton.setBorder(null);
		joinButton.setRequestFocusEnabled(false);
		joinButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		joinButton.setFont(GlobalFonts.DISPLAY);
		joinButton.addActionListener(e -> {
			//TODO
		});

		//HOST BUTTON
		JButton hostButton = new JButton("Host Online Lobby");
		hostButton.setLocation(0, 250);
		hostButton.setBackground(GlobalColors.BUTTON_COLOR);
		hostButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hostButton.setBorder(null);
		hostButton.setRequestFocusEnabled(false);
		hostButton.setForeground(GlobalColors.BUTTON_TEXT_COLOR);
		hostButton.setFont(GlobalFonts.DISPLAY);
		hostButton.addActionListener(e -> {
			//TODO
		});

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
	}
}
