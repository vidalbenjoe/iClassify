package com.capstoneii.iclassify.simulation.desiciontree;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.capstoneii.iclassify.R;

@SuppressLint("NewApi")
public class SimulCalculateActivity extends Fragment implements android.view.animation.Animation.AnimationListener{
	private ImageView gainoutlook,gaintemre,gainhumid,gainwind;
	Animation left, right, leftout, rightout,animFadein,animFadeout;
	private Button simulnextwithquestion;
	Button drag;
	LinearLayout drop;
	TextView text,sucess;
	int total , failure = 0;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
	}
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
		 View rootView = inflater .inflate(R.layout.simulcalculate_activity, container, false);  
		
		 	left = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	                R.anim.left);
	        right = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	                R.anim.right);
	        leftout = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	                R.anim.leftout);
	        rightout = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	                R.anim.rightout);
	        animFadein = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	                R.anim.fade_in);
	        animFadeout = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	                R.anim.fade_out);
	    
	        left.setAnimationListener(this);
		    right.setAnimationListener(this);
	       
	        
		 drag = (Button)rootView.findViewById(R.id.one);
	     drop = (LinearLayout)rootView.findViewById(R.id.bottomlinear);
	     text = (TextView)rootView.findViewById(R.id.Total);
	     sucess = (TextView)rootView.findViewById(R.id.Sucess);
		 gainoutlook = (ImageView) rootView.findViewById(R.id.gainid1);
		 gaintemre = (ImageView) rootView.findViewById(R.id.gainid2);
		 gainhumid = (ImageView) rootView.findViewById(R.id.gainid3);
		 gainwind = (ImageView) rootView.findViewById(R.id.gainid4);
		 simulnextwithquestion = (Button) rootView.findViewById(R.id.simulnextcalculatebt);
		
		
		 
		 gainoutlook.setOnClickListener( new View.OnClickListener() {

	            @Override
	            public void onClick(View v) {
	            	out();
	            	final Dialog dialog = new Dialog(getActivity());
	                dialog.setContentView(R.layout.custom_dialog);
	                dialog.setTitle("Gain Outlook Formula");
	                dialog.setCancelable(false);
	                
	                TextView formulaS1 = (TextView) dialog.findViewById(R.id.formulas1);
	                formulaS1.setText(R.string.wweak);
	                
	                TextView formulaS2 = (TextView) dialog.findViewById(R.id.formulas2);
	                formulaS2.setText(R.string.wstrong);
	                
	                TextView formulatext = (TextView) dialog.findViewById(R.id.formulatext);
	                formulatext.setText(R.string.w1);
	                
	                dialog.show();
	                Button declineButton = (Button) dialog.findViewById(R.id.cadbtnOk);
	                // if decline button is clicked, close the custom dialog
	                declineButton.setOnClickListener(new OnClickListener() {
	                    @Override
	                    public void onClick(View v) {
	                        dialog.dismiss();
	                    }
	                });
	            }
   });
		 gaintemre.setOnClickListener( new View.OnClickListener() {

	            @Override
	            public void onClick(View v) {
	            	out();
	            	final Dialog dialog = new Dialog(getActivity());
	                dialog.setContentView(R.layout.custom_dialog);
	                dialog.setTitle("Gain Temperature Formula");
	                dialog.setCancelable(false);
	                
	                TextView formulaS1 = (TextView) dialog.findViewById(R.id.formulas1);
	                formulaS1.setText(R.string.wweak);
	                
	                TextView formulaS2 = (TextView) dialog.findViewById(R.id.formulas2);
	                formulaS2.setText(R.string.wstrong);
	                
	                TextView formulatext = (TextView) dialog.findViewById(R.id.formulatext);
	                formulatext.setText(R.string.w1);
	                
	                dialog.show();
	                Button declineButton = (Button) dialog.findViewById(R.id.cadbtnOk);
	                // if decline button is clicked, close the custom dialog
	                declineButton.setOnClickListener(new OnClickListener() {
	                    @Override
	                    public void onClick(View v) {
	                        dialog.dismiss();
	                    }
	                });
	            }
   });
		 
		 gainhumid.setOnClickListener( new View.OnClickListener() {

	            @Override
	            public void onClick(View v) {
	            	out();
	            	final Dialog dialog = new Dialog(getActivity());
	                dialog.setContentView(R.layout.custom_dialog);
	                dialog.setTitle("Gain Humidity Formula");
	                dialog.setCancelable(false);
	                
	                TextView formulaS1 = (TextView) dialog.findViewById(R.id.formulas1);
	                formulaS1.setText(R.string.wweak);
	                
	                TextView formulaS2 = (TextView) dialog.findViewById(R.id.formulas2);
	                formulaS2.setText(R.string.wstrong);
	                
	                TextView formulatext = (TextView) dialog.findViewById(R.id.formulatext);
	                formulatext.setText(R.string.w1);
	                
	                dialog.show();
	                Button declineButton = (Button) dialog.findViewById(R.id.cadbtnOk);
	                // if decline button is clicked, close the custom dialog
	                declineButton.setOnClickListener(new OnClickListener() {
	                    @Override
	                    public void onClick(View v) {
	                        dialog.dismiss();
	                    }
	                });
	            }
      });
		 gainwind.setOnClickListener( new View.OnClickListener() {

	            @Override
	            public void onClick(View v) {
	            	out();
	            	final Dialog dialog = new Dialog(getActivity());
	                dialog.setContentView(R.layout.custom_dialog);
	                dialog.setTitle("Gain Wind Formula");
	                dialog.setCancelable(false);
	                
	                TextView formulaS1 = (TextView) dialog.findViewById(R.id.formulas1);
	                formulaS1.setText(R.string.wweak);
	                
	                TextView formulaS2 = (TextView) dialog.findViewById(R.id.formulas2);
	                formulaS2.setText(R.string.wstrong);
	                
	                TextView formulatext = (TextView) dialog.findViewById(R.id.formulatext);
	                formulatext.setText(R.string.w1);
	                
	                dialog.show();
	                Button declineButton = (Button) dialog.findViewById(R.id.cadbtnOk);
	                // if decline button is clicked, close the custom dialog
	                declineButton.setOnClickListener(new OnClickListener() {
	                    @Override
	                    public void onClick(View v) {
	                        dialog.dismiss();
	                    }
	                });
	            }
         });
		 simulnextwithquestion.setOnClickListener( new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	out();
	             	Fragment SimulDragAndDropActivity = new SimulDragAndDropActivity();
	            	FragmentTransaction ft  = getFragmentManager().beginTransaction();
	            	ft.replace(R.id.frame_container, SimulDragAndDropActivity);
	            	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
 	            	ft.addToBackStack(SimulDragAndDropActivity.getTag());
	            	ft.commit();
	            }
		 });
		 in();
		 return rootView;
		 
		 
	    }
		
		public void in(){
			gainoutlook.startAnimation(left);
			gaintemre.startAnimation(right);
	        gainhumid.startAnimation(left);
	        gainwind.startAnimation(right);
	    }
		
		public void out(){
			gainoutlook.startAnimation(leftout);
			gaintemre.startAnimation(rightout);
			gainhumid.startAnimation(leftout);
			gainwind.startAnimation(rightout);
	      
	        gainoutlook.postDelayed(new Runnable() {
	            @Override
	            public void run() {
	                in();
	            }
	        }, 1000);
	    }
		@Override
		public void onAnimationStart(Animation animation) {
			
			
		}
		@Override
		public void onAnimationEnd(Animation animation) {
		
			
		}
		@Override
		public void onAnimationRepeat(Animation animation) {
		
			
		}
}
