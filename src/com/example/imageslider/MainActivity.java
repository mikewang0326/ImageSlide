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
		
		CycleImagePagerController<ImageItemInfo> cycleController = new CycleImagePagerController<ImageItemInfo>(this,
				null, false);
		cycleController
				.setmOnCycleImagePagerClickListener(new CycleImagePagerController.OnCycleImagePagerClickListener<ImageItemInfo>() {

					@Override
					public void OnCycleImagePagerClick(int dataid,
							ImageItemInfo t) {
						Log.d(TAG, "OnCycleImagePagerClick() dataid = " + dataid);
					}

				});
		
		cycleController.setShowInfo(true);
		
		cycleController.setAutoFlow(true);

		cycleController.setmOnCycleImagePagerChangeListener(new CycleImagePagerController.OnCycleImagePagerChangeListener<ImageItemInfo>() {

			@Override
			public void OnCycleImagePagerChange(int pageid, ImageItemInfo t) {
				Log.d(TAG, "OnCycleImagePagerChange() pageid = " + pageid);
			}
		
		});
		
		ImageItemInfo imageItemInfo = new ImageItemInfo();
	    imageItemInfo.setDescripe("test");
	    imageItemInfo.setHref("http://www.sina.com.cn");
		imageItemInfo.setImageUrl("http://s3.51cto.com/wyfs02/M00/39/6C/wKiom1O6FhuT6jR3AAElYgNBeaQ888.jpg");
		
		ArrayList<ImageItemInfo> list = new ArrayList<ImageItemInfo>();
		list.add(imageItemInfo);
		list.add(imageItemInfo);
		list.add(imageItemInfo);
		list.add(imageItemInfo);
		
		cycleController.setData(list);
	}
	
}
