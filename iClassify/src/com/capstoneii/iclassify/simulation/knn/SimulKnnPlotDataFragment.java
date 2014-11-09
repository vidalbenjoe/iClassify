package com.capstoneii.iclassify.simulation.knn;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;

public class SimulKnnPlotDataFragment extends Fragment {
	Animation animation;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.knnsimul_layoutfifth,
				container, false);

		animation = AnimationUtils.loadAnimation(getActivity()
				.getApplicationContext(), R.anim.unzoom_in);

		final ImageView thirdsimulknnimage = (ImageView) rootView
				.findViewById(R.id.thirdsimulknnimage);
		final ImageView fourthsimulknnimage = (ImageView) rootView
				.findViewById(R.id.fourthsimulknnimage);
		final ImageView simulknnfifthnextbt = (ImageView) rootView
				.findViewById(R.id.simulknnfifthnextbt);

		thirdsimulknnimage.startAnimation(animation);
		fourthsimulknnimage.startAnimation(animation);
		simulknnfifthnextbt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment SimulMainKnnLastFragment = new SimulMainKnnLastFragment();
            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
            	ft.replace(R.id.frame_container, SimulMainKnnLastFragment);
            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            	ft.addToBackStack(SimulMainKnnLastFragment.getTag());
            	ft.commit();
			}
		});

		return rootView;
	}
}