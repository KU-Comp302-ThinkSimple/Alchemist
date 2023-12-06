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
import domain.potion.*;

public class ResultsTriangle extends JPanel {
	final private static ImageIcon TRANSPARENT = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/transparent_88x42.png"));
	
	final private static ImageIcon BLUE_PLUS = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/blueplus_67x67.png"));
	final private static ImageIcon RED_PLUS = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/redplus_67x67.png"));
	final private static ImageIcon GREEN_PLUS = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/greenplus_67x67.png"));
           
	final private static ImageIcon BLUE_MINUS = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/blueminus_67x67.png"));
	final private static ImageIcon RED_MINUS = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/redminus_67x67.png"));
	final private static ImageIcon GREEN_MINUS = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/greenminus_67x67.png"));
           
	final private static ImageIcon BACKGROUND = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/resultsTriangle_706x631.png"));

	final static private int CIRCLE_DEPTH=7; //how many circles each side of the triangle will have. 7 as there are 8 potions
	
	final private double scalingFactor; //scaling factor for the entire results triangle
	final private double circleOffsetX; // how many pixels each circle is offset by in the x direction
	final private double circleOffsetY; // how many pixels each circle is offset by in the y direction
	final private double circleSize; // size of the circles in pixels
	
	final private ArrayList<ResizableImage> circleImages;
	
	public ResultsTriangle() {
		this(0.6);
	}
	
	/**
	 * Create a ResultsTriangle object. All of the elements can be scaled by setting the scaling factor,
	 * however the resulting panel is not dynamic.
	 * @param scalingFactor
	 */
	public ResultsTriangle(double scalingFactor) {
		this.scalingFactor = scalingFactor;
		this.circleOffsetX = 87*scalingFactor;
		this.circleOffsetY = 1.002*circleOffsetX/2*Math.sqrt(3);
		this.circleSize = BLUE_MINUS.getIconWidth()*scalingFactor;
		circleImages = new ArrayList<ResizableImage>();

		
		//the size of the triangle background image determines the size of the panel
		double panelWidth = BACKGROUND.getIconWidth()*this.scalingFactor;
		double panelHeight = BACKGROUND.getIconHeight()*this.scalingFactor; 
		
		setLayout(null);//absolute layout makes arranging the cirles easier at the cost of dynamism
		setMaximumSize(new Dimension((int)panelWidth,(int)panelHeight));
		setMinimumSize(new Dimension((int)panelWidth,(int)panelHeight));
		
		ResizableImage backImg = new ResizableImage(BACKGROUND);
		backImg.setBounds(0, 0, (int)panelWidth, (int)panelHeight);//make the background image fill the panel
		
		add(backImg);
		
		//these dimensions denote the size of the bounding box around the circles.
		double triangleWidth = (CIRCLE_DEPTH-1)*circleOffsetX + circleSize;
		double triangleHeight = circleOffsetY*(CIRCLE_DEPTH-1) + circleSize;
		
		//these offsets help align the circles with the background image
		double triangleOffsetX =  (panelWidth-triangleWidth)/2 + 3.33*this.scalingFactor;
		double triangleOffsetY = 24*scalingFactor;
		
		//iterate over all rows
		for (int row = 0; row < CIRCLE_DEPTH; row++) {
			//determines how far left from the triangle bounding box the first circle's center will be 
			double rowInset = circleSize/2 + (CIRCLE_DEPTH-row-1)*circleOffsetX/2;
			//determines how far down from the triangle bounding box all the circles' centers in the row will be 
			double rowDepth = circleSize/2 + row*circleOffsetY;
			
			//iterate over all columns (circles in the row)
			for (int col = 0; col < row+1; col++) {
				ResizableImage circleImage;
				circleImage = new ResizableImage(TRANSPARENT); //the circles are transparent by default
				
				//determine the center location wrt. the panel
				double centerX = rowInset + col*circleOffsetX + triangleOffsetX;
				double centerY = rowDepth + triangleOffsetY;
				
				circleImage.setBounds(new Rectangle((int)(centerX-circleSize/2), (int)(centerY-circleSize/2), (int)circleSize, (int)circleSize));
				add(circleImage);
				circleImages.add(circleImage);
			}
		}
	}
	
	/**
	 * set the icon of the circle corresponding to the combination of potion 1 and potion 2
	 * @param potion1Idx index of the first potion
	 * @param potion2Idx index of the second potion
	 * @param iconIdx index of the icon to set the circle to
	 */
	public void setCircleImage(int potion1Idx, int potion2Idx, int iconIdx) {
		int idx = potionIdxToListIdx(potion1Idx, potion2Idx);
		ResizableImage img = circleImages.get(idx);
		img.setImage(iconIdxtoImageIcon(iconIdx));
	}
	
	public void setTriangleUsingPotionList(Iterable<Potion> potions) {
		for (Potion potion : potions) {
			Recipe recipe = potion.getPotionRecipe();
		}
	}
	
	/**
	 * clears the triangle
	 */
	public void clearTriangle() {
		for (ResizableImage resizableImage : circleImages) {
			resizableImage.setImage(TRANSPARENT);
		}
	}
	
	/**
	 * retrieve the icons using the icon idx
	 * @param idx
	 * @return
	 */
	private ImageIcon iconIdxtoImageIcon(int idx) {
		switch (idx){
		case 0: {
			return RED_MINUS;
		}
		case 1: {
			return GREEN_MINUS;
		}
		case 2: {
			return BLUE_MINUS;
		}
		case 3: {
			return RED_PLUS;
		}
		case 4: {
			return GREEN_PLUS;
		}
		case 5: {
			return BLUE_PLUS;
		}
		default:
			return TRANSPARENT;
		}
	}
	
	/**
	 * Converts row index and column index (which circle from the right it is in the row)
	 * to the index in the arrayList of circle images
	 * @param row
	 * @param col
	 * @return
	 */
	private int rowColToListIdx(int row, int col) {
		//since the circle images are kept in an arrayList, we need to be able to convert the row and column
		//into the array index
		int rowFirstIdx = (row)*(row+1)/2;//calculate the index of the first circle in the row
		return rowFirstIdx + col;//add the column
	}
	
	
	/**
	 * Converts the index of an element from the upper-triangular matrix representation
	 * to the row-col representation
	 * @param p column major upper triangular matrix index
	 * @return
	 * @throws RuntimeException
	 */
	private Point upperTriangularToRowCol(Point p) throws RuntimeException{
		//the triangle can be represented as an upper triangular matrix of size (CIRCLE_DEPTH,CIRCLE_DEPTH)
		//this way the potion index (with minor changes) corresponds to the matrix's columns and rows.
		//The rows of the triangle increase in the -x direction of the parameter p
		//and the columns of the triangle increase in the [1,1] vector direction of p
		//with some linear algebra, the two repr. can be coverted to eachother
		
		//make sure the index falls within the upper triangular matrix
		if(p.x<p.y) {
			throw new RuntimeException("Upper triangular matrix col idx must be higher than or equal to row idx");
		}
		return new Point(CIRCLE_DEPTH-1-p.x+p.y, p.y);//do the conversion
	}
	
	/**
	 * converts the potion indexes of the deduction board to the circle image arraylist idx.
	 * @param potionIdx1
	 * @param potionIdx2
	 * @return
	 * @throws RuntimeException if the potion indexes are out of bounds
	 */
	private int potionIdxToListIdx(int potionIdx1, int potionIdx2) throws RuntimeException{
		//check potion indices
		if(potionIdx1>CIRCLE_DEPTH || potionIdx1<0) {
			throw new RuntimeException("potionIdx1 is out of bounds");
		}
		
		if(potionIdx2>CIRCLE_DEPTH || potionIdx2<0) {
			throw new RuntimeException("potionIdx2 is out of bounds");
		}
		
		//sort the potion indices
		int upperTriangleX,upperTriangleY;
		if(potionIdx1>potionIdx2) {
			upperTriangleX = potionIdx1;
			upperTriangleY = potionIdx2;
		}
		else {
			upperTriangleX = potionIdx2;
			upperTriangleY = potionIdx1;
		}
		
		upperTriangleX -= 1; // go from potion idx to upper triangular idx
		Point rowCol = upperTriangularToRowCol(new Point(upperTriangleX, upperTriangleY));
		return rowColToListIdx(rowCol.x, rowCol.y);
	}
}
