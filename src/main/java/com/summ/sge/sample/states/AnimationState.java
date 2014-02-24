package com.summ.sge.sample.states;

import com.summ.sge.graphics.core.KeyEvent;
import com.summ.sge.graphics.core.ObjectFactory;
import com.summ.sge.graphics.core.Scene;
import com.summ.sge.graphics.math.Vector3f;
import com.summ.sge.graphics.objects.Rectangle;

public class AnimationState extends GameState {

	private Scene mGLScene;

	private Rectangle mQuad;

	private int rotation;

	public AnimationState() {

	}

	@Override
	public void init() {
		System.out.println("Animation state init");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		mGLScene = new Scene();

		mQuad = ObjectFactory.newRectangle();
		mQuad.setPosition(new Vector3f(400, 300, 0));
		mQuad.setSize(100, 100);
		mQuad.setRotation(rotation);
		mQuad.setColor(0xff009999);

		mGLScene.addObject(mQuad);
	}

	@Override
	public void onStart() {
		System.out.println("Animation state start");


	}

	@Override
	public void onFinish() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDrawFrame() {
		rotation++;
		mQuad.setRotation(rotation);

		mGLScene.draw();
	}

	@Override
	public boolean onKeyboardEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ESCAPE && event.getAction() == KeyEvent.KEY_RELEASED) {
			System.out.println("Key Escape released!!!");
			mStateLoader.loadState(new LobbyState());
			return true;
		}
		return false;
	}
}
