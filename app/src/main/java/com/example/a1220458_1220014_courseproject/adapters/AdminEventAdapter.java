package com.example.a1220458_1220014_courseproject.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.models.Event;


import java.util.ArrayList;



public class AdminEventAdapter
        extends RecyclerView.Adapter<AdminEventAdapter.ViewHolder>{


    ArrayList<Event> eventList;



    public AdminEventAdapter(
            ArrayList<Event> eventList){

        this.eventList = eventList;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType){


        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.item_admin_event,
                                parent,
                                false
                        );


        return new ViewHolder(view);

    }





    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position){


        Event event =
                eventList.get(position);



        holder.title.setText(
                event.getTitle()
        );


        holder.category.setText(
                event.getCategory()
        );


    }





    @Override
    public int getItemCount(){

        return eventList.size();

    }





    public static class ViewHolder
            extends RecyclerView.ViewHolder{


        TextView title;

        TextView category;



        public ViewHolder(
                @NonNull View itemView){


            super(itemView);



            title =
                    itemView.findViewById(
                            R.id.adminEventTitle
                    );



            category =
                    itemView.findViewById(
                            R.id.adminEventCategory
                    );


        }

    }

}