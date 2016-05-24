package com.example.filedownloadex;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper {

	public static Music getJson(String jsonstr){
		Music mu = new Music();
		try {
			JSONArray data1 = new JSONObject(jsonstr).getJSONArray("mp3");
			
			for(int i=0;i<data1.length();i++){
				String s = data1.getString(i);
				System.out.println(s);
				JSONObject object = new JSONObject(s);

				mu.setSinger(object.getString("singer"));
				mu.setSongname(object.getString("songname"));
				mu.setImages(object.getString("images"));
				mu.setSongfile(object.getString("songfile"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return mu;
	}
	
}
