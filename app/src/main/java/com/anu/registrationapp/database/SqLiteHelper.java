package com.anu.registrationapp.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.anu.registrationapp.models.User;

import java.util.ArrayList;
import java.util.List;

public class SqLiteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "users_db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "users";
    public static final String USER_NAME = "username";
    public static final String USER_EMAIL = "useremail";
    public static final String USER_PHONE_NUMBER = "userphonenumber";

    public SqLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, useremail TEXT, userphonenumber TEXT )";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(User user) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_NAME, user.getUsername());
        cv.put(USER_EMAIL, user.getEmail());
        cv.put(USER_PHONE_NUMBER, user.getPhoneNumber());

        return sqLiteDatabase.insert(TABLE_NAME, null, cv);
    }

    public List<User> getAllUsers() {

        List<User> usersList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String query = "SELECT * FROM users ORDER BY id";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        while (cursor.moveToNext() && cursor.getCount() > 0) {

            User user = new User();
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(USER_NAME));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(USER_EMAIL));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(USER_PHONE_NUMBER));

            user.setUsername(name);
            user.setEmail(email);
            user.setPhoneNumber(phone);
            usersList.add(user);

        }
        cursor.close();
        return usersList;
    }

    public boolean isUserAlreadyExist(String userEmail) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        String checkQuery = "SELECT * FROM users WHERE useremail='" + userEmail + "'";
        cursor = sqLiteDatabase.rawQuery(checkQuery, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

}
