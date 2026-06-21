package com.example.a1220458_1220014_courseproject.network;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpManager {


    public static String getData(String urlString){


        StringBuilder result =
                new StringBuilder();


        try{


            URL url =
                    new URL(urlString);


            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();


            connection.setRequestMethod("GET");

            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);


            connection.connect();



            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream()
                            )
                    );



            String line;


            while((line = reader.readLine()) != null){


                result.append(line);


            }


            reader.close();



            return result.toString();



        }catch(Exception e){


            e.printStackTrace();
            System.out.println("ERROR = " + e);




        }



        return null;


    }


}