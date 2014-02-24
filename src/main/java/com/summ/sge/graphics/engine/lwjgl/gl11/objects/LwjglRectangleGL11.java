package com.summ.sge.graphics.engine.lwjgl.gl11.objects;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_ALWAYS;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_GEQUAL;
import static org.lwjgl.opengl.GL11.GL_GREATER;
import static org.lwjgl.opengl.GL11.GL_LEQUAL;
import static org.lwjgl.opengl.GL11.GL_LESS;
import static org.lwjgl.opengl.GL11.GL_NEVER;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glAlphaFunc;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDepthFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import com.summ.sge.graphics.objects.Rectangle;

class LwjglRectangleGL11 extends Rectangle {

	@Override
	public void draw() {
		glTranslatef(mPosition.getX(), mPosition.getY(), 0);
		glRotatef(mRotation, 0f, 0f, 1f);
		glTranslatef(-mPosition.getX(), -mPosition.getY(), 0);

		if (mTexture > -1) {
			glDisable(GL_DEPTH_TEST);
//			glEnable(GL_DEPTH_TEST);
//			glDepthFunc(GL_LEQUAL);

//			glEnable(GL_ALPHA_TEST);
//			glAlphaFunc(GL_GEQUAL, 0);

			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

			glEnable(GL_TEXTURE_2D);
			glBindTexture(GL_TEXTURE_2D, mTexture);

			glColor3f(1.0f, 1.0f, 1.0f);
		} else {
			glColor3f(0.5f, 0.5f, 1.0f);
		}

		glBegin(GL_QUADS);
			glTexCoord2f(0.0f, 1.0f);
			glVertex2f(mPosition.getX(),			mPosition.getY());
			glTexCoord2f(1.0f, 1.0f);
			glVertex2f(mPosition.getX() + mWidth,	mPosition.getY());
			glTexCoord2f(1.0f, 0.0f);
			glVertex2f(mPosition.getX() + mWidth,	mPosition.getY() + mHeight);
			glTexCoord2f(0.0f, 0.0f);
			glVertex2f(mPosition.getX(),			mPosition.getY() + mHeight);
		glEnd();

		glDisable(GL_BLEND);
		glDisable(GL_TEXTURE_2D);
	}
}
