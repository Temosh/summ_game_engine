package com.summ.sge.graphics.core;

public class TextureLoader {
	static AbstractTextureLoader loader;

	public static int loadStubTexture() {
		return loader.loadStubTexture();
	}

	public static int loadTexture(String loc) {
		return loader.loadTexture(loc);
	}
}
