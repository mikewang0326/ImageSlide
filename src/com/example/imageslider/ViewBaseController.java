/*
 * Copyright (C) 2012 The Founder Mobile Media Technology Android EPaper Project
 * 
 * Founder Mobile Media PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.imageslider;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

public abstract class ViewBaseController {
	
	public static final int INVALID_LAYOUT_RES_ID = -1;
	
	protected Activity mActivity;
	protected int mViewResId = INVALID_LAYOUT_RES_ID;
	protected int mLayoutResId = INVALID_LAYOUT_RES_ID;
	protected View mParentView;
	protected View mView;
	
	public ViewBaseController (Activity act, int viewResId, int layoutResId) {
		this(act, null, viewResId, layoutResId);
	}
	
	public ViewBaseController (Activity act, View parentView, int viewResId, int layoutResId) {
		mActivity = act;
		mParentView = parentView;
		mViewResId = viewResId; 
		mLayoutResId = layoutResId;
		prepareView();
	}
	
	/*
	 * 1, from parent
	 * 2, from activity
	 * 3, from layout
	 */
	private void prepareView() {
		if (mParentView!=null){//1
			mView = mParentView.findViewById(mViewResId);
		} else if (mLayoutResId > INVALID_LAYOUT_RES_ID) {//2
			LayoutInflater inflater = LayoutInflater.from(mActivity);
			mView = inflater.inflate(mLayoutResId, null);
		} else if (mViewResId > INVALID_LAYOUT_RES_ID){//3
			mView = mActivity.findViewById(mViewResId);
		}
		initView();
	}
	
	public void setBackgroundResource(int resId) {
		mView.setBackgroundResource(resId);
	}
	
	public void setBackgroundDrawable(Drawable d) {
		mView.setBackgroundDrawable(d);	
	}
	
	public View getView() {
		return mView;
	}
	
	protected abstract void initView();
	
	//release something
    public void onDestory() {
    };
}
