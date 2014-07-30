/*
 * Copyright (C) 2012 The Founder Mobile Media Technology Android EPaper Project
 * 
 * Founder Mobile Media PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.imageslider;

public class CycleImgPagerInfo<T> {

	// img src url
	String img;

	// str title
	String title;

	/**
	 * Set the img
	 * 
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * Get the img
	 * 
	 * @param img
	 *            the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * Set the title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Get the title
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public T t;
}
