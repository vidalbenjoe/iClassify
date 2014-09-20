package com.capstoneii.iclassify.simulation.knn;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.FragmentTransaction;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.SecretTextView;
@SuppressLint("NewApi")
public class SimulMainKnnActivity extends Fragment {
    SecretTextView secretTextView,secretTextViewDesc;
    Animation left, right, leftout, rightout,animFadein,animFadeout;
    TextView simulprocess1,simulprocess2,simulprocess3,simulprocess4,simulprocess5;
	Animation animSideDown;
	int counter = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
        //http://people.revoledu.com/kardi/tutorial/KNN/KNN_Numerical-example.html
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.knnsimul_firstlayout, container, false);  
		 	
		 final ImageView stepknnimg = (ImageView) rootView.findViewById(R.id.stepknnimg);
		 	secretTextView = (SecretTextView)rootView.findViewById(R.id.knnsecrettext);
	        secretTextView.setmDuration(1800);
	        secretTextView.setIsVisible(false);
	        secretTextView.toggle();
	        
	        secretTextViewDesc = (SecretTextView)rootView.findViewById(R.id.knnsecretdesc);
	        secretTextViewDesc.setmDuration(3200);
	        secretTextViewDesc.setIsVisible(false);
	        secretTextViewDesc.toggle();
	       
	        final ImageView knnnextbt = (ImageView) rootView.findViewById(R.id.knnnextbt);
	        knnnextbt.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	if(InputFragmentView == knnnextbt){
	            		counter++;	
	            	}
	            	
	            	switch(counter){
	            	
	            	case 0:
	            		
	            		break;
	            	case 1:
	            		
	            		Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.unzoom_in);
	           		 	stepknnimg.startAnimation(animation);
	            		stepknnimg.setVisibility(View.VISIBLE);
	            		secretTextView.setmDuration(2000);
		      	        secretTextView.setIsVisible(false);
		      	        secretTextView.toggle();
		      	        
		      	        secretTextViewDesc.setmDuration(3200);
		      	        secretTextViewDesc.setIsVisible(false);
		  	        	secretTextViewDesc.toggle();
		  	        	
		  	        	secretTextView.setVisibility(View.INVISIBLE);
		  	        	secretTextViewDesc.setVisibility(View.INVISIBLE);
	            		break;
	            		
	            	case 2:
	            		Fragment SimulMainKnnDataset = new SimulMainKnnDataset();
	 	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	 	            	ft.replace(R.id.frame_container, SimulMainKnnDataset);
	 	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		            	ft.addToBackStack(SimulMainKnnDataset.getTag());
	 	            	ft.commit();
	            		break;
	            	default:
	            		break;
	            	}
	            	
	            }
	        });
	        
		 return rootView;
	    }
        
        public void onBackPressed(){
        	
        }
		
    }

