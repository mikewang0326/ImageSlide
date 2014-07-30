package com.example.imageslider;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class BasePagerAdapter<T> extends PagerAdapter {
	protected ArrayList<T> mList;
	protected Activity mContext;

	// 缓存pageView
	protected View[] mPageViews;

	public BasePagerAdapter(Activity context) {
		this.mContext = context;
		mList = new ArrayList<T>();
	}

	@Override
	public int getCount() {
		if (mPageViews != null)
			return mPageViews.length;
		else if (mList != null)
			return mList.size();
		else
			return 0;
	}

	/***
	 * get cur view
	 * @param position
	 * @return
	 */
	public View getItemView(int position){
		if(mPageViews != null && position<mPageViews.length){
			return mPageViews[position];
		}
		return null;
	}
	
	public long getItemId(int position) {
		return position;
	}

	public Object getItem(int position) {
		
		return mList == null && mList.size()>position ? null : mList.get(position);
	}

	public void setList(ArrayList<T> list) {
		this.mList = list;
		buildPageViews();
		refresh();
	}

	public ArrayList<T> getList() {
		return this.mList;
	}

	// 根据mList 创建pageViews
	public void buildPageViews() {
		if (this.mList == null || this.mList.size() == 0) {
			clearPageViews();
		} else {
			this.mPageViews = new View[this.getCount()];
		}
	}

	public void clearPageViews() {
		if (this.mPageViews != null) {
			if (this.mPageViews.length > 0) {
				for (View item : this.mPageViews) {
					item = null;
				}
			}
			this.mPageViews = null;
		}
	}

	private void refresh() {
		this.notifyDataSetChanged();
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	public View createViewItem(int position) {

		return null;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		if (mPageViews != null && position < mPageViews.length) {
			if (mPageViews[position] == null) {
				mPageViews[position] = createViewItem(position);
			}
			container.addView(mPageViews[position], 0);
			return mPageViews[position];
		} else {
			return super.instantiateItem(container, position);
		}
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
		if (mPageViews != null && position < mPageViews.length) {
			mPageViews[position] = null;
		}
	}
}
