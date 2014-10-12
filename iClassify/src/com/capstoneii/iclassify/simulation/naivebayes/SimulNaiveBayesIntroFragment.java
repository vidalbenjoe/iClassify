package com.capstoneii.iclassify.simulation.naivebayes;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;

@SuppressLint("NewApi")
public class SimulNaiveBayesIntroFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.naivesimul_firstlayout,
				container, false);
		final ImageView intronaiveskipbt = (ImageView) rootView
				.findViewById(R.id.intronaiveskipbt);
		final TypewriterTextView naivetextfirst = (TypewriterTextView) rootView
				.findViewById(R.id.naivetextfirst);
		naivetextfirst
				.setTypewriterText(getString(R.string.naivebayesobjectives));

		intronaiveskipbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Fragment SimulNaiveBayesStepFragment = new SimulNaiveBayesStepFragment();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.frame_container, SimulNaiveBayesStepFragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.addToBackStack(SimulNaiveBayesStepFragment.getTag());
				ft.commit();
			}
		});

		return rootView;
	}

	public void onBackPressed() {

	}

}
