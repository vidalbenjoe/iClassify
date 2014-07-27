package com.capstoneii.iclassify.simulation.naivebayes;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.capstoneii.iclassify.R;

public class SimulNaiveBayesCalculateFragment extends Fragment {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		    {
			 View rootView = inflater .inflate(R.layout.naivesimul_thirdlayout, container, false);  
			 	
			/* final Button viewdatasetnaive = (Button) rootView.findViewById(R.id.viewdatasetnaive);
			 final Button viewproblemnext = (Button) rootView.findViewById(R.id.intronaiveskipbt);
		        
		        viewproblemnext.setOnClickListener(new View.OnClickListener()
		        {
		            @Override
		            public void onClick(View InputFragmentView)
		            {
		            	
		            }
		        });*/
			 return rootView;
		    }
	    }