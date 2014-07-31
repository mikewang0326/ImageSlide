package com.example.imageslider;

public class BannerItem{
	
	private String id;
	
	private String description;
	
	private String imageUrl;
	
	private String href;
	
	private String title;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setImageUrl(String url) {
		this.imageUrl = url;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setHref(String href) {
		this.href = href;
	}

	public String getHref() {
		return href;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

}
