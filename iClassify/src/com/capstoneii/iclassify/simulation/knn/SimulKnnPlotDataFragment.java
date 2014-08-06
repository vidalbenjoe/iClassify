package com.capstoneii.iclassify.simulation.knn;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class SimulKnnPlotDataFragment extends Fragment{

	  public void onCreate(Bundle savedInstanceState){
		  super.onCreate(savedInstanceState);
	  }
	  
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.knnsimul_fifthlayout, container, false);  
		
		 return rootView;
	    }
}