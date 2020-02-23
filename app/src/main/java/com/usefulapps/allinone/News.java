package com.usefulapps.allinone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class News extends AppCompatActivity {

    private RequestQueue mQueue;
    private ImageView image;
    private TextView title;
    private TextView author;
    private TextView description;
    private EditText searchtxt;
    private Button searchbtn;
    private String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        searchtxt = findViewById(R.id.searchtxt);
        searchbtn = findViewById(R.id.searchbtn);

        final LinearLayout item = findViewById(R.id.linearLayout);

        search = "Android";

        mQueue = Volley.newRequestQueue(this);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = searchtxt.getText().toString();
                item.removeAllViews();
                jsonParse(item, search);
            }
        });

        jsonParse(item, search);
    }

    private void jsonParse(final LinearLayout item, String search){

        String url = "https://newsapi.org/v2/everything?q=" + search + "&sortBy=popularity&apiKey=82881c5c8b3b4e72b5660c117bcd8da9";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("articles");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                final JSONObject article = jsonArray.getJSONObject(i);

                                View apiNew = getLayoutInflater().inflate(R.layout.single_new, null);
                                item.addView(apiNew);

                                image = apiNew.findViewById(R.id.img);
                                title = apiNew.findViewById(R.id.title);
                                author = apiNew.findViewById(R.id.author);
                                description = apiNew.findViewById(R.id.description);

                                Picasso.get().load(article.getString("urlToImage")).into(image);
                                title.setText(article.getString("title"));
                                author.setText(article.getString("author"));
                                description.setText(article.getString("description"));

                                title.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(article.getString("url"))));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
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