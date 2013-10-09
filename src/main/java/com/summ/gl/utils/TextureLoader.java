package com.summ.gl.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;

public class TextureLoader {
	private static final int BYTES_PER_PIXEL = 4; // 3 for RGB, 4 for RGBA

	public static int loadTexture(String loc) {
		return loadTexture(loadImage(loc));
	}

	public static int loadTexture(BufferedImage image) {
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL); // 4 for RGBA, 3 for RGB

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF));	// Red component
				buffer.put((byte) ((pixel >> 8) & 0xFF));	// Green component
				buffer.put((byte) (pixel & 0xFF));			// Blue component
				buffer.put((byte) ((pixel >> 24) & 0xFF));	// Alpha component. Only for RGBA
			}
		}

		buffer.flip(); // FOR THE LOVE OF GOD DO NOT FORGET THIS

		int textureID = glGenTextures();

		glBindTexture(GL_TEXTURE_2D, textureID);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

		return textureID;
	}

	public static BufferedImage loadImage(String loc) {
		URL url = TextureLoader.class.getResource(loc);
		if (url == null) {
			System.err.println("TextureLoader: wrong resource location! [" + loc + "]");
		}

		try {
			return ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
