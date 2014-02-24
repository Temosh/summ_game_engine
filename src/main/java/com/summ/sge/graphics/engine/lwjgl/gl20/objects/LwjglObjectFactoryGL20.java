package com.summ.sge.graphics.engine.lwjgl.gl20.objects;

import com.summ.sge.graphics.core.AbstractObjectFactory;
import com.summ.sge.graphics.objects.Rectangle;

public class LwjglObjectFactoryGL20 extends AbstractObjectFactory {

	@Override
	protected Rectangle newRectangle() {
		return new LwjglRectangleGL20();
	}

}
