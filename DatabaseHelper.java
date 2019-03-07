package com.example.programmingknowledge.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "databasebadr.db";
    public static final String TABLE_NAME = "table1";
    public static final String col_1 = "ID";
    public static final String col_2 = "name";
    public static final String col_3 = "Mob";
    public static final String col_4 = "DET";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (" + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                       + "NAME TEXT,"
                                                       + "MOB TEXT, "
                                                       + "DET TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);

    }




    public boolean insertData(String name, String Mob, String Det) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, name);
        contentValues.put(col_3, Mob);
        contentValues.put(col_4, Det);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) return false;
        else return true;
    }



    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }


    public Cursor get_name(String str) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME+" where name like '%" + str + "%'", null);
        return res;
    }
    public Cursor get_mob(String str) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME+" where Mob like '%" + str + "%'", null);
        return res;
    }

    public boolean updateData(String id, String name, String Mob, String Det) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1, id);
        contentValues.put(col_2, name);
        contentValues.put(col_3, Mob);
        contentValues.put(col_4, Det);
        db.update(TABLE_NAME, contentValues, "ID = ? ", new String[]{id});
        return true;
    }




    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        return db.delete(TABLE_NAME, "ID = ? ", new String[]{id});
    }
}
