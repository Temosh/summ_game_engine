package com.summ.tennis.states;

import com.summ.gl.GLScene;
import com.summ.gl.Quad;
import com.summ.math.Vector3f;

public class AnimationState extends GameState {

	private GLScene mGLScene;

	private Quad mQuad;
	
	private int rotation;
	
	public AnimationState() {
		
	}

	@Override
	public void init() {
		System.out.println("Animation state init");

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		mGLScene = new GLScene();

		mQuad = new Quad();
		mQuad.setPosition(new Vector3f(400, 300, 0));
		mQuad.setSize(100, 100);
		mQuad.setRotation(rotation);

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
}
