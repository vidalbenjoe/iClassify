package com.capstoneii.iclassify.simulation.desiciontree;

import com.capstoneii.iclassify.R;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MotionEvent;
import android.view.DragEvent;
@SuppressLint("NewApi")
public class SimulDragAndDropActivity extends Fragment {

	Button drag1,drag2,drag3,drag4,invisibleoutlook;
	ImageView droptoroot;
	TextView text,sucess;
	EditText getcorrect;
	int total , failure = 0;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	}
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.drag_n_drop_layout, container, false);  
		 	drag1 = (Button)rootView.findViewById(R.id.one);
		 	drag2 = (Button)rootView.findViewById(R.id.two);
		 	drag3 = (Button)rootView.findViewById(R.id.three);
		 	drag4 = (Button)rootView.findViewById(R.id.four);
		 	invisibleoutlook = (Button)rootView.findViewById(R.id.outlookinvisible);
		 	
	        droptoroot = (ImageView) rootView.findViewById(R.id.dragheretoroot);
	        text = (TextView)rootView.findViewById(R.id.Total);
	        sucess = (TextView)rootView.findViewById(R.id.Sucess);
	        droptoroot.setOnDragListener(new View.OnDragListener() {
	            @Override
	            public boolean onDrag(View v, DragEvent event) {
	             
	            final int action = event.getAction();
	                  switch(action) {
	                  case DragEvent.ACTION_DRAG_STARTED:
	                	  drag1.setVisibility(View.INVISIBLE);
	                	  
	            break;
	                  case DragEvent.ACTION_DRAG_EXITED:
	                	 
	                    break;
	            case DragEvent.ACTION_DRAG_ENTERED:
	            	
	              break;
	            case DragEvent.ACTION_DROP:{
	            	 drag1.setVisibility(View.VISIBLE);
	            	 invisibleoutlook.setVisibility(View.VISIBLE);
	                      failure = failure+1;
	              return(true);
	            }
	            case DragEvent.ACTION_DRAG_ENDED:{
	            	
	              total = total +1;
	              int suc = total - failure;
	              /*sucess.setText("Sucessful Drops :"+suc);
	              text.setText("Total Drops: "+total);
	            */
	              if(suc >= 1){
	            	 drag1.setVisibility(View.INVISIBLE);
	            	 invisibleoutlook.setVisibility(View.VISIBLE);
	            	  final Dialog dialog = new Dialog(getActivity());
		                dialog.setContentView(R.layout.dialog_simul_correct_root);
		                dialog.setTitle("Correct!");
		                dialog.setCancelable(false);
		                dialog.show();
		             
		               
		               Button declineButton = (Button) dialog.findViewById(R.id.cadbtnOk);
		                
		                declineButton.setOnClickListener(new OnClickListener() {
		                    @Override
		                    public void onClick(View v) {
		                    	
		                       String ans;
		 		               getcorrect = (EditText) dialog.findViewById(R.id.gethumidityands);
		 		               ans = getcorrect.getText().toString();
		                    
		 		               if(ans.matches("Humidity")){
		 		            	  Fragment SimulDecisionResults = new SimulDecisionResults();
			    	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
			    	            	ft.replace(R.id.frame_container, SimulDecisionResults);
			    	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			     	            	ft.addToBackStack(SimulDecisionResults.getTag());
			    	            	ft.commit();
			                        dialog.dismiss();
		 		                }
		                    	 
		                    	 if(ans.contains("Humidity")){
		                    		 Fragment SimulDecisionResults = new SimulDecisionResults();
				    	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
				    	            	ft.replace(R.id.frame_container, SimulDecisionResults);
				    	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				     	            	ft.addToBackStack(SimulDecisionResults.getTag());
				    	            	ft.commit();
				                        dialog.dismiss();
		                    	 }
		                    	 if(ans.contains("humidity")){
		                    		 Fragment SimulDecisionResults = new SimulDecisionResults();
				    	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
				    	            	ft.replace(R.id.frame_container, SimulDecisionResults);
				    	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				     	            	ft.addToBackStack(SimulDecisionResults.getTag());
				    	            	ft.commit();
				                        dialog.dismiss();
		                    	 }
		                    	 
		                    	 
		                    	 if(ans.matches("humidity")){
		                    		 Fragment SimulDecisionResults = new SimulDecisionResults();
				    	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
				    	            	ft.replace(R.id.frame_container, SimulDecisionResults);
				    	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				     	            	ft.addToBackStack(SimulDecisionResults.getTag());
				    	            	ft.commit();
				                        dialog.dismiss();
		 		                }
		                    	 
		                    	 
		                    	 if(ans.matches("Temperature")){
		                    		 Toast.makeText(getActivity().getApplicationContext(), "Sorry your Answers is wrong, please study the algorithm",
		                    				   Toast.LENGTH_LONG).show();
		 		                }
		                    	 
		                    	 if(ans.matches("temperature")){
		                    		 Toast.makeText(getActivity().getApplicationContext(), "Sorry your Answers is wrong, please study the algorithm",
		                    				   Toast.LENGTH_LONG).show();
		 		                }
		                    	 
		                    	 if(ans.matches("Wind")){
		                    		 Toast.makeText(getActivity().getApplicationContext(), "Sorry your Answers is wrong, please study the algorithm",
		                    				   Toast.LENGTH_LONG).show();
		 		                }
		                    	 
		                    	 if(ans.matches("wind")){
		                    		 Toast.makeText(getActivity().getApplicationContext(), "Sorry your Answers is wrong, please study the algorithm",
		                    				   Toast.LENGTH_LONG).show();
		 		                }
		                    	 if(ans.matches("")){
		                    		 Toast.makeText(getActivity().getApplicationContext(), "Please put your answer in the textbox",
		                    				   Toast.LENGTH_LONG).show();
		                    	 }
		                        //this will gonna be tha last activity..
		                       /* The decision tree can also be expressed in rule format:

		                        	IF outlook = sunny AND humidity = high THEN playball = no

		                        	IF outlook = rain AND humidity = high THEN playball = no

		                        	IF outlook = rain AND wind = strong THEN playball = yes

		                        	IF outlook = overcast THEN playball = yes

		                        	IF outlook = rain AND wind = weak THEN playball = yes*/
		                    }
		                    
		                });
		               
	              }
	              return(true);
	            }
	            default:
	                  break;
	                  }
	            return true;
	            }});
	        
	        drag1.setOnTouchListener(new OnTouchListener() {
	            @Override
	            public boolean onTouch(View v, MotionEvent arg1) {
	          
	              ClipData data = ClipData.newPlainText("", "");
	              View.DragShadowBuilder shadow = new View.DragShadowBuilder(drag1);
	              v.startDrag(data, shadow, null, 0);
	              return false;
	            }
	          });
	        
	        
	        drag2.setOnTouchListener(new OnTouchListener() {
	            @Override
	            public boolean onTouch(View v, MotionEvent arg1) {
	          
	              ClipData data = ClipData.newPlainText("", "");
	              View.DragShadowBuilder shadow = new View.DragShadowBuilder(drag2);
	              v.startDrag(data, shadow, null, 0);
	              return false;
	            }
	          });
	        
	        drag3.setOnTouchListener(new OnTouchListener() {
	            @Override
	            public boolean onTouch(View v, MotionEvent arg1) {
	          
	              ClipData data = ClipData.newPlainText("", "");
	              View.DragShadowBuilder shadow = new View.DragShadowBuilder(drag3);
	              v.startDrag(data, shadow, null, 0);
	              return false;
	            }
	          });
	        
	        drag4.setOnTouchListener(new OnTouchListener() {
	            @Override
	            public boolean onTouch(View v, MotionEvent arg1) {
	          
	              ClipData data = ClipData.newPlainText("", "");
	              View.DragShadowBuilder shadow = new View.DragShadowBuilder(drag4);
	              v.startDrag(data, shadow, null, 0);
	              return false;
	            }
	          });
		 return rootView;
}
}
