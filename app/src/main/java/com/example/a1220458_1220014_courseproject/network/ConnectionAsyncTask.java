package com.example.a1220458_1220014_courseproject.network;


import android.app.Activity;
import android.os.AsyncTask;


import com.example.a1220458_1220014_courseproject.activities.IntroductionActivity;
import com.example.a1220458_1220014_courseproject.database.DatabaseHelper;
import com.example.a1220458_1220014_courseproject.models.Event;


import java.util.ArrayList;



public class ConnectionAsyncTask
        extends AsyncTask<String,String,String> {



    Activity activity;



    public ConnectionAsyncTask(Activity activity){

        this.activity = activity;

    }




    @Override
    protected String doInBackground(String... strings){


        return HttpManager.getData(strings[0]);


    }





    @Override
    protected void onPostExecute(String s){


        ArrayList<Event> events =
                EventJsonParser.getObjectFromJson(s);
        System.out.println(s);
        System.out.println("EVENTS SIZE = " + events.size());


        DatabaseHelper db =
                new DatabaseHelper(activity);



        for(Event e : events){


            db.insertEvent(e);


        }



        ((IntroductionActivity)activity)
                .openHome();


    }



}