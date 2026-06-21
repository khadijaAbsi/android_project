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

    DatabaseHelper databaseHelper;

    public ViewUsersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
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

        databaseHelper =
                new DatabaseHelper(requireContext());

        ArrayList<String> users =
                new ArrayList<>();

        Cursor cursor =
                databaseHelper.getAllUsers();
        System.out.println(
                "USERS COUNT = " +
                        cursor.getCount()
        );

        while(cursor.moveToNext()){

            String user =

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
                            );

            users.add(user);

        }

        cursor.close();

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        users
                );

        listUsers.setAdapter(adapter);

        return view;
    }
}