package com.summ.sge.sample;

import com.summ.sge.graphics.core.EngineType;
import com.summ.sge.graphics.core.GraphicsEngine;
import com.summ.sge.graphics.core.Window;
import com.summ.sge.sample.core.GameController;

public class SgeApp {

	public static void main(String[] argv) {
		new SgeApp();
	}

	public SgeApp() {
//		GraphicsEngine.init(EngineType.LWGL_GL11);
//		GraphicsEngine.init(EngineType.LWGL_GL20);
		GraphicsEngine.init(EngineType.SOFTWARE);
		GameController gameController = new GameController();

		Window glWindow = GraphicsEngine.createWindow(gameController);
		glWindow.addKeyboardListener(gameController);
		glWindow.addMouseListener(gameController);
		glWindow.open();
	}
}
