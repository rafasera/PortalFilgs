package com.rafaelfilgueiras.filgs.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import com.rafaelfilgueiras.filgs.R;
import com.rafaelfilgueiras.filgs.fragment.CurriculoFragment;
import com.rafaelfilgueiras.filgs.fragment.PostsFragment;
import com.rafaelfilgueiras.filgs.fragment.ProjetosFragment;
import com.rafaelfilgueiras.filgs.fragment.SobreFragment;


public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


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



        // Inicia o fragmento inicial da aplicacao
        PostsFragment postsFragment = new PostsFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameContainer, postsFragment);
        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
            getSupportFragmentManager().popBackStack();
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
            // Modo foi alterado para chamar o fragment
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
