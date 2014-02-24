package com.summ.sge.graphics.engine.software.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.summ.sge.graphics.engine.software.SwingCanvas;
import com.summ.sge.graphics.objects.Rectangle;

public class SwingRectangle extends Rectangle {

	@Override
	public void draw() {
		Graphics2D g = SwingCanvas.getContext();

		AffineTransform identity = g.getTransform();

		g.translate(mPosition.getX(), mPosition.getY());
		g.rotate(mRotation);

		g.setStroke(new BasicStroke(2));	//Толщина линии
		g.setColor(new Color(0xFF0000));	//Цвет линии
		g.fillRect(0, 0, mWidth, mHeight);
//		g.drawImage(TextureUtils.getBitmap(mTexture, mWidth, mHeight), op, x, y);

		g.setTransform(identity);
	}

}
