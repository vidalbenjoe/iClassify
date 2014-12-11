package com.capstoneii.iclassify.library;

import com.capstoneii.iclassify.R;

import android.app.Activity;

public class ActivityAnimator {
	public void flipHorizontalAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
	}
	
}
