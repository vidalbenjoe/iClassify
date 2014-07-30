package com.capstoneii.iclassify.simulation.desiciontree;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.videos.VideoMenuActivity;

@SuppressLint("NewApi")
public class SimulDecisionResults extends Fragment{
	private VideoView vv;
	public String videoFile;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	}
	
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.simuldecisionresult_layout, container, false);  
		  
		 Button watchvid = (Button) rootView.findViewById(R.id.watchvideoidr);
		 Button takeassess = (Button) rootView.findViewById(R.id.takeassess);
		 
		 final Dialog dialog = new Dialog(getActivity());
     	 dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
         dialog.setContentView(R.layout.popup);
         dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
         TextView strongtext = (TextView) dialog.findViewById(R.id.occurence);
         strongtext.setText(R.string.humidityresult);
         dialog.show();
		 
		 
		 watchvid.setOnClickListener(new View.OnClickListener()
	       {
	           @Override
	           public void onClick(View InputFragmentView)
	           {
	        	   
	                final Dialog dialog = new Dialog(getActivity());
	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	                dialog.setContentView(R.layout.watch_video_popup);
	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	                
	                dialog.show();
	                
	           }
	       });
		 
		 takeassess.setOnClickListener(new View.OnClickListener()
	       {
	           @Override
	           public void onClick(View InputFragmentView)
	           {
	        	 //ASSESSMENT
	           }
	       });
		 
		 
		 return rootView;
	    }
        
        public void showPopup(View v) {
            PopupMenu popup = new PopupMenu(getActivity(), v);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu, popup.getMenu());
            popup.show();
        }
        
        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        	 getActivity().getMenuInflater().inflate(R.menu.menu, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
        
       
//        
//        public boolean onOptionsItemSelected(MenuItem item) {
//        	  switch (item.getItemId()) {
//        	    case R.id.watchvid:
//        	     
//        	      return true;
//        	    case R.id.takeassessment:
//        	     
//        	      return true;
//        	    default:
//        	      return super.onOptionsItemSelected(item);
//        	  }
//        	}
        
        
        
       
}