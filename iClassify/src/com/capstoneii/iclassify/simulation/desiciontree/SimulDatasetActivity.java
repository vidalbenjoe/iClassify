package com.capstoneii.iclassify.simulation.desiciontree;

import com.capstoneii.iclassify.R;
import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.inqbarna.tablefixheaders.adapters.MatrixTableAdapter;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SimulDatasetActivity extends Fragment {
private Button calculate;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);}
		
		
		

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.table, container, false);  
	   
		 
		 calculate = (Button) rootView.findViewById(R.id.calculatebt);
		
		 calculate.setOnClickListener(new View.OnClickListener()
	       {
			

			@Override
			public void onClick(View v) {
				  	Fragment SimulCalculateActivity = new SimulCalculateActivity();
	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	            	ft.replace(R.id.frame_container, SimulCalculateActivity);
	            	ft.addToBackStack(null);
	            	ft.commit();
			}
			 
	       });
		 
			TableFixHeaders tableFixHeaders = (TableFixHeaders) rootView.findViewById(R.id.table);
			MatrixTableAdapter<String> matrixTableAdapter = new MatrixTableAdapter<String>(getActivity(), new String[][] {
					{
							"Outlook",
							"Temperature",
							"Humidity",
							"Wind",
							"Play Baseball?"},
					{
							"Sunny",
							"Hot",
							"High",
							"Weak",
							"No" },
					{
							"Sunny",
							"Hot",
							"High",
							"Strong",
							"No" },
					{
							"Overcast",
							"Hot",
							"High",
							"Weak",
							"Yes" },
					{
							"Rain",
							"Mild",
							"High",
							"Weak",
							"Yes" },
					{
							"Rain",
							"Cool",
							"Normal",
							"Weak",
							"Yes" },
					{
							"Rain",
							"Cool",
							"Normal",
							"Strong",
							"No" },
					
					{
							"Overcast",
							"Cool",
							"Normal",
							"Strong",
							"Yes" },
					{
							"Sunny",
							"Mild",
							"High",
							"Weak",
							"No" },
					{
							"Sunny",
							"Cool",
							"Normal",
							"Weak",
							"No" },	
							
					{
							"Rain",
							"Mild",
							"Normal",
							"Weak",
							"No"},
							
					{
							"Sunny",
							"Mild",
							"Normal",
							"Strong",
							"No" },
							
					{
							"Overcast",
							"Mild",
							"High",
							"Strong",
							"No" },
								
					{
							"Overcast",
							"Hot",
							"Normal",
							"Weak",
							"Yes"},
							
					{
							"Rain",
							"Mild",
							"High",
							"Strong",
							"No" },
									
			});
			tableFixHeaders.setAdapter(matrixTableAdapter);
		 return rootView;
	    }
        
	
	
}
