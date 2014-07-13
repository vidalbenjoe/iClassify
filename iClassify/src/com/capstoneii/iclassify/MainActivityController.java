package com.capstoneii.iclassify;

import bayesdiscussflip.NativeBayesLayoutActivity;

import com.capstoneii.iclassify.simulation.desiciontree.SimulStartActivity;
import descisiondiscussflip.DescTreeLayoutActivity;
import knearestdiscussflip.KNearestLayoutActivity;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import at.technikum.mti.fancycoverflow.FancyCoverFlowAdapter;


@SuppressLint("NewApi")
public class MainActivityController extends Fragment {
Button first_bt;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.layout_inflate, container, false);  
	       

		 	FancyCoverFlow fancyCoverFlow = (FancyCoverFlow) rootView.findViewById(R.id.fancyCoverFlow);
	        fancyCoverFlow.setReflectionEnabled(true);
	        fancyCoverFlow.setReflectionRatio(0.3f);
	        fancyCoverFlow.setReflectionGap(0);

	       fancyCoverFlow.setAdapter(new ViewGroupExampleAdapter());
	        
	       	
		 return rootView;
	    }
	 
	 
	 
	 private static class ViewGroupExampleAdapter extends FancyCoverFlowAdapter {

	        // =============================================================================
	        // Private members
	        // =============================================================================

		 private int[] images = {R.drawable.chooseintro, 
				 R.drawable.choosedt,
				 R.drawable.chooseknn,
				 R.drawable.choosenb,};
	        // =============================================================================
	        // Supertype overrides
	        // =============================================================================
		
		 public void onClick(View v) {
         	
			
    		
        }
	
		 
	        @Override
	        public int getCount() {
	            return images.length;
	        }

	        @Override
	        public Integer getItem(int i) {
	            return images[i];
	        }

	        @Override
	        public long getItemId(int i) {
	            return i;
	        }

	        
	        
	        @Override
	        public View getCoverFlowItem(final int i, View reuseableView, ViewGroup viewGroup) {
	            CustomViewGroup customViewGroup = null;

	            if (reuseableView != null) {
	                customViewGroup = (CustomViewGroup) reuseableView;
	            } else {
	                customViewGroup = new CustomViewGroup(viewGroup.getContext());
	                customViewGroup.setLayoutParams(new FancyCoverFlow.LayoutParams(300, 600));
	            }

	            customViewGroup.getImageView().setImageResource(this.getItem(i));
	          
	            
	            customViewGroup.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View InputFragmentView) {
	            
	            switch(i){
	            case 0:
	            	
	            	break;
	            	
	            case 1:
	            	Intent intent1 = new Intent(InputFragmentView.getContext(), DescTreeLayoutActivity.class);
	           	 	InputFragmentView.getContext().startActivity(intent1);
	            	break;
	            	
	            case 2:
	           	 	Intent intent2 = new Intent(InputFragmentView.getContext(), KNearestLayoutActivity.class);
	           	 	InputFragmentView.getContext().startActivity(intent2);
	            	break;
	            	
	            case 3:
	            	 Intent intent3 = new Intent(InputFragmentView.getContext(), NativeBayesLayoutActivity.class);
	            	 InputFragmentView.getContext().startActivity(intent3);
	            	break;
	            	
	            default:
	            	break;
	            
	            }
	            }
	            });
	            return customViewGroup;
	        }

	    }

	 
	    private static class CustomViewGroup extends LinearLayout {
	    	
	        // =============================================================================
	        // Child views
	        // =============================================================================

	        private ImageView imageView;

	        private Button button;

	        // =============================================================================
	        // Constructor
	        // =============================================================================

	        private CustomViewGroup(Context context) {
	            super(context);
	           
	            this.setOrientation(VERTICAL);
	            this.setWeightSum(5);

	            this.imageView = new ImageView(context);
	            this.button = new Button(context);

	            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

	            this.imageView.setLayoutParams(layoutParams);
	            this.button.setLayoutParams(layoutParams);

	            this.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
	            this.imageView.setAdjustViewBounds(true);

	           // this.button.setText("More Info");
	           // this.button.setId(0);
	       
	          

	            this.addView(this.imageView);
	           // this.addView(this.button);
	        }

	        // =============================================================================
	        // Getters
	        // =============================================================================

	        private ImageView getImageView() {
	            return imageView;
	        }
	    }
	 
}
