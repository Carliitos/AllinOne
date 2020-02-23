package com.usefulapps.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mTextViewResult = findViewById(R.id.text_view_result);

        mQueue = Volley.newRequestQueue(this);

        jsonParse();
    }

    private void jsonParse(){
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Terrasa,es&appid=33c8b808b2d6a28eeec5fea00eff2d13";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray weather = response.getJSONArray("weather");
                            String mainWeather = weather.getJSONObject(0).getString("main");

                            long temp = Math.round((response.getJSONObject("main").getInt("temp")) - 273.15);
                            long min = Math.round((response.getJSONObject("main").getInt("temp_min")) - 273.15);
                            long max = Math.round((response.getJSONObject("main").getInt("temp_max")) - 273.15);
                            double pressure = (response.getJSONObject("main").getInt("pressure")) / 100;
                            int humidity = response.getJSONObject("main").getInt("humidity");
                            double wind = response.getJSONObject("wind").getLong("speed");

                            mTextViewResult.append("Tiempo en Terrassa: " + temp + "º, " + mainWeather + "\n\nTemperatura mínima: " + min + "º\n\nTemperatura máxima: " + max + "º\n\nPresión atmosférica: " + pressure + " hPa\n\nHumedad: " + humidity + "%\n\nViento: " + wind + " km/h");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}
