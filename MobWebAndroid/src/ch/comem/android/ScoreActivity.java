package ch.comem.android;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class ScoreActivity extends Activity implements OnClickListener {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultat_activity); 
        
        TextView tvCours = (TextView)findViewById(R.id.tvCours);
        TextView tvSerie = (TextView)findViewById(R.id.tvSerie);
        TextView tvTaux = (TextView)findViewById(R.id.tvTaux);
        TextView tvResult = (TextView)findViewById(R.id.tvResult);
        tvCours.setText(getIntent().getExtras().get("coursName").toString());
        tvSerie.setText(getIntent().getExtras().get("serieName").toString());
        Integer nbReponsesJustes = new Integer(getIntent().getExtras().get("nbReponsesJustes").toString());
        Integer nbQuestions = new Integer(getIntent().getExtras().get("nbQuestions").toString());
        int taux = (int)100*nbReponsesJustes/nbQuestions;
        tvTaux.setText(taux+"%");
        if(taux >= 80)
        	tvTaux.setTextColor(Color.GREEN);
        else if(taux >= 50 && taux < 80)
        	tvTaux.setTextColor(Color.rgb(255, 127, 0));
        else
        	tvTaux.setTextColor(Color.RED);
        tvResult.setText(nbReponsesJustes + " / "+ nbQuestions);
        
        
        Button btnRetour = (Button)findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(this);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void onClick(View arg0) {
		SharedPreferences settings = getSharedPreferences("comemPref", 0);
        Long idStudent = new Long(settings.getString("idStudent", "0"));

        new RefreshInfos().execute(idStudent);
		
		Log.d("xxx editor", settings.getString("jsonString", "fuck off"));
		
		
	}
	
	public class RefreshInfos extends AsyncTask<Long, Void, String> {

		@Override
		protected String doInBackground(Long... params) {
			String jsonResult = "";
			try {
				URL url = new URL("http://10.0.2.2:8080/MobWebAppComemStarAppli/webresources/students/"+params[0].toString());
				jsonResult =  RestConnection.doConnection(url, "GET", null, null);
				Log.d("xxx editor", jsonResult);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return jsonResult;
		}
		
		/** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
       protected void onPostExecute(String jsonString) {
    	   	SharedPreferences settings = getSharedPreferences("comemPref", 0);
           
			SharedPreferences.Editor editor = settings.edit();
			editor.remove("jsonString");
			editor.commit();
			editor.putString("jsonString", jsonString);
			editor.commit();
			
			Intent intent = new Intent();
	      	intent.setClass(ScoreActivity.this, UserActivity.class); 
	      	ScoreActivity.this.startActivity(intent);
       }
	}
}
