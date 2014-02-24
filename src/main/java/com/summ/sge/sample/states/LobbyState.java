package com.summ.sge.sample.states;

import com.summ.sge.graphics.core.KeyEvent;
import com.summ.sge.graphics.core.MouseEvent;
import com.summ.sge.graphics.core.ObjectFactory;
import com.summ.sge.graphics.core.Scene;
import com.summ.sge.graphics.core.TextureLoader;
import com.summ.sge.graphics.math.Vector3f;
import com.summ.sge.graphics.objects.Rectangle;
import com.summ.sge.sample.core.LobbyMenu;

public class LobbyState extends GameState {

	private Scene mScene;
	private LobbyMenu mMenu;
	private Rectangle mBackground;
//	private HUD mHud;

	private boolean isPaused;

	public LobbyState() {

	}

	@Override
	public void init() {
		System.out.println("Lobby state init");

//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		mScene = new Scene();

		mBackground = ObjectFactory.newRectangle();
		mBackground.setPosition(new Vector3f(0, 0, 0));
		mBackground.setSize(800, 600);
		mBackground.setTexture(TextureLoader.loadTexture("big.jpg"));

		mMenu = new LobbyMenu(mStateLoader, 300, 400);
		mMenu.setPosition(new Vector3f(250.0f, 100.0f, 0.0f));
//		mMenu.setPosition(new Vector3f(0.0f, 0.0f, 0.0f));

		mScene.addObject(mBackground);
		mScene.addObject(mMenu);
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
		if (event.getAction() == KeyEvent.KEY_RELEASED) {
			switch (event.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				mStateLoader.loadState(new LoadingState());
				return true;

			case KeyEvent.VK_A:
				mStateLoader.loadState(new AnimationState());
				return true;

			case KeyEvent.VK_O:
				mStateLoader.loadState(new OverlayState());
				return true;

			default:
				break;
			}
		}
		return false;
	}

	@Override
	public boolean onMouseEvent(MouseEvent event) {
		return mMenu.onMouseEvent(event);
	}
}
