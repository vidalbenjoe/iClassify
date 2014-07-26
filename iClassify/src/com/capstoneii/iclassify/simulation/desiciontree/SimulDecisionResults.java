package com.capstoneii.iclassify.simulation.desiciontree;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.videos.VideoMenuActivity;

@SuppressLint("NewApi")
public class SimulDecisionResults extends Fragment{


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
	        	   Intent intent = new Intent(getActivity(), VideoMenuActivity.class);
	                startActivity(intent);
	           }
	       });
		 
		 takeassess.setOnClickListener(new View.OnClickListener()
	       {
	           @Override
	           public void onClick(View InputFragmentView)
	           {
	        	 //ASSESSMENT
	           }
	       });
		 
		 
		 return rootView;
	    }
}