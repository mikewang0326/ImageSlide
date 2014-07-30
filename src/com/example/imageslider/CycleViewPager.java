/*
 * Copyright (C) 2012 The Founder Mobile Media Technology Android EPaper Project
 * 
 * Founder Mobile Media PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.imageslider;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CycleViewPager extends ViewPager {

	/**
	 * The constructor
	 * 
	 * @param context
	 * @param attrs
	 */
	public CycleViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * The constructor
	 * 
	 * @param context
	 */
	public CycleViewPager(Context context) {
		super(context);
	}

	public void setCycleAdapter(CyclePagerAdapter adapter) {
		super.setAdapter(adapter);
	}

	public void setOnCyclePageChangeListener(OnCyclePageChangeListener listener) {
		super.setOnPageChangeListener(listener);
	}

	// viewpager 嵌套viewpager

	/** the last x position */
	private float lastX;
	private float lastY;

	int slidingLflag = 1;
	int slidingRflag = 2;
	int sliding = 0;

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			// Disallow parent ViewPager to intercept touch events
			this.getParent().requestDisallowInterceptTouchEvent(true);
			// Log.e("down  Int" + sliding);
			// save the current x position
			this.lastX = ev.getX();
			this.lastY = ev.getY();
			sliding = 0;
			break;

		case MotionEvent.ACTION_UP:
			// Allow parent ViewPager to intercept touch events.
			this.getParent().requestDisallowInterceptTouchEvent(false);
			// Log.e("up  Int" + sliding);
			// save the current x position
			this.lastX = ev.getX();
			this.lastY = ev.getY();
			sliding = 0;
			break;

		case MotionEvent.ACTION_MOVE:
			this.lastX = ev.getX();
			this.lastY = ev.getY();

			if (Math.abs(ev.getX() - this.lastX) > Math.abs(ev.getY()
					- this.lastY)) {
				this.getParent().requestDisallowInterceptTouchEvent(true);
			} else {
				// this.getParent().requestDisallowInterceptTouchEvent(false);
			}
			break;

		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(final MotionEvent ev) {
		final int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			// Disallow parent ViewPager to intercept touch events
			this.getParent().requestDisallowInterceptTouchEvent(true);

			// save the current x position
			this.lastX = ev.getX();
			this.lastY = ev.getY();
			sliding = 0;
			break;

		case MotionEvent.ACTION_UP:
			// Allow parent ViewPager to intercept touch events.
			this.getParent().requestDisallowInterceptTouchEvent(false);

			// save the current x position
			this.lastX = ev.getX();
			this.lastY = ev.getY();
			sliding = 0;
			break;

		case MotionEvent.ACTION_MOVE:

			
			this.lastX = ev.getX();
			this.lastY = ev.getY();
			break;
		}

		return super.onTouchEvent(ev);
		// return true;
	}

}
