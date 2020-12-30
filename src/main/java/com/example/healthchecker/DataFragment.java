package com.example.healthchecker;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

/**
 * HW 03
 * DataFragment.java
 * Kameron Glover and Maya Hamilton
 */
public class DataFragment extends Fragment implements View.OnClickListener {
    private static final String PROFILE = "profile";
    LinearLayoutManager layoutManager;
    ProfileAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Profile> profile = new ArrayList<>();

    public DataFragment() {
        // Required empty public constructor
    }

    public static DataFragment newInstance(ArrayList<Profile> pro) {
        DataFragment fragment = new DataFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(PROFILE, pro);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            profile = getArguments().getParcelableArrayList(PROFILE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View dataView = inflater.inflate(R.layout.fragment_data, container, false);
        recyclerView = dataView.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProfileAdapter(profile);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        dataView.findViewById(R.id.addButton2).setOnClickListener(this);
        return dataView;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.layout, new HeathFragment(profile)).addToBackStack(null).commit();
    }
}
