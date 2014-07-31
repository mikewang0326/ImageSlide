package com.example.imageslider;

import java.util.ArrayList;

import com.example.imageslider.ImageSliderPagerController.OnCycleImagePagerClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	
	public static final String TAG = "main";

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageSliderPagerController<BannerItem> cycleController = new ImageSliderPagerController<BannerItem>(this,
				null, false);
		cycleController
				.setmOnCycleImagePagerClickListener(new OnCycleImagePagerClickListener<BannerItem>() {

					@Override
					public void OnCycleImagePagerClick(int dataid,
							BannerItem t) {
						Log.d(TAG, "OnCycleImagePagerClick() dataid = " + dataid);
					}

				});
		
		cycleController.setIndicatorEnable(true);

		cycleController.setOnImageSliderPagerChangeListener(new ImageSliderPagerController.OnImageSliderPagerChangeListener<BannerItem>() {

			@Override
			public void OnCycleImagePagerChange(int pageid, BannerItem t) {
				Log.d(TAG, "OnCycleImagePagerChange() pageid = " + pageid);
			}
		
		});
		
		BannerItem bannerItem = new BannerItem();
	    bannerItem.setDescription("test");
	    bannerItem.setHref("http://www.sina.com.cn");
		bannerItem.setImageUrl("http://s3.51cto.com/wyfs02/M00/39/6C/wKiom1O6FhuT6jR3AAElYgNBeaQ888.jpg");
		
		ArrayList<ImageSlideItem<BannerItem>> list = new ArrayList<ImageSlideItem<BannerItem>>();
		ImageSlideItem<BannerItem> slideItem = new ImageSlideItem<BannerItem>();
		slideItem.setSlideImageUrl(bannerItem.getImageUrl());
		slideItem.setSlideImageTitle(bannerItem.getTitle());
		
		list.add(slideItem);
		list.add(slideItem);
		list.add(slideItem);
		list.add(slideItem);
		
		cycleController.setData(list);
		cycleController.setAutoFlow(true);
		
	}
	
}
