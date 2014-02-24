package com.summ.sge.sample.core;


import com.summ.sge.graphics.core.IFrameListener;
import com.summ.sge.graphics.core.IKeyboardListener;
import com.summ.sge.graphics.core.IMouseListener;
import com.summ.sge.graphics.core.KeyEvent;
import com.summ.sge.graphics.core.MouseEvent;
import com.summ.sge.sample.states.EmptyState;
import com.summ.sge.sample.states.GameState;
import com.summ.sge.sample.states.LoadingState;


public class GameController implements IFrameListener, IStateLoaderListener, IKeyboardListener, IMouseListener {

	private StateLoader mStateLoader;

	private GameState mCurrentState;
	private GameState mNextState;

	private volatile boolean mNewStatePrepared;

	public GameController() {
		mCurrentState = new EmptyState();
	}

	@Override
	public void onCreate() {
		mStateLoader = new StateLoader(this);
		mStateLoader.loadState(new LoadingState());
	}

	@Override
	public void onDrawFrame() {
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
	}

	@Override
	public boolean onKeyboardEvent(KeyEvent event) {
		return mCurrentState.onKeyboardEvent(event);
	}

	@Override
	public boolean onMouseEvent(MouseEvent event) {
		return mCurrentState.onMouseEvent(event);
	}
}