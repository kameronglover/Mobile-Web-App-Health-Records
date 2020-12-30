package com.example.healthchecker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

/**
 * HW 03
 * HealthFragment.java
 * Kameron Glover and Maya Hamilton
 */
public class HeathFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private static final String ARG_PARAM2 = "param2";
    ArrayList<Profile> users = new ArrayList<>();
    EditText name;
    EditText age;
    EditText phone;
    SeekBar seekBar;
    Button submit;
    Button cancel;
    int pAge;
    String pPhone;
    String pName;
    String date;
    int val;


    public HeathFragment() {
        // Required empty public constructor
    }



    public HeathFragment(ArrayList<Profile> pro) {
        users = pro;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View heathView = inflater.inflate(R.layout.fragment_heath, container, false);
        name = heathView.findViewById(R.id.inputName);
        phone = heathView.findViewById(R.id.inputPhone);
        seekBar = heathView.findViewById(R.id.seekBar);
        submit = heathView.findViewById(R.id.inputSubmit);
        cancel = heathView.findViewById(R.id.inputCancel);
        age = heathView.findViewById(R.id.inputBirth);
        heathView.findViewById(R.id.inputSubmit).setOnClickListener(this);
        heathView.findViewById(R.id.inputCancel).setOnClickListener(this);
        heathView.findViewById(R.id.inputBirth).setOnClickListener(this);

        return heathView;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inputSubmit:
                try {
                    pName = name.getText().toString();
                    if (pName.equals("")) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_LONG).show();
                }

                int ag = 2020 - pAge;
                try {
                    pPhone = phone.getText().toString();
                    if (pPhone.equals("")) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Enter Valid Phone Number", Toast.LENGTH_LONG).show();
                }

                try {
                    val = seekBar.getProgress();
                    if (val == 0) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Rate how you are feeling 1 to 10", Toast.LENGTH_LONG).show();
                }
                if (!pName.equals("") && !pPhone.equals("") && val != 0) {
                    users.add(new Profile(pName, ag, pPhone, val));
                    Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.layout, DataFragment.newInstance(users)).addToBackStack(null).commit();
                }
                break;

            case R.id.inputCancel:
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStackImmediate();
                break;

            case R.id.inputBirth:
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Objects.requireNonNull(getContext()), HeathFragment.this, 2020, 9, 1);
                datePickerDialog.show();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = (month + 1) + "/" + dayOfMonth + "/" + year;
        Calendar today = Calendar.getInstance();
        Calendar myDate = Calendar.getInstance();
        myDate.set(year, month + 1, dayOfMonth);
        try {
            if (today.before(myDate)) {
                throw new Exception();
            } else {
                age.setText(date);
                pAge = year;
            }
            age.setText(date);
            pAge = year;
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Enter Valid DOB", Toast.LENGTH_LONG).show();
            age.setText("");
        }
    }

}