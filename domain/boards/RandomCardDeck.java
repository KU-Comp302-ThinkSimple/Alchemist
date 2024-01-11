package domain.boards;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import domain.cards.Card;

/**
 * Abstract datatype which represents a deck of cards. This deck is refilled with a random
 * element of the cardSource list once a card is drawn, 
 * such that it always has size many elements.
 * @param <T>
 */
public abstract class RandomCardDeck<T extends Card> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2544832921864585670L;

	private final ArrayList<T> cardSourceList;

	protected final Collection<T> repr;
	private final int size;
	/**
	 * @param size Size of the deck. Is constant and the deck always contains this many elements
	 * @param cardSource The source where new cards are pulled from. This isn't ever modified.
	 * @param implementingClass The collection class which is used to represent the deck.
	 */
	public RandomCardDeck(int size, Collection<T> cardSource, Class implementingClass) {
		this.size = size;
		cardSourceList = new ArrayList<T>(cardSource);
		try {
			repr = (Collection<T>)implementingClass.getDeclaredConstructor().newInstance();
		}
		catch (Exception e) {
			throw new RuntimeException("The provided implementingClass is invalid.");
		}
	}
	
	/**
	 * Pops a card from the deck
	 * @return A card from the deck
	 */
	abstract public T popCard();
	
	/**
	 * checks if the underlying representation is intact.
	 * @return
	 */
	abstract public boolean repOk();
	
	/**
	 * refills the deck.
	 */
	public void refill() {
		//EFFECTS: Random cards from cardSourceList is added to the deck until the deck is full.
		while(repr.size()<size) {
			addCardFromSource();
		}
	}
	
	/**
	 * Add a random card from the cardSourceList to the deck.
	 */
	private void addCardFromSource() {		
		//EFFECTS: A random card from cardSourceList is added to the deck
		Random rand = new Random();
		repr.add(cardSourceList.get(rand.nextInt(cardSourceList.size())));
	}
	
	public int getSize() {
		return size;
	}
	
	public ArrayList<T> getCards() {
		return new ArrayList<T>(repr);
	}
	
	/**
	 * reorders the deck 
	 * @param order The list of indices for which represents the new order of the list
	 */
	public void reorder(int[] order) {
		//EFFECTS: Reorders the cards in the deck with the indices denoted in the "order" list
		ArrayList<T> cards = new ArrayList<T>(repr);
		repr.clear();
		for (int i : order) {
			repr.add(cards.get(i));
		}
	}
}
