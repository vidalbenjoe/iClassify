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
import android.widget.Toast;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.SecretTextView;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class SimpsonMaleClassifier extends Fragment {
	 SecretTextView secretTextView;
	 ImageView malesimpsons;
	 Button weightYes,weightNo,ishairlenght,hairlengthYes,hairlengthNo,hairlengthMale,hairlengthFemale,maleResult,simpsonNextbt;
	 Animation animSideDown,animSideUp,animationZoom,clock;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	        
	        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		    {
			 View rootView = inflater .inflate(R.layout.simpson_male_classifier_layout, container, false);  
			 	
			animationZoom = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_zoom_in);
			animSideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
 					R.anim.slide_down);
			
			animSideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
 					R.anim.slide_down);
     		
			clock = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
 					R.anim.clockwise);
     		
			 final TypewriterTextView simpsonMaleClassText = (TypewriterTextView)rootView.findViewById(R.id.simpsonMaleClassText);
			 simpsonMaleClassText.setTypewriterText(getString(R.string.SimpsonMaleClass));
			 animSideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
					R.anim.slide_down);
			 
			 malesimpsons = (ImageView) rootView.findViewById(R.id.malesimpsons);
			 malesimpsons.startAnimation(animationZoom);
	
			 
			 
			 weightYes = (Button) rootView.findViewById(R.id.weightYes);
			 weightNo = (Button) rootView.findViewById(R.id.weightNo);
			 
			 ishairlenght = (Button) rootView.findViewById(R.id.ishairlenght);
			 
			 hairlengthYes = (Button) rootView.findViewById(R.id.hairlengthYes);
			 hairlengthNo = (Button) rootView.findViewById(R.id.hairlengthNo);
			 
			 hairlengthMale = (Button) rootView.findViewById(R.id.hairlengthMale);
			 hairlengthFemale = (Button) rootView.findViewById(R.id.hairlengthFemale);
			 
			 simpsonNextbt = (Button) rootView.findViewById(R.id.simpsonNextbt);
			 maleResult = (Button) rootView.findViewById(R.id.maleResult);
			
			 weightYes.setOnClickListener(new View.OnClickListener()
		        {
		            @Override
		            public void onClick(View InputFragmentView)
		            {
		            	ishairlenght.setVisibility(View.VISIBLE);
		            	hairlengthYes.setVisibility(View.VISIBLE);
		            	hairlengthNo.setVisibility(View.VISIBLE);
		            	
		            	ishairlenght.startAnimation(animSideDown);
		            	hairlengthYes.startAnimation(animSideDown);
		            	hairlengthNo.startAnimation(animSideDown);
		            }
		        });
			 
			 hairlengthYes.setOnClickListener(new View.OnClickListener()
		        {
		            @Override
		            public void onClick(View InputFragmentView)
		            {
		            	hairlengthMale.setVisibility(View.VISIBLE);
		            	
		            	Toast.makeText(getActivity(),
			                    "classify as Male", Toast.LENGTH_SHORT)
			                    .show();
		            }
		        });
			 hairlengthNo.setOnClickListener(new View.OnClickListener()
		        {
		            @Override
		            public void onClick(View InputFragmentView)
		            {
		            	hairlengthFemale.setVisibility(View.VISIBLE);
		            
		            	Toast.makeText(getActivity(),
			                    "classify as Female", Toast.LENGTH_SHORT)
			                    .show();
		            }
		        });
			 
			 weightNo.setOnClickListener(new View.OnClickListener()
		        {
		            @Override
		            public void onClick(View InputFragmentView)
		            {
		            	maleResult.setVisibility(View.VISIBLE);
		            	Toast.makeText(getActivity(),
			                    "classify as Male", Toast.LENGTH_SHORT)
			                    .show();
		            }
		        });
			 
			 simpsonNextbt.setOnClickListener(new View.OnClickListener()
		        {
		            @Override
		            public void onClick(View InputFragmentView)
		            {
		            	    Fragment ChooseProblemActivity = new ChooseProblemActivity();
		 	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
		 	            	ft.replace(R.id.frame_container, ChooseProblemActivity);
		 	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			            	ft.addToBackStack(ChooseProblemActivity.getTag());
		 	            	ft.commit();
		 	            	
		            }
		        });
			 
			 return rootView;
		    }
	        
	    
}