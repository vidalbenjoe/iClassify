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
import android.widget.RelativeLayout;

import com.capstoneii.iclassify.R;

public class SimpsonsDecisionTreeFragment extends Fragment {
	Button simpsonNextbt;
	RelativeLayout simpsonRelative;
	ImageView familyformula,gainimage,backgroundImage,splittingweight,simpsonFamilyTreeRoot,simpsonFamilyTreeHair;
	Animation animSideDown,animSideUp;
	int buttonNextClick = 0;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);}
	    
	     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		    {
	    	 View rootView = inflater .inflate(R.layout.simpson_dt_layout_one, container, false);  
	    
	    	 backgroundImage = (ImageView) rootView.findViewById(R.id.backgroundImage);
	    	 gainimage  = (ImageView) rootView.findViewById(R.id.gainimage);
	    	 simpsonFamilyTreeRoot = (ImageView) rootView.findViewById(R.id.simpsonFamilyTreeRoot);
	    	 simpsonFamilyTreeHair = (ImageView) rootView.findViewById(R.id.simpsonFamilyTreeHair);
	    	 splittingweight = (ImageView) rootView.findViewById(R.id.splittingweight);
	    	 
	    	 familyformula = (ImageView) rootView.findViewById(R.id.familyformula);
	    	 simpsonNextbt = (Button) rootView.findViewById(R.id.simpsonNextbt);
	    	 
	    	 simpsonRelative = (RelativeLayout) rootView.findViewById(R.id.simpsonRelative);
	    	 
	    	 simpsonNextbt.setOnClickListener(new View.OnClickListener()
		        {
		            @Override
		            public void onClick(View InputFragmentView)
		            {
		            	if(InputFragmentView == simpsonNextbt){
		            		buttonNextClick++;	
		            	}
		            	switch(buttonNextClick){
		            	case 1:
		            		gainimage.setVisibility(View.VISIBLE);
		            		animSideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
			    					R.anim.slide_down);
		            		
		            		animSideUp = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
			    					R.anim.slide_up);
		            		backgroundImage.setVisibility(View.GONE);
		            		gainimage.setBackgroundResource(R.drawable.hairlengthgain);
		            		simpsonFamilyTreeRoot.setVisibility(View.VISIBLE);
			            	simpsonFamilyTreeHair.setVisibility(View.VISIBLE);
			            	simpsonFamilyTreeHair.setBackgroundResource(R.drawable.familyanswer);
			            	simpsonFamilyTreeHair.startAnimation(animSideDown);
			            	simpsonRelative.setBackgroundResource(R.drawable.backgroundblur);
			            	
			            	break;
		            		
		           case 2: 
		        	   		splittingweight.setVisibility(View.VISIBLE);
		        	   		gainimage.setVisibility(View.GONE);
		        	   		backgroundImage.setVisibility(View.GONE);
		        	   		simpsonFamilyTreeRoot.setVisibility(View.GONE);
		        	   		simpsonFamilyTreeHair.setVisibility(View.INVISIBLE);
		        	   		simpsonFamilyTreeHair.setVisibility(View.GONE);
		        	   		simpsonFamilyTreeHair.startAnimation(animSideUp);
		            		//simpsonRelative.setBackgroundResource(R.drawable.backgroundblur);
		        	   		familyformula.setVisibility(View.VISIBLE);
		            		break;
		           case 3:
		        	   splittingweight.setVisibility(View.GONE);
		        	   gainimage.setBackgroundResource(R.drawable.weightsimpsongain);
		        	   gainimage.setVisibility(View.VISIBLE);
		        	   familyformula.setVisibility(View.GONE);
		        	   simpsonFamilyTreeRoot.setVisibility(View.VISIBLE);
		        	   
		        	   simpsonFamilyTreeHair.setVisibility(View.VISIBLE);
		        	   simpsonFamilyTreeHair.setBackgroundResource(R.drawable.weight160);
		        	   simpsonFamilyTreeHair.startAnimation(animSideDown);
		        	   break;
		        	   
		           case 4:
		        	    Fragment SimpsonsNextProcedure = new SimpsonsNextProcedure();
	 	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	 	            	ft.replace(R.id.frame_container, SimpsonsNextProcedure);
	 	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		            	ft.addToBackStack(SimpsonsNextProcedure.getTag());
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



