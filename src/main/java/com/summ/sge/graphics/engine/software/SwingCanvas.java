package com.summ.sge.graphics.engine.software;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class SwingCanvas extends JComponent {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage[] frameBuffers = new BufferedImage[2];
	private static Graphics2D g;

	private int currentBuffer;

	/** time at last frame */
	private long mLastFrame;
	/** frames per second */
	private int mFps;
	/** last fps time */
	private long mLastFPS;
	/** last fps */
	private long mLastFPSCount;

	public SwingCanvas(int width, int height) {
		frameBuffers[0] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);
		frameBuffers[1] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);

		getDelta();
		mLastFPS = getTime();
	}

	@Override
	public void paint(Graphics g) {
		updateFPS();

		Graphics2D g2d = (Graphics2D) g;

		synchronized (this) {
			g2d.drawRenderedImage(frameBuffers[currentBuffer], AffineTransform.getTranslateInstance(0, 0));
		}
	}

	public void clear() {
		BufferedImage backBuffer = frameBuffers[currentBuffer ^ 1];
		g = backBuffer.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, backBuffer.getWidth(), backBuffer.getHeight());
	}

	public static Graphics2D getContext() {
		return g;
	}

	public void swapBuffers() {
		synchronized (this) {
			currentBuffer ^= 1;
		}
		repaint();
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
		return System.currentTimeMillis();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - mLastFPS > 1000) {
			mLastFPSCount = mFps;
			mFps = 0;
			mLastFPS += 1000;
		}
		mFps++;
	}

	public long getFps() {
		return mLastFPSCount;
	}
}
