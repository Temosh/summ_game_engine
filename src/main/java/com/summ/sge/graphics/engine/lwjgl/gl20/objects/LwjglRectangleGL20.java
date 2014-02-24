package com.summ.sge.graphics.engine.lwjgl.gl20.objects;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.*;

import com.summ.sge.graphics.engine.lwjgl.common.utils.LwjglProgramLoader;
import com.summ.sge.graphics.objects.Rectangle;

public class LwjglRectangleGL20 extends Rectangle {

	private static final int VERTICES_COUNT = 4;
	private static final int VERTEX_SIZE = 2;

	@Override
	public void draw() {
		int program = LwjglProgramLoader.getDefaultProgram();

		glUseProgram(program);

		glTranslatef(mPosition.getX(), mPosition.getY(), 0);
		glRotatef(mRotation, 0f, 0f, 1f);
		glTranslatef(-mPosition.getX(), -mPosition.getY(), 0);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_DST_ALPHA);

		if (mTexture > -1) {
			glActiveTexture(GL_TEXTURE0);
			glBindTexture(GL_TEXTURE_2D, mTexture);
		}

		int textureHandler = glGetUniformLocation(program, "sTexture");
		glUniform1i(textureHandler, 0);

		glBegin(GL_QUADS);
			glColor4f(1.0f, 1.0f, 1.0f, 0.33f);
			glTexCoord2f(0.0f, 1.0f);
			glVertex2f(mPosition.getX(),			mPosition.getY());

			glColor4f(1.0f, 1.0f, 1.0f, 0.0f);
			glTexCoord2f(1.0f, 1.0f);
			glVertex2f(mPosition.getX() + mWidth,	mPosition.getY());

			glColor4f(1.0f, 1.0f, 1.0f, 0.66f);
			glTexCoord2f(1.0f, 0.0f);
			glVertex2f(mPosition.getX() + mWidth,	mPosition.getY() + mHeight);

			glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			glTexCoord2f(0.0f, 0.0f);
			glVertex2f(mPosition.getX(),			mPosition.getY() + mHeight);
		glEnd();

		glUseProgram(0);
		glDisable(GL_BLEND);
	}
}
