package com.example.healthchecker;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * HW 03
 * ProfileAdapter.java
 * Kameron Glover and Maya Hamilton
 */

public class    ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    ArrayList<Profile> users;

    public ProfileAdapter(ArrayList<Profile> data){
        this.users = data;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.records, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, final int position) {
        Profile user = users.get(position);
        holder.name.setText(user.name);
        holder.age.setText(String.valueOf(user.age));
        holder.position = position;
        holder.user = user;

        if (user.value <= 2){
            holder.img.setImageResource(R.drawable.not_well);
            holder.feel.setText("Feeling: Not Well");
        }
        else if (user.value <= 4){
            holder.img.setImageResource(R.drawable.sad);
            holder.feel.setText("Feeling: Sad");
        }
        else if(user.value <= 6){
            holder.img.setImageResource(R.drawable.ok);
            holder.feel.setText("Feeling: Ok");
        }
        else if(user.value <= 8){
            holder.img.setImageResource(R.drawable.good);
            holder.feel.setText("Feeling: Good");
        }
        else if(user.value <= 10){
            holder.img.setImageResource(R.drawable.very_good);
            holder.feel.setText("Feeling: Very Good");
        }


        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, users.size());

                if (users.size() == 0){
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.layout, new NoDataFragment()).addToBackStack(null).commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Profile user;
        TextView name;
        TextView age;
        TextView feel;
        ImageView img;
        Button del;
        int position;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            feel = itemView.findViewById(R.id.feeling);
            img = itemView.findViewById(R.id.img);
            del = itemView.findViewById(R.id.button);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.layout, new DetailFragment(user)).addToBackStack(null).commit();
        }
    }
}
