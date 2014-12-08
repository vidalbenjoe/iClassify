package com.capstoneii.iclassify.problems.patientdectree;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.JustifyTextView;
import com.capstoneii.iclassify.library.SecretTextView;

public class PatientProblemsDecisionTreeFragment extends Fragment {
	SecretTextView textHead;
	JustifyTextView textFlu;
	TextView textFluNo, ClickmeText;
	EditText textFluNoTextBox, textFluTextBox;
	ImageView adamImage, imageView_close, tableflu,table_img;
	Button lookupbt;
	int nextButton = 0;
	Animation clock, animation;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.patient_decision_tree_layout,
				container, false);
		animation = AnimationUtils.loadAnimation(getActivity()
				.getApplicationContext(), R.anim.unzoom_in);
		
		lookupbt = (Button) rootView.findViewById(R.id.lookupbt);
		textFluNoTextBox = (EditText) rootView
				.findViewById(R.id.textFluNoTextBox);
		textFluTextBox = (EditText) rootView.findViewById(R.id.textFluTextBox);

		textHead = (SecretTextView) rootView.findViewById(R.id.textHead);

		textFlu = (JustifyTextView) rootView.findViewById(R.id.textFlu);

		textFluNo = (TextView) rootView.findViewById(R.id.textFluNo);
		textHead.setVisibility(View.VISIBLE);
		textFlu.setVisibility(View.VISIBLE);
		textHead.setText("Do I believe that a patient with the following symptoms has a flu?");

		textHead.setmDuration(1800);
		textHead.setIsVisible(false);
		textHead.toggle();

		textFlu.setText("Flu?");
		

		tableflu = (ImageView) rootView.findViewById(R.id.tableflu);
		tableflu.setImageResource(R.drawable.patientguesslookuptabe);
		adamImage = (ImageView) rootView.findViewById(R.id.adamImage);

		adamImage.startAnimation(animation);
		ClickmeText = (TextView) rootView.findViewById(R.id.ClickmeText);
		ClickmeText.setText("Click Me");
		ClickmeText.startAnimation(animation);
		adamImage.setVisibility(View.VISIBLE);
		adamImage.setBackgroundResource(R.drawable.adamdoc);
		adamImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				if (InputFragmentView == adamImage) {
					nextButton++;
				}

				switch (nextButton) {
				case 1:
					textHead.setText("Lookup Table");
					textFlu.setText(R.string.lookuptextpatient);
					
					textHead.setVisibility(View.VISIBLE);
					adamImage.setVisibility(View.INVISIBLE);
					tableflu.setVisibility(View.GONE);
					lookupbt.setVisibility(View.VISIBLE);
					lookupbt.setBackgroundResource(R.drawable.lookupgreen);
					lookupbt.startAnimation(animation);
					ClickmeText.setVisibility(View.GONE);
					lookupbt.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View InputFragmentView) {
							// display looup table with next button in dialog
							// box
							final Dialog dialog = new Dialog(getActivity());
							dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
							dialog.setContentView(R.layout.custom_dialog_image);
							dialog.getWindow().setBackgroundDrawable(new ColorDrawable
									(android.graphics.Color.TRANSPARENT));

							imageView_close = (ImageView) dialog.findViewById(R.id.imageView_close);
							imageView_close.setImageResource(R.drawable.patientlookuptablestart);
							table_img = (ImageView) dialog.findViewById(R.id.table_img);
							table_img.setVisibility(View.GONE);
							Button cadbtnNext = (Button) dialog.findViewById(R.id.cadbtnNext);
							cadbtnNext.setBackgroundResource(R.drawable.simulnextbt);
							cadbtnNext.setOnClickListener(new View.OnClickListener() {
										@Override
										public void onClick(
												View InputFragmentView) {
											dialog.dismiss();
											textHead.setText("TESTING");
											textFlu.setText(R.string.patienttesting);
											textFlu.startAnimation(animation);
											tableflu.setVisibility(View.VISIBLE);

											/*
											 * set image computation for
											 * adamImage X = (Chills=Yes, Runny
											 * Nose=No, Headache=Mild, Fever=No)
											 */

											lookupbt.setVisibility(View.INVISIBLE);
											adamImage.setVisibility(View.VISIBLE);
											adamImage.setBackgroundResource(R.drawable.nextbtred);
											adamImage.setOnClickListener(new View.OnClickListener() {
														@Override
														public void onClick(
																View InputFragmentView) {
															if (InputFragmentView == adamImage) {
																case2();
																nextButton = 1;
																nextButton++;
															}
															case2();
														}
													});
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

	public void case2() {

		switch (nextButton) {
		case 2:
			Fragment PAtientProblemDecTreeComputation = new PAtientProblemDecTreeComputation();
			FragmentTransaction ft = getFragmentManager()
					.beginTransaction();
			ft.replace(R.id.frame_container,
					PAtientProblemDecTreeComputation);
			ft.commit();
			break;

		default:
			break;
		}
	}
}
