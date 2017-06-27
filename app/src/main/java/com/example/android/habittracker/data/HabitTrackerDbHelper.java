package com.example.android.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database helper for HabitTracker app. Manages database creation and version management.
 */
public class HabitTrackerDbHelper extends SQLiteOpenHelper {

    /** Name of the database file */
    private static final String DATABASE_NAME = "habit.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabitTrackerDbHelper}.
     *
     * @param context of the app
     */
    public HabitTrackerDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the habits table
        String SQL_CREATE_HABIT_TABLE =  "CREATE TABLE " + HabitTrackerContract.HabitEntry.TABLE_NAME + " ("
                + HabitTrackerContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitTrackerContract.HabitEntry.COLUMN_DATE + " TEXT NOT NULL, "
                + HabitTrackerContract.HabitEntry.COLUMN_NUMBER_TANKS + " INT NOT NULL, "
                + HabitTrackerContract.HabitEntry.COLUMN_COMMENT + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Insert habit entry into the database
    public void insertHabit(String date, int tanks, String comment){
        ContentValues values = new ContentValues();
        values.put(HabitTrackerContract.HabitEntry.COLUMN_DATE, date);
        values.put(HabitTrackerContract.HabitEntry.COLUMN_NUMBER_TANKS, tanks);
        values.put(HabitTrackerContract.HabitEntry.COLUMN_COMMENT, comment);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(HabitTrackerContract.HabitEntry.TABLE_NAME, null, values);
    }

    // Read habit entry from the database
    public Cursor readHabit(){
        SQLiteDatabase db = getReadableDatabase();

        // Set the projection
        String[] projection = {
                HabitTrackerContract.HabitEntry._ID,
                HabitTrackerContract.HabitEntry.COLUMN_NUMBER_TANKS,
                HabitTrackerContract.HabitEntry.COLUMN_DATE,
                HabitTrackerContract.HabitEntry.COLUMN_COMMENT
        };

        Cursor cursor = db.query(HabitTrackerContract.HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }
}
