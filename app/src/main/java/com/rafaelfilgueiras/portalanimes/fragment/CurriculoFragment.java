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
public class CurriculoFragment extends Fragment {


    public CurriculoFragment() {
        // Required empty public constructor
    }

    //String htmlCurriculo

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_curriculo, container, false);

        // Ini WebView
        WebView curriculo_WEbview = view.findViewById(R.id.curriculo_WEbview);

        curriculo_WEbview.loadUrl("file:///android_asset/curriculo_rafa.html");

        return view;
    }



}
