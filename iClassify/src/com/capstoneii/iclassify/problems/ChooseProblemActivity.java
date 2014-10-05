package com.capstoneii.iclassify.problems;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.problems.patient.MainFragmentPatient;
import com.capstoneii.iclassify.problems.patientdectree.PatientProblemsDecisionTreeFragment;
import com.capstoneii.iclassify.problems.simpsonsknn.SimpsonTableFragmentKNN;

@SuppressLint("NewApi")
public class ChooseProblemActivity extends Fragment {

	Button catsheepBT, patientBT, simpsonBT;
	TextView headerText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.choose_problem_layout,
				container, false);

		headerText = (TextView) rootView.findViewById(R.id.headerText);
		headerText.setText("Please choose a scenario");

		catsheepBT = (Button) rootView.findViewById(R.id.catsheepBT);
		patientBT = (Button) rootView.findViewById(R.id.patientBT);
		simpsonBT = (Button) rootView.findViewById(R.id.simpsonBT);

		catsheepBT.setText("Cat and Sheep");
		patientBT.setText("Patient Scenario");
		simpsonBT.setText("Simpsons");
		catsheepBT.setVisibility(View.INVISIBLE);

		patientBT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Fragment chooseAlgoPatient = new chooseAlgoPatient();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.frame_container, chooseAlgoPatient);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});

		simpsonBT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Fragment chooseAlgoSimpson = new chooseAlgoSimpson();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.frame_container, chooseAlgoSimpson);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});

		return rootView;
	}
}

class chooseAlgoPatient extends Fragment {

	Button catsheepBT, patientBT, simpsonBT, chooseTopic;
	TextView problemDesc, headerText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.choose_problem_layout,
				container, false);
		problemDesc = (TextView) rootView.findViewById(R.id.problemDesc);
		problemDesc.setText(R.string.problempatient);

		headerText = (TextView) rootView.findViewById(R.id.headerText);
		headerText.setText(R.string.whatalgoyouwant);

		catsheepBT = (Button) rootView.findViewById(R.id.catsheepBT);
		patientBT = (Button) rootView.findViewById(R.id.patientBT);
		simpsonBT = (Button) rootView.findViewById(R.id.simpsonBT);

		catsheepBT.setText("");
		catsheepBT.setVisibility(View.GONE);
		patientBT.setText("Decision Tree");
		simpsonBT.setText("Naive Bayesian");

		patientBT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment PatientProblemsDecisionTreeFragment = new PatientProblemsDecisionTreeFragment();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.frame_container,
						PatientProblemsDecisionTreeFragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();

			}
		});
		simpsonBT.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment MainFragmentPatient = new MainFragmentPatient();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.frame_container, MainFragmentPatient);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});
		chooseTopic = (Button) rootView.findViewById(R.id.chooseTopic);
		chooseTopic.setVisibility(View.VISIBLE);

		chooseTopic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Fragment ChooseProblemActivity = new ChooseProblemActivity();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.frame_container, ChooseProblemActivity);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});
		return rootView;
	}
}

class chooseAlgoSimpson extends Fragment {

	Button simpsonID3BT, simpsonKNNBT, simpsonNBBT, chooseTopic;
	TextView problemDesc, headerText;
	ImageView problemimage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.choose_problem_layout,
				container, false);
		problemDesc = (TextView) rootView.findViewById(R.id.problemDesc);
		problemDesc.setText(R.string.problemsimpson);

		problemimage = (ImageView) rootView.findViewById(R.id.problemimage);
		problemimage.setBackgroundResource(R.drawable.comicsad);

		headerText = (TextView) rootView.findViewById(R.id.headerText);
		headerText.setText(R.string.whatalgoyouwant);

		simpsonID3BT = (Button) rootView.findViewById(R.id.catsheepBT);
		simpsonKNNBT = (Button) rootView.findViewById(R.id.patientBT);
		simpsonNBBT = (Button) rootView.findViewById(R.id.simpsonBT);
		simpsonNBBT.setVisibility(View.GONE);
		simpsonID3BT.setText("Decision Tree");
		simpsonKNNBT.setText("K -  Nearest");
		simpsonNBBT.setText("Naive Bayesian");

		chooseTopic = (Button) rootView.findViewById(R.id.chooseTopic);
		chooseTopic.setVisibility(View.VISIBLE);

		chooseTopic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Fragment ChooseProblemActivity = new ChooseProblemActivity();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.frame_container, ChooseProblemActivity);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});

		simpsonID3BT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Fragment SimpsonTableFragment = new SimpsonTableFragment();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.frame_container, SimpsonTableFragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();

			}
		});

		simpsonKNNBT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Fragment SimpsonTableFragmentKNN = new SimpsonTableFragmentKNN();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.frame_container, SimpsonTableFragmentKNN);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();

			}
		});

		return rootView;
	}

	public void onBackPressed() {

	}

}
