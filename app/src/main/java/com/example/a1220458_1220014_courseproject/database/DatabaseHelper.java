package com.example.a1220458_1220014_courseproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "events_app.db";
    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Users Table
        String CREATE_USERS_TABLE =
                "CREATE TABLE users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "email TEXT UNIQUE," +
                        "first_name TEXT," +
                        "last_name TEXT," +
                        "password TEXT," +
                        "gender TEXT," +
                        "major TEXT," +
                        "phone TEXT" +
                        ");";

        db.execSQL(CREATE_USERS_TABLE);

        // Admins Table
        String CREATE_ADMINS_TABLE =
                "CREATE TABLE admins (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "email TEXT UNIQUE," +
                        "password TEXT" +
                        ");";

        db.execSQL(CREATE_ADMINS_TABLE);

        // Events Table
        String CREATE_EVENTS_TABLE =
                "CREATE TABLE events (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "title TEXT," +
                        "description TEXT," +
                        "category TEXT," +
                        "date TEXT," +
                        "time TEXT," +
                        "location TEXT," +
                        "seats INTEGER," +
                        "image TEXT" +
                        ");";

        db.execSQL(CREATE_EVENTS_TABLE);
        ContentValues event1 = new ContentValues();

        event1.put("title", "Android Development Workshop");
        event1.put("description", "Learn Android Studio and Java");
        event1.put("category", "Technology");
        event1.put("date", "2026-07-01");
        event1.put("time", "10:00 AM");
        event1.put("location", "Engineering Building");
        event1.put("seats", 120);
        event1.put("image", "");

        db.insert("events", null, event1);



        ContentValues event2 = new ContentValues();

        event2.put("title", "AI Competition");
        event2.put("description", "Artificial Intelligence Challenge");
        event2.put("category", "Competition");
        event2.put("date", "2026-07-15");
        event2.put("time", "9:00 AM");
        event2.put("location", "Birzeit University");
        event2.put("seats", 300);
        event2.put("image", "");

        db.insert("events", null, event2);



        ContentValues event3 = new ContentValues();

        event3.put("title", "Career Day");
        event3.put("description", "Meet companies and recruiters");
        event3.put("category", "Career");
        event3.put("date", "2026-08-05");
        event3.put("time", "11:00 AM");
        event3.put("location", "Main Hall");
        event3.put("seats", 200);
        event3.put("image", "");

        db.insert("events", null, event3);
        // Favorites Table
        String CREATE_FAVORITES_TABLE =
                "CREATE TABLE favorites (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "user_id INTEGER," +
                        "event_id INTEGER," +
                        "UNIQUE(user_id, event_id)" +
                        ");";
        db.execSQL(CREATE_FAVORITES_TABLE);

        // Reservations Table
        String CREATE_RESERVATIONS_TABLE =
                "CREATE TABLE reservations (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "user_id INTEGER," +
                        "event_id INTEGER," +
                        "quantity INTEGER," +
                        "reservation_type TEXT," +
                        "status TEXT" +
                        ");";

        db.execSQL(CREATE_RESERVATIONS_TABLE);
        ContentValues values = new ContentValues();

        values.put("title","AI Workshop");
        values.put("description","Introduction to AI");
        values.put("category","Technology");
        values.put("date","2026-06-10");
        values.put("time","10:00 AM");
        values.put("location","Engineering Hall");
        values.put("seats",80);
        values.put("image","");


        db.insert(
                "events",
                null,
                values
        );
        // Default Admin
        db.execSQL(
                "INSERT INTO admins(email,password) VALUES('admin@admin.com','Admin123!')"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS admins");
        db.execSQL("DROP TABLE IF EXISTS events");
        db.execSQL("DROP TABLE IF EXISTS favorites");
        db.execSQL("DROP TABLE IF EXISTS reservations");

        onCreate(db);
    }

    // Insert User
    public boolean insertUser(String email,
                              String firstName,
                              String lastName,
                              String password,
                              String gender,
                              String major,
                              String phone) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("email", email);
        values.put("first_name", firstName);
        values.put("last_name", lastName);
        values.put("password", password);
        values.put("gender", gender);
        values.put("major", major);
        values.put("phone", phone);

        long result = db.insert("users", null, values);

        return result != -1;
    }

    // Check User Login
    public boolean checkUser(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE email=? AND password=?",
                new String[]{email, password}
        );

        boolean exists = cursor.getCount() > 0;

        cursor.close();

        return exists;
    }

    // Check Admin Login
    public boolean checkAdmin(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM admins WHERE email=? AND password=?",
                new String[]{email, password}
        );

        boolean exists = cursor.getCount() > 0;

        cursor.close();

        return exists;
    }
    public Cursor getUserByEmail(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM users WHERE email=?",
                new String[]{email}
        );
    }
    public boolean updateUser(String email,
                              String firstName,
                              String lastName,
                              String phone) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("first_name", firstName);
        values.put("last_name", lastName);
        values.put("phone", phone);

        int result = db.update(
                "users",
                values,
                "email=?",
                new String[]{email}
        );

        return result > 0;
    }
    public boolean updatePassword(String email,
                                  String newPassword) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("password", newPassword);

        int result = db.update(
                "users",
                values,
                "email=?",
                new String[]{email}
        );

        return result > 0;
    }
    public boolean insertReservation(int userId,
                                     int eventId,
                                     int quantity,
                                     String reservationType,
                                     String status) {


        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();


        values.put("user_id", userId);

        values.put("event_id", eventId);

        values.put("quantity", quantity);

        values.put("reservation_type", reservationType);

        values.put("status", status);



        long result = db.insert(
                "reservations",
                null,
                values
        );


        return result != -1;

    }
    public Cursor getReservations(){

        SQLiteDatabase db = this.getReadableDatabase();


        return db.rawQuery(
                "SELECT reservations.*, events.title " +
                        "FROM reservations " +
                        "INNER JOIN events " +
                        "ON reservations.event_id = events.id",
                null
        );

    }
    public boolean insertEvent(String title,
                               String description,
                               String category,
                               String date,
                               String time,
                               String location,
                               int seats,
                               String image){

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();


        values.put("title", title);
        values.put("description", description);
        values.put("category", category);
        values.put("date", date);
        values.put("time", time);
        values.put("location", location);
        values.put("seats", seats);
        values.put("image", image);


        long result =
                db.insert(
                        "events",
                        null,
                        values
                );


        return result != -1;

    }



    public Cursor getAllEvents(){

        SQLiteDatabase db = this.getReadableDatabase();


        return db.rawQuery(
                "SELECT * FROM events",
                null
        );

    }
    public Cursor getPopularEvents() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM events ORDER BY seats DESC",
                null
        );

    }


    public Cursor getRecommendedEvents() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM events ORDER BY date ASC",
                null
        );

    }
    public Cursor getFeaturedEvents(){

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM events WHERE category='Technology' OR category='Competition'",
                null
        );

    }
    public boolean insertFavorite(int userId, int eventId){

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put("user_id", userId);

        values.put("event_id", eventId);



        long result =
                db.insertWithOnConflict(
                        "favorites",
                        null,
                        values,
                        SQLiteDatabase.CONFLICT_IGNORE
                );


        return result != -1;

    }
    public boolean deleteFavorite(int userId, int eventId){

        SQLiteDatabase db = this.getWritableDatabase();


        int result =
                db.delete(
                        "favorites",
                        "user_id=? AND event_id=?",
                        new String[]{
                                String.valueOf(userId),
                                String.valueOf(eventId)
                        }
                );


        return result > 0;

    }
    public Cursor getFavorites(int userId){

        SQLiteDatabase db =
                this.getReadableDatabase();


        return db.rawQuery(

                "SELECT events.* FROM events " +
                        "INNER JOIN favorites " +
                        "ON events.id = favorites.event_id " +
                        "WHERE favorites.user_id=?",

                new String[]{
                        String.valueOf(userId)
                }

        );

    }
    public boolean isFavorite(int userId, int eventId){

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM favorites WHERE user_id=? AND event_id=?",
                        new String[]{
                                String.valueOf(userId),
                                String.valueOf(eventId)
                        }
                );


        boolean exists = cursor.getCount() > 0;


        cursor.close();


        return exists;

    }
}