package com.capstoneii.iclassify.problems;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.SecretTextView;
import com.capstoneii.iclassify.library.TypewriterTextView;

import descisiondiscussflip.DescTreeObjectives;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SimpsonQuizProblemResult extends FragmentActivity {

	ViewPager mViewPager;
	AppPagerAdapter mAppPageAdapter;

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
		int item = getIntent().getExtras().getInt("item");
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
				return new SimpsonsDecisionTreeFragment();
			case 2:
				return new SimpsonsNextProcedure();
			case 3:
				return new SimpsonMaleClassifier();
			case 4:
				return new SimpsonMaleClassifier();
			case 5:
				return new SimpsonsDecisionTreeFragment();
			case 6:
				return new SimpsonMaleClassifier();
			case 7:
				return new SimpsonsDecisionTreeFragment();
			case 8:
				return new SimpsonMaleClassifier();
			case 9:
				return new SimpsonMaleClassifier();
			case 10:
				return new SimpsonsDecisionTreeFragment();
			case 11:
				return new SimpsonMaleClassifier();
			case 13:
				return new SimpsonMaleClassifier();
			case 14:
				return new SimpsonsDecisionTreeFragment();
			case 15:
				return new SimpsonMaleClassifier();
			
			default:
				return null;
			}
		}

		@Override
		public int getCount() {
			return 16;
		}
	}

	public static class SimpsonTableFragment extends Fragment {
		SecretTextView secretTextView;
		ImageView simpsoneTable;
		Button nextProcBt;

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
