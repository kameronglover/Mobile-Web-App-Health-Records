package com.example.healthchecker;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

/**
 * HW 03
 * NoDataFragment.java
 * Kameron Glover and Maya Hamilton
 */
public class NoDataFragment extends Fragment implements View.OnClickListener {
    Button add;

    public NoDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View noDataView = inflater.inflate(R.layout.fragment_no_data, container, false);
        add = noDataView.findViewById(R.id.addButton);
        add.setOnClickListener(this);
        return noDataView;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
      Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.layout, new HeathFragment()).addToBackStack(null).commit();
    }
}