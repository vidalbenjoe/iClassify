package com.capstoneii.iclassify.simulation.naivebayes;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;

public class SimulNaiveBayesStepFragment extends Fragment {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.naivesimul_steplayout, container, false);  
	   
		    final ImageView stepnaiveimg = (ImageView)rootView.findViewById(R.id.stepnaiveimg);
	        Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.unzoom_in);
	        stepnaiveimg.startAnimation(animation);
	        
	        ImageView stepnaiveskipbt = (ImageView) rootView.findViewById(R.id.stepnaiveskipbt);
	        
	        stepnaiveskipbt.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	 Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.unzoom_out);
	            	 stepnaiveimg.startAnimation(animation);
	                 	Fragment SimulNaiveBayesTableFragment = new SimulNaiveBayesTableFragment();
	 	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	 	            	ft.replace(R.id.frame_container, SimulNaiveBayesTableFragment);
	 	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	 	            	ft.addToBackStack(SimulNaiveBayesTableFragment.getTag());
	 	            	ft.commit();
	            }
	        });
		 return rootView;
	    }
    }
