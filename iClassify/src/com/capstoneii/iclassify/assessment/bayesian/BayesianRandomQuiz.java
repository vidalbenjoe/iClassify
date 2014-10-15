package com.capstoneii.iclassify.assessment.bayesian;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.capstoneii.iclassify.QuizResultActivity;
import com.capstoneii.iclassify.QuizResultBayesian;
import com.capstoneii.iclassify.QuizResultDecision;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SessionCache;
import com.capstoneii.iclassify.assessment.knn.KNNRandomQuiz;
import com.capstoneii.iclassify.dbclasses.DBAdapter;
import com.capstoneii.iclassify.dbclasses.Question;
import com.capstoneii.iclassify.dbclasses.TempQuestion;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BayesianRandomQuiz extends Activity {

	List<Question> quesList;
	int score;
	int page = 1;
	int qid = 0;
	int qset = 1;
	String ncourse = "Naive Bayesian";
	Question question;
	SQLiteDatabase mdb;
	TextView tvQue, tvPage, tvRef;
	RadioButton rd1, rd2, rd3, rd4;
	Button bnext;
	SessionCache QuizSession;
	DBAdapter myDb;
	private Date date;
	String finalDate;

	LinearLayout quizlin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_quiz);

		getActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		QuizSession = new SessionCache(getApplicationContext());
		openDB();

		myDb.delAllTempRows();
		quesList = myDb.getAllQuestions2();
		if (quesList.size() == 0) {
			addQuestions2();
			quesList = myDb.getAllQuestions2();
		} else {
			Log.d("database not empty", "queslist with setno");
		}

		quizlin = (LinearLayout) findViewById(R.id.quizlin);

		question = quesList.get(qid);
		tvRef = (TextView) findViewById(R.id.tvRef);
		tvQue = (TextView) findViewById(R.id.tvQuestion);
		tvPage = (TextView) findViewById(R.id.tvPage);
		rd1 = (RadioButton) findViewById(R.id.rd1);
		rd2 = (RadioButton) findViewById(R.id.rd2);
		rd3 = (RadioButton) findViewById(R.id.rd3);
		rd4 = (RadioButton) findViewById(R.id.rd4);
		bnext = (Button) findViewById(R.id.bnext);
		
		
		bnext.setEnabled(false);
		setQuestionView();
		tvPage.setText(page + "");
		Log.d("score result", "Randomized Num " + tvRef.getText().toString());
		rd1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bnext.setEnabled(true);
			}
		});

		rd2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bnext.setEnabled(true);
			}
		});

		rd3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bnext.setEnabled(true);
			}
		});

		rd4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bnext.setEnabled(true);
			}
		});

		bnext.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("SimpleDateFormat")
			@Override
			public void onClick(View v) {
				RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
				RadioButton answer = (RadioButton) findViewById(grp
						.getCheckedRadioButtonId());
				grp.clearCheck();
				page++;
				
				if (question.getQans().equals(answer.getText())) {
					String lid = tvRef.getText().toString();
					String qitem = tvQue.getText().toString();
					String qans = question.getQans().toString();
					String quserans = answer.getText().toString();
					myDb.addtempQuestion(new TempQuestion("1", lid, qitem,
							qans, quserans));
					
					
					
					score++;
					Log.d("score result", "Your score is " + score);
				} else {
					String lid = tvRef.getText().toString();
					String qitem = tvQue.getText().toString();
					String qans = question.getQans().toString();
					String quserans = answer.getText().toString();
					myDb.addtempQuestion(new TempQuestion("1", lid, qitem,
							qans, quserans));
					
					
					Log.d("wrong answer", answer.getText() + " is incorrect");
				}
				if (qid < 10) {

					question = quesList.get(qid);
					setQuestionView();
					grp.clearCheck();
					tvPage.setText(page + "");
					bnext.setEnabled(false);

				} else {
					Intent in = getIntent();
					int retake = in.getExtras().getInt("retakeNum");
					date = new Date();
					SimpleDateFormat timeFormat = new SimpleDateFormat(
							"MMM dd, yyyy");
					finalDate = timeFormat.format(date);
					String subj = ncourse + " " + qset;
					String qdetails = "Quiz Retake " + retake;
					myDb.addscores(3, retake, subj, qdetails, score, finalDate);
					// myDb.addScores(3, subj , score, finalDate);

					myDb.deleteQuiz("Naive Bayesian");
					Intent intent = new Intent(BayesianRandomQuiz.this,
							QuizResultBayesian.class);
					Bundle b = new Bundle();
					b.putInt("qno", qset);
					b.putInt("score", score);
					b.putString("course", ncourse);// Your score
					b.putString("quizdetails", qdetails);
					intent.putExtras(b);
					startActivity(intent);
					BayesianRandomQuiz.this.finish();
					closeDB();
				}
			}
		});
	}

	private void setQuestionView() {
		tvRef.setText(question.getLid());
		tvQue.setText(question.getQitem());

		rd1.setText(question.getOpta());
		rd2.setText(question.getOptb());
		rd3.setText(question.getOptc());
		rd4.setText(question.getOptd());
		
		if (tvRef.getText().toString().equals("2")) {
			quizlin.setBackgroundResource(R.drawable.naivebayesquestionimage);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
		}
		else if (tvRef.getText().toString().equals("12")) {
			quizlin.setBackgroundResource(R.drawable.naivebayesquestionimage);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
			
		}else if (tvRef.getText().toString().equals("6")) {
			quizlin.setBackgroundResource(R.drawable.naivebayesquestionimage);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
			
		}else if (tvRef.getText().toString().equals("7")) {
			quizlin.setBackgroundResource(R.drawable.naivebayesquestionimage);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
			
		}
		
		
		
		else{
			quizlin.setVisibility(View.GONE);
		}
		
		
		qid++;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void openDB() {

		myDb = new DBAdapter(this);
		myDb.open();
	}

	private void closeDB() {
		myDb.close();
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public void onBackPressed() {

		final Dialog dialog = new Dialog(BayesianRandomQuiz.this,
				R.style.DialogAnim);

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.validate_message);

		date = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
		finalDate = timeFormat.format(date);

		Button bOk = (Button) dialog.findViewById(R.id.buttonOk);
		Button bCancel = (Button) dialog.findViewById(R.id.buttonCancel);
		TextView question = (TextView) dialog.findViewById(R.id.tvalertmessage);

		question.setText("Are you sure? You want to Exit?");

		bOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent in = getIntent();
				int retake = in.getExtras().getInt("retakeNum");

				String subj = ncourse + " " + qset;
				String qdetails = "Quiz Retake " + retake;
				myDb.addscores(3, retake, subj, qdetails, score, finalDate);

				myDb.deleteQuiz("Naive Bayesian");

				Intent intent = new Intent(BayesianRandomQuiz.this,
						QuizResultBayesian.class);
				Bundle b = new Bundle();
				b.putInt("qno", qset);
				b.putInt("score", score);
				b.putString("course", ncourse);// Your score
				b.putString("quizdetails", qdetails);
				intent.putExtras(b);
				startActivity(intent);
				BayesianRandomQuiz.this.finish();
				closeDB();
				dialog.dismiss();
			}
		});

		bCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}

	public void addQuestions2() {

		myDb.addQuestions2(new Question("1",
				"It provides a way of calculating the posterior probability.",
				"Bayes theorem", "Naive Theorem", "Flare", "Bayes theorem",
				"Naive Bayes")); // 1
		// 2
		myDb.addQuestions2(new Question(
				"4",
				"What do you call when the classifier assume that the effect of the value of a predictor on a given class is independent of the values of other predictors?",
				"Conditional independence", "Conditional dependence",
				"Unconditional independence", "Unconditional dependence",
				"Conditional independence")); // 3
		// 3
		myDb.addQuestions2(new Question("5",
				"Based on the decision tree, which one of the following statements is correct?",
				"If Weight greater than 160, classify as Male", "If Weight greater than 160, classify as Female",
				"If Hair Length less than or equal to 2, classify as Female", "If Weight greater than 160, classify as Male",
				"If Hair Length less than 160, classify as Male")); // 4
		// 4
		myDb.addQuestions2(new Question(
				"13",
				"In this process you add 1 to the count for every attribute value-class combination when an attribute value doesn’t occur with every class value",
				"Zero-frequency problem", "Zero-frequency problem",
				"One-Frequency problem", "Frequency problem",
				"Addition-Frequency")); // 11
		// 5
		myDb.addQuestions2(new Question(
				"10",
				"What are the two parameters that define density function for the normal distribution?",
				"Mean and standard deviation", "Mean and frequency",
				"Mean and standard deviation",
				"Frequency and standard deviation", "None of the above")); // 9
		// 6
		myDb.addQuestions2(new Question(
				"2",
				"In the picture is Officer Drew. Is Officer Drew a Male or a Female? \nBased on the table, with names and gender, what is the frequency of the Name DREW as a FEMALE?",
				"2", "4", "1", "2", "3")); // 2
		// 7
		myDb.addQuestions2(new Question(
				"10",
				"____________ information gain as a sum of information contributed by each attribute can offer an explanation on how values of the predictors influence the class probability.",
				"Kononenko’s", "Kononenko’s", "Konenko's", "Koneko's",
				"Konoko's"));// 9
		// 8
		myDb.addQuestions2(new Question(
				"22",
				"Calculate explicit probabilities for hypothesis, among the most practical approaches to certain types of learning problems",
				"Probabilistic learning", "Probabilistic training",
				"Probabilistic learning", "Probabilistic gaining",
				"Probabilistic plotting"));// 20
		// 9
		myDb.addQuestions2(new Question("6",
				"Based on the decision tree, what are the ‘attributes’?",
				"HAIR LENGTH and WEIGHT", "HAIR LENGTH and FEMALE",
				"MALE and WEIGHT", "MALE and FEMALE",
				"HAIR LENGTH and WEIGHT"));// 10
		// 10
		myDb.addQuestions2(new Question(
				"12",
				"In the picture is Officer Drew. Is Officer Drew a Male or a Female? Based on the table",
				"2 out of 8", "3 out of 8",
				"2 out of 8", "4 out of 8",
				"1 out of 8")); // 10
		// 11
		myDb.addQuestions2(new Question(
				"7",
				"Based on the decision tree, are these decision rules correct? \nIf Weight greater than 160, classify as Male\nElseif Hair Length less than or equal to 2, classify as Male\nElse classify as Female",
				"TRUE", "FALSE", "Last Statement is True",
				"TRUE", "First Statement is True")); // 11
		// 12
		myDb.addQuestions2(new Question(
				"9",
				"_____________ is applied to decision making and inferential statistics that deals with probability inference.",
				"Bayesian reasoning", "Probability reasoning",
				"Bayesian reasoning", "Decision reasoning",
				"Inferential reasoning")); // 8
		// 13
		myDb.addQuestions2(new Question(
				"4",
				"It assumes that the presence or absence of a particular feature of a class is unrelated to the presence or absence of any other feature.",
				"Bayes classifier", "Bayes classifier", "Decision Tree",
				"K Nearest Neighbor", "Classification")); // 3
		// 14
		myDb.addQuestions2(new Question("1",
				"Bayesian methods are called __________", "eager learners",
				"lazy learners", "active learners", "eager learners",
				"passive learners"));// 1
		// 15
		myDb.addQuestions2(new Question("14",
				"This theorem is the cornerstone of all Bayesian methods",
				"Bayes Theorem", "Classification theorem", "Bayes Theorem",
				"Probability theorem", "Euclidean theorem")); // 11
	}
}