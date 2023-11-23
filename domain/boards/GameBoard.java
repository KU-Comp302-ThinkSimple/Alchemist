package domain.boards;

public class GameBoard extends Board {
    private Board deductionBoard;
    private Board ingredientBoard;
    private Board publicationBoard;
    private Board potionBrewingBoard;

    public GameBoard(int size, String name) {
        super(size, name);
    }

    public GameBoard(int size, String name, DeductionBoard deductionBoard, IngredientBoard ingredientBoard,
                     PublicationBoard publicationBoard, PotionBrewingBoard potionBrewingBoard) {
        super(size, name);
        this.deductionBoard = deductionBoard;
        this.ingredientBoard = ingredientBoard;
        this.publicationBoard = publicationBoard;
        this.potionBrewingBoard = potionBrewingBoard;
    }

    public GameBoard() {
        super();
    }

	public Board getDeductionBoard() {
		return deductionBoard;
	}

	public void setDeductionBoard(Board deductionBoard) {
		this.deductionBoard = deductionBoard;
	}

	public Board getIngredientBoard() {
		return ingredientBoard;
	}

	public void setIngredientBoard(Board ingredientBoard) {
		this.ingredientBoard = ingredientBoard;
	}

	public Board getPublicationBoard() {
		return publicationBoard;
	}

	public void setPublicationBoard(Board publicationBoard) {
		this.publicationBoard = publicationBoard;
	}

	public Board getPotionBrewingBoard() {
		return potionBrewingBoard;
	}

	public void setPotionBrewingBoard(Board potionBrewingBoard) {
		this.potionBrewingBoard = potionBrewingBoard;
	}
    
}
