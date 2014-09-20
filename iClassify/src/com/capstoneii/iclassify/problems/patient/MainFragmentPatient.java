package com.capstoneii.iclassify.problems.patient;

import com.capstoneii.iclassify.R;

import android.app.Dialog;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainFragmentPatient extends Fragment {
	TextView textHead,textFlu;
	ImageView adamImage;
	Button lookupbt;
	int nextButton = 0;
	
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	 
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.patient_main_layout, container, false);  
		 textHead = (TextView) rootView.findViewById(R.id.textHead);
		 textFlu = (TextView) rootView.findViewById(R.id.textFlu);
		 adamImage = (ImageView) rootView.findViewById(R.id.adamImage);
		 
		 adamImage.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	
	            	if(InputFragmentView == adamImage){
	            		nextButton++;	
	            	}
	            	
	            	switch(nextButton){
	            	case 1:
	            		textFlu.setText("Lookup table");
	            		textHead.setVisibility(View.INVISIBLE);
	            		adamImage.setVisibility(View.INVISIBLE);
	            		lookupbt.setVisibility(View.VISIBLE);
	            		lookupbt.setOnClickListener(new View.OnClickListener()
	        	        {
	        	            @Override
	        	            public void onClick(View InputFragmentView)
	        	            {
	        	            	//display looup table with next button in dialog box
	        	            	
	        	            	final Dialog dialog = new Dialog(getActivity());
		    	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		    	                dialog.setContentView(R.layout.dialog_patient_lookup);
		    	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		    	              
		    	                Button cadbtnNext = (Button) dialog.findViewById(R.id.cadbtnNext);
		    	                cadbtnNext.setOnClickListener(new View.OnClickListener()
		    	    	        {
		    	    	            @Override
		    	    	            public void onClick(View InputFragmentView)
		    	    	            {
		    	    	            	//another Fragment(Testing)
		    	    	            	/*Fragment chooseAlgocatSheep = new chooseAlgocatSheep();
		    	    	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
		    	    	            	ft.replace(R.id.frame_container, chooseAlgocatSheep);
		    	    	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		    	    	            	ft.commit();*/
		    	    	            }
		    	    	        });
		    	                dialog.show();
	        	            }
	        	        });
	            		
	            		break;
	            		
	            	case 2:
	            		
	            		break;
	            		
	            	case 3:
	            		
	            		break;
	            		
	            		
	            		default:
	            			break;
	            			
	            	}
	            	
	            	
	            	
	            	
	            }
	        });
		 return rootView;
	    }
	 
}
