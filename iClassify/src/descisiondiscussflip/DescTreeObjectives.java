package descisiondiscussflip;

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

public class DescTreeObjectives extends ActionBarActivity {
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
		titleobjectives.setText("Decision Tree");
		objectivedesc = (TypewriterTextView) findViewById(R.id.objectivedesc);
		objectivedesc.setTypewriterText(getString(R.string.decisiontreeonjectives));
		
		
		startdiscussionbutton= (ImageView) findViewById(R.id.startdiscussionbutton);
		watchvideosbutton= (ImageView) findViewById(R.id.watchvideosbutton);
		
		startdiscussionbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Intent intent = new Intent(DescTreeObjectives.this,
						DescTreeLayoutActivity.class);
				DescTreeObjectives.this.startActivity(intent);
				DescTreeObjectives.this.finish();
			}
		});
		
		watchvideosbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View InputFragmentView) {
				Intent intent = new Intent(DescTreeObjectives.this,
						VideoMenuActivity.class);
				DescTreeObjectives.this.startActivity(intent);
				DescTreeObjectives.this.finish();
			}
		});
		
	}
}