package com.capstoneii.iclassify.problems.patientdectree;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.SecretTextView;
import com.capstoneii.iclassify.library.TypewriterTextView;
import com.capstoneii.iclassify.problems.ChooseProblemActivity;

public class PAtientProblemDecTreeComputation extends Fragment {
	SecretTextView textcompute2;
	ImageView treepatient, patientnextbt;
	int counter = 0;
	Animation animZoom;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.patient_dectree_compute,
				container, false);
		animZoom = AnimationUtils
				.loadAnimation(getActivity(), R.anim.unzoom_in);
		final com.capstoneii.iclassify.library.JustifyTextView textcomputehaha = (com.capstoneii.iclassify.library.JustifyTextView) rootView.findViewById(R.id.textcomputehaha);
		textcomputehaha.setText(R.string.patientprobid31);
		
		textcompute2 = (SecretTextView) rootView.findViewById(R.id.textcompute2);
		textcomputehaha.setVisibility(View.VISIBLE);

		treepatient = (ImageView) rootView.findViewById(R.id.treepatient);
		treepatient.setVisibility(View.INVISIBLE);

		patientnextbt = (ImageView) rootView.findViewById(R.id.patientnextbt);
		patientnextbt.setImageResource(R.drawable.simulnextbt);
		patientnextbt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v == patientnextbt) {
					counter++;
				}

				switch (counter) {
				case 1:
					textcomputehaha.setVisibility(View.GONE);
					textcompute2.setVisibility(View.VISIBLE);
					textcompute2.setText(R.string.patientprobid32);
					textcompute2.setmDuration(2200);
					textcompute2.setIsVisible(false);
					textcompute2.toggle();
					break;

				case 2:
					textcomputehaha.setVisibility(View.VISIBLE);
					textcomputehaha
							.setText(R.string.patientprobid33);
					textcompute2.setVisibility(View.GONE);
					break;
				case 3:
					textcomputehaha.setVisibility(View.VISIBLE);
					textcomputehaha
							.setText(R.string.patientprobid34);
					break;
				case 4:
					textcomputehaha.setVisibility(View.GONE);
					textcompute2.setVisibility(View.VISIBLE);
					textcompute2.setText(R.string.patientprobid35);
					textcompute2.setmDuration(1200);
					textcompute2.setIsVisible(false);
					textcompute2.toggle();
					break;

				case 5:
					textcomputehaha.setVisibility(View.VISIBLE);
					textcompute2.setVisibility(View.GONE);
					
					textcomputehaha
							.setText(R.string.thismeans);

					treepatient.setVisibility(View.VISIBLE);
					treepatient.setImageResource(R.drawable.patienttree);
					treepatient.startAnimation(animZoom);

					patientnextbt.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									final Dialog dialog = new Dialog(
											getActivity());
									dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
									dialog.setContentView(R.layout.custom_dialog_text);
									dialog.setCancelable(true);
									dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

									final TypewriterTextView customtextindialog = (TypewriterTextView) dialog.findViewById(R.id.customtextindialog);
									customtextindialog.setText(R.string.whydecisiontree);
									customtextindialog.setMovementMethod(new ScrollingMovementMethod());

									ImageView cadbtnNext = (ImageView) dialog.findViewById(R.id.cadbtnNext);
									cadbtnNext.setImageResource(R.drawable.backtomainmenu);
									cadbtnNext.setOnClickListener(new View.OnClickListener() {
												@Override
												public void onClick(
														View InputFragmentView) {
													// next
													Fragment ChooseProblemActivity = new ChooseProblemActivity();
													FragmentTransaction ft = getFragmentManager()
															.beginTransaction();
													ft.replace(
															R.id.frame_container,
															ChooseProblemActivity);
													ft.commit();
													dialog.dismiss();
												}
											});
									dialog.show();

								}
							});

					break;

				default:
					break;
				}

			}
		});

		return rootView;
	}
}
