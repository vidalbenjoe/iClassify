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
	
	   private ImageView zoomerImageZoom;
	   private Matrix matrix = new Matrix();
	   private float scale = 1f;
	   private ScaleGestureDetector SGD;

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

		UI.<ImageView> findViewById(layout, R.id.photo).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
				final Dialog dialog = new Dialog(InputFragmentView.getContext());
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.image_zoomer_dialog);
				dialog.setCancelable(true);
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
			    SGD = new ScaleGestureDetector(InputFragmentView.getContext(),new ScaleListener());
				zoomerImageZoom = (ImageView) dialog.findViewById(R.id.zoomerImageZoom);
				
				zoomerImageZoom.setImageBitmap(
						IO.readBitmap(inflater.getContext().getAssets(),
								data.imageFilename));
				
				
				zoomerImageZoom.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(
									View InputFragmentView) {
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
	
	
	
	
	   public boolean onTouchEvent(MotionEvent ev) {
		      SGD.onTouchEvent(ev);
		      return true;
		   }

		 private class ScaleListener extends ScaleGestureDetector.
		   SimpleOnScaleGestureListener {
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
