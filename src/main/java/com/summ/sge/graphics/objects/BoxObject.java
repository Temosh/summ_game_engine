package com.summ.sge.graphics.objects;

import com.summ.sge.graphics.core.IKeyboardListener;
import com.summ.sge.graphics.core.IMouseListener;
import com.summ.sge.graphics.core.KeyEvent;
import com.summ.sge.graphics.core.MouseEvent;
import com.summ.sge.graphics.math.Vector3f;

public abstract class BoxObject extends DisplayObject implements IKeyboardListener, IMouseListener {
	protected int mWidth;
	protected int mHeight;

	protected Vector3f mOffset = new Vector3f(0, 0, 0);

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
	public boolean onKeyboardEvent(KeyEvent event) {
		return false;
	}

	@Override
	public boolean onMouseEvent(MouseEvent event) {
		return false;
	}

	@Override
	public void setPosition(Vector3f vector) {
		super.setPosition(vector);
		updateContentPosition();
	}

	public void setOffset(float x, float y, float z) {
		setOffset(new Vector3f(x, y, z));
	}

	public void setOffset(Vector3f v) {
		mOffset = v;
		updateContentPosition();
	}

	public abstract void updateContentPosition();

	public Vector3f getAbsolutePosition() {
		return new Vector3f(mPosition.getX() + mOffset.getX(),
							mPosition.getY() + mOffset.getY(),
							mPosition.getZ() + mOffset.getZ());
	}

//	public Vector3f getAbsolutePosition(float offsetX, float offsetY, float offsetZ) {
//		return new Vector3f(mPosition.getX() + mOffset.getX() + offsetX,
//							mPosition.getY() + mOffset.getY() + offsetY,
//							mPosition.getZ() + mOffset.getZ() + offsetZ);
//	}
}
