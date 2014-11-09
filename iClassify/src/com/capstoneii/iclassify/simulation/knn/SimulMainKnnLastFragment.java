package com.capstoneii.iclassify.simulation.knn;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class SimulMainKnnLastFragment extends Fragment {
	Animation animation;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.knnsimul_sixthlayout,
				container, false);

		final TypewriterTextView sixthdesckknn = (TypewriterTextView) rootView
				.findViewById(R.id.sixthdesckknn);
		sixthdesckknn.setTypewriterText(getString(R.string.knnsimulationsixthalgostep));

		return rootView;
	}
}
