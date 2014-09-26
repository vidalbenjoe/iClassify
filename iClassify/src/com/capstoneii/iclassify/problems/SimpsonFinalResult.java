package com.capstoneii.iclassify.problems;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.SecretTextView;
import com.capstoneii.iclassify.library.TypewriterTextView;

//Based on comic attributes
//comic is a male

//

public class SimpsonFinalResult extends Fragment {
	SecretTextView secretTextView;
	ImageView simpsoneTable,comictable;
	Button backtomain;
	Animation animationZoom;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.simpson_final_result_layout,
				container, false);
		animationZoom = AnimationUtils.loadAnimation(getActivity(),
				R.anim.unzoom_in);
		comictable = (ImageView) rootView.findViewById(R.id.comictable);
		comictable.startAnimation(animationZoom);
		
		final TypewriterTextView simpsonAnimatedTextView = (TypewriterTextView) rootView
				.findViewById(R.id.simpsonAnimatedTextView);
		simpsonAnimatedTextView
				.setTypewriterText(getString(R.string.simpsonresult));

		simpsoneTable = (ImageView) rootView.findViewById(R.id.simpsoneTable);
		simpsoneTable.setVisibility(View.VISIBLE);
		backtomain = (Button) rootView.findViewById(R.id.backtomain);
		backtomain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Fragment ChooseProblemActivity = new ChooseProblemActivity();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.frame_container, ChooseProblemActivity);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.addToBackStack(ChooseProblemActivity.getTag());
				ft.commit();
			}
		});
		return rootView;
	}

	public void onBackPressed() {

	}
}
