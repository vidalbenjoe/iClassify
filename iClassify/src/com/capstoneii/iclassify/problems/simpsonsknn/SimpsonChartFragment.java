package com.capstoneii.iclassify.problems.simpsonsknn;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;
import com.capstoneii.iclassify.problems.ChooseProblemActivity;

public class SimpsonChartFragment extends Fragment {
	ImageView simpsonschartimage,knnnextbt;
	Animation zoomIn,zoomOut;
	int counter = 0;
	
	 Matrix matrix = new Matrix();
	 Matrix savedMatrix = new Matrix();
	 PointF startPoint = new PointF();
	 PointF midPoint = new PointF();
	 float oldDist = 1f;
	 static final int NONE = 0;
	 static final int DRAG = 1;
	 static final int ZOOM = 2;
	 int mode = NONE;


	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	        @SuppressLint({ "ClickableViewAccessibility", "FloatMath" }) public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
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
			 
			 simpsonschartimage.setOnTouchListener(new View.OnTouchListener() {

				   @Override
				   public boolean onTouch(View v, MotionEvent event) {

				    ImageView view = (ImageView) v;
				    System.out.println("matrix=" + savedMatrix.toString());
				    switch (event.getAction() & MotionEvent.ACTION_MASK) {
				    case MotionEvent.ACTION_DOWN:

				     savedMatrix.set(matrix);
				     startPoint.set(event.getX(), event.getY());
				     mode = DRAG;
				     break;

				    case MotionEvent.ACTION_POINTER_DOWN:

				     oldDist = spacing(event);

				     if (oldDist > 10f) {
				      savedMatrix.set(matrix);
				      midPoint(midPoint, event);
				      mode = ZOOM;
				     }
				     break;

				    case MotionEvent.ACTION_UP:

				    case MotionEvent.ACTION_POINTER_UP:
				     mode = NONE;

				     break;

				    case MotionEvent.ACTION_MOVE:
				     if (mode == DRAG) {
				      matrix.set(savedMatrix);
				      matrix.postTranslate(event.getX() - startPoint.x,
				        event.getY() - startPoint.y);
				     } else if (mode == ZOOM) {
				      float newDist = spacing(event);
				      if (newDist > 10f) {
				       matrix.set(savedMatrix);
				       float scale = newDist / oldDist;
				       matrix.postScale(scale, scale, midPoint.x, midPoint.y);
				      }
				     }
				     break;

				    }
				    view.setImageMatrix(matrix);

				    return true;
				   }

				
				   private float spacing(MotionEvent event) {
				    float x = event.getX(0) - event.getX(1);
				    float y = event.getY(0) - event.getY(1);
				    return FloatMath.sqrt(x * x + y * y);
				   }

				   private void midPoint(PointF point, MotionEvent event) {
				    float x = event.getX(0) + event.getX(1);
				    float y = event.getY(0) + event.getY(1);
				    point.set(x / 2, y / 2);
				   }
				  });

			 
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
		            		
		            	case 3:

		            		final Dialog dialog = new Dialog(getActivity());
		    				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		    				dialog.setContentView(R.layout.custom_dialog_text);
		    				dialog.setCancelable(false);
		    				dialog.getWindow().setBackgroundDrawable(
		    						new ColorDrawable(android.graphics.Color.TRANSPARENT));

		    				final TypewriterTextView customtextindialog = (TypewriterTextView) dialog
		    						.findViewById(R.id.customtextindialog);
		    				customtextindialog.setText(R.string.whyknn);
		    				customtextindialog.setMovementMethod(new ScrollingMovementMethod());

		    				ImageView cadbtnNext = (ImageView) dialog.findViewById(R.id.cadbtnNext);
		    				cadbtnNext.setImageResource(R.drawable.backtomainmenu);

		    				cadbtnNext.setOnClickListener(new View.OnClickListener() {
		    					@Override
		    					public void onClick(View InputFragmentView) {
		    						// next
		    						Fragment ChooseProblemActivity = new ChooseProblemActivity();
		    						FragmentTransaction ft = getFragmentManager().beginTransaction();
		    						ft.replace(R.id.frame_container,ChooseProblemActivity);
		    						ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		    						ft.addToBackStack(ChooseProblemActivity.getTag());
		    						ft.commit();
		    						dialog.dismiss();
		    					}
		    				});
		    				dialog.show();

		            		
		            		break;

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
