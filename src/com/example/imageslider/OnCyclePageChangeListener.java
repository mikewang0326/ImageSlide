/*
 * Copyright (C) 2012 The Founder Mobile Media Technology Android EPaper Project
 * 
 * Founder Mobile Media PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.imageslider;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class OnCyclePageChangeListener implements OnPageChangeListener {

	CycleViewPager vp;

	public OnCyclePageChangeListener(CycleViewPager vp) {
		this.vp = vp;
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		if (state == ViewPager.SCROLL_STATE_IDLE) {
			int curr = vp.getCurrentItem();
			int lastReal = vp.getAdapter().getCount() - 2;
			if (curr == 0) {
				vp.setCurrentItem(lastReal, false);
			} else if (curr > lastReal) {
				vp.setCurrentItem(1, false);
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
