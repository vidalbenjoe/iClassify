package com.capstoneii.iclassify.assessment.bayesian;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import bayesdiscussflip.NativeBayesLayoutActivity;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class BayesianAssessmentActivity extends ActionBarActivity {
	Button checkdropbt;
	int numDragged = 0;
	// text views being dragged and dropped onto
	private TextView option1, option2, option3, choice1, choice2, choice3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assessmentbayesian_layout);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		final TypewriterTextView animated_title = (TypewriterTextView) findViewById(R.id.animatedtitle);
		animated_title.setTypewriterText(getString(R.string.intro));

		checkdropbt = (Button) findViewById(R.id.checkdropbt);
		checkdropbt.setEnabled(false);
		// get both sets of text views

		// views to drag
		option1 = (TextView) findViewById(R.id.option_1);
		option2 = (TextView) findViewById(R.id.option_2);
		option3 = (TextView) findViewById(R.id.option_3);

		// views to drop onto
		choice1 = (TextView) findViewById(R.id.choice_1);
		choice2 = (TextView) findViewById(R.id.choice_2);
		choice3 = (TextView) findViewById(R.id.choice_3);

		// set touch listeners
		option1.setOnTouchListener(new ChoiceTouchListener());
		option2.setOnTouchListener(new ChoiceTouchListener());
		option3.setOnTouchListener(new ChoiceTouchListener());

		// set drag listeners
		choice1.setOnDragListener(new ChoiceDragListener());
		choice2.setOnDragListener(new ChoiceDragListener());
		choice3.setOnDragListener(new ChoiceDragListener());

		checkdropbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				// CheckDrop();
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

				if (view.getId() == R.id.option_1 && v.getId() != R.id.choice_1) {
					// display error if user drop it on the wrong target
					Toast.makeText(BayesianAssessmentActivity.this,
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
					numDragged++;

				}
				if (view.getId() == R.id.option_1 && v.getId() == R.id.choice_1) {
					dropTarget.setText("Step 1 " + dropped.getText());
					Toast.makeText(BayesianAssessmentActivity.this, "Correct!",
							Toast.LENGTH_SHORT).show();
					numDragged++;
				}

				if (view.getId() == R.id.option_2 && v.getId() != R.id.choice_2) {
					// display error if user drop it on the wrong target
					Toast.makeText(BayesianAssessmentActivity.this,
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
					numDragged++;

				}

				else if (view.getId() == R.id.option_2
						&& v.getId() == R.id.choice_2) {
					dropTarget.setText("Step 2 " + dropped.getText());
					Toast.makeText(BayesianAssessmentActivity.this, "Correct!",
							Toast.LENGTH_SHORT).show();
					numDragged++;
				}

				if (view.getId() == R.id.option_3 && v.getId() != R.id.choice_3) {
					// display error if user drop it on the wrong target
					Toast.makeText(BayesianAssessmentActivity.this,
							"Sorry, you dropped it on the wrong place",
							Toast.LENGTH_SHORT).show();
					numDragged++;
				}

				else if (view.getId() == R.id.option_3
						&& v.getId() == R.id.choice_3) {
					dropTarget.setText("Step 3 " + dropped.getText());
					Toast.makeText(BayesianAssessmentActivity.this, "Correct!",
							Toast.LENGTH_SHORT).show();
					numDragged++;
				}

				if (numDragged >= 3) {
					numDragged = 0;

					Toast.makeText(BayesianAssessmentActivity.this,
							"All buttons in the Right place",
							Toast.LENGTH_SHORT).show();
					checkdropbt.setEnabled(true);

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
			Intent intent = new Intent(this, NativeBayesLayoutActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			// app icon in action bar clicked; go home
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/*
	 * public void CheckDrop(){
	 * 
	 * Toast.makeText(DecisionTreeAssessmentActivity.this,
	 * "Sorry, you dropped it on the wrong place", Toast.LENGTH_SHORT) .show();
	 * }
	 */
}