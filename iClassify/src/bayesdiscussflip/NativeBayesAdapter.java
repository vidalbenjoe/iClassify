package bayesdiscussflip;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.aphidmobile.utils.AphidLog;
import com.aphidmobile.utils.IO;
import com.aphidmobile.utils.UI;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.library.TouchImageView;

public class NativeBayesAdapter extends BaseAdapter {

	boolean isChecked;
	ToggleButton toggleButton;

	private LayoutInflater inflater;

	private int repeatCount = 1;

	private List<NativeBayesData.Data> desctreeData;

	public TextToSpeech tts;

	private TouchImageView zoomerImageZoom;
	View layout;
	public DecimalFormat df;
	MediaPlayer nbone, nbtwo, nbthree, nbfour;
	public NativeBayesAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		desctreeData = new ArrayList<NativeBayesData.Data>(
				NativeBayesData.IMG_DESCRIPTIONS);
		df = new DecimalFormat("#.##");
		tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				if (status != TextToSpeech.ERROR) {
					tts.setLanguage(Locale.US);
				}
			}
		});
		
		nbone = MediaPlayer.create(context, R.raw.nbone);
		nbtwo = MediaPlayer.create(context, R.raw.nbtwo);
		nbthree = MediaPlayer.create(context, R.raw.nbthree);
		nbfour = MediaPlayer.create(context, R.raw.nbfour);
	}

	@Override
	public int getCount() {
		return desctreeData.size() * repeatCount;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint({ "InflateParams", "ClickableViewAccessibility", "FloatMath" })
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		layout = convertView;
		if (convertView == null) {
			layout = inflater.inflate(R.layout.discusstopic_layout, null);
			AphidLog.d("created new view from adapter: %d", position);
			
			if(position == 1 || position == 2 || position == 3 || position == 4 || position == 5){
				if ((nbone.isPlaying())
						|| (nbtwo.isPlaying())
						|| (nbthree.isPlaying())
						|| (nbfour.isPlaying())) {
					nbone.stop();
					nbtwo.stop();
					nbthree.stop();
					nbfour.stop();
					
				}
				UI.<ToggleButton> findViewById(layout, R.id.toggleButton).setChecked(false);		
			}
		}

		final NativeBayesData.Data data = desctreeData.get(position
				% desctreeData.size());

		UI.<TextView> findViewById(layout, R.id.title).setText(
				AphidLog.format("%s", data.title));

		UI.<ImageView> findViewById(layout, R.id.photo).setImageBitmap(
				IO.readBitmap(inflater.getContext().getAssets(),
						data.imageFilename));

		UI.<ImageView> findViewById(layout, R.id.photo).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View InputFragmentView) {
						final Dialog dialog = new Dialog(InputFragmentView
								.getContext());
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			
						dialog.setContentView(R.layout.image_zoomer_dialog);
						dialog.setCancelable(true);
						dialog.getWindow().setBackgroundDrawable(
								new ColorDrawable(
										android.graphics.Color.TRANSPARENT));

						zoomerImageZoom = (TouchImageView) dialog
								.findViewById(R.id.zoomerImageZoom);

						zoomerImageZoom.setImageBitmap(IO.readBitmap(inflater
								.getContext().getAssets(), data.imageFilename));

						zoomerImageZoom
								.setOnClickListener(new View.OnClickListener() {
									@Override
									public void onClick(View InputFragmentView) {
										dialog.dismiss();
									}
								});
						dialog.show();
					}
				});

		UI.<com.capstoneii.iclassify.library.SecretTextView> findViewById(
				layout, R.id.description).setText(
				Html.fromHtml(data.description));
		UI.<com.capstoneii.iclassify.library.SecretTextView> findViewById(
				layout, R.id.description).setmDuration(500);
		UI.<com.capstoneii.iclassify.library.SecretTextView> findViewById(
				layout, R.id.description).setIsVisible(false);
		UI.<com.capstoneii.iclassify.library.SecretTextView> findViewById(
				layout, R.id.description).toggle();

		@SuppressWarnings("unused")
		final String toSpeak = UI
				.<com.capstoneii.iclassify.library.SecretTextView> findViewById(
						layout, R.id.description).getText().toString();

		UI.<ToggleButton> findViewById(layout, R.id.toggleButton);
		UI.<ToggleButton> findViewById(layout, R.id.toggleButton)
				.setOnCheckedChangeListener(
						new CompoundButton.OnCheckedChangeListener() {

							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {

								if (isChecked) {
									
									UI.<ToggleButton> findViewById(layout, R.id.toggleButton).setChecked(false);
									if (position == 0) {
										nbone.start();
										nbtwo.stop();
										nbthree.stop();
										nbfour.stop();
										
									} else if (position == 1) {
										nbone.stop();
										nbtwo.start();
										nbthree.stop();
										nbfour.stop();
										
									} else if (position == 2) {
										nbone.stop();
										nbtwo.stop();
										nbthree.start();
										nbfour.stop();
									}else if (position == 3) {
										nbone.stop();
										nbtwo.stop();
										nbthree.stop();
										nbfour.start();
									}

								} if (!isChecked) {
									UI.<ToggleButton> findViewById(layout, R.id.toggleButton).setChecked(true);		
									if ((nbone.isPlaying())
											|| (nbtwo.isPlaying())
											|| (nbthree.isPlaying())) {
										nbone.stop();
										nbtwo.stop();
										nbthree.stop();
										nbfour.stop();
									}

								}
							}
						});

		return layout;
	}

	public void removeData(int index) {
		if (desctreeData.size() > 1) {
			desctreeData.remove(index);
		}
	}

	public void onPause() {
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
	}

	public void onDestroy() {
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}

	}

}
