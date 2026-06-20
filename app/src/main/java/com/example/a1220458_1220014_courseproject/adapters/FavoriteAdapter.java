package com.example.a1220458_1220014_courseproject.adapters;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.fragments.EventDetailsFragment;
import com.example.a1220458_1220014_courseproject.fragments.ReservationFormFragment;
import com.example.a1220458_1220014_courseproject.models.Event;
import android.content.Intent;
import com.example.a1220458_1220014_courseproject.activities.LoginActivity;

import java.util.ArrayList;
import android.content.SharedPreferences;
import android.content.Context;



public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{



    ArrayList<Event> favoriteList;



    public FavoriteAdapter(ArrayList<Event> favoriteList){

        this.favoriteList = favoriteList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {


        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(

                                R.layout.item_favorite,
                                parent,
                                false);


        return new ViewHolder(view);

    }





    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {


        Event event = favoriteList.get(position);




        holder.title.setText(event.getTitle());

        holder.category.setText(event.getCategory());



        holder.itemView.setOnClickListener(v -> {


            EventDetailsFragment fragment =
                    new EventDetailsFragment();


            Bundle bundle = new Bundle();


            bundle.putString("title", event.getTitle());
            bundle.putInt(
                    "eventId",
                    event.getId()
            );

            bundle.putString("description", event.getDescription());

            bundle.putString("category", event.getCategory());

            bundle.putString("date", event.getDate());

            bundle.putString("location", event.getLocation());


            fragment.setArguments(bundle);



            ((FragmentActivity)v.getContext())
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();


        });



        holder.remove.setOnClickListener(v -> {
            SharedPreferences prefs =
                    v.getContext().getSharedPreferences(
                            "LoginPrefs",
                            Context.MODE_PRIVATE
                    );


            int userId =
                    prefs.getInt(
                            "user_id",
                            -1
                    );


            DatabaseHelper db =
                    new DatabaseHelper(
                            v.getContext()
                    );

            db.deleteFavorite(
                    userId,
                    event.getId()
            );


            favoriteList.remove(position);


            notifyItemRemoved(position);


            notifyItemRangeChanged(
                    position,
                    favoriteList.size()
            );


        });
        holder.reserve.setOnClickListener(v -> {

            SharedPreferences prefs =
                    v.getContext().getSharedPreferences(
                            "LoginPrefs",
                            Context.MODE_PRIVATE
                    );


            int userId =
                    prefs.getInt(
                            "user_id",
                            -1
                    );


            if(userId == -1){

                Intent intent =
                        new Intent(
                                v.getContext(),
                                LoginActivity.class
                        );

                v.getContext().startActivity(intent);

                return;

            }


            ReservationFormFragment fragment =
                    new ReservationFormFragment();


            Bundle bundle =
                    new Bundle();


            bundle.putInt(
                    "eventId",
                    event.getId()
            );


            fragment.setArguments(bundle);



            ((FragmentActivity)v.getContext())
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.fragmentContainer,
                            fragment
                    )
                    .addToBackStack(null)
                    .commit();


        });


    }




    @Override
    public int getItemCount(){

        return favoriteList.size();

    }




    public static class ViewHolder extends RecyclerView.ViewHolder{


        TextView title;

        TextView category;

        Button remove;

        Button reserve;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);


            title =
                    itemView.findViewById(R.id.favoriteTitle);


            category =
                    itemView.findViewById(R.id.favoriteCategory);


            remove =
                    itemView.findViewById(R.id.removeFavorite);
            reserve =
                    itemView.findViewById(
                            R.id.reserveFavorite
                    );

        }

    }


}