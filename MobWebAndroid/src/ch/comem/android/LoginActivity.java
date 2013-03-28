package ch.comem.android;

import java.net.MalformedURLException;
import java.net.URL;

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
import android.widget.Toast;

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        
        Button bntLogin = (Button)findViewById(R.id.btnLogin);
        bntLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(LoginActivity.this, "Test Login !", Toast.LENGTH_SHORT).show();
				//
				SharedPreferences settings = getSharedPreferences("comemPref", 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.remove("jsonString");
				editor.clear();
				editor.commit();
				
				JSONObject jsonvalue =new JSONObject();
				try{
					jsonvalue.put("mail", ((EditText)findViewById(R.id.etMail)).getText().toString());
					jsonvalue.put("pass", ((EditText)findViewById(R.id.etPass)).getText().toString());
				}catch(JSONException e){
					
				}
				new Login().execute(jsonvalue);
				
			}
		});
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private class Login extends AsyncTask<JSONObject, Void, String> {
        /** The system calls this to perform work in a worker thread and
          * delivers it the parameters given to AsyncTask.execute() */
        protected String doInBackground(JSONObject... params) {
        	String jsonResult = "";
			try {
				URL url = new URL("http://10.0.2.2:8080/MobWebAppComemStarAppli/webresources/students/login");
				jsonResult =  RestConnection.doConnection(url, "PUT", params[0], null);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return jsonResult;
        }
        
        /** The system calls this to perform work in the UI thread and delivers
          * the result from doInBackground() */
        protected void onPostExecute(String jsonString) {
            if(jsonString != null){
            	
            	try{
            		//Log.d("ziki", jsonString);
            		
					SharedPreferences settings = getSharedPreferences("comemPref", 0);
					SharedPreferences.Editor editor = settings.edit();
					editor.putString("jsonString", jsonString);
					//editor.putBoolean("silentMode", mSilentMode);
					// Commit the edits!
					editor.commit();
            		Intent intent = new Intent();
          	      	intent.setClass(LoginActivity.this, UserActivity.class); 
          	      	LoginActivity.this.startActivity(intent); 
            	}catch(Exception e){
            		
            	}

            }
            else{
            	Toast.makeText(LoginActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
            }
        }
    }
}