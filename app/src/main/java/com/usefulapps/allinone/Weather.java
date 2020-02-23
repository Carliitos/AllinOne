package com.usefulapps.allinone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private EditText searchtxt;
    private Button searchbtn;
    private String search;
    private ConstraintLayout constraintL;
    private ImageView weatheriv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        searchtxt = findViewById(R.id.searchtxt);
        searchbtn = findViewById(R.id.searchbtn);

        mTextViewResult = findViewById(R.id.text_view_result);

        search = "Terrasa,es";

        mQueue = Volley.newRequestQueue(this);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = searchtxt.getText().toString();
                mTextViewResult.setText("");
                jsonParse(search);
            }
        });

        jsonParse(search);
    }

    private void jsonParse(final String search){
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + search + "&appid=33c8b808b2d6a28eeec5fea00eff2d13";

        constraintL = findViewById(R.id.constraintL);
        weatheriv = findViewById(R.id.weather);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray weather = response.getJSONArray("weather");
                            String mainWeather = weather.getJSONObject(0).getString("main");

                            switch(mainWeather){
                                case "Clear":
                                    constraintL.setBackgroundResource(R.drawable.clear);
                                    weatheriv.setBackgroundResource(R.drawable.clearimage);
                                    break;
                                case "Clouds":
                                    constraintL.setBackgroundResource(R.drawable.clouds);
                                    weatheriv.setBackgroundResource(R.drawable.cloudsimage);
                                    break;
                                case "Rain":
                                    constraintL.setBackgroundResource(R.drawable.rain);
                                    weatheriv.setBackgroundResource(R.drawable.rainimage);
                            }

                            long temp = Math.round((response.getJSONObject("main").getInt("temp")) - 273.15);
                            long min = Math.round((response.getJSONObject("main").getInt("temp_min")) - 273.15);
                            long max = Math.round((response.getJSONObject("main").getInt("temp_max")) - 273.15);
                            double pressure = (response.getJSONObject("main").getInt("pressure")) / 100;
                            int humidity = response.getJSONObject("main").getInt("humidity");
                            double wind = response.getJSONObject("wind").getLong("speed");

                            mTextViewResult.append("Tiempo en " + search + ": " + temp + "º, " + mainWeather + "\n\nTemperatura mínima: " + min + "º\n\nTemperatura máxima: " + max + "º\n\nPresión atmosférica: " + pressure + " hPa\n\nHumedad: " + humidity + "%\n\nViento: " + wind + " km/h");
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
