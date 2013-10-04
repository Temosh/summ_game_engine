package com.summ.gl;

import org.lwjgl.opengl.GL11;

import com.summ.math.Vector3f;

public class Quad implements IDisplayObject {

	Vector3f mPosition;
	int mRotation;
	
	@Override
	public void draw() {
		GL11.glColor3f(0.5f, 0.5f, 1.0f);

		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(mPosition.getX(),		mPosition.getY());
			GL11.glVertex2f(mPosition.getX() + 200,	mPosition.getY());
			GL11.glVertex2f(mPosition.getX() + 200,	mPosition.getY() + 200);
			GL11.glVertex2f(mPosition.getX(),		mPosition.getY() + 200);
		GL11.glEnd();
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
