package descisiondiscussflip;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
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

@SuppressLint({ "FloatMath", "InflateParams", "ClickableViewAccessibility" })
public class DescTreeAdapter extends BaseAdapter {
	public TextToSpeech tts;
	private LayoutInflater inflater;

	private int repeatCount = 1;
	View layout;
	private List<TopicData.Data> desctreeData;
	private ImageView zoomerImageZoom;
	private Matrix matrix = new Matrix();
	private float scale = 1f;
	private ScaleGestureDetector SGD;
	public String toSpeak;
	MediaPlayer dtone, dttwo, dtthree, dtfour;

	public DescTreeAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		desctreeData = new ArrayList<TopicData.Data>(TopicData.IMG_DESCRIPTIONS);

		tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				if (status != TextToSpeech.ERROR) {
					tts.setLanguage(Locale.US);
				}
			}
		});

		dtone = MediaPlayer.create(context, R.raw.dtone);
		dttwo = MediaPlayer.create(context, R.raw.dttwo);
		dtthree = MediaPlayer.create(context, R.raw.dtthree);
		dtfour = MediaPlayer.create(context, R.raw.dtfour);

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

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		layout = convertView;
		if (convertView == null) {
			layout = inflater.inflate(R.layout.discusstopic_layout, null);
			AphidLog.d("created new view from adapter: %d", position);

			if (position == 1 || position == 2 || position == 3
					|| position == 4 || position == 5) {
				if ((dtone.isPlaying()) || (dttwo.isPlaying())
						|| (dtthree.isPlaying()) || (dtfour.isPlaying())) {
					dtone.stop();
					dttwo.stop();
					dtthree.stop();
					dtfour.stop();

				}
				UI.<ToggleButton> findViewById(layout, R.id.toggleButton)
						.setChecked(false);
			}
		}

		final TopicData.Data data = desctreeData.get(position
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
						SGD = new ScaleGestureDetector(InputFragmentView
								.getContext(), new ScaleListener());
						zoomerImageZoom = (ImageView) dialog
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

		UI.<com.capstoneii.iclassify.library.JustifyTextView> findViewById(
				layout, R.id.description).setText(
				Html.fromHtml(data.description));
	

		toSpeak = UI
				.<com.capstoneii.iclassify.library.JustifyTextView> findViewById(
						layout, R.id.description).getText().toString();
		UI.<ToggleButton> findViewById(layout, R.id.toggleButton);
		UI.<ToggleButton> findViewById(layout, R.id.toggleButton)
				.setOnCheckedChangeListener(
						new CompoundButton.OnCheckedChangeListener() {

							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {

								if (isChecked) {

									UI.<ToggleButton> findViewById(layout,
											R.id.toggleButton)
											.setChecked(false);
									if (position == 0) {
										dtone.start();
										dttwo.stop();
										dtthree.stop();
										dtfour.stop();

									} else if (position == 1) {
										dtone.stop();
										dttwo.start();
										dtthree.stop();
										dtfour.stop();

									} else if (position == 2) {
										dtone.stop();
										dttwo.stop();
										dtthree.start();
										dtfour.stop();
									} else if (position == 3) {
										dtone.stop();
										dttwo.stop();
										dtthree.stop();
										dtfour.start();
									}

								}
								if (!isChecked) {
									UI.<ToggleButton> findViewById(layout,
											R.id.toggleButton).setChecked(true);
									if ((dtone.isPlaying())
											&& (dttwo.isPlaying())
											&& (dtthree.isPlaying() && (dtfour
													.isPlaying()))) {
										dtone.stop();
										dttwo.stop();
										dtthree.stop();
										dtfour.stop();
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

	@SuppressWarnings("deprecation")
	public void onDestroy() {
		tts.stop();
		tts.shutdown();
		tts.speak(toSpeak, TextToSpeech.STOPPED, null);

	}

	public void onBackPressed() {
		tts.stop();
		tts.shutdown();

	}

	public void onStop() {
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}

	}

	public boolean onTouchEvent(MotionEvent ev) {
		SGD.onTouchEvent(ev);
		return true;
	}

	private class ScaleListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			scale *= detector.getScaleFactor();
			scale = Math.max(0.1f, Math.min(scale, 5.0f));
			matrix.setScale(scale, scale);
			zoomerImageZoom.setImageMatrix(matrix);
			return true;
		}
	}

}
