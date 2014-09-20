package com.capstoneii.iclassify.simulation.desiciontree;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.assessment.decisionid3.DecisionTreeAssessmentActivity;

@SuppressLint("NewApi")
public class SimulDecisionResults extends Fragment{
	public String videoFile;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	}
	
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.simuldecisionresult_layout, container, false);  
		  
		 Button watchvid = (Button) rootView.findViewById(R.id.watchvideoidr);
		 Button takeassess = (Button) rootView.findViewById(R.id.takeassess);
		 
		 final Dialog dialog = new Dialog(getActivity());
     	 dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
         dialog.setContentView(R.layout.popup);
         dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
         TextView strongtext = (TextView) dialog.findViewById(R.id.occurence);
         strongtext.setText(R.string.humidityresult);
         dialog.show();
		 
		 
		 watchvid.setOnClickListener(new View.OnClickListener()
	       {
	           @Override
	           public void onClick(View InputFragmentView)
	           {
	        	   
	                final Dialog dialog = new Dialog(getActivity());
	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	                dialog.setContentView(R.layout.watch_video_popup);
	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	                
	                dialog.show();
	                
	           }
	       });
		 
		 takeassess.setOnClickListener(new View.OnClickListener()
	       {
	           @Override
	           public void onClick(View InputFragmentView)
	           {
	        	   Intent intent = new Intent(getActivity(), DecisionTreeAssessmentActivity.class);
	        	   getActivity().startActivity(intent);
	           }
	       });
		 
		 
		 return rootView;
	    }
        
        public void showPopup(View v) {
            PopupMenu popup = new PopupMenu(getActivity(), v);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu, popup.getMenu());
            popup.show();
        }
        
        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        	 getActivity().getMenuInflater().inflate(R.menu.menu, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
        
       
        
        public boolean onOptionsItemSelected(MenuItem item) {
        	  switch (item.getItemId()) {
        	    case R.id.action_settings:
        	     
        	      return true;
        	  
        	    default:
        	      return super.onOptionsItemSelected(item);
        	  }
        	}
        
        
        
       
}