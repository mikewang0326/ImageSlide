package com.example.imageslider;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imageslider.ImageSliderPagerController.OnCycleImagePagerClickListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BannerPagerAdapter<T> extends ImageSliderPagerAdapter {
	private static final String TAG = "bpa";

	private DisplayImageOptions mOptions = new DisplayImageOptions.Builder()
			// .showStubImage(R.drawable.bg_base_magazine_item)
			// .showImageForEmptyUri(R.drawable.bg_base_magazine_item)
			// .showImageOnFail(R.drawable.bg_base_magazine_item)
			.cacheInMemory().cacheOnDisc().bitmapConfig(Bitmap.Config.RGB_565)
			.build();

	private boolean mShowInfoEnable = true;

	private OnCycleImagePagerClickListener<T> mOnCycleImagePagerClickListener;


	public BannerPagerAdapter(Activity context) {
		super(context);
	}

	public void setShowInfoEnable(boolean b) {
		mShowInfoEnable = b;
	}

	public void setCycleClickListener(OnCycleImagePagerClickListener<T> mOnCycleImagePagerClickListener) {
		this.mOnCycleImagePagerClickListener = mOnCycleImagePagerClickListener;
	}

	@Override
	public View createViewItem(final int position) {
		ImageSlideItem<T> item = (ImageSlideItem<T>) this.getItem(position);
		View v = this.mContext.getLayoutInflater().inflate(
				R.layout.item_slider_image_pager, null);
		ImageView iv = (ImageView) v.findViewById(R.id.img);
		TextView title = (TextView) v.findViewById(R.id.img_title);

		if (mShowInfoEnable) {
			title.setVisibility(View.VISIBLE);
		} else {
			title.setVisibility(View.INVISIBLE);
		}

		if (item != null) {
			ImageLoader.getInstance().displayImage(item.getSlideImageUrl(), iv,
					mOptions);
			title.setText(item.getSlideImageTitle() != null ? item.getSlideImageTitle() : "");
		}

		v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "onclick() mOnCycleImagePagerClickListener = "
						+ mOnCycleImagePagerClickListener);
				if (mOnCycleImagePagerClickListener != null) {
					ImageSlideItem<T> item = (ImageSlideItem<T>) getItem(position);
					Log.d(TAG, "onclick() item = " + item);
					if (item != null)
						mOnCycleImagePagerClickListener.OnCycleImagePagerClick(
								position, item.t);
				}
			}
		});
		return v;
	}
}
