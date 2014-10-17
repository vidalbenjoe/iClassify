package com.capstoneii.iclassify.assessment.decisionid3;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.capstoneii.iclassify.R;

import descisiondiscussflip.DescTreeObjectives;

public class DecisionTreeAssessmentJumbleWord extends ActionBarActivity {
	TextView jumblequestionText;
	ImageView imgt, imgr, imgy, imgp, imgn, imge, imgo;
	EditText jumbleedittext;
	Button checkbt, clearbt;
	Animation animationZoom, zoomOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assessment_jumbled_letter_first);

		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));

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
		Intent intent = new Intent(DecisionTreeAssessmentJumbleWord.this,
				DescTreeObjectives.class);
		DecisionTreeAssessmentJumbleWord.this.startActivity(intent);
		DecisionTreeAssessmentJumbleWord.this.finish();
	}

	public void onBackPressed() {

	}
}
