package com.capstoneii.iclassify;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import drawer.MainDrawerActivity;

public class QuizResultActivity extends ActionBarActivity {
	ImageView resultimage;
	int score;
	int setq;
	String ncourse;
	String qdetails;
	TextView correct;
	TextView wrong;
	TextView mesg;
	Button bscorelog, bqresult;
	int correctans, wrongans;
	
	int totalsumof;
	int sumOf;
	double jsper;
	int retake;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_result_layout);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		Bundle g = getIntent().getExtras();
		setq= g.getInt("qno");
		score = g.getInt("score");
		ncourse = g.getString("course");
		qdetails = g.getString("quizdetails");
		
		wrongans = 10 - score;
		correct.setText( score + "");
		wrong.setText(wrongans + "");
		mesg.setText(score+"/10");
		
		resultimage = (ImageView) findViewById(R.id.resultimage);
		// get rating bar object
		RatingBar bar = (RatingBar) findViewById(R.id.ratingBar1);
		bar.setNumStars(5);
		bar.setStepSize(0.5f);
		// get text view
		TextView t = (TextView) findViewById(R.id.textResult);
		// get score
		Bundle b = getIntent().getExtras();
		int score = b.getInt("score");
		// display score
		bar.setRating(score);
		switch (score) {
		case 1:
			t.setText("Oopsie! Better Luck Next Time!");
		case 2:

			t.setText("Please study more ! :(");
			break;
		case 3:
			t.setText("Practice makes perfect!");
		case 4:
			t.setText("Hmmmm.. Someone's been reading a lot of trivia");

			break;
		case 5:
			t.setText("Who are you? A trivia wizard???");

			break;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, MainDrawerActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			// app icon in action bar clicked; go home
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
