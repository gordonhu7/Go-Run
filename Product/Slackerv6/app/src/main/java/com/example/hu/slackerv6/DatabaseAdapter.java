package com.example.hu.slackerv6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by Hu on 2/21/2015.
 */
public class DatabaseAdapter  {

    Database helper;
    public DatabaseAdapter(Context context){
        helper = new Database(context);
    }
    public long insertData(String date, String place, String duration, String distance, String description){

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(Database.DATE, date);
        contentValues.put(Database.PLACE, place);
        contentValues.put(Database.DISTANCE, distance);
        contentValues.put(Database.DURATION, duration);
        contentValues.put(Database.DESCRIPTION, description);
        long id = db.insert(Database.TABLE_NAME, null, contentValues );
        return id;
    }

    public String getAllData(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Database.UID, Database.DATE, Database.PLACE,Database.DISTANCE, Database.DISTANCE,Database.DURATION,Database.DESCRIPTION};
        Cursor cursor = db.query(Database.TABLE_NAME, columns, null, null, null, null,null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            int cid = cursor.getInt(0);
            String date = cursor.getString(1);
            String place = cursor.getString(2);
            String distance = cursor.getString(3);
            String duration = cursor.getString(4);
            String description = cursor.getString(5);
            buffer.append(cid +" " + date + " " + place + " " + distance + " " + duration + " "
                    + description + "\n");
        }
        return buffer.toString();
    }

    static class Database extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "OldRuns";
        private static final String TABLE_NAME = "Data";
        private static final String UID = "_id";
        private static final int DATABASE_VERSION = 1;
        private static final String DATE = "date";
        private static final String PLACE = "place";
        private static final String DURATION = "duration";
        private static final String DISTANCE = "distance";
        private static final String DESCRIPTION = "description";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "+UID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ""+DATE+" VARCHAR(255), "+PLACE+" VARCHAR(255),"+DISTANCE+" VARCHAR(255),"+DURATION+" VARCHAR(255), "+DESCRIPTION+" VARCHAR(255));";
        private Context context;
        private static final String DROP_TABLE="DROP TABLE IF EXISTS " + TABLE_NAME;

        public Database(Context context) {   //constructor
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
        }

        @Override //might need to do a try catch thing here
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)  {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }

    }
}

