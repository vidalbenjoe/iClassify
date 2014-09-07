package com.capstoneii.iclassify.problems;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.capstoneii.iclassify.R;

public class SimpsonsDecisionTreeFragment extends Fragment {
	Button simpsonNextbt;
	RelativeLayout simpsonRelative;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);}
	    
	     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		    {
	    	 View rootView = inflater .inflate(R.layout.simpson_dt_layout_one, container, false);  
	    
	    	 simpsonNextbt = (Button) rootView.findViewById(R.id.simpsonNextbt);
	    	 
	    	 simpsonRelative = (RelativeLayout) rootView.findViewById(R.id.simpsonRelative);
	    	 
	    	 simpsonNextbt.setOnClickListener(new View.OnClickListener()
		        {
		            @Override
		            public void onClick(View InputFragmentView)
		            {
		            	simpsonRelative.setBackgroundResource(R.drawable.backgroundblur);
		            }
		        });
	    	 
	    	
	    	 return rootView;
	    }
	   
	}