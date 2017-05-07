package com.dev.lambda.lambdabrewer.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.lambda.lambdabrewer.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Alarmas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Alarmas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Alarmas extends Fragment {

    public Alarmas() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarmas, container, false);
    }


}
