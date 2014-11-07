package com.capstoneii.iclassify.simulation.knn;

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

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.backgroundfordialog,
					container, false);
			ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
			imgaeresult.setImageResource(R.drawable.knnquizresultimage1);
					return rootView;
					}

		}
	
	public static class KnnImageResultFirstSetNext extends Fragment  {

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.backgroundfordialog,
					container, false);
			ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
			imgaeresult.setImageResource(R.drawable.knnquizresultimage2);
					return rootView;
					}

		}

	
	public static class KnnImageResultSecondSet extends Fragment  {

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.backgroundfordialog,
					container, false);
			ImageView imgaeresult = (ImageView) rootView.findViewById(R.id.imgaeresult);
			imgaeresult.setImageResource(R.drawable.knnquizresultimage3);
					return rootView;
					}

		}

	public static class KnnImageResultThirdSet extends Fragment  {

		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.knn_last_set_result_layout,
					container, false);
			ImageView imgaeresultknn = (ImageView) rootView.findViewById(R.id.imgaeresultknn);
			imgaeresultknn.setImageResource(R.drawable.knnquizresultimage4);
					return rootView;
					}

		}
	
	
}
