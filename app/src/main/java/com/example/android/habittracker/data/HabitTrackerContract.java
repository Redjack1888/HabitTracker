package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * API Contract for the HabitTracker app.
 */
public class HabitTrackerContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private HabitTrackerContract() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static class HabitEntry implements BaseColumns {
        /** Name of database table for habits */
        public static final String TABLE_NAME = "habits";

        /**
         * Unique ID number for the pet (only for use in the database table).
         *
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;
        /**
         * Date of the habit performance.
         *
         * Type: TEXT
         */
        public static final String COLUMN_DATE = "date";
        /**
         * Number of tanks that the user swimmed.
         *
         * Type: INTEGER
         */
        public static final String COLUMN_NUMBER_TANKS = "number_tanks";
        /**
         * Comment about the habit performance.
         *
         * Type: TEXT
         */
        public static final String COLUMN_COMMENT = "comment";
    }

}