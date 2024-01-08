package domain.boards;

import java.util.ArrayList;
import java.util.Collection;

import domain.cards.*;

/**
 * Concrete implementation of the ingredient deck which uses an ArrayList
 */
public class IngredientCardDeckArrayList extends RandomCardDeck<IngredientCard>{

	public IngredientCardDeckArrayList(int size, Collection<IngredientCard> cardSource) {
		super(size, cardSource, ArrayList.class);
	}

	@Override
	public IngredientCard popCard() {
		IngredientCard ret = (IngredientCard)((ArrayList<IngredientCard>) this.repr).remove(0);
		refill();
		return ret;
	}

	@Override
	public boolean repOk() {
		return repr.size() == getSize();
	}

}
