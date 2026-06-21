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



public class AdminEditEventAdapter
        extends RecyclerView.Adapter<AdminEditEventAdapter.ViewHolder>{



    ArrayList<Event> events;


    OnEventClick listener;



    public interface OnEventClick{

        void click(Event event);

    }




    public AdminEditEventAdapter(
            ArrayList<Event> events,
            OnEventClick listener){


        this.events = events;

        this.listener = listener;

    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType){


        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.item_admin_edit_event,
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
                events.get(position);



        holder.title.setText(
                event.getTitle()
        );



        holder.itemView.setOnClickListener(v -> {


            listener.click(event);


        });


    }





    @Override
    public int getItemCount(){

        return events.size();

    }






    static class ViewHolder
            extends RecyclerView.ViewHolder{


        TextView title;



        public ViewHolder(
                @NonNull View itemView){

            super(itemView);


            title =
                    itemView.findViewById(
                            R.id.editEventTitle
                    );

        }

    }


}