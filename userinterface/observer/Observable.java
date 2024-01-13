package userinterface.observer;

public interface Observable {
    void addObserver(Observer observer);
    void notifyObserver();
}
