package com.example.qinggong.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

import com.example.qinggong.model.MyApplication;
import com.example.qinggong.model.QingGongEntity;
import com.example.qinggong.util.LogUtil;
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

    public PeriodUtil periodUtil;
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

    public String getQingGong(String month,int age)
    {
        String sex="";
        if(age>=19 && age<=41) {
            if (MonthUtil.isNumMonth(month))
                month = MonthUtil.getMonthEn(Integer.parseInt(month));
            if (MonthUtil.isEnMonth(month)) {
                try {
                    //通过月份和年龄作为检索条件
                    Cursor cursor = db.query("qinggongtable", new String[]{month}, "age=?", new String[]{age + ""}, null, null, null);
                    if (cursor.moveToFirst()) {
                        do {
                            sex = cursor.getString(cursor.getColumnIndex(month));//获得性别

                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sex;
    }

    public List<String> getMonths(int year,int age,String sex) {
        List<String> months = new ArrayList<String>();
//        if(!((age-1)>=19 && (age-1)<=41))
//            return months;
        age--;//先减掉一岁，因为获得的月份是从小到大的，所以会先获得小的年份
        periodUtil=new PeriodUtil(year);
        //periodUtil.setCurrentMonth(1);//这里是为了测试而修改
        TreeMap<String,List<String>> mapMonth=periodUtil.getMonth();
        if(mapMonth.size()>=1) {
            Iterator<String> key=mapMonth.keySet().iterator();
            while (key.hasNext()) {
                String strKey=key.next();
                List<String> month=mapMonth.get(strKey);
                try {
                    Cursor cursor = db.query("qinggongtable", month.toArray(new String[]{}), "age=?", new String[]{age+""}, null, null, null);
                    if(cursor.moveToFirst()) {
                        do {
                            for (String m : month) {
                                if(cursor.getString(cursor.getColumnIndex(m)).contains(sex))
                                {
                                    String tmp = strKey + "年-" + MonthUtil.getMonthNum(m) + "月,";
                                    months.add(tmp);
                                }
                            }
                        } while (cursor.moveToNext());
                        cursor.close();
                    }
                    age+=1;
                    if(!(age>=19 && age<=41))//这里判断年龄是否超过，如果超过则直接中断。其实也没有必要，因为不在范围内的年龄，在数据库里根本没有数据。
                        break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return months;
    }
    public List<String> getMonths(String age)
    {
        List<String> month=new ArrayList<String>();
        try
        {
            Cursor cursor=db.query("qinggongtable",null,"age=?",new String[]{age},null,null,null);
            if(cursor.moveToFirst())
            {
                for(int i=1;i<=12;i++)
                {
                    month.add(i+"月==="+cursor.getString(i)+"孩");
                }
            }
            cursor.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return month;
    }
}
