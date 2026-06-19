package com.example.a1220458_1220014_courseproject.activities;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;


import com.google.android.material.navigation.NavigationView;

import com.example.a1220458_1220014_courseproject.R;

import com.example.a1220458_1220014_courseproject.fragments.HomeFragment;
import com.example.a1220458_1220014_courseproject.fragments.EventsFragment;
import com.example.a1220458_1220014_courseproject.fragments.ReservationsFragment;
import com.example.a1220458_1220014_courseproject.fragments.FavoritesFragment;
import com.example.a1220458_1220014_courseproject.fragments.SpecialFragment;

import com.example.a1220458_1220014_courseproject.fragments.ProfileFragment;
import com.example.a1220458_1220014_courseproject.fragments.ContactUsFragment;

public class HomeActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);



        drawerLayout = findViewById(R.id.drawerLayout);

        navigationView = findViewById(R.id.navigationView);



        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer,new HomeFragment())
                .commit();



        navigationView.setNavigationItemSelectedListener(item -> {


            int id = item.getItemId();


            if(id == R.id.home){

                loadFragment(new HomeFragment());

            }

            else if(id == R.id.events){

                loadFragment(new EventsFragment());

            }

            else if(id == R.id.reservations){

                loadFragment(new ReservationsFragment());

            }

            else if(id == R.id.favorites){

                loadFragment(new FavoritesFragment());

            }

            else if(id == R.id.special){

                loadFragment(new SpecialFragment());

            }
            else if(id == R.id.profile){

                loadFragment(new ProfileFragment());

            }

            else if(id == R.id.contactUs){

                loadFragment(new ContactUsFragment());

            }


            drawerLayout.closeDrawers();


            return true;

        });


    }



    private void loadFragment(Fragment fragment){


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer,fragment)
                .commit();


    }

}