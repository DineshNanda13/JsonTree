package com.meterstoinches.jsontree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class MainActivity extends AppCompatActivity {

    public static final String url = "https://www.json-generator.com/api/json/get/bUOZmcKmle?indent=2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("Direct item: ", response.getString("type").toString());

                            JSONArray featuresJsonArray = response.getJSONArray("features");

                            for(int i = 0; i < featuresJsonArray.length(); i++){

                                JSONObject propertiesObject = featuresJsonArray.getJSONObject(i).getJSONObject("properties");

                                Log.d("Place: ",propertiesObject.getString("place").toString());

                            }

                            for(int i = 0; i < featuresJsonArray.length(); i++){

                                JSONObject geometryObject = featuresJsonArray.getJSONObject(i).getJSONObject("geometry");

                                Log.d("Type: ",geometryObject.getString("type").toString());
                                Log.d("Coordinates: ",geometryObject.getString("coordinates").toString());

                            }

                            JSONObject metadataJsonObject = response.getJSONObject("metadata");
                            Log.d("MetaData Status: ",metadataJsonObject.getString("status").toString());
                            Log.d("MetaData Count: ",metadataJsonObject.getString("count").toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: "+error.getMessage());
            }
        });
        queue.add(jsonObjectRequest);
    }
}
