package drawer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import com.capstoneii.iclassify.MainActivityController;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.problems.ChooseProblemActivity;
import com.capstoneii.iclassify.simulation.desiciontree.SimulStartActivity;
import com.capstoneii.iclassify.simulation.knn.SimulMainKnnActivity;
import com.capstoneii.iclassify.simulation.naivebayes.SimulNaiveBayesIntroFragment;
import com.capstoneii.iclassify.videos.VideoMenuActivity;


@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainDrawerActivity extends ActionBarActivity  {
List<Map<String, String>> moreList;
private PopupWindow pwMyPopWindow;
private ListView lvPopupList;
private int NUM_OF_VISIBLE_LIST_ROWS = 3;
	
 String[] menutitles;
 TypedArray menuIcons;

 // nav drawer title
 private CharSequence mDrawerTitle;
 private CharSequence mTitle;

 private DrawerLayout mDrawerLayout;
 private ListView mDrawerList;
 private ActionBarDrawerToggle mDrawerToggle;

 private List<RowItem> rowItems;
 private CustomAdapter adapter;

 private FrameLayout frame;
 private float lastTranslate = 0.0f;

 @Override
 protected void onCreate(Bundle savedInstanceState) {

  super.onCreate(savedInstanceState);
  getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.divider_color)));
 
  setContentView(R.layout.drawer_layout);
  
  iniData();
  iniPopupWindow();

  mTitle = mDrawerTitle = getTitle();

  menutitles = getResources().getStringArray(R.array.titles);
  menuIcons = getResources().obtainTypedArray(R.array.icons);

  mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
  mDrawerList = (ListView) findViewById(R.id.slider_list);
  frame = (FrameLayout) findViewById(R.id.frame_container);

  
  int width = getResources().getDisplayMetrics().widthPixels/2;
  DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) mDrawerList.getLayoutParams();
  params.width = width;
  mDrawerList.setLayoutParams(params);
  

  rowItems = new ArrayList<RowItem>();

  for (int i = 0; i < menutitles.length; i++) {
   RowItem items = new RowItem(menutitles[i], menuIcons.getResourceId(
     i, -1));
   rowItems.add(items);
  }

  menuIcons.recycle();

  adapter = new CustomAdapter(getApplicationContext(), rowItems);

  mDrawerList.setAdapter(adapter);
  mDrawerList.setOnItemClickListener(new SlideitemListener());

  // enabling action bar app icon and behaving it as toggle button
  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  getSupportActionBar().setHomeButtonEnabled(true);

  mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
    R.drawable.ic_drawer, R.string.app_name,R.string.app_name) {
       public void onDrawerClosed(View view) {
    	   getSupportActionBar().setTitle(mTitle);
         // calling onPrepareOptionsMenu() to show action bar icons
         invalidateOptionsMenu();
 
       }

        public void onDrawerOpened(View drawerView) {
        	getSupportActionBar().setTitle(mDrawerTitle);
               // calling onPrepareOptionsMenu() to hide action bar icons
              invalidateOptionsMenu();
         }
        
        @SuppressLint("NewApi")
        public void onDrawerSlide(View drawerView, float slideOffset)
        {
            float moveFactor = (mDrawerList.getWidth() * slideOffset);
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) 
            {
                frame.setTranslationX(moveFactor);
            }
            else
            {
                TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                anim.setDuration(0);
                anim.setFillAfter(true);
                frame.startAnimation(anim);
                lastTranslate = moveFactor;
            }
        }
       
  };

  mDrawerLayout.setDrawerListener(mDrawerToggle);

  if (savedInstanceState == null) {
       // on first time display view for first nav item
       updateDisplay(0);
     }
 }

 class SlideitemListener implements ListView.OnItemClickListener {
       @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                 updateDisplay(position);
            }
      }

 private void updateDisplay(int position) {
      Fragment fragment = null;
         switch (position) {
               case 0://discussion
                  fragment = new MainActivityController();
                            break;
               case 1://videos
            	   Intent intent1 = new Intent(this, VideoMenuActivity.class);
	           	 	this.startActivity(intent1);
            	 
                           break;
               case 2://simulation
            	 
            		if (pwMyPopWindow.isShowing()) {
            			pwMyPopWindow.dismiss();
            			} else {

            			pwMyPopWindow.showAsDropDown(lvPopupList);
            			}
                           break;
               case 3://assessment
            	  
            	  fragment = new ChooseProblemActivity();
                           break;          
                           
                           
               case 4://assessment
             	  
            	  finish();
                           break;          
              default:
                         break;
    }

  if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
             fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
            // update selected item and title, then close the drawer
            setTitle(menutitles[position]);
             mDrawerLayout.closeDrawer(mDrawerList);
  } else {
              // error in creating fragment
              Log.e("MainActivity", "Error in creating fragment");
            }

  }
     @Override
     public void setTitle(CharSequence title) {
         mTitle = title;
         getSupportActionBar().setTitle(mTitle);
     }
    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        
         return true;
     }
 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
  // toggle nav drawer on selecting action bar app icon/title
          if (mDrawerToggle.onOptionsItemSelected(item)) {
                     return true;
              }
             // Handle action bar actions click
           /* switch (item.getItemId()) {
                    case R.id.menu_overflow:
                    	  return true;
                    default :
                             
                }*/
          return super.onOptionsItemSelected(item);
 }

 /***
  * Called when invalidateOptionsMenu() is triggered
  */
    @Override
     public boolean onPrepareOptionsMenu(Menu menu) {
               // if nav drawer is opened, hide the action items
                boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
                menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
                return super.onPrepareOptionsMenu(menu);
      }
  
 /**
  * When using the ActionBarDrawerToggle, you must call it during
  * onPostCreate() and onConfigurationChanged()...
  */

    @Override
     protected void onPostCreate(Bundle savedInstanceState) {
         super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
         mDrawerToggle.syncState();
   }

     @Override
     public void onConfigurationChanged(Configuration newConfig) {
         super.onConfigurationChanged(newConfig);
          // Pass any configuration change to the drawer toggles
           mDrawerToggle.onConfigurationChanged(newConfig);
    }
     
     
     private void iniData() {

  		moreList = new ArrayList<Map<String, String>>();
  		Map<String, String> map;
  		map = new HashMap<String, String>();
  		map.put("share_key", "Decision Tree Algorithm");
  		moreList.add(map);
  		map = new HashMap<String, String>();
  		map.put("share_key", "Native Bayesian");
  		moreList.add(map);
  		map = new HashMap<String, String>();
  		map.put("share_key", "K-Nearest Neighbor");
  		moreList.add(map);
  		}

  		private void iniPopupWindow() {

  		LayoutInflater inflater = (LayoutInflater) this
  		.getSystemService(LAYOUT_INFLATER_SERVICE);
  		View layout = inflater.inflate(R.layout.task_detail_popupwindow, null);
  		lvPopupList = (ListView) layout.findViewById(R.id.lv_popup_list);
  		pwMyPopWindow = new PopupWindow(layout);
  		pwMyPopWindow.setFocusable(true);

  		lvPopupList.setAdapter(new SimpleAdapter(MainDrawerActivity.this, moreList,
  		R.layout.list_item_popupwindow, new String[] { "share_key" },
  		new int[] { R.id.tv_list_item }));
  		lvPopupList.setOnItemClickListener(new OnItemClickListener() {

  		public void onItemClick(AdapterView<?> parent, View view,
  		int position, long id) {
  			
  			switch(position){
  			case 0 :
  				Fragment SimulProcessIntroActivity = new SimulStartActivity();
            	FragmentTransaction ft0  = getFragmentManager().beginTransaction();
            	ft0.replace(R.id.frame_container, SimulProcessIntroActivity);
            	//ft.addToBackStack(null);
            	ft0.commit();
            	mDrawerLayout.closeDrawer(mDrawerList);
            	pwMyPopWindow.dismiss();
                getSupportActionBar().setTitle("Decision Tree");
                
  				break;
  			case 1:
  				
  				Fragment SimulNaiveBayesIntroFragment = new SimulNaiveBayesIntroFragment();
            	FragmentTransaction ft1  = getFragmentManager().beginTransaction();
            	ft1.replace(R.id.frame_container, SimulNaiveBayesIntroFragment);
            	//ft.addToBackStack(null);
            	ft1.commit();
            	mDrawerLayout.closeDrawer(mDrawerList);
            	pwMyPopWindow.dismiss();
                getSupportActionBar().setTitle("Naive Bayesian");
  				
  				break;
  				
  			case 2: 
  				Fragment SimulMainKnnActivity = new SimulMainKnnActivity();
            	FragmentTransaction ft2  = getFragmentManager().beginTransaction();
            	ft2.replace(R.id.frame_container, SimulMainKnnActivity);
            	//ft.addToBackStack(null);
            	ft2.commit();
            	mDrawerLayout.closeDrawer(mDrawerList);
            	pwMyPopWindow.dismiss();
                getSupportActionBar().setTitle("K - Nearest Neighbor");
  				
  				
  				break;
  				
  				default:
  				break;
  			
  			}

  		}
  		});

  		lvPopupList.measure(View.MeasureSpec.UNSPECIFIED,
  		View.MeasureSpec.UNSPECIFIED);
  		pwMyPopWindow.setWidth(lvPopupList.getMeasuredWidth());
  		pwMyPopWindow.setHeight((lvPopupList.getMeasuredHeight() + 20)
  		* NUM_OF_VISIBLE_LIST_ROWS);

  		pwMyPopWindow.setBackgroundDrawable(this.getResources().getDrawable(
  		R.drawable.bg_popupwindow));
  		pwMyPopWindow.setOutsideTouchable(true);
  		}
public void onBackPressed(){
	
}
 	
} 