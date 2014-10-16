package com.capstoneii.iclassify.assessment.knn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.capstoneii.iclassify.QuizResultActivity;
import com.capstoneii.iclassify.QuizResultBayesian;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SessionCache;
import com.capstoneii.iclassify.assessment.bayesian.BayesianRandomQuiz;
import com.capstoneii.iclassify.assessment.decisionid3.DecisionTreeRandomQuiz;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class KNNRandomQuiz extends Activity {
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
		
		instructionDialog();
		QuizSession = new SessionCache(getApplicationContext());
		openDB();

		myDb.delAllTempRows();
		quesList = myDb.getAllQuestions1();
		if (quesList.size() == 0) {
			addQuestions1();
			quesList = myDb.getAllQuestions1();
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

					myDb.deleteQuiz("K Nearest Neighbor");
					Intent intent = new Intent(KNNRandomQuiz.this,
							QuizResultActivity.class);
					Bundle b = new Bundle();
					b.putInt("qno", qset);
					b.putInt("score", score);
					b.putString("course", ncourse);// Your score
					b.putString("quizdetails", qdetails);
					intent.putExtras(b);
					startActivity(intent);
					KNNRandomQuiz.this.finish();
					closeDB();
				}
			}
		});
	}

	private void setQuestionView() {
		tvRef.setText(question.getLid());
		tvQue.setText(question.getQitem());
		
		String refNumber = tvRef.getText().toString();
		int refInt = Integer.parseInt(refNumber);
		
		if(refInt <= 3){
			quizlin.setBackgroundResource(R.drawable.decisiontreequestionimage);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
			
		}else if(refInt >= 4 || refInt <= 9){
			quizlin.setBackgroundResource(R.drawable.decisiontreequestionimageoutlook);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
		}else if(refInt == 10){
			quizlin.setVisibility(View.GONE);
		}
		else{
			quizlin.setVisibility(View.GONE);
		}
		
		
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

		final Dialog dialog = new Dialog(KNNRandomQuiz.this,
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

				myDb.deleteQuiz("K Nearest Neighbor");

				Intent intent = new Intent(KNNRandomQuiz.this,
						QuizResultActivity.class);
				Bundle b = new Bundle();
				b.putInt("qno", qset);
				b.putInt("score", score);
				b.putString("course", ncourse);// Your score
				b.putString("quizdetails", qdetails);
				intent.putExtras(b);
				startActivity(intent);
				KNNRandomQuiz.this.finish();
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


	public void addQuestions1() {

		myDb.addQuestions1(new Question("1",
				"It was a method first described in the early 1950’s. ",
				"K-Nearest-Neighbor Method", "Decision Tree", "Classification",
				"Naive Bayesian", "K-Nearest-Neighbor Method")); // 1
		// 2
		myDb.addQuestions1(new Question(
				"4",
				"This are based on learning by analogy, that is, by comparing a given test tuple with training tuples that are similar to it.",
				"Nearest-Neighbor Classifiers", "Nearest-Neighbor Classifiers",
				"Decision Tree Deduction", "Decision Tree Classification",
				"Decision Tree Induction")); // 3
		// 3
		myDb.addQuestions1(new Question(
				"5",
				"It is the task of predicting continuous (or ordered) values for given input.",
				"Numeric Prediction", "Decision Tree", "Numeric Prediction",
				"Decision Tree Classification", "Naive Bayesian")); // 4
		// 4
		myDb.addQuestions1(new Question(
				"13",
				"It has since been widely used in the area of pattern recognition",
				"K-Nearest-Neighbor Method", "K-Nearest-Neighbor Method",
				"Decision Tree Classification", "Naive Bayesian",
				"Decision Tree")); // 11
		// 5
		myDb.addQuestions1(new Question(
				"10",
				"K-Nearest-Neighbour Method is labor intensive when given large training sets, and did not gain popularity until the _______ when increased computing power became available.",
				"1960s", "1960s", "1970s", "1980s", "1990s")); // 9
		// 6
		myDb.addQuestions1(new Question(
				"2",
				"For k-nearest-neighbor classification, the unknown tuple is assigned the most common class among its k nearest neighbors.",
				"True", "It depends", "maybe", "false", "True")); // 2
		// 7
		myDb.addQuestions1(new Question(
				"10",
				"Nearest-neighbor classifiers can be extremely fast when classifying test tuples.",
				"False", "False", "True", "Maybe", "It Depends"));// 9
		// 8
		myDb.addQuestions1(new Question(
				"22",
				"It use distance-based comparisons that intrinsically assign equal weight to each attribute. ",
				"Nearest-Neighbor Classifiers", "Nearest-Neighbor Classifiers",
				"Decision Tree Classification", "Decision Tree Deduction",
				"Decision Tree Induction"));// 20
		// 9
		myDb.addQuestions1(new Question(
				"12",
				"A method to speed up classification time that compute the distance based on a subset of the n attributes.",
				"Partial Distance Method", "Nearest-Neighbor Classifiers",
				"Partial Distance Method", "Decision Tree Induction",
				"Decision Tree"));// 10
		// 10
		myDb.addQuestions1(new Question(
				"12",
				"A method to speed up classification time that removes training tuples that prove useless.",
				"Editing Method", "Editing Method", "Partial Method",
				"General Method", "Changing Method")); // 10
		// 11
		myDb.addQuestions1(new Question(
				"13",
				"A commonly used distance metric for continuous variables is ____________",
				"Euclidean distance", "Hamming distance", "Nearest distance",
				"Euclidean distance", "Bayesian Distance")); // 11
		// 12
		myDb.addQuestions1(new Question(
				"9",
				"For discrete variables, such as for text classification, another metric can be used, such as the overlap metric",
				"Hamming distance", "Euclidean distance", "Hamming distance",
				"Nearest distance", "Bayesian Distance")); // 8
		// 13
		myDb.addQuestions1(new Question("4",
				"If k is too ______, sensitive to noise points", "Small",
				"High", "Small", "Low", "Large")); // 3
		// 14
		myDb.addQuestions1(new Question(
				"1",
				"If k is too ______, neighborhood may include points from other classes",
				"Large", "Small", "Low", "Large", "High"));// 1
		// 15
		myDb.addQuestions1(new Question("13",
				"k-NN classifiers are __________", "Lazy Learners",
				"Expensive Learners", "Eager Learners", "Lazy Learners",
				"Classification Learners")); // 11

	}
	public void instructionDialog(){
		final Dialog dialog = new Dialog(KNNRandomQuiz.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_assessment_instruction);
		dialog.setCancelable(true);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(
						android.graphics.Color.TRANSPARENT));

		ImageView insokbt = (ImageView) dialog
				.findViewById(R.id.insokbt);
		insokbt.setImageResource(R.drawable.backtomainmenu);
		insokbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				// next
				
				dialog.dismiss();
			}
		});
		dialog.show();
	}
}