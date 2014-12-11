package knearestdiscussflip;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.util.FloatMath;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.aphidmobile.utils.UI;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SessionCache;
import com.capstoneii.iclassify.assessment.knn.KNNRandomQuiz;
import com.capstoneii.iclassify.dbclasses.DBAdapter;
import com.capstoneii.iclassify.library.JustifyTextView;

import drawer.MainDrawerActivity;

public class KNearestDiscussionContent extends ActionBarActivity {

	int counter;
	Button previousdiscussbt,nextdiscussbt;
	ToggleButton toggleButton;
	TextView title;
	ImageView photo;
	JustifyTextView description;
	public TextToSpeech tts;
	String toSpeak;
	private Matrix matrix = new Matrix();
	private float scale = 1f;
	private ScaleGestureDetector SGD;
	
	DBAdapter myDb;
	SessionCache QuizSession;

	int retake;
	int prevTotal;
	int curTotal;
	String finalDate;
	Intent intent;
	String initVal = "1";
	KNearestAdapter newadapter;
	
	 Matrix savedMatrix = new Matrix();
	 PointF startPoint = new PointF();
	 PointF midPoint = new PointF();
	 float oldDist = 1f;
	 static final int NONE = 0;
	 static final int DRAG = 1;
	 static final int ZOOM = 2;
	 int mode = NONE;

	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		intent = new Intent();
		QuizSession = new SessionCache(KNearestDiscussionContent.this);

		newadapter = new KNearestAdapter(this);

		openDB();

		Date date = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
		finalDate = timeFormat.format(date);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.discusstopic_layout);
		
		tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
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
		
		title.setText("K Nearest Neighbors - Classification");
		description.setText(R.string.discussionknnzero);
		
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
			       matrix.postScale(scale, scale, midPoint.x, midPoint.y);
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
		toggleButton.setOnCheckedChangeListener(
				new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(
							CompoundButton buttonView, boolean isChecked) {

						if (isChecked) {
							toggleButton.setChecked(true);	
							toSpeak = description.getText().toString();
							tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
						}else{
							if(tts!=null){
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
					if(tts!=null){
						tts.stop();
					}else{
						tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
					}
				}
				
				if(counter > 5){
					
				}else{
				switch(counter){
				
				case 0:
					title.setText("K Nearest Neighbors - Classification");
					description.setText(R.string.discussionknnzero);
					nextdiscussbt.setVisibility(View.VISIBLE);
					break;
				
				case 1:
					title.setText("Algorithm");
					photo.setImageResource(R.drawable.knnimg1);
					description.setText(R.string.discussionknnone);
					previousdiscussbt.setVisibility(View.VISIBLE);
					
					break;
				case 2:
					title.setText("");
					photo.setImageResource(R.drawable.knnimg2);
					description.setText(R.string.discussionknntwo);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 3:
					title.setText("");
					photo.destroyDrawingCache();
					description.setText(R.string.discussionknnthree);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 4:
					title.setText("Example:");
					photo.setImageResource(R.drawable.knnimg3);
					description.setText(R.string.discussionknnfour);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 5:
					title.setText("Step 1: ");
					photo.setImageResource(R.drawable.knnimg4);
					description.setText(R.string.discussionknnfive);
					previousdiscussbt.setVisibility(View.VISIBLE);
					nextdiscussbt.setVisibility(View.INVISIBLE);
					break;
					
					default:
						break;
				
				}
				}
				
			}
		});
		
		previousdiscussbt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v == previousdiscussbt) {
					counter--;
					toggleButton.setChecked(false);	
					if(tts!=null){
						tts.stop();
					}else{
						tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
					}
				}
				
				
				switch(counter){
				case 5:
					title.setText("Step 1: ");
					photo.setImageResource(R.drawable.knnimg4);
					description.setText(R.string.discussionknnfive);
					previousdiscussbt.setVisibility(View.VISIBLE);
					nextdiscussbt.setVisibility(View.INVISIBLE);
					
				case 4:
					title.setText("Example:");
					photo.setImageResource(R.drawable.knnimg3);
					description.setText(R.string.discussionknnfour);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 3:
					title.setText("");
					photo.destroyDrawingCache();
					description.setText(R.string.discussionknnthree);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 2:
					title.setText("");
					photo.setImageResource(R.drawable.knnimg2);
					description.setText(R.string.discussionknntwo);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					

				case 1:
					title.setText("Algorithm");
					photo.setImageResource(R.drawable.knnimg1);
					description.setText(R.string.discussionknnone);
					previousdiscussbt.setVisibility(View.VISIBLE);
				case 0:
					title.setText("K Nearest Neighbors - Classification");
					description.setText(R.string.discussionknnzero);
					previousdiscussbt.setVisibility(View.INVISIBLE);
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
			Intent intent2 = new Intent(this, KNearestObjectives.class);
			intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			startActivity(intent2);
			super.onStop();
			android.os.Process.killProcess(android.os.Process.myPid());
			KNearestDiscussionContent.this.finish();
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

								if (QuizSession.hasFlQuiz1()) {

									final Dialog dialog = new Dialog(
											KNearestDiscussionContent.this,
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
												myDb.deleteQuiz("K Nearest Neighbor");

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
														"K Nearest Neighbor 1");

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
														"K Nearest Neighbor",
														"", "0 %");

												QuizSession
														.FinishSessionNum1(Integer
																.toString(sum));
												intent = new Intent(
														KNearestDiscussionContent.this,
														KNNRandomQuiz.class);
												intent.putExtra("retakeNum",
														sum);
												startActivity(intent);
												dialog.dismiss();
												KNearestDiscussionContent.this
														.overridePendingTransition(
																R.anim.slide_in_left,
																R.anim.slide_out_left);
												KNearestDiscussionContent.this
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
												myDb.deleteQuiz("K Nearest Neighbor");

												QuizSession
														.StoreFlLastQuizTaken(finalDate);
												QuizSession
														.StoreAllLastQuizTaken(finalDate);

												myDb.deletescorerowSet(2,
														"K Nearest Neighbor 1");

												int sum = retake + 1;// 5
												myDb.addjsquiz(1,
														"K Nearest Neighbor",
														"", "0 %");

												QuizSession
														.FinishSessionNum1(Integer
																.toString(sum));
												intent = new Intent(
														KNearestDiscussionContent.this,
														KNNRandomQuiz.class);
												intent.putExtra("retakeNum",
														sum);
												startActivity(intent);
												dialog.dismiss();
												KNearestDiscussionContent.this
														.overridePendingTransition(
																R.anim.slide_in_left,
																R.anim.slide_out_left);
												KNearestDiscussionContent.this
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
												myDb.deleteQuiz("K Nearest Neighbor");

												QuizSession
														.StoreFlLastQuizTaken(finalDate);
												QuizSession
														.StoreAllLastQuizTaken(finalDate);

												myDb.deletescorerowSet(2,
														"K Nearest Neighbor 1");

												int sum = retake + 1;// 5
												myDb.addjsquiz(1,
														"K Nearest Neighbor",
														"", "0 %");

												QuizSession
														.FinishSessionNum1(Integer
																.toString(sum));
												intent = new Intent(
														KNearestDiscussionContent.this,
														KNNRandomQuiz.class);
												intent.putExtra("retakeNum",
														sum);
												startActivity(intent);
												dialog.dismiss();
												KNearestDiscussionContent.this
														.overridePendingTransition(
																R.anim.slide_in_left,
																R.anim.slide_out_left);
												KNearestDiscussionContent.this
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
										myDb.deleteQuiz("K Nearest Neighbor");
										QuizSession
												.StoreFlLastQuizTaken(finalDate);
										QuizSession
												.StoreAllLastQuizTaken(finalDate);

										int sum = retake + 1;
										myDb.addjsquiz(1, "K Nearest Neighbor",
												"", "0 %");

										curTotal = prevTotal + 10;
										QuizSession.StoreTotal1(Integer
												.toString(curTotal));
										QuizSession.FinishSessionNum1(Integer
												.toString(sum));
										intent = new Intent(
												KNearestDiscussionContent.this,
												KNNRandomQuiz.class);
										intent.putExtra("retakeNum", sum);
										startActivity(intent);
										KNearestDiscussionContent.this
												.overridePendingTransition(
														R.anim.slide_in_left,
														R.anim.slide_out_left);
										KNearestDiscussionContent.this.finish();
									}
								} else {
									QuizSession.StoreFlLastQuizTaken(finalDate);
									QuizSession
											.StoreAllLastQuizTaken(finalDate);
									int passVal = Integer.parseInt(initVal);
									myDb.addjsquiz(1, "K Nearest Neighbor",
											initVal, "0 %");
									curTotal = prevTotal + 10;
									QuizSession.StoreTotal1(Integer
											.toString(curTotal));
									QuizSession.FinishSessionNum1(initVal);
									intent = new Intent(
											KNearestDiscussionContent.this,
											KNNRandomQuiz.class);
									intent.putExtra("retakeNum", passVal);
									startActivity(intent);
									KNearestDiscussionContent.this
											.overridePendingTransition(
													R.anim.slide_in_left,
													R.anim.slide_out_left);
									KNearestDiscussionContent.this.finish();

								}

								/*
								 * Intent intent = new
								 * Intent(KNearestLayoutActivity.this,
								 * KNNAssessmentDragAndDrop.class);
								 * KNearestLayoutActivity
								 * .this.startActivity(intent);
								 * KNearestLayoutActivity.this.finish();
								 */

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

		myDb = new DBAdapter(KNearestDiscussionContent.this);
		myDb.open();
	}

	
	
	
}
