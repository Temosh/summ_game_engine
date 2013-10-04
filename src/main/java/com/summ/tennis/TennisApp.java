package com.summ.tennis;

import org.lwjgl.opengl.GL11;

import com.summ.gl.GLScene;
import com.summ.gl.IFrameListener;
import com.summ.gl.OpenGLWindow;
import com.summ.gl.Quad;
import com.summ.math.Vector3f;

public class TennisApp implements IFrameListener {

	private GLScene mGlScene;

	public static void main(String[] argv) {
		new TennisApp();
	}

	public TennisApp() {
		OpenGLWindow glWindow = new OpenGLWindow(this);

		mGlScene = new GLScene();

		Quad quad = new Quad();
		quad.setPosition(new Vector3f(100.0f, 100.0f, 0.0f));

		mGlScene.addObject(quad);

		glWindow.open();
	}

	@Override
	public void onCreate() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	@Override
	public void onDrawFrame() {
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		mGlScene.draw();
	}

	@Override
	public void onDestroying() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}
}
