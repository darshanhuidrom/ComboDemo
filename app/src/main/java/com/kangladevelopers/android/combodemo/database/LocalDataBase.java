package com.kangladevelopers.android.combodemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DARSHAN HUIDROJM on 7/25/2016.
 */
abstract public class LocalDataBase {


    private final OpenerHelper mHelper;
    private SQLiteDatabase mDB;
    private String mDatabaseName;


    public LocalDataBase(Context context){
        mDatabaseName = DatabaseConstant.DATA_BASE_NAME;
        mHelper = new OpenerHelper(context);
        mDB = mHelper.getWritableDatabase();
    }


    private class OpenerHelper extends SQLiteOpenHelper {
        public OpenerHelper(Context context) {
            super(context, DatabaseConstant.DATA_BASE_NAME, null, DatabaseConstant.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(getCreateTableString());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(DatabaseConstant.TABLE_NAME);

        }
    }


    public int update(){
        return onUpdate(mDB);
    }

    abstract public  String getCreateTableString();
    abstract public int onUpdate(SQLiteDatabase db);



}
