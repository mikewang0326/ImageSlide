package com.example.imageslider;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	public static final String TAG = "main";

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageSliderPagerController<ImageSlideItem> cycleController = new ImageSliderPagerController<ImageSlideItem>(this,
				null, false);
		cycleController
				.setmOnCycleImagePagerClickListener(new ImageSliderPagerController.OnCycleImagePagerClickListener<ImageSlideItem>() {

					@Override
					public void OnCycleImagePagerClick(int dataid,
							ImageSlideItem t) {
						Log.d(TAG, "OnCycleImagePagerClick() dataid = " + dataid);
					}

				});
		
		cycleController.setShowInfo(true);
		
		cycleController.setAutoFlow(true);

		cycleController.setmOnCycleImagePagerChangeListener(new ImageSliderPagerController.OnCycleImagePagerChangeListener<ImageSlideItem>() {

			@Override
			public void OnCycleImagePagerChange(int pageid, ImageSlideItem t) {
				Log.d(TAG, "OnCycleImagePagerChange() pageid = " + pageid);
			}
		
		});
		
		BannerItem bannerItem = new BannerItem();
	    bannerItem.setDescription("test");
	    bannerItem.setHref("http://www.sina.com.cn");
		bannerItem.setImageUrl("http://s3.51cto.com/wyfs02/M00/39/6C/wKiom1O6FhuT6jR3AAElYgNBeaQ888.jpg");
		
		ArrayList<ImageSlideItem> list = new ArrayList<ImageSlideItem>();
		list.add(bannerItem);
		list.add(bannerItem);
		list.add(bannerItem);
		list.add(bannerItem);
		
		cycleController.setData(list);
	}
	
}
