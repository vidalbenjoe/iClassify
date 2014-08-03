package com.capstoneii.iclassify.simulation.knn;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class SimulMainKnnFactoryTissue extends Fragment {
	  ImageView factoryimg, tissueimg;
	  
	  public void onCreate(Bundle savedInstanceState){
		  super.onCreate(savedInstanceState);
	  }
	  
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.knnsimul_thirdlayout, container, false);  
		 final ImageView factoryimg = (ImageView) rootView.findViewById(R.id.factoryimg);
		 final ImageView tissueimg = (ImageView) rootView.findViewById(R.id.tissueimg);
		 factoryimg.setVisibility(View.INVISIBLE);
		 tissueimg.setVisibility(View.INVISIBLE);
		 
		 final TypewriterTextView knnthirdext = (TypewriterTextView)rootView.findViewById(R.id.knnthirdext);
    	 knnthirdext.setTypewriterText(getString(R.string.knnsimulexample2));
    	 
    	 new CountDownTimer(2000, 1000) {

		     public void onTick(long millisUntilFinished) {
		     }
		     public void onFinish() {
		    	 Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.unzoom_in);
		    	 factoryimg.startAnimation(animation);
		    	 factoryimg.setVisibility(View.VISIBLE);
		    	 
		    	 new CountDownTimer(2000, 1000) {

				     public void onTick(long millisUntilFinished) {
				      
				     }
				     public void onFinish() {
				    Animation animation2 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.unzoom_in);
					tissueimg.startAnimation(animation2);
					tissueimg.setVisibility(View.VISIBLE);
					
					//another timer
					//after ontouch..move to next activity
					
			         
			         new CountDownTimer(2800, 1000) {

					     public void onTick(long millisUntilFinished) {
					      
					     }
					     public void onFinish() {
					
					    	 new CountDownTimer(3000, 1000) {

							     public void onTick(long millisUntilFinished) {
							      
							     }
							     public void onFinish() {
					 	            	 final Dialog dialog = new Dialog(getActivity());
								     	 dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
								         dialog.setContentView(R.layout.transparent_popuptext);
								         dialog.setCancelable(true);
								         dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
								         final ImageView transpaimage = (ImageView) dialog.findViewById(R.id.transpaimage2);
								      	
								         transpaimage.setImageResource(R.drawable.canweguestimg);
								         transpaimage.setVisibility(View.VISIBLE);
								         dialog.show();
								         
								         new CountDownTimer(4000, 4000) {

										     public void onTick(long millisUntilFinished) {
										      
										     }
										     public void onFinish() {
										
										    	 Fragment SimulMainKnnStepProcess = new SimulMainKnnStepProcess();
								 	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
								 	            	ft.replace(R.id.frame_container, SimulMainKnnStepProcess);
									            	ft.addToBackStack(SimulMainKnnStepProcess.getTag());
								 	            	ft.commit();
								 	            	dialog.dismiss();
								    	 }
								    	 }.start();
					    	 }
					    	 }.start();
			    	 }
			    	 }.start();
		    	 }
		    	 }.start();
    	 }
    	 }.start();
    	 
		 return rootView;
	    }
}
