package com.example.android.habittracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.android.habittracker.data.HabitTrackerContract.HabitEntry;
import com.example.android.habittracker.data.HabitTrackerDbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabitTrackerDbHelper dbHelper = new HabitTrackerDbHelper(this);

        //Get current date
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = formatter.format(date);

        // Insert Dummy Data on create
        dbHelper.insertHabit(dateString,
                5,
                getString(R.string.dummy_comment_1));

        dbHelper.insertHabit(dateString,
                2,
                getString(R.string.dummy_comment_2));

        Cursor cursor = dbHelper.readHabit();
        while(cursor.moveToNext()){
            // Show database rows in the log for verify inserted data
            Log.v(LOG_TAG, "HabitEntry :" +
                    cursor.getInt(cursor.getColumnIndex(HabitEntry._ID)) + " " +
                    cursor.getString(cursor.getColumnIndex(HabitEntry.COLUMN_DATE)) + " " +
                    cursor.getInt(cursor.getColumnIndex(HabitEntry.COLUMN_NUMBER_TANKS)) + " " +
                    cursor.getString(cursor.getColumnIndex(HabitEntry.COLUMN_COMMENT)));
        }
        // Always close the cursor when you're done reading from it. This releases all its
        // resources and makes it invalid.
        cursor.close();
        // Display Database Info
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the habits database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        HabitTrackerDbHelper mDbHelper = new HabitTrackerDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM habits"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor = db.rawQuery("SELECT * FROM " + HabitEntry.TABLE_NAME, null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // habits table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_habit);
            displayView.setText(getString(R.string.rows_number) + cursor.getCount());
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}