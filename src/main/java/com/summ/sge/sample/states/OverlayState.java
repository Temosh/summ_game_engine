package com.summ.sge.sample.states;

import com.summ.sge.graphics.core.KeyEvent;
import com.summ.sge.graphics.core.ObjectFactory;
import com.summ.sge.graphics.core.Scene;
import com.summ.sge.graphics.core.TextureLoader;
import com.summ.sge.graphics.math.Vector3f;
import com.summ.sge.graphics.objects.Rectangle;

public class OverlayState extends GameState {

	private Scene mGLScene;

	private Rectangle mQuad;
	private Rectangle mQuad2;

	private int rotation;

	public OverlayState() {

	}

	@Override
	public void init() {
		System.out.println("Overlay state init");

		mGLScene = new Scene();

//		TextureLoader.loadTexture("/res/big.jpg");

		mQuad = ObjectFactory.newRectangle();
		mQuad.setPosition(new Vector3f(200, 100, 0));
		mQuad.setSize(400, 400);
		mQuad.setRotation(rotation);
		mQuad.setTexture(TextureLoader.loadTexture("pop.jpg"));
//		mQuad.setTexture(TextureLoader.loadTexture("troy.jpg"));
//		mQuad.setTexture(TextureLoader.loadTexture("big.jpg"));
//		mQuad.setTexture(TextureLoader.loadStubTexture());

		mGLScene.addObject(mQuad);

		mQuad2 = ObjectFactory.newRectangle();
		mQuad2.setPosition(new Vector3f(300, 200, 0));
		mQuad2.setSize(200, 200);
		mQuad2.setRotation(rotation);
		mQuad2.setAlpha(0.5f);
		mQuad2.setTexture(TextureLoader.loadTexture("orange.png"));
//		mQuad2.setTexture(TextureLoader.loadTexture("pop.jpg"));
//		mQuad2.setTexture(TextureLoader.loadTexture("big.jpg"));
//		mQuad2.setTexture(TextureLoader.loadStubTexture());

		mGLScene.addObject(mQuad2);
	}

	@Override
	public void onStart() {
		System.out.println("Overlay state start");
	}

	@Override
	public void onFinish() {
		System.out.println("Overlay state finish");
	}

	@Override
	public void onDrawFrame() {
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
