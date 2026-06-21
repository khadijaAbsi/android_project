package com.example.a1220458_1220014_courseproject.models;

public class Reservation {

    String title;
    String date;
    int quantity;
    String type;
    String status;

    public Reservation(String title, String date, int quantity, String type, String status) {
        this.title = title;
        this.date = date;
        this.quantity = quantity;
        this.type = type;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }
}