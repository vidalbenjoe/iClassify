package com.capstoneii.iclassify.simulation.desiciontree;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.capstoneii.iclassify.R;

@SuppressLint("NewApi")
public class SimulCalculateActivity extends Fragment implements android.view.animation.Animation.AnimationListener{
	private ImageView gainoutlook,gainhumid,gainwind;
	Animation left, right, leftout, rightout,animFadein,animFadeout;
	private Button simulnextwithquestion;
	Button drag;
	LinearLayout drop;
	TextView text,sucess;
	int total , failure = 0;
	Animation animSideDown;
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
		    //animSideDown.setAnimationListener(this);
		
		 drag = (Button)rootView.findViewById(R.id.one);
	     drop = (LinearLayout)rootView.findViewById(R.id.bottomlinear);
	     text = (TextView)rootView.findViewById(R.id.Total);
	     sucess = (TextView)rootView.findViewById(R.id.Sucess);
		 gainoutlook = (ImageView) rootView.findViewById(R.id.gainid1);
		
		 gainhumid = (ImageView) rootView.findViewById(R.id.gainid3);
		 gainwind = (ImageView) rootView.findViewById(R.id.gainid4);
		 simulnextwithquestion = (Button) rootView.findViewById(R.id.simulnextcalculatebt);
		
		 
		 gainoutlook.setOnClickListener( new View.OnClickListener() {

	            @Override
	            public void onClick(View v) {
	            	out();
	            	final Dialog dialog = new Dialog(getActivity());
	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	                dialog.setContentView(R.layout.custom_dialog);
	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	                dialog.setCancelable(false);
	                
	              
	                left = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	    	                R.anim.left);
	    	        right = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	    	                R.anim.right);
	    	    	// load the animation
	    	      
	    			final ImageView formulaimg = (ImageView) dialog.findViewById(R.id.formulaimg);
	    			final ImageView heirarimg = (ImageView) dialog.findViewById(R.id.heirarimg);
	    			
	    			formulaimg.setImageResource(R.drawable.outlookdragimg);
	                heirarimg.setImageResource(R.drawable.outlookheirar);
	                
	    			animSideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	    					R.anim.slide_down);
	    			
	    	        Button windweak = (Button) dialog.findViewById(R.id.windweak);
	    	        Button windstrong = (Button) dialog.findViewById(R.id.windstrong);
	                windweak.setBackgroundResource(R.drawable.outlookrain);
	                windstrong.setBackgroundResource(R.drawable.outlooksunny);
	    	        
	                windweak.startAnimation(left);
	                windstrong.startAnimation(right);
	                
	                TextView entropytitle = (TextView) dialog.findViewById(R.id.entropytitle);
	                entropytitle.setText("Gain(S,Outlook)");
	                final TextView formulatext = (TextView) dialog.findViewById(R.id.formulatext);
	                formulatext.setText(R.string.o1);
	                formulatext.setVisibility(View.INVISIBLE);
	                dialog.show();
	                
	                formulaimg.setOnClickListener( new View.OnClickListener() {

	    	            @Override
	    	            public void onClick(View v) {
	    	            	heirarimg.setVisibility(View.VISIBLE);
	    	            	heirarimg.startAnimation(animSideDown);
	    	            	formulatext.setVisibility(View.VISIBLE);
	    	            }
	                });
	                
	                
	                windstrong.setOnClickListener( new View.OnClickListener() {

	    	            @Override
	    	            public void onClick(View v) {
	    	            	//show popup
	    	               
	    	            	final Dialog dialog = new Dialog(getActivity());
	    	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    	                dialog.setContentView(R.layout.popup);
	    	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	    	                TextView strongtext = (TextView) dialog.findViewById(R.id.occurence);
	    	                strongtext.setText(R.string.windoccurencesstrong);
	    	                dialog.show();
	    	            }
	                });
	           
	                windweak.setOnClickListener( new View.OnClickListener() {

	    	            @Override
	    	            public void onClick(View v) {
	    	            	
	    	            	final Dialog dialog = new Dialog(getActivity());
	    	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    	                dialog.setContentView(R.layout.popup);
	    	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	    	                TextView weaktext = (TextView) dialog.findViewById(R.id.occurence);
	    	                weaktext.setText(R.string.windoccurencesweak);
	    	                dialog.show();
	    	            }
	                });
	                
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
	            	out();
	            	final Dialog dialog = new Dialog(getActivity());
	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	                dialog.setContentView(R.layout.custom_dialog);
	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	                dialog.setTitle("Gain Wind Formula");
	                dialog.setCancelable(false);
	                
	              
	                left = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	    	                R.anim.left);
	    	        right = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	    	                R.anim.right);
	    	    	// load the animation
	    	      
	    		
	    			final ImageView formulaimg = (ImageView) dialog.findViewById(R.id.formulaimg);
	    			final ImageView heirarimg = (ImageView) dialog.findViewById(R.id.heirarimg);
	    			
	    			
	    			formulaimg.setImageResource(R.drawable.humiddragimg);
	                heirarimg.setImageResource(R.drawable.humidityheirar);
	                
	    			animSideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	    					R.anim.slide_down);
	    	        Button windweak = (Button) dialog.findViewById(R.id.windweak);
	    	        Button windstrong = (Button) dialog.findViewById(R.id.windstrong);
	    	        windweak.setBackgroundResource(R.drawable.humidityhighl);
	                windstrong.setBackgroundResource(R.drawable.humiditynormal);
	    	        
	                
	                windweak.startAnimation(left);
	                windstrong.startAnimation(right);
	                
	                TextView entropytitle = (TextView) dialog.findViewById(R.id.entropytitle);
	                entropytitle.setText("Gain(S,Humidity)");
	                final TextView formulatext = (TextView) dialog.findViewById(R.id.formulatext);
	                formulatext.setText(R.string.h1);
	                formulatext.setVisibility(View.INVISIBLE);
	                dialog.show();
	                
	                formulaimg.setOnClickListener( new View.OnClickListener() {

	    	            @Override
	    	            public void onClick(View v) {
	    	            	heirarimg.setVisibility(View.VISIBLE);
	    	            	heirarimg.startAnimation(animSideDown);
	    	            	formulatext.setVisibility(View.VISIBLE);
	    	            }
	                });
	                
	                windstrong.setOnClickListener( new View.OnClickListener() {

	    	            @Override
	    	            public void onClick(View v) {
	    	            	//show popup
	    	               
	    	            	final Dialog dialog = new Dialog(getActivity());
	    	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    	                dialog.setContentView(R.layout.popup);
	    	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	    	                TextView strongtext = (TextView) dialog.findViewById(R.id.occurence);
	    	                strongtext.setText(R.string.windoccurencesstrong);
	    	                dialog.show();
	    	            }
	                });
	           
	                windweak.setOnClickListener( new View.OnClickListener() {

	    	            @Override
	    	            public void onClick(View v) {
	    	            	
	    	            	final Dialog dialog = new Dialog(getActivity());
	    	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    	                dialog.setContentView(R.layout.popup);
	    	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	    	                TextView weaktext = (TextView) dialog.findViewById(R.id.occurence);
	    	                weaktext.setText(R.string.windoccurencesweak);
	    	                dialog.show();
	    	            }
	                });
	                
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
	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	                dialog.setContentView(R.layout.custom_dialog);
	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	                dialog.setTitle("Gain Wind Formula");
	                dialog.setCancelable(false);
	                
	              
	                left = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	    	                R.anim.left);
	    	        right = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	    	                R.anim.right);
	    	    	// load the animation
	    	      
	    		
	    			final ImageView formulaimg = (ImageView) dialog.findViewById(R.id.formulaimg);
	    			final ImageView heirarimg = (ImageView) dialog.findViewById(R.id.heirarimg);
	    			
	    			formulaimg.setImageResource(R.drawable.winddragimg);
	                heirarimg.setImageResource(R.drawable.windheirar);
	                
	    			animSideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
	    					R.anim.slide_down);
	    			 Button windweak = (Button) dialog.findViewById(R.id.windweak);
		    	     Button windstrong = (Button) dialog.findViewById(R.id.windstrong);
		    	     windweak.setBackgroundResource(R.drawable.weakwind);
		             windstrong.setBackgroundResource(R.drawable.strongwind);
		    	        
	                windweak.startAnimation(left);
	                windstrong.startAnimation(right);
	                
	                TextView entropytitle = (TextView) dialog.findViewById(R.id.entropytitle);
	                entropytitle.setText("Gain(S,Wind)");
	                final TextView formulatext = (TextView) dialog.findViewById(R.id.formulatext);
	                formulatext.setText(R.string.w1);
	                formulatext.setVisibility(View.INVISIBLE);
	                dialog.show();
	                
	                formulaimg.setOnClickListener( new View.OnClickListener() {

	    	            @Override
	    	            public void onClick(View v) {
	    	            	heirarimg.setVisibility(View.VISIBLE);
	    	            	heirarimg.startAnimation(animSideDown);
	    	            	formulatext.setVisibility(View.VISIBLE);
	    	            }
	                });
	                
	                
	                windstrong.setOnClickListener( new View.OnClickListener() {

	    	            @Override
	    	            public void onClick(View v) {
	    	            	//show popup
	    	               
	    	            	final Dialog dialog = new Dialog(getActivity());
	    	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    	                dialog.setContentView(R.layout.popup);
	    	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	    	                TextView strongtext = (TextView) dialog.findViewById(R.id.occurence);
	    	                strongtext.setText(R.string.windoccurencesstrong);
	    	                dialog.show();
	    	            }
	                });
	           
	                windweak.setOnClickListener( new View.OnClickListener() {

	    	            @Override
	    	            public void onClick(View v) {
	    	            	
	    	            	final Dialog dialog = new Dialog(getActivity());
	    	            	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    	                dialog.setContentView(R.layout.popup);
	    	                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	    	                TextView weaktext = (TextView) dialog.findViewById(R.id.occurence);
	    	                weaktext.setText(R.string.windoccurencesweak);
	    	                dialog.show();
	    	            }
	                });
	                
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
	        gainhumid.startAnimation(right);
	        gainwind.startAnimation(left);
	    }
		
		public void out(){
			gainoutlook.startAnimation(leftout);
			gainhumid.startAnimation(rightout);
			gainwind.startAnimation(leftout);
	      
	        gainoutlook.postDelayed(new Runnable() {
	            @Override
	            public void run() {
	                in();
	            }
	        }, 1000);
	    }
		 @Override
			public void onAnimationEnd(Animation animation) {
				// Take any action after completing the animation

				// check for zoom in animation
				if (animation == animSideDown) {			
				}

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}
		
}
