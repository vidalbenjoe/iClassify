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
public class SimulMainKnnActivity extends Fragment implements android.view.animation.Animation.AnimationListener{
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
		 	
		 
		 left = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	                R.anim.left);
	        right = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	                R.anim.right);
	        leftout = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	                R.anim.leftout);
	        rightout = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	                R.anim.rightout);
	        animFadein = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	                R.anim.fade_in);
	        animFadeout = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	                R.anim.fade_out);
		 
	        left.setAnimationListener(this);
		    right.setAnimationListener(this);
		    
		    final TextView simulprocess1 = (TextView)rootView.findViewById(R.id.simulprocess1);
		    final TextView simulprocess2 = (TextView)rootView.findViewById(R.id.simulprocess2);
		    final TextView simulprocess3 = (TextView)rootView.findViewById(R.id.simulprocess3);
		    final TextView simulprocess4 = (TextView)rootView.findViewById(R.id.simulprocess4);
		    final TextView simulprocess5 = (TextView)rootView.findViewById(R.id.simulprocess5);
		        
	
		    
		 	secretTextView = (SecretTextView)rootView.findViewById(R.id.knnsecrettext);
	        secretTextView.setmDuration(1900);
	        secretTextView.setIsVisible(false);
	        secretTextView.toggle();
	        
	        secretTextViewDesc = (SecretTextView)rootView.findViewById(R.id.knnsecretdesc);
	        secretTextViewDesc.setmDuration(3000);
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
	            		
	            		secretTextView.setmDuration(2000);
		      	        secretTextView.setIsVisible(false);
		      	        secretTextView.toggle();
		      	        
		      	        secretTextViewDesc.setmDuration(3200);
		      	        secretTextViewDesc.setIsVisible(false);
		  	        	secretTextViewDesc.toggle();
		  	        	
		  	        	simulprocess1.setVisibility(View.VISIBLE);
		  	        	simulprocess2.setVisibility(View.VISIBLE);
		  	        	simulprocess3.setVisibility(View.VISIBLE);
		  	        	simulprocess4.setVisibility(View.VISIBLE);
		  	        	simulprocess5.setVisibility(View.VISIBLE);
		  	        	
		  	        	simulprocess1.startAnimation(left);
		  	        	simulprocess2.startAnimation(right);
		  	        	simulprocess3.startAnimation(left);
		  	        	simulprocess4.startAnimation(right);
		  	        	simulprocess5.startAnimation(left);
		  	        	
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
        
        public void in(){
        	simulprocess1.startAnimation(left);
        	simulprocess2.startAnimation(right);
        	simulprocess3.startAnimation(left);
        	simulprocess4.startAnimation(right);
        	simulprocess5.startAnimation(left);
	    }
		
		public void out(){
			simulprocess1.startAnimation(leftout);
			simulprocess2.startAnimation(rightout);
			simulprocess3.startAnimation(leftout);
			simulprocess4.startAnimation(rightout);
			simulprocess5.startAnimation(leftout);
			
			simulprocess1.postDelayed(new Runnable() {
	            @Override
	            public void run() {
	                in();
	            }
	        }, 1000);
	    }
		@Override
		public void onAnimationEnd(Animation animation) {
			// Take any action after completing the animation

			// check for zoom in animation
			if (animation == animSideDown) {			
			}

		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		

		}

		@Override
		public void onAnimationStart(Animation animation) {
			
		}
      
    }

