/*
 * Copyright (C) 2012 The Founder Mobile Media Technology Android EPaper Project
 * 
 * Founder Mobile Media PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.imageslider;

import android.app.Activity;
import android.view.View;

public abstract class CyclePagerAdapter<T> extends ArrayListPagerAdapter{

	/**
	 * The constructor
	 * 
	 * @param context
	 */
	public CyclePagerAdapter(Activity context) {
		super(context);
	}

//	@Override
//	public void buildPageViews() {
//		if (this.mList == null || this.mList.size() == 0) {
//			clearPageViews();
//		} else {
//			mPageViews = new View[this.mList.size() + 2];
//		}
//	}

	public View createViewItem(int position) {

		return null;
	}

//	@Override
//	public Object getItem(int position) {
//		if (mList != null && mList.size() > 0) {
//			if (position == 0) {
//				return mList.get(mList.size() - 1);
//			} else if (position == getCount() - 1) {
//				return mList.get(0);
//			} else {
//				return mList.get(position - 1);
//			}
//		} else {
//			return null;
//		}
//	}

	/***
	 * 当前view 对应的当前data id
	 * 
	 * @param position
	 * @return
	 */
//	public int getDataId(int position) {
//		int curDataId = 0;
//		if (position == 0) {
//			curDataId = getCount() - 1;
//		} else if (position == getCount() - 1) {
//			curDataId = 0;
//		} else {
//			curDataId = position + 1;
//		}
//		return curDataId;
//	}

}
