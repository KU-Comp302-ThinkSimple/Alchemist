package domain.boards;

public class GameBoard extends Board {
    private final Board deductionBoard;
    private final Board ingredientBoard;
    private final Board publicationBoard;
    private final Board potionBrewingBoard;
    private final Board marketplaceBoard;

    public GameBoard(int size, String name, DeductionBoard deductionBoard, IngredientBoard ingredientBoard,
                     PublicationBoard publicationBoard, PotionBrewingBoard potionBrewingBoard, MarketplaceBoard marketplaceBoard) {
        super(size, name);
        this.deductionBoard = deductionBoard;
        this.ingredientBoard = ingredientBoard;
        this.publicationBoard = publicationBoard;
        this.potionBrewingBoard = potionBrewingBoard;
        this.marketplaceBoard = marketplaceBoard;
    }

	public Board getDeductionBoard() {
		return deductionBoard;
	}

	public Board getIngredientBoard() {
		return ingredientBoard;
	}

	public Board getPublicationBoard() {
		return publicationBoard;
	}

	public Board getPotionBrewingBoard() {
		return potionBrewingBoard;
	}
	
	public Board getMarketplaceBoard() {
		return marketplaceBoard;
	}

//	public void setPotionBrewingBoard(Board potionBrewingBoard) {
//		this.potionBrewingBoard = potionBrewingBoard;
//	}
//    
//	public void setPublicationBoard(Board publicationBoard) {
//		this.publicationBoard = publicationBoard;
//	}
//	
//	public void setIngredientBoard(Board ingredientBoard) {
//		this.ingredientBoard = ingredientBoard;
//	}
//	
//	public void setDeductionBoard(Board deductionBoard) {
//		this.deductionBoard = deductionBoard;
//	}
}
