package bayesdiscussflip;
import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import com.aphidmobile.flip.FlipViewController;
import com.capstoneii.iclassify.R;
import android.support.v7.app.ActionBarActivity;
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
  public boolean onOptionsItemSelected(MenuItem item) { 
    switch (item.getItemId()) {
  case android.R.id.home:
	  Intent intent = new Intent(this, MainDrawerActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(intent);
  // app icon in action bar clicked; go home
  return true;
  default:
  return super.onOptionsItemSelected(item); 
      }
  }

}
