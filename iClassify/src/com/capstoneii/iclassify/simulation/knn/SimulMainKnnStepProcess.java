package com.capstoneii.iclassify.simulation.knn;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class SimulMainKnnStepProcess extends Fragment {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.knnsimul_fourthlayout,
				container, false);
		final TypewriterTextView knnfourthext = (TypewriterTextView) rootView
				.findViewById(R.id.knnfourthext);
		knnfourthext.setTypewriterText(getString(R.string.knnsimulexample4));

		final ImageView knnsecondfactorybt = (ImageView) rootView.findViewById(R.id.knnsecondfactorybt);
		
		knnsecondfactorybt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Fragment SimulKnnPlotDataFragment = new SimulKnnPlotDataFragment();
            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
            	ft.replace(R.id.frame_container, SimulKnnPlotDataFragment);
            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            	ft.addToBackStack(SimulKnnPlotDataFragment.getTag());
            	ft.commit();
			}
		});
		
		return rootView;
	}
}