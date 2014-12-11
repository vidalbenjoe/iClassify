package descisiondiscussflip;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SessionCache;
import com.capstoneii.iclassify.assessment.decisionid3.DecisionTreeRandomQuiz;
import com.capstoneii.iclassify.dbclasses.DBAdapter;
import com.capstoneii.iclassify.library.JustifyTextView;

import drawer.MainDrawerActivity;

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

public class DescTreeDiscussionContent extends ActionBarActivity {
	
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
	DescTreeAdapter desctreeadapter;
	
	
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
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.discusstopic_layout);
SGD = new ScaleGestureDetector(this,new ScaleListener());
		
		tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				if (status != TextToSpeech.ERROR) {
					tts.setLanguage(Locale.US);
				}
			}
		});
		
		intent = new Intent();
		QuizSession = new SessionCache(DescTreeDiscussionContent.this);
		openDB();

		desctreeadapter = new DescTreeAdapter(this);
		Date date = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
		finalDate = timeFormat.format(date);
		
		title = (TextView) findViewById(R.id.title);
		photo = (ImageView) findViewById(R.id.photo);
		description = (JustifyTextView) findViewById(R.id.description);
		
		nextdiscussbt = (Button) findViewById(R.id.nextdiscussbt);
		
		title.setText("Decision tree");
		description.setText(R.string.discussionknnzero);
		photo.setImageResource(R.drawable.desctreeimg1);
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
		
		
		previousdiscussbt = (Button) findViewById(R.id.previousdiscussbt);
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
				
			
				switch(counter){
				
				case 0:
					title.setText("Decision tree");
					description.setText(R.string.discussiondesczero);
					photo.setImageResource(R.drawable.desctreeimg1);
					previousdiscussbt.setVisibility(View.INVISIBLE);
					break;
				
				case 1:
					title.setText("Entropy");
					photo.setImageResource(R.drawable.desctreeimg2);
					description.setText(R.string.discussiondescone);
					previousdiscussbt.setVisibility(View.VISIBLE);
					
					break;
				case 2:
					title.setText("");
					photo.setImageResource(R.drawable.desctreeimg3);
					description.setText(R.string.discussiondesctwo);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 3:
					title.setText("");
					photo.setImageResource(R.drawable.desctreeimg4);
					description.setText(R.string.discussiondescfour);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 4:
					title.setText("Information Gain");
					photo.setImageResource(R.drawable.desctreeimg4);
					description.setText(R.string.discussiondescthree);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 5:
					title.setText("Step 1");
					description.setText(R.string.discussiondescsix);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 6:
					title.setText("Step 2");
					description.setText(R.string.discussiondescseven);
					photo.setImageResource(R.drawable.desctreeimg5);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 7:
					title.setText("Step 3");
					description.setText(R.string.discussiondesceight);
					photo.setImageResource(R.drawable.desctreeimg6);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 8:
					title.setText("Step 4a");
					description.setText(R.string.discussiondescnine);
					photo.setImageResource(R.drawable.desctreeimg7);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 9:
					title.setText("Step 4b");
					description.setText(R.string.discussiondescten);
					photo.setImageResource(R.drawable.desctreeimg8);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 10:
					title.setText("Step 5");
					description.setText(R.string.discussiondesceleven);
					photo.setImageResource(R.drawable.desctreeimg9);
					previousdiscussbt.setVisibility(View.VISIBLE);
					break;
					
				case 11:
					title.setText("Decision Tree to Decision Rules");
					description.setText(R.string.discussiondesctwelve);
					photo.setImageResource(R.drawable.desctreeimg10);
					previousdiscussbt.setVisibility(View.VISIBLE);
					nextdiscussbt.setVisibility(View.INVISIBLE);
					
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
						if(tts!=null){
							tts.stop();
						}else{
							tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
						}
					}
					
					
					switch(counter){
					case 0:
						title.setText("Decision tree");
						description.setText(R.string.discussiondesczero);
						photo.setImageResource(R.drawable.desctreeimg1);
						previousdiscussbt.setVisibility(View.INVISIBLE);
						nextdiscussbt.setVisibility(View.VISIBLE);
						
						break;
					
					case 1:
						title.setText("Entropy");
						photo.setImageResource(R.drawable.desctreeimg2);
						description.setText(R.string.discussiondescone);
						previousdiscussbt.setVisibility(View.VISIBLE);
						
						break;
					case 2:
						title.setText("");
						photo.setImageResource(R.drawable.desctreeimg3);
						description.setText(R.string.discussiondesctwo);
						previousdiscussbt.setVisibility(View.VISIBLE);
						break;
						
					case 3:
						title.setText("");
						photo.setImageResource(R.drawable.desctreeimg4);
						description.setText(R.string.discussiondescfour);
						previousdiscussbt.setVisibility(View.VISIBLE);
						break;
						
					case 4:
						title.setText("Information Gain");
						photo.setImageResource(R.drawable.desctreeimg4);
						description.setText(R.string.discussiondescthree);
						previousdiscussbt.setVisibility(View.VISIBLE);
						break;
						
					case 5:
						title.setText("Step 1");
						description.setText(R.string.discussiondescsix);
						previousdiscussbt.setVisibility(View.VISIBLE);
						break;
						
					case 6:
						title.setText("Step 2");
						description.setText(R.string.discussiondescseven);
						photo.setImageResource(R.drawable.desctreeimg5);
						previousdiscussbt.setVisibility(View.VISIBLE);
						break;
						
					case 7:
						title.setText("Step 3");
						description.setText(R.string.discussiondesceight);
						photo.setImageResource(R.drawable.desctreeimg6);
						previousdiscussbt.setVisibility(View.VISIBLE);
						break;
						
					case 8:
						title.setText("Step 4a");
						description.setText(R.string.discussiondescnine);
						photo.setImageResource(R.drawable.desctreeimg7);
						previousdiscussbt.setVisibility(View.VISIBLE);
						break;
						
					case 9:
						title.setText("Step 4b");
						description.setText(R.string.discussiondescten);
						photo.setImageResource(R.drawable.desctreeimg8);
						previousdiscussbt.setVisibility(View.VISIBLE);
						break;
						
					case 10:
						title.setText("Step 5");
						description.setText(R.string.discussiondesceleven);
						photo.setImageResource(R.drawable.desctreeimg9);
						previousdiscussbt.setVisibility(View.VISIBLE);
						break;
						
					case 11:
						title.setText("Decision Tree to Decision Rules");
						description.setText(R.string.discussiondesctwelve);
						photo.setImageResource(R.drawable.desctreeimg10);
						previousdiscussbt.setVisibility(View.VISIBLE);
						nextdiscussbt.setVisibility(View.INVISIBLE);
						break;
						
			
						default:
							break;
					}
					
				
				}
			});
		
		
	}
	

	public boolean onTouchEvent(MotionEvent ev) {
		SGD.onTouchEvent(ev);
		return true;
	}

	private class ScaleListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			scale *= detector.getScaleFactor();
			scale = Math.max(0.1f, Math.min(scale, 5.0f));
			matrix.setScale(scale, scale);
			photo.setImageMatrix(matrix);
			return true;
		}
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
											DescTreeDiscussionContent.this,
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
														DescTreeDiscussionContent.this,
														DecisionTreeRandomQuiz.class);
												intent.putExtra("retakeNum",
														sum);
												startActivity(intent);
												dialog.dismiss();
												DescTreeDiscussionContent.this
														.overridePendingTransition(
																R.anim.slide_in_left,
																R.anim.slide_out_left);
												DescTreeDiscussionContent.this
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
														DescTreeDiscussionContent.this,
														DecisionTreeRandomQuiz.class);
												intent.putExtra("retakeNum",
														sum);
												startActivity(intent);
												dialog.dismiss();
												DescTreeDiscussionContent.this
														.overridePendingTransition(
																R.anim.slide_in_left,
																R.anim.slide_out_left);
												DescTreeDiscussionContent.this
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
														DescTreeDiscussionContent.this,
														DecisionTreeRandomQuiz.class);
												intent.putExtra("retakeNum",
														sum);
												startActivity(intent);
												dialog.dismiss();
												DescTreeDiscussionContent.this
														.overridePendingTransition(
																R.anim.slide_in_left,
																R.anim.slide_out_left);
												DescTreeDiscussionContent.this
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
												DescTreeDiscussionContent.this,
												DecisionTreeRandomQuiz.class);
										intent.putExtra("retakeNum", sum);
										startActivity(intent);
										DescTreeDiscussionContent.this
												.overridePendingTransition(
														R.anim.slide_in_left,
														R.anim.slide_out_left);
										DescTreeDiscussionContent.this.finish();
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
											DescTreeDiscussionContent.this,
											DecisionTreeRandomQuiz.class);
									intent.putExtra("retakeNum", passVal);
									startActivity(intent);
									DescTreeDiscussionContent.this
											.overridePendingTransition(
													R.anim.slide_in_left,
													R.anim.slide_out_left);
									DescTreeDiscussionContent.this.finish();

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
		myDb = new DBAdapter(DescTreeDiscussionContent.this);
		myDb.open();
	}
	
	

}
