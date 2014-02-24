package com.summ.sge.sample.core;

import com.summ.sge.graphics.core.TextureLoader;
import com.summ.sge.graphics.math.Vector3f;
import com.summ.sge.sample.states.AnimationState;
import com.summ.sge.sample.states.LoadingState;
import com.summ.sge.widgets.Button;
import com.summ.sge.widgets.Button.ButtonListener;
import com.summ.sge.widgets.Frame;

public class LobbyMenu extends Frame {

	private StateLoader mStateLoader;

	public LobbyMenu(StateLoader stateLoader, int width, int height) {
		super(width, height);

		mStateLoader = stateLoader;

		setBackgroundTexture(TextureLoader.loadStubTexture());

		Button button1 = new Button();
		button1.setPosition(new Vector3f(25.0f, 25.0f, 0.0f));
		button1.setSize(250, 50);
		button1.setButtonListener(new ButtonListener() {
			@Override
			public boolean onButtonClick() {
				mStateLoader.loadState(new LoadingState());
				return true;
			}
		});
		addElement(button1);

		Button button2 = new Button();
		button2.setPosition(new Vector3f(25.0f, 100.0f, 0.0f));
		button2.setSize(250, 50);
		button2.setButtonListener(new ButtonListener() {
			@Override
			public boolean onButtonClick() {
				mStateLoader.loadState(new AnimationState());
				return true;
			}
		});
		addElement(button2);
	}
}
