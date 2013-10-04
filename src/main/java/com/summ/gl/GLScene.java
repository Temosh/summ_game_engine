package com.summ.gl;

import java.util.ArrayList;
import java.util.List;

public class GLScene {
	
	private List<IDisplayObject> objects = new ArrayList<IDisplayObject>();
	
	public GLScene() {
		
	}
	
	public void addObject(IDisplayObject object) {
		objects.add(object);
	}
	
	public void draw() {
		for (IDisplayObject object : objects) {
			object.draw();
		}
	}
}
