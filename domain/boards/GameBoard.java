package domain.boards;

import java.io.Serializable;

public class GameBoard extends Board implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5471086281121837842L;
	private DeductionBoard deductionBoard;
    private IngredientBoard ingredientBoard;
    private PublicationBoard publicationBoard;
    private PotionBrewingBoard potionBrewingBoard;

    public GameBoard() {
        this.deductionBoard=new DeductionBoard();
        this.ingredientBoard=new IngredientBoard();
        this.publicationBoard=new PublicationBoard();
        this.potionBrewingBoard=new PotionBrewingBoard();
    }

	public DeductionBoard getDeductionBoard() {
		return (DeductionBoard) deductionBoard;
	}

	public void setDeductionBoard(DeductionBoard deductionBoard) {
		this.deductionBoard = deductionBoard;
	}

	public IngredientBoard getIngredientBoard() {
		return ingredientBoard;
	}

	public void setIngredientBoard(IngredientBoard ingredientBoard) {
		this.ingredientBoard = ingredientBoard;
	}

	public PublicationBoard getPublicationBoard() {
		return publicationBoard;
	}

	public void setPublicationBoard(PublicationBoard publicationBoard) {
		this.publicationBoard = publicationBoard;
	}

	public PotionBrewingBoard getPotionBrewingBoard() {
		return potionBrewingBoard;
	}

	public void setPotionBrewingBoard(PotionBrewingBoard potionBrewingBoard) {
		this.potionBrewingBoard = potionBrewingBoard;
	}

}
