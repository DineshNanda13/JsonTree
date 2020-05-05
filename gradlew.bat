package com.meterstoinches.fetchingjsontreedata;

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

public class MainActivity extends AppCompatActivity {

    public static final String url =
            "https://www.json-generator.com/api/json/get/bUOZmcKmle?indent=2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //first item : object
                            /*
                            Log.d("First Object: ",
                                    response.getString("type").toString());
                                    */

                            //object within object
                            /*
                            JSONObject obj = response.getJSONObject("metadata");

                            Log.d("MetaData: ", obj.toString());

                            Log.d("MetaInfo: ", obj.getString("generated"));
                            */

                            //Json array
                            JSONArray arr = response.getJSONArray("features");

                            //since it is an array it has a length
                            for(int i = 0; i < arr.length(); i++){

                                JSONObject propObj = arr.getJSONObject(i).getJSONObject("properties");

                                Log.d("Place: ",arr.ge