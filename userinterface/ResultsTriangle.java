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

import domain.GameController;
import domain.potion.*;
import domain.*;
import domain.player.*;

public class ResultsTriangle extends JPanel {
	final private static ImageIcon TRANSPARENT = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/transparent_88x42.png"));

	final private static ImageIcon BLUE_PLUS = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/blueplus_67x67.png"));
	final private static ImageIcon RED_PLUS = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/redplus_67x67.png"));
	final private static ImageIcon GREEN_PLUS = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/greenplus_67x67.png"));

	final private static ImageIcon BLUE_MINUS = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/blueminus_67x67.png"));
	final private static ImageIcon RED_MINUS = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/redminus_67x67.png"));
	final private static ImageIcon GREEN_MINUS = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/greenminus_67x67.png"));

	final private static ImageIcon NEUTRAL = new ImageIcon(ResultsTriangle.class.getResource("/userinterface/images/grayneutral_67x67.png"));

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
		this.setOpaque(false);


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
	 * updates the triangle to reflect the active user's potions
	 */
	public void updateResultsTriangle() {
		Player player = LocalData.getInstance().getLocalPlayer();
		updateTriangleUsingPotionList(player.getInventory().getPlayerPotionList());
	}

	/**
	 * set the icon of the circle corresponding to the combination of ingredient 1 and ingredient 2
	 * @param ingredient1Idx index of the first ingredient
	 * @param ingredient2Idx index of the second ingredient
	 * @param iconIdx index of the icon to set the circle to
	 */
	public void setCircleImage(int ingredient1Idx, int ingredient2Idx, int iconIdx) {
		setCircleImage(ingredient1Idx, ingredient2Idx, iconIdxtoImageIcon(iconIdx));
	}

	/**
	 * set the icon of the circle corresponding to the combination of ingredient 1 and ingredient 2
	 * @param ingredient1Idx index of the first ingredient
	 * @param ingredient2Idx index of the second ingredient
	 * @param ImageIcon to set the circle to
	 */
	public void setCircleImage(int ingredient1Idx, int ingredient2Idx, ImageIcon icon) {
		int idx = ingredientIdxToListIdx(ingredient1Idx, ingredient2Idx);
		ResizableImage img = circleImages.get(idx);
		img.setImage(icon);
	}

	/**
	 * clears triangle then updates the contents in accordance with the potions in the iterable.
	 * @param potions to update the triangle by
	 */
	public void updateTriangleUsingPotionList(Iterable<Potion> potions) {
		clearTriangle();
		for (Potion potion : potions) {
			potion.determinePotion();
			Recipe recipe = potion.getPotionRecipe();
			int ingredient1Idx = recipe.getIngredient1().getId();
			int ingredient2Idx = recipe.getIngredient2().getId();
			ImageIcon iconToSet = TRANSPARENT;
			if(recipe.checkRedMatch()==0) {
				iconToSet = RED_MINUS;

			}else if(recipe.checkRedMatch()==1) {

				iconToSet = RED_PLUS;
			}else if(recipe.checkGreenMatch()==0) {

				iconToSet = GREEN_MINUS;

			}else if(recipe.checkGreenMatch()==1) {
				iconToSet = GREEN_PLUS;

			}else if(recipe.checkBlueMatch()==0) {
				iconToSet = BLUE_MINUS;

			}else if(recipe.checkBlueMatch()==1) {
				iconToSet = BLUE_PLUS;
			}else {
				iconToSet = NEUTRAL;
				//				throw new RuntimeException("Neutral potion icon missing");
			}

			setCircleImage(ingredient1Idx, ingredient2Idx, iconToSet);
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
		//this way the ingredient index (with minor changes) corresponds to the matrix's columns and rows.
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
	 * converts the ingredient indexes of the deduction board to the circle image arraylist idx.
	 * @param ingredientIdx1
	 * @param ingredientIdx2
	 * @return
	 * @throws RuntimeException if the ingredient indexes are out of bounds
	 */
	private int ingredientIdxToListIdx(int ingredientIdx1, int ingredientIdx2) throws RuntimeException{
		//check ingredient indices
		if(ingredientIdx1>CIRCLE_DEPTH || ingredientIdx1<0) {
			throw new RuntimeException("ingredientIdx1 is out of bounds");
		}

		if(ingredientIdx2>CIRCLE_DEPTH || ingredientIdx2<0) {
			throw new RuntimeException("ingredientIdx2 is out of bounds");
		}

		//sort the ingredient indices
		int upperTriangleX,upperTriangleY;
		if(ingredientIdx1>ingredientIdx2) {
			upperTriangleX = ingredientIdx1;
			upperTriangleY = ingredientIdx2;
		}
		else {
			upperTriangleX = ingredientIdx2;
			upperTriangleY = ingredientIdx1;
		}

		upperTriangleX -= 1; // go from ingredient idx to upper triangular idx
		Point rowCol = upperTriangularToRowCol(new Point(upperTriangleX, upperTriangleY));
		return rowColToListIdx(rowCol.x, rowCol.y);
	}
}
