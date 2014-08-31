package com.capstoneii.iclassify.assessment.knn;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;

@SuppressLint("NewApi")
public class KnnAssessmentActivity extends ActionBarActivity {
	Button checkdropbt;
	int numDragged = 0;
	//text views being dragged and dropped onto
	private TextView option7, option8, option9,option10,option11,
	choice7, choice8, choice9, choice10, choice11;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assessmentknn_layout);
		 getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.divider_color)));
		 getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		 
		final TypewriterTextView animated_title = (TypewriterTextView)findViewById(R.id.animatedtitle);
		animated_title.setTypewriterText(getString(R.string.intro));
		
		checkdropbt = (Button) findViewById(R.id.checkdropbt);
		//checkdropbt.setEnabled(false);
		//get both sets of text views

		//views to drag
		option7 = (TextView)findViewById(R.id.option_7);
		option8 = (TextView)findViewById(R.id.option_8);
		option9 = (TextView)findViewById(R.id.option_9);
		option10 = (TextView)findViewById(R.id.option_10);
		option11 = (TextView)findViewById(R.id.option_11);

		//views to drop onto
		choice7 = (TextView)findViewById(R.id.choice_7);
		choice8 = (TextView)findViewById(R.id.choice_8);
		choice9 = (TextView)findViewById(R.id.choice_9);
		choice9 = (TextView)findViewById(R.id.choice_10);
		choice11 = (TextView)findViewById(R.id.choice_11);

		//set touch listeners
		option7.setOnTouchListener(new ChoiceTouchListener());
		option8.setOnTouchListener(new ChoiceTouchListener());
		option9.setOnTouchListener(new ChoiceTouchListener());
		option10.setOnTouchListener(new ChoiceTouchListener());
		option11.setOnTouchListener(new ChoiceTouchListener());
	
		//set drag listeners
		choice7.setOnDragListener(new ChoiceDragListener());
		choice8.setOnDragListener(new ChoiceDragListener());
		choice9.setOnDragListener(new ChoiceDragListener());
		choice10.setOnDragListener(new ChoiceDragListener());
		choice11.setOnDragListener(new ChoiceDragListener());
			}
	
	private final class ChoiceTouchListener implements OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
				//start dragging the item touched
				view.startDrag(data, shadowBuilder, view, 0);
				return true;
			} else {
				return false;
			}
		}
	} 

	private class ChoiceDragListener implements OnDragListener {
		@Override
		public boolean onDrag(View v, DragEvent event) {
			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				//no action necessary
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				//no action necessary
				break;
			case DragEvent.ACTION_DRAG_EXITED:        
				//no action necessary
				break;
			case DragEvent.ACTION_DROP:
				
				//handle the dragged view being dropped over a drop view
				 View view = (View) event.getLocalState();
				//stop displaying the view where it was before it was dragged
				view.setVisibility(View.INVISIBLE);
				//view dragged item is being dropped on
				TextView dropTarget = (TextView) v;
				//view being dragged and dropped
				TextView dropped = (TextView) view;
				//update the text in the target view to reflect the data being dropped
				dropTarget.setText(dropped.getText());
				//make it bold to highlight the fact that an item has been dropped
				dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
				//if an item has already been dropped here, there will be a tag
				Object tag = dropTarget.getTag();
				//if there is already an item here, set it back visible in its original place
				if(tag!=null)
				{
					//the tag is the view id already dropped here
					int existingID = (Integer)tag;
					//set the original view visible again
					findViewById(existingID).setVisibility(View.VISIBLE);
				}
				//set the tag in the target view being dropped on - to the ID of the view being dropped
				dropTarget.setTag(dropped.getId());
		

				break;
			case DragEvent.ACTION_DRAG_ENDED:
				//no action necessary
				break;
			default:
				break;
			}
			return true;
		}
	} 
	
		}
	
	
