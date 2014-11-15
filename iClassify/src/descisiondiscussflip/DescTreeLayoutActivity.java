package descisiondiscussflip;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.aphidmobile.flip.FlipViewController;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SessionCache;
import com.capstoneii.iclassify.assessment.decisionid3.DecisionTreeRandomQuiz;
import com.capstoneii.iclassify.dbclasses.DBAdapter;

@SuppressLint({ "NewApi", "SimpleDateFormat" })
public class DescTreeLayoutActivity extends ActionBarActivity {
	private FlipViewController flipView;
	DBAdapter myDb;
	SessionCache QuizSession;

	int retake;
	int prevTotal;
	int curTotal;
	String finalDate;
	Intent intent;
	String initVal = "1";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		intent = new Intent();
		QuizSession = new SessionCache(DescTreeLayoutActivity.this);
		openDB();

		Date date = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
		finalDate = timeFormat.format(date);

		flipView = new FlipViewController(this);
		// Use RGB_565 can reduce peak memory usage on large screen device, but
		// it's up to you to choose the best bitmap format
		flipView.setAnimationBitmapFormat(Bitmap.Config.RGB_565);
		flipView.setAdapter(new DescTreeAdapter(this));

		setContentView(flipView);
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.transparent_popuptext);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		final ImageView transpaimage = (ImageView) dialog
				.findViewById(R.id.transpaimage);
		transpaimage.setVisibility(View.VISIBLE);
		transpaimage.setImageResource(R.drawable.tutsimg);

		transpaimage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				transpaimage.setVisibility(View.GONE);
				dialog.cancel();
			}
		});

		dialog.show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		flipView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		flipView.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent3 = new Intent(this, DescTreeObjectives.class);
			intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent3);
			this.finish();
			// app icon in action bar clicked; go home
			return true;

		case R.id.menu_overflow:
			View menuItemView = findViewById(R.id.menu_overflow); // SAME ID AS
																	// MENU ID
			PopupMenu popupMenu = new PopupMenu(this, menuItemView);
			popupMenu.inflate(R.menu.main);

			popupMenu
					.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
						public boolean onMenuItemClick(MenuItem item) {
							switch (item.getItemId()) {
							case R.id.action_settings:
								// go to assessment

								if (QuizSession.hasFlQuiz3()) {

									final Dialog dialog = new Dialog(
											DescTreeLayoutActivity.this,
											R.style.DialogAnim);
									dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
									dialog.setContentView(R.layout.validate_message);

									Button bYes = (Button) dialog
											.findViewById(R.id.buttonOk);
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
												QuizSession
														.StoreFlLastQuizTaken(finalDate);
												QuizSession
														.StoreAllLastQuizTaken(finalDate);

												// delete the scorerow if the
												// user wants to
												// overwrite the first take of
												// quiz
												myDb.deletescorerowSet(1,
														"Decision Tree 1");

												// get the retake value + 1
												// sum is 4 so when the user try
												// to take the quiz
												// again, he will not able to
												// take it any more, he
												// will the next condition which
												// will appear
												// "You have taken this 4 times"
												int sum = retake + 1;
												myDb.addjsquiz(1,
														"Decision Tree", "",
														"0 %");

												QuizSession
														.FinishSessionNum1(Integer
																.toString(sum));
												intent = new Intent(
														DescTreeLayoutActivity.this,
														DecisionTreeRandomQuiz.class);
												intent.putExtra("retakeNum",
														sum);
												startActivity(intent);
												dialog.dismiss();
												DescTreeLayoutActivity.this
														.overridePendingTransition(
																R.anim.slide_in_left,
																R.anim.slide_out_left);
												DescTreeLayoutActivity.this
														.finish();
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

												QuizSession
														.StoreFlLastQuizTaken(finalDate);
												QuizSession
														.StoreAllLastQuizTaken(finalDate);

												myDb.deletescorerowSet(2,
														"Decision Tree 1");

												int sum = retake + 1;// 5
												myDb.addjsquiz(1,
														"Decision Tree", "",
														"0 %");

												QuizSession
														.FinishSessionNum1(Integer
																.toString(sum));
												intent = new Intent(
														DescTreeLayoutActivity.this,
														DecisionTreeRandomQuiz.class);
												intent.putExtra("retakeNum",
														sum);
												startActivity(intent);
												dialog.dismiss();
												DescTreeLayoutActivity.this
														.overridePendingTransition(
																R.anim.slide_in_left,
																R.anim.slide_out_left);
												DescTreeLayoutActivity.this
														.finish();

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

												QuizSession
														.StoreFlLastQuizTaken(finalDate);
												QuizSession
														.StoreAllLastQuizTaken(finalDate);

												myDb.deletescorerowSet(2,
														"Decision Tree 1");

												int sum = retake + 1;// 5
												myDb.addjsquiz(1,
														"Decision Tree", "",
														"0 %");

												QuizSession
														.FinishSessionNum1(Integer
																.toString(sum));
												intent = new Intent(
														DescTreeLayoutActivity.this,
														DecisionTreeRandomQuiz.class);
												intent.putExtra("retakeNum",
														sum);
												startActivity(intent);
												dialog.dismiss();
												DescTreeLayoutActivity.this
														.overridePendingTransition(
																R.anim.slide_in_left,
																R.anim.slide_out_left);
												DescTreeLayoutActivity.this
														.finish();
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
										QuizSession
												.StoreFlLastQuizTaken(finalDate);
										QuizSession
												.StoreAllLastQuizTaken(finalDate);

										int sum = retake + 1;
										myDb.addjsquiz(1, "Decision Tree", "",
												"0 %");

										curTotal = prevTotal + 10;
										QuizSession.StoreTotal1(Integer
												.toString(curTotal));
										QuizSession.FinishSessionNum1(Integer
												.toString(sum));
										intent = new Intent(
												DescTreeLayoutActivity.this,
												DecisionTreeRandomQuiz.class);
										intent.putExtra("retakeNum", sum);
										startActivity(intent);
										DescTreeLayoutActivity.this
												.overridePendingTransition(
														R.anim.slide_in_left,
														R.anim.slide_out_left);
										DescTreeLayoutActivity.this.finish();
									}
								} else {
									QuizSession.StoreFlLastQuizTaken(finalDate);
									QuizSession
											.StoreAllLastQuizTaken(finalDate);
									int passVal = Integer.parseInt(initVal);
									myDb.addjsquiz(1, "Decision Tree", initVal,
											"0 %");
									curTotal = prevTotal + 10;
									QuizSession.StoreTotal1(Integer
											.toString(curTotal));
									QuizSession.FinishSessionNum1(initVal);
									intent = new Intent(
											DescTreeLayoutActivity.this,
											DecisionTreeRandomQuiz.class);
									intent.putExtra("retakeNum", passVal);
									startActivity(intent);
									DescTreeLayoutActivity.this
											.overridePendingTransition(
													R.anim.slide_in_left,
													R.anim.slide_out_left);
									DescTreeLayoutActivity.this.finish();

								}
								
								return true;
							}
							return true;
						}
					});

			popupMenu.show();

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/***
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return super.onPrepareOptionsMenu(menu);
	}

	private void openDB() {
		myDb = new DBAdapter(DescTreeLayoutActivity.this);
		myDb.open();
	}

}
