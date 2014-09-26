package com.capstoneii.iclassify.simulation.knn;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

		return rootView;
	}
}