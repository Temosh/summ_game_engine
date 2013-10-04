package com.summ.gl;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class OpenGLWindow implements IRendererListener {

	private static final int FRAME_RATE = 60;

	/** time at last frame */
	private long mLastFrame;
	/** frames per second */
	private int mFps;
	/** last fps time */
	private long mLastFPS;

	private GLRenderer mRenderer;

	private IFrameListener mFrameListener;

	public OpenGLWindow(IFrameListener frameListener) {
		if (frameListener == null) throw new IllegalArgumentException("Frame listener cannot be null");

		mRenderer = new GLRenderer(this);
		mFrameListener = frameListener;
	}
	
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

		Display.setVSyncEnabled(true);

		mFrameListener.onCreate();

		getDelta();
		mLastFPS = getTime();
	}

	@Override
	public void onDraw() {
		updateFPS();

		pollInput();

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
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					System.out.println("A Key Pressed");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					System.out.println("S Key Pressed");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					System.out.println("D Key Pressed");
				}
			} else {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					System.out.println("A Key Released");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					System.out.println("S Key Released");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					System.out.println("D Key Released");
				}
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
}
