package com.summ.sge.widgets;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.summ.sge.graphics.core.IMouseListener;
import com.summ.sge.graphics.core.MouseEvent;
import com.summ.sge.graphics.core.ObjectFactory;
import com.summ.sge.graphics.objects.BoxObject;
import com.summ.sge.graphics.objects.Rectangle;

public class Frame extends BoxObject implements IMouseListener {
	private Rectangle mTopBorder;
	private Rectangle mBottomBorder;
	private Rectangle mLeftBorder;
	private Rectangle mRightBorder;

	private Rectangle mContentArea;

	private List<BoxObject> mElements = new ArrayList<>();

	public Frame(int width, int height) {
		setSize(width, height);

		mContentArea = ObjectFactory.newRectangle();
		mContentArea.setSize(width, height);
	}

	public void addElement(BoxObject boxObject) {
		boxObject.setOffset(mPosition);
		mElements.add(boxObject);
	}

	public void addElement(int position, BoxObject boxObject) {
		boxObject.setOffset(mPosition);
		mElements.add(position, boxObject);
	}

	public void removeElement(BoxObject boxObject) {
		mElements.remove(boxObject);
	}

	public void removeElement(int position) {
		mElements.remove(position);
	}

	public void removeAllElements() {
		mElements.clear();
	}

	@Override
	public boolean onMouseEvent(MouseEvent event) {
		if (event.getAction() == MouseEvent.MOUSE_RELEASED && event.getButton() == MouseEvent.BUTTON1) {
			ListIterator<BoxObject> listIter = mElements.listIterator(mElements.size());
			while (listIter.hasPrevious()) {
				BoxObject element = listIter.previous();
				if (event.getX() > element.getAbsolutePosition().getX() &&
						event.getX() < element.getAbsolutePosition().getX() + element.getWidth() &&
						event.getY() > element.getAbsolutePosition().getY() &&
						event.getY() < element.getAbsolutePosition().getY() + element.getHeight()) {
					boolean result = element.onMouseEvent(event);
					if (result) return true;
				}
			}
		}
		return false;
	}

	@Override
	public void draw() {
		mContentArea.draw();

		if (mRightBorder != null) mRightBorder.draw();
		if (mLeftBorder != null) mLeftBorder.draw();
		if (mBottomBorder != null) mBottomBorder.draw();
		if (mTopBorder != null) mTopBorder.draw();

		for (BoxObject element : mElements) {
			element.draw();
		}
	}

	public void setBackgroundTexture(int texture) {
		mContentArea.setTexture(texture);
	}

	public void setBackgroundColor(int color) {
		//TODO
	}

	public void setBackgroundAlpha(float alpha) {
		mContentArea.setAlpha(alpha);
	}

	@Override
	public void updateContentPosition() {
		mContentArea.setPosition(getAbsolutePosition());

		for (BoxObject element : mElements) {
			element.setOffset(mPosition);
		}
	}
}
