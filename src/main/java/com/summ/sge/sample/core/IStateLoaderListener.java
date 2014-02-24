package com.summ.sge.sample.core;

import com.summ.sge.sample.states.GameState;

public interface IStateLoaderListener {
	void onLoadCompleate(GameState state);
}
