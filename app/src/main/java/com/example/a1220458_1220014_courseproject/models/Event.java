package com.example.a1220458_1220014_courseproject.models;


public class Event {


    private int id;
    private String title;
    private String description;
    private String category;
    private String date;
    private String time;
    private String location;
    private int seats;
    private String image;



    public Event(int id, String title, String description,
                 String category, String date,
                 String time, String location,
                 int seats, String image) {


        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.date = date;
        this.time = time;
        this.location = location;
        this.seats = seats;
        this.image = image;

    }


    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public String getCategory() {
        return category;
    }


    public String getDate() {
        return date;
    }


    public String getTime() {
        return time;
    }


    public String getLocation() {
        return location;
    }


    public int getSeats() {
        return seats;
    }

}