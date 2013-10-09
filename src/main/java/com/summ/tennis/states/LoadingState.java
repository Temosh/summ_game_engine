package com.summ.tennis.states;

import com.summ.gl.GLScene;
import com.summ.gl.Quad;
import com.summ.gl.utils.TextureLoader;
import com.summ.math.Vector3f;

public class LoadingState extends GameState {

	private GLScene mGLScene;

	private Quad mQuad;
	
	private int rotation;
	
	public LoadingState() {
		
	}

	@Override
	public void init() {
		System.out.println("Loading state init");

		mGLScene = new GLScene();

		mQuad = new Quad();
		mQuad.setPosition(new Vector3f(300, 200, 0));
		mQuad.setSize(200, 200);
		mQuad.setRotation(rotation);
		mQuad.setTexture(TextureLoader.loadTexture("/res/orange.png"));

		mGLScene.addObject(mQuad);
	}

	@Override
	public void onStart() {
		System.out.println("Loading state start");

	}

	@Override
	public void onFinish() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDrawFrame() {
		mGLScene.draw();
	}
}
