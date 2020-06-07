package com.rafaelfilgueiras.portalanimes.fragment;



import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {

    // Referente ao recycleView
    private final String JSON_URL = "http://filgs.com.br/wp-json/wp/v2/posts";



    FilgsAPI filgsAPI = new FilgsAPI();

    public PostsFragment() {
        // Required empty public constructor
    }

    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private List<FilgsAPI> lstFilgsAPI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // IMPORTANTE alterar para objeto View view para receber o oncreate só assim podemos usar elementos do xml
        View view = inflater.inflate(R.layout.fragment_posts, container, false);




        lstFilgsAPI = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerview);

        jsonrequest();
        return view;
    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {


                    try {
                        jsonObject = response.getJSONObject(i);
                        FilgsAPI filgsAPI = new FilgsAPI();
                        //filgsAPI.setTitle(jsonObject.getString("title"));
                        filgsAPI.setTitle(jsonObject.getJSONObject("title").getString("rendered"));
                        filgsAPI.setExcerpt(jsonObject.getJSONObject("excerpt").getString("rendered")
                                .replace("<p>", "")
                                .replace("</p>", "")
                                .replace("</br>","")
                                .replace("<br>","")
                                .replace("<br />","")
                        );
                        filgsAPI.setRating(jsonObject.getString("date")
                                .substring(0, 16)
                                .replace("T", " ")
                                .replace("-", "/"));
                        filgsAPI.setContent(jsonObject.getJSONObject("content").getString("rendered")
                                .replace("\t", "")
                                .replace("\n", "")
                                //.replace("attachment-large size-large", "attachment-zerif_project_photo size-zerif_project_photo")
                                .replace("attachment-large", "attachment-zerif_project_photo")
                                .replace("size-large", "size-zerif_project_photo")
                        );


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

        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

    }

    private void setupercycleview(List<FilgsAPI> lstFilgsAPI) {

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(getActivity(), lstFilgsAPI);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(myadapter);

    }



}
