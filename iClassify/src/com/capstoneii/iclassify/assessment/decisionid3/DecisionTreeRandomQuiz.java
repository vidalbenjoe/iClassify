package com.capstoneii.iclassify.assessment.decisionid3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.capstoneii.iclassify.QuizResultActivity;
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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DecisionTreeRandomQuiz extends Activity {

	List<Question> quesList;
	int score;
	int page = 1;
	int qid = 0;
	int qset = 4;
	String ncourse = "Flash";
	Question question;
	SQLiteDatabase mdb;
	TextView tvQue, tvPage, tvRef;
	RadioButton rd1, rd2, rd3, rd4;
	Button bnext;
	SessionCache QuizSession;
	DBAdapter myDb;
	private Date date;
	String finalDate;

	int totalsumof;
	int sumOf;
	double jsper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_quiz);

		QuizSession = new SessionCache(getApplicationContext());
		openDB();

		myDb.delAllTempRows();
		quesList = myDb.getAllQuestions4();
		if (quesList.size() == 0) {
			addQuestions4();
			quesList = myDb.getAllQuestions4();
		} else {
			Log.d("database not empty", "queslist with setno");
		}

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
			private Date date;

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
					;
					String quserans = answer.getText().toString();
					myDb.addtempQuestion(new TempQuestion("4", lid, qitem,
							qans, quserans));
					score++;
					Log.d("score result", "Your score is " + score);
				} else {
					String lid = tvRef.getText().toString();
					String qitem = tvQue.getText().toString();
					String qans = question.getQans().toString();
					String quserans = answer.getText().toString();
					myDb.addtempQuestion(new TempQuestion("4", lid, qitem,
							qans, quserans));
					Log.d("wrong answer", answer.getText() + " is incorrect");
				}

				if (qid < 10) {
					question = quesList.get(qid);
					setQuestionView();
					grp.clearCheck();
					tvPage.setText(page + "");
					bnext.setEnabled(false);
					bnext.setVisibility(View.GONE);
				} else {
					Intent in = getIntent();
					int retake = in.getExtras().getInt("retakeNum");

					date = new Date();
					SimpleDateFormat timeFormat = new SimpleDateFormat(
							"MMM dd, yyyy");
					String finalDate = timeFormat.format(date);
					String subj = ncourse + " " + qset;
					String qdetails = "Quiz Retake " + retake;
					myDb.addscores(3, retake, subj, qdetails, score, finalDate);
					// myDb.addScores(3, subj , score, finalDate);
					// myDb.deleteQuiz("Flash Chapter 4");

					Intent intent = new Intent(DecisionTreeRandomQuiz.this,
							QuizResultActivity.class);
					Bundle b = new Bundle();
					b.putInt("qno", qset);
					b.putInt("score", score);
					b.putString("course", ncourse);// Your score
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
				myDb.deleteQuiz("Flash Chapter");

				Intent intent = new Intent(DecisionTreeRandomQuiz.this,
						QuizResultActivity.class);
				Bundle b = new Bundle();
				b.putInt("qno", qset);
				b.putInt("score", score);
				b.putString("course", ncourse);// Your score
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

	public void addQuestions4() {
		myDb.addQuestions4(new Question(
				"0",
				"It is a flow-chart-like tree structure, where each node denotes a test on an attribute value, each branch represents an outcome of the test, and tree leaves represent classes or class distributions. ",
				"Decision Tree", "Classification", "Decision Tree",
				"Decision Tree Classifier", "Naive Bayesian")); // 1
		// 2

		myDb.addQuestions4(new Question(
				"2",
				"It constructs a flowchart like structure where each internal nonleaf node denotes a test on an attribute, each branch corresponds to an outcome of the test, and each external leaf node denotes a class prediction.",
				"Decision Tree Induction", "Decision Tree Networks",
				"Decision Tree Deduction", "Decision Tree Induction",
				"Decision Tree Classification")); // 1
		// 4
		myDb.addQuestions4(new Question(
				"32",
				"It is the learning of decision trees from class-labeled training tuples.",
				"Decision Tree Induction", "Decision Tree Deduction",
				"Decision Tree Induction", "Deduction", "Induction")); // 21
		// 5
		myDb.addQuestions4(new Question(
				"4",
				"It does not require any domain knowledge or parameter setting, and therefore is appropriate for exploratory knowledge discovery.",
				"Decision Tree Classifiers ", "Decision Tree Classifiers ",
				"Decision Tree", "Decision Tree Classification",
				"Classification")); // 4
		// 6
		myDb.addQuestions4(new Question(
				"4",
				"It attempts to identify and remove such branches, with the goal of improving classification accuracy on unseen data. ",
				"Tree Pruning", "Tree Pruning", "Tree Planning",
				" Tree Induction", "Tree Prepruning")); // 4
		// 7

		myDb.addQuestions4(new Question(
				"6",
				"It is the number of instances gets smaller as you traverse down the tree.",
				"Data Fragmentation", "Data Scanning", "Data Classification",
				"Data Segmentation", "Data Fragmentation"));// 7
		// 9
		myDb.addQuestions4(new Question(
				"17",
				"This approach considers the cost complexity of a tree to be a function of the number of leaves in the tree and the error rate of the tree. ",
				"CART", "PART", "SORT", "CASE", "CART"));// 18
		// 10
		myDb.addQuestions4(new Question(
				"36",
				"It is an approach that a tree is “pruned” by halting its construction early.",
				"Prepruning ", "Postpruning", "Decision Tree ", "Prepruning ",
				"Classification")); // 23
		// 11
		myDb.addQuestions4(new Question(
				"9",
				"It is an approach which removes subtrees from a “fully grown” tree",
				"Postpruning", "Decision Tree", "Postpruning",
				"Classification", "Prepruning")); // 7
		// 12
		myDb.addQuestions4(new Question(
				"0",
				"Decision tree can be seen as rules for performing a _________ .",
				"Categorisation", "Categorisation", "Organization",
				"Preparation", "Selection")); // 1
		// 13
		myDb.addQuestions4(new Question("4",
				"ID3 uses a measure called _________", "Information Gain",
				"Data Gain", "Term Gain", "Information Gain", "Gain")); // 4
		// 14
		myDb.addQuestions4(new Question("32",
				"Node with the __________ information gain is chosen",
				"Highest", "Lowest", "Largest", "Smallest", "Highest"));// 21
		// 15
		myDb.addQuestions4(new Question(
				"19",
				"In decision tree learning, ID3 is an algorithm invented by __________",
				"Ross Quinlan", "Rose Quinlan", "Ross Quinlan", "Rod Quinlan ",
				"Ruth Quinlan")); // 13

	}
}