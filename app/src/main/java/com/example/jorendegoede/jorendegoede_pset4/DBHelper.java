package com.example.jorendegoede.jorendegoede_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Joren de Goede on 8-5-2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Static strings
    private static final String DATABASE_NAME = "Name";
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE = "Task";
    public static final String DATABASE_COLUMN = "TaskName";
    public static final String DATABASE_CHECK = "Done";

    //    private static final String DATABASE_ID = "_id";

    // Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /////////////
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String query = "CREATE TABLE " + DATABASE_TABLE + "("
//            + DATABASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATABASE_COLUMN
//            + " TEXT NOT NULL," + DATABASE_CHECK;
//
//        db.execSQL(query);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL);", DATABASE_TABLE, DATABASE_COLUMN, DATABASE_CHECK);
        db.execSQL(query);
    }

//    public void create(Note contact) {
//        SQLiteDatabase db = getWritableDatabase();
//        onUpgrade(db, 1, 1);
//        ContentValues values = new ContentValues();
//        values.put(DATABASE_COLUMN, contact.getTaskName());
//        values.put(DATABASE_CHECK, contact.getCheckbox());
//        db.insert(DATABASE_TABLE, null, values);
//        db.close();
//    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format("DROP TABLE IF EXISTS %s", DATABASE_TABLE);
        db.execSQL(query);
        onCreate(db);
    }

    public void insertNewTask(String task) {
        // Make writable
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATABASE_COLUMN, task);
        db.insertWithOnConflict(DATABASE_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

//    public int update(Note contact) {
//        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(DATABASE_COLUMN, contact.getTask());
//        values.put(DATABASE_CHECK, contact.getCheckbox());
//
//        // Return whether it has succeeded or not
//        return db.update(DATABASE_TABLE, values, DATABASE_ID + " = ? ", new String[] {String.valueOf(contact.getID())});
//    }

    /////////////
//    public void deleteTask(String task) {
//        // Make Writable
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // Delete taskcolumn
//        db.delete(DATABASE_TABLE, " " + DATABASE_ID + " = ? ", new String[]{task});
//        db.close();
//    }


    public void deleteTask(String task) {
        // Make Writable
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete taskcolumn
        db.delete(DATABASE_TABLE, DATABASE_COLUMN + " = ?", new String[]{task});
        db.close();
    }


//    public ArrayList<String> getTaskList() {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // A list of custom objects, to store our data.
//        ArrayList<String> taskList = new ArrayList<>();
//
//        // Create a query to give to the cursor
//        Cursor cursor = db.query(DATABASE_TABLE, new String[]{DATABASE_COLUMN}, null, null, null, null, null);
//        while (cursor.moveToNext()) {
//            int index = cursor.getColumnIndex(DATABASE_COLUMN);
//            taskList.add(cursor.getString(index));
//        }
//        cursor.close();
//        db.close();
//        return taskList;
//    }

    public ArrayList<String> getTaskList() {
        SQLiteDatabase db = this.getReadableDatabase();

        // A list of custom objects, to store our data.
        ArrayList<String> taskList = new ArrayList<>();

        // Create a query to give to the cursor
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{DATABASE_COLUMN}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DATABASE_COLUMN);
            taskList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return taskList;
    }


    //////////
//    public ArrayList<String> getTaskList() {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // A list of custom objects, to store our data.
//        ArrayList<String> taskList = new ArrayList<>();
//
//        // Create a query to give to the cursor
//        String query = "SELECT " + DATABASE_ID + ", " + DATABASE_COLUMN + ", " + DATABASE_CHECK + "FROM " + DATABASE_TABLE;
//        Cursor cursor = db.rawQuery(query, null);
//
//        // SET cursor to the beginning of the database
//        if (cursor.moveToFirst()){
//            do {
//                // add id, done-status and to-do from current row TodoList
//                String task = cursor.getString(cursor.getColumnIndex(DATABASE_COLUMN));
//                String done = cursor.getString(cursor.getColumnIndex(DATABASE_CHECK));
//                int id = cursor.getInt(cursor.getColumnIndex(DATABASE_ID));
//
//                // Create a contact object with newly retrieved data
//                Note note = new Note(task, done, id);
//                notes.add(note);
//            }
//            // While there is still a next entry
//            while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//        return taskList;
//    }

}



























    /////////////////////////
//    // Static strings
//    private static final String KEY_ID = "_id";
//    private static final String KEY_NAME = "name";
//    private static final String KEY_NUMBER = "number";
//    private static final String TABLE = "contactTable";

//    // Constructor
//    public DBHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CREATE_DB = "CREATE TABLE " + TABLE + "("
//                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME
//                + " TEXT NOT NULL," + KEY_NUMBER + "TEXT NOT NULL";
//
//        db.execSQL(CREATE_DB);
//    }
//
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
//        onCreate(db);
//    }
//
//    public void create(Note contact) {
//        SQLiteDatabase db = getWritableDatabase();
//        onUpgrade(db, 1, 1);
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, contact.getName());
//        values.put(KEY_NUMBER, contact.getNumber());
//        db.insert(TABLE, null, values);
//        db.close();
//    }
//
//    public ArrayList<Note> read() {
//        SQLiteDatabase db = getReadableDatabase();
//
//        // A list of custom objects, to store our data.
//        ArrayList<Note> contacts = new ArrayList<>();
//
//        // Create a query to give to the cursor
//        String query = "SELECT " + KEY_ID + ", " + KEY_NAME + ", " + KEY_NUMBER + "FROM " + TABLE;
//        Cursor cursor = db.rawQuery(query, null);
//
//        // SET cursor to the beginning of the database
//        if (cursor.moveToFirst()){
//            do {
//                // add id, done-status and to-do from current row TodoList
//                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
//                String number = cursor.getString(cursor.getColumnIndex(KEY_NUMBER));
//                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
//
//                // Create a contact object with newly retrieved data
//                Note contact = new Note(name, number, id);
//                contacts.add(contact);
//            }
//            // While there is still a next entry
//            while (cursor.moveToNext());
//        }
//        // Close the database and the cursor
//        cursor.close();
//        db.close();
//        return contacts;
//    }
//
//    public int update(Note contact) {
//        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, contact.getName());
//        values.put(KEY_NUMBER, contact.getNumber());
//
//        // Return whether it has succeeded or not
//        return db.update(TABLE, values, KEY_ID + " = ? ", new String[] {String.valueOf(contact.getID())});
//    }
//
//    public void delete(Note contact) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.delete(TABLE, " " + KEY_ID + " = ? ", new String[] {String.valueOf(contact.getID())});
//        db.close();
//    }

