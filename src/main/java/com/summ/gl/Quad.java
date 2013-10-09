package com.summ.gl;

import com.summ.math.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Quad implements IDisplayObject {

	private Vector3f mPosition;
	private int mWidth;
	private int mHeight;
	private int mRotation;

	private int mTexture = -1;

	@Override
	public void draw() {
		glTranslatef(mPosition.getX(), mPosition.getY(), 0);
		glRotatef(mRotation, 0f, 0f, 1f);
		glTranslatef(-mPosition.getX(), -mPosition.getY(), 0);

		if (mTexture > -1) {
			glEnable(GL_TEXTURE_2D);
			glDisable(GL_DEPTH_TEST);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE);
			glBindTexture(GL_TEXTURE_2D, mTexture);
		} else {
			glColor3f(0.5f, 0.5f, 1.0f);
		}

		glBegin(GL_QUADS);
			glTexCoord2f(0.0f, 0.0f);
			glVertex2f(mPosition.getX(),			mPosition.getY());
			glTexCoord2f(1.0f, 0.0f);
			glVertex2f(mPosition.getX() + mWidth,	mPosition.getY());
			glTexCoord2f(1.0f, 1.0f);
			glVertex2f(mPosition.getX() + mWidth,	mPosition.getY() + mHeight);
			glTexCoord2f(0.0f, 1.0f);
			glVertex2f(mPosition.getX(),			mPosition.getY() + mHeight);
		glEnd();

		glDisable(GL_TEXTURE_2D);
	}

	public void setSize(int width, int height) {
		mWidth = width;
		mHeight = height;
	}

	public void setTexture(int texture) {
		mTexture = texture;
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
