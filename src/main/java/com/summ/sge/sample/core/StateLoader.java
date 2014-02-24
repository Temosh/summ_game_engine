package com.summ.sge.sample.core;

import java.util.concurrent.ExecutorService;

import com.summ.sge.graphics.core.GraphicsEngine;
import com.summ.sge.sample.states.GameState;

public class StateLoader {

	private static final String LOADER_THREAD_NAME = "Loader_thread";

	private IStateLoaderListener mListener;

	private ExecutorService mLoaderExecutor;

	public StateLoader(IStateLoaderListener listener) {
		mListener = listener;
		mLoaderExecutor = GraphicsEngine.createRendererThread(LOADER_THREAD_NAME);
	}

	public void loadState(final GameState state) {
		mLoaderExecutor.submit(new Runnable() {
			@Override
			public void run() {
				try {
					state.setStateLoader(StateLoader.this);
					state.init();
					mListener.onLoadCompleate(state);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
