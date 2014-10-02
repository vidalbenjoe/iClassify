package com.capstoneii.iclassify.assessment.bayesian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import bayesdiscussflip.NativeBayesLayoutActivity;

import com.capstoneii.iclassify.Question;
import com.capstoneii.iclassify.QuizResultActivity;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.assessment.knn.DBHelperKnn;
import com.capstoneii.iclassify.assessment.knn.KnnAssessmentActivity;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class BayesianAssessmentActivity extends ActionBarActivity {
	List<Question> quesList;
	int score = 0;
	Question currentQ;
	TypewriterTextView txtQuestion;
	RadioButton rda, rdb, rdc;
	Button butNext;
	Random randomQuestion;
	int qid = randomQuestion.nextInt(11);
	boolean fill;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_multiple_question_layout);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		//getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		DBHelperNaive db = new DBHelperNaive(this);
		quesList = db.getAllQuestions();
		currentQ = quesList.get(qid);
		txtQuestion = (TypewriterTextView) findViewById(R.id.textView1);
		rda = (RadioButton) findViewById(R.id.radio0);
		rdb = (RadioButton) findViewById(R.id.radio1);
		rdc = (RadioButton) findViewById(R.id.radio2);
		butNext = (Button) findViewById(R.id.button1);
		
		setQuestionView();
		butNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
				RadioButton answer = (RadioButton) findViewById(grp
						.getCheckedRadioButtonId());
				Log.d("yourans", currentQ.getANSWER() + " " + answer.getText());
				if (currentQ.getANSWER().equals(answer.getText())) {
					score++;
					Log.d("score", "Your score" + score);
				}
			
				if (qid < 10) {
					currentQ = quesList.get(qid);
					setQuestionView();   
				
			} else {
				Intent intent = new Intent(BayesianAssessmentActivity.this,
						QuizResultActivity.class);
				Bundle b = new Bundle();
				b.putInt("score", score); // Your score
				intent.putExtras(b); // Put your score to your next Intent
				startActivity(intent);
				finish();
				
				}
	          
			}
		});
	}

	private void setQuestionView() {
		txtQuestion.setText(currentQ.getQUESTION());
		rda.setText(currentQ.getOPTA());
		rdb.setText(currentQ.getOPTB());
		rdc.setText(currentQ.getOPTC());
		qid++;
		
	}

	public void onBackPressed() {

	}

}