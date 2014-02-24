package com.summ.sge.graphics.engine.lwjgl;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.summ.sge.graphics.core.GraphicsEngine;
import com.summ.sge.graphics.core.IFrameListener;
import com.summ.sge.graphics.core.IKeyboardListener;
import com.summ.sge.graphics.core.KeyEvent;
import com.summ.sge.graphics.core.Window;

public class LwjglWindow extends Window {

	private static final int FRAME_RATE = 60;

	/** time at last frame */
	private long mLastFrame;
	/** frames per second */
	private int mFps;
	/** last fps time */
	private long mLastFPS;

	private LwjglRenderer mRenderer;

	private IFrameListener mFrameListener;

	public LwjglWindow(IFrameListener frameListener) {
		if (frameListener == null) throw new IllegalArgumentException("Frame listener cannot be null");

		mRenderer = new LwjglRenderer(this);
		mFrameListener = frameListener;
	}

	@Override
	public void open() {
		mRenderer.start();
	}

	@Override
	public void onInit() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setResizable(true);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));

		Display.setVSyncEnabled(true);

		initScene();
		mFrameListener.onCreate();

		getDelta();
		mLastFPS = getTime();
	}

	@Override
	public void onDraw() {
		updateFPS();

		pollInput();

		clearScene();

		mFrameListener.onDrawFrame();

		Display.update();
		Display.sync(FRAME_RATE);
	}

	@Override
	public void onDestroy() {
		mFrameListener.onDestroying();
		Display.destroy();
		mFrameListener.onDestroy();
	}

	public void pollInput() {
		if (Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
			int y = Mouse.getY();
			System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			System.out.println("SPACE KEY IS DOWN");
		}

		while (Keyboard.next()) {
			int action;
			int key;

			if (Keyboard.getEventKeyState()) {
				action = KeyEvent.KEY_PRESSED;
			} else {
				action = KeyEvent.KEY_RELEASED;
			}

			switch (Keyboard.getEventKey()) {
			case Keyboard.KEY_A:
				key = KeyEvent.VK_A;
				break;
			case Keyboard.KEY_O:
				key = KeyEvent.VK_O;
				break;
			case Keyboard.KEY_ESCAPE:
				key = KeyEvent.VK_ESCAPE;
				break;
			case Keyboard.KEY_SPACE:
				key = KeyEvent.VK_SPACE;
				break;
			default:
				key = KeyEvent.VK_NONE;
				break;
			}

			for (IKeyboardListener listener : mKeyboardListeners) {
				listener.onKeyboardEvent(new KeyEvent(action, key));
			}
		}
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 *
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - mLastFrame);
		mLastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 *
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - mLastFPS > 1000) {
			Display.setTitle("FPS: " + mFps);
			mFps = 0;
			mLastFPS += 1000;
		}
		mFps++;
	}

	//TODO Temporary solution
	private void initScene() {
		switch (GraphicsEngine.getType()) {
		case LWGL_GL11:
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, 800, 0, 600, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			break;

		case LWGL_GL20:
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, 800, 0, 600, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
//			GL11.glViewport(0, 0, 800, 600);
			break;

		default:
			throw new IllegalArgumentException("Engine not supported");
		}
	}

	//TODO Temporary solution
	private void clearScene() {
		switch (GraphicsEngine.getType()) {
		case LWGL_GL11: /* fall through */
		case LWGL_GL20:
			GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			break;

		default:
			throw new IllegalArgumentException("Engine not supported");
		}
	}
}
