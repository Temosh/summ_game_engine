package com.summ.sge.graphics.engine.software.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.summ.sge.graphics.core.AbstractTextureLoader;

public class SwingTextureLoader extends AbstractTextureLoader {

	@Override
	public int loadStubTexture() {
		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setColor(new Color(0x009900));
		g.fillRect(0, 0, image.getWidth(), image.getHeight());

		return loadTexture(image);
	}

	@Override
	protected int loadTexture(BufferedImage image) {
		return TextureHolder.INSTANCE.createTexture(image);
	}

}
