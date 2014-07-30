/*
 * Copyright (C) 2012 The Founder Mobile Media Technology Android EPaper Project
 * 
 * Founder Mobile Media PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.imageslider;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class OnImageSliderPageChangeListener implements OnPageChangeListener {

	private ImageSliderViewPager mViewPager;

	public OnImageSliderPageChangeListener(ImageSliderViewPager vp) {
		mViewPager = vp;
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		if (state == ViewPager.SCROLL_STATE_IDLE) {
			int currentPosition = mViewPager.getCurrentItem();
			int lastReal = mViewPager.getAdapter().getCount() - 2;
			if (currentPosition == 0) {
				mViewPager.setCurrentItem(lastReal, false);
			} else if (currentPosition > lastReal) {
				mViewPager.setCurrentItem(1, false);
			}
		}
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int position) {

	}
}
