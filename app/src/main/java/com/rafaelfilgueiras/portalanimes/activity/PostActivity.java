package com.rafaelfilgueiras.portalanimes.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.rafaelfilgueiras.portalanimes.R;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // Apenas para garantir que a actionbar não vai aparecer.
        getSupportActionBar().hide();

        // Receber dados da Intent Main recuperados do Adpter

        String name = getIntent().getExtras().getString("post_name");
        String data = getIntent().getExtras().getString("post_data");
        String Content = getIntent().getExtras().getString("post_Content");

        // ini View
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        // Ini WebView
        WebView webView_post = findViewById(R.id.webView_post);


        //TextView tv_name = findViewById(R.id.aa_anime_name);
        TextView tv_data = findViewById(R.id.aa_data);
        //TextView tv_content = findViewById(R.id.aa_description);

        // setar os valores recebidos
        //tv_name.setText(name);
        tv_data.setText(data);
        //tv_content.setText(Content);

        // string para receber o content antes de passar pra webview (pensar em algo mais descente)
        String html = Content
                .replace("attachment-large", "attachment-zerif_project_photo")
                .replace("size-large", "size-zerif_project_photo");

        // setar o valor no webview
        webView_post.loadData(html,null,"utf-8");

        // colocando o titulo tambem na collapsingtoolbar
        collapsingToolbarLayout.setTitle(name);

    }
}







