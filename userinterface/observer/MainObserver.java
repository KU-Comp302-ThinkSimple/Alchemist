package userinterface.observer;

import userinterface.MainGameWindowOffline;

public class MainObserver implements Observer{
    private MainGameWindowOffline mainGameWindow;
    public MainObserver(MainGameWindowOffline mainGameWindow){
        this.mainGameWindow = mainGameWindow;
    }

    public void update(){
        mainGameWindow.updateMainGameWindow();
    }
}
