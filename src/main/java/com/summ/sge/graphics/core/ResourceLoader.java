package com.summ.sge.graphics.core;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;

public class ResourceLoader {
	public static final String RESOURCE_PATH 	= "/res/";

	public static final String AUDIO_PATH 		= RESOURCE_PATH + "audio/";
	public static final String IMAGES_PATH 		= RESOURCE_PATH + "images/";
	public static final String SHADERS_PATH 	= RESOURCE_PATH + "shaders/";

	public static BufferedImage loadImage(String path) {
		URL url = ResourceLoader.class.getResource(IMAGES_PATH + path);
		if (url == null) {
			System.err.println("ResourceLoader: wrong image path! [" + path + "]");
		}

		try {
			return ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String loadShaderSource(String path) {
		URL url = ResourceLoader.class.getResource(SHADERS_PATH + path);
		if (url == null) {
			System.err.println("ResourceLoader: wrong shader path! [" + path + "]");
		}

		StringBuilder shaderSource = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(url.getFile()));
			String line;
			while ((line = reader.readLine()) != null) {
				shaderSource.append(line).append('\n');
			}
		} catch (IOException e) {
			System.err.println("Shader wasn't loaded properly.");
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return shaderSource.toString();
	}
}
