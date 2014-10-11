package bayesdiscussflip;

import java.text.DecimalFormat;
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
import com.capstoneii.iclassify.library.TouchImageView;

public class NativeBayesAdapter extends BaseAdapter {

	private LayoutInflater inflater;

	private int repeatCount = 1;

	private List<NativeBayesData.Data> desctreeData;

	public TextToSpeech tts;

	private TouchImageView zoomerImageZoom;

	private DecimalFormat df;
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

						zoomerImageZoom = (TouchImageView) dialog
								.findViewById(R.id.zoomerImageZoom);

						zoomerImageZoom.setImageBitmap(IO.readBitmap(inflater
								.getContext().getAssets(), data.imageFilename));

						zoomerImageZoom.setOnClickListener(new View.OnClickListener() {
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
