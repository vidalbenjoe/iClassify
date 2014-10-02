package com.capstoneii.iclassify.problems;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.SecretTextView;
import com.capstoneii.iclassify.library.TypewriterTextView;

//Based on comic attributes
//comic is a male

//

public class SimpsonFinalResult extends Fragment {
	SecretTextView secretTextView;
	ImageView simpsoneTable, comictable;
	ImageView backtomain;
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
		backtomain = (ImageView) rootView.findViewById(R.id.backtomain);
		backtomain.setImageResource(R.drawable.simulnextbt);
		backtomain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {

				final Dialog dialog = new Dialog(getActivity());
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog_text);
				dialog.setCancelable(false);
				dialog.getWindow().setBackgroundDrawable(
						new ColorDrawable(android.graphics.Color.TRANSPARENT));

				final TypewriterTextView customtextindialog = (TypewriterTextView) dialog
						.findViewById(R.id.customtextindialog);
				customtextindialog.setText(R.string.whydecisiontree);
				customtextindialog.setMovementMethod(new ScrollingMovementMethod());

				ImageView cadbtnNext = (ImageView) dialog.findViewById(R.id.cadbtnNext);
				cadbtnNext.setImageResource(R.drawable.backtomainmenu);

				cadbtnNext.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View InputFragmentView) {
						// next
						Fragment ChooseProblemActivity = new ChooseProblemActivity();
						FragmentTransaction ft = getFragmentManager().beginTransaction();
						ft.replace(R.id.frame_container,ChooseProblemActivity);
						ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
						ft.addToBackStack(ChooseProblemActivity.getTag());
						ft.commit();
						dialog.dismiss();
					}
				});
				dialog.show();

			}
		});
		return rootView;
	}

	public void onBackPressed() {

	}
}
