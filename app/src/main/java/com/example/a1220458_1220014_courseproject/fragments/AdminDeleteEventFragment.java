package com.example.a1220458_1220014_courseproject.fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.adapters.AdminDeleteEventAdapter;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import com.example.a1220458_1220014_courseproject.models.Event;


import java.util.ArrayList;



public class AdminDeleteEventFragment extends Fragment {



    RecyclerView recyclerView;

    DatabaseHelper db;


    ArrayList<Event> events = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState){


        View view =
                inflater.inflate(
                        R.layout.fragment_admin_delete_event,
                        container,
                        false
                );



        recyclerView =
                view.findViewById(
                        R.id.deleteEventsRecyclerView
                );


        db =
                new DatabaseHelper(getContext());


        loadEvents();



        return view;

    }





    private void loadEvents(){


        Cursor cursor =
                db.getAllEvents();



        while(cursor.moveToNext()){


            events.add(
                    new Event(

                            cursor.getInt(0),

                            cursor.getString(1),

                            cursor.getString(2),

                            cursor.getString(3),

                            cursor.getString(4),

                            cursor.getString(5),

                            cursor.getString(6),

                            cursor.getInt(7),

                            cursor.getString(8)

                    )
            );


        }


        cursor.close();



        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext())
        );



        recyclerView.setAdapter(
                new AdminDeleteEventAdapter(
                        events,

                        event -> {


                            boolean deleted =
                                    db.deleteEvent(
                                            event.getId()
                                    );



                            if(deleted){


                                Toast.makeText(
                                        getContext(),
                                        "Event Deleted",
                                        Toast.LENGTH_SHORT
                                ).show();



                                events.remove(event);

                                recyclerView.getAdapter()
                                        .notifyDataSetChanged();

                            }


                        }

                )
        );



    }



}