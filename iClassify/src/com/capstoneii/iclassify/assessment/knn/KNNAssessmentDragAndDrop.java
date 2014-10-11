package com.capstoneii.iclassify.assessment.knn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SessionCache;
import com.capstoneii.iclassify.SplashScreenActivity;
import com.capstoneii.iclassify.dbclasses.DBAdapter;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class KNNAssessmentDragAndDrop extends ActionBarActivity {
	Button checkdropbt, clearbt;
	int numDragged = 0;
	// text views being dragged and dropped onto
	private TextView option1, option2, option3, option4, option5, choice1,
			choice2, choice3, choice4, choice5;
	private static String TAG = SplashScreenActivity.class.getName();
	private static long SLEEP_TIME = 1; // Sleep for some time
	DBAdapter myDb;
	SessionCache QuizSession;
	
	int retake;
	int prevTotal;
	int curTotal;
	String finalDate;
	Intent intent;
	String initVal = "1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assessment_knn_drag);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		intent = new Intent();
		QuizSession = new SessionCache(KNNAssessmentDragAndDrop.this);
		openDB();
		
		Date date = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
	    finalDate = timeFormat.format(date);
		
		final TypewriterTextView animated_title = (TypewriterTextView) findViewById(R.id.animatedtitle);
		animated_title.setTypewriterText(getString(R.string.intro));

		checkdropbt = (Button) findViewById(R.id.checkdropbt);
		clearbt = (Button) findViewById(R.id.clearbt);
		checkdropbt.setVisibility(View.INVISIBLE);
		// get both sets of text views

		// views to drag
		option1 = (TextView) findViewById(R.id.option_1);
		option2 = (TextView) findViewById(R.id.option_2);
		option3 = (TextView) findViewById(R.id.option_3);
		option4 = (TextView) findViewById(R.id.option_4);
		option5 = (TextView) findViewById(R.id.option_5);

		option1.setText(R.string.knnoption_7);
		option2.setText(R.string.knnoption_8);
		option3.setText(R.string.knnoption_9);
		option4.setText(R.string.knnoption_10);
		option5.setText(R.string.knnoption_11);

		// views to drop onto
		choice1 = (TextView) findViewById(R.id.choice_1);
		choice2 = (TextView) findViewById(R.id.choice_2);
		choice3 = (TextView) findViewById(R.id.choice_3);
		choice4 = (TextView) findViewById(R.id.choice_4);
		choice5 = (TextView) findViewById(R.id.choice_5);

		// set touch listeners
		option1.setOnTouchListener(new ChoiceTouchListener());
		option2.setOnTouchListener(new ChoiceTouchListener());
		option3.setOnTouchListener(new ChoiceTouchListener());
		option4.setOnTouchListener(new ChoiceTouchListener());
		option5.setOnTouchListener(new ChoiceTouchListener());

		// set drag listeners
		choice1.setOnDragListener(new ChoiceDragListener());
		choice2.setOnDragListener(new ChoiceDragListener());
		choice3.setOnDragListener(new ChoiceDragListener());
		choice4.setOnDragListener(new ChoiceDragListener());
		choice5.setOnDragListener(new ChoiceDragListener());

		clearbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {

				Intent intent = new Intent(KNNAssessmentDragAndDrop.this,
						KNNAssessmentDragAndDrop.class);
				KNNAssessmentDragAndDrop.this.startActivity(intent);
				KNNAssessmentDragAndDrop.this.finish();
			}
		});

	}

	/**
	 * ChoiceTouchListener will handle touch events on draggable views
	 * 
	 */
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

	/**
	 * DragListener will handle dragged views being dropped on the drop area -
	 * only the drop action will have processing added to it as we are not -
	 * amending the default behavior for other parts of the drag process
	 * 
	 */
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
				TextView dropTarget = (TextView) v;
				// view being dragged and dropped
				TextView dropped = (TextView) view;
				// update the text in the target view to reflect the data being
				// dropped
				dropTarget.setText(dropped.getText());
				// make it bold to highlight the fact that an item has been
				// dropped
				dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
				// if an item has already been dropped here, there will be a tag
				Object tag = dropTarget.getTag();
				// if there is already an item here, set it back visible in its
				// original place
				if (tag != null) {
					// the tag is the view id already dropped here
					int existingID = (Integer) tag;
					// set the original view visible again
					findViewById(existingID).setVisibility(View.VISIBLE);
				}
				// set the tag in the target view being dropped on - to the ID
				// of the view being dropped
				dropTarget.setTag(dropped.getId());

				if (view.getId() == R.id.option_1 && v.getId() != R.id.choice_1) {
					// display error if user drop it on the wrong target
					Toast.makeText(KNNAssessmentDragAndDrop.this,
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
				}

				if (view.getId() == R.id.option_1 && v.getId() == R.id.choice_1) {
					dropTarget.setText("Step 1 " + dropped.getText());
					Toast.makeText(KNNAssessmentDragAndDrop.this, "Correct!",
							Toast.LENGTH_SHORT).show();
					numDragged++;
				}

				if (view.getId() == R.id.option_2 && v.getId() != R.id.choice_2) {
					// display error if user drop it on the wrong target
					Toast.makeText(KNNAssessmentDragAndDrop.this,
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
				}

				else if (view.getId() == R.id.option_2
						&& v.getId() == R.id.choice_2) {
					dropTarget.setText("Step 2 " + dropped.getText());
					Toast.makeText(KNNAssessmentDragAndDrop.this, "Correct!",
							Toast.LENGTH_SHORT).show();

					numDragged++;
				}
				if (view.getId() == R.id.option_3 && v.getId() != R.id.choice_3) {
					// display error if user drop it on the wrong target
					Toast.makeText(KNNAssessmentDragAndDrop.this,
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
				} else if (view.getId() == R.id.option_3
						&& v.getId() == R.id.choice_3) {
					dropTarget.setText("Step 3 " + dropped.getText());
					Toast.makeText(KNNAssessmentDragAndDrop.this, "Correct!",
							Toast.LENGTH_SHORT).show();
					numDragged++;

				}

				if (view.getId() == R.id.option_4 && v.getId() != R.id.choice_4) {
					// display error if user drop it on the wrong target
					Toast.makeText(KNNAssessmentDragAndDrop.this,
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
				} else if (view.getId() == R.id.option_4
						&& v.getId() == R.id.choice_4) {
					dropTarget.setText("Step 4 " + dropped.getText());
					Toast.makeText(KNNAssessmentDragAndDrop.this, "Correct!",
							Toast.LENGTH_SHORT).show();
					numDragged++;

				}

				if (view.getId() == R.id.option_5 && v.getId() != R.id.choice_5) {
					// display error if user drop it on the wrong target
					Toast.makeText(KNNAssessmentDragAndDrop.this,
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
				} else if (view.getId() == R.id.option_5
						&& v.getId() == R.id.choice_5) {
					dropTarget.setText("Step 5 " + dropped.getText());
					Toast.makeText(KNNAssessmentDragAndDrop.this, "Correct!",
							Toast.LENGTH_SHORT).show();
					numDragged++;

				}

				if (numDragged >= 5) {
					if ((view.getId() == R.id.option_1 && v.getId() == R.id.choice_1)
							|| (view.getId() == R.id.option_2 && v.getId() == R.id.choice_2)
							|| (view.getId() == R.id.option_3 && v.getId() == R.id.choice_3)
							|| (view.getId() == R.id.option_4 && v.getId() == R.id.choice_4)
							|| (view.getId() == R.id.option_5 && v.getId() == R.id.choice_5)) {
						checkdropbt.setVisibility(View.VISIBLE);
						CheckDrop();
					}

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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			super.onBackPressed();
			// app icon in action bar clicked; go home
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void CheckDrop() {
		checkdropbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {

				IntentLauncher launcher = new IntentLauncher();
				launcher.start();
			}
		});
	}

	private class IntentLauncher extends Thread {
		@Override
		/**
		 * Sleep for some time and than start new activity.
		 */
		public void run() {
			try {
				// Sleeping

				Thread.sleep(SLEEP_TIME * 1000);

			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			
			if(QuizSession.hasFlQuiz1()){
				
				final Dialog dialog = new Dialog(KNNAssessmentDragAndDrop.this, R.style.DialogAnim);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.validate_message);
				
				Button bYes = (Button) dialog.findViewById(R.id.buttonOk);
				Button bNo = (Button) dialog.findViewById(R.id.buttonCancel);
				TextView tvalertmessage = (TextView) dialog.findViewById(R.id.tvalertmessage);
				
				HashMap<String, String> quizRecord = QuizSession.getTotalSum();
				retake = Integer.parseInt(quizRecord.get(SessionCache.REPEATING1));
				prevTotal = Integer.parseInt(quizRecord.get(SessionCache.JS_MAX_ITEM1));
				
				if(retake == 3){
					tvalertmessage.setText("You have taken this 3 times, Do you want to take this quiz? the first try you have taken will overwrite");
					bYes.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
								
						//delete the record
							myDb.deleteQuiz("Flash Chapter 1");
							//store last quiz session for JS and for all the records
							QuizSession.StoreFlLastQuizTaken(finalDate);
							QuizSession.StoreAllLastQuizTaken(finalDate);
							//delete the scorerow if the user wants to overwrite the first take of quiz
							myDb.deletescorerowSet(1,"Flash 1");
							
							int sum = retake + 1;
							myDb.addjsquiz(1,"Flash Chapter 1", "","0 %");
							QuizSession.FinishSessionNum1(Integer.toString(sum));
							
							intent = new Intent(KNNAssessmentDragAndDrop.this, KNNRandomQuiz.class);
							intent.putExtra("retakeNum", sum);
							startActivity(intent);
							dialog.dismiss();
							KNNAssessmentDragAndDrop.this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
							KNNAssessmentDragAndDrop.this.finish();
						
					}
				});
					
					bNo.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
								dialog.dismiss();	
							}
						});
									
						dialog.show();
					
					
					
				}else{
					//this condition will use if retake is value 1 to 2
					myDb.deleteQuiz("Flash Chapter 1");
					QuizSession.StoreFlLastQuizTaken(finalDate);
					QuizSession.StoreAllLastQuizTaken(finalDate);
					
					int sum = retake + 1;
					myDb.addjsquiz(1,"Flash Chapter 1", "","0 %");
					
					curTotal = prevTotal + 10;
					QuizSession.StoreTotal1(Integer.toString(curTotal));									
					QuizSession.FinishSessionNum1(Integer.toString(sum));
					intent = new Intent(KNNAssessmentDragAndDrop.this, KNNRandomQuiz.class);
					intent.putExtra("retakeNum", sum);
					startActivity(intent);
					KNNAssessmentDragAndDrop.this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
					KNNAssessmentDragAndDrop.this.finish();
		}
			}
				
				else{
					QuizSession.StoreFlLastQuizTaken(finalDate);
					QuizSession.StoreAllLastQuizTaken(finalDate);
					
					int passVal = Integer.parseInt(initVal);
					myDb.addjsquiz(1,"Flash Chapter 1", initVal,"0 %");
					
					curTotal = prevTotal + 10;
					QuizSession.StoreTotal1(Integer.toString(curTotal));
					QuizSession.FinishSessionNum1(initVal);
					intent = new Intent(KNNAssessmentDragAndDrop.this, KNNRandomQuiz.class);
					intent.putExtra("retakeNum", passVal);
					startActivity(intent);
					KNNAssessmentDragAndDrop.this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
					KNNAssessmentDragAndDrop.this.finish();

			}
				
			}
		}

	
	private void openDB() {
		
		myDb = new DBAdapter(KNNAssessmentDragAndDrop.this);
		myDb.open();
	}
}
