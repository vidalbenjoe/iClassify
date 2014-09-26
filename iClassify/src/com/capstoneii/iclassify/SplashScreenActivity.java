package com.capstoneii.iclassify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.capstoneii.iclassify.library.Titanic;
import com.capstoneii.iclassify.library.TitanicTextView;
import com.capstoneii.iclassify.library.Typefaces;
import com.capstoneii.iclassify.viewpager.ViewPagerIntro;

public class SplashScreenActivity extends Activity {

	AnimationDrawable frameAnimation;
	private static String TAG = SplashScreenActivity.class.getName();
	private static long SLEEP_TIME = 5; // Sleep for some time

	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); // Removes title bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // Removes
																// notification
																// bar

		setContentView(R.layout.splash_screen);

		TitanicTextView tv = (TitanicTextView) findViewById(R.id.titanictext);
		// set fancy typeface
		tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

		// start animation
		new Titanic().start(tv);

		// Start timer and launch main activity
		IntentLauncher launcher = new IntentLauncher();
		launcher.start();
	}

	private class IntentLauncher extends Thread {
		@Override
		/**
		 * Sleep for some time and than start new activity.
		 */
		public void run() {
			try {
				// Sleeping

				Thread.sleep(SLEEP_TIME * 2000);

			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}

			// Start main activity
			Intent intent = new Intent(SplashScreenActivity.this,
					ViewPagerIntro.class);
			SplashScreenActivity.this.startActivity(intent);
			SplashScreenActivity.this.finish();
		}

	}
}