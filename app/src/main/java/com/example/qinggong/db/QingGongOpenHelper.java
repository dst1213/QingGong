package com.example.qinggong.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ZJGJK03 on 2014/11/25.
 */
public class QingGongOpenHelper extends SQLiteOpenHelper {

    public static final String CreateTable="create table qinggongtable(age int primary key,one text,two text,three text,four text,five text,six text,seven text,eight text,nine text,ten text,eleven text,twelve text)";


    public QingGongOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(db==null)
            return;
        try
        {
            db.execSQL(CreateTable);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
