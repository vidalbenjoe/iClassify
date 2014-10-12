package knearestdiscussflip;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TypewriterTextView;
import com.capstoneii.iclassify.videos.VideoMenuActivity;


public class KNearestObjectives extends ActionBarActivity {
	TextView titleobjectives;
	TypewriterTextView objectivedesc;
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
		titleobjectives.setText("K- Nearest Neighbor");
		objectivedesc = (TypewriterTextView) findViewById(R.id.objectivedesc);
		objectivedesc.setTypewriterText(getString(R.string.knearestobjectives));
		
		
		startdiscussionbutton= (ImageView) findViewById(R.id.startdiscussionbutton);
		watchvideosbutton= (ImageView) findViewById(R.id.watchvideosbutton);
		
		startdiscussionbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Intent intent = new Intent(KNearestObjectives.this,
						KNearestLayoutActivity.class);
				KNearestObjectives.this.startActivity(intent);
				KNearestObjectives.this.finish();
			}
		});
		
		watchvideosbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Intent intent = new Intent(KNearestObjectives.this,
						VideoMenuActivity.class);
				KNearestObjectives.this.startActivity(intent);
				KNearestObjectives.this.finish();
			}
		});
		
	}
}