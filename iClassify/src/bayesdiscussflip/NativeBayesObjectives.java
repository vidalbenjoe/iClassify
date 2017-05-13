package bayesdiscussflip;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.JustifyTextView;
import com.capstoneii.iclassify.videos.NaiveVideo;

import drawer.MainDrawerActivity;

public class NativeBayesObjectives extends ActionBarActivity {
	TextView titleobjectives;
	JustifyTextView objectivedesc;
	ImageView startdiscussionbutton,watchvideosbutton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.objective_layout_menu);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		titleobjectives = (TextView) findViewById(R.id.titleobjectives);
		titleobjectives.setText("Naive Bayesian");
		objectivedesc = (JustifyTextView) findViewById(R.id.objectivedesc);
		//objectivedesc.setTypewriterText(getString(R.string.naivebayesobjectives));
		objectivedesc.setText(R.string.naivebayesobjectives);
		
		startdiscussionbutton= (ImageView) findViewById(R.id.startdiscussionbutton);
		watchvideosbutton= (ImageView) findViewById(R.id.watchvideosbutton);
		
		startdiscussionbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Intent intent = new Intent(NativeBayesObjectives.this,
						NativeBayesDiscussContent.class);
				NativeBayesObjectives.this.startActivity(intent);
				NativeBayesObjectives.this.finish();
			}
		});
		
		watchvideosbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Intent intent = new Intent(NativeBayesObjectives.this,
						NaiveVideo.class);
				NativeBayesObjectives.this.startActivity(intent);
				NativeBayesObjectives.this.finish();
			}
		});
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent2 = new Intent(this, MainDrawerActivity.class);
			intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent2);
			this.finish();
			// app icon in action bar clicked; go home
			return true;
			
		}
		return true;
	}
}
