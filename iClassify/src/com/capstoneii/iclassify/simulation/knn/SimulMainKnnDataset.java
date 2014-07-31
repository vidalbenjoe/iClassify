package com.capstoneii.iclassify.simulation.knn;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capstoneii.iclassify.R;

public class SimulMainKnnDataset extends Fragment {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	    
	        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		    {
			 View rootView = inflater .inflate(R.layout.knnsimul_secondlayout, container, false);  
			 //add a popup displayimng KNN dataset
			
			 
			 return rootView;
		    }
	        
	    }
