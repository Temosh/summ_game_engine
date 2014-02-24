package com.summ.sge.graphics.core;

import java.awt.image.BufferedImage;

public abstract class AbstractTextureLoader {

	public abstract int loadStubTexture();

	public int loadTexture(String loc) {
		return loadTexture(ResourceLoader.loadImage(loc));
	}

	protected abstract int loadTexture(BufferedImage image);
}
