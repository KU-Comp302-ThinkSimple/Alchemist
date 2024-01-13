package userinterface.observer;

import userinterface.MainGameWindow;

public class MainObserver implements Observer{
    private MainGameWindow mainGameWindow;
    public MainObserver(MainGameWindow mainGameWindow){
        this.mainGameWindow = mainGameWindow;
    }

    public void update(){
        mainGameWindow.updateMainGameWindow();
    }
}
