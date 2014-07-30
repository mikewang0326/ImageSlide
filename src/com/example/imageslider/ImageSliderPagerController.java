/*
 * Copyright (C) 2012 The Founder Mobile Media Technology Android EPaper Project
 * 
 * Founder Mobile Media PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.imageslider;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.imageslider.CirclePageIndicator.MyOnAttachStateChangeListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class ImageSliderPagerController<T> extends ViewBaseController {
	
	private String TAG = "ispc";
	
	public ImageSliderPagerController(Activity act, View mBaseView,
			boolean isInflater) {
		super(act, mBaseView, R.id.cycle_container,
				isInflater ? R.layout.cycle_img_pager : INVALID_LAYOUT_RES_ID);
	}

	private DisplayImageOptions mOptions;

	private ImageSliderViewPager mViewPager;

	private BannerPagerAdapter<ImageSlideItem> mAdapter;
	
	private CirclePageIndicator mIndicator;
	private FixedSpeedScroller mScroller;
	
	private Handler mHandler;
	
	private int mTimeSpan = 5000;//default value
	
	private LinearLayout mInfo;
	
	private ArrayList<ImageSlideItem> mList;

	@Override
	protected void initView() {
		Log.d("main", "initView mActivity = " + mActivity);
		
		mOptions = new DisplayImageOptions.Builder()
				// .showStubImage(R.drawable.bg_base_magazine_item)
				// .showImageForEmptyUri(R.drawable.bg_base_magazine_item)
				// .showImageOnFail(R.drawable.bg_base_magazine_item)
				.cacheInMemory().cacheOnDisc()
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		mViewPager = (ImageSliderViewPager) this.mView.findViewById(R.id.cvp);// 鑾峰緱cvp瀵硅薄
		mInfo = (LinearLayout) this.mView.findViewById(R.id.viewflow_dic_ll);
		mIndicator = (CirclePageIndicator) this.mView.findViewById(R.id.viewflowindic);
		
		mIndicator.addMyOnAttachStateChangeListener(new MyOnAttachStateChangeListener() {
			
			@Override
			public void onViewAttachedToWindow(View v) {
				Log.d(TAG, "onViewAttachedToWindow()");
				if(isAutoFlow){
					startAutoFlowTimer();
				}
			}
			
			@Override
			public void onViewDetachedFromWindow(View v) {
				Log.d(TAG, "onViewDetachedFromWindow()");
				stopAutoFlowTimer();
			}
			
		});
		
		mAdapter = new BannerPagerAdapter<ImageSlideItem>(this.mActivity);
		
		mAdapter.setShowInfoEnable(true);
		
		mAdapter.setCycleClickListener(mOnImageSliderPagerClickListener);
		
		mViewPager.setSliderAdapter(mAdapter);
//		mViewPager.setOnCyclePageChangeListener(null);
		mViewPager.setOnPageChangeListener(null);
		
		controlViewPagerSpeed();
		mIndicator.setViewPager(mViewPager, 0);
		mIndicator.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				if(mOnCycleImagePagerChangeListener!=null && mList!=null && mList.size()>arg0){
					mOnCycleImagePagerChangeListener.OnCycleImagePagerChange(arg0, mList.get(arg0));
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	
	private void controlViewPagerSpeed() {  
		try {  
		    Field mField;  
		    mField = ViewPager.class.getDeclaredField("mScroller");  
		    mField.setAccessible(true);  
		  
		    mScroller = new FixedSpeedScroller(  
		    		mViewPager.getContext(),  
		       null);  
		    mScroller.setmDuration(2000); // 2000ms  
		    mField.set(mViewPager, mScroller);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		   } 
	
	public void startAutoFlowTimer() {
		stopAutoFlowTimer();
		
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (getChildCount() <= 0+1)//1涓厓绱犱笉婊氬姩
					return;
				snapToScreen((getCurrentItem() + 1) % getChildCount());
				Message message = mHandler.obtainMessage(0);
				sendMessageDelayed(message, mTimeSpan);
			}
		};

		Message message = mHandler.obtainMessage(0);
		mHandler.sendMessageDelayed(message, mTimeSpan);
	}

	public void setTimeSpan(int timeSpan) {
		mTimeSpan = timeSpan;
	}
	
	public int getTimeSpan() {
		return mTimeSpan;
	}
	
	public void stopAutoFlowTimer() {
		if (mHandler != null)
			mHandler.removeMessages(0);
		mHandler = null;
	}
	
	private int getChildCount () {
		return mAdapter == null ? 0 : mAdapter.getCount(); 
	}
	
	private int getCurrentItem() {
		return mViewPager.getCurrentItem();
	}
	
	private void snapToScreen(int position) {
		mViewPager.setCurrentItem(position);
	}

	public void setData(ArrayList<ImageSlideItem> mList) {
		this.mList = mList;
		mAdapter.setList(mList);
		if(mOnCycleImagePagerChangeListener!=null && mList!=null && mList.size()>0){
			mOnCycleImagePagerChangeListener.OnCycleImagePagerChange(0, mList.get(0));
		}
	}

	// cycle image paper click
	OnCycleImagePagerClickListener<ImageSlideItem> mOnImageSliderPagerClickListener;

	/**
	 * Set the mOnCycleImagePagerClickListener
	 * 
	 * @return the mOnCycleImagePagerClickListener
	 */
	public OnCycleImagePagerClickListener<ImageSlideItem> getmOnCycleImagePagerClickListener() {
		return mOnImageSliderPagerClickListener;
	}

	/**
	 * Get the mOnCycleImagePagerClickListener
	 * 
	 * @param mOnCycleImagePagerClickListener
	 *            the mOnCycleImagePagerClickListener to set
	 */
	public void setmOnCycleImagePagerClickListener(
			OnCycleImagePagerClickListener<ImageSlideItem> mOnCycleImagePagerClickListener) {
		this.mOnImageSliderPagerClickListener = mOnCycleImagePagerClickListener;
	}

	public interface OnCycleImagePagerClickListener<T> {
		public void OnCycleImagePagerClick(int dataid, T t);
	}
	
	@Override
	public void onDestory() {
		Log.d(TAG, "CycleImagePagerController onDestroy() mIndicator = " + mIndicator);
		if (null != mIndicator) {
			stopAutoFlowTimer();
		}
		super.onDestory();
	}
	//-----------------------
	//autoFlow
	private boolean isAutoFlow = true;
	
	
	public boolean isAutoFlow() {
		return isAutoFlow;
	}

	public void setAutoFlow(boolean isAutoFlow) {
		this.isAutoFlow = isAutoFlow;
		if(isAutoFlow){
			startAutoFlowTimer();
		}else{
			stopAutoFlowTimer();
		}
	}
	
	//-----------------------
	//showinfo

	private boolean isShowInfo = true;
	public void setShowInfo(boolean isShowInfo){
		this.isShowInfo = isShowInfo;
		if(isShowInfo){
			mInfo.setVisibility(View.VISIBLE);
		}else{
			mInfo.setVisibility(View.INVISIBLE);
		}
	}
	
	public boolean getShowInfo(){
		return isShowInfo;
	}
	//-----------------------------
	// cycle image paper change
	OnCycleImagePagerChangeListener<ImageSlideItem> mOnCycleImagePagerChangeListener;

	public OnCycleImagePagerChangeListener<ImageSlideItem> getmOnCycleImagePagerChangeListener() {
		return mOnCycleImagePagerChangeListener;
	}

	public void setmOnCycleImagePagerChangeListener(
			OnCycleImagePagerChangeListener<ImageSlideItem> mOnCycleImagePagerChangeListener) {
		this.mOnCycleImagePagerChangeListener = mOnCycleImagePagerChangeListener;
	}

	public interface OnCycleImagePagerChangeListener<ImageItemInfo> {
		public void OnCycleImagePagerChange(int pageid, ImageItemInfo t);
	}
	
	public void onResume() {
		startAutoFlowTimer();
	}
	
	public void onStop() {
		stopAutoFlowTimer();
	}
}
