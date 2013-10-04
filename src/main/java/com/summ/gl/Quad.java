package com.summ.gl;

import org.lwjgl.opengl.GL11;

import com.summ.math.Vector3f;

public class Quad implements IDisplayObject {

	private Vector3f mPosition;
	private int mWidth;
	private int mHeight;
	private int mRotation;

	@Override
	public void draw() {
		GL11.glColor3f(0.5f, 0.5f, 1.0f);

		GL11.glTranslatef(mPosition.getX(), mPosition.getY(), 0);
		GL11.glRotatef(mRotation, 0f, 0f, 1f);
		GL11.glTranslatef(-mPosition.getX(), -mPosition.getY(), 0);

		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(mPosition.getX(),			mPosition.getY());
			GL11.glVertex2f(mPosition.getX() + mWidth,	mPosition.getY());
			GL11.glVertex2f(mPosition.getX() + mWidth,	mPosition.getY() + mHeight);
			GL11.glVertex2f(mPosition.getX(),			mPosition.getY() + mHeight);
		GL11.glEnd();
	}

	
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

	@Override
	public void setPosition(Vector3f vector) {
		mPosition = vector;
	}

	@Override
	public void setRotation(int angle) {
		mRotation = angle;
	}

	@Override
	public Vector3f getPosition() {
		return mPosition;
	}

	@Override
	public int getRotation() {
		return mRotation;
	}
}
