package com.capstoneii.iclassify.problems.simpsonsknn;

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
import com.capstoneii.iclassify.library.SecretTextView;
import com.capstoneii.iclassify.library.TypewriterTextView;
public class SimpsonTableFragmentKNN extends Fragment {
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
		            	//dialog box(Comic's Attribute)
		            	final Dialog dialog = new Dialog(getActivity());
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.custom_dialog_image);
						dialog.getWindow().setBackgroundDrawable(
								new ColorDrawable(
										android.graphics.Color.TRANSPARENT));
						
						TextView nameof = (TextView) dialog.findViewById(R.id.nameof);
						nameof.setText("Comic's Attribute");
						
						Button cadbtnNext = (Button) dialog.findViewById(R.id.cadbtnNext);
						dialog.show();
						
						cadbtnNext.setOnClickListener(new View.OnClickListener()
				        {
				            @Override
				            public void onClick(View InputFragmentView)
				            {
				            	Fragment SimpsonKnnStep = new SimpsonKnnStep();
				            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
				            	ft.replace(R.id.frame_container, SimpsonKnnStep);
				            	ft.commit();
				            	dialog.dismiss();
				            }
						
				        });
		            }
		            
		        });
			 
			 return rootView;
		    }
	        public void onBackPressed(){
	        	
	        }
}
