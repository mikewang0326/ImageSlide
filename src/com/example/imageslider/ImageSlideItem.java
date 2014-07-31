package com.example.imageslider;

public class ImageSlideItem<T> {
	
	public String mImageUrl;
	public String mTitle;
	
	public T t;
	
	public void setSlideImageUrl(String url) {
		mImageUrl = url;
	}
	
	public String getSlideImageUrl() {
		return mImageUrl;
	};
	
	public void setSlideImageTitle(String title) {
		mTitle = title;
	}
	
	public String getSlideImageTitle() {
		return mTitle;
	};
	
}
