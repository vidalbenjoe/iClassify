package com.capstoneii.iclassify.simulation.desiciontree;

import com.capstoneii.iclassify.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SimulHeirarchyDataActivity extends Fragment {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	}
	
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.simulintroprocess_layout, container, false);  
	   
		 return rootView;
	    }
}
