package com.rafaelfilgueiras.portalanimes.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.rafaelfilgueiras.portalanimes.R;
import com.rafaelfilgueiras.portalanimes.adapter.RecyclerViewAdapter;
import com.rafaelfilgueiras.portalanimes.fragment.CurriculoFragment;
import com.rafaelfilgueiras.portalanimes.fragment.PostsFragment;
import com.rafaelfilgueiras.portalanimes.fragment.ProjetosFragment;
import com.rafaelfilgueiras.portalanimes.fragment.SobreFragment;
import com.rafaelfilgueiras.portalanimes.model.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // declaraveis que chamam as coisas
    //private final String JSON_URL = "http://filgs.com.br/wp-json/wp/v2/posts";

    //private JsonArrayRequest request;
    //private RequestQueue requestQueue;
    //private List<Anime> lstAnime;
    //private RecyclerView recyclerView;
    //private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // inicializa o frameLayout
        //frameLayout = findViewById(R.id.frameContainer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // referente ao cardview
        //lstAnime = new ArrayList<>();
        //recyclerView = findViewById(R.id.recyclerview);
        //jsonrequest();

        // Inicia o fragmento inicial da aplicacao
        PostsFragment postsFragment = new PostsFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameContainer, postsFragment);
        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*
    Esse trexo é responsavel pelo menu de opções lateral 3 pontos pertencente a @menu/principal.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    } */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_postagens) {
            fragmentPostagens();
        } else if (id == R.id.nav_curriculo) {
            // Dessa forma a gente já redireciona para a activity que for necessária.
            //Intent mainIntent = new Intent(this, MainActivity.class);
            //this.startActivity(mainIntent);
            fragmentCurriculo();

        } else if (id == R.id.nav_projetos) {

            fragmentProjetos();

        } else if (id == R.id.nav_sobre) {

            fragmentSobre();

        } else if (id == R.id.nav_sair) {

            existeApp();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    // Json RPC
    /*
    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {

                    String ExcerptText;

                    try {
                        jsonObject = response.getJSONObject(i);
                        Anime anime = new Anime();
                        //anime.setTitle(jsonObject.getString("title"));
                        anime.setTitle(jsonObject.getJSONObject("title").getString("rendered"));
                        anime.setExcerpt(jsonObject.getJSONObject("excerpt").getString("rendered")
                                .replace("<p>", "")
                                .replace("</p>", ""));
                        anime.setRating(jsonObject.getString("date")
                                .substring(0, 16)
                                .replace("T", " ")
                                .replace("-", "/"));
                        anime.setContent(jsonObject.getJSONObject("content").getString("rendered"));
                        // feitas alterações para obter o objeto
                        // Alteracoes para receber dados da descricao - IMPORTANTE esse campo deve ir para nova activity
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

        requestQueue = Volley.newRequestQueue(PrincipalActivity.this);
        requestQueue.add(request);

    }

    private void setupercycleview(List<Anime> lstAnime) {

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);

    }
    */

    // usando fragments
    private void fragmentPostagens() {

        PostsFragment postsFragment = new PostsFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, postsFragment);
        fragmentTransaction.commit();
    }

    private void fragmentCurriculo() {

        CurriculoFragment curriculoFragment = new CurriculoFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, curriculoFragment);
        fragmentTransaction.commit();
    }

    private void fragmentProjetos() {

        ProjetosFragment projetosFragment = new ProjetosFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, projetosFragment);
        fragmentTransaction.commit();
    }

    private void fragmentSobre() {

        SobreFragment sobreFragment = new SobreFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, sobreFragment);
        fragmentTransaction.commit();
    }

    // Fechar a App
    private void existeApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalActivity.this);
        builder.setMessage("Do you want to exit?");
        builder.setCancelable(true);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finishAffinity();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

}
