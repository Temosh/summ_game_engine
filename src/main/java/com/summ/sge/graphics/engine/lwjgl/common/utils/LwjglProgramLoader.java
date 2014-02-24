package com.summ.sge.graphics.engine.lwjgl.common.utils;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glLinkProgram;

public class LwjglProgramLoader {

	private static int defaultProgram = 0;

	public static int getDefaultProgram() {
		if (defaultProgram < 1) {
			defaultProgram = glCreateProgram();
			if (defaultProgram < 0) {
				System.err.println("Cannot create program");
			}

			glAttachShader(defaultProgram, LwjglShaderLoader.getDafaultVertexShader());
			glAttachShader(defaultProgram, LwjglShaderLoader.getDafaultFragmentShader());

			glLinkProgram(defaultProgram);
			if (glGetProgrami(defaultProgram, GL_LINK_STATUS) == GL_FALSE) {
				System.err.println("Program wasn't able to be linked correctly.");
			}
		}

		return defaultProgram;
	}
}
