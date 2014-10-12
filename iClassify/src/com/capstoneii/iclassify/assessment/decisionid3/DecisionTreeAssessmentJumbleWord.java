package com.capstoneii.iclassify.assessment.decisionid3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SessionCache;
import com.capstoneii.iclassify.assessment.bayesian.BayesianAssessmentDragAndDrop;
import com.capstoneii.iclassify.assessment.bayesian.BayesianRandomQuiz;
import com.capstoneii.iclassify.assessment.knn.KNNAssessmentDragAndDrop;
import com.capstoneii.iclassify.assessment.knn.KNNRandomQuiz;
import com.capstoneii.iclassify.dbclasses.DBAdapter;

public class DecisionTreeAssessmentJumbleWord extends ActionBarActivity {
	TextView jumblequestionText;
	ImageView imgt, imgr, imgy, imgp, imgn, imge, imgo;
	EditText jumbleedittext;
	Button checkbt, clearbt;
	Animation animationZoom, zoomOut;
	
	DBAdapter myDb;
	SessionCache QuizSession;
	
	int retake;
	int prevTotal;
	int curTotal;
	String finalDate;
	Intent intent;
	String initVal = "1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assessment_jumbled_letter_first);

		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		
		intent = new Intent();
		QuizSession = new SessionCache(DecisionTreeAssessmentJumbleWord.this);
		openDB();
		
		Date date = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
	    finalDate = timeFormat.format(date);
		
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		animationZoom = AnimationUtils.loadAnimation(this, R.anim.unzoom_in);
		zoomOut = AnimationUtils.loadAnimation(this, R.anim.unzoom_out);

		imgt = (ImageView) findViewById(R.id.imgt);
		imgr = (ImageView) findViewById(R.id.imgr);
		imgy = (ImageView) findViewById(R.id.imgy);
		imgp = (ImageView) findViewById(R.id.imgp);
		imgn = (ImageView) findViewById(R.id.imgn);
		imge = (ImageView) findViewById(R.id.imge);
		imgo = (ImageView) findViewById(R.id.imgo);

		imgt.setVisibility(View.VISIBLE);
		imgr.setVisibility(View.VISIBLE);
		imgy.setVisibility(View.VISIBLE);
		imgp.setVisibility(View.VISIBLE);
		imgn.setVisibility(View.VISIBLE);
		imge.setVisibility(View.VISIBLE);
		imgo.setVisibility(View.VISIBLE);

		imgtTimer();
		imgrTimer();
		imgyTimer();
		imgpTimer();
		imgnTimer();
		imgeTimer();
		imgoTimer();

		jumblequestionText = (TextView) findViewById(R.id.jumblequestionText);
		jumblequestionText.setText(R.string.jumbleid3question1);

		jumbleedittext = (EditText) findViewById(R.id.jumbleedittext);
		jumbleedittext.addTextChangedListener(watch);

		checkbt = (Button) findViewById(R.id.checkbt);
		clearbt = (Button) findViewById(R.id.clearbt);

		checkbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				// Check the the user answer if matched with Entropy
				String ans;
				ans = jumbleedittext.getText().toString();

				if (ans.equals("Entropy")
						|| (ans.equals("entropy") || (ans.equals("ENTROPY")))) {
					new CheckDroped().execute();
				} else {

					Toast.makeText(
							DecisionTreeAssessmentJumbleWord.this
									.getApplicationContext(),
							"Please provide a correct answer",
							Toast.LENGTH_LONG).show();
					final Dialog dialog = new Dialog(
							DecisionTreeAssessmentJumbleWord.this);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.correct_dialog);
					dialog.setCancelable(false);
					dialog.getWindow().setBackgroundDrawable(
							new ColorDrawable(
									android.graphics.Color.TRANSPARENT));
					ImageView correctcheck = (ImageView) dialog
							.findViewById(R.id.correctcheck);
					correctcheck.setImageResource(R.drawable.wrongcircle);
					correctcheck.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View InputFragmentView) {
							dialog.dismiss();
						}
					});

					dialog.show();
				}
			}
		});

		clearbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				jumbleedittext.setText("");

				imgt.setVisibility(View.VISIBLE);
				imgr.setVisibility(View.VISIBLE);
				imgy.setVisibility(View.VISIBLE);
				imgp.setVisibility(View.VISIBLE);
				imgn.setVisibility(View.VISIBLE);
				imge.setVisibility(View.VISIBLE);
				imgo.setVisibility(View.VISIBLE);

				imgt.startAnimation(animationZoom);
				imgr.startAnimation(animationZoom);
				imgy.startAnimation(animationZoom);
				imgp.startAnimation(animationZoom);
				imgn.startAnimation(animationZoom);
				imge.startAnimation(animationZoom);
				imgo.startAnimation(animationZoom);

			}
		});
	}

	TextWatcher watch = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable arg0) {

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {

		}

		@Override
		public void onTextChanged(CharSequence s, int a, int b, int c) {
			// jumblequestionText.setText(s);

			String ans;
			ans = jumbleedittext.getText().toString();

			if (ans.contains("T") || (ans.contains("t"))) {
				imgt.startAnimation(zoomOut);
				new CountDownTimer(500, 500) {
					public void onTick(long millisUntilFinished) {

					}

					public void onFinish() {
						imgt.setVisibility(View.GONE);
					}
				}.start();
			}

			if (ans.contains("R") || (ans.contains("r"))) {
				imgr.startAnimation(zoomOut);
				new CountDownTimer(500, 500) {
					public void onTick(long millisUntilFinished) {

					}

					public void onFinish() {
						imgr.setVisibility(View.GONE);
					}
				}.start();
			}

			if (ans.contains("Y") || (ans.contains("y"))) {
				imgy.startAnimation(zoomOut);
				new CountDownTimer(500, 500) {
					public void onTick(long millisUntilFinished) {

					}

					public void onFinish() {
						imgy.setVisibility(View.GONE);
					}
				}.start();
			}

			if (ans.contains("P") || (ans.contains("p"))) {
				imgp.startAnimation(zoomOut);
				new CountDownTimer(500, 500) {
					public void onTick(long millisUntilFinished) {

					}

					public void onFinish() {
						imgp.setVisibility(View.GONE);
					}
				}.start();
			}

			if (ans.contains("N") || (ans.contains("n"))) {
				imgn.startAnimation(zoomOut);
				new CountDownTimer(500, 500) {
					public void onTick(long millisUntilFinished) {

					}

					public void onFinish() {
						imgn.setVisibility(View.GONE);
					}
				}.start();
			}

			if (ans.contains("E") || (ans.contains("e"))) {
				imge.startAnimation(zoomOut);
				new CountDownTimer(500, 500) {
					public void onTick(long millisUntilFinished) {

					}

					public void onFinish() {
						imge.setVisibility(View.GONE);
					}
				}.start();
			}

			if (ans.contains("O") || (ans.contains("o"))) {
				imgo.startAnimation(zoomOut);
				new CountDownTimer(500, 500) {
					public void onTick(long millisUntilFinished) {

					}

					public void onFinish() {
						imgo.setVisibility(View.GONE);
					}
				}.start();
			}
			if (ans.isEmpty()) {
				imgt.setVisibility(View.VISIBLE);
				imgr.setVisibility(View.VISIBLE);
				imgy.setVisibility(View.VISIBLE);
				imgp.setVisibility(View.VISIBLE);
				imgn.setVisibility(View.VISIBLE);
				imge.setVisibility(View.VISIBLE);
				imgo.setVisibility(View.VISIBLE);

				imgt.startAnimation(animationZoom);
				imgr.startAnimation(animationZoom);
				imgy.startAnimation(animationZoom);
				imgp.startAnimation(animationZoom);
				imgn.startAnimation(animationZoom);
				imge.startAnimation(animationZoom);
				imgo.startAnimation(animationZoom);
			}

			if (ans.contains("")) {
				imgt.setVisibility(View.VISIBLE);
				imgr.setVisibility(View.VISIBLE);
				imgy.setVisibility(View.VISIBLE);
				imgp.setVisibility(View.VISIBLE);
				imgn.setVisibility(View.VISIBLE);
				imge.setVisibility(View.VISIBLE);
				imgo.setVisibility(View.VISIBLE);

				imgt.startAnimation(animationZoom);
				imgr.startAnimation(animationZoom);
				imgy.startAnimation(animationZoom);
				imgp.startAnimation(animationZoom);
				imgn.startAnimation(animationZoom);
				imge.startAnimation(animationZoom);
				imgo.startAnimation(animationZoom);
			}

		}
	};

	public void imgtTimer() {
		new CountDownTimer(500, 500) {
			public void onTick(long millisUntilFinished) {

			}

			public void onFinish() {
				imgt.startAnimation(animationZoom);
			}
		}.start();
	}

	public void imgrTimer() {
		new CountDownTimer(1000, 1000) {

			public void onTick(long millisUntilFinished) {

			}

			public void onFinish() {
				imgr.startAnimation(animationZoom);
			}
		}.start();

	}

	public void imgyTimer() {
		new CountDownTimer(1500, 1500) {

			public void onTick(long millisUntilFinished) {

			}

			public void onFinish() {
				imgy.startAnimation(animationZoom);
			}
		}.start();
	}

	public void imgpTimer() {
		new CountDownTimer(2000, 2000) {

			public void onTick(long millisUntilFinished) {

			}

			public void onFinish() {
				imgp.startAnimation(animationZoom);
			}
		}.start();
	}

	public void imgnTimer() {
		new CountDownTimer(2500, 2500) {

			public void onTick(long millisUntilFinished) {

			}

			public void onFinish() {
				imgn.startAnimation(animationZoom);
			}
		}.start();

	}

	public void imgeTimer() {
		new CountDownTimer(3000, 3000) {

			public void onTick(long millisUntilFinished) {

			}

			public void onFinish() {
				imge.startAnimation(animationZoom);
			}
		}.start();
	}

	public void imgoTimer() {
		new CountDownTimer(3500, 3500) {

			public void onTick(long millisUntilFinished) {

			}

			public void onFinish() {
				imgo.startAnimation(animationZoom);
			}
		}.start();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			super.onBackPressed();
			// app icon in action bar clicked; go home
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private class CheckDroped extends AsyncTask<Void, Void, Void> {
		private ProgressDialog progress = null;

		@Override
		protected Void doInBackground(Void... params) {
			try {

				Thread.sleep(1000);
			} catch (Exception e) {

				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onPreExecute() {
			// start the progress dialog
			progress = ProgressDialog.show(
					DecisionTreeAssessmentJumbleWord.this, null, "Checking...");
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.setIndeterminate(true);
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			progress.dismiss();
			NextAssess();
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}
	}

	public void NextAssess() {
		if(QuizSession.hasFlQuiz3()){
			
			final Dialog dialog = new Dialog(
					DecisionTreeAssessmentJumbleWord.this, R.style.DialogAnim);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.validate_message);

			Button bYes = (Button) dialog.findViewById(R.id.buttonOk);
			Button bNo = (Button) dialog.findViewById(R.id.buttonCancel);
			TextView tvalertmessage = (TextView) dialog
					.findViewById(R.id.tvalertmessage);

			HashMap<String, String> quizRecord = QuizSession.getTotalSum();
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

						// store last quiz session for JS and for all the
						// records
						QuizSession.StoreFlLastQuizTaken(finalDate);
						QuizSession.StoreAllLastQuizTaken(finalDate);

						// delete the scorerow if the user wants to
						// overwrite the first take of quiz
						myDb.deletescorerowSet(1, "Naive Bayesian 1");

						// get the retake value + 1
						// sum is 4 so when the user try to take the quiz
						// again, he will not able to take it any more, he
						// will the next condition which will appear
						// "You have taken this 4 times"
						int sum = retake + 1;
						myDb.addjsquiz(1, "Decision Tree", "", "0 %");

						QuizSession.FinishSessionNum1(Integer.toString(sum));
						intent = new Intent(
								DecisionTreeAssessmentJumbleWord.this,
								DecisionTreeRandomQuiz.class);
						intent.putExtra("retakeNum", sum);
						startActivity(intent);
						dialog.dismiss();
						DecisionTreeAssessmentJumbleWord.this
								.overridePendingTransition(
										R.anim.slide_in_left,
										R.anim.slide_out_left);
						DecisionTreeAssessmentJumbleWord.this.finish();
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

						myDb.deletescorerowSet(2, "Decision Tree 1");

						int sum = retake + 1;// 5
						myDb.addjsquiz(1, "Decision Tree", "", "0 %");

						QuizSession.FinishSessionNum1(Integer.toString(sum));
						intent = new Intent(
								DecisionTreeAssessmentJumbleWord.this,
								DecisionTreeRandomQuiz.class);
						intent.putExtra("retakeNum", sum);
						startActivity(intent);
						dialog.dismiss();
						DecisionTreeAssessmentJumbleWord.this
								.overridePendingTransition(
										R.anim.slide_in_left,
										R.anim.slide_out_left);
						DecisionTreeAssessmentJumbleWord.this.finish();

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

						myDb.deletescorerowSet(2, "Decision Tree 1");

						int sum = retake + 1;// 5
						myDb.addjsquiz(1, "Decision Tree", "", "0 %");

						QuizSession.FinishSessionNum1(Integer.toString(sum));
						intent = new Intent(
								DecisionTreeAssessmentJumbleWord.this,
								DecisionTreeRandomQuiz.class);
						intent.putExtra("retakeNum", sum);
						startActivity(intent);
						dialog.dismiss();
						DecisionTreeAssessmentJumbleWord.this
								.overridePendingTransition(
										R.anim.slide_in_left,
										R.anim.slide_out_left);
						DecisionTreeAssessmentJumbleWord.this.finish();
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
			// this condition will use if retake is value 1 to 2
			myDb.deleteQuiz("Naive Bayesian");
			QuizSession.StoreFlLastQuizTaken(finalDate);
			QuizSession.StoreAllLastQuizTaken(finalDate);

			int sum = retake + 1;
			myDb.addjsquiz(1, "Decision Tree", "", "0 %");

			curTotal = prevTotal + 10;
			QuizSession.StoreTotal1(Integer.toString(curTotal));
			QuizSession.FinishSessionNum1(Integer.toString(sum));
			intent = new Intent(DecisionTreeAssessmentJumbleWord.this,
					DecisionTreeRandomQuiz.class);
			intent.putExtra("retakeNum", sum);
			startActivity(intent);
			DecisionTreeAssessmentJumbleWord.this
					.overridePendingTransition(R.anim.slide_in_left,
							R.anim.slide_out_left);
			DecisionTreeAssessmentJumbleWord.this.finish();
		}
	} else {
		QuizSession.StoreFlLastQuizTaken(finalDate);
		QuizSession.StoreAllLastQuizTaken(finalDate);
		int passVal = Integer.parseInt(initVal);
		myDb.addjsquiz(1, "Decision Tree", initVal, "0 %");
		curTotal = prevTotal + 10;
		QuizSession.StoreTotal1(Integer.toString(curTotal));
		QuizSession.FinishSessionNum1(initVal);
		intent = new Intent(DecisionTreeAssessmentJumbleWord.this,
				DecisionTreeRandomQuiz.class);
		intent.putExtra("retakeNum", passVal);
		startActivity(intent);
		DecisionTreeAssessmentJumbleWord.this.overridePendingTransition(
				R.anim.slide_in_left, R.anim.slide_out_left);
		DecisionTreeAssessmentJumbleWord.this.finish();

	}

	}
	
private void openDB() {
		
		myDb = new DBAdapter(DecisionTreeAssessmentJumbleWord.this);
		myDb.open();
	}

	public void onBackPressed() {

	}
}
