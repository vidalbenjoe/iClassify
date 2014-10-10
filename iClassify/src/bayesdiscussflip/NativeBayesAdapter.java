package bayesdiscussflip;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aphidmobile.utils.AphidLog;
import com.aphidmobile.utils.IO;
import com.aphidmobile.utils.UI;
import com.capstoneii.iclassify.R;

public class NativeBayesAdapter extends BaseAdapter {

	private LayoutInflater inflater;

	private int repeatCount = 1;

	private List<NativeBayesData.Data> desctreeData;

	public TextToSpeech tts;

	private ImageView zoomerImageZoom;
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	PointF startPoint = new PointF();
	PointF midPoint = new PointF();
	float oldDist = 1f;
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;

	public NativeBayesAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		desctreeData = new ArrayList<NativeBayesData.Data>(
				NativeBayesData.IMG_DESCRIPTIONS);

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

	@SuppressLint({ "InflateParams", "ClickableViewAccessibility", "FloatMath" })
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View layout = convertView;
		if (convertView == null) {
			layout = inflater.inflate(R.layout.discusstopic_layout, null);
			AphidLog.d("created new view from adapter: %d", position);
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

						zoomerImageZoom
								.setOnTouchListener(new View.OnTouchListener() {

									@Override
									public boolean onTouch(View v,
											MotionEvent event) {

										ImageView view = (ImageView) v;
										System.out.println("matrix="
												+ savedMatrix.toString());
										switch (event.getAction()
												& MotionEvent.ACTION_MASK) {
										case MotionEvent.ACTION_DOWN:

											savedMatrix.set(matrix);
											startPoint.set(event.getX(),
													event.getY());
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
												matrix.postTranslate(
														event.getX()
																- startPoint.x,
														event.getY()
																- startPoint.y);
											} else if (mode == ZOOM) {
												float newDist = spacing(event);
												if (newDist > 10f) {
													matrix.set(savedMatrix);
													float scale = newDist
															/ oldDist;
													matrix.postScale(scale,
															scale, midPoint.x,
															midPoint.y);
												}
											}
											break;

										}
										view.setImageMatrix(matrix);

										return true;
									}

									@SuppressLint("FloatMath")
									private float spacing(MotionEvent event) {
										float x = event.getX(0) - event.getX(1);
										float y = event.getY(0) - event.getY(1);
										return FloatMath.sqrt(x * x + y * y);
									}

									private void midPoint(PointF point,
											MotionEvent event) {
										float x = event.getX(0) + event.getX(1);
										float y = event.getY(0) + event.getY(1);
										point.set(x / 2, y / 2);
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

		String toSpeak = UI
				.<com.capstoneii.iclassify.library.SecretTextView> findViewById(
						layout, R.id.description).getText().toString();
		tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
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
