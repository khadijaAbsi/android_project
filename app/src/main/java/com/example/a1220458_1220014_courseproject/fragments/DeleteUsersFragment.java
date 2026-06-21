package com.example.a1220458_1220014_courseproject.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;

import java.util.ArrayList;

public class DeleteUsersFragment extends Fragment {

    ListView listDeleteUsers;

    DatabaseHelper databaseHelper;

    ArrayList<Integer> userIds =
            new ArrayList<>();

    ArrayList<String> users =
            new ArrayList<>();

    public DeleteUsersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view =
                inflater.inflate(
                        R.layout.fragment_delete_users,
                        container,
                        false
                );

        listDeleteUsers =
                view.findViewById(R.id.listDeleteUsers);

        databaseHelper =
                new DatabaseHelper(requireContext());

        loadUsers();

        listDeleteUsers.setOnItemClickListener(
                (parent, view1, position, id) -> {

                    int userId =
                            userIds.get(position);

                    boolean deleted =
                            databaseHelper.deleteUser(
                                    userId
                            );

                    if(deleted){

                        Toast.makeText(
                                requireContext(),
                                "User Deleted",
                                Toast.LENGTH_SHORT
                        ).show();

                        loadUsers();
                    }
                });

        return view;
    }

    private void loadUsers(){

        users.clear();
        userIds.clear();

        Cursor cursor =
                databaseHelper.getAllUsers();

        while(cursor.moveToNext()){

            userIds.add(
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow("id")
                    )
            );

            users.add(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("first_name")
                    )
                            + " "
                            +
                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("last_name")
                            )
            );
        }

        cursor.close();

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        users
                );

        listDeleteUsers.setAdapter(adapter);
    }
}