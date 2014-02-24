package com.summ.sge.graphics.engine.software.utils;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public enum TextureHolder {
	INSTANCE;

	private int textureId;

	private HashMap<Integer, BufferedImage> mTextures = new HashMap<>();

	public int createTexture(BufferedImage textureImage) {
		textureId++;
		mTextures.put(textureId, textureImage);
		return textureId;
	}

	public void releaseTexture(int texture) {
		mTextures.remove(texture);
	}

	public BufferedImage getTextureImage(int texture) {
		return mTextures.get(texture);
	}
}
