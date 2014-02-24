package com.summ.sge.graphics.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.SharedDrawable;

import com.summ.sge.graphics.engine.lwjgl.LwjglWindow;
import com.summ.sge.graphics.engine.lwjgl.common.utils.LwjglTextureLoader;
import com.summ.sge.graphics.engine.lwjgl.gl11.objects.LwjglObjectFactoryGL11;
import com.summ.sge.graphics.engine.lwjgl.gl20.objects.LwjglObjectFactoryGL20;
import com.summ.sge.graphics.engine.software.SwingWindow;
import com.summ.sge.graphics.engine.software.objects.SwingObjectFactory;
import com.summ.sge.graphics.engine.software.utils.SwingTextureLoader;

public class GraphicsEngine {

	private static EngineType type;

	public static void init(EngineType engineType) {
		type = engineType;

		switch (engineType) {
		case LWGL_GL11:
			ObjectFactory.factory = new LwjglObjectFactoryGL11();
			TextureLoader.loader = new LwjglTextureLoader();
			break;

		case LWGL_GL20:
			ObjectFactory.factory = new LwjglObjectFactoryGL20();
			TextureLoader.loader = new LwjglTextureLoader();
			break;

		case SOFTWARE:
			ObjectFactory.factory = new SwingObjectFactory();
			TextureLoader.loader = new SwingTextureLoader();
			break;

		default:
			break;
		}
	}

	public static Window createWindow(IFrameListener listener) {
		if (type == null) throw new IllegalStateException("Need to init graphics engine first");

		switch (type) {
		case LWGL_GL11: /* fall through */
		case LWGL_GL20:
			return new LwjglWindow(listener);

		case SOFTWARE:
			return new SwingWindow(listener);

		default:
			throw new IllegalArgumentException("Engine not supported");
		}
	}

	public static ExecutorService createRendererThread(final String threadName) {
		switch (type) {
		case LWGL_GL11: /* fall through */
		case LWGL_GL20:
			ExecutorService executor = null;

			try {
				final SharedDrawable sharedDrawable = new SharedDrawable(Display.getDrawable());

				executor = Executors.newSingleThreadExecutor(new ThreadFactory() {
					@Override
					public Thread newThread(final Runnable r) {
						Thread t = new Thread(new Runnable() {
							@Override
							public void run() {
								try {
									sharedDrawable.makeCurrent();
								} catch (LWJGLException e) {
									e.printStackTrace();
								}
								r.run();
							}
						}, threadName);
						if (t.isDaemon()) t.setDaemon(false);
						if (t.getPriority() != Thread.NORM_PRIORITY) t.setPriority(Thread.NORM_PRIORITY);
						return t;
					}
				});
			} catch (LWJGLException e) {
				e.printStackTrace();
			}

			return executor;

		case SOFTWARE:
			return Executors.newSingleThreadExecutor();

		default:
			throw new IllegalArgumentException("Engine not supported");
		}
	}

	public static EngineType getType() {
		return type;
	}
}
