package com.summ.sge.sample.states;

import com.summ.sge.graphics.core.KeyEvent;
import com.summ.sge.graphics.core.MouseEvent;
import com.summ.sge.graphics.core.Scene;
import com.summ.sge.graphics.core.TextureLoader;
import com.summ.sge.graphics.math.Vector3f;
import com.summ.sge.widgets.Button;
import com.summ.sge.widgets.Frame;

public class LobbyState extends GameState {

	private Scene mScene;
	private Frame mMenu;
//	private HUD mHud;

	private boolean isPaused;

	public LobbyState() {

	}

	@Override
	public void init() {
		System.out.println("Lobby state init");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		mScene = new Scene();

		mMenu = new Frame(300, 400);
		mMenu.setPosition(new Vector3f(250.0f, 100.0f, 0.0f));
		mMenu.setBackgroundTexture(TextureLoader.loadStubTexture());

		mScene.addObject(mMenu);

		Button button1 = new Button();
		button1.setPosition(new Vector3f(25.0f, 25.0f, 0.0f));
		button1.setSize(250, 50);
		mMenu.addElement(button1);

		Button button2 = new Button();
		button2.setPosition(new Vector3f(25.0f, 100.0f, 0.0f));
		button2.setSize(250, 50);
		mMenu.addElement(button2);
	}

	@Override
	public void onStart() {
		System.out.println("Lobby state start");

	}

	@Override
	public void onFinish() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDrawFrame() {
		mScene.draw();

		if (isPaused) {
			mMenu.draw();
		}
	}

	@Override
	public boolean onKeyboardEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ESCAPE && event.getAction() == KeyEvent.KEY_RELEASED) {
			System.out.println("Key Escape released!!!");
			mStateLoader.loadState(new LoadingState());
			return true;
		} else if (event.getKeyCode() == KeyEvent.VK_SPACE && event.getAction() == KeyEvent.KEY_RELEASED) {
			System.out.println("Key Space released!!!");
			mStateLoader.loadState(new AnimationState());
			return true;
		}
		return false;
	}

	@Override
	public boolean onMouseEvent(MouseEvent event) {
		return false;
	}
}
