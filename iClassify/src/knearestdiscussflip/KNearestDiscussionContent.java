package knearestdiscussflip;

import java.util.Locale;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.aphidmobile.utils.UI;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.JustifyTextView;

import drawer.MainDrawerActivity;

public class KNearestDiscussionContent extends ActionBarActivity {

	int counter;
	Button previousdiscussbt,nextdiscussbt;
	ToggleButton toggleButton;
	TextView title;
	ImageView photo;
	JustifyTextView description;
	public TextToSpeech tts;
	String toSpeak;
	private Matrix matrix = new Matrix();
	private float scale = 1f;
	private ScaleGestureDetector SGD;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.discusstopic_layout);
		SGD = new ScaleGestureDetector(this,new ScaleListener());
		
		tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				if (status != TextToSpeech.ERROR) {
					tts.setLanguage(Locale.US);
				}
			}
		});
		
		
		
		
		title = (TextView) findViewById(R.id.title);
		photo = (ImageView) findViewById(R.id.photo);
		description = (JustifyTextView) findViewById(R.id.description);
		
		
		nextdiscussbt = (Button) findViewById(R.id.nextdiscussbt);
		
		title.setText("K Nearest Neighbors - Classification");
		description.setText(R.string.discussionknnzero);
		
		previousdiscussbt = (Button) findViewById(R.id.previousdiscussbt);
		toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
		toggleButton.setOnCheckedChangeListener(
				new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(
							CompoundButton buttonView, boolean isChecked) {

						if (isChecked) {
							toggleButton.setChecked(true);	
							toSpeak = description.getText().toString();
							tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
						}else{
							if(tts!=null){
								tts.stop();
							}
						}
						
					}
					
				});
		
		nextdiscussbt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (v == nextdiscussbt) {
					counter++;
					toggleButton.setChecked(false);	
					if(tts!=null){
						tts.stop();
					}else{
						tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
					}
				}
				
				if(counter > 5){
					
				}else{
				switch(counter){
				
				case 0:
					title.setText("K Nearest Neighbors - Classification");
					description.setText(R.string.discussionknnzero);
					break;
				
				case 1:
					title.setText("Algorithm");
					photo.setImageResource(R.drawable.knnimg1);
					description.setText(R.string.discussionknnone);
					previousdiscussbt.setVisibility(View.VISIBLE);
					
					break;
				case 2:
					title.setText("");
					photo.setImageResource(R.drawable.knnimg2);
					description.setText(R.string.discussionknntwo);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 3:
					title.setText("");
					photo.destroyDrawingCache();
					description.setText(R.string.discussionknnthree);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 4:
					title.setText("Example:");
					photo.setImageResource(R.drawable.knnimg3);
					description.setText(R.string.discussionknnfour);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 5:
					title.setText("Step 1: ");
					photo.setImageResource(R.drawable.knnimg4);
					description.setText(R.string.discussionknnfive);
					previousdiscussbt.setVisibility(View.VISIBLE);
					nextdiscussbt.setVisibility(View.INVISIBLE);
					break;
					
					default:
						break;
				
				}
				}
				
			}
		});
		
		previousdiscussbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v == previousdiscussbt) {
					counter--;
					if(tts!=null){
						tts.stop();
					}else{
						tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
					}
				}
				
				
				switch(counter){
				case 5:
					title.setText("Step 1: ");
					photo.setImageResource(R.drawable.knnimg4);
					description.setText(R.string.discussionknnfive);
					previousdiscussbt.setVisibility(View.VISIBLE);
					nextdiscussbt.setVisibility(View.INVISIBLE);
					
				case 4:
					title.setText("Example:");
					photo.setImageResource(R.drawable.knnimg3);
					description.setText(R.string.discussionknnfour);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 3:
					title.setText("");
					photo.destroyDrawingCache();
					description.setText(R.string.discussionknnthree);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 2:
					title.setText("");
					photo.setImageResource(R.drawable.knnimg2);
					description.setText(R.string.discussionknntwo);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					

				case 1:
					title.setText("Algorithm");
					photo.setImageResource(R.drawable.knnimg1);
					description.setText(R.string.discussionknnone);
					previousdiscussbt.setVisibility(View.VISIBLE);
				case 0:
					title.setText("K Nearest Neighbors - Classification");
					description.setText(R.string.discussionknnzero);
					previousdiscussbt.setVisibility(View.INVISIBLE);
					break;
					default:
						break;
				}
				
			
			}
		});
		
	}
	
	 public boolean onOptionsItemSelected(MenuItem item) { 
		    switch (item.getItemId()) {
		  case android.R.id.home:
			  Intent intent2 = new Intent(this, MainDrawerActivity.class);
		      intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		      startActivity(intent2);
		      this.finish();
		  // app icon in action bar clicked; go home
		  return true;
		  
		    }
		    return true;
	 }
	
	
	public boolean onTouchEvent(MotionEvent ev) {
		SGD.onTouchEvent(ev);
		return true;
	}

	private class ScaleListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			scale *= detector.getScaleFactor();
			scale = Math.max(0.1f, Math.min(scale, 5.0f));
			matrix.setScale(scale, scale);
			photo.setImageMatrix(matrix);
			return true;
		}
	}
	
}
