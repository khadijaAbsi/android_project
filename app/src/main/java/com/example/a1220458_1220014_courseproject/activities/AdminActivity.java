package com.example.a1220458_1220014_courseproject.activities;


import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;


import com.example.a1220458_1220014_courseproject.R;

import com.example.a1220458_1220014_courseproject.fragments.AddAdminFragment;
import com.example.a1220458_1220014_courseproject.fragments.AddEventFragment;
import com.example.a1220458_1220014_courseproject.fragments.AdminDeleteEventFragment;
import com.example.a1220458_1220014_courseproject.fragments.HomeFragment;
import com.example.a1220458_1220014_courseproject.fragments.AdminReservationsFragment;

import com.example.a1220458_1220014_courseproject.fragments.AdminEditEventFragment;
import com.google.android.material.navigation.NavigationView;



public class AdminActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;

    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin);




        drawerLayout =
                findViewById(R.id.drawerLayout);


        navigationView =
                findViewById(R.id.navigationView);





        getSupportFragmentManager()

                .beginTransaction()

                .replace(
                        R.id.fragmentContainer,
                        new HomeFragment()
                )

                .commit();







        navigationView.setNavigationItemSelectedListener(item -> {



            int id =
                    item.getItemId();






            if(id == R.id.addAdmin){


                loadFragment(
                        new AddAdminFragment()
                );


            }






            else if(id == R.id.addEvent){


                loadFragment(
                        new AddEventFragment()
                );


            }






            else if(id == R.id.editEvent){

                loadFragment(
                        new AdminEditEventFragment()
                );

            }






            else if(id == R.id.deleteEvent){

                loadFragment(
                        new AdminDeleteEventFragment()
                );

            }










            // View all reservations

            else if(id == R.id.viewReservations){


                loadFragment(
                        new AdminReservationsFragment()
                );


            }







            // Logout admin

            else if(id == R.id.logout){



                startActivity(

                        new Intent(
                                AdminActivity.this,
                                LoginActivity.class
                        )

                );



                finish();


            }






            drawerLayout.closeDrawers();



            return true;


        });



    }








    private void loadFragment(Fragment fragment){



        getSupportFragmentManager()

                .beginTransaction()

                .replace(
                        R.id.fragmentContainer,
                        fragment
                )

                .addToBackStack(null)

                .commit();


    }


}