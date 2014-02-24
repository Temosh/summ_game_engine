package com.summ.sge.graphics.objects;

public abstract class TexturedObject extends DisplayObject {
	protected int mTexture = -1;

	public void setTexture(int texture) {
		mTexture = texture;
	}
}
