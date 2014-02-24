package com.summ.sge.widgets;

import com.summ.sge.graphics.core.MouseEvent;
import com.summ.sge.graphics.core.ObjectFactory;
import com.summ.sge.graphics.objects.BoxObject;
import com.summ.sge.graphics.objects.Rectangle;

public class Button extends BoxObject {

	private Rectangle mBody;

	private ButtonListener mMouseListener;

	public interface ButtonListener {
		boolean onButtonClick();
	}

	public Button() {
		mBody = ObjectFactory.newRectangle();
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		mBody.setSize(width, height);
	}

	public void setBackgroundTexture(int texture) {
		mBody.setTexture(texture);
	}

	@Override
	public void draw() {
		mBody.draw();
	}

	@Override
	public void updateContentPosition() {
		mBody.setPosition(getAbsolutePosition());
	}

	@Override
	public boolean onMouseEvent(MouseEvent event) {
		if (mMouseListener != null && event.getAction() == MouseEvent.MOUSE_RELEASED) {
			return mMouseListener.onButtonClick();
		}
		return false;
	}

	public void setButtonListener(ButtonListener buttonListener) {
		mMouseListener = buttonListener;
	}
}
