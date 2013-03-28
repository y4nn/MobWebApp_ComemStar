package ch.comem.android;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.NetworkOnMainThreadException;
import android.util.Log;

public class RestConnection {
	public static String doConnection(URL url, String typeConnection, JSONObject jsonObjectValue, JSONArray jsonArrayValue){
		String jsonString = "";
    	
    	try{
        	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        	
        	urlConnection.setRequestMethod(typeConnection);
        	
        	urlConnection.setRequestProperty("Accept", "application/json");
        	
        	urlConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        	if(jsonObjectValue != null){
        		urlConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        		urlConnection.setDoOutput(true);
	        	OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
	        	out.write(jsonObjectValue.toString());
	        	out.close();
        	}
        	if(jsonArrayValue != null){
        		urlConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        		urlConnection.setDoOutput(true);
	        	OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
	        	out.write(jsonArrayValue.toString());
	        	out.close();
        	}
        	
        	InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        	
        	BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        	String line = reader.readLine();
        	jsonString = line;
        	while((line=reader.readLine())!=null){
        		jsonString+=line;
        	}
        	urlConnection.disconnect();
        	reader.close();
        	in.close();
    	}catch(IOException e){
    		Log.d("xxx exc", e + " - " + e.getMessage());
    	}catch(NetworkOnMainThreadException f){
    		Log.d("xxx net", f + " - ");
    	}
        return jsonString;
	}
}
