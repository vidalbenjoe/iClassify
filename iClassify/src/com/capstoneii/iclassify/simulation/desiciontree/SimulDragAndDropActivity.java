package com.capstoneii.iclassify.simulation.desiciontree;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.assessment.decisionid3.DecisionTreeAssessmentActivity;

@SuppressLint("NewApi")
public class SimulDragAndDropActivity extends Fragment {

	Button drag1, drag2, drag3, drag4, invisibleoutlook;
	ImageView droptoroot;
	TextView text, sucess;
	EditText getcorrect;
	int numDragged = 0;
	int total, failure = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.drag_n_drop_layout,
				container, false);
		drag1 = (Button) rootView.findViewById(R.id.one);
		drag2 = (Button) rootView.findViewById(R.id.two);
		drag3 = (Button) rootView.findViewById(R.id.three);
		drag4 = (Button) rootView.findViewById(R.id.four);
		invisibleoutlook = (Button) rootView.findViewById(R.id.outlookinvisible);

		drag1.setOnTouchListener(new ChoiceTouchListener());
		drag2.setOnTouchListener(new ChoiceTouchListener());
		drag3.setOnTouchListener(new ChoiceTouchListener());
		drag4.setOnTouchListener(new ChoiceTouchListener());
		droptoroot = (ImageView) rootView.findViewById(R.id.dragheretoroot);
		text = (TextView) rootView.findViewById(R.id.Total);
		sucess = (TextView) rootView.findViewById(R.id.Sucess);
		
		droptoroot.setOnDragListener(new ChoiceDragListener());
		
		/*droptoroot.setOnDragListener(new View.OnDragListener() {
			@Override
			public boolean onDrag(View v, DragEvent event) {

				final int action = event.getAction();
				switch (action) {
				case DragEvent.ACTION_DRAG_STARTED:
					drag1.setVisibility(View.VISIBLE);
					break;
				case DragEvent.ACTION_DRAG_EXITED:

					break;
				case DragEvent.ACTION_DRAG_ENTERED:

					break;
				case DragEvent.ACTION_DROP: {
					failure = failure + 1;
					return (true);
				}
				case DragEvent.ACTION_DRAG_ENDED: {
					// http://android-er.blogspot.com/2012/04/drag-and-drop-to-copy-items-between.html

					total = total + 1;
					int suc = 0 + total;
					
					 * sucess.setText("Sucessful Drops :"+suc);
					 * text.setText("Total Drops: "+total);
					 

					if (suc == 1) {
						v.post(new Runnable() {
							public void run() {
								invisibleoutlook.setVisibility(View.VISIBLE);
								droptoroot.setVisibility(View.INVISIBLE);
							}
						});

						final Dialog dialog = new Dialog(getActivity());
						dialog.setContentView(R.layout.dialog_simul_correct_root);
						dialog.setTitle("Correct!");
						dialog.setCancelable(false);
						dialog.show();

						Button declineButton = (Button) dialog
								.findViewById(R.id.cadbtnOk);

						declineButton.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {

								String ans;
								getcorrect = (EditText) dialog
										.findViewById(R.id.gethumidityands);
								ans = getcorrect.getText().toString();

								if (ans.matches("Humidity")) {
									Fragment SimulDecisionResults = new SimulDecisionResults();
									FragmentTransaction ft = getFragmentManager()
											.beginTransaction();
									ft.replace(R.id.frame_container,
											SimulDecisionResults);
									ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
									ft.addToBackStack(SimulDecisionResults
											.getTag());
									ft.commit();
									dialog.dismiss();
								}

								if (ans.contains("Humidity")) {
									Fragment SimulDecisionResults = new SimulDecisionResults();
									FragmentTransaction ft = getFragmentManager()
											.beginTransaction();
									ft.replace(R.id.frame_container,
											SimulDecisionResults);
									ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
									ft.addToBackStack(SimulDecisionResults
											.getTag());
									ft.commit();
									dialog.dismiss();
								}
								if (ans.contains("humidity")) {
									Fragment SimulDecisionResults = new SimulDecisionResults();
									FragmentTransaction ft = getFragmentManager()
											.beginTransaction();
									ft.replace(R.id.frame_container,
											SimulDecisionResults);
									ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
									ft.addToBackStack(SimulDecisionResults
											.getTag());
									ft.commit();
									dialog.dismiss();
								}

								if (ans.matches("humidity")) {
									Fragment SimulDecisionResults = new SimulDecisionResults();
									FragmentTransaction ft = getFragmentManager()
											.beginTransaction();
									ft.replace(R.id.frame_container,
											SimulDecisionResults);
									ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
									ft.addToBackStack(SimulDecisionResults
											.getTag());
									ft.commit();
									dialog.dismiss();
								}

								if (ans.matches("Temperature")) {
									Toast.makeText(
											getActivity()
													.getApplicationContext(),
											"Sorry your Answers is wrong, please study the algorithm",
											Toast.LENGTH_LONG).show();
								}

								if (ans.matches("temperature")) {
									Toast.makeText(
											getActivity()
													.getApplicationContext(),
											"Sorry your Answers is wrong, please study the algorithm",
											Toast.LENGTH_LONG).show();
								}

								if (ans.matches("Wind")) {
									Toast.makeText(
											getActivity()
													.getApplicationContext(),
											"Sorry your Answers is wrong, please study the algorithm",
											Toast.LENGTH_LONG).show();
								}

								if (ans.matches("wind")) {
									Toast.makeText(
											getActivity()
													.getApplicationContext(),
											"Sorry your Answers is wrong, please study the algorithm",
											Toast.LENGTH_LONG).show();
								}
								if (ans.matches("")) {
									Toast.makeText(
											getActivity()
													.getApplicationContext(),
											"Please put your answer in the textbox",
											Toast.LENGTH_LONG).show();
								}
								// this will gonna be tha last activity..
								
								 * The decision tree can also be expressed in
								 * rule format:
								 * 
								 * IF outlook = sunny AND humidity = high THEN
								 * playball = no
								 * 
								 * IF outlook = rain AND humidity = high THEN
								 * playball = no
								 * 
								 * IF outlook = rain AND wind = strong THEN
								 * playball = yes
								 * 
								 * IF outlook = overcast THEN playball = yes
								 * 
								 * IF outlook = rain AND wind = weak THEN
								 * playball = yes
								 
							}
						});
					}
					return (true);
				}
				default:
					break;
				}
				return true;
			}
		});*/

		return rootView;
	}
	
	
	@SuppressLint("ClickableViewAccessibility") private final class ChoiceTouchListener implements OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				/*
				 * Drag details: we only need default behavior - clip data could
				 * be set to pass data as part of drag - shadow can be tailored
				 */
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
						view);
				// start dragging the item touched
				view.startDrag(data, shadowBuilder, view, 0);
				return true;
			} else {
				return false;
			}
		}
	}

	
	public class ChoiceDragListener implements OnDragListener {
		// http://stackoverflow.com/questions/21567086/on-drag-listener-multiple-if-statements
		@Override
		public boolean onDrag(final View v, DragEvent event) {
			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				// no action necessary
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				// no action necessary
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				// no action necessary
				break;
			case DragEvent.ACTION_DROP:

				// handle the dragged view being dropped over a drop view
				final View view = (View) event.getLocalState();
				// stop displaying the view where it was before it was dragged
				view.setVisibility(View.INVISIBLE);
				// view dragged item is being dropped on
				ImageView dropTarget = (ImageView) v;
				// view being dragged and dropped
				Button dropped = (Button) view;
				// update the text in the target view to reflect the data being
				// dropped
				
				// make it bold to highlight the fact that an item has been
				// dropped
			
				// if an item has already been dropped here, there will be a tag
				Object tag = dropTarget.getTag();
				// if there is already an item here, set it back visible in its
				// original place
				if (tag != null) {
					// the tag is the view id already dropped here
					int existingID = (Integer) tag;
					// set the original view visible again
					//findViewById(existingID).setVisibility(View.VISIBLE);
				}
				// set the tag in the target view being dropped on - to the ID
				// of the view being dropped
				dropTarget.setTag(dropped.getId());

				
				if (view.getId() == R.id.one && v.getId() == R.id.dragheretoroot) {
					//dropTarget.setText("Step 1 " + dropped.getText());
				Toast.makeText(getActivity(),
							"Correct!", Toast.LENGTH_SHORT).show();
				
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.dialog_simul_correct_root);
				dialog.setTitle("Correct!");
				dialog.setCancelable(false);
				dialog.show();

				Button declineButton = (Button) dialog
						.findViewById(R.id.cadbtnOk);

				declineButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						String ans;
						getcorrect = (EditText) dialog
								.findViewById(R.id.gethumidityands);
						ans = getcorrect.getText().toString();

						if (ans.matches("Humidity")) {
							Fragment SimulDecisionResults = new SimulDecisionResults();
							FragmentTransaction ft = getFragmentManager()
									.beginTransaction();
							ft.replace(R.id.frame_container,
									SimulDecisionResults);
							ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
							ft.addToBackStack(SimulDecisionResults
									.getTag());
							ft.commit();
							dialog.dismiss();
						}

						if (ans.contains("Humidity")) {
							Fragment SimulDecisionResults = new SimulDecisionResults();
							FragmentTransaction ft = getFragmentManager()
									.beginTransaction();
							ft.replace(R.id.frame_container,
									SimulDecisionResults);
							ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
							ft.addToBackStack(SimulDecisionResults
									.getTag());
							ft.commit();
							dialog.dismiss();
						}
						if (ans.contains("humidity")) {
							Fragment SimulDecisionResults = new SimulDecisionResults();
							FragmentTransaction ft = getFragmentManager()
									.beginTransaction();
							ft.replace(R.id.frame_container,
									SimulDecisionResults);
							ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
							ft.addToBackStack(SimulDecisionResults
									.getTag());
							ft.commit();
							dialog.dismiss();
						}

						if (ans.matches("humidity")) {
							Fragment SimulDecisionResults = new SimulDecisionResults();
							FragmentTransaction ft = getFragmentManager()
									.beginTransaction();
							ft.replace(R.id.frame_container,
									SimulDecisionResults);
							ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
							ft.addToBackStack(SimulDecisionResults
									.getTag());
							ft.commit();
							dialog.dismiss();
						}

						if (ans.matches("Temperature")) {
							Toast.makeText(
									getActivity()
											.getApplicationContext(),
									"Sorry your Answers is wrong, please study the algorithm",
									Toast.LENGTH_LONG).show();
						}

						if (ans.matches("temperature")) {
							Toast.makeText(
									getActivity()
											.getApplicationContext(),
									"Sorry your Answers is wrong, please study the algorithm",
									Toast.LENGTH_LONG).show();
						}

						if (ans.matches("Wind")) {
							Toast.makeText(
									getActivity()
											.getApplicationContext(),
									"Sorry your Answers is wrong, please study the algorithm",
									Toast.LENGTH_LONG).show();
						}

						if (ans.matches("wind")) {
							Toast.makeText(
									getActivity()
											.getApplicationContext(),
									"Sorry your Answers is wrong, please study the algorithm",
									Toast.LENGTH_LONG).show();
						}
						if (ans.matches("")) {
							Toast.makeText(
									getActivity()
											.getApplicationContext(),
									"Please put your answer in the textbox",
									Toast.LENGTH_LONG).show();
						}
						// this will gonna be tha last activity..
						/*
						 * The decision tree can also be expressed in
						 * rule format:
						 * 
						 * IF outlook = sunny AND humidity = high THEN
						 * playball = no
						 * 
						 * IF outlook = rain AND humidity = high THEN
						 * playball = no
						 * 
						 * IF outlook = rain AND wind = strong THEN
						 * playball = yes
						 * 
						 * IF outlook = overcast THEN playball = yes
						 * 
						 * IF outlook = rain AND wind = weak THEN
						 * playball = yes
						 */
					}
				});
					
				}
				if (view.getId() == R.id.one && v.getId() != R.id.dragheretoroot) {
					// display error if user drop it on the wrong target
					Toast.makeText(getActivity(),
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
					
					final Dialog dialog = new Dialog(getActivity());
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.correct_dialog);
					dialog.setCancelable(false);
					dialog.getWindow().setBackgroundDrawable(
							new ColorDrawable(
									android.graphics.Color.TRANSPARENT));
					ImageView correctcheck = (ImageView) dialog
							.findViewById(R.id.correctcheck);
					correctcheck.setImageResource(R.drawable.wrongcircle);
					correctcheck
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View InputFragmentView) {
									dialog.dismiss();
								}
							});

					dialog.show();
				}
				if (view.getId() == R.id.two && v.getId() == R.id.dragheretoroot) {
					// display error if user drop it on the wrong target
					Toast.makeText(getActivity(),
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
					
					final Dialog dialog = new Dialog(getActivity());
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.correct_dialog);
					dialog.setCancelable(false);
					dialog.getWindow().setBackgroundDrawable(
							new ColorDrawable(
									android.graphics.Color.TRANSPARENT));
					ImageView correctcheck = (ImageView) dialog
							.findViewById(R.id.correctcheck);
					correctcheck.setImageResource(R.drawable.wrongcircle);
					correctcheck
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View InputFragmentView) {
									dialog.dismiss();
								}
							});

					dialog.show();
				}

				else if (view.getId() == R.id.three && v.getId() == R.id.dragheretoroot) {
					Toast.makeText(getActivity(),
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
					
					final Dialog dialog = new Dialog(getActivity());
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.correct_dialog);
					dialog.setCancelable(false);
					dialog.getWindow().setBackgroundDrawable(
							new ColorDrawable(
									android.graphics.Color.TRANSPARENT));
					ImageView correctcheck = (ImageView) dialog
							.findViewById(R.id.correctcheck);
					correctcheck.setImageResource(R.drawable.wrongcircle);
					correctcheck
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View InputFragmentView) {
									dialog.dismiss();
								}
							});

					dialog.show();
				}
				if (view.getId() == R.id.four && v.getId() != R.id.dragheretoroot) {
					// display error if user drop it on the wrong target
					Toast.makeText(getActivity(),
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
					
					final Dialog dialog = new Dialog(getActivity());
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.correct_dialog);
					dialog.setCancelable(false);
					dialog.getWindow().setBackgroundDrawable(
							new ColorDrawable(
									android.graphics.Color.TRANSPARENT));
					ImageView correctcheck = (ImageView) dialog
							.findViewById(R.id.correctcheck);
					correctcheck.setImageResource(R.drawable.wrongcircle);
					correctcheck
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View InputFragmentView) {
									dialog.dismiss();
								}
							});

					dialog.show();
					
				} 
				

				break;
			case DragEvent.ACTION_DRAG_ENDED:
				// no action necessary
				break;
			default:
				break;
			}
			return true;
		}
	}
	
	
}
