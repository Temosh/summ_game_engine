package com.summ.sge.graphics.engine.software.objects;

import com.summ.sge.graphics.core.AbstractObjectFactory;
import com.summ.sge.graphics.objects.Rectangle;

public class SwingObjectFactory extends AbstractObjectFactory {

	@Override
	protected Rectangle newRectangle() {
		return new SwingRectangle();
	}

}
