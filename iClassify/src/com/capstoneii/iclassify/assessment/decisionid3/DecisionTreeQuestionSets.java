package com.capstoneii.iclassify.assessment.decisionid3;

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
	int qid = 0;
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
					
				} else {
					Intent intent = new Intent(DecisionTreeQuestionSets.this,
							QuizResultActivity.class);
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
