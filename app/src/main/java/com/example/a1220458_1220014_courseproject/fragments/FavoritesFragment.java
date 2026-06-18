package com.example.a1220458_1220014_courseproject.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a1220458_1220014_courseproject.R;
import com.example.a1220458_1220014_courseproject.adapters.EventAdapter;
import com.example.a1220458_1220014_courseproject.utils.FavoriteManager;



public class FavoritesFragment extends Fragment {



    RecyclerView recyclerView;



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
                view.findViewById(R.id.favoritesRecyclerView);



        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));



        EventAdapter adapter =
                new EventAdapter(
                        FavoriteManager.getFavorites()
                );


        recyclerView.setAdapter(adapter);



        return view;

    }

}