package com.summ.tennis.core;

import org.lwjgl.opengl.GL11;

import com.summ.gl.IFrameListener;
import com.summ.tennis.states.AnimationState;
import com.summ.tennis.states.EmptyState;
import com.summ.tennis.states.GameState;
import com.summ.tennis.states.LoadingState;

public class GameController implements IFrameListener, IStateLoaderListener {

	private StateLoader mStateLoader;

	private GameState mCurrentState;
	private GameState mNextState;
	
	private volatile boolean mNewStatePrepared;

	public GameController() {
		mCurrentState = new EmptyState();
	}

	@Override
	public void onCreate() {
		System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));

		mStateLoader = new StateLoader(this);
		mStateLoader.loadState(new LoadingState());

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	@Override
	public void onDrawFrame() {
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		if (mNewStatePrepared) gotoNextState();

		mCurrentState.onDrawFrame();
	}

	private void gotoNextState() {
		mCurrentState.onFinish();
		mNextState.onStart();

		mCurrentState = mNextState;
		mNextState = null;

		mNewStatePrepared = false;
	}

	@Override
	public void onDestroying() {
		
	}

	@Override
	public void onDestroy() {
		System.exit(0);
	}

	@Override
	public void onLoadCompleate(GameState state) {
		mNextState = state;
		mNewStatePrepared = true;
		
		if (state instanceof LoadingState) {
			mStateLoader.loadState(new AnimationState());
		}
	}
}
