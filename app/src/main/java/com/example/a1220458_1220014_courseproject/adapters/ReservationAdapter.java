package com.example.a1220458_1220014_courseproject.adapters;
import com.example.a1220458_1220014_courseproject.models.Reservation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a1220458_1220014_courseproject.R;



import java.util.ArrayList;


public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {



    ArrayList<Reservation> reservations;


    public ReservationAdapter(ArrayList<Reservation> reservations){

        this.reservations = reservations;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {


        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.item_reservation,
                                parent,
                                false);


        return new ViewHolder(view);

    }




    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {


        Reservation reservation =
                reservations.get(position);


        holder.text.setText(
                "Event: " + reservation.getTitle()
                        + "\nQuantity: " + reservation.getQuantity()
                        + "\nType: " + reservation.getType()
                        + "\nStatus: " + reservation.getStatus()
        );

        holder.date.setText(
                "Date: " + reservation.getDate()
        );


    }




    @Override
    public int getItemCount() {

        return reservations.size();

    }




    public static class ViewHolder extends RecyclerView.ViewHolder{


        TextView text;
        TextView date;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);


            text =
                    itemView.findViewById(R.id.reservationInfo);
            date =
                    itemView.findViewById(R.id.reservationDate);

        }

    }


}