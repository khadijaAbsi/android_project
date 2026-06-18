package com.example.a1220458_1220014_courseproject.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.models.Event;
import com.example.a1220458_1220014_courseproject.utils.FavoriteManager;
import com.example.a1220458_1220014_courseproject.utils.ReservationManager;


import java.util.ArrayList;



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




        holder.reserve.setOnClickListener(v -> {


            ReservationManager.addReservation(event);


            Toast.makeText(
                    v.getContext(),
                    "Reservation added",
                    Toast.LENGTH_SHORT
            ).show();


        });


        holder.favorite.setOnClickListener(v -> {


            FavoriteManager.addFavorite(event);


            Toast.makeText(
                    v.getContext(),
                    "Added to Favorites",
                    Toast.LENGTH_SHORT
            ).show();


        });



    }




    @Override
    public int getItemCount() {

        return eventList.size();

    }






    public static class EventViewHolder extends RecyclerView.ViewHolder {



        TextView title;
        TextView category;
        TextView date;


        Button reserve;
        Button favorite;



        public EventViewHolder(@NonNull View itemView) {

            super(itemView);



            title = itemView.findViewById(R.id.eventTitle);

            category = itemView.findViewById(R.id.eventCategory);

            date = itemView.findViewById(R.id.eventDate);


            reserve = itemView.findViewById(R.id.btnReserve);

            favorite = itemView.findViewById(R.id.btnFavorite);


        }

    }


}