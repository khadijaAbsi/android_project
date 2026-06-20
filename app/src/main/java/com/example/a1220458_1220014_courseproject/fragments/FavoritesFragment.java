package com.example.a1220458_1220014_courseproject.fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.adapters.FavoriteAdapter;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import com.example.a1220458_1220014_courseproject.models.Event;


import java.util.ArrayList;
import android.content.SharedPreferences;
import android.content.Context;


public class FavoritesFragment extends Fragment {


    RecyclerView recyclerView;

    DatabaseHelper databaseHelper;


    ArrayList<Event> favorites;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(
                R.layout.fragment_favorites,
                container,
                false);



        recyclerView =
                view.findViewById(
                        R.id.favoritesRecyclerView);



        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));



        databaseHelper =
                new DatabaseHelper(getContext());



        favorites =
                new ArrayList<>();



        SharedPreferences prefs =
                requireContext().getSharedPreferences(
                        "LoginPrefs",
                        Context.MODE_PRIVATE
                );


        int userId =
                prefs.getInt(
                        "user_id",
                        -1
                );


        Cursor cursor =
                databaseHelper.getFavorites(userId);


        while(cursor.moveToNext()){


            favorites.add(

                    new Event(

                            cursor.getInt(
                                    cursor.getColumnIndexOrThrow("id")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("title")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("description")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("category")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("date")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("time")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("location")
                            ),

                            cursor.getInt(
                                    cursor.getColumnIndexOrThrow("seats")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("image")
                            )

                    )
            );

        }


        cursor.close();



        FavoriteAdapter adapter =
                new FavoriteAdapter(favorites);



        recyclerView.setAdapter(adapter);



        return view;

    }

}