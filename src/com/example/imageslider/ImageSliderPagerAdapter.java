/*
 * Copyright (C) 2012 The Founder Mobile Media Technology Android EPaper Project
 * 
 * Founder Mobile Media PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.imageslider;

import android.app.Activity;
import android.view.View;

public abstract class ImageSliderPagerAdapter<ImageSliderItem> extends BasePagerAdapter{

	/**
	 * The constructor
	 * 
	 * @param context
	 */
	public ImageSliderPagerAdapter(Activity context) {
		super(context);
	}

}
