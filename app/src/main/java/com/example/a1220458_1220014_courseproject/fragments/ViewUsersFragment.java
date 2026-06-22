package com.example.a1220458_1220014_courseproject.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;

import java.util.ArrayList;

public class ViewUsersFragment extends Fragment {

    ListView listUsers;

    DatabaseHelper db;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view =
                inflater.inflate(
                        R.layout.fragment_view_users,
                        container,
                        false
                );

        listUsers =
                view.findViewById(R.id.listUsers);

        db =
                new DatabaseHelper(getContext());

        ArrayList<String> users =
                new ArrayList<>();

        Cursor cursor =
                db.getAllUsers();

        while(cursor.moveToNext()){

            users.add(

                    cursor.getString(
                            cursor.getColumnIndexOrThrow("first_name")
                    )

                            + " "

                            +

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("last_name")
                            )

                            + "\n"

                            +

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("email")
                            )

            );
        }

        cursor.close();

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        getContext(),
                        android.R.layout.simple_list_item_1,
                        users
                );

        listUsers.setAdapter(adapter);

        return view;
    }
}