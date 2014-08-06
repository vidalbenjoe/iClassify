package com.capstoneii.iclassify.simulation.knn;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class SimulMainKnnDataset extends Fragment {
	Dialog dialog;
    ImageView factoryimg, tissueimg,knnnextbt;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	    
	        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		    {
			 View rootView = inflater .inflate(R.layout.knnsimul_secondlayout, container, false);  
		
			 //add a pop up displayimng KNN dataset
			 //animate the text, after a few seconds ---opoup the images then loop
			 //edit the images 
			 //dont show pop up automatically
			 final TypewriterTextView knnsecondtext = (TypewriterTextView)rootView.findViewById(R.id.knnsecondtext);
			 knnsecondtext.setTypewriterText(getString(R.string.knnsimulexample1));
			 knnnextbt = (ImageView) rootView.findViewById(R.id.knnnextbt);
			 knnnextbt.setVisibility(View.INVISIBLE);
			 
			
			 new CountDownTimer(4200, 1000) {

			     public void onTick(long millisUntilFinished) {
			      
			     }
			     public void onFinish() {
			    	 
			    	 dialog = new Dialog(getActivity());
		         	 dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		             dialog.setContentView(R.layout.naiveweatherpopup);
		             dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		             TextView strongtext = (TextView) dialog.findViewById(R.id.occurence);
		             strongtext.setText("Acid durability and Strenght");
		             final ImageView naivedataimg = (ImageView) dialog.findViewById(R.id.naivedataimg);
					 	
		             naivedataimg.setImageResource(R.drawable.knnaciddata);
		            
		             dialog.show();
		             
		             new CountDownTimer(4800, 1000) {

					     public void onTick(long millisUntilFinished) {
					      
					     }
					     public void onFinish() {
				             knnnextbt.setVisibility(View.VISIBLE);
					     }
					  }.start();
			     }
			  }.start();
			  
			  knnnextbt.setOnClickListener(new View.OnClickListener()
		        {
		            @Override
		            public void onClick(View InputFragmentView)
		            {
		            	Fragment SimulMainKnnFactoryTissue = new SimulMainKnnFactoryTissue();
	 	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	 	            	ft.replace(R.id.frame_container, SimulMainKnnFactoryTissue);
	 	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		            	ft.addToBackStack(SimulMainKnnFactoryTissue.getTag());
	 	            	ft.commit();
	 	            	dialog.dismiss();
	 	            	
		            }
		            });
			 

			 return rootView;
			 
		    }
	        public boolean onTouchEvent(MotionEvent event)  
	        {  
	               if(event.getAction() == MotionEvent.ACTION_OUTSIDE){  
	               //hadle canceled dialog
	               }  
	               return false;  
	        }  
	    }

