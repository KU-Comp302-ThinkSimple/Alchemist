package userinterface;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import userinterface.util.GlobalColors;
import userinterface.util.GlobalDimensions;

public class MainGameWindowOnline extends JFrame{

	JPanel contentPane;

	public MainGameWindowOnline() {

		this.setUndecorated(true);
		this.setMaximumSize(new Dimension(1920, 1080));
		this.setBounds(0, 0, 1920, 1080);
		//this.setExtendedState(JFrame.NORMAL);

		this.setTitle("Alchemists Online");
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
		contentPane.setLayout(null);
		this.setContentPane(contentPane);


	}

}
