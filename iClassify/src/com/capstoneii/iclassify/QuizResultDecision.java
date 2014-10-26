package com.capstoneii.iclassify;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.capstoneii.iclassify.assessment.decisionid3.DecisionTreeAssessmentActivity;
import com.capstoneii.iclassify.assessment.decisionid3.DecisionTreeRandomQuiz;
import com.capstoneii.iclassify.assessment.bayesian.BayesianRandomQuiz;
import com.capstoneii.iclassify.dbclasses.DBAdapter;
import com.capstoneii.iclassify.dbclasses.TempQuestion;
import com.capstoneii.iclassify.problems.SimpsonQuizProblemResult;
import com.capstoneii.iclassify.problems.SimpsonTableFragment;

import descisiondiscussflip.DescTreeLayoutActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class QuizResultDecision extends Activity implements AnimationListener {

	ImageButton backbutton;
	TextView correct;
	TextView wrong;
	TextView mesg;
	Button bscorelog, bqresult,retakequiz;
	int correctans, wrongans;
	String me;
	int score;
	int setq;
	String ncourse;
	String qdetails;
	DBAdapter myDb;

	SessionCache QuizSession;
	String tdate;

	TempQuestion tquestion;

	Animation push_up_in, bounce_in1, bounce_in2, bounce_in3, fade_in;
	private TextView tvcorrect, tvwrong;
	private Cursor cr;

	int totalsumof;
	int sumOf;
	double jsper;
	int retake;

	String quizdetails;
	int prevTotal;
	int curTotal;
	String finalDate;
	Intent intent;
	String initVal = "1";

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_result);
		intent = new Intent();
		openDB();
		QuizSession = new SessionCache(getApplicationContext());

		Date date = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
		finalDate = timeFormat.format(date);

		HashMap<String, String> totalSum = QuizSession.getTotalSum();
		sumOf = Integer.parseInt(totalSum.get(SessionCache.JS_MAX_ITEM1));
		retake = Integer.parseInt(totalSum.get(SessionCache.REPEATING1));

		totalsumof = myDb.getallRowswithName("Flash 1");

		if (totalsumof != 0) {
			double psDiv = (double) totalsumof / sumOf;
			jsper = psDiv * 100.0;
		} else {
			jsper = 0;
		}
		DecimalFormat df = new DecimalFormat("00.00");
		String quizaverage = df.format(jsper) + "%";

		if (retake == 1) {
			quizdetails = "Quiz has been taken for the first time";
		} else if (retake == 4) {
			quizdetails = "Quiz overwrite the first quiz try";
		} else {
			quizdetails = "Quiz has been taken " + retake + " times";
		}

		myDb.addjsquiz(1, "Decision Tree", quizdetails, quizaverage);

		tvcorrect = (TextView) findViewById(R.id.txtcorrect);
		tvwrong = (TextView) findViewById(R.id.txtwrong);
		correct = (TextView) findViewById(R.id.tvCorrect);
		wrong = (TextView) findViewById(R.id.tvWrong);
		mesg = (TextView) findViewById(R.id.tvMesg);
		bscorelog = (Button) findViewById(R.id.bSlog);
		bqresult = (Button) findViewById(R.id.bQview);
		retakequiz = (Button) findViewById(R.id.retakequiz);
		
		Bundle g = getIntent().getExtras();
		setq = g.getInt("qno");
		score = g.getInt("score");
		ncourse = g.getString("course");
		qdetails = g.getString("quizdetails");

		wrongans = 10 - score;
		correct.setText(score + "");
		wrong.setText(wrongans + "");
		mesg.setText(score + "/10");

		if (score < 5) {
			obtlDialog();
		}

		tdate = totalSum.get(SessionCache.FL_QUIZ_TAKE);

		QuizSession.StoredLastScore(mesg.getText().toString(), qdetails,
				ncourse + " 1");

		push_up_in = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.push_up_in);
		fade_in = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.fade_in);
		bounce_in1 = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.bounce);
		bounce_in2 = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.bounce);
		bounce_in3 = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.bounce);

		push_up_in.setAnimationListener(this);
		fade_in.setAnimationListener(this);
		bounce_in1.setAnimationListener(this);
		bounce_in2.setAnimationListener(this);
		bounce_in3.setAnimationListener(this);

		correct.setAnimation(bounce_in1);

		bscorelog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				/*
				 * Bundle b =new Bundle(); b.putInt("refID", 3);
				 * //b.putString("showScore",ncourse); b.putString("Course",
				 * "JavaScript"); b.putString("lastdate", tdate); Intent intent
				 * = new Intent(JsResult1Activity.this,
				 * ListCourseScoreActivity2.class); intent.putExtras(b);
				 * startActivity(intent);
				 * overridePendingTransition(R.anim.slide_in_left,
				 * R.anim.slide_out_left);
				 */
				openDB();
				populateSwithdb();
			}
		});

		bqresult.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openDB();
				populateQwithdb();
			}
		});
		
		retakequiz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (QuizSession.hasFlQuiz2()) {

					final Dialog dialog = new Dialog(QuizResultDecision.this,
							R.style.DialogAnim);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.validate_message);

					Button bYes = (Button) dialog.findViewById(R.id.buttonOk);
					Button bNo = (Button) dialog
							.findViewById(R.id.buttonCancel);
					TextView tvalertmessage = (TextView) dialog
							.findViewById(R.id.tvalertmessage);
					HashMap<String, String> quizRecord = QuizSession
							.getTotalSum();
					retake = Integer.parseInt(quizRecord
							.get(SessionCache.REPEATING1));
					prevTotal = Integer.parseInt(quizRecord
							.get(SessionCache.JS_MAX_ITEM1));

					if (retake == 3) {
						tvalertmessage
								.setText("You have taken this 3 times, Do you want to take this quiz? the first try you have taken will overwrite");
						bYes.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {

								// delete the record
								myDb.deleteQuiz("Decision Tree");

								// store last quiz session for
								// JS and for all the
								// records
								QuizSession.StoreFlLastQuizTaken(finalDate);
								QuizSession.StoreAllLastQuizTaken(finalDate);

								// delete the scorerow if the
								// user wants to
								// overwrite the first take of
								// quiz
								myDb.deletescorerowSet(1, "Decision Tree");

								// get the retake value + 1
								// sum is 4 so when the user try
								// to take the quiz
								// again, he will not able to
								// take it any more, he
								// will the next condition which
								// will appear
								// "You have taken this 4 times"
								int sum = retake + 1;
								myDb.addjsquiz(1, "Decision Tree", "", "0 %");

								QuizSession.FinishSessionNum1(Integer
										.toString(sum));
								intent = new Intent(QuizResultDecision.this,
										DecisionTreeRandomQuiz.class);
								intent.putExtra("retakeNum", sum);
								startActivity(intent);
								dialog.dismiss();
								QuizResultDecision.this
										.overridePendingTransition(
												R.anim.slide_in_left,
												R.anim.slide_out_left);
								QuizResultDecision.this.finish();
							}
						});
						bNo.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						});

						dialog.show();

					} else if (retake == 4) {
						tvalertmessage
								.setText("You have taken this 4 times, Do you want to take this quiz? the second try you have taken will overwrite");

						bYes.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								myDb.deleteQuiz("Decision Tree");

								QuizSession.StoreFlLastQuizTaken(finalDate);
								QuizSession.StoreAllLastQuizTaken(finalDate);

								myDb.deletescorerowSet(2, "Decision Tree 2");

								int sum = retake + 1;// 5
								myDb.addjsquiz(1, "Decision Tree", "", "0 %");

								QuizSession.FinishSessionNum1(Integer
										.toString(sum));
								intent = new Intent(QuizResultDecision.this,
										DecisionTreeRandomQuiz.class);
								intent.putExtra("retakeNum", sum);
								startActivity(intent);
								dialog.dismiss();
								QuizResultDecision.this
										.overridePendingTransition(
												R.anim.slide_in_left,
												R.anim.slide_out_left);
								QuizResultDecision.this.finish();
							}
						});
						bNo.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						});
						dialog.show();
					}

					else if (retake == 5) {
						tvalertmessage
								.setText("You have taken this 5 times, Do you want to take this quiz? the second try you have taken will overwrite");

						bYes.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								myDb.deleteQuiz("Decision Tree");

								QuizSession.StoreFlLastQuizTaken(finalDate);
								QuizSession.StoreAllLastQuizTaken(finalDate);

								myDb.deletescorerowSet(2, "Decision Tree 2");

								int sum = retake + 1;// 5
								myDb.addjsquiz(1, "Decision Tree", "", "0 %");

								QuizSession.FinishSessionNum1(Integer
										.toString(sum));
								intent = new Intent(QuizResultDecision.this,
										DecisionTreeRandomQuiz.class);
								intent.putExtra("retakeNum", sum);
								startActivity(intent);
								dialog.dismiss();
								QuizResultDecision.this
										.overridePendingTransition(
												R.anim.slide_in_left,
												R.anim.slide_out_left);
								QuizResultDecision.this.finish();
							}
						});
						bNo.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						});
						dialog.show();
					} else {
						// this condition will use if retake is
						// value 1 to 2
						myDb.deleteQuiz("Decision Tree");
						QuizSession.StoreFlLastQuizTaken(finalDate);
						QuizSession.StoreAllLastQuizTaken(finalDate);

						int sum = retake + 1;
						myDb.addjsquiz(1, "Decision Tree", "", "0 %");

						curTotal = prevTotal + 10;
						QuizSession.StoreTotal1(Integer.toString(curTotal));
						QuizSession.FinishSessionNum1(Integer.toString(sum));
						intent = new Intent(QuizResultDecision.this,
								DecisionTreeRandomQuiz.class);
						intent.putExtra("retakeNum", sum);
						startActivity(intent);
						QuizResultDecision.this.overridePendingTransition(
								R.anim.slide_in_left, R.anim.slide_out_left);
						QuizResultDecision.this.finish();
					}
				} else {
					QuizSession.StoreFlLastQuizTaken(finalDate);
					QuizSession.StoreAllLastQuizTaken(finalDate);
					int passVal = Integer.parseInt(initVal);
					myDb.addjsquiz(1, "Decision Tree", initVal, "0 %");
					curTotal = prevTotal + 10;
					QuizSession.StoreTotal1(Integer.toString(curTotal));
					QuizSession.FinishSessionNum1(initVal);
					intent = new Intent(QuizResultDecision.this,
							DecisionTreeRandomQuiz.class);
					intent.putExtra("retakeNum", passVal);
					startActivity(intent);
					QuizResultDecision.this.overridePendingTransition(
							R.anim.slide_in_left, R.anim.slide_out_left);
					QuizResultDecision.this.finish();

				}
			}
		});
	}
	
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void openDB() {
		myDb = new DBAdapter(this);
		myDb.open();
	}

	@SuppressWarnings("deprecation")
	private void populateSwithdb() {

		final Dialog dialog = new Dialog(this, R.style.DialogAnim);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.activity_quizhistory);

		TextView tvQuizChapter = (TextView) dialog
				.findViewById(R.id.tvchapterName);
		TextView tvLastQuiz = (TextView) dialog
				.findViewById(R.id.tvlastquizhistory);
		ListView myList = (ListView) dialog.findViewById(R.id.listofhistory);
		cr = myDb.getAllscorewithChapter("Decision Tree");
		startManagingCursor(cr);

		tvQuizChapter.setText("" + ncourse + " Chapter 1");
		tvLastQuiz.setText(" " + tdate + "");

		String[] fromFieldNames = new String[] { DBAdapter._NAME,
				DBAdapter._QDETAILS, DBAdapter._DATE, DBAdapter._SCORE };
		int[] toViewIDs = new int[] { R.id.tvQuiztitle, R.id.tvQdetails,
				R.id.tvQdatetaken, R.id.tvQscore };

		SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(this,
				R.layout.history_layout, cr, fromFieldNames, toViewIDs);
		myList.setAdapter(myCursorAdapter);
		dialog.show();
	}

	@SuppressWarnings("deprecation")
	private void populateQwithdb() {

		final Dialog dialog = new Dialog(this, R.style.DialogAnim);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.activity_summary);
		ListView myList = (ListView) dialog.findViewById(R.id.listquest);
		Cursor cr = myDb.getAlltempRows();
		startManagingCursor(cr);

		String[] fromFieldNames = new String[] { DBAdapter.TEMP_Q_ITEM,
				DBAdapter.TEMP_UANS, DBAdapter.TEMP_ANS };
		int[] toViewIDs = new int[] { R.id.questiontv, R.id.useranswertv,
				R.id.trueanswertv };

		SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(this, // Context
				R.layout.question_item, // Row layout template
				cr, // cursor (set of DB records to map)
				fromFieldNames, // DB Column names
				toViewIDs // View IDs to put information in
		);
		myList.setAdapter(myCursorAdapter);
		myList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Cursor cursor = myDb.getRow(id);
				if (cursor.moveToFirst()) {
					long idDb = cursor.getLong(DBAdapter.COL_ROWID);
					int qset = Integer.parseInt(cursor.getString(DBAdapter.COL_SETID));
					int item = Integer.parseInt(cursor.getString(DBAdapter.COL_REFID));
					String qitem = cursor.getString(DBAdapter.COL_QITEM);
					String qans = cursor.getString(DBAdapter.COL_QANS);
					String quans = cursor.getString(DBAdapter.COL_QUANS);
					
					String Message = "Lesson"+ (item +1) +".";

					Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_LONG).show();
					Bundle b = new Bundle();
					b.putInt("item", item);
					Intent intent =new Intent(getApplicationContext(), SimpsonQuizProblemResult.class);
					intent.putExtras(b);
					startActivity(intent);
				}
				cursor.close();
			}
		});
		dialog.show();
	}

	@Override
	public void onAnimationStart(Animation animation) {

		if (animation == bounce_in1) {
			correct.setVisibility(View.VISIBLE);
		}
		if (animation == bounce_in2) {
			wrong.setVisibility(View.VISIBLE);
		}
		if (animation == fade_in) {
			bscorelog.setVisibility(View.VISIBLE);
			bqresult.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (animation == bounce_in1) {
			wrong.clearAnimation();
			wrong.startAnimation(bounce_in2);
		}
		if (animation == bounce_in2) {
			bscorelog.startAnimation(fade_in);
			bqresult.startAnimation(fade_in);
		}
		if (animation == bounce_in2) {
			tvcorrect.startAnimation(bounce_in3);
			tvwrong.startAnimation(bounce_in3);
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}

	@Override
	public void onBackPressed() {
		Toast.makeText(getApplicationContext(), "Press 'back' on the top",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent i = new Intent(QuizResultDecision.this,
					DescTreeLayoutActivity.class);
			startActivity(i);
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void obtlDialog() {
		final Dialog dialog = new Dialog(QuizResultDecision.this,
				R.style.DialogAnim);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.validate_message);
		dialog.setCancelable(false);
		Button bOk = (Button) dialog.findViewById(R.id.buttonOk);
		Button bCancel = (Button) dialog.findViewById(R.id.buttonCancel);
		bOk.setText("Yes");
		bCancel.setText("No");
		TextView question = (TextView) dialog.findViewById(R.id.tvalertmessage);

		question.setText("Your score is less than 5.Would you like to try extra activity?");

		bOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(QuizResultDecision.this,
						DecisionTreeAssessmentActivity.class);
				QuizResultDecision.this.startActivity(intent);
				QuizResultDecision.this.finish();
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
}