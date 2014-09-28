package com.capstoneii.iclassify.problems.simpsonsknn;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class SimpsonChartFragment extends Fragment {
	ImageView simpsonschartimage,knnnextbt;
	Animation zoomIn,zoomOut;
	int counter = 0;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		    {
			 View rootView = inflater .inflate(R.layout.simpson_chart_layout, container, false);  
			 	
			 zoomIn = AnimationUtils.loadAnimation(getActivity(),
						R.anim.unzoom_in);
			 zoomOut = AnimationUtils.loadAnimation(getActivity(),
						R.anim.unzoom_out);
			 
			 
			 final TypewriterTextView simpsonAnimatedTextViewChart = (TypewriterTextView)rootView.findViewById(R.id.simpsonAnimatedTextViewChart);
			 simpsonAnimatedTextViewChart.setTypewriterText(getString(R.string.problemsimpson));
			 
			 simpsonschartimage = (ImageView) rootView.findViewById(R.id.simpsonschartimage);
			 simpsonschartimage.startAnimation(zoomIn);
			 simpsonschartimage.setImageResource(R.drawable.simpsonchart);
			 knnnextbt = (ImageView) rootView.findViewById(R.id.knnnextbt);
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
		            		
		            		simpsonAnimatedTextViewChart.setText("The table belows is a solution for the problem using KNN/ K Nearest Neighbor ");
		            		
		            		simpsonschartimage.setImageResource(R.drawable.simpsongknncompute);
		            		simpsonschartimage.startAnimation(zoomIn);
		            		break;
		            	case 2:
		            		simpsonAnimatedTextViewChart.setText("Since we have 3 males then we can conclude that Comic is a male! ");
		            		
		            		simpsonschartimage.setImageResource(R.drawable.simpsoncharttree);
		            		simpsonschartimage.startAnimation(zoomIn);
		            		

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
