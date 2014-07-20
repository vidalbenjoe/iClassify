package com.capstoneii.iclassify.simulation.desiciontree;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.SecretTextView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
@SuppressLint("NewApi")
public class SimulProcessIntroActivity extends Fragment {
private Button viewproblemnext;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.simulintroprocess_layout, container, false);  
	   
		    final ImageView image = (ImageView)rootView.findViewById(R.id.imageView1);
	        Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.unzoom_in);
	        image.startAnimation(animation);
	        
	        Button viewproblemnext = (Button) rootView.findViewById(R.id.processintrobtnnext);
	        
	        viewproblemnext.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	 Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.unzoom_out);
	                 image.startAnimation(animation);
	            	
	            	
	                 	Fragment SimulProblemActivity = new SimulProblemActivity();
	 	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	 	            	ft.replace(R.id.frame_container, SimulProblemActivity);
	 	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	 	            	ft.addToBackStack(SimulProblemActivity.getTag());
	 	            	ft.commit();
	            }
	        });
		 	
	       
		 return rootView;
	    }
        
        public void onBackPressed() {

            // See bug: http://stackoverflow.com/questions/13418436/android-4-2-back-stack-behaviour-with-nested-fragments/14030872#14030872
            // If the fragment exists and has some back-stack entry
            FragmentManager fm = getFragmentManager();
            Fragment currentFragment = fm.findFragmentById(R.id.content_frame);
            if (currentFragment != null && currentFragment.getChildFragmentManager().getBackStackEntryCount() > 0) {
                // Get the fragment fragment manager - and pop the backstack
                currentFragment.getChildFragmentManager().popBackStack();
            }
       
           
            }
    
    }
