package ch.comem.android;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class CoursAdapter extends BaseExpandableListAdapter {
	private Context context;
	private JSONArray jListeCours;

	public CoursAdapter(Context context, JSONArray jListeCours) {
		this.context = context;
		this.jListeCours = jListeCours;
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		JSONObject jSerie = null;
		try {
			JSONObject jCours = jListeCours.getJSONObject(arg0);
			JSONArray jaListSeries = jCours.getJSONArray("serieDTO");
			jSerie = jaListSeries.getJSONObject(arg1);
		} catch (JSONException e) {
		}
		return jSerie;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		Long id = 0L;
		try {
			JSONObject jCours = jListeCours.getJSONObject(arg0);
			JSONArray jaListSeries = jCours.getJSONArray("serieDTO");
			JSONObject jSerie = jaListSeries.getJSONObject(arg1);
			id = jSerie.getLong("id");
		} catch (JSONException e) {
		}
		
		return id;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3, ViewGroup arg4) {
		TextView tv = new TextView(context);
		
		try {
			JSONObject jCours = jListeCours.getJSONObject(arg0);
			JSONArray jaListSeries = jCours.getJSONArray("serieDTO");
			JSONObject jSerie = jaListSeries.getJSONObject(arg1);
			tv.setText(jSerie.getString("name"));
			tv.setPadding(70, 5, 5, 0);
			tv.setTextSize(20f);
		} catch (JSONException e) {
		}
		return tv;
	}

	@Override
	public int getChildrenCount(int arg0) {
		int length = 0;
		try {
			JSONObject jCours = jListeCours.getJSONObject(arg0);
			JSONArray jaListSeries = jCours.getJSONArray("serieDTO");
			length = jaListSeries.length();
		} catch (JSONException e) {
		}
		return length;
	}

	@Override
	public Object getGroup(int arg0) {
		JSONObject jCours = null;
		try {
			jCours = jListeCours.getJSONObject(arg0);
		} catch (JSONException e) {
		}
		return jCours;
	}

	@Override
	public int getGroupCount() {
		return jListeCours.length();
	}

	@Override
	public long getGroupId(int arg0) {
		Long id = 0L;
		try {
			JSONObject jCours = jListeCours.getJSONObject(arg0);
			id = jCours.getLong("id");
		} catch (JSONException e) {
		}
		return id;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		TextView tv = new TextView(context);
		
		try {
			JSONObject jCours = jListeCours.getJSONObject(arg0);
			tv.setText(jCours.getString("name"));
			tv.setPadding(50, 5, 5, 0);
			tv.setTextSize(20f);
		} catch (JSONException e) {
		}
		return tv;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
