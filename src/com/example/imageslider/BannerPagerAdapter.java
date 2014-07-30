package com.example.imageslider;

import java.util.ArrayList;

import com.example.imageslider.ImageSliderPagerController.OnCycleImagePagerClickListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BannerPagerAdapter<ImageSlideItem> extends ImageSliderPagerAdapter{
	
	private DisplayImageOptions mOptions = new DisplayImageOptions.Builder()
	// .showStubImage(R.drawable.bg_base_magazine_item)
	// .showImageForEmptyUri(R.drawable.bg_base_magazine_item)
	// .showImageOnFail(R.drawable.bg_base_magazine_item)
	.cacheInMemory().cacheOnDisc()
	.bitmapConfig(Bitmap.Config.RGB_565).build();
	
	private boolean mShowInfoEnable = true;
	
	private OnCycleImagePagerClickListener mOnCycleImagePagerClickListener;
	
	private ArrayList<BannerItem> mList;

	public BannerPagerAdapter(Activity context) {
		super(context);
	}
	
	public void setShowInfoEnable(boolean b) {
		mShowInfoEnable = b;
	} 
	
	public void setCycleClickListener(OnCycleImagePagerClickListener<ImageSlideItem> listener) {
		mOnCycleImagePagerClickListener = listener;
	}
	
	@Override
	public View createViewItem(final int position) {
		BannerItem item = (BannerItem) this
				.getItem(position);
		View v = this.mContext.getLayoutInflater().inflate(
				R.layout.item_slider_image_pager, null);
		ImageView iv = (ImageView) v.findViewById(R.id.img);
		TextView title = (TextView) v.findViewById(R.id.img_title);
		
		if(mShowInfoEnable){
			title.setVisibility(View.VISIBLE);
		}else{
			title.setVisibility(View.INVISIBLE);
		}
		
		if (item != null) {
			ImageLoader.getInstance().displayImage(item.getImageUrl(), iv,
					mOptions);
			title.setText(item.getTitle() != null ? item.getTitle()
					: "");
		}

		v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mOnCycleImagePagerClickListener != null) {
					BannerItem item = (BannerItem)getItem(position);
					if(item!=null)
					mOnCycleImagePagerClickListener
							.OnCycleImagePagerClick(position,
									item);
				}
			}
		});
		return v;
	}

}
