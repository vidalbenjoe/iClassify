package com.capstoneii.iclassify.simulation.knn;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.capstoneii.iclassify.R;

import drawer.MainDrawerActivity;

public class SimulMainKnnActivity extends Fragment{
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
        
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.knnsimul_firstlayout, container, false);  
	       
		
	       
	        
		 return rootView;
	    }
      

    }

