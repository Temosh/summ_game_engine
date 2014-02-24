package com.summ.sge.graphics.objects;

public abstract class Rectangle extends TexturedObject {
	protected int mWidth;
	protected int mHeight;

	public void setSize(int width, int height) {
		mWidth = width;
		mHeight = height;
	}

	public int getWidth() {
		return mWidth;
	}

	public int getHeight() {
		return mHeight;
	}
}
