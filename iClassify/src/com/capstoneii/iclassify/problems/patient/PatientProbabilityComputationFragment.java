package com.capstoneii.iclassify.problems.patient;

import android.app.Dialog;
import android.app.Fragment;
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
import com.capstoneii.iclassify.library.SecretTextView;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class PatientProbabilityComputationFragment extends Fragment {
	
	ImageView computationprobability,patientnextbt;
	Animation animation;
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
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.patient_probability_layout,
				container, false);
		animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.unzoom_in);
		 
		final SecretTextView patientprobatext = (SecretTextView) rootView.findViewById(R.id.patientprobatext);
		patientprobatext.setText(R.string.patientcomputestatement1);
		patientprobatext.setmDuration(1800);
		patientprobatext.setIsVisible(false);
		patientprobatext.toggle();
        
		
		computationprobability = (ImageView) rootView.findViewById(R.id.computationprobability);
		computationprobability.setImageResource(R.drawable.probablookup1);
		computationprobability.startAnimation(animation);
		computationprobability.setOnTouchListener(new View.OnTouchListener() {

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
		patientnextbt = (ImageView) rootView.findViewById(R.id.patientnextbt);
		patientnextbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				if (InputFragmentView == patientnextbt) {
					counter++;
				}
				switch(counter){
				case 1:
					computationprobability.setImageResource(R.drawable.probablookup2);
					computationprobability.startAnimation(animation);
					patientprobatext.setText(R.string.patientcomputestatement2);
					patientprobatext.setmDuration(1800);
					patientprobatext.setIsVisible(false);
					patientprobatext.toggle();
					
					computationprobability.setOnTouchListener(new View.OnTouchListener() {

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
					
					
					break;
				case 2:
					computationprobability.setImageResource(R.drawable.probabcomputation);
					computationprobability.startAnimation(animation);
					patientprobatext.setText(R.string.patientcomputestatement3);
					break;
					
				case 3:
					computationprobability.setImageResource(R.drawable.probabcomputation2);
					computationprobability.startAnimation(animation);
					patientprobatext.setText(R.string.patientcomputestatement4);
					break;
					
				case 4:
					computationprobability.setImageResource(R.drawable.patiensolutionresults);
					computationprobability.startAnimation(animation);
					patientprobatext.setText(R.string.patientcomputestatement5);
					break;
				case 5:
					patientprobatext.setText(R.string.patientcomputestatement6);
					computationprobability.setImageResource(R.drawable.patientdividevalue);
					computationprobability.startAnimation(animation);
					break;
				case 6:
					final Dialog dialog = new Dialog(getActivity());
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog_text);
					dialog.setCancelable(false);
					dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
					
					
					final TypewriterTextView customtextindialog = (TypewriterTextView)dialog.findViewById(R.id.customtextindialog);
					customtextindialog.setText(R.string.probabio);
					customtextindialog.setMovementMethod(new ScrollingMovementMethod());
					
					
					ImageView cadbtnNext = (ImageView) dialog.findViewById(R.id.cadbtnNext);
					cadbtnNext.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View InputFragmentView) {
							//next
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
}
