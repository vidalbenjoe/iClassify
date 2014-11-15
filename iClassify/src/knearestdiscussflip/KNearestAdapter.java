package knearestdiscussflip;

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

public class KNearestAdapter extends BaseAdapter {
	public TextToSpeech tts;
	private LayoutInflater inflater;

	private int repeatCount = 1;

	private List<KNearestData.Data> desctreeData;

	private ImageView zoomerImageZoom;
	private Matrix matrix = new Matrix();
	private float scale = 1f;
	private ScaleGestureDetector SGD;
	public View layout;
	MediaPlayer knnone, knntwo, knnthree, knnfour, knnfive;

	public KNearestAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		desctreeData = new ArrayList<KNearestData.Data>(
				KNearestData.IMG_DESCRIPTIONS);
		knnone = MediaPlayer.create(context, R.raw.knnone);
		knntwo = MediaPlayer.create(context, R.raw.knntwo);
		knnthree = MediaPlayer.create(context, R.raw.knnthree);
		knnfour = MediaPlayer.create(context, R.raw.knnfour);
		knnfive = MediaPlayer.create(context, R.raw.knnfive);

		tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				if (status != TextToSpeech.ERROR) {
					tts.setLanguage(Locale.US);
				}
			}
		});

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

	@SuppressLint({ "FloatMath", "InflateParams", "ClickableViewAccessibility" })
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		layout = convertView;
		if (convertView == null) {
			layout = inflater.inflate(R.layout.discusstopic_layout, null);
			AphidLog.d("created new view from adapter: %d", position);
			System.out.printf("Position: %d", position);
			
			
			if(position == 1 || position == 2 || position == 3 || position == 4 || position == 5){
				if ((knnone.isPlaying())
						|| (knntwo.isPlaying())
						|| (knnthree.isPlaying())
						|| (knnfour.isPlaying())
						|| (knnfive.isPlaying())) {
					knnone.stop();
					knntwo.stop();
					knnthree.stop();
					knnfour.stop();
					knnfive.stop();
				}
			}
				

		}

		final KNearestData.Data data = desctreeData.get(position
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
										// next

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
										knnone.start();
										knntwo.stop();
										knnthree.stop();
										knnfour.stop();
										knnfive.stop();

									} else if (position == 1) {
										knnone.stop();
										knntwo.start();
										knnthree.stop();
										knnfour.stop();
										knnfive.stop();
										
									} else if (position == 2) {
										knnone.stop();
										knntwo.stop();
										knnthree.start();
										knnfour.stop();
										knnfive.stop();
									} else if (position == 3) {
										knnone.stop();
										knntwo.stop();
										knnthree.stop();
										knnfour.start();
										knnfive.stop();
									} else if (position == 4) {
										knnone.stop();
										knntwo.stop();
										knnthree.stop();
										knnfour.stop();
										knnfive.start();
									}

								}
								if (!isChecked) {
									UI.<ToggleButton> findViewById(layout, R.id.toggleButton).setChecked(true);		
									if ((knnone.isPlaying())
											|| (knntwo.isPlaying())
											|| (knnthree.isPlaying())
											|| (knnfour.isPlaying())
											|| (knnfive.isPlaying())) {
										knnone.stop();
										knntwo.stop();
										knnthree.stop();
										knnfour.stop();
										knnfive.stop();
										
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
		if ((knnone.isPlaying())
				|| (knntwo.isPlaying())
				|| (knnthree.isPlaying())
				|| (knnfour.isPlaying())
				|| (knnfive.isPlaying())) {
			knnone.stop();
			knntwo.stop();
			knnthree.stop();
			knnfour.stop();
			knnfive.stop();
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
