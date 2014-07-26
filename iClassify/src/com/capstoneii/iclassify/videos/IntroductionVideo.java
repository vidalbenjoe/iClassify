package com.capstoneii.iclassify.videos;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.ExpandableButtonMenu;
import com.capstoneii.iclassify.library.ExpandableMenuOverlay;

import drawer.MainDrawerActivity;
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
import android.widget.TextView;
import android.widget.VideoView;

public class IntroductionVideo extends ActionBarActivity {
	private VideoView vv;
	public String videoFile1;
	public TextView setvideoDesc;
	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.choose_video_layout);
	        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.divider_color)));
	        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	        
	        vv = (VideoView) findViewById(R.id.videoView1);
			videoFile1 = "android.resource://" + getPackageName() + "/" + R.raw.videoknn;
			
			
			setvideoDesc = (TextView) findViewById(R.id.videoDesc);
			setvideoDesc.setMovementMethod(new ScrollingMovementMethod());
			setvideoDesc.setText(R.string.knnvideodesc);
			
			MediaController mc = new MediaController(this);
		    mc.setAnchorView(vv);
	        mc.setMediaPlayer(vv);
	        Uri uri = Uri.parse(videoFile1);
	        vv.setMediaController(mc);
	        vv.setVideoURI(uri);
	        vv.requestFocus();
	        vv.start();
	        
	    }
	    
	    public void onCompletion(MediaPlayer  mp) {
		       // Statements to be executed when the video finishes.
		       finish();
		       
		       //pause.setVisibility(ImageView.VISIBLE);
		    }

	    public boolean onTouchEvent (MotionEvent ev){	
	        if(ev.getAction() == MotionEvent.ACTION_DOWN){
	           if(vv.isPlaying()){
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
	    // app icon in action bar clicked; go home
	    return true;
	    default:
	    return super.onOptionsItemSelected(item); 
	        }
	    }
	}
