package com.summ.sge.sample.states;

import com.summ.sge.graphics.core.IKeyboardListener;
import com.summ.sge.graphics.core.IMouseListener;
import com.summ.sge.graphics.core.KeyEvent;
import com.summ.sge.graphics.core.MouseEvent;
import com.summ.sge.sample.core.StateLoader;

public abstract class GameState implements IKeyboardListener, IMouseListener {
	protected StateLoader mStateLoader;

	public abstract void init();

	public abstract void onStart();
	public abstract void onFinish();

	public abstract void onDrawFrame();

	public void setStateLoader(StateLoader stateLoader) {
		mStateLoader = stateLoader;
	}

	@Override
	public boolean onMouseEvent(MouseEvent event) {
		return false;
	}

	@Override
	public boolean onKeyboardEvent(KeyEvent event) {
		return false;
	}
}
