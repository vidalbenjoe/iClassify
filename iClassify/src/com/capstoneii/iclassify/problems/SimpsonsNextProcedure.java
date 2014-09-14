package com.capstoneii.iclassify.problems;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.SecretTextView;
import com.capstoneii.iclassify.library.TypewriterTextView;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SimpsonsNextProcedure  extends Fragment {
	 SecretTextView secretTextView;
	 ImageView proceduresimpfamily;
	 Animation animSideDown;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	        
	        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		    {
			 View rootView = inflater .inflate(R.layout.simpson_dt_next_proc_layout, container, false);  
			 	
			 final TypewriterTextView simpsonAnimatedTextView = (TypewriterTextView)rootView.findViewById(R.id.simpsonAnimatedTextView);
			 simpsonAnimatedTextView.setTypewriterText(getString(R.string.SimpsonWeight));
			 animSideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
 					R.anim.slide_down);
			 proceduresimpfamily = (ImageView) rootView.findViewById(R.id.proceduresimpfamily);
			 
			 delayFamAnim();
		   
			 return rootView;
		    }
	        
	        public void delayFamAnim(){
	        	  new CountDownTimer(5800, 1000) {

	 			     public void onTick(long millisUntilFinished) {
	 			      
	 			     }
	 			     public void onFinish() {
	 			    	 proceduresimpfamily.startAnimation(animSideDown);
	 			     }
	 			  }.start();
	        }
}
