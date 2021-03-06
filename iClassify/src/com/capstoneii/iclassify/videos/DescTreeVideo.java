package com.capstoneii.iclassify.videos;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.MediaController;
import android.widget.VideoView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.ExpandableButtonMenu;
import com.capstoneii.iclassify.library.ExpandableMenuOverlay;
import com.capstoneii.iclassify.library.JustifyTextView;

import drawer.MainDrawerActivity;

public class DescTreeVideo extends ActionBarActivity {
	private VideoView vv;
	public String videoFile1, videoFile2, videoFile3;
	public JustifyTextView setvideoDesc;
	int vid;
	private ExpandableMenuOverlay menuOverlay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_video_layout);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		vv = (VideoView) findViewById(R.id.videoView1);
		videoFile1 = "android.resource://" + getPackageName() + "/"
				+ R.raw.videoknn;

		videoFile2 = "android.resource://" + getPackageName() + "/"
				+ R.raw.videoidtree;

		videoFile3 = "android.resource://" + getPackageName() + "/"
				+ R.raw.videonaive;

		setvideoDesc = (JustifyTextView) findViewById(R.id.videoDesc);
		setvideoDesc.setMovementMethod(new ScrollingMovementMethod());
		setvideoDesc.setText(R.string.treevideodesc);

		MediaController mc = new MediaController(this);
		mc.setAnchorView(vv);
		mc.setMediaPlayer(vv);
		Uri uri = Uri.parse(videoFile2);
		vv.setMediaController(mc);
		vv.setVideoURI(uri);
		vv.requestFocus();
		vv.start();

		menuOverlay = (ExpandableMenuOverlay) findViewById(R.id.button_menu);
		menuOverlay
				.setOnMenuButtonClickListener(new ExpandableButtonMenu.OnMenuButtonClick() {
					@Override
					public void onClick(ExpandableButtonMenu.MenuButton action) {
						switch (action) {
						case MID:// knn
							menuOverlay.getButtonMenu().toggle();
							MediaController mc1 = new MediaController(
									DescTreeVideo.this);
							mc1.setAnchorView(vv);
							mc1.setMediaPlayer(vv);
							Uri uri = Uri.parse(videoFile1);
							vv.setMediaController(mc1);
							vv.setVideoURI(uri);
							vv.requestFocus();
							vv.start();
							setvideoDesc = (JustifyTextView) findViewById(R.id.videoDesc);
							setvideoDesc
									.setMovementMethod(new ScrollingMovementMethod());
							setvideoDesc.setText(R.string.knnvideodesc);
							break;

						case LEFT:// idr

							menuOverlay.getButtonMenu().toggle();
							MediaController mc2 = new MediaController(
									DescTreeVideo.this);
							mc2.setAnchorView(vv);
							mc2.setMediaPlayer(vv);
							Uri uri2 = Uri.parse(videoFile2);
							vv.setMediaController(mc2);
							vv.setVideoURI(uri2);
							vv.requestFocus();
							vv.start();
							setvideoDesc = (JustifyTextView) findViewById(R.id.videoDesc);
							setvideoDesc
									.setMovementMethod(new ScrollingMovementMethod());
							setvideoDesc.setText(R.string.treevideodesc);
							

							break;
						case RIGHT:// naive
							menuOverlay.getButtonMenu().toggle();
							MediaController mc3 = new MediaController(
									DescTreeVideo.this);
							mc3.setAnchorView(vv);
							mc3.setMediaPlayer(vv);
							Uri uri3 = Uri.parse(videoFile3);
							vv.setMediaController(mc3);
							vv.setVideoURI(uri3);
							vv.requestFocus();
							vv.start();
							setvideoDesc = (JustifyTextView) findViewById(R.id.videoDesc);
							setvideoDesc
									.setMovementMethod(new ScrollingMovementMethod());
							setvideoDesc.setText(R.string.naivevideodesc);
							break;
						}
					}
				});

	}

	public void onCompletion(MediaPlayer mp) {
		// Statements to be executed when the video finishes.
		finish();

		// pause.setVisibility(ImageView.VISIBLE);
	}

	public boolean onTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			if (vv.isPlaying()) {
				vv.pause();

			} else {
				vv.start();

			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, MainDrawerActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);

		case R.id.action_settings:

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onBackPressed() {

	}
}
