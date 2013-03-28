package ch.comem.android;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class UserActivity extends Activity {
	protected JSONObject joStudentDTO = null;
	protected JSONArray jaListeCours  = null;
	protected JSONArray jaListeBadges = null;
	protected long playerID = 0;
	protected String jsonString = null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity); 
        
        /////////////////////////////////////////////////////////////////
        // TabHost
        TabHost th = (TabHost)findViewById(R.id.tabStudent);
        th.setup();
        TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("Cours");
        th.addTab(specs);
        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Profil");
        th.addTab(specs);
        
        

//        this.pupulateCours();
//        this.populateProfil();

    }
	
	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences settings = getSharedPreferences("comemPref", 0);
        jsonString = settings.getString("jsonString", "error comemPref");

        //Toast.makeText(this, "PROUT: " + getIntent().getExtras().get("prout"), Toast.LENGTH_LONG).show();
        
        try {
			joStudentDTO = new JSONObject(jsonString);
			JSONObject jclasse = joStudentDTO.getJSONObject("classe");
			jaListeCours = jclasse.getJSONArray("listeCours");
			String jplayerString = joStudentDTO.getString("playerJSON");
			JSONObject jplayer = new JSONObject(jplayerString);
			playerID = jplayer.getLong("id");
			jaListeBadges = jplayer.getJSONArray("listeBadges");
			
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("idStudent", joStudentDTO.getString("id"));
			editor.commit();
			Log.d("idSSSSS", joStudentDTO.getString("id"));
		} catch (JSONException e) {
			Log.d("json errorr", e.getMessage());
		}
		this.pupulateCours();
		this.populateProfil();
	}
	
	private void pupulateCours(){
        ExpandableListView elvCours = (ExpandableListView)findViewById(R.id.elvCours);
        elvCours.setAdapter(new CoursAdapter(this, jaListeCours));
        
        elvCours.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				JSONObject jCours;
				try {
					jCours = jaListeCours.getJSONObject(groupPosition);
					JSONArray jaListSeries = jCours.getJSONArray("serieDTO");
					JSONObject jSerie = jaListSeries.getJSONObject(childPosition);
					
					//Toast.makeText(UserActivity.this, jSerie.getString("id") + " - " +jSerie.getString("name"), Toast.LENGTH_SHORT).show();
					
					Intent intent = new Intent();  
            		intent.putExtra("serieID", jSerie.getString("id"));
            		intent.putExtra("serieName", jSerie.getString("name"));
            		intent.putExtra("coursID", jCours.getString("id"));
            		intent.putExtra("coursName", jCours.getString("name"));
            		intent.putExtra("playerID", playerID);
          	      	intent.setClass(UserActivity.this, QuestionActivity.class); 
          	      	UserActivity.this.startActivity(intent); 
				} catch (JSONException e) {
				}
				return false;
			}
		});
	}
	
	private void populateProfil(){
		TextView tvUserName = (TextView)findViewById(R.id.tvUserName);
        try {
			tvUserName.setText(joStudentDTO.getString("firstName") +" "+ joStudentDTO.getString("lastName"));
		} catch (JSONException e) {
		}
        tvUserName.setTextSize(20);
        
        ListView lvBadges = (ListView)findViewById(R.id.lvBadges);
        BadgesAdapter badapter = new BadgesAdapter(this, jaListeBadges);
//        badapter.notifyDataSetChanged();
        lvBadges.setAdapter(badapter);
        lvBadges.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				try {
					JSONObject jBadge = jaListeBadges.getJSONObject(arg2);
					AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(UserActivity.this);
					dialogBuilder.setTitle(jBadge.getString("name"));
					dialogBuilder.setMessage(jBadge.getString("description"));
					
					AlertDialog alertDialog = dialogBuilder.create();
					alertDialog.show();
					
				} catch (JSONException e) {
				}
			}
		});
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
