package com.summ.gl;

import com.summ.math.Vector3f;

public interface IDisplayObject {
	void draw();

	void setPosition(Vector3f vector);
	void setRotation(int angle);

	Vector3f getPosition();
	int getRotation();
}
