package bayesdiscussflip;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SessionCache;
import com.capstoneii.iclassify.assessment.bayesian.BayesianRandomQuiz;
import com.capstoneii.iclassify.dbclasses.DBAdapter;
import com.capstoneii.iclassify.library.JustifyTextView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.util.FloatMath;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.ToggleButton;

public class NativeBayesDiscussContent extends ActionBarActivity {
	int counter;
	Button previousdiscussbt, nextdiscussbt;
	ToggleButton toggleButton;
	TextView title;
	ImageView photo;
	JustifyTextView description;
	public TextToSpeech tts;
	String toSpeak;
	private Matrix matrix = new Matrix();

	DBAdapter myDb;
	SessionCache QuizSession;

	int retake;
	int prevTotal;
	int curTotal;
	String finalDate;
	Intent intent;
	String initVal = "1";

	Matrix savedMatrix = new Matrix();
	PointF startPoint = new PointF();
	PointF midPoint = new PointF();
	float oldDist = 1f;
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		intent = new Intent();
		QuizSession = new SessionCache(NativeBayesDiscussContent.this);

		openDB();

		Date date = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
		finalDate = timeFormat.format(date);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.discusstopic_layout);
		tts = new TextToSpeech(getApplicationContext(),
				new TextToSpeech.OnInitListener() {
					@Override
					public void onInit(int status) {
						if (status != TextToSpeech.ERROR) {
							tts.setLanguage(Locale.US);
						}
					}
				});
		title = (TextView) findViewById(R.id.title);
		photo = (ImageView) findViewById(R.id.photo);
		description = (JustifyTextView) findViewById(R.id.description);
		nextdiscussbt = (Button) findViewById(R.id.nextdiscussbt);
		
		title.setText("Naive Bayesian");
		description.setText(R.string.discussionbayeszero);
		previousdiscussbt = (Button) findViewById(R.id.previousdiscussbt);
		photo.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				ImageView view = (ImageView) v;
				System.out.println("matrix=" + savedMatrix.toString());
				switch (event.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_DOWN:

					savedMatrix.set(matrix);
					startPoint.set(event.getX(), event.getY());
					mode = DRAG;
					break;

				case MotionEvent.ACTION_POINTER_DOWN:

					oldDist = spacing(event);

					if (oldDist > 10f) {
						savedMatrix.set(matrix);
						midPoint(midPoint, event);
						mode = ZOOM;
					}
					break;

				case MotionEvent.ACTION_UP:

				case MotionEvent.ACTION_POINTER_UP:
					mode = NONE;

					break;

				case MotionEvent.ACTION_MOVE:
					if (mode == DRAG) {
						matrix.set(savedMatrix);
						matrix.postTranslate(event.getX() - startPoint.x,
								event.getY() - startPoint.y);
					} else if (mode == ZOOM) {
						float newDist = spacing(event);
						if (newDist > 10f) {
							matrix.set(savedMatrix);
							float scale = newDist / oldDist;
							matrix.postScale(scale, scale, midPoint.x,
									midPoint.y);
						}
					}
					break;

				}
				view.setImageMatrix(matrix);

				return true;
			}

			private float spacing(MotionEvent event) {
				float x = event.getX(0) - event.getX(1);
				float y = event.getY(0) - event.getY(1);
				return FloatMath.sqrt(x * x + y * y);
			}

			private void midPoint(PointF point, MotionEvent event) {
				float x = event.getX(0) + event.getX(1);
				float y = event.getY(0) + event.getY(1);
				point.set(x / 2, y / 2);
			}
		});

		toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
		toggleButton
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked) {
							toggleButton.setChecked(true);
							toSpeak = description.getText().toString();
							tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
						} else {
							if (tts != null) {
								tts.stop();
							}
						}

					}

				});

		nextdiscussbt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v == nextdiscussbt) {
					counter++;
					toggleButton.setChecked(false);
					if (tts != null) {
						tts.stop();
					} else {
						tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
					}
				}

				switch (counter) {

				case 0:
					title.setText("Naive Bayesian");
					description.setText(R.string.discussionbayeszero);
					nextdiscussbt.setVisibility(View.VISIBLE);
					break;
				case 1:
					title.setText("Algorithm");
					description.setText(R.string.discussionbayesone);
					photo.setImageResource(R.drawable.bayesimg1);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;	
					
				case 2:
					title.setText("Example");
					description.setText(R.string.discussionbayestwo);
					photo.setImageResource(R.drawable.bayesimg2);
					break;	
					
				case 3:
					title.setText("The zero-frequency problem");
					description.setText(R.string.discussionbayesthree);
					break;	
					
				case 4:
					title.setText("Numerical Predictors");
					description.setText(R.string.discussionbayesfour);
					photo.setImageResource(R.drawable.bayesimg3);
					break;
					
					
				case 5:
					title.setText("Predictors Contribution");
					description.setText(R.string.discussionbayesfour);
					photo.setImageResource(R.drawable.bayesimg4);
					break;
					
					
				case 6:
					title.setText("Predictors Contribution");
					description.setText(R.string.discussionbayesfive);
					photo.setImageResource(R.drawable.bayesimg5);
					nextdiscussbt.setVisibility(View.INVISIBLE);
					break;



				default:
					break;

				}
			}

		});

		previousdiscussbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v == previousdiscussbt) {
					counter--;
					toggleButton.setChecked(false);
					if (tts != null) {
						tts.stop();
					} else {
						tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
					}
				}

				switch (counter) {
				case 0:
					title.setText("Naive Bayesian");
					description.setText(R.string.discussionbayeszero);
					nextdiscussbt.setVisibility(View.VISIBLE);
					previousdiscussbt.setVisibility(View.INVISIBLE);
				
					break;
				case 1:
					title.setText("Algorithm");
					description.setText(R.string.discussionbayesone);
					photo.setImageResource(R.drawable.bayesimg1);
					nextdiscussbt.setVisibility(View.VISIBLE);
					break;	
					
				case 2:
					title.setText("Example");
					description.setText(R.string.discussionbayestwo);
					photo.setImageResource(R.drawable.bayesimg2);
					break;	
					
				case 3:
					title.setText("The zero-frequency problem");
					description.setText(R.string.discussionbayesthree);
					break;	
					
				case 4:
					title.setText("Numerical Predictors");
					description.setText(R.string.discussionbayesfour);
					photo.setImageResource(R.drawable.bayesimg3);
					break;
					
					
				case 5:
					title.setText("Predictors Contribution");
					description.setText(R.string.discussionbayesfour);
					photo.setImageResource(R.drawable.bayesimg4);
					break;
					
					
				case 6:
					title.setText("Predictors Contribution");
					description.setText(R.string.discussionbayesfive);
					photo.setImageResource(R.drawable.bayesimg5);
					nextdiscussbt.setVisibility(View.INVISIBLE);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;


				default:
					break;
				}

			}
		});
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
			Intent intent2 = new Intent(this, NativeBayesObjectives.class);
			intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent2);
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

								if (QuizSession.hasFlQuiz2()) {

									final Dialog dialog = new Dialog(
											NativeBayesDiscussContent.this,
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
												myDb.deleteQuiz("Naive Bayesian");

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
														"Naive Bayesian 1");

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
														"Naive Bayesian", "",
														"0 %");

												QuizSession
														.FinishSessionNum1(Integer
																.toString(sum));
												intent = new Intent(
														NativeBayesDiscussContent.this,
														BayesianRandomQuiz.class);
												intent.putExtra("retakeNum",
														sum);
												startActivity(intent);
												dialog.dismiss();
												NativeBayesDiscussContent.this
														.overridePendingTransition(
																R.anim.slide_in_left,
																R.anim.slide_out_left);
												NativeBayesDiscussContent.this
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
												myDb.deleteQuiz("Naive Bayesian");

												QuizSession
														.StoreFlLastQuizTaken(finalDate);
												QuizSession
														.StoreAllLastQuizTaken(finalDate);

												myDb.deletescorerowSet(2,
														"Naive Bayesian 1");

												int sum = retake + 1;// 5
												myDb.addjsquiz(1,
														"Naive Bayesian", "",
														"0 %");

												QuizSession
														.FinishSessionNum1(Integer
																.toString(sum));
												intent = new Intent(
														NativeBayesDiscussContent.this,
														BayesianRandomQuiz.class);
												intent.putExtra("retakeNum",
														sum);
												startActivity(intent);
												dialog.dismiss();
												NativeBayesDiscussContent.this
														.overridePendingTransition(
																R.anim.slide_in_left,
																R.anim.slide_out_left);
												NativeBayesDiscussContent.this
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
												myDb.deleteQuiz("Naive Bayesian");

												QuizSession
														.StoreFlLastQuizTaken(finalDate);
												QuizSession
														.StoreAllLastQuizTaken(finalDate);

												myDb.deletescorerowSet(2,
														"Naive Bayesian 1");

												int sum = retake + 1;// 5
												myDb.addjsquiz(1,
														"Naive Bayesian", "",
														"0 %");

												QuizSession
														.FinishSessionNum1(Integer
																.toString(sum));
												intent = new Intent(
														NativeBayesDiscussContent.this,
														BayesianRandomQuiz.class);
												intent.putExtra("retakeNum",
														sum);
												startActivity(intent);
												dialog.dismiss();
												NativeBayesDiscussContent.this
														.overridePendingTransition(
																R.anim.slide_in_left,
																R.anim.slide_out_left);
												NativeBayesDiscussContent.this
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
										myDb.deleteQuiz("Naive Bayesian");
										QuizSession
												.StoreFlLastQuizTaken(finalDate);
										QuizSession
												.StoreAllLastQuizTaken(finalDate);

										int sum = retake + 1;
										myDb.addjsquiz(1, "Naive Bayesian", "",
												"0 %");

										curTotal = prevTotal + 10;
										QuizSession.StoreTotal1(Integer
												.toString(curTotal));
										QuizSession.FinishSessionNum1(Integer
												.toString(sum));
										intent = new Intent(
												NativeBayesDiscussContent.this,
												BayesianRandomQuiz.class);
										intent.putExtra("retakeNum", sum);
										startActivity(intent);
										NativeBayesDiscussContent.this
												.overridePendingTransition(
														R.anim.slide_in_left,
														R.anim.slide_out_left);
										NativeBayesDiscussContent.this.finish();
									}
								} else {
									QuizSession.StoreFlLastQuizTaken(finalDate);
									QuizSession
											.StoreAllLastQuizTaken(finalDate);
									int passVal = Integer.parseInt(initVal);
									myDb.addjsquiz(1, "Naive Bayesian",
											initVal, "0 %");
									curTotal = prevTotal + 10;
									QuizSession.StoreTotal1(Integer
											.toString(curTotal));
									QuizSession.FinishSessionNum1(initVal);
									intent = new Intent(
											NativeBayesDiscussContent.this,
											BayesianRandomQuiz.class);
									intent.putExtra("retakeNum", passVal);
									startActivity(intent);
									NativeBayesDiscussContent.this
											.overridePendingTransition(
													R.anim.slide_in_left,
													R.anim.slide_out_left);
									NativeBayesDiscussContent.this.finish();

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

		myDb = new DBAdapter(NativeBayesDiscussContent.this);
		myDb.open();
	}

	
	@Override
	protected void onPause(){
		if(tts!=null){
			tts.stop();
		}
		
		super.onPause();
	}
	
	@Override
	protected void onDestroy(){
		if(tts!=null){
			tts.stop();
		}
		
		super.onDestroy();
	}
	
	@Override
	protected void onStop(){
		if(tts!=null){
			tts.stop();
		}
		
		super.onStop();
	}
	
}
