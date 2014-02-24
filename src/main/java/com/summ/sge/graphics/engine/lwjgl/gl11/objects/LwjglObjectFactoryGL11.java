package com.summ.sge.graphics.engine.lwjgl.gl11.objects;

import com.summ.sge.graphics.core.AbstractObjectFactory;
import com.summ.sge.graphics.objects.Rectangle;

public class LwjglObjectFactoryGL11 extends AbstractObjectFactory {

	@Override
	protected Rectangle newRectangle() {
		return new LwjglRectangleGL11();
	}

}
