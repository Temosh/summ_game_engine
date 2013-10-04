package com.summ.tennis.states;

public abstract class GameState {
	public abstract void init();
	
	public abstract void onStart();
	public abstract void onFinish();

	public abstract void onDrawFrame();
}
