package com.capstoneii.iclassify.assessment.decisionid3;

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

import com.capstoneii.iclassify.QuizResultDecision;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SessionCache;
import com.capstoneii.iclassify.dbclasses.DBAdapter;
import com.capstoneii.iclassify.dbclasses.Question;
import com.capstoneii.iclassify.dbclasses.TempQuestion;

public class DecisionTreeRandomQuiz extends Activity {

	List<Question> quesList;
	int score;
	int page = 1;
	int qid = 0;
	int qset = 1;
	String ncourse = "Decision Tree";
	Question question;
	SQLiteDatabase mdb;
	TextView tvQue, tvPage, tvRef, instructiondesc;
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
					String qdetails = "Quiz Retake" + retake;
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

		String refNumber = tvRef.getText().toString();
		int refInt = Integer.parseInt(refNumber);

		if (refInt <= 5) {
			quizlin.setBackgroundResource(R.drawable.decisiontreequestionimage);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());

		} else if (refInt >= 6) {
			quizlin.setBackgroundResource(R.drawable.decisiontreequestionimageoutlook);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
		} else if (refInt >= 11) {
			quizlin.setBackgroundResource(R.drawable.decisiontreequestiontypecardoor);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
		} else {
			quizlin.setVisibility(View.GONE);
		}

		/*
		 * if (tvRef.getText().toString().equals("")) {
		 * 
		 * }
		 */

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
				"1",
				"Based on the decision tree, what could be the gender of this Simpson's Character:Marge has a hair length of 10 inches and a weight of 150 lbs.",
				"Female", "Male", "Female", "Unknown", "None of these")); // 2
																			// ARRAY
		// 2

		myDb.addQuestions3(new Question(
				"2",
				"Based on the decision tree, what could be the gender of this Simpson's Character:Otto has a hair length of 10 inches and a weight of 180 lbs",
				"Male", "Male", "Female", "Unknown", "None of these")); // //
																		// 3TH
																		// IN
																		// SCORE
																		// ARRAY
																		// RESULTS
		// 4
		myDb.addQuestions3(new Question(
				"3",
				"Based on the decision tree, which one of the following statements is correct?",
				"If Weight greater than 160, classify as Male",
				"If Weight greater than 160, classify as Female",
				"If Hair Length less than or equal to 2, classify as Female",
				"If Weight greater than 160, classify as Male",
				"If Hair Length less than 160, classify as Male")); // 4TH IN
																	// SCORE
																	// ARRAY
																	// RESULTS

		myDb.addQuestions3(new Question(
				"4",
				"Based on the decision tree, are these decision rules correct?\nIf Weight greater than 160, classify as Male\nElseif Hair Length less than or equal to 2, classify as Male \nElse classify as Female ",
				"TRUE", "TRUE", "First Statement is True", "FALSE",
				"Last Statement is True")); // 5TH IN SCORE ARRAY RESULTS

		// OTHER SET OF QUESTION IMAGE --- WEATHER OUTLOOK
		myDb.addQuestions3(new Question("5",
				"Based on the decision tree, what are the 'attributes'",
				"HAIR LENGTH and WEIGHT", "MALE and FEMALE",
				"HAIR LENGTH and WEIGHT", "HAIR LENGTH and FEMALE",
				"MALE and WEIGHT")); // 6TH IN SCORE ARRAY RESULTS

		myDb.addQuestions3(new Question(
				"6",
				"Based on the decision tree, should we play tennis under these circumstances:\nIt is a sunny day with high humidity. ",
				"NO, we cannot play tennis.", "YES we can play tennis.",
				"I don't know", "NO, we cannot play tennis.", "Maybe"));// 7
		// 9
		myDb.addQuestions3(new Question(
				"7",
				"Based on the decision tree, should we play tennis under these circumstances:\nIt is a rainy day with strong winds",
				"NO, we cannot play tennis.", "YES, we can play tennis",
				"NO, we cannot play tennis.", "I don't know", "Maybe"));// 8

		myDb.addQuestions3(new Question(
				"8",
				"Based on the decision tree, which one of the following statements is correct?",
				"All of the above.",
				"If the outlook is rainy and the wind is weak, then we can play tennis.",
				"If the outlook is rainy and the wind is strong, then we cannot play tennis.",
				"If the outlook is sunny and the humidity is high, then we cannot play tennis.",
				"All of the above.")); // 9

		myDb.addQuestions3(new Question(
				"9",
				"Based on the decision tree, what are the attribute values of OUTLOOK?",
				"SUNNY, OVERCAST, RAINY", "HIGH, NORMAL",
				"OUTLOOK, HUMIDITY and WIND", "SUNNY, OVERCAST, RAINY",
				"STRONG, WEAK"));// 10

		myDb.addQuestions3(new Question("10",
				"Based on the decision tree, what are the 'attributes'",
				"OUTLOOK, HUMIDITY and WIND", "OUTLOOK, HUMIDITY and WIND",
				"SUNNY, OVERCAST, RAINY", "STRONG, WEAK", "HIGH, NORMAL"));// 11
		// TRHID SET
		myDb.addQuestions3(new Question("11",
				"Based on the decision tree, what are the ‘attributes’?",
				"TYPE, DOORS, TIRES", "TYPE, DOORS, TIRES",
				"CAR, MINI VAN, SUV", "BLACKWALL, WHITEWALL",
				"ALL OF THE ABOVE"));// 12

		myDb.addQuestions3(new Question(
				"12",
				"Based on the decision tree, what are the attribute values of 'TYPE'?",
				"CAR, MINI VAN, SUV", "NEGATIVE, POSITIVE",
				"CAR, MINI VAN, SUV", "TYPE, DOORS, TIRES", "DOORS, TIRES"));

		myDb.addQuestions3(new Question(
				"13",
				"Based on the decision tree, which one of the following statements is correct?",
				"All of the above",
				"If type is car and doors are 2 then it is positive",
				"if type is SUV and tires are blackwall it is negative.",
				"if type is mini van then it is negative.", "All of the above"));

		myDb.addQuestions3(new Question(
				"14",
				"14. Based on the decision tree, what is the class under these circumstances: It is a SUV with a blackwall tires.",
				"NEGATIVE", "POSITIVE", "CANNOT BE", "WHITEWALL", "NEGATIVE"));

		myDb.addQuestions3(new Question(
				"15",
				"Based on the decision tree, what is the class under these circumstances: It is a car with 2 doors.",
				"POSITIVE", "CANNOT BE", "negative", "WHITEWALL", "POSITIVE"));

	}

	public void instructionDialog() {
		final Dialog dialog = new Dialog(DecisionTreeRandomQuiz.this);
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
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	public void directionAssess() {
		final Dialog dialog2 = new Dialog(this);
		dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog2.setContentView(R.layout.transparent_popuptext);
		dialog2.setCancelable(false);
		dialog2.setCanceledOnTouchOutside(false);
		dialog2.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		final ImageView transpaimage = (ImageView) dialog2
				.findViewById(R.id.transpaimage2);
		transpaimage.setVisibility(View.VISIBLE);
		transpaimage.setImageResource(R.drawable.directionassimage);
		transpaimage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				transpaimage.setVisibility(View.GONE);
				dialog2.dismiss();
			}
		});
	}

}