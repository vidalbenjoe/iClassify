package com.capstoneii.iclassify.problems.patient;

import com.capstoneii.iclassify.R;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("NewApi")
public class MainFragmentPatient extends Fragment {
	TextView textHead,textFlu,textFluNo,ClickmeText;
	EditText textFluNoTextBox,textFluTextBox;
	ImageView adamImage;
	Button lookupbt;
	int nextButton = 0;
	Animation clock;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	 
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.patient_main_layout, container, false);  
		 
		 
		 lookupbt = (Button) rootView.findViewById(R.id.lookupbt);
		 textFluNoTextBox = (EditText) rootView.findViewById(R.id.textFluNoTextBox);
		 textFluTextBox = (EditText) rootView.findViewById(R.id.textFluTextBox);
		 
		 
		 textHead = (TextView) rootView.findViewById(R.id.textHead);
		 textFlu = (TextView) rootView.findViewById(R.id.textFlu);
		 textFluNo = (TextView) rootView.findViewById(R.id.textFluNo);
 		 textHead.setVisibility(View.VISIBLE);
 		 textFlu.setVisibility(View.VISIBLE);
 		 textHead.setText("Do I believe that a patient with the following symptoms has a flu?");
		 textFlu.setText("Flu?");
		 
		 adamImage = (ImageView) rootView.findViewById(R.id.adamImage);
		 ClickmeText = (TextView) rootView.findViewById(R.id.ClickmeText);
		 ClickmeText.setText("Click Me");
		 adamImage.setVisibility(View.VISIBLE);
		 adamImage.setBackgroundResource(R.drawable.adamdoc);
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
	            		textHead.setText("Lookup Table");
	            		textFlu.setText(R.string.lookuptextpatient);
	            		textFlu.setVisibility(View.VISIBLE);
	            		textHead.setVisibility(View.VISIBLE);
	            		adamImage.setVisibility(View.INVISIBLE);
	            		lookupbt.setVisibility(View.VISIBLE);
	            		lookupbt.setText("Lookup");
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
		    	    	            	dialog.dismiss();
		    	    	            	textHead.setText("TESTING");
		    	    	            	textFlu.setText(R.string.patienttesting);
		    	    	            	
		    	    	            	/*set image computation for adamImage
		    	    	            	X = (Chills=Yes, Runny Nose=No, Headache=Mild, Fever=No)*/
		    	    	            	lookupbt.setVisibility(View.INVISIBLE);
		    	    	            	adamImage.setVisibility(View.VISIBLE);
		    	    	            	adamImage.setBackgroundResource(R.drawable.nextbtred);
		    	    	            	adamImage.setOnClickListener(new View.OnClickListener()
		  		    	    	        {
		  		    	    	            @Override
		  		    	    	            public void onClick(View InputFragmentView)
		  		    	    	            {
		  		    	    	            	if(InputFragmentView == adamImage){
		  		    	    	            		case2();
				    	    	            		nextButton=1;
				    	    	            		nextButton++;	
				    	    	            	}
		  		    	    	            	case2();
		  		    	    	            }
		  		    	    	        });
		    	    	            }
		    	    	        });
		    	                dialog.show();
	        	            }
	        	        });
	            		
	            		break;
	            				default:
	            			break;
	            	}
	            }
	        });
		 return rootView;
	    }
	 
	 public void case2(){
		 
		 switch(nextButton){
	 case 2:
 		textHead.setText("TESTING PHASE");
 		
 		
 		//show add another textView below textFlu then add a textBox below each Text
 		//check if the user answers are correct
 		
 		textFlu.setText(R.string.patientmultiply);//zoom animation
 		textFluNo.setVisibility(View.VISIBLE);
 		textFluNo.setText(R.string.textFluNoMultiply);
 		
 		textFlu.setGravity(Gravity.LEFT);
 		textFluNo.setGravity(Gravity.LEFT);
 		
 		textFluTextBox.setVisibility(View.VISIBLE);
 		textFluNoTextBox.setVisibility(View.VISIBLE);
 		
 		adamImage.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View InputFragmentView)
	            {
	            	String getvalueFluNoTextBox = ((EditText) getActivity().findViewById(R.id.textFluNoTextBox)).getText().toString();//get the value of text;
	         		
	         		String getValueFluTextBox = ((EditText) getActivity().findViewById(R.id.textFluTextBox)).getText().toString();
	         		
	            	if(getvalueFluNoTextBox.equals(null)){
	            		 Toast.makeText(getActivity(), "Please put your answer in the textbox", Toast.LENGTH_SHORT).show();
	            	 }else if(getvalueFluNoTextBox.matches("")){
	            		 Toast.makeText(getActivity(), "Please put your answer in the textbox", Toast.LENGTH_SHORT).show();
	            	 }else if(getvalueFluNoTextBox.isEmpty()){
	            		 Toast.makeText(getActivity(), "Please put your answer in the textbox", Toast.LENGTH_SHORT).show();
	            	 }
	            	 
	            	 if(getValueFluTextBox.equals(null)){
	            		 Toast.makeText(getActivity(), "Please put your answer in the textbox", Toast.LENGTH_SHORT).show();
	            	 }else if(getValueFluTextBox.matches("")){
	            		 Toast.makeText(getActivity(), "Please put your answer in the textbox", Toast.LENGTH_SHORT).show();
	            	 }else if(getValueFluTextBox.isEmpty()){
	            		 Toast.makeText(getActivity(), "Please put your answer in the textbox", Toast.LENGTH_SHORT).show();
	            	 }
	            	if(getValueFluTextBox.equals("0.006")||(getvalueFluNoTextBox.equals("0.0185"))){
	            		 Toast.makeText(getActivity().getApplicationContext(), "Next Fragment",
	              				   Toast.LENGTH_LONG).show();
	            	 }
	            	else{
	            		Toast.makeText(getActivity().getApplicationContext(), "Please provide a correct answer",
	              				   Toast.LENGTH_LONG).show();
	         		}
	            }
	        });
 		
 		break;
 		
 	case 3:
 		
 		break;
 	default:
		break;
	 }
	 }
	 
	 public void onBackPressed(){
			
	 }
}


//another Fragment(Testing)
/*Fragment chooseAlgocatSheep = new chooseAlgocatSheep();
FragmentTransaction ft  = getFragmentManager().beginTransaction();
ft.replace(R.id.frame_container, chooseAlgocatSheep);
ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
ft.commit();*/
