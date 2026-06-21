package com.example.a1220458_1220014_courseproject.adapters;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.fragments.EventDetailsFragment;
import com.example.a1220458_1220014_courseproject.fragments.ReservationFormFragment;
import com.example.a1220458_1220014_courseproject.models.Event;


import java.util.ArrayList;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import com.example.a1220458_1220014_courseproject.activities.LoginActivity;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {


    ArrayList<Event> eventList;


    public EventAdapter(ArrayList<Event> eventList){

        this.eventList = eventList;

    }



    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event,parent,false);


        return new EventViewHolder(view);

    }




    @Override
    public void onBindViewHolder(
            @NonNull EventViewHolder holder,
            int position) {



        Event event = eventList.get(position);



        holder.title.setText(event.getTitle());

        holder.category.setText(event.getCategory());

        holder.date.setText(event.getDate());



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





        holder.favorite.setOnClickListener(v -> {
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
            if(userId == -1){

                Intent intent =
                        new Intent(
                                v.getContext(),
                                LoginActivity.class
                        );

                v.getContext().startActivity(intent);

                return;

            }

            boolean added =
                    db.insertFavorite(
                            userId,
                            event.getId()
                    );



            if(added){


                Toast.makeText(
                        v.getContext(),
                        "Added to Favorites",
                        Toast.LENGTH_SHORT
                ).show();


            }else{


                Toast.makeText(
                        v.getContext(),
                        "Already in Favorites",
                        Toast.LENGTH_SHORT
                ).show();


            }


        });



    }


    @Override
    public int getItemCount() {

        return eventList.size();

    }

    public void updateList(ArrayList<Event> newList){


        eventList.clear();


        eventList.addAll(newList);


        notifyDataSetChanged();


    }


    public static class EventViewHolder extends RecyclerView.ViewHolder {


        Button deleteButton;

        TextView title;

        TextView category;

        TextView date;


        Button reserve;

        Button favorite;





        public EventViewHolder(@NonNull View itemView) {

            super(itemView);



            title = itemView.findViewById(R.id.eventTitle);
            deleteButton =
                    itemView.findViewById(R.id.deleteButton);
            category = itemView.findViewById(R.id.eventCategory);

            date = itemView.findViewById(R.id.eventDate);



            reserve = itemView.findViewById(R.id.btnReserve);

            favorite = itemView.findViewById(R.id.btnFavorite);


        }

    }


}