package com.capstoneii.iclassify.viewpager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.capstoneii.iclassify.ActivityAnimator;
import com.capstoneii.iclassify.R;

import drawer.MainDrawerActivity;

public class FifthFragment extends Fragment {
	public Button startbuttoncircle, viewpagerabout, viewpagerhelp,
			viewpagerexit;
	AlertDialog alertDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fifth_fragment, container, false);

		TextView tv = (TextView) v.findViewById(R.id.tvFragFifth);
		tv.setText(getArguments().getString("msg"));

		viewpagerabout = (Button) v.findViewById(R.id.viewpagerabout);
		viewpagerabout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showAlertDialog(getActivity(), "About", "iClassify is a mobile learning application that serves as a reference for Data Mining, " +
						"specifically about Classification models, for IT students. The m-learning application is expected to provide users with " +
						"discussions about the basic concepts of classification techniques in data mining namely: k-nearest neighbor classifier," +
						" decision tree classifier and Naive Bayes Classifier. The application utilizes the use of multimedia and simulation of " +
						"algorithms to supplement in the learning process. ", false);
			}
		});

		viewpagerhelp = (Button) v.findViewById(R.id.viewpagerhelp);
		viewpagerhelp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showAlertDialog(
						getActivity(),
						"Help",
						"Welcome to iClassify!This application introduces the basic concepts of classification and techniques. \n\n"
								+ "To start the discussion about Classification concepts in data mining, select topics in main menu.\n\n "
								+ "To watch video tutorials about the different topics discussed, click VIDEOS in the main menu. \n\n"
								+ "To see demonstrations and how the different algorithm works, click SIMULATION in the main menu.\n\n"
								+ " For more information, instructions are provided within the Simulation menu.\n\n "
								+ "Further detailed instructions are also provided within each simulation.\n\n "
								+ "To assess your learning, take the assessment quiz.\n\n "
								+ "For more information, instructions are provided within the Quiz menu. \n\n\n",
						false);
			}
		});

		viewpagerexit = (Button) v.findViewById(R.id.viewpagerexit);
		viewpagerexit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});

		startbuttoncircle = (Button) v.findViewById(R.id.startbuttoncircles);

		startbuttoncircle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				// Start main activity
				Intent intent = new Intent(getActivity(),
						MainDrawerActivity.class);
				getActivity().startActivity(intent);
				getActivity().finish();

				try {
					ActivityAnimator anim = new ActivityAnimator();
					anim.getClass().getMethod("Animation", Activity.class)
							.invoke(anim, this);
				} catch (Exception e) {

				}

			}
		});

		return v;
	}

	public static FifthFragment newInstance(String text) {

		FifthFragment f = new FifthFragment();
		Bundle b = new Bundle();
		b.putString("msg", text);
		f.setArguments(b);

		return f;
	}

	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(final DialogInterface dialog, final int which) {
				dismissDialog();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	private void dismissDialog() {
		alertDialog.dismiss();
	}
}