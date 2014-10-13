package com.capstoneii.iclassify.assessment.decisionid3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.capstoneii.iclassify.QuizResultDecision;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SessionCache;
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

public class DecisionTreeRandomQuiz extends Activity {

	List<Question> quesList;
	int score;
	int page = 1;
	int qid = 0;
	int qset = 1;
	String ncourse = "Decision Tree";
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
		quesList = myDb.getAllQuestions3();
		if (quesList.size() == 0) {
			addQuestions3();
			quesList = myDb.getAllQuestions3();
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

					myDb.deleteQuiz("Decision Tree");

					Intent intent = new Intent(DecisionTreeRandomQuiz.this,
							QuizResultDecision.class);
					Bundle b = new Bundle();
					b.putInt("qno", qset);
					b.putInt("score", score);
					b.putString("course", ncourse);// Your score
					b.putString("quizdetails", qdetails);
					intent.putExtras(b);
					startActivity(intent);
					DecisionTreeRandomQuiz.this.finish();
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
		qid++;
		
		if (tvRef.getText().toString().equals("2")) {
			quizlin.setBackgroundResource(R.drawable.decisiontreequestionimage);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
		}
		else if (tvRef.getText().toString().equals("17")) {
			quizlin.setBackgroundResource(R.drawable.decisiontreequestionimage);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
		}else{
			quizlin.setVisibility(View.GONE);
		}
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

		final Dialog dialog = new Dialog(DecisionTreeRandomQuiz.this,
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

				myDb.deleteQuiz("Decision Tree");

				Intent intent = new Intent(DecisionTreeRandomQuiz.this,
						QuizResultDecision.class);
				Bundle b = new Bundle();
				b.putInt("qno", qset);
				b.putInt("score", score);
				b.putString("course", ncourse);// Your score
				b.putString("quizdetails", qdetails);
				intent.putExtras(b);
				startActivity(intent);
				DecisionTreeRandomQuiz.this.finish();
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

	public void addQuestions3() {
		myDb.addQuestions3(new Question(
				"0",
				"It is a flow-chart-like tree structure, where each node denotes a test on an attribute value, each branch represents an outcome of the test, and tree leaves represent classes or class distributions. ",
				"Decision Tree", "Classification", "Decision Tree",
				"Decision Tree Classifier", "Naive Bayesian")); // 1
		// 2

		myDb.addQuestions3(new Question(
				"2",
				"Based on the decision tree, which one of the following statements is correct?",
				"If Weight greater than 160, classify as Male", "If Weight greater than 160, classify as Male",
				"If Hair Length less than or equal to 2, classify as Female", "If Hair Length less than 160, classify as Male",
				"If Weight greater than 160, classify as Female")); // 1
		// 4
		myDb.addQuestions3(new Question(
				"32",
				"It is the learning of decision trees from class-labeled training tuples.",
				"Decision Tree Induction", "Decision Tree Deduction",
				"Decision Tree Induction", "Deduction", "Induction")); // 21
		// 5
		myDb.addQuestions3(new Question(
				"4",
				"It does not require any domain knowledge or parameter setting, and therefore is appropriate for exploratory knowledge discovery.",
				"Decision Tree Classifiers ", "Decision Tree Classifiers ",
				"Decision Tree", "Decision Tree Classification",
				"Classification")); // 4
		// 6
		myDb.addQuestions3(new Question(
				"4",
				"It attempts to identify and remove such branches, with the goal of improving classification accuracy on unseen data. ",
				"Tree Pruning", "Tree Pruning", "Tree Planning",
				" Tree Induction", "Tree Prepruning")); // 4
		// 7

		myDb.addQuestions3(new Question(
				"6",
				"It is the number of instances gets smaller as you traverse down the tree.",
				"Data Fragmentation", "Data Scanning", "Data Classification",
				"Data Segmentation", "Data Fragmentation"));// 7
		// 9
		myDb.addQuestions3(new Question(
				"17",
				"2.	Based on the decision tree, what could be the gender of this Simpson’s Character:\nOtto has a hair length of 10 inches and a weight of 180 lbs. ",
				"Male", "Female", "Male", "None of these", "Unknown"));// 18
		// 10
		myDb.addQuestions3(new Question(
				"36",
				"It is an approach that a tree is “pruned” by halting its construction early.",
				"Prepruning ", "Postpruning", "Decision Tree ", "Prepruning ",
				"Classification")); // 23
		// 11
		myDb.addQuestions3(new Question(
				"9",
				"It is an approach which removes subtrees from a “fully grown” tree",
				"Postpruning", "Decision Tree", "Postpruning",
				"Classification", "Prepruning")); // 7
		// 12
		myDb.addQuestions3(new Question(
				"0",
				"Decision tree can be seen as rules for performing a _________ .",
				"Categorisation", "Categorisation", "Organization",
				"Preparation", "Selection")); // 1
		// 13
		myDb.addQuestions3(new Question("4",
				"ID3 uses a measure called _________", "Information Gain",
				"Data Gain", "Term Gain", "Information Gain", "Gain")); // 4
		// 14
		myDb.addQuestions3(new Question("32",
				"Node with the __________ information gain is chosen",
				"Highest", "Lowest", "Largest", "Smallest", "Highest"));// 21
		// 15
		myDb.addQuestions3(new Question(
				"19",
				"In decision tree learning, ID3 is an algorithm invented by __________",
				"Ross Quinlan", "Rose Quinlan", "Ross Quinlan", "Rod Quinlan ",
				"Ruth Quinlan")); // 13

	}
}