package knearestdiscussflip;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aphidmobile.utils.AphidLog;
import com.aphidmobile.utils.IO;
import com.aphidmobile.utils.UI;
import com.capstoneii.iclassify.R;

public class KNearestAdapter extends BaseAdapter {
	public TextToSpeech tts;
	private LayoutInflater inflater;

	private int repeatCount = 1;

	private List<KNearestData.Data> desctreeData;
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	PointF startPoint = new PointF();
	PointF midPoint = new PointF();
	float oldDist = 1f;
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;

	MediaPlayer knnone, knntwo, knnthree, knnfour, knnfive;

	public KNearestAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		desctreeData = new ArrayList<KNearestData.Data>(
				KNearestData.IMG_DESCRIPTIONS);

		tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				if (status != TextToSpeech.ERROR) {
					tts.setLanguage(Locale.US);
				}
			}
		});

		knnone = MediaPlayer.create(context, R.raw.knnone);
		knntwo = MediaPlayer.create(context, R.raw.knntwo);
		knnthree = MediaPlayer.create(context, R.raw.knnthree);
		knnfour = MediaPlayer.create(context, R.raw.knnfour);
		knnfive = MediaPlayer.create(context, R.raw.knnfive);

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
	public View getView(int position, View convertView, ViewGroup parent) {
		View layout = convertView;
		if (convertView == null) {
			layout = inflater.inflate(R.layout.discusstopic_layout, null);
			AphidLog.d("created new view from adapter: %d", position);
			System.out.printf("Position: %d", position);

		}

		final KNearestData.Data data = desctreeData.get(position
				% desctreeData.size());

		UI.<TextView> findViewById(layout, R.id.title).setText(
				AphidLog.format("%s", data.title));

		UI.<ImageView> findViewById(layout, R.id.photo).setImageBitmap(
				IO.readBitmap(inflater.getContext().getAssets(),
						data.imageFilename));

		UI.<ImageView> findViewById(layout, R.id.photo).setOnTouchListener(
				new View.OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {

						ImageView view = (ImageView) v;
						System.out.println("matrix=" + savedMatrix.toString());
						switch (event.getAction() & MotionEvent.ACTION_MASK) {
						case MotionEvent.ACTION_DOWN:

							savedMatrix.set(matrix);
							startPoint.set(event.getX(), event.getY());
							mode = DRAG;
							break;

						case MotionEvent.ACTION_POINTER_DOWN:

							oldDist = spacing(event);

							if (oldDist > 10f) {
								savedMatrix.set(matrix);
								midPoint(midPoint, event);
								mode = ZOOM;
							}
							break;

						case MotionEvent.ACTION_UP:

						case MotionEvent.ACTION_POINTER_UP:
							mode = NONE;

							break;

						case MotionEvent.ACTION_MOVE:
							if (mode == DRAG) {
								matrix.set(savedMatrix);
								matrix.postTranslate(event.getX()
										- startPoint.x, event.getY()
										- startPoint.y);
							} else if (mode == ZOOM) {
								float newDist = spacing(event);
								if (newDist > 10f) {
									matrix.set(savedMatrix);
									float scale = newDist / oldDist;
									matrix.postScale(scale, scale, midPoint.x,
											midPoint.y);
								}
							}
							break;

						}
						view.setImageMatrix(matrix);
						return true;
					}

					private float spacing(MotionEvent event) {
						float x = event.getX(0) - event.getX(1);
						float y = event.getY(0) - event.getY(1);
						return FloatMath.sqrt(x * x + y * y);
					}

					private void midPoint(PointF point, MotionEvent event) {
						float x = event.getX(0) + event.getX(1);
						float y = event.getY(0) + event.getY(1);
						point.set(x / 2, y / 2);
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

		for (int i = 0; i < position; i++) {
			String toSpeak = UI
					.<com.capstoneii.iclassify.library.SecretTextView> findViewById(
							layout, R.id.description).getText().toString();
			tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
		}

		/*
		 * if (position == 1) { knnone.start(); } if (position == 2) {
		 * knnone.stop(); knntwo.start(); knnthree.stop(); knnfour.stop();
		 * knnfive.stop(); } if (position == 3) { knnone.stop(); knntwo.stop();
		 * knnthree.start(); knnfour.stop(); knnfive.stop(); } if (position ==
		 * 4) { knnone.stop(); knntwo.stop(); knnthree.stop(); knnfour.start();
		 * knnfive.stop(); } else if (position == 5) { knnone.stop();
		 * knntwo.stop(); knnthree.stop(); knnfour.stop(); knnfive.start(); }
		 */
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

}
