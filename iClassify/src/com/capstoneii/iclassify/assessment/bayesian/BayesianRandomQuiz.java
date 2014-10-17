package com.capstoneii.iclassify.assessment.bayesian;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.capstoneii.iclassify.QuizResultBayesian;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SessionCache;
import com.capstoneii.iclassify.dbclasses.DBAdapter;
import com.capstoneii.iclassify.dbclasses.Question;
import com.capstoneii.iclassify.dbclasses.TempQuestion;

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
	// Dialog object
	TextView instructiondesc, headerte;

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

		String refNumber = tvRef.getText().toString();
		int refInt = Integer.parseInt(refNumber);

		if (refInt <= 5) {
			quizlin.setBackgroundResource(R.drawable.naivebayesquestionimage);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());

		} else if (refInt >= 6) {
			quizlin.setBackgroundResource(R.drawable.naivebayesquestiontable);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
		} else if (refInt >= 11) {
			quizlin.setBackgroundResource(R.drawable.naivebayesquestiondomestic);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
		} else {
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

		// NAIVE BAYESIAN

		myDb.addQuestions2(new Question(
				"1",
				"In the picture is Officer Drew. Is Officer Drew a Male or a Female? Based on the table, with names and gender, what is the frequency of the Name DREW as a FEMALE?",
				"2", "1", "2", "3", "4"));

		myDb.addQuestions2(new Question(
				"2",
				"In the picture is Officer Drew. Is Officer Drew a Male or a Female? Based on the table, how many are the total cases?",
				"8", "2", "4", "6", "8"));

		myDb.addQuestions2(new Question(
				"3",
				"In the picture is Officer Drew. Is Officer Drew a Male or a Female? Based on the table, with names and gender, what is the frequency of the Name DREW as a MALE?",
				"1", "1", "2", "3", "4"));

		myDb.addQuestions2(new Question(
				"4",
				"In the picture is Officer Drew. Is Officer Drew a Male or a Female? Based on the table, with names and gender, what is the probability that Drew is a female?",
				"2 out of 8", "1 out of 8", "2 out of 8", "3 out of 8",
				"4 out of 8"));

		myDb.addQuestions2(new Question(
				"5",
				"Given the following probabilities from the data, is Officer Drew a male or a female? \n p(male | drew) = 0.33 \n p(female | drew) = 0.67",
				"Female, because a probability of 0.67 is higher than 0.33.",
				"Female, because a probability of 0.67 is higher than 0.33.",
				"Male, because a probability of 0.33 is less than 0.67.",
				"None of the above, because the probabilities are too low to classify.",
				"Drew is an officer."));

		// JO BAGONG PICTURE NAMAN NA DITO SA MGA SUMUNOD NA QUESTION HA. BAKA
		// LANG MALITO KA

		myDb.addQuestions2(new Question("6",
				"How many attributes are there from the given table?", "4",
				"1", "2", "3", "4"));

		myDb.addQuestions2(new Question(
				"7",
				"In these tables we have to note that there are _ cases of not being able to play a game, and _ cases of being able to play a game.",
				"5, 9", "5, 9", "5, 8", "9, 5", "8, 5"));

		myDb.addQuestions2(new Question("8",
				"Based on the table, how many frequency of the outlook=sunny?",
				"5", "4", "5", "6", "7"));

		myDb.addQuestions2(new Question(
				"9",
				"Given the following probabilities of the data, can we play a game or not? \n P(Play=Yes | X) = 0.2424 \n P(Play=No | X) = 0.9421",
				"Since 0.9421 is greater than 0.2424 then the answer is ‘no’, we cannot play a game of tennis today.",
				"Since 0.9421 is greater than 0.2424 then the answer is ‘no’, we cannot play a game of tennis today.",
				"Since 0.9421 is less than 0.2424 then the answer is ‘yes’, we can play a game of tennis today.",
				"None of the above because the probabilities are too low to classify",
				"The probabilities of the data are wrong "));

		myDb.addQuestions2(new Question(
				"10",
				"Are the following probabilities correct?  P(Play=Yes) = 9/14 and P(Play=No) = 5/14",
				"True", "True", "False", "Maybe", "Unknown"));

		// BAGONG PICTURE ULIT TO

		myDb.addQuestions2(new Question("11",
				"Based on the table, how many attributes are there?", "4", "1",
				"2", "3", "4"));

		myDb.addQuestions2(new Question(
				"12",
				"In these table we have to note that there are _ cases of not stolen, and _ cases of stolen.",
				"5, 5", "5, 5", "5, 6", "5, 7", "5, 8"));

		myDb.addQuestions2(new Question("13",
				"Based on the table, how many frequency of the type = sports?",
				"6", "6", "7", "8", "9"));

		myDb.addQuestions2(new Question(
				"14",
				"Given the following probabilities of the data, is the car stolen or not? \n P(STOLEN=Yes | X) = 0.037 \n P(STOLEN=No | X) = 0.069",
				"Since 0.069 is greater than 0.037 then the answer is ‘no’, the car is not stolen",
				"Since 0.069 is greater than 0.037 then the answer is ‘no’, the car is not stolen",
				"Since 0.069 is less than 0.037 then the answer is ‘yes’, the car is stolen",
				"None of the above because the probabilities are too low to classify",
				"The probabilities of the data are wrong "));

		myDb.addQuestions2(new Question("15",
				"What are the attributes of ORIGIN?", "IMPORTED, DOMESTIC",
				"IMPORTED, DOMESTIC", "SPORTS, SUV", "RED, YELLOW", "YES, NO"));

	}

	public void instructionDialog() {
		final Dialog dialog = new Dialog(BayesianRandomQuiz.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_assessment_instruction);
		dialog.setCancelable(true);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));

		instructiondesc = (TextView) dialog.findViewById(R.id.instructiondesc);
		instructiondesc.setText(R.string.assessmentintronaive);

		ImageView insokbt = (ImageView) dialog.findViewById(R.id.insokbt);
		insokbt.setImageResource(R.drawable.okbutton);
		insokbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				// next
				directionAssess();
				dialog.cancel();
			}
		});
		dialog.show();
	}

	public void directionAssess() {
		final Dialog dialog3 = new Dialog(this);
		dialog3.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog3.setContentView(R.layout.transparent_popuptext);
		dialog3.setCancelable(false);
		dialog3.setCanceledOnTouchOutside(true);
		dialog3.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		final ImageView transpaimage = (ImageView) dialog3
				.findViewById(R.id.transpaimage2);
		transpaimage.setVisibility(View.VISIBLE);
		transpaimage.setImageResource(R.drawable.directionassimage);
		transpaimage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				transpaimage.setVisibility(View.GONE);
				dialog3.cancel();
			}
		});
	}
}