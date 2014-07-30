package com.example.imageslider;

import android.view.View;
import android.view.ViewGroup;

public interface OnItemSubViewClickListener {
	void onItemSubViewClick(View view, int position, View convertView,
			ViewGroup parent, Object tag);
}