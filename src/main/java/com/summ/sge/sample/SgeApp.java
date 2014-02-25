package com.summ.sge.sample;

import com.summ.sge.graphics.core.EngineType;
import com.summ.sge.graphics.core.GraphicsEngine;
import com.summ.sge.graphics.core.Window;
import com.summ.sge.sample.core.GameController;

public class SgeApp {

//	private static final EngineType DEFAULT_ENGINE = EngineType.LWGL_GL11;
//	private static final EngineType DEFAULT_ENGINE = EngineType.LWGL_GL20;
	private static final EngineType DEFAULT_ENGINE = EngineType.SOFTWARE;

	public static void main(String[] args) {
		EngineType engine = DEFAULT_ENGINE;

		if (args.length > 0) {
			switch (args[0]) {
			case "gl11":
				engine = EngineType.LWGL_GL11;
				break;
			case "gl20":
				engine = EngineType.LWGL_GL20;
				break;
			case "soft":
				engine = EngineType.SOFTWARE;
				break;
			default:
				break;
			}
		}

		new SgeApp(engine);
	}

	public SgeApp(EngineType engineType) {
		GraphicsEngine.init(engineType);

		GameController gameController = new GameController();

		Window glWindow = GraphicsEngine.createWindow(gameController);
		glWindow.addKeyboardListener(gameController);
		glWindow.addMouseListener(gameController);
		glWindow.open();
	}
}
