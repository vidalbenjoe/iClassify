package com.capstoneii.iclassify.simulation.naivebayes;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.problems.SimpsonQuizProblemResult.SimulCalculateActivity;

public class NaiveQuizProblemResult extends FragmentActivity {
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
				return new NaiveImageResult();
			case 1:
				return new NaiveImageResult();
			case 2:
				return new NaiveImageResult();
			case 3:
				return new NaiveImageResult2();
			case 4:
				return new NaiveImageResult3();
			case 5:
				return new NaiveImageResult4();
			case 6:
				return new NaiveImageResult7();
			case 7:
				return new naiveexample();
			case 8:
				return new NaiveImageResult7();
			case 9:
				return new naiveexample();
			case 10:
				return new naiveexample();
			case 11:
				return new naivebayesquestiondomestic();
			case 12:
				return new naivecom();
			case 13:
				return new naivebayesquestiondomestic();
			case 14:
				return new NaiveImageResult();
			case 15:
				return new naivebayesquestiondomestic();
			
			default:
				return null;
			}
		}

		@Override
		public int getCount() {
			return 16;
		}

	}
	
	
	public static class NaiveImageResult extends Fragment  {

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.backgroundfordialog,
					container, false);
			ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
			imgaeresult.setImageResource(R.drawable.naiveresultcarone);
					return rootView;
					}

		}
	
	public static class NaiveImageResult2 extends Fragment  {

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.backgroundfordialog,
					container, false);
			ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
			imgaeresult.setImageResource(R.drawable.naiveresultpolicetwo);
					return rootView;
					}

		}
	
	public static class NaiveImageResult3 extends Fragment  {

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.backgroundfordialog,
					container, false);
			ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
			imgaeresult.setImageResource(R.drawable.naiveresultpolicethree);
					return rootView;
					}

		}
	
	public static class NaiveImageResult4 extends Fragment  {

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.backgroundfordialog,
					container, false);
			ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
			imgaeresult.setImageResource(R.drawable.naiveresultpolicefour);
					return rootView;
					}

		}
	
	public static class NaiveImageResult5 extends Fragment  {

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.backgroundfordialog,
					container, false);
			ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
			imgaeresult.setImageResource(R.drawable.naiveresultpolicefive);
					return rootView;
					}

		}
	
	public static class NaiveImageResult6 extends Fragment  {

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.backgroundfordialog,
					container, false);
			ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
			imgaeresult.setImageResource(R.drawable.naiveresultpolicefive);
					return rootView;
					}

		}
	
	
	public static class NaiveImageResult7 extends Fragment  {

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.backgroundfordialog,
					container, false);
			ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
			imgaeresult.setImageResource(R.drawable.naivebayesquestiontable);
					return rootView;
					}

		}
	
	
	public static class naiveexample extends Fragment  {

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.backgroundfordialog,
					container, false);
			ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
			imgaeresult.setImageResource(R.drawable.naiveexample);
					return rootView;
					}

		}
	
	public static class naivebayesquestiondomestic extends Fragment  {

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.backgroundfordialog,
					container, false);
			ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
			imgaeresult.setImageResource(R.drawable.naivebayesquestiondomestic);
					return rootView;
					}

		}
	
	public static class naivecom extends Fragment  {

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.backgroundfordialog,
					container, false);
			ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
			imgaeresult.setImageResource(R.drawable.naivecom);
					return rootView;
					}

		}
	
	
}
