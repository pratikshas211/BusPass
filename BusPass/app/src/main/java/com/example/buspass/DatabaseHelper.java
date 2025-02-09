//
//package com.example.buspass;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//
//    public static final String DATABASE_NAME = "user.db";
//    public static final int DATABASE_VERSION = 1;
//
//    // Table for users
//    public static final String TABLE_USERS = "users";
//    public static final String COLUMN_USER_ID = "id";
//    public static final String COLUMN_USERNAME = "username";
//    public static final String COLUMN_EMAIL = "email";
//    public static final String COLUMN_PASSWORD = "password";
//
//    // Table for class
//    public static final String TABLE_CARDS = "cards";
//    public static final String COLUMN_CLASS_ID = "id";
//    public static final String COLUMN_NAME = "name";
//    public static final String COLUMN_GENDER = "gender";
//    public static final String COLUMN_CONTACT = "contact";
//    public static final String COLUMN_CATEGORY = "category";
//    public static final String COLUMN_SOURCE = "source";
//    public static final String COLUMN_DESTINATION = "destination";
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // Create table for users
//        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
//                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + COLUMN_USERNAME + " TEXT, "
//                + COLUMN_EMAIL + " TEXT, "
//                + COLUMN_PASSWORD + " TEXT)";
//        db.execSQL(CREATE_USERS_TABLE);
//
//        // Create table for class
//        String CREATE_CLASS_TABLE = "CREATE TABLE " + TABLE_CARDS + " ("
//                + COLUMN_CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + COLUMN_NAME + " TEXT, "
//                + COLUMN_GENDER + " TEXT, "
//                + COLUMN_CONTACT + " TEXT, "
//                + COLUMN_CATEGORY + " TEXT, "
//                + COLUMN_SOURCE + " TEXT, "
//                + COLUMN_DESTINATION + " TEXT)";
//        db.execSQL(CREATE_CLASS_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
//        onCreate(db);
//    }
//
//    // Insert a new user into the database
//    public boolean insertUser(String username, String email, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USERNAME, username);
//        values.put(COLUMN_EMAIL, email);
//        values.put(COLUMN_PASSWORD, password);
//
//        long result = db.insert(TABLE_USERS, null, values);
//        db.close();
//
//        return result != -1;
//    }
//
//    // Check if the user exists with the given username and password
//    public boolean checkUser(String username, String password) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columns = {COLUMN_USER_ID};
//        String selection = COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?";
//        String[] selectionArgs = {username, password};
//        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
//        int cursorCount = cursor.getCount();
//        cursor.close();
//        db.close();
//
//        return cursorCount > 0;
//    }
//
//    // Insert data into the class table
////    public boolean insertClass(String name, String gender, String contact, String category, String source, String destination) {
////        SQLiteDatabase db = this.getWritableDatabase();
////        ContentValues values = new ContentValues();
////        values.put(COLUMN_NAME, name);
////        values.put(COLUMN_GENDER, gender);
////        values.put(COLUMN_CONTACT, contact);
////        values.put(COLUMN_CATEGORY, category);
////        values.put(COLUMN_SOURCE, source);
////        values.put(COLUMN_DESTINATION, destination);
////
////        long result = db.insert(TABLE_CARDSTABLE_CARDS, null, values);
////        db.close();
////
////        return result != -1;
////    }
//    // Method to retrieve data from the class table
//    public Cursor getClassData() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT * FROM " + TABLE_CARDS;
//        return db.rawQuery(query, null);
//    }
//
//    public boolean insertData(String name, String contact, String gender, String category, String source, String destination) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NAME, name);
//        values.put(COLUMN_GENDER, gender);
//        values.put(COLUMN_CONTACT, contact);
//        values.put(COLUMN_CATEGORY, category);
//        values.put(COLUMN_SOURCE, source);
//        values.put(COLUMN_DESTINATION, destination);
//
//        long result = db.insert(TABLE_CARDS, null, values);
//        db.close();
//
//        return result != -1;
//    }
//}

package com.example.buspass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "user.db";
    public static final int DATABASE_VERSION = 2; // Increment the version to ensure table updates

    // Table for users
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    // Table for cards
    public static final String TABLE_CARDS = "cards";
    public static final String COLUMN_CARD_ID = "id";  // Renamed for clarity
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_CONTACT = "contact";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_SOURCE = "source";
    public static final String COLUMN_DESTINATION = "destination";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table for users
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);

        // Create table for cards
        String CREATE_CARDS_TABLE = "CREATE TABLE " + TABLE_CARDS + " ("
                + COLUMN_CARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_GENDER + " TEXT, "
                + COLUMN_CONTACT + " TEXT, "
                + COLUMN_CATEGORY + " TEXT, "
                + COLUMN_SOURCE + " TEXT, "
                + COLUMN_DESTINATION + " TEXT)";
        db.execSQL(CREATE_CARDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
        // Create new tables
        onCreate(db);
    }

//     Insert a new user into the database
    public boolean insertUser(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        return result != -1;
    }

    // Check if the user exists with the given username and password
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_USER_ID};
        String selection = COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }

    // Insert data into the cards table
    public boolean insertData(String name, String contact, String gender, String category, String source, String destination) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_CONTACT, contact);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_SOURCE, source);
        values.put(COLUMN_DESTINATION, destination);

        long result = db.insert(TABLE_CARDS, null, values);
        db.close();

        return result != -1;
    }

    // Retrieve data from the class table
    public Cursor getClassData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CARDS;
        return db.rawQuery(query, null);
    }
}

