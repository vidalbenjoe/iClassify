package com.capstoneii.iclassify.simulation.desiciontree;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.capstoneii.iclassify.R;

@SuppressLint("NewApi")
public class SimulProblemActivity extends Fragment{
private TextView probtext;
private Button viewdataset;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	}
	
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.simulproblem_layout, container, false);  
	   
		 final ImageView image = (ImageView)rootView.findViewById(R.id.baseballimg);
	        Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.unzoom_in);
	        image.startAnimation(animation);
	        
	       probtext = (TextView) rootView.findViewById(R.id.problemtextdesc);
	       probtext.setMovementMethod(ScrollingMovementMethod.getInstance());
	       viewdataset = (Button) rootView.findViewById(R.id.viewdatasetbt);
	       viewdataset.setOnClickListener(new View.OnClickListener()
	       {
	           @Override
	           public void onClick(View InputFragmentView)
	           {
	        
	        	    Fragment SimulDatasetActivity = new SimulDatasetActivity();
	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();	     
	            	ft.replace(R.id.frame_container, SimulDatasetActivity);
	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	            	ft.addToBackStack(SimulDatasetActivity.getTag());
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


