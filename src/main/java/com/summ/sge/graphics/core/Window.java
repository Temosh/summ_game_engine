package com.summ.sge.graphics.core;

import java.util.ArrayList;
import java.util.List;

public abstract class Window implements IRendererListener {
	protected List<IKeyboardListener> mKeyboardListeners = new ArrayList<>(1);
	protected List<IMouseListener> mMouseListeners = new ArrayList<>(1);

	public abstract void open();

	public void addKeyboardListener(IKeyboardListener keyboardListener) {
		mKeyboardListeners.add(keyboardListener);
	}

	public void addMouseListener(IMouseListener mouseListener) {
		mMouseListeners.add(mouseListener);
	}
}
