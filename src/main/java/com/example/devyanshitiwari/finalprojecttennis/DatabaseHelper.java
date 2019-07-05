package com.example.devyanshitiwari.finalprojecttennis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "PLAYER_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PLAYER = "playerdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_FORMAT = "format";
    private static final String KEY_LASTSET = "lastset";
    private static final String KEY_Won = "lastset";

    private static final String CREATE_TABLE_PLAYER = "CREATE TABLE "
            + TABLE_PLAYER+ "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT, "+ KEY_FORMAT + " TEXT,"+ KEY_LASTSET + " TEXT,"+KEY_Won+"TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_PLAYER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PLAYER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_PLAYER + "'");
        onCreate(db);
    }

    public long addPlayerDetail(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        long insert = db.insert(TABLE_PLAYER, null, values);

        return insert;
    }
    public long addFormatDetail(String f) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FORMAT, f);
        long insert = db.insert(TABLE_PLAYER, null, values);

        return insert;
    }

    public long addlastSetDetail(String last) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LASTSET, last);
        long insert = db.insert(TABLE_PLAYER, null, values);

        return insert;
    }

    public long addVictoryDetail(String vic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Won, vic);
        long insert = db.insert(TABLE_PLAYER, null, values);
        return insert;
    }

    public void getAllPlayersList() {
        ArrayList<String> nameArrayList = new ArrayList<String>();
        String name="";
        String selectQuery = "SELECT   * FROM " + TABLE_PLAYER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                name = c.getString(c.getColumnIndex(KEY_NAME));
                nameArrayList.add(name);
            } while (c.moveToNext());
            Log.d("array",nameArrayList.toString());
        }


    }
}