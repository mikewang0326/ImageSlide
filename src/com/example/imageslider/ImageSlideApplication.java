package com.example.imageslider;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class ImageSlideApplication extends Application{
	
	@Override
	public void onCreate() {
		super.onCreate();
	    initImageLoader(getApplicationContext());
	}
	
	public static void initImageLoader(Context context) {
		Log.d("main", "initImageLoader()");
		// This configuration tuning is custom. You can tune every option, you may tune some of them, 
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new FIFOLimitedMemoryCache(1024 * 2000))
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				//.enableLogging() // Not necessary in common
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

}
