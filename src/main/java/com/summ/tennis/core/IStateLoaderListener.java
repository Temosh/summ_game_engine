package com.summ.tennis.core;

import com.summ.tennis.states.GameState;

public interface IStateLoaderListener {
	void onLoadCompleate(GameState state);
}
