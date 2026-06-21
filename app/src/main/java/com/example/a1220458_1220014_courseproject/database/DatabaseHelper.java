package com.example.a1220458_1220014_courseproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a1220458_1220014_courseproject.models.Event;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "events_app.db";
    private static final int DATABASE_VERSION = 4;

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
    public void insertEvent(Event event){


        SQLiteDatabase db =
                this.getWritableDatabase();


        ContentValues values =
                new ContentValues();


        values.put("title", event.getTitle());

        values.put("description", event.getDescription());

        values.put("category", event.getCategory());

        values.put("date", event.getDate());

        values.put("time", event.getTime());

        values.put("location", event.getLocation());

        values.put("seats", event.getSeats());

        values.put("image", event.getImage());



        long result =
                db.insert(
                        "events",
                        null,
                        values
                );


        System.out.println("INSERT RESULT = " + result);


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
    public void clearEvents(){


        SQLiteDatabase db =
                this.getWritableDatabase();


        db.delete(
                "events",
                null,
                null
        );


    }
    public int getEventSeats(int eventId){

        SQLiteDatabase db =
                this.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT seats FROM events WHERE id=?",
                        new String[]{
                                String.valueOf(eventId)
                        });

        int seats = 0;

        if(cursor.moveToFirst()){

            seats =
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow("seats")
                    );

        }

        cursor.close();

        return seats;

    }
    public void updateSeats(
            int eventId,
            int newSeats){

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(
                "seats",
                newSeats
        );

        db.update(
                "events",
                values,
                "id=?",
                new String[]{
                        String.valueOf(eventId)
                });

    }
    public int getReservedSeats(int eventId){

        SQLiteDatabase db =
                this.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT SUM(quantity) FROM reservations WHERE event_id=?",
                        new String[]{
                                String.valueOf(eventId)
                        });

        int reserved = 0;

        if(cursor.moveToFirst()){

            reserved =
                    cursor.isNull(0)
                            ? 0
                            : cursor.getInt(0);

        }

        cursor.close();

        return reserved;

    }
    public int getUserId(String email){

        SQLiteDatabase db =
                this.getReadableDatabase();


        Cursor cursor =
                db.rawQuery(
                        "SELECT id FROM users WHERE email=?",
                        new String[]{email}
                );


        int id = -1;


        if(cursor.moveToFirst()){

            id = cursor.getInt(0);

        }


        cursor.close();


        return id;

    }
    public Cursor getUserReservations(int userId){

        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(
                "SELECT events.title, reservations.quantity, reservations.reservation_type, reservations.status " +
                        "FROM reservations " +
                        "INNER JOIN events ON reservations.event_id = events.id " +
                        "WHERE reservations.user_id = ?",
                new String[]{
                        String.valueOf(userId)
                }
        );

    }
    public void checkUsers(){

        Cursor cursor =
                getReadableDatabase()
                        .rawQuery(
                                "SELECT * FROM users",
                                null
                        );


        System.out.println("USERS COUNT = " + cursor.getCount());


        while(cursor.moveToNext()){

            System.out.println(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("email")
                    )
            );

        }

        cursor.close();
    }
    public boolean insertAdmin(String email,
                               String password){

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put("email", email);
        values.put("password", password);

        long result =
                db.insert(
                        "admins",
                        null,
                        values
                );

        return result != -1;
    }


    public Cursor getAllUsers(){

        SQLiteDatabase db =
                this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM users",
                null
        );
    }


    public boolean deleteUser(int userId){

        SQLiteDatabase db =
                this.getWritableDatabase();

        int result =
                db.delete(
                        "users",
                        "id=?",
                        new String[]{
                                String.valueOf(userId)
                        }
                );

        return result > 0;
    }
}