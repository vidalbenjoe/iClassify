package bayesdiscussflip;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

  public NativeBayesAdapter(Context context) {
    inflater = LayoutInflater.from(context);
    desctreeData = new ArrayList<NativeBayesData.Data>(NativeBayesData.IMG_DESCRIPTIONS);
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
  public View getView(int position, View convertView, ViewGroup parent) {
    View layout = convertView;
    if (convertView == null) {
      layout = inflater.inflate(R.layout.discusstopic_layout, null);
      AphidLog.d("created new view from adapter: %d", position);
    }

    final NativeBayesData.Data data = desctreeData.get(position % desctreeData.size());

    UI
        .<TextView>findViewById(layout, R.id.title)
        .setText(AphidLog.format("%s", data.title));

    UI
    .<ImageView>findViewById(layout, R.id.photo)
    .setImageBitmap(IO.readBitmap(inflater.getContext().getAssets(), data.imageFilename));

    UI
        .<TextView>findViewById(layout, R.id.description)
        .setText(Html.fromHtml(data.description));
    
    return layout;
  }

  public void removeData(int index) {
    if (desctreeData.size() > 1) {
      desctreeData.remove(index);
    }
  }
}
