package domain.boards;

public class PotionBrewingBoard extends Board{
    public PotionBrewingBoard(int size, String name) {
        super(size, name);
    }
    
    //TODO front end should tell this which ingredients are chosen
    //TODO first create recipe using the two ingredients and then create a potion using the recipe, then add the potion to the inventory of the player(You can use currentPlayer from Game class).Check all the funcs under potion, recipe etc.
}
