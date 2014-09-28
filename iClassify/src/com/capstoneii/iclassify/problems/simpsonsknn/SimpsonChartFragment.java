package com.capstoneii.iclassify.problems.simpsonsknn;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class SimpsonChartFragment extends Fragment {
	ImageView simpsonschartimage;
	Animation zoomIn,zoomOut;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		    {
			 View rootView = inflater .inflate(R.layout.simpson_chart_layout, container, false);  
			 	
			 
			 zoomIn = AnimationUtils.loadAnimation(getActivity(),
						R.anim.unzoom_in);
			 zoomOut = AnimationUtils.loadAnimation(getActivity(),
						R.anim.unzoom_out);
			 
			 
			 final TypewriterTextView simpsonAnimatedTextViewChart = (TypewriterTextView)rootView.findViewById(R.id.simpsonAnimatedTextViewChart);
			 simpsonAnimatedTextViewChart.setTypewriterText(getString(R.string.problemsimpson));
			 
			 simpsonschartimage= (ImageView) rootView.findViewById(R.id.simpsonschartimage);
			 simpsonschartimage.startAnimation(zoomIn);
			 
			 return rootView;
		    }
	        
	        
	        public void onBackPressed(){
	        	
	        }
}
