package com.summ.examples;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class ExampleApplet extends Applet {

	private static final long serialVersionUID = 1L;

	private Canvas display_parent;

	/** Thread which runs the main game loop */
	private Thread gameThread;

	/** time at last frame */
	private long mLastFrame;
	/** frames per second */
	private int mFps;

	/** is the game loop running */
	private boolean running = false;

	private int x = 100;
	private int y = 100;
	private float rotation = 0;

	public void startLWJGL() {
		gameThread = new Thread() {
			@Override
			public void run() {
				running = true;
				try {
					Display.setParent(display_parent);
					Display.create();
					initGL();
				} catch (LWJGLException e) {
					e.printStackTrace();
					return;
				}
				gameLoop();
			}
		};
		gameThread.start();
	}

	/**
	 * Tell game loop to stop running, after which the LWJGL Display will be destoryed. The main thread will wait for the Display.destroy().
	 */
	private void stopLWJGL() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}

	/**
	 * Applet Destroy method will remove the canvas, before canvas is destroyed it will notify stopLWJGL() to stop the main game loop and to destroy the Display
	 */
	@Override
	public void destroy() {
		remove(display_parent);
		super.destroy();
	}

	@Override
	public void init() {
		setLayout(new BorderLayout());
		try {
			display_parent = new Canvas() {
				private static final long serialVersionUID = 1L;

				@Override
				public final void addNotify() {
					super.addNotify();
					startLWJGL();
				}

				@Override
				public final void removeNotify() {
					stopLWJGL();
					super.removeNotify();
				}
			};
			display_parent.setSize(getWidth(), getHeight());
			add(display_parent);
			display_parent.setFocusable(true);
			display_parent.requestFocus();
			display_parent.setIgnoreRepaint(true);
			setVisible(true);
		} catch (Exception e) {
			System.err.println(e);
			throw new RuntimeException("Unable to create display");
		}
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, getWidth(), 0, getHeight(), 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		getDelta();
	}

	public void renderGL() {
		// Clear The Screen And The Depth Buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		// R,G,B,A Set The Color To Blue One Time Only
		GL11.glColor3f(0.5f, 0.5f, 1.0f);

		// draw quad
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(rotation, 0f, 0f, 1f);
		GL11.glTranslatef(-x, -y, 0);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x - 50, y - 50);
		GL11.glVertex2f(x + 50, y - 50);
		GL11.glVertex2f(x + 50, y + 50);
		GL11.glVertex2f(x - 50, y + 50);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	public void gameLoop() {
		while (running) {
			update(getDelta());

			renderGL();

			Display.sync(60);
			Display.update();
		}

		Display.destroy();
	}

	public void update(int delta) {
		// rotate quad
		rotation += 0.15f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) x -= 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) x += 0.35f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) y += 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) y -= 0.35f * delta;

		// keep quad on the screen
		if (x < 0) x = 0;
		if (x > 800) x = 800;
		if (y < 0) y = 0;
		if (y > 600) y = 600;
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
}