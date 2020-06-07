// update em 19/02/2018 as 22:48


package com.rafaelfilgueiras.portalanimes.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.rafaelfilgueiras.portalanimes.R;
import com.rafaelfilgueiras.portalanimes.adapter.RecyclerViewAdapter;
import com.rafaelfilgueiras.portalanimes.model.FilgsAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "http://filgs.com.br/wp-json/wp/v2/posts";

    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<FilgsAPI> lstFilgsAPI;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstFilgsAPI = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        jsonrequest();

    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {

                    String ExcerptText;

                    try {
                        jsonObject = response.getJSONObject(i);
                        FilgsAPI filgsAPI = new FilgsAPI();
                        //filgsAPI.setTitle(jsonObject.getString("title"));
                        filgsAPI.setTitle(jsonObject.getJSONObject("title").getString("rendered"));
                        filgsAPI.setExcerpt(jsonObject.getJSONObject("excerpt").getString("rendered")
                                .replace("<p>", "")
                                .replace("</p>", ""));
                        filgsAPI.setRating(jsonObject.getString("date")
                                .substring(0, 16)
                                .replace("T", " ")
                                .replace("-", "/"));
                        filgsAPI.setContent(jsonObject.getJSONObject("content").getString("rendered")
                                .replace("\t", "")
                                .replace("\n", "")
                                .replace("attachment-large", "attachment-zerif_project_photo")
                                .replace("size-large", "size-zerif_project_photo"));

                        // feitas alterações para obter o objeto
                        // Alteracoes para receber dados da descricao - IMPORTANTE esse campo deve ir para nova activity
                        //filgsAPI.setExcerpt(jsonObject.getString("version"));
                        //filgsAPI.setStudio(jsonObject.getString("studio"));
                        //filgsAPI.setRating(jsonObject.getString("Rating"));
                        //filgsAPI.setNb_episode(jsonObject.getInt("episode"));
                        //filgsAPI.setImg_url(jsonObject.getString("img"));
                        lstFilgsAPI.add(filgsAPI);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                setupercycleview(lstFilgsAPI);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }

    private void setupercycleview(List<FilgsAPI> lstFilgsAPI) {

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, lstFilgsAPI);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);

    }
}
