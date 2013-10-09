package com.summ.tennis.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.SharedDrawable;

import com.summ.tennis.states.GameState;

public class StateLoader {

	private static final String LOADER_THREAD_NAME = "Loader_thread";

	private IStateLoaderListener mListener;

	private SharedDrawable mSharedDrawable;
	private ExecutorService mLoaderExecutor;

	public StateLoader(IStateLoaderListener listener) {
		mListener = listener;

		try {
			mSharedDrawable = new SharedDrawable(Display.getDrawable());
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		mLoaderExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
			@Override
			public Thread newThread(final Runnable r) {
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							mSharedDrawable.makeCurrent();
						} catch (LWJGLException e) {
							e.printStackTrace();
						}
						r.run();
					}
				}, LOADER_THREAD_NAME);
				if (t.isDaemon()) t.setDaemon(false);
				if (t.getPriority() != Thread.NORM_PRIORITY) t.setPriority(Thread.NORM_PRIORITY);
				return t;
			}
		});
	}

	public void loadState(final GameState state) {
		mLoaderExecutor.submit(new Runnable() {
			@Override
			public void run() {
				state.init();
				mListener.onLoadCompleate(state);
			}
		});
	}
}
