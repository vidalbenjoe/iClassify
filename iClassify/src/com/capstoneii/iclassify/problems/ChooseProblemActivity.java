package com.capstoneii.iclassify.problems;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.problems.patient.MainFragmentPatient;
import com.capstoneii.iclassify.simulation.desiciontree.SimulStartActivity;
@SuppressLint("NewApi")
public class ChooseProblemActivity extends Fragment {

    Button catsheepBT,patientBT,simpsonBT;
    TextView headerText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
        
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.choose_problem_layout, container, false);  
	       
		 headerText =  (TextView) rootView.findViewById(R.id.headerText);
		 headerText.setText("Please choose problem you want to solve by the algorithm");
		 
		 catsheepBT = (Button) rootView.findViewById(R.id.catsheepBT);
		 patientBT = (Button) rootView.findViewById(R.id.patientBT);
		 simpsonBT = (Button) rootView.findViewById(R.id.simpsonBT);
		 
		 catsheepBT.setText("Cat and Sheep");
		 patientBT.setText("Patient Scenario");
		 simpsonBT.setText("Simpsons");
		 catsheepBT.setVisibility(View.INVISIBLE);
		 catsheepBT.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	Fragment chooseAlgocatSheep = new chooseAlgocatSheep();
 	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
 	            	ft.replace(R.id.frame_container, chooseAlgocatSheep);
 	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
 	            	ft.commit();
	            }
	        });
		 
		 patientBT.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	Fragment chooseAlgoPatient = new chooseAlgoPatient();
	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	            	ft.replace(R.id.frame_container, chooseAlgoPatient);
	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	            	ft.commit();
	            }
	        });
		 
		 simpsonBT.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	Fragment chooseAlgoSimpson = new chooseAlgoSimpson();
	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	            	ft.replace(R.id.frame_container, chooseAlgoSimpson);
	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	            	ft.commit();
	            }
	        });
		 
		 return rootView;
	    }
        
    }

class chooseAlgocatSheep extends Fragment {

    Button catsheepBT,patientBT,simpsonBT,chooseTopic;
    TextView headerText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
        
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.choose_problem_layout, container, false);  
	 
		 headerText =  (TextView) rootView.findViewById(R.id.headerText);
		 headerText.setText("Choose algorithm you want to simulate");
		 
		 catsheepBT = (Button) rootView.findViewById(R.id.catsheepBT);
		 patientBT = (Button) rootView.findViewById(R.id.patientBT);
		 simpsonBT = (Button) rootView.findViewById(R.id.simpsonBT);
		 
		 catsheepBT.setText("Decision Tree");
		 patientBT.setText("K -  Nearest");
		 simpsonBT.setText("Naive Bayesian");
		 
		 chooseTopic = (Button) rootView.findViewById(R.id.chooseTopic);
		 chooseTopic.setVisibility(View.VISIBLE);
		 
		 chooseTopic.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	Fragment ChooseProblemActivity = new ChooseProblemActivity();
	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	            	ft.replace(R.id.frame_container, ChooseProblemActivity);
	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	            	ft.commit();
	            }
	        });
		 
		 catsheepBT.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	Fragment SimulStartActivity = new SimulStartActivity();
	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	            	ft.replace(R.id.frame_container, SimulStartActivity);
	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	            	ft.commit();
	            }
	        });
		 
		 return rootView;
	    }
    }


class chooseAlgoPatient extends Fragment {

    Button catsheepBT,patientBT,simpsonBT,chooseTopic;
    TextView headerText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
        
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.choose_problem_layout, container, false);  
		 headerText =  (TextView) rootView.findViewById(R.id.headerText);
		 headerText.setText("Choose algorithm you want to simulate");
		 
		 catsheepBT = (Button) rootView.findViewById(R.id.catsheepBT);
		 patientBT = (Button) rootView.findViewById(R.id.patientBT);
		 simpsonBT = (Button) rootView.findViewById(R.id.simpsonBT);
		 
		 catsheepBT.setText("Decision Tree");
		 patientBT.setText("K -  Nearest");
		 simpsonBT.setText("Naive Bayesian");
		 
		 patientBT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		 
		 simpsonBT.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Fragment MainFragmentPatient = new MainFragmentPatient();
	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	            	ft.replace(R.id.frame_container, MainFragmentPatient);
	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	            	ft.commit();
					
				}
			});
		 
		 chooseTopic = (Button) rootView.findViewById(R.id.chooseTopic);
		 chooseTopic.setVisibility(View.VISIBLE);
		 
		 chooseTopic.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	Fragment ChooseProblemActivity = new ChooseProblemActivity();
	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	            	ft.replace(R.id.frame_container, ChooseProblemActivity);
	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	            	ft.commit();
	            }
	        });
		 
		 return rootView;
	    }
        
    }

class chooseAlgoSimpson extends Fragment {

    Button simpsonID3BT,simpsonKNNBT,simpsonNBBT,chooseTopic;
    TextView headerText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
        
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.choose_problem_layout, container, false);  
		 headerText =  (TextView) rootView.findViewById(R.id.headerText);
		 headerText.setText("Choose algorithm you want to simulate");
		 
		 simpsonID3BT = (Button) rootView.findViewById(R.id.catsheepBT);
		 simpsonKNNBT = (Button) rootView.findViewById(R.id.patientBT);
		 simpsonNBBT = (Button) rootView.findViewById(R.id.simpsonBT);
		 
		 simpsonID3BT.setText("Decision Tree");
		 simpsonKNNBT.setText("K -  Nearest");
		 simpsonNBBT.setText("Naive Bayesian");
		 
		 chooseTopic = (Button) rootView.findViewById(R.id.chooseTopic);
		 chooseTopic.setVisibility(View.VISIBLE);
		 
		 chooseTopic.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	Fragment ChooseProblemActivity = new ChooseProblemActivity();
	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	            	ft.replace(R.id.frame_container, ChooseProblemActivity);
	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	            	ft.commit();
	            }
	        });
		 
		 simpsonID3BT.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	Fragment SimpsonsDecisionTreeFragment = new SimpsonsDecisionTreeFragment();
	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	            	ft.replace(R.id.frame_container, SimpsonsDecisionTreeFragment);
	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	            	ft.commit();
	            }
	        });
		 return rootView;
	    }
        public void onBackPressed(){
        	
        }
        
    }
