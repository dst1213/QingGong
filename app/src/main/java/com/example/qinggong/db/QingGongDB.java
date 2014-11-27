package com.example.qinggong.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

import com.example.qinggong.model.MyApplication;
import com.example.qinggong.model.QingGongEntity;
import com.example.qinggong.util.MonthUtil;
import com.example.qinggong.util.PeriodUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by ZJGJK03 on 2014/11/25.
 */
public class QingGongDB {

    public static final String DB_Name="QingGongDB";
    public static final int VERSION=1;
    static QingGongDB qingGongDB;
    SQLiteDatabase db;

    private QingGongDB() {
        QingGongOpenHelper dbhelper=new QingGongOpenHelper(MyApplication.getContext(),DB_Name,null,VERSION);
        try {
            db = dbhelper.getWritableDatabase();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static QingGongDB getInstance()
    {
        if(qingGongDB==null)
        {
            qingGongDB=new QingGongDB();
        }
        return qingGongDB;
    }

    public String getQingGong(String month,String age)
    {
        String sex=null;
        try
        {
            //通过月份和年龄作为检索条件
            Cursor cursor=db.query("qinggongtable",new String[]{month},"age=?",new String[]{age},null,null,null);
            if(cursor.moveToFirst())
            {
                do {
                    sex=cursor.getString(cursor.getColumnIndex(month));//获得性别

                }while (cursor.moveToNext());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sex;
    }

    public List<String> getMonths(String year,String sex) {
        List<String> months = new ArrayList<String>();

        PeriodUtil periodUtil=new PeriodUtil(year);
        TreeMap<String,List<String>> mapMonth=periodUtil.getMonth();
        //
        if(mapMonth.size()>=1) {
            Iterator<String> key=mapMonth.keySet().iterator();
            while (key.hasNext()) {
                String strKey=key.next();
                List<String> month=mapMonth.get(strKey);
                try {
                    Cursor cursor = db.query("qinggongtable", month.toArray(new String[]{}), "age=?", new String[]{strKey}, null, null, null);
                    if(cursor.moveToFirst()) {
                        do {
                            for (String m : month) {
                                String tmp = strKey + "年-" + MonthUtil.getMonth(cursor.getString(cursor.getColumnIndex(m))) + "月,";
                                months.add(tmp);
                            }
                        } while (cursor.moveToNext());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return months;
    }
}
