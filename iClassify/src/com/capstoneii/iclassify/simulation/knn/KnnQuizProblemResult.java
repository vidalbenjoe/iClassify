package com.capstoneii.iclassify.simulation.knn;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.problems.SimpsonQuizProblemResult.SimulCalculateActivity;

public class KnnQuizProblemResult extends FragmentActivity {
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
				return new KnnImageResultFirstSet();
			case 1:
				return new KnnImageResultFirstSet();
			case 2:
				return new KnnImageResultFirstSet();
			case 3:
				return new KnnImageResultFirstSet();
			case 4:
				return new KnnImageResultFirstSet();
			case 5:
				return new KnnImageResultFirstSetNext();
			case 6:
				return new KnnImageResultFirstSetNext();
			case 7:
				return new KnnImageResultSecondSet();
			case 8:
				return new KnnImageResultSecondSet();
			case 9:
				return new KnnImageResultSecondSet();
			case 10:
				return new KnnImageResultSecondSet();
			case 11:
				return new KnnImageResultSecondSet();
			case 12:
				return new KnnImageResultThirdSet();
			case 13:
				return new KnnImageResultThirdSet();
			case 14:
				return new KnnImageResultThirdSet();
			case 15:
				return new KnnImageResultThirdSet();
			case 16:
				return new KnnImageResultThirdSet();
			
			default:
				return null;
			}
		}

		@Override
		public int getCount() {
			return 16;
		}

	}
	
	
	public static class KnnImageResultFirstSet extends Fragment  {
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
			imgaeresult.setImageResource(R.drawable.knnquizresultimage1);
			
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
	
	public static class KnnImageResultFirstSetNext extends Fragment  {
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
			imgaeresult.setImageResource(R.drawable.knnquizresultimage2);
			
			
			
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

	
	public static class KnnImageResultSecondSet extends Fragment  {
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
			imgaeresult.setImageResource(R.drawable.knnquizresultimage3);
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

	public static class KnnImageResultThirdSet extends Fragment  {
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
			View rootView = inflater.inflate(R.layout.knn_last_set_result_layout,
					container, false);
			ImageView imgaeresultknn = (ImageView) rootView.findViewById(R.id.imgaeresultknn);
			imgaeresultknn.setImageResource(R.drawable.knnquizresultimage4);
			
			imgaeresultknn.setOnTouchListener(new View.OnTouchListener() {

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
	
	
}
