package com.capstoneii.iclassify.simulation.desiciontree;

import com.capstoneii.iclassify.ActivityAnimator;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.ExpandableButtonMenu;
import com.capstoneii.iclassify.library.ExpandableMenuOverlay;
import com.capstoneii.iclassify.library.SecretTextView;

import drawer.MainDrawerActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
@SuppressLint("NewApi")
public class SimulStartActivity extends Fragment {
private Button viewsumulation;

    SecretTextView secretTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
        
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.secretetext_layout, container, false);  
	       
		 secretTextView = (SecretTextView)rootView.findViewById(R.id.textview);
	        secretTextView.setmDuration(2000);
	        secretTextView.setIsVisible(false);
	        secretTextView.toggle();
	      
	        Button viewsimulation = (Button) rootView.findViewById(R.id.viewsimul);
	        
	        
	        viewsimulation.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	// Start main activity
	            
	            	Fragment SimulProcessIntroActivity = new SimulProcessIntroActivity();
 	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
 	            	ft.replace(R.id.frame_container, SimulProcessIntroActivity);
 	            	ft.addToBackStack(null);
 	            	ft.commit();
	              	 
	            }
	        });
	        
		 	
	       
		 return rootView;
	    }
        

     
       
    }
