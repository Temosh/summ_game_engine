package com.summ.sge.graphics.core;

import com.summ.sge.graphics.objects.Rectangle;

public class ObjectFactory {
	static AbstractObjectFactory factory;

	static void init(EngineType engineType) {

	}

	public static void newPoint() {

	}

	public static void newLine() {

	}

	public static void newTriangle() {

	}

	public static Rectangle newRectangle() {
		return factory.newRectangle();
	}

	public static void newQuad() {

	}

	public static void newEllipse() {

	}

	public static void newCircle() {

	}
}
