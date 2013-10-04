package com.summ.tennis;

import com.summ.gl.OpenGLWindow;
import com.summ.tennis.core.GameController;

public class TennisApp {

	public static void main(String[] argv) {
		new TennisApp();
	}

	public TennisApp() {
		GameController gameController = new GameController();

		OpenGLWindow glWindow = new OpenGLWindow(gameController);
		glWindow.open();
	}
}
