package domain.boards;

import userinterface.observer.Observable;
import userinterface.observer.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArtifactCardBoard extends Board implements Serializable, Observable {

    private List<Observer> observers = new ArrayList<>();

    public ArrayList<IngredientCardDeckArrayList> useElixirOfInsight(){
        return null;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers){
            observer.update();
        }
    }
}
