package com.capstoneii.iclassify.simulation.desiciontree;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.capstoneii.iclassify.R;
import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.inqbarna.tablefixheaders.adapters.MatrixTableAdapter;

@SuppressLint("NewApi")
public class SimulDatasetActivity extends Fragment {
	private Button calculate;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.table, container, false);

		calculate = (Button) rootView.findViewById(R.id.calculatebt);

		calculate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment SimulCalculateActivity = new SimulCalculateActivity();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.frame_container, SimulCalculateActivity);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.addToBackStack(SimulCalculateActivity.getTag());
				ft.commit();
			}

		});

		TableFixHeaders tableFixHeaders = (TableFixHeaders) rootView
				.findViewById(R.id.table);
		MatrixTableAdapter<String> matrixTableAdapter = new MatrixTableAdapter<String>(
				getActivity(), new String[][] {
						{ "Outlook", "Temperature", "Humidity", "Wind",
								"Play Baseball?" },
						{ "Sunny", "Hot", "High", "Weak", "No" },
						{ "Sunny", "Hot", "High", "Strong", "No" },
						{ "Overcast", "Hot", "High", "Weak", "Yes" },
						{ "Rain", "Mild", "High", "Weak", "Yes" },
						{ "Rain", "Cool", "Normal", "Weak", "Yes" },
						{ "Rain", "Cool", "Normal", "Strong", "No" },

						{ "Overcast", "Cool", "Normal", "Strong", "Yes" },
						{ "Sunny", "Mild", "High", "Weak", "No" },
						{ "Sunny", "Cool", "Normal", "Weak", "No" },

						{ "Rain", "Mild", "Normal", "Weak", "No" },

						{ "Sunny", "Mild", "Normal", "Strong", "No" },

						{ "Overcast", "Mild", "High", "Strong", "No" },

						{ "Overcast", "Hot", "Normal", "Weak", "Yes" },

						{ "Rain", "Mild", "High", "Strong", "No" },

				});
		tableFixHeaders.setAdapter(matrixTableAdapter);
		return rootView;
	}

	public void onBackPressed() {

		// See bug:
		// http://stackoverflow.com/questions/13418436/android-4-2-back-stack-behaviour-with-nested-fragments/14030872#14030872
		// If the fragment exists and has some back-stack entry
		FragmentManager fm = getFragmentManager();
		Fragment currentFragment = fm.findFragmentById(R.id.content_frame);
		if (currentFragment != null
				&& currentFragment.getChildFragmentManager()
						.getBackStackEntryCount() > 0) {
			// Get the fragment fragment manager - and pop the backstack
			currentFragment.getChildFragmentManager().popBackStack();
		}

	}

}
