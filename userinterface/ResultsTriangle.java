package userinterface;

import javax.swing.JPanel;
import javax.swing.text.html.ListView;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.lang.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ResultsTriangle extends JPanel {

	/**
	 * Create the panel.
	 */
	boolean isVisible = true;
	final ImageIcon circle = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/circle_800x800.png"));
	final ImageIcon cross = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/cross_88x42.png"));
	
	final ImageIcon transparent = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/transparent_88x42.png"));
	
	final ImageIcon bluePlus = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/blueplus_67x67.png"));
	final ImageIcon redPlus = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/redplus_67x67.png"));
	final ImageIcon greenPlus = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/greenplus_67x67.png"));

	final ImageIcon blueMinus = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/blueminus_67x67.png"));
	final ImageIcon redMinus = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/redminus_67x67.png"));
	final ImageIcon greenMinus = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/greenminus_67x67.png"));

	
	int circleDepth=7;
	int sideLength = 50;
	int sideLengthHeight = (int)Math.ceil(sideLength/2*Math.sqrt(3));
	int circleSize = 30;
	JComboBox potionSelector1;
	JComboBox potionSelector2;
	JComboBox iconSelector;
	
	ArrayList<ResizableImage> circleImages;
	public ResultsTriangle() {
		int panelWidth = (circleDepth-1)*sideLength + circleSize;
		int panelHeight = sideLengthHeight*(circleDepth-1) + circleSize;
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(null);
		panel.setMaximumSize(new Dimension(panelWidth,panelHeight));
		panel.setMinimumSize(new Dimension(panelWidth,panelHeight));
		
		circleImages = new ArrayList<ResizableImage>();
		for (int row = 0; row < circleDepth; row++) {
			int rowInset = circleSize/2 + (circleDepth-row-1)*sideLength/2; // circle center
			int rowDepth = circleSize/2 + row*sideLengthHeight;
			for (int col = 0; col < row+1; col++) {
				ResizableImage circleImage;
				circleImage = new ResizableImage(circle);
				int centerX = rowInset + col*sideLength;
				int centerY = rowDepth;
				circleImage.setBounds(new Rectangle(centerX-circleSize/2, centerY-circleSize/2, circleSize, circleSize));
				panel.add(circleImage);
				circleImages.add(circleImage);
			}
			System.out.println();
		}
		
		
		String[] potions = {"0","1","2","3","4","5","6","7"};
		String[] icons = {"rm", "gm", "bm", "rp", "gp", "bp", "empty"};
		JButton button = new JButton();
		potionSelector1 = new JComboBox(potions);
		potionSelector2 = new JComboBox(potions);
		iconSelector = new JComboBox(icons);
		potionSelector1.setMaximumSize(new Dimension(200, 40));
		potionSelector2.setMaximumSize(new Dimension(200, 40));
		iconSelector.setMaximumSize(new Dimension(200, 40));
		
		button.addActionListener(e -> {
			
			int idx = potionIdxToListIdx(potionSelector1.getSelectedIndex(), potionSelector2.getSelectedIndex());
			ResizableImage img = circleImages.get(idx);
			switch (iconSelector.getSelectedIndex()){
			case 0: {
				img.setImage(redMinus);
			}
			case 1: {
				img.setImage(greenMinus);
			}
			case 2: {
				img.setImage(blueMinus);
			}
			case 3: {
				img.setImage(redPlus);
			}
			case 4: {
				img.setImage(greenPlus);
			}
			case 5: {
				img.setImage(bluePlus);
			}
			default:
				img.setImage(transparent);
			}
		});
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(panel);
		add(potionSelector1);
		add(potionSelector2);
		add(iconSelector);
		add(button);
		
		this.validate();

	}
	
	private int rowColToListIdx(int row, int col) {
		int rowFirstIdx = (row)*(row+1)/2;
		return rowFirstIdx + col;
	}
	
	private Point upperTriangularToRowCol(Point p) throws RuntimeException{
		if(p.x<p.y) {
			throw new RuntimeException("Upper triangular matrix col idx must be higher than or equal to row idx");
		}
		return new Point(circleDepth-1-p.x+p.y, p.y);
	}
	
	private int potionIdxToListIdx(int potionIdx1, int potionIdx2) {
		int upperTriangleX,upperTriangleY;
		if(potionIdx1>potionIdx2) {
			upperTriangleX = potionIdx1;
			upperTriangleY = potionIdx2;
		}
		else {
			upperTriangleX = potionIdx2;
			upperTriangleY = potionIdx1;
		}
		upperTriangleX -= 1;
//		upperTriangleY -= 1;
		Point rowCol = upperTriangularToRowCol(new Point(upperTriangleX, upperTriangleY));
		return rowColToListIdx(rowCol.x, rowCol.y);
	}
}
