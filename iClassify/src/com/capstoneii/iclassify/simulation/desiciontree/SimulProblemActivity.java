package com.capstoneii.iclassify.simulation.desiciontree;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.capstoneii.iclassify.R;

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
	            	ft.addToBackStack(null);
	            	ft.commit();
	            
	           }
	       });
		 	
	       
		 return rootView;
	    }
        
       
    }


