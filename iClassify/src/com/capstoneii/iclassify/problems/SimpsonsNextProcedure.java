package com.capstoneii.iclassify.problems;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.SecretTextView;
import com.capstoneii.iclassify.library.TypewriterTextView;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class SimpsonsNextProcedure  extends Fragment {
	 SecretTextView secretTextView;
	 ImageView proceduresimpfamily;
	 Button nextProcBt;
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
			 proceduresimpfamily.setVisibility(View.INVISIBLE);
			 
			 nextProcBt = (Button) rootView.findViewById(R.id.nextProcBt);
			 
			 nextProcBt.setOnClickListener(new View.OnClickListener()
		        {
		            @Override
		            public void onClick(View InputFragmentView)
		            {
		            	 	Fragment SimpsonMaleClassifier = new SimpsonMaleClassifier();
		 	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
		 	            	ft.replace(R.id.frame_container, SimpsonMaleClassifier);
		 	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			            	ft.addToBackStack(SimpsonMaleClassifier.getTag());
		 	            	ft.commit();
		            }
		        });
			 
			 delayFamAnim();
			 
			 return rootView;
		    }
	        public void delayFamAnim(){
	        	  new CountDownTimer(6500, 1000) {

	 			     public void onTick(long millisUntilFinished) {
	 			      
	 			     }
	 			     public void onFinish() {
	 			    	 proceduresimpfamily.setVisibility(View.VISIBLE);
	 			    	 proceduresimpfamily.startAnimation(animSideDown);
	 			     }
	 			  }.start();
	        }
	        public void onBackPressed(){
	        	
	        }
}
