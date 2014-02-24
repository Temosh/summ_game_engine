package com.summ.sge.graphics.core;

import java.util.ArrayList;
import java.util.List;

import com.summ.sge.graphics.objects.DisplayObject;

public class Scene {

	private List<DisplayObject> objects = new ArrayList<DisplayObject>();

	public Scene() {

	}

	public void addObject(DisplayObject object) {
		objects.add(object);
	}

	public void draw() {
		for (DisplayObject object : objects) {
			object.draw();
		}
	}
}
