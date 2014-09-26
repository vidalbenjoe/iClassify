package com.capstoneii.iclassify;

import android.app.Activity;

public class ActivityAnimator {
	public void unzoomAnimation(Activity a) {
		a.overridePendingTransition(R.anim.unzoom_in, R.anim.unzoom_out);
	}
}