package com.summ.sge.graphics.engine.lwjgl.common.utils;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_INFO_LOG_LENGTH;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glShaderSource;

import com.summ.sge.graphics.core.ResourceLoader;

public class LwjglShaderLoader {

	private static final String DEFAULT_VERTEX_SHADER	= "default.vert";
	private static final String DEFAULT_FRAGMENT_SHADER	= "default.frag";

	private static int defaultVertexShader = 0;
	private static int defaultFragmentShader = 0;

	public static int getDafaultVertexShader() {
		if (defaultVertexShader < 1) {
			defaultVertexShader = loadShader(DEFAULT_VERTEX_SHADER, GL_VERTEX_SHADER);
		}

		return defaultVertexShader;
	}

	public static int getDafaultFragmentShader() {
		if (defaultFragmentShader < 1) {
			defaultFragmentShader = loadShader(DEFAULT_FRAGMENT_SHADER, GL_FRAGMENT_SHADER);
		}

		return defaultFragmentShader;
	}

	public static int loadShader(String shaderName, int shaderType) {
		int shader = glCreateShader(shaderType);

		glShaderSource(shader, ResourceLoader.loadShaderSource(shaderName));
		glCompileShader(shader);

		if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
			if (shaderType == GL_VERTEX_SHADER) {
				System.err.println("Vertex shader wasn't able to be compiled correctly.");
			} else {
				System.err.println("Fragment shader wasn't able to be compiled correctly.");
			}
			System.err.println(glGetShaderInfoLog(shader, glGetShaderi(shader, GL_INFO_LOG_LENGTH)));
		}

		return shader;
	}
}
