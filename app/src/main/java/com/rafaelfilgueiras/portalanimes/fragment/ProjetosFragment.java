package com.rafaelfilgueiras.portalanimes.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.rafaelfilgueiras.portalanimes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjetosFragment extends Fragment {


    public ProjetosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_projetos, container, false);

        // Ini WebView
        WebView projetos_Webview = view.findViewById(R.id.projetos_Webview);

        projetos_Webview.loadUrl("file:///android_asset/projetos.html");

        return view;
    }

}
