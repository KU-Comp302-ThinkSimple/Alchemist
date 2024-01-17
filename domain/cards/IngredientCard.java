package domain.cards;

import domain.potion.Molecule;
import userinterface.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IngredientCard extends Card{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3646985582619093105L;
	final int id;
	final String name;
	final Molecule molecule;
	private transient List<Observer> observers = new ArrayList<>();
	
	public IngredientCard(int id, String name, Molecule molecule) {
		this.id = id;
		this.name = name;
		this.molecule=molecule;
	}

	public String getName() {
		return name;
	}

	public Molecule getMolecule() {
		return molecule;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, molecule, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IngredientCard other = (IngredientCard) obj;
		return id == other.id && Objects.equals(molecule, other.molecule) && Objects.equals(name, other.name);
	}

	@Override
	public void addObserver(Observer observer) {
		if(observers == null) {
			observers = new ArrayList<>();
		}
		observers.add(observer);
	}

	@Override
	public void notifyObserver() {
		if(observers == null) {
			return;
		}
		for (Observer observer : observers){
			observer.update();
		}
	}
}
