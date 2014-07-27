package com.capstoneii.iclassify.simulation.naivebayes;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;
import com.capstoneii.iclassify.simulation.desiciontree.SimulDatasetActivity;

public class SimulNaiveBayesTableFragment extends Fragment {
	
	int counter =0;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	    
	        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		    {
			 View rootView = inflater .inflate(R.layout.naivesimul_secondlayout, container, false);  
			 	final TypewriterTextView naivetextsecond = (TypewriterTextView)rootView.findViewById(R.id.naivetextsecond);
			 	naivetextsecond.setTypewriterText(getString(R.string.naivebayes1));
			 	final TextView naiveintrotitle = (TextView)rootView.findViewById(R.id.naiveintrotitle);
			 	
			 	final Button viewdatasetnaive = (Button) rootView.findViewById(R.id.viewdatasetnaive);
			 	final Button viewproblemnext = (Button) rootView.findViewById(R.id.naivesecondnextbt);
			 	
			 	final ImageView outlookicon = (ImageView) rootView.findViewById(R.id.outlookicon);
			 	final ImageView temperatureicon = (ImageView) rootView.findViewById(R.id.temperatureicon);
			 	final ImageView humidityicon = (ImageView) rootView.findViewById(R.id.humidityicon);
			 	final ImageView windicon = (ImageView) rootView.findViewById(R.id.windicon);
			 	final ImageView imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
			 	
			 	viewdatasetnaive.setOnClickListener( new View.OnClickListener() {

	    	            @Override
	    	            public void onClick(View v) {
	    	            	//show popup
	    	               
	    	            	final Dialog dialog = new Dialog(getActivity());
	    	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    	                dialog.setContentView(R.layout.naiveweatherpopup);
	    	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	    	                TextView strongtext = (TextView) dialog.findViewById(R.id.occurence);
	    	                strongtext.setText("Data set");
	    	                final ImageView naivedataimg = (ImageView) dialog.findViewById(R.id.naivedataimg);
	    				 	
	    	                naivedataimg.setImageResource(R.drawable.weatherdatasetimg);
	    	                dialog.show();
	    	            }
	                });
			 	
		        viewproblemnext.setOnClickListener(new View.OnClickListener()
		        {
		            @Override
		            public void onClick(View InputFragmentView)
		            {
		            	if(InputFragmentView == viewproblemnext){
		            		counter++;	
		            	}
		            	
		            	switch(counter){
		            	case  0:
		            		
		            		break;
		            		
		            	case  1:
		            		viewdatasetnaive.setVisibility(View.INVISIBLE);
		            		naivetextsecond.setTypewriterText(getString(R.string.naivebayes2));
		            		outlookicon.setVisibility(View.VISIBLE);
		            		temperatureicon.setVisibility(View.VISIBLE);
		            		humidityicon.setVisibility(View.VISIBLE);
		            		windicon.setVisibility(View.VISIBLE);
		            		
		            		outlookicon.setOnClickListener( new View.OnClickListener() {

		 	    	            @Override
		 	    	            public void onClick(View v) {
		 	    	            	//show popup
		 	    	               
		 	    	            	final Dialog dialog = new Dialog(getActivity());
		 	    	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		 	    	                dialog.setContentView(R.layout.naiveweatherpopup);
		 	    	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		 	    	                TextView strongtext = (TextView) dialog.findViewById(R.id.occurence);
		 	    	                strongtext.setText("Outlook");
		 	    	                final ImageView naivedataimg = (ImageView) dialog.findViewById(R.id.naivedataimg);
		 	    				 	
		 	    	                naivedataimg.setImageResource(R.drawable.outlookdataimg);
		 	    	                dialog.show();
		 	    	            }
		 	                });
		            		
		 	           
		            		temperatureicon.setOnClickListener( new View.OnClickListener() {

		 	    	            @Override
		 	    	            public void onClick(View v) {
		 	    	            	//show popup
		 	    	               
		 	    	            	final Dialog dialog = new Dialog(getActivity());
		 	    	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		 	    	                dialog.setContentView(R.layout.naiveweatherpopup);
		 	    	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		 	    	                TextView strongtext = (TextView) dialog.findViewById(R.id.occurence);
		 	    	                strongtext.setText("Temperature");
		 	    	               final ImageView naivedataimg = (ImageView) dialog.findViewById(R.id.naivedataimg);
		 	    				 	
		 	    	                naivedataimg.setImageResource(R.drawable.temperaturedataimg);
		 	    	                dialog.show();
		 	    	            }
		 	                });
		 	           
		            		humidityicon.setOnClickListener( new View.OnClickListener() {

		 	    	            @Override
		 	    	            public void onClick(View v) {
		 	    	            	//show popup
		 	    	               
		 	    	            	final Dialog dialog = new Dialog(getActivity());
		 	    	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		 	    	                dialog.setContentView(R.layout.naiveweatherpopup);
		 	    	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		 	    	                TextView strongtext = (TextView) dialog.findViewById(R.id.occurence);
		 	    	                strongtext.setText("Humidity");
		 	    	               final ImageView naivedataimg = (ImageView) dialog.findViewById(R.id.naivedataimg);
		 	    				 	
		 	    	                naivedataimg.setImageResource(R.drawable.humiditydataimg);
		 	    	                dialog.show();
		 	    	            }
		 	                });
		            		
		            		windicon.setOnClickListener( new View.OnClickListener() {

		 	    	            @Override
		 	    	            public void onClick(View v) {
		 	    	            	//show popup
		 	    	               
		 	    	            	final Dialog dialog = new Dialog(getActivity());
		 	    	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		 	    	                dialog.setContentView(R.layout.naiveweatherpopup);
		 	    	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		 	    	                TextView strongtext = (TextView) dialog.findViewById(R.id.occurence);
		 	    	                strongtext.setText("Wind");
		 	    	               final ImageView naivedataimg = (ImageView) dialog.findViewById(R.id.naivedataimg);
		 	    				 	
		 	    	               naivedataimg.setImageResource(R.drawable.winddataimg);
		 	    	                dialog.show();
		 	    	            }
		 	                });
		 	           
		            		break;
		            		
		            	case 2:
		            		naiveintrotitle.setVisibility(View.VISIBLE);
		            		naivetextsecond.setTypewriterText(getString(R.string.naivetesting1));
		            		outlookicon.setVisibility(View.VISIBLE);
		            		temperatureicon.setVisibility(View.VISIBLE);
		            		humidityicon.setVisibility(View.VISIBLE);
		            		windicon.setVisibility(View.VISIBLE);
		            		break;
		            		
		            	case  3:
		            		naiveintrotitle.setText("Solution");
		            		naivetextsecond.setTypewriterText(getString(R.string.naiveplaygame));
		            		naivetextsecond.setTextSize(15.0f);
		            		outlookicon.setVisibility(View.INVISIBLE);
		            		temperatureicon.setVisibility(View.INVISIBLE);
		            		humidityicon.setVisibility(View.INVISIBLE);
		            		windicon.setVisibility(View.INVISIBLE);
		            		break;
		            		
		            	case  4:
		            		naivetextsecond.setTypewriterText(getString(R.string.naivedontplaygame));
		            		naivetextsecond.setTextSize(15.0f);
		            		break;
		            		
		            	case  5:
		            		//Change Nina
		            		naivetextsecond.setTypewriterText(getString(R.string.calculatenaive1));
		            		imageView2.setImageResource(R.drawable.ninblackboard);
		            		break;
		            		
		            	case  6:
		            		naivetextsecond.setTypewriterText(getString(R.string.calculatenaive2));
		            		naivetextsecond.setTextSize(18.0f);
		            		
		            		break;	
		            		
		            	case  7:
		            		naivetextsecond.setTypewriterText(getString(R.string.calculatenaive3));
		            		naivetextsecond.setTextSize(18.0f);
		            		imageView2.setImageResource(R.drawable.nintennis);
		            		break;	
		            		
		            	case  8:
		            		
		            		naivetextsecond.setTypewriterText(getString(R.string.calculatenaive4));
		            		naivetextsecond.setTextSize(18.0f);
		            		imageView2.setImageResource(R.drawable.ninblackboard);
		            		break;	
		            		
		            	case  9:
		            		naivetextsecond.setTypewriterText(getString(R.string.calculatenaive5));
		            		naivetextsecond.setTextSize(18.0f);
		            		
		            		break;	
		            		
		            		
		            	case  10:
		            		naivetextsecond.setTypewriterText(getString(R.string.calculatenaive6));
		            		naivetextsecond.setTextSize(18.0f);
		            		break;	
		            		
		            	case  11:
		            		naivetextsecond.setTypewriterText(getString(R.string.calculatenaive7));
		            		naivetextsecond.setTextSize(18.0f);
		            		naiveintrotitle.setText("No Tennis");
		            		imageView2.setImageResource(R.drawable.edwardtennis);
		            		break;	
		            	case  12:
		            		//Calculate
		            		//ask user if they want to watch videos or take assessment
		            		//show pop up
		            		
		            		/*Fragment SimulNaiveBayesCalculateFragment = new SimulNaiveBayesCalculateFragment();
		 	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
		 	            	ft.replace(R.id.frame_container, SimulNaiveBayesCalculateFragment);
		 	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		 	            	ft.addToBackStack(SimulNaiveBayesCalculateFragment.getTag());
		 	            	ft.commit();*/
		            		break;	
		            		
		            		default:
		            			break;
		            	}
		            }
		        });
			 	
			 return rootView;
		    }
	        
	    }
