package com.capstoneii.iclassify.problems.patient;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class PatientProbabilityComputationFragment extends Fragment {
	TypewriterTextView patientprobatext;
	ImageView computationprobability,patientnextbt;
	Animation animation;
	int counter = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.patient_probability_layout,
				container, false);
		animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.unzoom_in);
		 
		final TypewriterTextView patientprobatext = (TypewriterTextView) rootView.findViewById(R.id.patientprobatext);
		patientprobatext.setTypewriterText(getString(R.string.patientcomputestatement1));
		patientprobatext.setVisibility(View.VISIBLE);
		
		computationprobability = (ImageView) rootView.findViewById(R.id.computationprobability);
		computationprobability.setImageResource(R.drawable.probablookup1);
		computationprobability.startAnimation(animation);
		
		patientnextbt = (ImageView) rootView.findViewById(R.id.patientnextbt);
		patientnextbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				if (InputFragmentView == patientnextbt) {
					counter++;
				}
				switch(counter){
				case 1:
					computationprobability.setImageResource(R.drawable.probablookup2);
					computationprobability.startAnimation(animation);
					patientprobatext.setTypewriterText(getString(R.string.patientcomputestatement2));
					
					break;
				case 2:
					break;
					
				case 3:
					
					break;
					
					
				default:
				break;
				}
				
			}
			
		});
		
		return rootView;
	}
}
