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
	
    private boolean mAutoFlow = true;
	
	private int mTimeSpan = 5000;//default value
	
	private LinearLayout mIndicatorLayout;
	
	private boolean mIndicatorEnable = false;
	
	private ArrayList<ImageSlideItem> mSlideList;
	
	private OnImageSliderPagerChangeListener<ImageSlideItem> mOnImageSliderPagerChangeListener;

	@Override
	protected void initView() {
		Log.d(TAG, "initView mActivity = " + mActivity);
		initImageLoader();
		initAdapter();
		initViewPager();
		controlViewPagerSpeed();
		initIndiacator();
	}

	private void initImageLoader() {
		mOptions = new DisplayImageOptions.Builder()
				// .showStubImage(R.drawable.bg_base_magazine_item)
				// .showImageForEmptyUri(R.drawable.bg_base_magazine_item)
				// .showImageOnFail(R.drawable.bg_base_magazine_item)
				.cacheInMemory().cacheOnDisc()
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	private void initViewPager() {
		mViewPager = (ImageSliderViewPager) this.mView.findViewById(R.id.cvp);// 鑾峰緱cvp瀵硅薄
		mViewPager.setSliderAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(null);
	}

	private void initIndiacator() {
		mIndicatorLayout = (LinearLayout) this.mView.findViewById(R.id.viewflow_dic_ll);
		mIndicator = (CirclePageIndicator) this.mView.findViewById(R.id.viewflowindic);
		mIndicator.addMyOnAttachStateChangeListener(new MyOnAttachStateChangeListener() {
			
			@Override
			public void onViewAttachedToWindow(View v) {
				Log.d(TAG, "onViewAttachedToWindow()");
				if(mAutoFlow){
					startAutoFlowTimer();
				}
			}
			
			@Override
			public void onViewDetachedFromWindow(View v) {
				Log.d(TAG, "onViewDetachedFromWindow()");
				stopAutoFlowTimer();
			}
			
		});
		
		mIndicator.setViewPager(mViewPager, 0);
		mIndicator.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				if(mOnImageSliderPagerChangeListener!=null && mSlideList!=null && mSlideList.size()>arg0){
					mOnImageSliderPagerChangeListener.OnCycleImagePagerChange(arg0, mSlideList.get(arg0));
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

	private void initAdapter() {
		mAdapter = new BannerPagerAdapter<ImageSlideItem>(mActivity);
		mAdapter.setShowInfoEnable(true);
		mAdapter.setCycleClickListener(mOnImageSliderPagerClickListener);
	}
	
	private void controlViewPagerSpeed() {  
		try {  
		    Field mField;  
		    mField = ViewPager.class.getDeclaredField("mScroller");  
		    mField.setAccessible(true);  
		  
		    mScroller = new FixedSpeedScroller(  
		    		mViewPager.getContext(),  
		       null);  
		    mScroller.setmDuration(2000); 
		    mField.set(mViewPager, mScroller);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		   } 
	
	public void setIndicatorEnable(boolean enable){
		mIndicatorEnable = enable;
		if(mIndicatorEnable){
			mIndicatorLayout.setVisibility(View.VISIBLE);
		}else{
			mIndicatorLayout.setVisibility(View.INVISIBLE);
		}
	}
	
	public boolean isAutoFlow() {
		return mAutoFlow;
	}

	public void setAutoFlow(boolean isAutoFlow) {
		this.mAutoFlow = isAutoFlow;
		if(isAutoFlow){
			startAutoFlowTimer();
		}else{
			stopAutoFlowTimer();
		}
	}
	
	public void startAutoFlowTimer() {
		stopAutoFlowTimer();
		initHanlderIfNeed();
		Message message = mHandler.obtainMessage(0);
		mHandler.sendMessageDelayed(message, mTimeSpan);
	}

	private void initHanlderIfNeed() {
		if (null != mHandler)
			return;
		
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (getChildCount() <= 1)//1涓厓绱犱笉婊氬姩
					return;
				snapToScreen((getCurrentItem() + 1) % getChildCount());
				Message message = mHandler.obtainMessage(0);
				sendMessageDelayed(message, mTimeSpan);
			}
		};
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
		this.mSlideList = mList;
		mAdapter.setList(mList);
		if(mOnImageSliderPagerChangeListener!=null && mList!=null && mList.size()>0){
			mOnImageSliderPagerChangeListener.OnCycleImagePagerChange(0, mList.get(0));
		}
	}

	OnCycleImagePagerClickListener<ImageSlideItem> mOnImageSliderPagerClickListener;

	public void setmOnCycleImagePagerClickListener(
			OnCycleImagePagerClickListener<ImageSlideItem> mOnCycleImagePagerClickListener) {
		this.mOnImageSliderPagerClickListener = mOnCycleImagePagerClickListener;
	}

	public interface OnCycleImagePagerClickListener<T> {
		public void OnCycleImagePagerClick(int dataid, T t);
	}
	
	public void setOnImageSliderPagerChangeListener(
			OnImageSliderPagerChangeListener<ImageSlideItem> onImageSliderPagerChangeListener) {
		this.mOnImageSliderPagerChangeListener = onImageSliderPagerChangeListener;
	}

	public interface OnImageSliderPagerChangeListener<ImageItemInfo> {
		public void OnCycleImagePagerChange(int pageid, ImageItemInfo t);
	}
	
	@Override
	public void onDestory() {
		Log.d(TAG, "CycleImagePagerController onDestroy() mIndicator = " + mIndicator);
		if (null != mIndicator) {
			stopAutoFlowTimer();
		}
		super.onDestory();
	}
	
}
