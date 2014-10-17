package com.capstoneii.iclassify.assessment.knn;

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

import com.capstoneii.iclassify.QuizResultActivity;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SessionCache;
import com.capstoneii.iclassify.dbclasses.DBAdapter;
import com.capstoneii.iclassify.dbclasses.Question;
import com.capstoneii.iclassify.dbclasses.TempQuestion;

public class KNNRandomQuiz extends Activity {
	List<Question> quesList;
	int score;
	int page = 1;
	int qid = 0;
	int qset = 1;
	String ncourse = "Naive Bayesian";
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

		if (refInt <= 5) {
			quizlin.setBackgroundResource(R.drawable.knnquestionimage);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());

		} else if (refInt >= 6) {
			quizlin.setBackgroundResource(R.drawable.knnquestionsix);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
		} else if (refInt >= 11) {
			quizlin.setBackgroundResource(R.drawable.knnquestionlast);
			quizlin.setVisibility(View.VISIBLE);
			Log.d("REFERENCE NUMBER:", tvRef.getText().toString());
		} else {
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

		final Dialog dialog = new Dialog(KNNRandomQuiz.this, R.style.DialogAnim);

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

		myDb.addQuestions1(new Question(
				"1",
				"What is the square to query instance (3,7) of x1 = 7 and x2 = 7 ?",
				"16", "16", "17", "18", "19"));

		myDb.addQuestions1(new Question(
				"2",
				"What is the square to query instance (3,7) of x1 = 7 and x2 = 4 ?",
				"25", "25", "25", "26", "27"));

		myDb.addQuestions1(new Question(
				"3",
				"What is the square to query instance (3,7) of x1= 3 and x2 = 4 ?",
				"9", "7", "8", "9", "10"));

		myDb.addQuestions1(new Question(
				"4",
				"What is the square to query instance (3,7) of x1 = 1 and x2 = 4 ?",
				"13", "10", "11", "12", "13"));

		myDb.addQuestions1(new Question("5",
				"Which one is not included in 3-Nearest neighbors?",
				"Second row", "First row", "Second row", "Third row",
				"Fourth row"));

		// BAGONG PICTURE

		myDb.addQuestions1(new Question(
				"6",
				"According to the illustration, what is the size of K in Figure A?",
				"1", "1", "2", "3", "4"));

		myDb.addQuestions1(new Question(
				"7",
				"According to the illustration, what is the size of K in Figure B?",
				"2", "1", "2", "3", "4"));

		myDb.addQuestions1(new Question(
				"8",
				"According to the illustration, what is the size of K in Figure C?",
				"3", "1", "2", "3", "4"));

		myDb.addQuestions1(new Question("9",
				"In Figure C, the unknown record ‘x’ can be classified as?",
				"Positive", "Male", "Negative", "Female", "Positive"));

		myDb.addQuestions1(new Question("10",
				"In Figure A, the unknown record ‘x’ can be classified as?",
				"Positive", "Male", "Negative", "Female", "Positive"));

		// BAGONG PICTURE

		myDb.addQuestions1(new Question("11",
				"According to the illustration, what is the size of K?", "5",
				"5", "2", "3", "4"));

		myDb.addQuestions1(new Question("12",
				"According to the illustration, what is the unknown class?",
				"Xq", "+", "-", "Xq", "none of these"));

		myDb.addQuestions1(new Question(
				"13",
				"According to the illustration, what is the size the training data?",
				"13", "10", "11", "12", "13"));

		myDb.addQuestions1(new Question(
				"14",
				"According to the illustration, if k=5, the unknown record ‘Xq’ can be classified as?",
				"Negative", "Male", "Female", "Positive", "Negative"));

		myDb.addQuestions1(new Question(
				"15",
				"According to the illustration, which of the following statements is true?",
				"Since K = 5, then in this case query instance xq will be classified as negative since three of its nearest neighbors are classified as negative.",
				"Since K = 2, then in this case query instance xq will be classified as negative since three of its nearest neighbors are classified as negative.",
				"Since K = 5, then in this case query instance xq will be classified as negative since three of its nearest neighbors are classified as negative.",
				"Since K = 3, then in this case query instance xq will be classified as negative since three of its nearest neighbors are classified as negative.",
				"None of these."));

	}

	public void instructionDialog() {
		final Dialog dialog = new Dialog(KNNRandomQuiz.this);
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
	
	public void directionAssess(){
		final Dialog dialog1 = new Dialog(this);
		dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog1.setContentView(R.layout.transparent_popuptext);
		dialog1.setCancelable(true);
		dialog1.setCanceledOnTouchOutside(true);
		dialog1.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		final ImageView transpaimage = (ImageView) dialog1
				.findViewById(R.id.transpaimage2);
		transpaimage.setVisibility(View.VISIBLE);
		transpaimage.setImageResource(R.drawable.directionassimage);
		transpaimage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				transpaimage.setVisibility(View.GONE);
				dialog1.cancel();
			}
		});
	}
	
	
	
}