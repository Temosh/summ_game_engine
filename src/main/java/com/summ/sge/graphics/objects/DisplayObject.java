package com.summ.sge.graphics.objects;

import com.summ.sge.graphics.core.Drawable;
import com.summ.sge.graphics.math.Vector3f;
import com.summ.sge.graphics.utils.ColorUtils;

public abstract class DisplayObject implements Drawable {
	/**
	 * Position of the left-bottom corner of the object
	 */
	protected Vector3f mPosition = new Vector3f(0, 0, 0);

	protected int mRotation;

	protected int mColor = 0xffffffff;

	public void setPosition(Vector3f vector) {
		mPosition = vector;
	}

	public Vector3f getPosition() {
		return mPosition;
	}

	public void setRotation(int angle) {
		mRotation = angle;
	}

	public int getRotation() {
		return mRotation;
	}

	public void setColor(int color) {
		mColor = color;
	}

	public int getColor() {
		return mColor;
	}

	public void setAlpha(float alpha) {
		mColor &= 0x00ffffff;
		mColor |= (int) (alpha * 255) << 24;
	}

	public float getAlpha() {
		return ColorUtils.getAlpha(mColor);
	}
}
