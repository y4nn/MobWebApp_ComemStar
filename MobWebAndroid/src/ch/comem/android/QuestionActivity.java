package ch.comem.android;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends Activity implements OnClickListener {
	protected JSONArray jaQuestions;
	protected JSONObject jsonvalue = null;
	protected int nbQuestions = 0;
	protected int nbReponsesJustes = 0;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_activity); 

        
        //Toast.makeText(this, "Cours: "+ getIntent().getExtras().get("coursID"), Toast.LENGTH_SHORT).show();
        
        new GetQuestions().execute(getIntent().getExtras().get("serieID").toString());
        
        Button btnValider = (Button)findViewById(R.id.btnQuestionValider);
        btnValider.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View arg0) {
		LinearLayout myLayout = (LinearLayout)findViewById(R.id.layoutQuestionnaire);
		
		try {
			for (int i = 0; i < myLayout.getChildCount(); i++) {
		        View v = myLayout.getChildAt(i);
		        if (v.getClass() == LinearLayout.class) {
		        	for (int j = 0; j < ((LinearLayout)v).getChildCount() ; j++) {
		        		View w =  ((LinearLayout)v).getChildAt(j);
		        		 if (w.getClass() == RadioGroup.class) {
		     	            RadioGroup rg = (RadioGroup)w;
		     	            for(int k = 0; k< jaQuestions.length(); k++){
								JSONObject joQuestion = jaQuestions.getJSONObject(k);
								if(joQuestion.getInt("id") == rg.getId()){
									for(int l = 0; l < joQuestion.getJSONArray("reponse").length(); l++){
										JSONObject joReponse = joQuestion.getJSONArray("reponse").getJSONObject(l);
										if(joReponse.getInt("id") == rg.getCheckedRadioButtonId()){
											if(joReponse.getBoolean("isValid"))
												nbReponsesJustes += 1;
										}
									}
								}
		     	            }
		     	            nbQuestions = jaQuestions.length();
		     	        }
		        	}
		        }
		    }
			jsonvalue =new JSONObject();
			Integer serieID = new Integer(getIntent().getExtras().get("serieID").toString());
			Integer playerID = new Integer(getIntent().getExtras().get("playerID").toString());
			jsonvalue.put("serieId", serieID);
			int score = (int)100*nbReponsesJustes/nbQuestions;
			jsonvalue.put("score", score);
			jsonvalue.put("applicationId", 1);
			jsonvalue.put("playerId", playerID);
			
			Log.d("jsonVal", jsonvalue.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		new setReponses().execute(jsonvalue);
		/*try {
			URL url = new URL("http://10.0.2.2:8080/MobWebAppComemStarAppli/webresources/series/end");
			RestConnection.doConnection(url, "POST", jsonvalue, null);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		Intent intent = new Intent();  
		intent.putExtra("serieName", getIntent().getExtras().get("serieName").toString());
		intent.putExtra("coursName", getIntent().getExtras().get("coursName").toString());
		intent.putExtra("nbReponsesJustes", nbReponsesJustes);
		intent.putExtra("nbQuestions", nbQuestions);
      	intent.setClass(this, ScoreActivity.class); 
      	this.startActivity(intent); */
		//Toast.makeText(QuestionActivity.this, nbReponsesJustes + " / " + nbQuestions + " = "+ 100*nbReponsesJustes/nbQuestions, Toast.LENGTH_LONG).show();
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	private class setReponses extends AsyncTask<JSONObject, Void, Void>{

		@Override
		protected Void doInBackground(JSONObject... params) {
			try {
				URL url = new URL("http://10.0.2.2:8080/MobWebAppComemStarAppli/webresources/series/end");
				RestConnection.doConnection(url, "POST", params[0], null);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		/** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
       protected void onPostExecute(Void params) {
    	   	Intent intent = new Intent();  
			intent.putExtra("serieName", getIntent().getExtras().get("serieName").toString());
			intent.putExtra("coursName", getIntent().getExtras().get("coursName").toString());
			intent.putExtra("nbReponsesJustes", nbReponsesJustes);
			intent.putExtra("nbQuestions", nbQuestions);
			intent.setClass(QuestionActivity.this, ScoreActivity.class); 
			QuestionActivity.this.startActivity(intent); 
       }
		
	}
	
	
	private class GetQuestions extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			String jsonResult = "";
			try {
				URL url = new URL("http://10.0.2.2:8080/MobWebAppComemStarAppli/webresources/series/"+params[0]);
				jsonResult =  RestConnection.doConnection(url, "GET", null, null);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return jsonResult;
		}
		
		
		/** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
       protected void onPostExecute(String jsonString) {
    	   Log.d("QQQuestions", jsonString);
    	   
    	   SharedPreferences settings = getSharedPreferences("comemPref", 0);
           String idStudent = settings.getString("idStudent", "0");
           
           Log.d("idS", idStudent);
    	   try {
				JSONObject joSerie = new JSONObject(jsonString);
				jaQuestions = joSerie.getJSONArray("questionDTO");
				
				//Inflate the Hidden Layout Information View
                LinearLayout myLayout = (LinearLayout)findViewById(R.id.layoutQuestionnaire);
				for(int i = 0; i< jaQuestions.length(); i++){
					
					JSONObject joQuestion = jaQuestions.getJSONObject(i);
		                
	                View hiddenInfo = getLayoutInflater().inflate(R.layout.question_model, null, false);
		            TextView myTextView = (TextView)hiddenInfo.findViewById(R.id.tvQuestion1);
		            myTextView.setPadding(10, 10, 10, 10);
		            myTextView.setText(joQuestion.getString("question"));
		            
		            RadioGroup rgQuestions = (RadioGroup)hiddenInfo.findViewById(R.id.rgQuestion1);
		            rgQuestions.setId(joQuestion.getInt("id"));
		            JSONArray jaReponses = joQuestion.getJSONArray("reponse");
		            for(int j = 0; j< jaReponses.length(); j++){
		            	JSONObject joReponse = jaReponses.getJSONObject(j);
		            	RadioButton rbtnReponse = new RadioButton(QuestionActivity.this);
		            	rbtnReponse.setText(joReponse.getString("answer"));
		            	rbtnReponse.setId(joReponse.getInt("id"));
		            	rbtnReponse.setPadding(30, 0, 0, 0);
		            	rgQuestions.addView(rbtnReponse);
		            }
	                myLayout.addView(hiddenInfo);
				}
				
			} catch (JSONException e) {
				Log.d("er jaQ", e + " /// "+e.getMessage());
				e.printStackTrace();
			}
       }
	}

	

}
