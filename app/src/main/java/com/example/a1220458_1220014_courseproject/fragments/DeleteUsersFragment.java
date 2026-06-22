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
import com.example.a1220458_1220014_courseproject.adapters.AdminDeleteUserAdapter;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import com.example.a1220458_1220014_courseproject.models.User;

import java.util.ArrayList;

public class DeleteUsersFragment extends Fragment {

    RecyclerView recyclerView;

    DatabaseHelper db;

    ArrayList<User> users = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view =
                inflater.inflate(
                        R.layout.fragment_delete_users,
                        container,
                        false
                );

        recyclerView =
                view.findViewById(
                        R.id.deleteUsersRecyclerView
                );

        db =
                new DatabaseHelper(getContext());

        loadUsers();

        return view;
    }

    private void loadUsers() {

        Cursor cursor =
                db.getAllUsers();

        while (cursor.moveToNext()) {

            users.add(
                    new User(
                            cursor.getInt(
                                    cursor.getColumnIndexOrThrow("id")
                            ),

                            cursor.getString(
                                    cursor.getColumnIndexOrThrow("first_name")
                            )
                                    + " "
                                    +
                                    cursor.getString(
                                            cursor.getColumnIndexOrThrow("last_name")
                                    )
                    )
            );
        }

        cursor.close();

        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        recyclerView.setAdapter(
                new AdminDeleteUserAdapter(
                        users,

                        user -> {

                            boolean deleted =
                                    db.deleteUser(
                                            user.getId()
                                    );

                            if (deleted) {

                                Toast.makeText(
                                        getContext(),
                                        "User Deleted",
                                        Toast.LENGTH_SHORT
                                ).show();

                                users.remove(user);

                                recyclerView.getAdapter()
                                        .notifyDataSetChanged();
                            }
                        }
                )
        );
    }
}