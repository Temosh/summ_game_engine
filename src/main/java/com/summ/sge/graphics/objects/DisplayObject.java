package com.summ.sge.graphics.objects;

import com.summ.sge.graphics.core.Drawable;
import com.summ.sge.graphics.math.Vector3f;

public abstract class DisplayObject implements Drawable {
	/**
	 * Position of the left-bottom corner of the object
	 */
	protected Vector3f mPosition = new Vector3f(0, 0, 0);

	protected int mRotation;

	public void setPosition(Vector3f vector) {
		mPosition = vector;
	}

	public void setRotation(int angle) {
		mRotation = angle;
	}

	public Vector3f getPosition() {
		return mPosition;
	}

	public int getRotation() {
		return mRotation;
	}
}
