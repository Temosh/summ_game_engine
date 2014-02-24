package com.summ.sge.graphics.core;

import java.awt.Point;

public class MouseEvent {
	/**
	 * The "mouse clicked" event.
	 * This <code>MouseEvent</code> occurs when a mouse button is pressed and released.
	 */
	public static final int MOUSE_CLICKED = 0;

	/**
	 * The "mouse pressed" event.
	 * This <code>MouseEvent</code> occurs when a mouse button is pushed down.
	 */
	public static final int MOUSE_PRESSED = 1;

	/**
	 * The "mouse released" event.
	 * This <code>MouseEvent</code> occurs when a mouse button is let up.
	 */
	public static final int MOUSE_RELEASED = 2;

	/**
	 * The "mouse moved" event.
	 * This <code>MouseEvent</code> occurs when the mouse position changes.
	 */
	public static final int MOUSE_MOVED = 3;

	/**
	 * The "mouse entered" event.
	 * This <code>MouseEvent</code> occurs when the mouse cursor enters the unobscured part of component's geometry.
	 */
	public static final int MOUSE_ENTERED = 4;

	/**
	 * The "mouse exited" event.
	 * This <code>MouseEvent</code> occurs when the mouse cursor exits the unobscured part of component's geometry.
	 */
	public static final int MOUSE_EXITED = 5;

	/**
	 * The "mouse dragged" event.
	 * This <code>MouseEvent</code> occurs when the mouse position changes while a mouse button is pressed.
	 */
	public static final int MOUSE_DRAGGED = 6;

	/**
	 * The "mouse wheel" event.
	 * This is the only <code>MouseWheelEvent</code>. It occurs when a mouse equipped with a wheel has its wheel rotated.
	 */
	public static final int MOUSE_WHEEL = 7;

	/**
	 * Indicates no mouse buttons; used by {@link #getButton}.
	 */
	public static final int NOBUTTON = 0;

	/**
	 * Indicates mouse button #1; used by {@link #getButton}.
	 */
	public static final int BUTTON1 = 1;

	/**
	 * Indicates mouse button #2; used by {@link #getButton}.
	 */
	public static final int BUTTON2 = 2;

	/**
	 * Indicates mouse button #3; used by {@link #getButton}.
	 */
	public static final int BUTTON3 = 3;

	/**
	 * The mouse event's x coordinate. The x value is relative to the component that fired the event.
	 *
	 * @see #getX()
	 */
	private int x;

	/**
	 * The mouse event's y coordinate. The y value is relative to the component that fired the event.
	 *
	 * @see #getY()
	 */
	private int y;

	private int mAction;
	private int mButton;

	public MouseEvent(int action, int button, int x, int y) {
		mAction = action;
		mButton = button;
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the horizontal x position of the event relative to the source component.
	 *
	 * @return x an integer indicating horizontal position relative to the component
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the vertical y position of the event relative to the source component.
	 *
	 * @return y an integer indicating vertical position relative to the component
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the x,y position of the event relative to the source component.
	 *
	 * @return a <code>Point</code> object containing the x and y coordinates relative to the source component
	 *
	 */
	public Point getPoint() {
		return new Point(x, y);
	}

	public int getAction() {
		return mAction;
	}

	public int getButton() {
		return mButton;
	}
}
