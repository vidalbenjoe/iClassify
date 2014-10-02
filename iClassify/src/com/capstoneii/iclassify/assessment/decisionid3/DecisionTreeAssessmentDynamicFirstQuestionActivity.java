package com.capstoneii.iclassify.assessment.decisionid3;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SplashScreenActivity;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class DecisionTreeAssessmentDynamicFirstQuestionActivity extends
		ActionBarActivity {
	Button checkdropbt;
	int numDragged = 0;
	// text views being dragged and dropped onto
	private TextView option1, option2, option3, option4, choice1;
	private static String TAG = SplashScreenActivity.class.getName();
	private static long SLEEP_TIME = 1; // Sleep for some time
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assessmentdecision_tree_layout);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		final TypewriterTextView animated_title = (TypewriterTextView) findViewById(R.id.animatedtitle);
		animated_title
				.setTypewriterText("What to do this weekend based on the tree? ");

		ImageView heirartreeimg = (ImageView) findViewById(R.id.heirartreeimg);
		heirartreeimg.setImageResource(R.drawable.decisiontreequestion1img);

		checkdropbt = (Button) findViewById(R.id.checkdropbt);
		checkdropbt.setEnabled(false);
		
		
		
		// get both sets of text views

		// views to drag
		option1 = (TextView) findViewById(R.id.option_1);
		option2 = (TextView) findViewById(R.id.option_2);
		option3 = (TextView) findViewById(R.id.option_3);
		option4 = (TextView) findViewById(R.id.option_4);

		// views to drop onto
		choice1 = (TextView) findViewById(R.id.choice_1);
		// set touch listeners
		option1.setOnTouchListener(new ChoiceTouchListener());
		option2.setOnTouchListener(new ChoiceTouchListener());
		option3.setOnTouchListener(new ChoiceTouchListener());
		option4.setOnTouchListener(new ChoiceTouchListener());

		// set drag listeners
		choice1.setOnDragListener(new ChoiceDragListener());

		checkdropbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				new CheckDroped().execute();
			}
		});
	}
	/**
	 * ChoiceTouchListener will handle touch events on draggable views
	 * 
	 */
	private final class ChoiceTouchListener implements OnTouchListener {
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
	private class ChoiceDragListener implements OnDragListener {
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

				if (view.getId() == R.id.option_1 && v.getId() == R.id.choice_1) {
					// display error if user drop it on the wrong target
					Toast.makeText(
							DecisionTreeAssessmentDynamicFirstQuestionActivity.this,
							"Correct!", Toast.LENGTH_SHORT).show();
					checkdropbt.setEnabled(true);
				}
				if (view.getId() == R.id.option_2 && v.getId() == R.id.choice_1) {
					// display error if user drop it on the wrong target
					Toast.makeText(
							DecisionTreeAssessmentDynamicFirstQuestionActivity.this,
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
					checkdropbt.setEnabled(false);

				}
				if (view.getId() == R.id.option_3 && v.getId() == R.id.choice_1) {
					// display error if user drop it on the wrong target
					Toast.makeText(
							DecisionTreeAssessmentDynamicFirstQuestionActivity.this,
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
					checkdropbt.setEnabled(false);
				}

				if (view.getId() == R.id.option_4 && v.getId() == R.id.choice_1) {
					// display error if user drop it on the wrong target
					Toast.makeText(
							DecisionTreeAssessmentDynamicFirstQuestionActivity.this,
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
					checkdropbt.setEnabled(false);
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

/*	public void CheckDrop() {
		new CheckDroped().execute();

	}*/

	private class CheckDroped extends AsyncTask<Void, Void, Void> {
		private ProgressDialog progress = null;

		@Override
		protected Void doInBackground(Void... params) {
			try {

				Thread.sleep(1000);
			} catch (Exception e) {

				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onPreExecute() {
			// start the progress dialog
			progress = ProgressDialog.show(
					DecisionTreeAssessmentDynamicFirstQuestionActivity.this,
					null, "Checking...");
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.setIndeterminate(true);
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			progress.dismiss();
			NextAssess();
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}
	}

	public void NextAssess() {
		//http://www.java2s.com/Open-Source/Java_Open_Source_App/Game/Puzzle.htm
		//http://www.java2s.com/Open-Source/Android_Free_Code/Game/List_of_Free_code_Game.htm
		
		/*Toast.makeText(DecisionTreeAssessmentDynamicFirstQuestionActivity.this,
				R.string.assessmenttetobjectivesid3, Toast.LENGTH_SHORT).show();*/
		IntentLauncher launcher = new IntentLauncher();
		launcher.start();
	}
	
	
	private class IntentLauncher extends Thread {
		@Override
		/**
		 * Sleep for some time and than start new activity.
		 */
		public void run() {
			try {
				// Sleeping

				Thread.sleep(SLEEP_TIME * 800);

			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			Intent intent = new Intent(DecisionTreeAssessmentDynamicFirstQuestionActivity.this,
					DecisionTreeAssessmentJumbleWord.class);
			DecisionTreeAssessmentDynamicFirstQuestionActivity.this.startActivity(intent);
			DecisionTreeAssessmentDynamicFirstQuestionActivity.this.finish();
			
		}

	}


}