package com.summ.sge.graphics.utils;

public class ColorUtils {
	public static float getRed(int color) {
		return ((color & 0x00ff0000) >>> 16) / 255.0f;
	}

	public static float getGreen(int color) {
		return ((color & 0x0000ff00) >>> 8) / 255.0f;
	}

	public static float getBlue(int color) {
		return (color & 0x000000ff) / 255.0f;
	}

	public static float getAlpha(int color) {
		return ((color & 0xff000000) >>> 24) / 255.0f;
	}
}
