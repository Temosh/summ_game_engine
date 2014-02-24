package com.summ.sge.sample.states;

import com.summ.sge.graphics.core.ObjectFactory;
import com.summ.sge.graphics.core.Scene;
import com.summ.sge.graphics.core.TextureLoader;
import com.summ.sge.graphics.math.Vector3f;
import com.summ.sge.graphics.objects.Rectangle;

public class LoadingState extends GameState {

	private Scene mGLScene;

	private Rectangle mQuad;
	private Rectangle mQuad2;

	private int rotation;

	public LoadingState() {

	}

	@Override
	public void init() {
		System.out.println("Loading state init");

		mGLScene = new Scene();

//		TextureLoader.loadTexture("/res/big.jpg");

		mQuad = ObjectFactory.newRectangle();
		mQuad.setPosition(new Vector3f(150, 150, 0));
		mQuad.setSize(200, 300);
		mQuad.setRotation(rotation);
		mQuad.setTexture(TextureLoader.loadTexture("orange.png"));
//		mQuad.setTexture(TextureLoader.loadTexture("pop.jpg"));
//		mQuad.setTexture(TextureLoader.loadTexture("troy.jpg"));
//		mQuad.setTexture(TextureLoader.loadTexture("big.jpg"));
//		mQuad.setTexture(TextureLoader.loadStubTexture());

		mGLScene.addObject(mQuad);

		mQuad2 = ObjectFactory.newRectangle();
		mQuad2.setPosition(new Vector3f(450, 200, 0));
		mQuad2.setSize(200, 200);
		mQuad2.setRotation(rotation);
//		mQuad2.setTexture(TextureLoader.loadTexture("pop.jpg"));
//		mQuad2.setTexture(TextureLoader.loadTexture("big.jpg"));
		mQuad2.setTexture(TextureLoader.loadStubTexture());

		mGLScene.addObject(mQuad2);

		mStateLoader.loadState(new LobbyState());
	}

	@Override
	public void onStart() {
		System.out.println("Loading state start");
	}

	@Override
	public void onFinish() {
		System.out.println("Loading state finish");
	}

	@Override
	public void onDrawFrame() {
		mGLScene.draw();
	}
}
