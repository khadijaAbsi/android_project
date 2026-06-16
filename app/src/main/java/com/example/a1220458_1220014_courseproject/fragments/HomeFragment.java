package com.example.a1220458_1220014_courseproject.fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.a1220458_1220014_courseproject.R;



public class HomeFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(
                R.layout.fragment_home,
                container,
                false);


        View button = view.findViewById(R.id.exploreButton);


        Animation animation =
                AnimationUtils.loadAnimation(
                        getContext(),
                        R.anim.button_scale);


        button.startAnimation(animation);



        return view;


    }


}