package com.example.qinggong.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ZJGJK03 on 2014/11/25.
 */
public class QingGongOpenHelper extends SQLiteOpenHelper {

    public static final String CreateTable="create table qinggongtable(age int primary key,one text,two text,three text,four text,five text,six text,seven text,eight text,nine text,ten text,eleven text,twelve text)";
    public static final String Insert19="insert into qinggongtable values(19,'男','女','男','女','女','男','男','男','男','男','女','女')";
    public static final String Insert20="insert into qinggongtable values(20,'女','男','女','男','男','男','男','男','男','女','男','男')";
    public static final String Insert21="insert into qinggongtable values(21,'男','女','女','女','女','女','女','女','女','女','女','女')";
    public static final String Insert22="insert into qinggongtable values(22,'女','男','男','女','男','女','女','男','女','女','女','女')";
    public static final String Insert23="insert into qinggongtable values(23,'男','男','女','男','男','女','男','女','男','男','男','女')";
    public static final String Insert24="insert into qinggongtable values(24,'男','女','男','男','女','男','男','女','女','女','女','女')";
    public static final String Insert25="insert into qinggongtable values(25,'女','男','男','女','女','男','女','男','男','男','男','男')";
    public static final String Insert26="insert into qinggongtable values(26,'男','女','女','男','女','女','男','女','男','男','男','男')";
    public static final String Insert27="insert into qinggongtable values(27,'女','男','女','男','女','女','男','男','男','男','女','男')";
    public static final String Insert28="insert into qinggongtable values(28,'男','女','男','女','女','女','男','男','男','男','女','女')";
    public static final String Insert29="insert into qinggongtable values(29,'女','男','女','女','男','男','男','男','男','男','女','女')";
    public static final String Insert30="insert into qinggongtable values(30,'男','女','女','女','女','女','女','女','女','女','男','男')";
    public static final String Insert31="insert into qinggongtable values(31,'男','女','男','女','女','女','女','女','女','女','女','男')";
    public static final String Insert32="insert into qinggongtable values(32,'男','女','男','女','女','女','女','女','女','女','女','男')";
    public static final String Insert33="insert into qinggongtable values(33,'女','男','女','男','女','女','女','男','女','女','女','男')";
    public static final String Insert34="insert into qinggongtable values(34,'男','女','男','女','女','女','女','女','女','女','男','男')";
    public static final String Insert35="insert into qinggongtable values(35,'男','男','女','男','女','女','女','男','女','女','男','男')";
    public static final String Insert36="insert into qinggongtable values(36,'女','男','男','女','男','女','女','女','男','男','男','男')";
    public static final String Insert37="insert into qinggongtable values(37,'男','女','男','男','女','男','女','男','女','男','女','男')";
    public static final String Insert38="insert into qinggongtable values(38,'女','男','女','男','男','女','男','女','男','女','男','女')";
    public static final String Insert39="insert into qinggongtable values(39,'男','女','男','男','男','女','女','男','女','男','女','女')";
    public static final String Insert40="insert into qinggongtable values(40,'女','男','女','男','女','男','男','女','男','女','男','女')";
    public static final String Insert41="insert into qinggongtable values(41,'男','女','男','女','男','女','男','男','女','男','女','男')";

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
            db.execSQL(Insert19);
            db.execSQL(Insert20);
            db.execSQL(Insert21);
            db.execSQL(Insert22);
            db.execSQL(Insert23);
            db.execSQL(Insert24);
            db.execSQL(Insert25);
            db.execSQL(Insert26);
            db.execSQL(Insert27);
            db.execSQL(Insert28);
            db.execSQL(Insert29);
            db.execSQL(Insert30);
            db.execSQL(Insert31);
            db.execSQL(Insert32);
            db.execSQL(Insert33);
            db.execSQL(Insert34);
            db.execSQL(Insert35);
            db.execSQL(Insert36);
            db.execSQL(Insert37);
            db.execSQL(Insert38);
            db.execSQL(Insert39);
            db.execSQL(Insert40);
            db.execSQL(Insert41);
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
