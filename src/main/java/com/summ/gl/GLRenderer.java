package com.summ.gl;

import org.lwjgl.opengl.Display;

public class GLRenderer {
	private static final String RENDERER_THREAD_NAME = "Renderer_thread";
	
	private Thread mRenderThread;
	
	private IRendererListener mListener;

	private class RendererRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " start");
			mListener.onInit();

			while(!Thread.interrupted() && !Display.isCloseRequested()) {
				mListener.onDraw();
			}

			System.out.println(Thread.currentThread().getName() + " destroy");
			mListener.onDestroy();
			System.out.println(Thread.currentThread().getName() + " end");
		}
	}

	public GLRenderer(IRendererListener listener) {
		this(listener, false);
	}

	public GLRenderer(IRendererListener listener, boolean useSeparateThread) {
		mListener = listener;

		mRenderThread = new Thread(new RendererRunnable(), RENDERER_THREAD_NAME);
	}

	public void start() {
		if (mRenderThread == null) {
			new RendererRunnable().run();
		} else {
			mRenderThread.start();
		}
	}
}
