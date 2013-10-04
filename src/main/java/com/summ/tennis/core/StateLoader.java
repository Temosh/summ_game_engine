package com.summ.tennis.core;

import com.summ.tennis.states.GameState;

public class StateLoader {

	private IStateLoaderListener mListener;

	public StateLoader(IStateLoaderListener listener) {
		mListener = listener;
	}

	public void loadState(GameState state) {
		state.init();

		mListener.onLoadCompleate(state);
	}
}
