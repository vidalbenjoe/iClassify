package bayesdiscussflip;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.aphidmobile.flip.FlipViewController;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.assessment.bayesian.BayesianAssessmentActivity;

import drawer.MainDrawerActivity;

@SuppressLint("NewApi")
public class NativeBayesLayoutActivity extends ActionBarActivity {

  private FlipViewController flipView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.divider_color)));
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    flipView = new FlipViewController(this);

    //Use RGB_565 can reduce peak memory usage on large screen device, but it's up to you to choose the best bitmap format 
    flipView.setAnimationBitmapFormat(Bitmap.Config.RGB_565);

    flipView.setAdapter(new NativeBayesAdapter(this));

    setContentView(flipView);
    final Dialog dialog = new Dialog(this);
	 dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
   dialog.setContentView(R.layout.transparent_popuptext);
   dialog.setCancelable(true);
   dialog.setCanceledOnTouchOutside(true);
   dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
   final ImageView transpaimage = (ImageView) dialog.findViewById(R.id.transpaimage);
   transpaimage.setVisibility(View.VISIBLE);
   transpaimage.setImageResource(R.drawable.tutsimg);
   
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
	  Intent intent = new Intent(this, MainDrawerActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(intent);
  // app icon in action bar clicked; go home
  return true;
  
  case R.id.menu_overflow:
	  View menuItemView = findViewById(R.id.menu_overflow); // SAME ID AS MENU ID
	    PopupMenu popupMenu = new PopupMenu(this, menuItemView); 
	    popupMenu.inflate(R.menu.main);
	    
	    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
               switch(item.getItemId()){
               case R.id.action_settings:
            	   //go to assessment
            	  
            		 Intent intent = new Intent(NativeBayesLayoutActivity.this, BayesianAssessmentActivity.class);
            		 NativeBayesLayoutActivity.this.startActivity(intent);
            		 NativeBayesLayoutActivity.this.finish();
            	 
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
   
  
  

}
