package com.example.healthchecker;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

/**
 * HW 03
 * DetailFragment.java
 * Kameron Glover and Maya Hamilton
 */
public class DetailFragment extends Fragment {
    Profile profile;
    TextView name;
    TextView age;
    TextView phone;
    ImageView img;
    TextView feel;
    TextView value;

    public DetailFragment() {
        // Required empty public constructor
    }
    public DetailFragment(Profile pro) {
        this.profile = pro;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View detailView = inflater.inflate(R.layout.fragment_detail, container, false);
        name = detailView.findViewById(R.id.detailName);
        age = detailView.findViewById(R.id.detailAge);
        phone = detailView.findViewById(R.id.detailPhone);
        img = detailView.findViewById(R.id.imageView);
        feel = detailView.findViewById(R.id.detailFeeling);
        value = detailView.findViewById(R.id.detailRate);

        name.setText(profile.name);
        age.setText(String.valueOf(profile.age));
        phone.setText(profile.phone);
        value.setText(profile.value + " Out of 10");

        if (profile.value <= 2){
            img.setImageResource(R.drawable.not_well);
            feel.setText("Feeling: Not Well");
        }
        else if (profile.value <= 4){
            img.setImageResource(R.drawable.sad);
            feel.setText("Feeling: Sad");
        }
        else if(profile.value <= 6){
            img.setImageResource(R.drawable.ok);
            feel.setText("Feeling: Ok");
        }
        else if(profile.value <= 8){
            img.setImageResource(R.drawable.good);
            feel.setText("Feeling: Good");
        }
        else if(profile.value <= 10){
            img.setImageResource(R.drawable.very_good);
            feel.setText("Feeling: Very Good");
        }

        detailView.findViewById(R.id.detailButton).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStackImmediate();
            }
        });
        return detailView;
    }
}