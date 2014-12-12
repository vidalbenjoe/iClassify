package com.capstoneii.iclassify.problems;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.assessment.decisionid3.DecisionTreeAssessmentActivity;
import com.capstoneii.iclassify.library.SecretTextView;
import com.capstoneii.iclassify.library.TypewriterTextView;

import descisiondiscussflip.DescTreeObjectives;

public class SimpsonQuizProblemResult extends FragmentActivity {
	int item;
	ViewPager mViewPager;
	AppPagerAdapter mAppPageAdapter;
	public static SimulCalculateActivity fragment;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager_view);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		mAppPageAdapter = new AppPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mAppPageAdapter);
		item = getIntent().getExtras().getInt("item");
		mViewPager.setCurrentItem(item);

	}

	
	public static class AppPagerAdapter extends FragmentPagerAdapter {

		public AppPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			switch (i) {
			case 0:
				return new SimpsonTableFragment();
			case 1:
				return new SimpsonTableFragment();
			case 2:
				return new SimpsonTableFragment();
			case 3:
				return new SimpsonTableFragment();
			case 4:
				return new SimpsonMaleClassifier();
			case 5:
				return new SimpsonMaleClassifier();
			case 6:
				return new SimpsonTableFragment();
			case 7:
				return new SimulDecisionResults();
			case 8:
				return new SimulDecisionResults();
			case 9:
				return new SimulDecisionResults();
			case 10:
				return new SimulDecisionResults();
			case 11:
				return new SimulDecisionResults();
			case 12:
				return new ImageDialog();
			case 13:
				return new ImageDialog2();
			case 14:
				return new ImageDialog3();
			case 15:
				return new ImageDialog4();
			case 16:
				return new ImageDialog5();
		
			default:
				return null;
			}
		}

		@Override
		public int getCount() {
			return 17;
		}

	}
	
	public static class DecisionCalculateDialog extends Fragment implements
		android.view.animation.Animation.AnimationListener {
	private ImageView gainoutlook, gainhumid, gainwind;
	Animation left, right, leftout, rightout, animFadein, animFadeout;
	private Button simulnextwithquestion;
	Button drag;
	LinearLayout drop;
	TextView text, sucess;
	int total, failure = 0;
	Animation animSideDown;
	private Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	PointF startPoint = new PointF();
	PointF midPoint = new PointF();
	float oldDist = 1f;
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;
	public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		}
		
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.simulcalculate_activity,
				container, false);
				
				final Dialog dialog = new Dialog(getActivity());
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);
				dialog.getWindow().setBackgroundDrawable(
						new ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.setCancelable(false);

				left = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.left);
				right = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.right);
				// load the animation

				final ImageView formulaimg = (ImageView) dialog
						.findViewById(R.id.formulaimg);
				final ImageView heirarimg = (ImageView) dialog
						.findViewById(R.id.heirarimg);

				formulaimg.setImageResource(R.drawable.outlookdragimg);
				heirarimg.setImageResource(R.drawable.outlookheirar);
				
				
				
				
				formulaimg.setOnTouchListener(new View.OnTouchListener() {

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

				
				heirarimg.setOnTouchListener(new View.OnTouchListener() {

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

				
				

				animSideDown = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.slide_down);

				Button windweak = (Button) dialog.findViewById(R.id.windweak);
				Button windstrong = (Button) dialog
						.findViewById(R.id.windstrong);
				windweak.setBackgroundResource(R.drawable.outlookrain);
				windstrong.setBackgroundResource(R.drawable.outlooksunny);

				windweak.startAnimation(left);
				windstrong.startAnimation(right);

				TextView entropytitle = (TextView) dialog
						.findViewById(R.id.entropytitle);
				entropytitle.setText("Gain(S,Outlook)");
				final TextView formulatext = (TextView) dialog
						.findViewById(R.id.formulatext);
				formulatext.setText(R.string.o1);
				formulatext.setVisibility(View.INVISIBLE);
				dialog.show();

				formulaimg.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						heirarimg.setVisibility(View.VISIBLE);
						heirarimg.startAnimation(animSideDown);
						formulatext.setVisibility(View.VISIBLE);
					}
				});

				windstrong.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// show popup

						final Dialog dialog = new Dialog(getActivity());
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.popup);
						dialog.getWindow().setBackgroundDrawable(
								new ColorDrawable(
										android.graphics.Color.TRANSPARENT));
						TextView strongtext = (TextView) dialog
								.findViewById(R.id.occurence);
						strongtext.setText(R.string.outlookoccurencessunny);
						dialog.show();
					}
				});

				windweak.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						final Dialog dialog = new Dialog(getActivity());
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.popup);
						dialog.getWindow().setBackgroundDrawable(
								new ColorDrawable(
										android.graphics.Color.TRANSPARENT));
						TextView weaktext = (TextView) dialog
								.findViewById(R.id.occurence);
						weaktext.setText(R.string.outlookoccurencesrain);
						dialog.show();

					}
				});

				Button declineButton = (Button) dialog
						.findViewById(R.id.cadbtnOk);
				// if decline button is clicked, close the custom dialog
				declineButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();

					}
				});
				return rootView;
				}

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
public static class ImageDialog extends Fragment  {
	private Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	PointF startPoint = new PointF();
	PointF midPoint = new PointF();
	float oldDist = 1f;
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;
public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View rootView = inflater.inflate(R.layout.backgroundfordialog,
			container, false);
	ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
	imgaeresult.setImageResource(R.drawable.desresultcarone);
	
	
	
	imgaeresult.setOnTouchListener(new View.OnTouchListener() {

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

	
	
//	final Dialog dialog = new Dialog(getActivity());
//	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//	dialog.setContentView(R.layout.correct_dialog);
//	dialog.getWindow().setBackgroundDrawable(
//			new ColorDrawable(android.graphics.Color.TRANSPARENT));
//	dialog.setCancelable(true);
//	
//	
//	
//	
//	ImageView correctcheck = (ImageView) dialog
//			.findViewById(R.id.correctcheck);
//	correctcheck.setImageResource(R.drawable.desresultcarone);
//	
//	dialog.show();
			
			return rootView;
			}

	
}

public static class ImageDialog2 extends Fragment  {
	private Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	PointF startPoint = new PointF();
	PointF midPoint = new PointF();
	float oldDist = 1f;
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;
public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View rootView = inflater.inflate(R.layout.backgroundfordialog,
			container, false);
	ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
	imgaeresult.setImageResource(R.drawable.desresultcartwo);
	
	
	imgaeresult.setOnTouchListener(new View.OnTouchListener() {

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

	
			return rootView;
			}

	
}


public static class ImageDialog3 extends Fragment  {
	private Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	PointF startPoint = new PointF();
	PointF midPoint = new PointF();
	float oldDist = 1f;
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;
public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View rootView = inflater.inflate(R.layout.backgroundfordialog,
			container, false);
	ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
	imgaeresult.setImageResource(R.drawable.desresultcarthree);
	
	
	imgaeresult.setOnTouchListener(new View.OnTouchListener() {

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

	
			return rootView;
			}

	
}


public static class ImageDialog4 extends Fragment  {
	private Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	PointF startPoint = new PointF();
	PointF midPoint = new PointF();
	float oldDist = 1f;
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;
public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View rootView = inflater.inflate(R.layout.backgroundfordialog,
			container, false);
	ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
	imgaeresult.setImageResource(R.drawable.desresultcarfour);
	
	
	imgaeresult.setOnTouchListener(new View.OnTouchListener() {

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

	
			return rootView;
			}
}


public static class ImageDialog5 extends Fragment  {
	private Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	PointF startPoint = new PointF();
	PointF midPoint = new PointF();
	float oldDist = 1f;
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;
public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View rootView = inflater.inflate(R.layout.backgroundfordialog,
			container, false);
	ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
	imgaeresult.setImageResource(R.drawable.desresultcarfive);
	
	
	imgaeresult.setOnTouchListener(new View.OnTouchListener() {

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

	
			return rootView;
			}

}


public static class SimpsonTableFragment extends Fragment {
		SecretTextView secretTextView;
		ImageView simpsoneTable;
		Button nextProcBt;
		private Matrix matrix = new Matrix();
		Matrix savedMatrix = new Matrix();
		PointF startPoint = new PointF();
		PointF midPoint = new PointF();
		float oldDist = 1f;
		static final int NONE = 0;
		static final int DRAG = 1;
		static final int ZOOM = 2;
		int mode = NONE;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.simpson_table_fragment,
					container, false);

			final TypewriterTextView simpsonAnimatedTextView = (TypewriterTextView) rootView
					.findViewById(R.id.simpsonAnimatedTextView);
			simpsonAnimatedTextView
					.setTypewriterText(getString(R.string.tablesimpson));

			simpsoneTable = (ImageView) rootView
					.findViewById(R.id.simpsoneTable);
			
			
	
			
			simpsoneTable.setOnTouchListener(new View.OnTouchListener() {

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

			
			
			
			
			simpsoneTable.setVisibility(View.VISIBLE);
			nextProcBt = (Button) rootView.findViewById(R.id.nextProcBt);
			nextProcBt.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View InputFragmentView) {

					// dialog box(Comic's Attribute)
					final Dialog dialog = new Dialog(getActivity());
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog_image);
					dialog.getWindow().setBackgroundDrawable(
							new ColorDrawable(
									android.graphics.Color.TRANSPARENT));

					TextView nameof = (TextView) dialog
							.findViewById(R.id.nameof);
					nameof.setText("Comic's Attribute");

					Button cadbtnNext = (Button) dialog
							.findViewById(R.id.cadbtnNext);
					dialog.show();

					cadbtnNext.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View InputFragmentView) {
							/*
							 * SimpsonsDecisionTreeFragment
							 * SimpsonsDecisionTreeFragment = new
							 * SimpsonsDecisionTreeFragment();
							 * android.support.v4.app.FragmentTransaction ft =
							 * getFragmentManager().beginTransaction();
							 * ft.replace(R.id.frame_container,
							 * SimpsonsDecisionTreeFragment);
							 * ft.setTransition(FragmentTransaction
							 * .TRANSIT_FRAGMENT_FADE); ft.commit();
							 * dialog.dismiss();
							 */
						}

					});
				}

			});

			return rootView;
		}

		public void onBackPressed() {

		}
	}
	
	
	public static class SimulCalculateActivity extends Fragment implements
		android.view.animation.Animation.AnimationListener {
	private ImageView gainoutlook, gainhumid, gainwind;
	Animation left, right, leftout, rightout, animFadein, animFadeout;
	private Button simulnextwithquestion;
	Button drag;
	LinearLayout drop;
	TextView text, sucess;
	int total, failure = 0;
	Animation animSideDown;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.simulcalculate_activity,
				container, false);

		left = AnimationUtils.loadAnimation(getActivity()
				.getApplicationContext(), R.anim.left);
		right = AnimationUtils.loadAnimation(getActivity()
				.getApplicationContext(), R.anim.right);
		leftout = AnimationUtils.loadAnimation(getActivity()
				.getApplicationContext(), R.anim.leftout);
		rightout = AnimationUtils.loadAnimation(getActivity()
				.getApplicationContext(), R.anim.rightout);
		animFadein = AnimationUtils.loadAnimation(getActivity()
				.getApplicationContext(), R.anim.fade_in);
		animFadeout = AnimationUtils.loadAnimation(getActivity()
				.getApplicationContext(), R.anim.fade_out);

		left.setAnimationListener(this);
		right.setAnimationListener(this);
		// animSideDown.setAnimationListener(this);

		drag = (Button) rootView.findViewById(R.id.one);
		drop = (LinearLayout) rootView.findViewById(R.id.bottomlinear);
		text = (TextView) rootView.findViewById(R.id.Total);
		sucess = (TextView) rootView.findViewById(R.id.Sucess);
		gainoutlook = (ImageView) rootView.findViewById(R.id.gainid1);

		gainhumid = (ImageView) rootView.findViewById(R.id.gainid3);
		gainwind = (ImageView) rootView.findViewById(R.id.gainid4);
		simulnextwithquestion = (Button) rootView
				.findViewById(R.id.simulnextcalculatebt);

		gainoutlook.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				out();
				final Dialog dialog = new Dialog(getActivity());
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);
				dialog.getWindow().setBackgroundDrawable(
						new ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.setCancelable(false);

				left = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.left);
				right = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.right);
				// load the animation

				final ImageView formulaimg = (ImageView) dialog
						.findViewById(R.id.formulaimg);
				final ImageView heirarimg = (ImageView) dialog
						.findViewById(R.id.heirarimg);

				formulaimg.setImageResource(R.drawable.outlookdragimg);
				heirarimg.setImageResource(R.drawable.outlookheirar);

				animSideDown = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.slide_down);

				Button windweak = (Button) dialog.findViewById(R.id.windweak);
				Button windstrong = (Button) dialog
						.findViewById(R.id.windstrong);
				windweak.setBackgroundResource(R.drawable.outlookrain);
				windstrong.setBackgroundResource(R.drawable.outlooksunny);

				windweak.startAnimation(left);
				windstrong.startAnimation(right);

				TextView entropytitle = (TextView) dialog
						.findViewById(R.id.entropytitle);
				entropytitle.setText("Gain(S,Outlook)");
				final TextView formulatext = (TextView) dialog
						.findViewById(R.id.formulatext);
				formulatext.setText(R.string.o1);
				formulatext.setVisibility(View.INVISIBLE);
				dialog.show();

				formulaimg.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						heirarimg.setVisibility(View.VISIBLE);
						heirarimg.startAnimation(animSideDown);
						formulatext.setVisibility(View.VISIBLE);
					}
				});

				windstrong.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// show popup

						final Dialog dialog = new Dialog(getActivity());
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.popup);
						dialog.getWindow().setBackgroundDrawable(
								new ColorDrawable(
										android.graphics.Color.TRANSPARENT));
						TextView strongtext = (TextView) dialog
								.findViewById(R.id.occurence);
						strongtext.setText(R.string.outlookoccurencessunny);
						dialog.show();
					}
				});

				windweak.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						final Dialog dialog = new Dialog(getActivity());
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.popup);
						dialog.getWindow().setBackgroundDrawable(
								new ColorDrawable(
										android.graphics.Color.TRANSPARENT));
						TextView weaktext = (TextView) dialog
								.findViewById(R.id.occurence);
						weaktext.setText(R.string.outlookoccurencesrain);
						dialog.show();

					}
				});

				Button declineButton = (Button) dialog
						.findViewById(R.id.cadbtnOk);
				// if decline button is clicked, close the custom dialog
				declineButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();

					}
				});

			}

		});

		gainhumid.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				out();
				final Dialog dialog = new Dialog(getActivity());
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);
				dialog.getWindow().setBackgroundDrawable(
						new ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.setTitle("Gain Wind Formula");
				dialog.setCancelable(false);

				left = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.left);
				right = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.right);
				// load the animation

				final ImageView formulaimg = (ImageView) dialog
						.findViewById(R.id.formulaimg);
				final ImageView heirarimg = (ImageView) dialog
						.findViewById(R.id.heirarimg);

				formulaimg.setImageResource(R.drawable.humiddragimg);
				heirarimg.setImageResource(R.drawable.humidityheirar);

				animSideDown = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.slide_down);
				Button windweak = (Button) dialog.findViewById(R.id.windweak);
				Button windstrong = (Button) dialog
						.findViewById(R.id.windstrong);
				windweak.setBackgroundResource(R.drawable.humidityhighl);
				windstrong.setBackgroundResource(R.drawable.humiditynormal);

				windweak.startAnimation(left);
				windstrong.startAnimation(right);

				TextView entropytitle = (TextView) dialog
						.findViewById(R.id.entropytitle);
				entropytitle.setText("Gain(S,Humidity)");
				final TextView formulatext = (TextView) dialog
						.findViewById(R.id.formulatext);
				formulatext.setText(R.string.h1);
				formulatext.setVisibility(View.INVISIBLE);
				dialog.show();

				formulaimg.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						heirarimg.setVisibility(View.VISIBLE);
						heirarimg.startAnimation(animSideDown);
						formulatext.setVisibility(View.VISIBLE);
					}
				});

				windstrong.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// show popup

						final Dialog dialog = new Dialog(getActivity());
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.popup);
						dialog.getWindow().setBackgroundDrawable(
								new ColorDrawable(
										android.graphics.Color.TRANSPARENT));
						TextView strongtext = (TextView) dialog
								.findViewById(R.id.occurence);
						strongtext.setText(R.string.humidityoccurencesnormal);
						dialog.show();
					}
				});

				windweak.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						final Dialog dialog = new Dialog(getActivity());
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.popup);
						dialog.getWindow().setBackgroundDrawable(
								new ColorDrawable(
										android.graphics.Color.TRANSPARENT));
						TextView weaktext = (TextView) dialog
								.findViewById(R.id.occurence);
						weaktext.setText(R.string.humidityoccurenceshigh);
						dialog.show();
					}
				});

				Button declineButton = (Button) dialog
						.findViewById(R.id.cadbtnOk);
				// if decline button is clicked, close the custom dialog
				declineButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();

					}
				});

			}

		});
		gainwind.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				out();
				final Dialog dialog = new Dialog(getActivity());
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);
				dialog.getWindow().setBackgroundDrawable(
						new ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.setTitle("Gain Wind Formula");
				dialog.setCancelable(false);

				left = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.left);
				right = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.right);
				// load the animation

				final ImageView formulaimg = (ImageView) dialog
						.findViewById(R.id.formulaimg);
				final ImageView heirarimg = (ImageView) dialog
						.findViewById(R.id.heirarimg);

				formulaimg.setImageResource(R.drawable.winddragimg);
				heirarimg.setImageResource(R.drawable.windheirar);

				animSideDown = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.slide_down);
				Button windweak = (Button) dialog.findViewById(R.id.windweak);
				Button windstrong = (Button) dialog
						.findViewById(R.id.windstrong);
				windweak.setBackgroundResource(R.drawable.weakwind);
				windstrong.setBackgroundResource(R.drawable.strongwind);

				windweak.startAnimation(left);
				windstrong.startAnimation(right);

				TextView entropytitle = (TextView) dialog
						.findViewById(R.id.entropytitle);
				entropytitle.setText("Gain(S,Wind)");
				final TextView formulatext = (TextView) dialog
						.findViewById(R.id.formulatext);
				formulatext.setText(R.string.w1);
				formulatext.setVisibility(View.INVISIBLE);
				dialog.show();

				formulaimg.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						heirarimg.setVisibility(View.VISIBLE);
						heirarimg.startAnimation(animSideDown);
						formulatext.setVisibility(View.VISIBLE);
					}
				});

				windstrong.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// show popup

						final Dialog dialog = new Dialog(getActivity());
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.popup);
						dialog.getWindow().setBackgroundDrawable(
								new ColorDrawable(
										android.graphics.Color.TRANSPARENT));
						TextView strongtext = (TextView) dialog
								.findViewById(R.id.occurence);
						strongtext.setText(R.string.windoccurencesstrong);
						dialog.show();
					}
				});

				windweak.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						final Dialog dialog = new Dialog(getActivity());
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.popup);
						dialog.getWindow().setBackgroundDrawable(
								new ColorDrawable(
										android.graphics.Color.TRANSPARENT));
						TextView weaktext = (TextView) dialog
								.findViewById(R.id.occurence);
						weaktext.setText(R.string.windoccurencesweak);
						dialog.show();
					}
				});

				Button declineButton = (Button) dialog
						.findViewById(R.id.cadbtnOk);
				// if decline button is clicked, close the custom dialog
				declineButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();

					}
				});

			}

		});
		simulnextwithquestion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				out();
//				Fragment SimulDragAndDropActivity = new SimulDragAndDropActivity();
//				FragmentTransaction ft = getFragmentManager()
//						.beginTransaction();
//				ft.replace(R.id.frame_container, SimulDragAndDropActivity);
//				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//				ft.addToBackStack(SimulDragAndDropActivity.getTag());
//				ft.commit();
			}
		});
		in();
		return rootView;

	}

	public void in() {
		gainoutlook.startAnimation(left);
		gainhumid.startAnimation(right);
		gainwind.startAnimation(left);
	}

	public void out() {
		gainoutlook.startAnimation(leftout);
		gainhumid.startAnimation(rightout);
		gainwind.startAnimation(leftout);

		gainoutlook.postDelayed(new Runnable() {
			@Override
			public void run() {
				in();
			}
		}, 1000);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// Take any action after completing the animation

		// check for zoom in animation
		if (animation == animSideDown) {
		}

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

}

	public static class SimpsonsDecisionTreeFragment extends Fragment {
		Button simpsonNextbt;
		RelativeLayout simpsonRelative;
		ImageView familyformula, gainimage, backgroundImage, splittingweight,
				simpsonFamilyTreeRoot, simpsonFamilyTreeHair;
		Animation animSideDown, animSideUp;
		int buttonNextClick = 0;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.simpson_dt_layout_one,
					container, false);

			backgroundImage = (ImageView) rootView
					.findViewById(R.id.backgroundImage);
			gainimage = (ImageView) rootView.findViewById(R.id.gainimage);
			simpsonFamilyTreeRoot = (ImageView) rootView
					.findViewById(R.id.simpsonFamilyTreeRoot);
			simpsonFamilyTreeHair = (ImageView) rootView
					.findViewById(R.id.simpsonFamilyTreeHair);
			splittingweight = (ImageView) rootView
					.findViewById(R.id.splittingweight);

			familyformula = (ImageView) rootView
					.findViewById(R.id.familyformula);
			simpsonNextbt = (Button) rootView.findViewById(R.id.simpsonNextbt);

			simpsonRelative = (RelativeLayout) rootView
					.findViewById(R.id.simpsonRelative);

			simpsonNextbt.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View InputFragmentView) {
					if (InputFragmentView == simpsonNextbt) {
						buttonNextClick++;
					}
					switch (buttonNextClick) {
					case 0:

						break;
					case 1:
						gainimage.setVisibility(View.VISIBLE);
						animSideDown = AnimationUtils.loadAnimation(
								getActivity().getApplicationContext(),
								R.anim.slide_down);

						animSideUp = AnimationUtils.loadAnimation(getActivity()
								.getApplicationContext(), R.anim.slide_up);
						backgroundImage.setVisibility(View.GONE);
						gainimage
								.setBackgroundResource(R.drawable.hairlengthgain);
						simpsonFamilyTreeRoot.setVisibility(View.VISIBLE);
						simpsonFamilyTreeHair.setVisibility(View.VISIBLE);
						simpsonFamilyTreeHair
								.setBackgroundResource(R.drawable.familyanswer);
						simpsonFamilyTreeHair.startAnimation(animSideDown);
						simpsonRelative
								.setBackgroundResource(R.drawable.backgroundblur);

						break;

					case 2:
						splittingweight.setVisibility(View.VISIBLE);
						gainimage.setVisibility(View.GONE);
						backgroundImage.setVisibility(View.GONE);
						simpsonFamilyTreeRoot.setVisibility(View.GONE);
						simpsonFamilyTreeHair.setVisibility(View.INVISIBLE);
						simpsonFamilyTreeHair.setVisibility(View.GONE);
						simpsonFamilyTreeHair.startAnimation(animSideUp);
						// simpsonRelative.setBackgroundResource(R.drawable.backgroundblur);
						familyformula.setVisibility(View.VISIBLE);
						break;
					case 3:
						splittingweight.setVisibility(View.GONE);
						gainimage
								.setBackgroundResource(R.drawable.weightsimpsongain);
						gainimage.setVisibility(View.VISIBLE);
						familyformula.setVisibility(View.GONE);
						simpsonFamilyTreeRoot.setVisibility(View.VISIBLE);

						simpsonFamilyTreeHair.setVisibility(View.VISIBLE);
						simpsonFamilyTreeHair
								.setBackgroundResource(R.drawable.weight160);
						simpsonFamilyTreeHair.startAnimation(animSideDown);
						break;

					case 4:
						/*
						 * Fragment SimpsonsNextProcedure = new
						 * SimpsonsNextProcedure(); FragmentTransaction ft =
						 * getFragmentManager().beginTransaction();
						 * ft.replace(R.id.frame_container,
						 * SimpsonsNextProcedure);
						 * ft.setTransition(FragmentTransaction
						 * .TRANSIT_FRAGMENT_FADE);
						 * ft.addToBackStack(SimpsonsNextProcedure.getTag());
						 * ft.commit();
						 */
						break;

					default:
						break;

					}

				}
			});

			return rootView;
		}

		public void onBackPressed() {

		}

	}

	public static class SimpsonsNextProcedure extends Fragment {
		SecretTextView secretTextView;
		ImageView proceduresimpfamily;
		Button nextProcBt;
		Animation animSideDown;
		private Matrix matrix = new Matrix();
		Matrix savedMatrix = new Matrix();
		PointF startPoint = new PointF();
		PointF midPoint = new PointF();
		float oldDist = 1f;
		static final int NONE = 0;
		static final int DRAG = 1;
		static final int ZOOM = 2;
		int mode = NONE;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.simpson_dt_next_proc_layout, container, false);

			final TypewriterTextView simpsonAnimatedTextView = (TypewriterTextView) rootView
					.findViewById(R.id.simpsonAnimatedTextView);
			simpsonAnimatedTextView
					.setTypewriterText(getString(R.string.SimpsonWeight));
			animSideDown = AnimationUtils.loadAnimation(getActivity()
					.getApplicationContext(), R.anim.slide_down);
			proceduresimpfamily = (ImageView) rootView
					.findViewById(R.id.proceduresimpfamily);
			proceduresimpfamily.setVisibility(View.INVISIBLE);

			
	
			
			proceduresimpfamily.setOnTouchListener(new View.OnTouchListener() {

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

			
			
			
			
			nextProcBt = (Button) rootView.findViewById(R.id.nextProcBt);

			nextProcBt.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View InputFragmentView) {
					/*
					 * Fragment SimpsonMaleClassifier = new
					 * SimpsonMaleClassifier(); FragmentTransaction ft =
					 * getFragmentManager().beginTransaction();
					 * ft.replace(R.id.frame_container, SimpsonMaleClassifier);
					 * ft
					 * .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE
					 * ); ft.addToBackStack(SimpsonMaleClassifier.getTag());
					 * ft.commit();
					 */
				}
			});

			delayFamAnim();

			return rootView;
		}

		public void delayFamAnim() {
			new CountDownTimer(6000, 1000) {

				public void onTick(long millisUntilFinished) {

				}

				public void onFinish() {
					proceduresimpfamily.setVisibility(View.VISIBLE);
					proceduresimpfamily.startAnimation(animSideDown);
				}
			}.start();
		}

		public void onBackPressed() {

		}
	}
	
	
	public static class SimulDecisionResults extends Fragment {
	public String videoFile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.simuldecisionresult_layout,
				container, false);

		Button watchvid = (Button) rootView.findViewById(R.id.watchvideoidr);
		Button takeassess = (Button) rootView.findViewById(R.id.takeassess);

		final Dialog dialog = new Dialog(getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.popup);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		TextView strongtext = (TextView) dialog.findViewById(R.id.occurence);
		strongtext.setText(R.string.humidityresult);
		dialog.show();

		watchvid.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {

				final Dialog dialog = new Dialog(getActivity());
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.watch_video_popup);
				dialog.getWindow().setBackgroundDrawable(
						new ColorDrawable(android.graphics.Color.TRANSPARENT));

				dialog.show();

			}
		});

		takeassess.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Intent intent = new Intent(getActivity(),
						DecisionTreeAssessmentActivity.class);
				getActivity().startActivity(intent);
			}
		});

		return rootView;
	}

	public void showPopup(View v) {
		PopupMenu popup = new PopupMenu(getActivity(), v);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.menu, popup.getMenu());
		popup.show();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		getActivity().getMenuInflater().inflate(R.menu.menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
	

	public static class SimpsonMaleClassifier extends Fragment {
		SecretTextView secretTextView;
		ImageView malesimpsons;
		Button weightYes, weightNo, ishairlenght, hairlengthYes, hairlengthNo,
				hairlengthMale, hairlengthFemale, maleResult, simpsonNextbt;
		Animation animSideDown, animSideUp, animationZoom, clock;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.simpson_male_classifier_layout, container, false);

			animationZoom = AnimationUtils.loadAnimation(getActivity(),
					R.anim.unzoom_in);
			animSideDown = AnimationUtils.loadAnimation(getActivity()
					.getApplicationContext(), R.anim.slide_down);

			animSideDown = AnimationUtils.loadAnimation(getActivity()
					.getApplicationContext(), R.anim.slide_down);

			clock = AnimationUtils.loadAnimation(getActivity()
					.getApplicationContext(), R.anim.clockwise);

			final TypewriterTextView simpsonMaleClassText = (TypewriterTextView) rootView
					.findViewById(R.id.simpsonMaleClassText);
			simpsonMaleClassText
					.setTypewriterText(getString(R.string.SimpsonMaleClass));
			animSideDown = AnimationUtils.loadAnimation(getActivity()
					.getApplicationContext(), R.anim.slide_down);

			malesimpsons = (ImageView) rootView.findViewById(R.id.malesimpsons);
			malesimpsons.startAnimation(animationZoom);

			weightYes = (Button) rootView.findViewById(R.id.weightYes);
			weightNo = (Button) rootView.findViewById(R.id.weightNo);

			ishairlenght = (Button) rootView.findViewById(R.id.ishairlenght);

			hairlengthYes = (Button) rootView.findViewById(R.id.hairlengthYes);
			hairlengthNo = (Button) rootView.findViewById(R.id.hairlengthNo);

			hairlengthMale = (Button) rootView
					.findViewById(R.id.hairlengthMale);
			hairlengthFemale = (Button) rootView
					.findViewById(R.id.hairlengthFemale);

			simpsonNextbt = (Button) rootView.findViewById(R.id.simpsonNextbt);
			maleResult = (Button) rootView.findViewById(R.id.maleResult);

			weightYes.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View InputFragmentView) {
					ishairlenght.setVisibility(View.VISIBLE);
					hairlengthYes.setVisibility(View.VISIBLE);
					hairlengthNo.setVisibility(View.VISIBLE);

					ishairlenght.startAnimation(animSideDown);
					hairlengthYes.startAnimation(animSideDown);
					hairlengthNo.startAnimation(animSideDown);
				}
			});

			hairlengthYes.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View InputFragmentView) {
					hairlengthMale.setVisibility(View.VISIBLE);

					Toast.makeText(getActivity(), "classify as Male",
							Toast.LENGTH_SHORT).show();
				}
			});
			hairlengthNo.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View InputFragmentView) {
					hairlengthFemale.setVisibility(View.VISIBLE);

					Toast.makeText(getActivity(), "classify as Female",
							Toast.LENGTH_SHORT).show();
				}
			});

			weightNo.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View InputFragmentView) {
					maleResult.setVisibility(View.VISIBLE);
					Toast.makeText(getActivity(), "classify as Male",
							Toast.LENGTH_SHORT).show();
				}
			});

			simpsonNextbt.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View InputFragmentView) {
					/*
					 * Fragment SimpsonFinalResult = new SimpsonFinalResult();
					 * FragmentTransaction ft = getFragmentManager()
					 * .beginTransaction(); ft.replace(R.id.frame_container,
					 * SimpsonFinalResult);
					 * ft.setTransition(FragmentTransaction.
					 * TRANSIT_FRAGMENT_FADE);
					 * ft.addToBackStack(SimpsonFinalResult.getTag());
					 * ft.commit();
					 */
				}
			});

			return rootView;
		}

	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			  Intent intent3 = new Intent(this, DescTreeObjectives.class);
		      intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		      startActivity(intent3);
		      this.finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}



