package com.rafaelfilgueiras.portalanimes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.rafaelfilgueiras.portalanimes.R;
import com.rafaelfilgueiras.portalanimes.adapter.RecyclerViewAdapter;
import com.rafaelfilgueiras.portalanimes.model.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "https://egpersonaltrainner.000webhostapp.com/wp-json/wp/v2/posts";

    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Anime> lstAnime;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstAnime = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        jsonrequest();

    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        Anime anime = new Anime();
                        //anime.setTitle(jsonObject.getString("title"));
                        anime.setTitle(jsonObject.getJSONObject("title").getString("rendered"));
                        // feitas alterações para obter o objeto
                        //anime.setDescription(jsonObject.getString("description"));
                        //anime.setExcerpt(jsonObject.getString("version"));
                        //anime.setStudio(jsonObject.getString("studio"));
                        //anime.setRating(jsonObject.getString("Rating"));
                        //anime.setNb_episode(jsonObject.getInt("episode"));
                        //anime.setImg_url(jsonObject.getString("img"));
                        lstAnime.add(anime);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                setupercycleview(lstAnime);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }

    private void setupercycleview(List<Anime> lstAnime) {

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);

    }
}
