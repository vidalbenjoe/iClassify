package com.capstoneii.iclassify.assessment.decisionid3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.capstoneii.iclassify.Question;
import com.capstoneii.iclassify.QuizResultActivity;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;

public class DecisionTreeQuestionSets extends ActionBarActivity {
	List<Question> quesList;
	int score = 0;
	Question currentQ;
	TypewriterTextView txtQuestion;
	RadioButton rda, rdb, rdc;
	Button butNext;
	Random randomQuestion;
	String ncourse = "Decision";
	int qid = 0;
	int qset = 1;
	private Date date;
	String finalDate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_multiple_question_layout);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		//getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		final DBHelperIdTree db = new DBHelperIdTree(this);
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
				
				if (qid < quesList.size()) {
						currentQ = quesList.get(qid);
						setQuestionView();   
						date = new Date();
						SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
					    finalDate = timeFormat.format(date);
				} else {
					Intent intent = new Intent(DecisionTreeQuestionSets.this,
							QuizResultActivity.class);
					
					/*<!--ADDED->*/
					Intent in = getIntent();
					int retake = in.getExtras().getInt("retakeNum");
					
					date = new Date();
					SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
				    finalDate = timeFormat.format(date);
				    String subj = ncourse +" "+qset;
				    String qdetails = "Quiz Retake "+retake;
				    db.addscores(3, retake, subj, qdetails, score, finalDate);
				    //ncourse String ncourse = "Decision";
				    
				    ///add the
					Bundle b = new Bundle();
					b.putInt("score", score); // Your score
					intent.putExtras(b); // Put your score to your next Intent
					startActivity(intent);
					finish();
					db.close();
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
