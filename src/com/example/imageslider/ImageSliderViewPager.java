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

public class ImageSliderViewPager extends ViewPager {
	
	/**
	 *  the last x position 
	 */
	private float mLastX;
	private float mLastY;

	public ImageSliderViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ImageSliderViewPager(Context context) {
		super(context);
	}

	public void setSliderAdapter(BasePagerAdapter adapter) {
		super.setAdapter(adapter);
	}

	public void setOnCyclePageChangeListener(OnImageSliderPageChangeListener listener) {
		super.setOnPageChangeListener(listener);
	}

	/*
	 * handle the event for viewpager in viewpager
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			// Disallow parent ViewPager to intercept touch events
			this.getParent().requestDisallowInterceptTouchEvent(true);
			// Log.e("down  Int" + sliding);
			// save the current x position
			this.mLastX = ev.getX();
			this.mLastY = ev.getY();
			break;

		case MotionEvent.ACTION_UP:
			// Allow parent ViewPager to intercept touch events.
			this.getParent().requestDisallowInterceptTouchEvent(false);
			// Log.e("up  Int" + sliding);
			// save the current x position
			this.mLastX = ev.getX();
			this.mLastY = ev.getY();
			break;

		case MotionEvent.ACTION_MOVE:
			this.mLastX = ev.getX();
			this.mLastY = ev.getY();

			if (Math.abs(ev.getX() - this.mLastX) > Math.abs(ev.getY()
					- this.mLastY)) {
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
			this.mLastX = ev.getX();
			this.mLastY = ev.getY();
			break;

		case MotionEvent.ACTION_UP:
			// Allow parent ViewPager to intercept touch events.
			this.getParent().requestDisallowInterceptTouchEvent(false);

			// save the current x position
			this.mLastX = ev.getX();
			this.mLastY = ev.getY();
			break;

		case MotionEvent.ACTION_MOVE:
			this.mLastX = ev.getX();
			this.mLastY = ev.getY();
			break;
		}

		return super.onTouchEvent(ev);
	}

}
