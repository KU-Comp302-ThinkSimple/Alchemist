package userinterface.observer;

import userinterface.MainGameWindowOffline;
import userinterface.MainGameWindowOnline;

public class MainObserver implements Observer{
	private MainGameWindowOffline mainGameWindow;
	private MainGameWindowOnline mainGameWindowOnline;

	public MainObserver(MainGameWindowOffline mainGameWindow){
		this.mainGameWindow = mainGameWindow;
	}

	public MainObserver(MainGameWindowOnline mainGameWindow){
		this.mainGameWindowOnline = mainGameWindow;
	}

	@Override
	public void update(){
		if (this.mainGameWindow == null) {
			mainGameWindowOnline.updateMainGameWindow();
		}
		else {
			mainGameWindow.updateMainGameWindow();
		}

	}
}
