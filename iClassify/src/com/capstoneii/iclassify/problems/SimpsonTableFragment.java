package com.capstoneii.iclassify.problems;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.SecretTextView;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class SimpsonTableFragment  extends Fragment {
	 SecretTextView secretTextView;
	 ImageView simpsoneTable;
	 Button nextProcBt;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		    {
			 View rootView = inflater .inflate(R.layout.simpson_table_fragment, container, false);  
			 	
			 final TypewriterTextView simpsonAnimatedTextView = (TypewriterTextView)rootView.findViewById(R.id.simpsonAnimatedTextView);
			 simpsonAnimatedTextView.setTypewriterText(getString(R.string.tablesimpson));
			 
			 simpsoneTable = (ImageView) rootView.findViewById(R.id.simpsoneTable);
			 simpsoneTable.setVisibility(View.VISIBLE);
			 nextProcBt = (Button) rootView.findViewById(R.id.nextProcBt);
			 nextProcBt.setOnClickListener(new View.OnClickListener()
		        {
		            @Override
		            public void onClick(View InputFragmentView)
		            {
		            	 	Fragment SimpsonsDecisionTreeFragment = new SimpsonsDecisionTreeFragment();
		 	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
		 	            	ft.replace(R.id.frame_container, SimpsonsDecisionTreeFragment);
		 	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			            	ft.addToBackStack(SimpsonsDecisionTreeFragment.getTag());
		 	            	ft.commit();
		            }
		        });
			 return rootView;
		    }
	        public void onBackPressed(){
	        	
	        }
}
