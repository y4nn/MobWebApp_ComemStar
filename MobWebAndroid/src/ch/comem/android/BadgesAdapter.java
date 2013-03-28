package ch.comem.android;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BadgesAdapter extends BaseAdapter {
	private Context context;
	private JSONArray jaListeBadges;

	public BadgesAdapter(Context context, JSONArray jaListeBadges) {
		
		this.context = context;
		this.jaListeBadges = jaListeBadges;
	}

	@Override
	public int getCount() {
		return jaListeBadges.length();
	}

	@Override
	public Object getItem(int arg0) {
		JSONObject jBadge = null;
		try {
			jBadge = jaListeBadges.getJSONObject(arg0);
		} catch (JSONException e) {
		}
		return jBadge;
	}

	@Override
	public long getItemId(int arg0) {
		long id = 0;
		try {
			JSONObject jBadge = null;
			jBadge = jaListeBadges.getJSONObject(arg0);
			jBadge.getLong("id");
		} catch (JSONException e) {
		}
		return id;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		LinearLayout ll = new LinearLayout(context);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ImageView iv = new ImageView(context);
		
		TextView tv = new TextView(context);
		tv.setGravity(Gravity.CENTER_VERTICAL);
		tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		String imgName = "";
		try {
			JSONObject jBadge = jaListeBadges.getJSONObject(arg0);
			tv.setText(jBadge.getString("name"));
			imgName = jBadge.getString("icone");
		} catch (JSONException e) {
		}
		tv.setPadding(37, 0, 0, 0);
		tv.setTextSize(18f);
		Drawable image = null;
		if(imgName.equals("cup"))
			image = this.context.getResources().getDrawable(R.drawable.cup);
		else if(imgName.equals("diamond"))
			image = this.context.getResources().getDrawable(R.drawable.diamond);
		else if(imgName.equals("medal_gold"))
			image = this.context.getResources().getDrawable(R.drawable.medal_gold);
		else if(imgName.equals("medal_silver"))
			image = this.context.getResources().getDrawable(R.drawable.medal_silver);
		else if(imgName.equals("medal_bronze"))
			image = this.context.getResources().getDrawable(R.drawable.medal_bronze);
		
		//Drawable image = this.context.getResources().getDrawable(R.drawable.diamond);
		iv.setImageDrawable(image);
		iv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		
		ll.addView(iv);
		ll.addView(tv);
		notifyDataSetChanged();
		return ll;
	}

}
